package Part1;

import Util.NetworkUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Club implements Serializable {
    public List<Player> clubPlayers = new ArrayList();
    HashMap<String, Integer> map = new HashMap<>();
    private NetworkUtil networkUtil;
    private boolean Online;


    private String name;
    private int size;

    public Club(){}
    Club(String name){
        this.name = name;
    }

    void printClubPlayers(){
        for(Player player : clubPlayers )
        {
            player.printPlayer();
            System.out.println();
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    // return the list of players of max height


    // return string of total salary
    public String totalSalary(){
        double value = 0;
        for(Player player:clubPlayers){
            value += player.getWeeklySalary();
        }

        //convert total salary of 1 week to million...1 million = 1000000
        value /= 1000000;

        // return the total salary in millions in a year...1 year = 52 weeks
        return String.valueOf(value*52);

    }


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public NetworkUtil getNetworkUtil() {
        return networkUtil;
    }

    public void setNetworkUtil(NetworkUtil networkUtil) {
        this.networkUtil = networkUtil;
    }

    public boolean isOnline() {
        return Online;
    }

    public void setOnline(boolean online) {
        Online = online;
    }
}
