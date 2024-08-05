package FX;

import Part1.*;
import Util.Message;
import Util.NetworkUtil;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class FirstSceneController implements Initializable {

    @FXML
    private Button searchClubs;
    @FXML
    private Button exitSystem;
    @FXML
    private Button searchPlayers;
    @FXML
    private Button market;
    @FXML
    private TableView<Player> table;
    @FXML
    private TableColumn<Player,String> nameColumn;
    @FXML
    private TableColumn<Player,String>countryColumn;
    @FXML
    private Label CLUBNAME;
    @FXML
    private TextField textField = new TextField();
    @FXML
    private MenuItem salaryRange = new MenuItem();
    @FXML
    private MenuItem playerCount = new MenuItem();
    @FXML
    private MenuItem country = new MenuItem();
    @FXML
    private MenuItem playername = new MenuItem();
    @FXML
    private Label notFound;



    private Stage stage = new Stage();

    private List<Player> playerList = new ArrayList<>();
    private List<Club> clubs = new ArrayList<>();
    private List<Country> countryList = new ArrayList<>();
    private List<Player>soldPlayer;
    private static String clubname;
    private static Club selectedClub;
    private NetworkUtil networkUtil;
    private Main main;
    private Runnable readThread;

    private boolean playerNameClicked = false;
    private boolean rangeClicked = false;
    private boolean playerCountClicked = false;
    private boolean countryClicked = false;



    private ObservableList<Player> observableList = FXCollections.observableArrayList();


    public void setValues(List<Player> playerList, List<Club> clubs, List<Country> countryList, String clubname, NetworkUtil networkUtil, Runnable readThread){
        this.playerList = playerList;
        this.clubs = clubs;
        this.countryList=countryList;
        //System.out.println("In constructor : "+ soldPlayer.size());
        this.clubname = clubname;
        this.networkUtil = networkUtil;
        this.readThread = readThread;

        textField.setEditable(false);

        // find the selected club
        for(Club club: clubs){
            if(clubname.equals(club.getName()))
            {
                selectedClub = club;
                System.out.println(selectedClub.getName());
                CLUBNAME.setText(clubname.toUpperCase());
            }
        }
    }




    public void playerSearch(ActionEvent event) throws Exception{

        observableList.clear();
        for(Player player: selectedClub.clubPlayers){
                observableList.add(player);
        }
    }

    /**Exit Button clicked*/

    public void setExitSystem(ActionEvent event) throws Exception{

        /*for(Player player: playerList)
            System.out.println(player.getName());*/

        /** write the players list in a file**/
       // fileOperation.writeToText(playerList,"players.txt");

        /** closes the GUI */
        Platform.exit();
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        CLUBNAME.setText(clubname);

        // initialize the columns
        nameColumn.setCellValueFactory(new PropertyValueFactory<Player,String>("name"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<Player,String>("country"));

        table.setItems(observableList);

        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        //table.getSelectionModel().setCellSelectionEnabled(true);


    }


    @FXML
    public void showDetails(ActionEvent event) throws IOException {
        ObservableList<Player> list;
        list = table.getSelectionModel().getSelectedItems();

        String playerName = list.get(0).getName();

        stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("playerDetails.fxml"));
        Parent root = loader.load();

        PlayerDetailsController playerDetailsController = loader.getController();
        playerDetailsController.setValues(playerList , playerName);

        stage.setTitle("Player Details");
        stage.setScene(new Scene(root));
        stage.show();


    }


    public void viewMid(ActionEvent event) {
        observableList.clear();
        for(Player player : selectedClub.clubPlayers) {
            if(player.getPosition().equals("Midfielder"))
            observableList.add(player);
        }
    }

    public void viewGoal(ActionEvent event) {
        observableList.clear();
        for(Player player : selectedClub.clubPlayers) {
            if(player.getPosition().equals("Goalkeeper"))
                observableList.add(player);
        }
    }

    public void viewDefender(ActionEvent event) {
        observableList.clear();
        for(Player player : selectedClub.clubPlayers) {
            if(player.getPosition().equals("Defender"))
                observableList.add(player);
        }
    }

    public void viewStriker(ActionEvent event) {
        observableList.clear();
        for(Player player : selectedClub.clubPlayers) {
            if(player.getPosition().equals("Forward"))
                observableList.add(player);
        }
    }


    public void getFromTextField(ActionEvent event)  {
        observableList.clear();
        String s = textField.getText();
        notFound.setText("");


        if(playerNameClicked){
            for(Player player : selectedClub.clubPlayers){
                if(player.getName().equalsIgnoreCase(s)){
                    observableList.add(player);
                }
            }
            if(observableList.size() == 0)
                notFound.setText("not found");
        }
        else if(rangeClicked){
            String[] range = s.split("-");
            double low = Double.parseDouble(range[0]);
            double high = Double.parseDouble(range[1]);

            for(Player player : selectedClub.clubPlayers){
                if(player.getWeeklySalary() >= low && player.getWeeklySalary() <= high ){
                    observableList.add(player);
                }
            }
            if(observableList.size() == 0)
                notFound.setText("not found");
        }
        else if(countryClicked){
            for(Player player : selectedClub.clubPlayers){
                if(player.getCountry().equalsIgnoreCase(s)){
                    observableList.add(player);
                }
            }
            if(observableList.size() == 0)
                notFound.setText("not found");
        }


        textField.setEditable(false);
        textField.setText(null);
        textField.setPromptText("Not in Use");

    }


    public void getPlayerName(ActionEvent event) {
        textField.setEditable(true);
        textField.setPromptText("Player Name");

        playerNameClicked = true;
        rangeClicked = false;
        playerCountClicked = false;
        countryClicked = false;


    }

    public void getSalaryRange(ActionEvent event) {
        textField.setEditable(true);
        textField.setPromptText("low - high");

        playerNameClicked = false;
        rangeClicked = true;
        playerCountClicked = false;
        countryClicked = false;


    }

    public void countPlayers(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("playerCount.fxml"));
        Parent root = loader.load();

        PlayerCountController playerCountController = loader.getController();
        playerCountController.setValue(countryList,selectedClub);

        stage = new Stage();
        stage.setTitle("Player Count");
        stage.setScene(new Scene(root));
        stage.show();


    }

    public void getCountry(ActionEvent event) throws IOException{
        textField.setEditable(true);
        textField.setPromptText("Country");

        playerNameClicked = false;
        rangeClicked = false;
        playerCountClicked = false;
        countryClicked = true;


    }

    public void onSellClicked(ActionEvent event) throws IOException {

        PriceController priceController;
        Message message = new Message();
        Player player = table.getSelectionModel().getSelectedItem();

        int idx = table.getSelectionModel().getSelectedIndex();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("setPrice.fxml"));
        Parent root = loader.load();
        priceController = loader.getController();

        Stage stage = new Stage();
        stage.setTitle("Confirmation");
        stage.setScene(new Scene(root));
        stage.showAndWait();

        observableList.remove(idx);

        selectedClub.clubPlayers.remove(player);

        for (Country country1 : countryList) {
            if (country1.getName().equals(player.getCountry()))
                country1.countryPlayers.remove(player);
        }


        //System.out.println("In first SceneController: " + priceController.getMarketPrice());

        message.setFrom(clubname);
        message.setTo("ALL");
        message.setStatus("Sell");
        message.setPlayer(player);
        message.setIndex(idx);

        try {
            networkUtil.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void onBuyClicked(ActionEvent event) {

        Message message = new Message();
        Player player = table.getSelectionModel().getSelectedItem();

        int idx = table.getSelectionModel().getSelectedIndex();
        observableList.remove(idx);

        selectedClub.clubPlayers.add(player);
        for(Country country: countryList){
            if(country.getName().equals(player.getCountry()))
                country.countryPlayers.add(player);
        }

        message.setFrom(clubname);
        message.setTo("ALL");
        message.setStatus("Buy");
        message.setPlayer(player);

        try {
            networkUtil.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onMarketClicked(ActionEvent event) {
        observableList.clear();

        soldPlayer = main.getSoldPlayer();
        //System.out.println("In Market :" +clubname+ soldPlayer.size());

        for(Player player: soldPlayer){
            observableList.add(player);
        }
    }

    void setMain(Main main) {
        this.main = main;
    }

    void setNetworkUtil(NetworkUtil networkUtil){
        this.networkUtil = networkUtil;
    }

    NetworkUtil getNetworkUtil(){
        return  networkUtil;
    }

    public void getMaxHeight(ActionEvent event) {
        observableList.clear();
        GetInformation getInformation = new GetInformation();
        List<Player>mHeight = getInformation.getMaxHeight(selectedClub.clubPlayers);
        for(Player player:mHeight){
            observableList.add(player);
        }
    }

    public void getMaxAge(ActionEvent event) {
        observableList.clear();
        GetInformation getInformation = new GetInformation();
        List<Player>mAge = getInformation.getMaxAge(selectedClub.clubPlayers);
        for(Player player:mAge){
            observableList.add(player);
        }
    }


    public void getMaxSalary(ActionEvent event) {
        observableList.clear();
        GetInformation getInformation = new GetInformation();
        List<Player>mSal = getInformation.getMaxAge(selectedClub.clubPlayers);
        for(Player player:mSal){
            observableList.add(player);
        }
    }

    public void getTotalSalary(ActionEvent event) throws IOException{
        String totalSalary = selectedClub.totalSalary();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("getInfo.fxml"));
        Parent root = loader.load();

        GetInfoController getInfoController = loader.getController();
        getInfoController.setValues(clubname, totalSalary);

        stage.setScene(new Scene(root));
        stage.setTitle("Total Salary");
        stage.show();
    }
}
