package FX;

import Part1.AddPlayer;
import Part1.Player;
import Util.Message;
import javafx.application.Platform;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadThread implements Runnable {
    private final Thread thr;
    private final Main main;
    private static List<Player> soldPlayer = new ArrayList<>();
    AddPlayer addPlayer;
    FirstSceneController firstSceneController;

    public ReadThread(Main main, AddPlayer addPlayer) {
        this.main = main;
        this.thr = new Thread(this);
        this.addPlayer = addPlayer;
        thr.start();
    }
    public void setController(FirstSceneController firstSceneController){
        this.firstSceneController = firstSceneController;
    }

    public void run() {
        try {
            while (true) {
                Object o = main.getNetworkUtil().read();
                if (o != null) {
                    if (o instanceof String) {
                        String string = (String) o;
                        String[] s = string.split(",");
                        //System.out.println(club.getName());
                        //System.out.println(club.isOnline());
                        // the following is necessary to update JavaFX UI components from user created threads
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                if (s[0].equals("success")) {
                                    try {
                                        main.showHomePage(s[1],this);
                                       // System.out.println(club.getNetworkUtil());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    main.showAlert();
                                }

                            }
                        });
                    }

                    if(o instanceof Message){
                        Message obj = (Message) o;
                        if(obj.getStatus().equals("Sell")) {
                            soldPlayer.add(obj.getPlayer());

                            //for (Player player : soldPlayer)
                                //System.out.println("In Read Thread Sold Player " + player.getName());

                            main.setSoldPlayer(soldPlayer);
                            //System.out.println("In read thread sold player size = " + soldPlayer.size());

                        }
                        if(obj.getStatus().equals("Buy")){

                            soldPlayer = main.getSoldPlayer();
                            //System.out.println("size of sold player before buy: "+ soldPlayer.size());
                            //for (Player p : soldPlayer)
                            //System.out.println(p.getName());

                            Player player = obj.getPlayer();
                            System.out.println(player.getName());

                            for(Player player1: soldPlayer){
                                if(player.getName().equals(player1.getName()))
                                    soldPlayer.remove(player1);
                            }

                            System.out.println("Size of sold player after buy: "+ soldPlayer.size());
                            //System.out.println("size of sold player after buy: "+ soldPlayer.size());
                            //for (Player p : soldPlayer)
                                //System.out.println(p.getName());



                            main.setSoldPlayer(soldPlayer);
                        }

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                main.getNetworkUtil().closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



