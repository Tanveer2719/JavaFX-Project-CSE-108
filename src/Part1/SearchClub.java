package Part1;

import java.util.*;

public class SearchClub {
    Scanner scanner = new Scanner(System.in);       // scanner to take input
    boolean mainMenu = false;               // (mainMenu == true) moves to the main method
    /**
     * Club Searching Options:
     * (1) Player(s) with the maximum salary of a club
     * (2) Player(s) with the maximum age of a club
     * (3) Player(s) with the maximum height of a club
     * (4) Total yearly salary of a club
     * (5) Back to Main Menu
     *
     */
    public void clubSearchOption(List<Player> playerList, List<Club> clubs) {

        // create a find object
        Find find = new Find();

        // create getInformation object to find the req info
        GetInformation getInformation = new GetInformation();

        // loop to check wheather the main menu key is pressed or not
        while (!mainMenu){
            System.out.println();   // a new line after every loop

            List<Player>sortedPlayers = new ArrayList();

            System.out.println("(1) Player(s) with maximum salary of a club");
            System.out.println("(2) Player(s) with the maximum age of a club");
            System.out.println("(3) Player(s) with the maximum height of a club");
            System.out.println("(4) Total yearly salary of a club");
            System.out.println("(5) Back to Main Menu");

            //InputIndex object to take the input
            InputIndex inputIndex = new InputIndex();
            int index = inputIndex.getIndex(5);


            switch (index){

                /**
                 *If the user chooses option (1), you should ask the user to input a club name
                 * and then search the players with the maximum weekly salary.
                 * Display all information of these players if the club is found,
                 * or display “No such club with this name” if not found.
                 */

                case 1:
                {
                    System.out.println();

                    System.out.println("Enter a club name: ");
                    String clubname = scanner.nextLine();

                    // create a club class to match the given input club
                    Club club = new Club();

                    //checks if the club exists
                    int clubFound = find.findClub(clubs,clubname);

                    // exit the switch if not found
                    if(clubFound == -1){
                        System.out.println("No such club with this name");
                        break;
                    }

                    club = clubs.get(clubFound);

                // print the player(s) with highest salary
                   sortedPlayers = getInformation.getHighestSalary(club.clubPlayers);
                    for(Player player:sortedPlayers)
                    {
                        player.printPlayer();
                        System.out.println();
                    }

                    break;

                }

                /**
                 * If the user chooses option (2), you should ask the user to input a club name
                 * and then search the players with the maximum age.
                 * Display all information of these players if the club is found, or display “No such club with this name” if not found.
                 */
                case 2:
                {
                    System.out.println();

                    System.out.println("Enter a club name: ");
                    String clubname = scanner.nextLine();

                    // create a club class to match the given input club
                    Club club = new Club();

                    //checks if the club exists
                    int clubFound  = find.findClub(clubs,clubname);

                    // exit the switch if not found
                    if(clubFound == -1){
                        System.out.println("No such club with this name");
                        break;
                    }

                    club = clubs.get(clubFound);

                    // find the player with maximum age
                    sortedPlayers = getInformation.getMaxAge(club.clubPlayers);
                    for (Player player:sortedPlayers)
                    {
                        player.printPlayer();
                        System.out.println();
                    }
                    break;

                }

                /**
                 * If the user chooses option (3), you should ask the user to input a club name
                 * and then search the players with the maximum height.
                 * Display all information of these players if the club is found, or display “No such club with this name” if not found.
                 */
                case 3:{
                    System.out.println();

                    System.out.println("Enter a club name: ");
                    String clubname = scanner.nextLine();

                    // create a club class to match the given input club
                    Club club = new Club();

                    //checks if the club exists
                    int clubFound = find.findClub(clubs,clubname);

                    // exit the switch if not found
                    if(clubFound == -1){
                        System.out.println("No such club with this name");
                        break;
                    }

                    club = clubs.get(clubFound);

                    // find the player with maximum Height
                    sortedPlayers = getInformation.getMaxHeight(club.clubPlayers);
                    for (Player player:sortedPlayers)
                    {
                        player.printPlayer();
                        System.out.println();
                    }

                    break;

                }

                /**
                 * If the user chooses option (4), you should ask the user to input a club name
                 * and then find out the total yearly salary of all the players of this club.
                 * Display the total salary for the club, or display “No such club with this name” if not found.
                 */
                case 4:{
                    System.out.println();

                    System.out.println("Enter a club name: ");
                    String clubname = scanner.nextLine();

                    // create a club class to match the given input club
                    Club club = new Club();

                    //checks if the club exists
                    int clubFound = find.findClub(clubs,clubname);

                    // exit the switch if not found
                    if(clubFound == -1){
                        System.out.println("No such club with this name");
                        break;
                    }

                    club = clubs.get(clubFound);

                    // print the total salary
                    System.out.print("Total yearly salary of the players of " + club.getName() + " is: "+club.totalSalary() +" million");
                    break;

                }

                /**
                 * If the user chooses option (5), the program should go back to the main menu.
                 */
                case 5:{
                    mainMenu = true;
                    System.out.println();
                    return;
                }
            }
        }
    }
}
