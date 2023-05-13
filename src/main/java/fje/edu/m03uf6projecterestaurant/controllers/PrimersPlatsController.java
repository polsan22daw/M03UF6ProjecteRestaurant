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

public class PrimersPlatsController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            inicialitzarBotonsPlats();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //actualitzarImatges();
    }

    @FXML
    private Button nextButton;
    @FXML
    private Button crearButton;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private void canviarEscenaAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root;
        if (event.getSource() == nextButton) {
            root = FXMLLoader.load(getClass().getResource("/fje/edu/m03uf6projecterestaurant/segon_plat.fxml"));
        } else if (event.getSource() == crearButton){
            root = FXMLLoader.load(getClass().getResource("/fje/edu/m03uf6projecterestaurant/crear_plat.fxml"));
        }else if (event.getSource() instanceof Button) {
            root = FXMLLoader.load(getClass().getResource("/fje/edu/m03uf6projecterestaurant/editar_esborrar_plat.fxml"));
        }else {
            return;
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleBotonCrear(ActionEvent event) throws IOException {
        CrearPlatController crearPlatController = new CrearPlatController();
        crearPlatController.setCategoria("primer plat");
        canviarEscenaAction(event);
    }
    /*public void actualitzarImatges() {
        PlatDAO platDAO = new PlatDAOImpl();
        try {
            Plat plato1 = platDAO.getPlatById(1);

            Image image1 = platDAO.obtenirImatgeDeUrl(plato1.getUrlIMG());
            ImageView imageView1 = new ImageView(image1);
            imageView1.setFitWidth(150);
            imageView1.setFitHeight(150);
            button1.setGraphic(imageView1);

            Plat plato2 = platDAO.getPlatById(2);
            Image image2 = platDAO.obtenirImatgeDeUrl(plato2.getUrlIMG());
            ImageView imageView2 = new ImageView(image2);
            imageView2.setFitWidth(150);
            imageView2.setFitHeight(150);
            button2.setGraphic(imageView2);

            Plat plato3 = platDAO.getPlatById(3);
            Image image3 = platDAO.obtenirImatgeDeUrl(plato3.getUrlIMG());
            ImageView imageView3 = new ImageView(image3);
            imageView3.setFitWidth(150);
            imageView3.setFitHeight(150);
            button3.setGraphic(imageView3);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }*/

    @FXML
    public void mostrarFormulariEditar(ActionEvent event) throws IOException {
        Button botonPresionado = (Button) event.getSource();
        Plat platSeleccionado = null;
        if (botonPresionado.getId().equals("button1")) {
            platSeleccionado = new PlatDAOImpl().getPlatById(1);
        } else if (botonPresionado.getId().equals("button2")) {
            platSeleccionado = new PlatDAOImpl().getPlatById(2);
        } else if (botonPresionado.getId().equals("button3")) {
            platSeleccionado = new PlatDAOImpl().getPlatById(3);;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fje/edu/m03uf6projecterestaurant/editar_esborrar_plat.fxml"));
        Parent root = loader.load();
        EditarEsborrarPlatController controlador = loader.getController();
        controlador.setPlatSeleccionat(platSeleccionado);
        Scene scene = botonPresionado.getScene();
        scene.setRoot(root);
    }

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private void inicialitzarBotonsPlats() throws SQLException {
        List<Plat> plats = new PlatDAOImpl().selectAllPlats();
        VBox vbox = new VBox();
        vbox.setSpacing(10);

        for (Plat plat : plats) {
            if (plat.getCategoria().equals("primer plat")) {
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