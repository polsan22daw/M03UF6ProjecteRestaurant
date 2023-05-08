package fje.edu.m03uf6projecterestaurant.controllers;

import fje.edu.m03uf6projecterestaurant.classes.Plat;
import fje.edu.m03uf6projecterestaurant.classes.PlatDAO;
import fje.edu.m03uf6projecterestaurant.classes.PlatDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import static fje.edu.m03uf6projecterestaurant.ConnexioMesProves.obtenirConnexio;

public class CrearPlatController {
    @FXML
    private void canviarEscenaAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fje/edu/m03uf6projecterestaurant/primer_plat.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private javafx.scene.control.Button crearButton;
    @FXML
    private javafx.scene.control.TextField camp_nom;
    @FXML
    private javafx.scene.control.TextArea camp_descripcio;
    @FXML
    private javafx.scene.control.TextField camp_preu;
    @FXML
    private javafx.scene.control.TextArea camp_ingredients;
    @FXML
    private javafx.scene.control.TextField camp_UrlIMG;
    @FXML
    private void guardarPlat(ActionEvent event) throws SQLException, IOException {
        String nom = camp_nom.getText();
        String descripcio = camp_descripcio.getText();
        Double preu = Double.parseDouble(camp_preu.getText());
        String ingredients = camp_ingredients.getText();
        String urlIMG = camp_UrlIMG.getText();

        Plat plat = new Plat(1, nom, descripcio, preu, ingredients, urlIMG);

        PlatDAO platDAO = new PlatDAOImpl(obtenirConnexio());
        platDAO.createPlat(plat);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fje/edu/m03uf6projecterestaurant/primer_plat.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
