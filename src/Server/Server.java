package Server;

import Part1.Player;
import Util.Clubinfo;
import Util.NetworkUtil;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Server {

    private ServerSocket serverSocket;
    public HashMap<String, Clubinfo> userMap;
    public List<String> list;
    public List<Player> playerList;

    Server() {
        userMap = new HashMap<>();
        list = new ArrayList<>();
        userMap.put("Arsenal", new Clubinfo());
        userMap.put("Manchester United" , new Clubinfo());
        userMap.put("Manchester City" , new Clubinfo());
        userMap.put("Chelsea" , new Clubinfo());
        userMap.put("Liverpool" , new Clubinfo());

        /*list.addAll("Manchester United","Manchester City","Chelsea","Liverpool","Arsenal");
        list.add("Manchester United");
        list.add("Manchester City");
        list.add("");
        list.add("");
        list.add("Arsenal");*/
        try {
            serverSocket = new ServerSocket(33333);
            System.out.println("Server Started..");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client accepted");
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }

    public void serve(Socket clientSocket) throws IOException {
        NetworkUtil networkUtil = new NetworkUtil(clientSocket);
        new ReadThreadServer(userMap, networkUtil,playerList);
    }

    public static void main(String[] args) {
        new Server();
    }
}
