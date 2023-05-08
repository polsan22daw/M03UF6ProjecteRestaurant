package fje.edu.m03uf6projecterestaurant.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SegonsPlatsController {

    @FXML
    private Button nextButton;
    @FXML
    private void canviarEscenaAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root;
        if(event.getSource()==nextButton){
            root = FXMLLoader.load(getClass().getResource("/fje/edu/m03uf6projecterestaurant/postres.fxml"));
        }
        else {
            root = FXMLLoader.load(getClass().getResource("/fje/edu/m03uf6projecterestaurant/primer_plat.fxml"));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
