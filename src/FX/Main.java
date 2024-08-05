package FX;


import Part1.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import Util.NetworkUtil;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private Stage stage;
    private NetworkUtil networkUtil;
    private List<Player> playerList = new ArrayList<>();
    private List<Club> clubs = new ArrayList<>();
    private List<Country> countryList = new ArrayList<>();
    private List<Player> soldPlayer = new ArrayList<>();

    FileOperation fileOperation = new FileOperation();

    public Stage getStage() {
        return stage;
    }

    public NetworkUtil getNetworkUtil() {
        return networkUtil;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;

        playerList = fileOperation.readFromTxt("players.txt");
        AddPlayer addPlayer = new AddPlayer();

        for(Player temp :playerList){
            addPlayer.clubAddition(clubs, temp);
            addPlayer.countryAddition(countryList, temp);
        }

        String serverAddress = "127.0.0.1";
        int serverPort = 33333;
        networkUtil = new NetworkUtil(serverAddress, serverPort);
        ReadThread readThread = new ReadThread(this, addPlayer);


        // XML Loading using FXMLLoader

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Login.fxml"));
        Parent root = loader.load();

        // Loading the controller
        LoginController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Login");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void showHomePage(String clubname, Runnable readThread) throws Exception {

        try {
            playerList = fileOperation.readFromTxt("players.txt");
            AddPlayer addPlayer = new AddPlayer();

            for(Player temp :playerList){
                addPlayer.clubAddition(clubs, temp);
                addPlayer.countryAddition(countryList, temp);
            }

            //Stage primaryStage = (Stage) button.getScene().getWindow();


            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("FirstScene.fxml"));
            Parent root = loader.load();

            FirstSceneController controller = loader.getController();
            controller.setValues(playerList,clubs,countryList,clubname,networkUtil,readThread);
            controller.setMain(this);

            stage.setTitle("Player Database System");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Credentials");
        alert.setHeaderText("Incorrect Credentials");
        alert.setContentText("The username and password you provided is not correct.");
        alert.showAndWait();
    }

    public static void main(String[] args) {
        // This will launch the JavaFX application
        launch(args);
    }

    public void setSoldPlayer(List<Player> soldPlayer) {
        this.soldPlayer = soldPlayer;
        System.out.println("In main: size of sold Player: " + soldPlayer.size());
    }

    public List<Player> getSoldPlayer() {
        return soldPlayer;
    }
}
