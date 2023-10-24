package ch.makery.address;

import ch.makery.address.model.repository.impl.ConexionJDBC;

import java.sql.Connection;
import java.sql.SQLException;

public class MainPrueba {
    public static void main(String[] args) throws SQLException {
        ConexionJDBC conexion = new ConexionJDBC();
        Connection conn = conexion.conectarBD();

    }
}
