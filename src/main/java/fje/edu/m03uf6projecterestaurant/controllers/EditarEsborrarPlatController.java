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
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class EditarEsborrarPlatController {

    private String categoria;
    @FXML
    private void canviarEscenaAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fje/edu/m03uf6projecterestaurant/primer_plat.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private PlatDAO platDAO = new PlatDAOImpl();

    private Plat plat;

    @FXML
    private Button EditarButton;
    @FXML
    private Button EsborrarButton;
    @FXML
    private TextField camp_nom;
    @FXML
    private TextArea camp_descripcio;
    @FXML
    private TextField camp_preu;
    @FXML
    private TextArea camp_ingredients;
    @FXML
    private TextField camp_UrlIMG;
    private Plat platSeleccionat;
    public void setPlatSeleccionat(Plat plat) {
        this.platSeleccionat = plat;
        camp_nom.setText(plat.getNom());
        camp_descripcio.setText(plat.getDescripcio());
        camp_preu.setText(String.valueOf(plat.getPreu()));
        camp_ingredients.setText(plat.getIngredients());
        camp_UrlIMG.setText(plat.getUrlIMG());
    }

    @FXML
    private void editarPlat(ActionEvent event) throws IOException {
        String nom = camp_nom.getText();
        String descripcio = camp_descripcio.getText();
        Double preu = Double.parseDouble(camp_preu.getText());
        String ingredients = camp_ingredients.getText();
        String urlIMG = camp_UrlIMG.getText();

        Plat plat = new Plat(1, nom, descripcio, preu, ingredients, urlIMG, categoria);

        PlatDAO platDAO = new PlatDAOImpl();
        platDAO.updatePlat(plat);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fje/edu/m03uf6projecterestaurant/primer_plat.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void esborrarPlat(ActionEvent event) throws IOException {
        PlatDAO platDAO = new PlatDAOImpl();
        platDAO.deletePlat(platSeleccionat.getId());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fje/edu/m03uf6projecterestaurant/primer_plat.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void setPlat(Plat platSeleccionat) {
    }
}
