package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelpScreenController {

    @FXML private Button closeBtn;
    //@FXML private AnchorPane helpAnchor;

    public void closeBtnClicked(ActionEvent actionEvent){
        /*Parent todoListParent = FXMLLoader.load(getClass().getResource("ToDo.fxml"));
        Scene todoListScene = new Scene(todoListParent);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(todoListScene);
        window.show();*/

        Stage currentStage = (Stage) closeBtn.getScene().getWindow();
        currentStage.close();
    }

    /*@Override
    public void initialize(URL location, ResourceBundle resources) {
        //ScrollPane scrollPane = new ScrollPane();
        //scrollPane.setContent(helpAnchor);
    }*/
}
