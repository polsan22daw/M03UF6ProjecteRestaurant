package fje.edu.m03uf6projecterestaurant.controllers;

import fje.edu.m03uf6projecterestaurant.classes.Plat;
import fje.edu.m03uf6projecterestaurant.classes.PlatDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class SegonsPlatsController implements Initializable {


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
    private Button nextButton;
    @FXML
    private Button previousButton;
    @FXML
    private void canviarEscenaAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root;
        if(event.getSource()==nextButton){
            root = FXMLLoader.load(getClass().getResource("/fje/edu/m03uf6projecterestaurant/postres.fxml"));
        }else if (event.getSource() == previousButton) {
            root = FXMLLoader.load(getClass().getResource("/fje/edu/m03uf6projecterestaurant/primer_plat.fxml"));
        }else if (event.getSource() == crearButton) {
            root = FXMLLoader.load(getClass().getResource("/fje/edu/m03uf6projecterestaurant/crear_plat.fxml"));
        }else if (event.getSource() instanceof Button) {
            root = FXMLLoader.load(getClass().getResource("/fje/edu/m03uf6projecterestaurant/editar_esborrar_plat.fxml"));
        }
        else {
            root = FXMLLoader.load(getClass().getResource("/fje/edu/m03uf6projecterestaurant/primer_plat.fxml"));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleBotonCrear(ActionEvent event) throws IOException {
        CrearPlatController crearPlatController = new CrearPlatController();
        crearPlatController.setCategoria("segon plat");
        canviarEscenaAction(event);
    }


    @FXML
    public void mostrarFormulariEditar(int idPlatSeleccionat) throws IOException {
        Plat platSeleccionat = new PlatDAOImpl().getPlatById(idPlatSeleccionat);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fje/edu/m03uf6projecterestaurant/editar_esborrar_plat.fxml"));
        Parent root = loader.load();
        EditarEsborrarPlatController controlador = loader.getController();
        controlador.setPlatSeleccionat(platSeleccionat);
        Scene scene = scrollPane.getScene();
        scene.setRoot(root);
    }

    @FXML
    private ScrollPane scrollPane;

    public void inicialitzarBotonsPlats() throws SQLException {
        List<Plat> plats = new PlatDAOImpl().selectAllPlats();
        VBox vbox = new VBox();
        vbox.setSpacing(70);
        vbox.setPadding(new Insets(20, 20, 20, 20));

        Label title = new Label("Segons Plats");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        vbox.getChildren().add(title);

        HBox hbox = new HBox();
        hbox.setSpacing(50);
        hbox.setAlignment(Pos.CENTER);
        int count = 0;

        for (Plat plat : plats) {
            if (plat.getCategoria().equals("segon plat")) {
                Image img = new Image(plat.getUrlIMG());
                ImageView imgView = new ImageView(img);
                imgView.setFitHeight(150);
                imgView.setFitWidth(150);
                Button button = new Button();
                button.setGraphic(imgView);
                button.setStyle("-fx-background-color: #bfbfbf; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-color: #505050; -fx-border-width: 2px;");
                button.setOnAction(e -> {
                    try {
                        mostrarFormulariEditar(plat.getId());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                button.setId(Integer.toString(plat.getId()));

                VBox.setMargin(button, new Insets(20, 0, 0, 0));
                hbox.getChildren().add(button);
                count++;

                if (count == 3) {
                    vbox.getChildren().add(hbox);
                    hbox = new HBox();
                    hbox.setSpacing(50);
                    hbox.setAlignment(Pos.CENTER);
                    count = 0;
                }
            }
        }

        if (count != 0) {
            vbox.getChildren().add(hbox);
        }

        scrollPane.setContent(vbox);
    }


}
