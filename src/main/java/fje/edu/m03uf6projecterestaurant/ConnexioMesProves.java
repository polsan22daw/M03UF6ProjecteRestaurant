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
class ConnexioMesProves {

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

            stat.executeUpdate("CREATE TABLE test (text CHAR(20))");
            stat.executeUpdate("INSERT INTO test VALUES ('sergi')");
            stat.executeUpdate("INSERT INTO test VALUES ('paco')");
            stat.executeUpdate("INSERT INTO test VALUES ('rafael')");

            ResultSet resultat = stat.executeQuery("SELECT * FROM test");
            if (resultat.next()) {
                System.out.println(resultat.getString(1));
            }
            resultat.close();
            //stat.executeUpdate("DROP TABLE prova");
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
        FileInputStream in = new FileInputStream("C:\\Users\\angel\\Desktop\\DAW\\M03\\UF6\\PrimerJDBC\\src\\main\\resources\\database.properties");
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