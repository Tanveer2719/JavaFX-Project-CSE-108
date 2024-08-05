package FX;

import Part1.Club;
import Part1.Count;
import Part1.Country;
import Part1.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class PlayerCountController implements Initializable {

    private List<Country> countryList;
    private Club club;
    private ObservableList<Count> countObservableList = FXCollections.observableArrayList();

    private List<Count> count = new ArrayList<>();

    public void setValue(List<Country> countryList, Club selectedClub){

        this.countryList = countryList;
        club = selectedClub;

        for(Player player : club.clubPlayers){
            boolean found = false;
            for(Count count1: countObservableList){
                if(count1.getCountryName().equals(player.getCountry())){
                    found = true;
                    count1.setCount(count1.getCount() + 1);
                }
            }
            if(!found){
                countObservableList.add(new Count(player.getCountry(), 1));
            }

        }

        System.out.println(countObservableList.size());

    }

    @FXML
    TableView<Count> table = new TableView<>();

    @FXML
    TableColumn<Count, String> countryName = new TableColumn<>();

    @FXML
    TableColumn<Count, Integer> playerNo = new TableColumn<>();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryName.setCellValueFactory(new PropertyValueFactory<Count, String>("countryName"));
        playerNo.setCellValueFactory(new PropertyValueFactory<Count,Integer>("count"));

        table.setItems(countObservableList);

    }
}
