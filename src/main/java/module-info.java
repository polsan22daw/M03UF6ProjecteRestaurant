module fje.edu.m03uf6peojecterestaurant {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens fje.edu.m03uf6peojecterestaurant to javafx.fxml;
    exports fje.edu.m03uf6peojecterestaurant;
}