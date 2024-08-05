package Server;

import Part1.Club;
import Part1.Player;
import Util.Clubinfo;
import Util.Message;
import Util.NetworkUtil;

import java.io.IOException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ReadThreadServer implements Runnable {
    private final Thread thr;
    private final NetworkUtil networkUtil;
    public HashMap<String, Clubinfo> userMap;
    
    public ReadThreadServer(HashMap<String, Clubinfo> map, NetworkUtil networkUtil, List<Player> playerList) {
        this.userMap = map;
        this.networkUtil = networkUtil;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = networkUtil.read();
                if (o != null) {
                    if (o instanceof Club) {
                        Club club = (Club) o;
                        Clubinfo clubinfo = userMap.get(club.getName());
                        if(clubinfo != null){
                            clubinfo.setNetworkUtil(networkUtil);
                            clubinfo.setOnline(true);
                            networkUtil.write("success,"+ club.getName());
                        }else{
                            networkUtil.write("failure");
                        }
                    }
                    if(o instanceof Message) {
                        Message obj = (Message) o;
                        if (obj.getStatus().equals("Sell")) {
                            System.out.println("Sold Player in Server :" + obj.getPlayer().getName());
                            //playerList.add(obj.getPlayer());
                            Iterator<String> iterator = userMap.keySet().iterator();
                            while (iterator.hasNext()) {
                                String name = iterator.next();
                                Clubinfo clientInfo = userMap.get(name);
                                if (!name.equals(obj.getFrom()) && clientInfo.isOnline()) {
                                    clientInfo.getNetworkUtil().write(obj);
                                }
                            }
                        }
                        if(obj.getStatus().equals("Buy")){
                            System.out.println("Bought Player in Server :" + obj.getPlayer().getName());
                            Iterator<String> iterator = userMap.keySet().iterator();
                            while (iterator.hasNext()) {
                                String name = iterator.next();
                                Clubinfo clientInfo = userMap.get(name);
                                if (clientInfo.isOnline()) {
                                    clientInfo.getNetworkUtil().write(obj);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
           e.printStackTrace();
        } finally {
            try {
                networkUtil.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



