package fje.edu.m03uf6projecterestaurant;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Classe que estableix una connexió amb una BD mitjançant JDBC definit en un
 * fitxer. Utilitza blocs try/catch compatibles amb JDK 6 o <
 *
 * @author sergi grau
 * @version 2.0, 02.02.2012
 */
public class ConnexioMesProves {

    public static void main(String args[]) {
        try {
            executarTest();
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Realitza un test amb la BD, creant una taula, afegint valors i recuperant
     * registres. Finalment esborra la taula creada
     */
    public static void executarTest() throws SQLException, IOException {
        Connection conn = obtenirConnexio();


        try {
            Statement stat = conn.createStatement();

            //stat.executeUpdate("CREATE TABLE plat (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, nom VARCHAR(50), descripcio VARCHAR(200), preu DOUBLE, urlIMG VARCHAR(200), categoria VARCHAR(50), ingredients VARCHAR(200))");

            stat.executeUpdate("INSERT INTO plat (nom, descripcio, preu, urlIMG, categoria, ingredients)"
                    + "VALUES ('Plato 1', 'Descripción del plato 1', 10.99, 'https://example.com/image1.jpg', 'Primers', 'Ingrediente 1, Ingrediente 2')");
            stat.executeUpdate("INSERT INTO plat (nom, descripcio, preu, urlIMG, categoria, ingredients)"
                    + "VALUES ('Plato 2', 'Descripción del plato 2', 15.99, 'https://example.com/image2.jpg', 'Segons', 'Ingrediente 3, Ingrediente 4')");
            System.out.println("Valores insertados en la tabla 'plat' correctamente.");

            ResultSet resultat = stat.executeQuery("SELECT * FROM plat");
            while (resultat.next()) {
                System.out.println("Id: " + resultat.getInt("id")
                        + ", Nom: " + resultat.getString("nom")
                        + ", Descripció: " + resultat.getString("descripcio")
                        + ", Preu: " + resultat.getDouble("preu")
                        + ", URL de la imatge: " + resultat.getString("urlIMG")
                        + ", Categoría: " + resultat.getString("categoria")
                        + ", Ingredients: " + resultat.getString("ingredients"));
            }
            resultat.close();
            // stat.executeUpdate("DROP TABLE plat");
            // System.out.println("Tabla 'plat' eliminada correctamente.");
        } finally {
            conn.close();
        }
    }

    /**
     * Relitza una connexió a la BD, a partir de les propietats especificades en
     * un fitxer database.properties
     *
     * @return la connexió amb la BD
     */
    public static Connection obtenirConnexio() throws SQLException, IOException {
        Properties props = new Properties();
        FileInputStream in = new FileInputStream("C:\\Users\\polsa\\OneDrive\\Escritorio\\DAW2\\M03\\UF6\\M03UF6ProjecteRestaurant\\src\\main\\resources\\database.properties");
        props.load(in);
        in.close();

        String drivers = props.getProperty("jdbc.drivers");
        if (drivers != null) {
            System.setProperty("jdbc.drivers", drivers);
        }
        String url = props.getProperty("jdbc.url");
        String usuari = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");

        return DriverManager.getConnection(url, usuari, password);
    }
}