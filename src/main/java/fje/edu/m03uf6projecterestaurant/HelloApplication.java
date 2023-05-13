package fje.edu.m03uf6projecterestaurant;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import static fje.edu.m03uf6projecterestaurant.ConnexioMesProves.executarTest;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        executarTest();
        Parent root = FXMLLoader.load(getClass().getResource("primer_plat.fxml"));
        stage.setTitle("Restaurant");
        //stage.setScene(new Scene(root, 800, 500));
        stage.setScene(new Scene(root, 1400, 800));
        stage.show();
    }
}