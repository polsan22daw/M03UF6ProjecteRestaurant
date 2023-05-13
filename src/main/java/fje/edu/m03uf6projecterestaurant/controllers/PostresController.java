package fje.edu.m03uf6projecterestaurant.controllers;

import fje.edu.m03uf6projecterestaurant.classes.Plat;
import fje.edu.m03uf6projecterestaurant.classes.PlatDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class PostresController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            inicialitzarBotonsPlats();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private Button crearButton;
    @FXML
    private Button previousButton;
    @FXML
    private void canviarEscenaAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root;
        if(event.getSource()==crearButton){
            root = FXMLLoader.load(getClass().getResource("/fje/edu/m03uf6projecterestaurant/crear_plat.fxml"));
        }else if (event.getSource()==previousButton) {
            root = FXMLLoader.load(getClass().getResource("/fje/edu/m03uf6projecterestaurant/segon_plat.fxml"));
        }else if (event.getSource() instanceof Button) {
            root = FXMLLoader.load(getClass().getResource("/fje/edu/m03uf6projecterestaurant/editar_esborrar_plat.fxml"));
        }else {
            root = FXMLLoader.load(getClass().getResource("/fje/edu/m03uf6projecterestaurant/primer_plat.fxml"));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleBotonCrear(ActionEvent event) throws IOException {
        CrearPlatController crearPlatController = new CrearPlatController();
        crearPlatController.setCategoria("postre");
        canviarEscenaAction(event);
    }

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private void inicialitzarBotonsPlats() throws SQLException {
        List<Plat> plats = new PlatDAOImpl().selectAllPlats();
        VBox vbox = new VBox();
        vbox.setSpacing(10);

        for (Plat plat : plats) {
            if (plat.getCategoria().equals("postre")) {
                Image img = new Image(plat.getUrlIMG());
                ImageView imgView = new ImageView(img);
                imgView.setFitHeight(150);
                imgView.setFitWidth(150);
                Button button = new Button();
                button.setGraphic(imgView);
                button.setOnAction(e -> {
                    try {
                        canviarEscenaAction(e);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                vbox.getChildren().add(button);
            }
        }
        scrollPane.setContent(vbox);
    }
}
