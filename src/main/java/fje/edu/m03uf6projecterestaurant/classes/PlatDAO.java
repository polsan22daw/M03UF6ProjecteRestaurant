package fje.edu.m03uf6projecterestaurant.classes;

import javafx.scene.image.Image;

import java.io.IOException;
import java.sql.SQLException;

public interface PlatDAO {
    Plat getPlatById(int id);
    boolean createPlat(Plat plat) throws SQLException;
    boolean updatePlat(Plat plat);
    boolean deletePlat(int id);

    Image obtenirImatgeDeUrl(String url) throws SQLException, IOException;
}
