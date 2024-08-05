package Part1;

import java.util.List;
import java.util.Scanner;

public class SearchPlayers {
    Scanner scanner = new Scanner(System.in);
    boolean mainMenu = false;               // (mainMenu == true) moves to the main method

    // create a find object for searching purpose
    Find find = new Find();

    public void playerSearchOption(List<Player> playerList, List<Country> countryList, List<Club> clubs) {

        /**
         * (1) By Player Name
         * (2) By Club and Country
         * (3) By Position
         * (4) By Salary Range
         * (5) Country-wise player count
         * (6) Back to Main Menu
         */

        // the loop continues untill 6 is pressed and (mainMenu ==  true)

        while(!mainMenu) {

            System.out.println(); //print a blank line after every loop

            System.out.println("(1) By Player Name");
            System.out.println("(2) By Club and Country");
            System.out.println("(3) By Position");
            System.out.println("(4) By Salary Range");
            System.out.println("(5) Country-wise player count");
            System.out.println("(6) Back to Main Menu");

            // create a object of input index class to get the input

            InputIndex inputIndex = new InputIndex();
            int index = inputIndex.getIndex(6);

            switch (index) {
                case 1:       // enter a player name and show player info
                {
                    int found ;
                    String pName;
                    System.out.println("Enter a player name: ");
                    pName = scanner.nextLine();

                    found = find.findPlayer(playerList,pName);

                    if (found == -1)
                        System.out.println("No such player with this name");
                    else{
                        playerList.get(found).printPlayer();
                    }


                    break;
                }
                /**
                 * If the user chooses option (2), you should ask the user to input a country first and then ask the
                 * user to input a club. The user can input a club name (e.g., “Manchester United”) or “ANY.” If
                 * “ANY” is inputted, the program should display all players of this country from the database.
                 * Display all information of players with this country and this club (or all clubs if “ANY” is inputted)
                 * if found, or display “No such player with this country and club” if not found.
                 */
                case 2: {
                    String countryName;
                    String clubName;
                    int clubFound ;

                    System.out.println("Enter the Country name : ");
                    countryName = scanner.nextLine();


                    Country country = new Country();    // create a country object

                    int countryFound = find.findCountry(countryList,countryName);

                    if (countryFound == -1) {
                        System.out.println("No such player with this country and club");
                        break;
                    }else{
                        country = countryList.get(countryFound);
                    }

                    System.out.println("Enter club name or type (ANY) to get list of all players  : ");
                    clubName = scanner.nextLine();



                    /**
                     * print players if----> clubname  = ANY
                     * else ---->check if the clubname matches any clubs of that countryPlayer list
                     * if matches----> print that player
                     * else print not found and end  the switch loop
                     */

                    if (clubName.equalsIgnoreCase("ANY")) {
                        country.printCountryPlayers();

                    } else {
                        clubFound = find.findClub(clubs,clubName);

                        if (clubFound == -1) {
                            System.out.println("No such player with this country and club");
                            break;
                        }
                        else{
                            for(Player player:country.countryPlayers){
                                if(player.getClub().equalsIgnoreCase(clubName)){
                                    player.printPlayer();
                                }
                            }
                        }


                    }
                    break;
                }


                /**
                 * If the user chooses option (3), you should ask the user to input a position and
                 * then search the database for any player with the specific position.
                 * If found, display all the players or display “No such player with this position” if not found.
                 */
                case 3: {
                    String positionName;
                    boolean positionFound = false;
                    System.out.println("Enter the position of the players : ");
                    positionName = scanner.nextLine();

                    for (Player player : playerList) {
                        if (player.getPosition().equalsIgnoreCase(positionName)) {
                            player.printPlayer();
                            System.out.println(); // print a blank line after every player
                            positionFound = true;
                        }
                    }
                    if (!positionFound) System.out.println("No such player with this position");
                    break;


                }

                /**
                 *If the user chooses option (4), you should ask the user to input two numbers as range and
                 * then search the database for any player with a weekly salary in this range.
                 * If found, display all the players or display “No such player with this weekly salary range” if not found.
                 */
                case 4: {
                    double upperRange, lowerRange;
                    boolean rangeFound = false;
                    System.out.println("Enter the Weekly Salary Range : ");
                    System.out.print("Upper Range: ");
                    upperRange = scanner.nextDouble();
                    System.out.print("Lower Range: ");
                    lowerRange = scanner.nextDouble();

                    for (Player player : playerList) {
                        double salary = player.getWeeklySalary();
                        if (salary >= lowerRange && salary <= upperRange) {
                            player.printPlayer();
                            System.out.println(); // print a blank line after every player
                            rangeFound = true;
                        }
                    }
                    if (!rangeFound) System.out.println("No such player with this weekly salary range");

                    break;

                }

                /**
                 * If the user chooses option (5), display the country-wise count of players.
                 * You don’t need to take any input from the users.
                 */
                case 5: {
                    for (Country country : countryList) {
                        System.out.println(country.getName() + ": " + country.countryPlayers.size());
                    }
                    break;
                }
                /**
                 * If the user chooses option (6), the program should go back to the main menu.
                 */
                case 6: {
                    mainMenu = true;
                    return;
                }
            }
        }
    }

}
