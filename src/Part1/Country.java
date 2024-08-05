package Part1;

import java.util.ArrayList;
import java.util.List;

public class Country {

    public List<Player> countryPlayers = new ArrayList();
    private String name;
    private int size;

   /**CONSTRUCTORS**/
    Country(){}
    Country(String name){
        this.name = name;
    }

    void printCountryPlayers(){
        for(Player player : countryPlayers )
        {
            player.printPlayer();
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
