package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelpScreenController {

    public void goBackBtnClicked(ActionEvent actionEvent) throws IOException{
        Parent todoListParent = FXMLLoader.load(getClass().getResource("ToDo.fxml"));
        Scene todoListScene = new Scene(todoListParent);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(todoListScene);
        window.show();
    }
}
