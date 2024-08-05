package Part1;

import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) throws Exception {

        List<Player> playerList = new ArrayList();
        List<Club> clubs = new ArrayList();
        List<Country>countryList = new ArrayList();

        /**Read the players list from the .txt file**/
        FileOperation fileOperation = new FileOperation();
        playerList = fileOperation.readFromTxt("players.txt");



        
       // Create a AddPlayer object to add the players to the club and the country class
        
        AddPlayer addPlayer = new AddPlayer();
        
        for (Player temp : playerList) {
            addPlayer.clubAddition(clubs,temp);
            addPlayer.countryAddition(countryList,temp);
        }


        /**
         * Main Menu:
         * (1) Search Players
         * (2) Search Clubs
         * (3) Add Player
         * (4) Exit System
         **/

        boolean exitProgram = false;            // check wheather to exit the program or not

        /**
         * the loop continues untill the exitProgram becomes true
         */
        while(!exitProgram){

            System.out.println("Main Menue:");
            System.out.println("(1) Search Players");
            System.out.println("(2) Search Clubs");
            System.out.println("(3) Add Player");
            System.out.println("(4) Exit System");

           // create new object of inputIndex type to get index
            InputIndex inputIndex = new InputIndex();
            int index  = inputIndex.getIndex(4);


            switch (index){

                /**
                 * Option (1) of the main menu allows the user to search for players in the database.
                 * The user should be asked what criteria(s) he/she wants to use to search for players.
                 */
                case 1:
                {
                    SearchPlayers search = new SearchPlayers();   // create an object of SearchPlayer class
                    search.playerSearchOption(playerList,countryList,clubs);
                    break;
                }

                /**
                 *Option (2) of the main menu allows the user to search for players of specific club in the database.
                 *The user should be asked what criteria(s) he/she wants to use to search.
                 */
                case 2:{
                    SearchClub search = new SearchClub(); // create an object of SearchClub class
                    search.clubSearchOption(playerList,clubs);
                    break;
                }

                /**
                 * Option (3) of the main menu allows the user to add a new player to the database.
                 * You have to take input for all the information of a player.
                 * Please note that each club can have a maximum of 7 players
                 */
                case 3:{
                    // create an object of the class CreatePlayer
                    CreatePlayer createPlayer = new CreatePlayer();

                    //assign the returned Player to a temporary Player
                    Player temp = createPlayer.newPlayer();

                    //call the addPlayer method to check if the Player is valid or not and add him to the playerList,countryList and clubs
                    addPlayer.addNewPlayer(playerList,countryList,clubs,temp);

                    break;
                }

                /**
                 * Option (4) of the main menu exits the program.
                 * All the players currently in memory are automatically saved back to "players.txt" if any new player is added.
                 */
                case 4:{
                    exitProgram = true;    // this ends the loop;

                    /** write the players list in a file**/
                    fileOperation.writeToText(playerList,"players.txt");
                }
            }
        }

        /**
 * You should assume the following when implementing your program:
 * • You are not allowed to hardcode anything
 * • You have to reuse your code later, so design and code accordingly
 * • All player names are unique - if a player with the inputted name is already in the database, you cannot add this player, and you should generate an error
 * • There is no limit to how many players can be stored
 * • When performing searches, all search strings are not case-sensitive
 * • The data file is always in the correct format - i.e., no need to validate the data when reading it in
 * • All on-screen input/output should be formatted in a user-friendly manner. Sensible error messages should be displayed whenever appropriate (e.g., when searching for a player who is not in the database, a “Not Found” error message should be displayed)
 * • You are not allowed to use JavaFX or Java Swing for this part; everything should be command line based
 */

    }
}
