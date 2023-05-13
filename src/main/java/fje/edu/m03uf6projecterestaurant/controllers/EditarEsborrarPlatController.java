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
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

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
    private void editarPlat(ActionEvent event) throws IOException, SQLException {
        String nom = camp_nom.getText();
        String descripcio = camp_descripcio.getText();
        Double preu = Double.parseDouble(camp_preu.getText());
        String ingredients = camp_ingredients.getText();
        String urlIMG = camp_UrlIMG.getText();

        Plat plat = new Plat(platSeleccionat.getId(), nom, descripcio, preu, ingredients, urlIMG, categoria);

        PlatDAO platDAO = new PlatDAOImpl();
        if (platDAO.updatePlat(plat)) {
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Informació");
            info.setHeaderText(null);
            info.setContentText("Plat actualitzat correctament!");
            info.showAndWait();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fje/edu/m03uf6projecterestaurant/primer_plat.fxml"));
            Parent root = loader.load();
            PrimersPlatsController controller = loader.getController();
            controller.inicialitzarBotonsPlats();
            Scene scene = new Scene(root);
            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.show();
        } else {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error");
            error.setHeaderText(null);
            error.setContentText("No s'ha pogut actualitzar el plat!");
            error.showAndWait();
        }
    }

    public void esborrarPlat() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmació");
        alert.setHeaderText(null);
        alert.setContentText("Estàs segur que vols eliminar aquest plat?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            PlatDAO platDAO = new PlatDAOImpl();
            boolean eliminat = platDAO.deletePlat(platSeleccionat);
            if (eliminat) {
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setTitle("Informació");
                info.setHeaderText(null);
                info.setContentText("Plat eliminat correctament!");
                info.showAndWait();

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fje/edu/m03uf6projecterestaurant/primer_plat.fxml"));
                    Parent root = loader.load();
                    PrimersPlatsController controller = loader.getController();
                    controller.inicialitzarBotonsPlats();
                    Scene scene = EsborrarButton.getScene();
                    scene.setRoot(root);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Error");
                error.setHeaderText(null);
                error.setContentText("No s'ha pogut eliminar el plat!");
                error.showAndWait();
            }
        }
    }
}
