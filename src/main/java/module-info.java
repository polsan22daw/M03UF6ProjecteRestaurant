module fje.edu.m03uf6peojecterestaurant {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens fje.edu.m03uf6projecterestaurant to javafx.fxml;
    exports fje.edu.m03uf6projecterestaurant;
}