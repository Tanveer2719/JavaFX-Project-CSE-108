package FX;


import Part1.Find;
import Part1.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.util.ArrayList;
import java.util.List;

public class PlayerDetailsController {

    int found ;
    Player player ;
    String pName;
    List<Player> playerList = new ArrayList<>();
    Find find = new Find();


    @FXML
    private ImageView image;
    @FXML
    private Label name;
    @FXML
    private Label country;
    @FXML
    private Label club;
    @FXML
    private Label age;
    @FXML
    private Label position;
    @FXML
    private Label height;
    @FXML
    private Label jerseyNo;
    @FXML
    private Label weeklySalary;



    public void setValues(List<Player> playerList , String name) {
        this.playerList = playerList;
        pName = name;
        found = find.findPlayer(playerList,pName);
        player = playerList.get(found);

        this.name.setText("Name : " + player.getName());
        age.setText("Age : " + player.getAge());
        country.setText("Country : "+ player.getCountry());
        position.setText("Position : " + player.getPosition() );
        jerseyNo.setText("Jersey no : " + player.getJerseyNo());
        height.setText("Height : "+ player.getHeight());
        weeklySalary.setText("WeekSalary : " + player.getWeeklySalary());
        club.setText("Club : " + player.getClub());
        Image img = new Image(Main.class.getResourceAsStream("player.jpg"));
        image.setImage(img);


    }
}

