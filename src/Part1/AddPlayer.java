package Part1;

import java.util.List;

public class AddPlayer {

    boolean nameFound = false;
    boolean addedToClub = false;

    // create a find object for searching purpose
    Find find = new Find();


    public void countryAddition(List<Country> countryList, Player temp) {
        int countryFound ;
        int countryIndex = -1;

        countryFound = find.findCountry(countryList,temp.getCountry()); /** if country found returns index else returns -1 **/

        if (countryFound != -1) {       /** if found, add the player to the country  else, create a new country and add the player **/
            countryIndex = countryFound;
            countryList.get(countryIndex).countryPlayers.add(temp);
        } else {
            Country c = new Country(temp.getCountry());
            countryList.add(c);
            countryList.get(countryList.size() - 1).countryPlayers.add(temp);
        }
    }

    public void clubAddition(List<Club>clubs , Player temp){

        int clubFound;
        int clubIndex = -1;

        clubFound = find.findClub(clubs, temp.getClub());       /**returns club index if found else return -1**/

        if (clubFound != -1 ) {     // club is found

            clubIndex = clubFound;

            /** Loop to check wheather the created player's jersey no has matched with the other players in the club **/
            for(Player player: clubs.get(clubIndex).clubPlayers){
                if(player.getJerseyNo() == temp.getJerseyNo()){
                    //System.out.println("Jersey no should be unique !!");
                    addedToClub = false;
                    return;
                }
            }

            clubs.get(clubIndex).clubPlayers.add(temp);
            addedToClub = true;


        } else {
            Club c = new Club(temp.getClub());
            clubs.add(c);
            clubs.get(clubs.size() - 1).clubPlayers.add(temp);
            addedToClub = true;
        }
    }

    public void addNewPlayer(List<Player> playerList, List<Country> countryList, List<Club> clubs, Player temp) {


        /**
         * check if the name is unique or not
         * If not return to main method;
         */
        for(Player player : playerList ){
            if(temp.getName().equalsIgnoreCase(player.getName())){  // if name matches
               nameFound =true;
               System.out.println("Player's name should be unique!!!");
               return;
            }
        }
        
        /**
         * Add the player to the specific club ,
         * else create a new club ,
         * also check the maximum no of club players (7 the most)
         * **/
        clubAddition(clubs,temp);

        /** Add the player to the countryList or create a new country in the list **/
        countryAddition(countryList,temp);

        /** Add the player to the player list after all the checking finished **/
        if(!nameFound && addedToClub )
            playerList.add(temp);

        return;
    }
}
