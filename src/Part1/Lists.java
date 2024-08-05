package Part1;

import java.util.ArrayList;
import java.util.List;

public class Lists {
    private List<Player> playerList = new ArrayList();
    private List<Club> clubs = new ArrayList();
    private List<Country> countryList = new ArrayList();

    public void createList() throws Exception {
        /**
         * Read the players list from the .txt file
         **/
        FileOperation fileOperation = new FileOperation();
        playerList =fileOperation.readFromTxt("players.txt");

        /**
         * Create a AddPlayer object to add the players to the club and the country class
         **/
        AddPlayer addPlayer = new AddPlayer();

        for(Player temp :playerList){
            addPlayer.clubAddition(clubs, temp);
            addPlayer.countryAddition(countryList, temp);
        }
    }

    public List<Player> getPlayerList(){
        return playerList;
    }
    public List<Club> getClubs(){
        return clubs;
    }
    public List<Country> getCountryList(){
        return countryList;
    }



}

