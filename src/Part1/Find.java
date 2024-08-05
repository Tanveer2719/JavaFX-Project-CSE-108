package Part1;
import java.util.List;

public class Find {

    // check any club of the given name is available... if found return index in the clubs list else return -1

    int findClub(List<Club>clubs,String club1){
        for(int i = 0;i<clubs.size();i++){
            if(clubs.get(i).getName().equalsIgnoreCase(club1)){
                return i;
            }
        }
        return -1;
    }

    // Check if any player of the given country is available....if found return index in the country list else return -1

    int findCountry(List<Country>countryList,String country){
        for(int i = 0;i<countryList.size();i++){
            if(countryList.get(i).getName().equalsIgnoreCase(country)){
                return i;
            }
        }
        return -1;
    }

    // Check any player of the name is found in the playerlist...if found return index else return -1

    public int findPlayer(List<Player> playerList, String pName) {
        for(int i = 0;i<playerList.size();i++){
            if(playerList.get(i).getName().equalsIgnoreCase(pName)){
                return i;
            }
        }
        return -1;
    }
}
