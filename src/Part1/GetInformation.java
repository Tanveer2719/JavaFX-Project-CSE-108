package Part1;

import java.util.ArrayList;
import java.util.List;

public class GetInformation {

    // returns a list of players with highest salary
    public List<Player> getHighestSalary(List<Player> clubPlayers) {
        double highestSalary = 0;
        List<Player> temp = new ArrayList();

        for (Player player : clubPlayers) {
            if (player.getWeeklySalary() > highestSalary) {
                highestSalary = player.getWeeklySalary();
            }
        }
        for (Player player : clubPlayers) {
            if (player.getWeeklySalary() == highestSalary) {
                temp.add(player);
            }
        }

        return temp;
    }

    // returns a list of players with highest age
    public List<Player> getMaxAge(List<Player> clubPlayers) {
        int age = 0;
        for(Player player:clubPlayers){
            if(player.getAge() > age){
                age = player.getAge();
            }
        }

        List<Player> tempList = new ArrayList();

        for(Player player :clubPlayers ){
            if(player.getAge() == age){
                tempList.add(player);
            }
        }

        return tempList;
    }

    // returns a list of players with highest height
    public List<Player> getMaxHeight(List<Player> clubPlayers) {

        double height = 0;
        for(Player player:clubPlayers){
            if(player.getHeight() > height){
                height = player.getHeight();
            }
        }

        List<Player> tempList = new ArrayList();

        for(Player player :clubPlayers ){
            if(player.getHeight() == height){
                tempList.add(player);
            }
        }

        return tempList;
    }
}
