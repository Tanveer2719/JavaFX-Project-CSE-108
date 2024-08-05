package FX;

import Part1.Club;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;


public class LoginController {

    private Main main;

    @FXML
    private TextField userText;


    @FXML
    private Button resetButton;

    @FXML
    private Button loginButton;

    @FXML
    void loginAction(ActionEvent event) {
        String userName = userText.getText();
        Club club = new Club();
        club.setName(userName);
        //loginDTO.setPassword(password);
        try {
            main.getNetworkUtil().write(club);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void resetAction(ActionEvent event) {
        userText.setText(null);
        //passwordText.setText(null);
    }

    void setMain(Main main) {
        this.main = main;
    }

}
