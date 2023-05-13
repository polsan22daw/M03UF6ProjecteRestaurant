package fje.edu.m03uf6projecterestaurant;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("primer_plat.fxml"));
        stage.setTitle("Restaurant");
        //stage.setScene(new Scene(root, 800, 500));
        stage.setScene(new Scene(root, 1400, 800));
        stage.show();
    }
}