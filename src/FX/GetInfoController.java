package FX;


import javafx.fxml.FXML;

import javafx.scene.control.Label;

public class GetInfoController {

    @FXML
    private Label clubLabel;

    @FXML
    private Label tSalary;





    public void setValues(String clubName, String totalSalary){
        clubLabel.setText(clubName.toUpperCase());
        double t = Double.parseDouble(totalSalary);
        int total = (int) t;
        tSalary.setText( "Total salary per year is \n       "+ total + " Million");

    }

}
