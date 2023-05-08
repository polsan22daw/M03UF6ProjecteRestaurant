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

public class PrimersPlatsController {
    @FXML
    private Button nextButton;
    @FXML
    private Button crearButton;
    @FXML
    private void canviarEscenaAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root;
        if(event.getSource()==nextButton){
            root = FXMLLoader.load(getClass().getResource("/fje/edu/m03uf6projecterestaurant/segon_plat.fxml"));
        }
        else if(event.getSource()==crearButton){
            root = FXMLLoader.load(getClass().getResource("/fje/edu/m03uf6projecterestaurant/crear_plat.fxml"));
        }
        else {
            return;
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
/*
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    public void actualitzarImatges() {
        PlatDAO platDAO = new PlatDAO();
        try {
            Image image1 = platDAO.obtenirImatgeDeUrl("https://i.pinimg.com/originals/16/4c/fa/164cfa87a4129f997c25281a4e2def07.png");
            button1.setGraphic(new ImageView(image1));
            Image image2 = platDAO.obtenirImatgeDeUrl("https://i.pinimg.com/originals/16/4c/fa/164cfa87a4129f997c25281a4e2def07.png");
            button2.setGraphic(new ImageView(image2));
            Image image3 = platDAO.obtenirImatgeDeUrl("https://i.pinimg.com/originals/16/4c/fa/164cfa87a4129f997c25281a4e2def07.png");
            button3.setGraphic(new ImageView(image3));
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }*/
}