/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Sathwika Deegutla
 */
package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HelpScreenController {

    //private instance Button for closing the window
    @FXML
    private Button closeBtn;

    public void closeBtnClicked(ActionEvent actionEvent) {
        //action event to close the current window when button is clicked
        //get current stage
        Stage currentStage = (Stage) closeBtn.getScene().getWindow();
        currentStage.close(); //close it
    }

}
