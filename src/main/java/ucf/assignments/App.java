/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Sathwika Deegutla
 */

package ucf.assignments;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    public static void main(String[] args) {
        //launches the application
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //sets the primary stage to ToDo fxml
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ToDo.fxml"));

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("TO-DO");
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
