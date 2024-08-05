package FX;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PriceController {

    @FXML
    private TextField priceField;

    @FXML
    private Button sell;

    private String marketPrice;

    @FXML
    void setPrice(ActionEvent event) {

    }

    @FXML
    void setPriceAndSell(ActionEvent event) {
        marketPrice = priceField.getText();
        Stage stage = (Stage) sell.getScene().getWindow();

        stage.close();

    }

    public String getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
    }
}
