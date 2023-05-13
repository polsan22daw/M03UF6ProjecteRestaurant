package fje.edu.m03uf6projecterestaurant.classes;

import javafx.scene.image.Image;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static fje.edu.m03uf6projecterestaurant.ConnexioMesProves.obtenirConnexio;

public class PlatDAOImpl implements PlatDAO {
    private static final String SELECT_ALL_PLATS_SQL = "SELECT * FROM Plat";
    private Connection connection;

    public PlatDAOImpl(Connection connection) throws SQLException, IOException {
        this.connection = obtenirConnexio();
    }

    public PlatDAOImpl() {

    }

    @Override
    public Plat getPlatById(int id) {
        Connection connection = null;
        Plat plat = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = obtenirConnexio();
            preparedStatement = connection.prepareStatement("SELECT * FROM plat WHERE id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                plat = new Plat(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("descripcio"),
                        resultSet.getDouble("preu"),
                        resultSet.getString("ingredients"),
                        resultSet.getString("urlIMG"),
                        resultSet.getString("categoria")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return plat;
    }

    private static final String CREATE_SQL = "INSERT INTO plat (nom, descripcio, preu, ingredients, urlIMG, categoria) VALUES (?, ?, ?, ?, ?, ?)";

    @Override
    public boolean createPlat(Plat plat) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int generatedId = 0;

        try {
            connection = obtenirConnexio();
            statement = connection.prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, plat.getNom());
            statement.setString(2, plat.getDescripcio());
            statement.setDouble(3, plat.getPreu());
            statement.setString(4, plat.getIngredients());
            statement.setString(5, plat.getUrlIMG());
            statement.setString(6, plat.getCategoria());

            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return generatedId > 0;
    }

    private static final String UPDATE_PLAT_QUERY = "UPDATE plat SET nom = ?, descripcio = ?, preu = ?, ingredients = ?, urlIMG = ? WHERE id = ?";

    @Override
    public boolean updatePlat(Plat plat) {
        try (Connection conn = obtenirConnexio();
             PreparedStatement statement = conn.prepareStatement(UPDATE_PLAT_QUERY)) {

            statement.setString(1, plat.getNom());
            statement.setString(2, plat.getDescripcio());
            statement.setDouble(3, plat.getPreu());
            statement.setString(4, plat.getIngredients());
            statement.setString(5, plat.getUrlIMG());
            statement.setInt(6, plat.getId());

            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.err.println("Error updating plat: " + e.getMessage());
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final String DELETE_PLAT_QUERY = "DELETE FROM plat WHERE id = ?";

    @Override
    public boolean deletePlat(Plat plat) {
        try (Connection conn = obtenirConnexio();
             PreparedStatement statement = conn.prepareStatement(DELETE_PLAT_QUERY)) {

            statement.setInt(1, plat.getId());
            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.err.println("Error esborrant plat: " + e.getMessage());
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Image obtenirImatgeDeUrl(String url) throws SQLException, IOException {
        Image image = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = obtenirConnexio();
            statement = connection.prepareStatement("SELECT urlIMG FROM plat WHERE urlIMG = ?");
            statement.setString(1, url);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String imageUrl = resultSet.getString("urlIMG");
                image = new Image(imageUrl);
            }
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return image;
    }

    public List<Plat> selectAllPlats() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        List<Plat> plats = new ArrayList<>();

        try {
            connection = obtenirConnexio();
            statement = connection.prepareStatement(SELECT_ALL_PLATS_SQL);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Plat plat = new Plat(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("descripcio"),
                        resultSet.getDouble("preu"),
                        resultSet.getString("ingredients"),
                        resultSet.getString("urlIMG"),
                        resultSet.getString("categoria")
                );

                plats.add(plat);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return plats;
    }
}