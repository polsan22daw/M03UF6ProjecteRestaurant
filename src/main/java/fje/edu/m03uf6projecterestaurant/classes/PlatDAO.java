package fje.edu.m03uf6projecterestaurant.classes;

import java.sql.SQLException;

public interface PlatDAO {
    Plat getPlatById(int id);
    boolean createPlat(Plat plat) throws SQLException;
    boolean updatePlat(Plat plat);
    boolean deletePlat(int id);
}
