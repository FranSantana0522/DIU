package ch.makery.address.model.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionJDBC {
    public ConexionJDBC() {
}

    /**
     * Conecta con la base de datos
     * @return conn -Conexion con la base de datos
     * @throws SQLException
     */
        public Connection conectarBD() throws SQLException {
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/Agenda?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
                Class.forName("com.mysql.cj.jdbc.Driver");
                return conn;
            } catch (SQLException var2) {
                SQLException ex = var2;
                System.out.println("\n--- SQLException capturada ---\n");

                while(ex != null) {
                    System.out.println("Mensaje:   " + ex.getMessage());
                    System.out.println("SQLState:  " + ex.getSQLState());
                    System.out.println("ErrorCode: " + ex.getErrorCode());
                    ex = ex.getNextException();
                    System.out.println("");
                }

                throw new SQLException();
            } catch (Exception var3) {
                throw new SQLException();
            }
        }

    /**
     * Desconecta la base de datos
     * @param conn
     */
    public void desconectarBD(Connection conn) {
            try {
                conn.close();
            } catch (SQLException var3) {
                SQLException ex = var3;
                System.out.println("\n--- SQLException capturada ---\n");

                while(ex != null) {
                    System.out.println("Mensaje:   " + ex.getMessage());
                    System.out.println("SQLState:  " + ex.getSQLState());
                    System.out.println("ErrorCode: " + ex.getErrorCode());
                    ex = ex.getNextException();
                    System.out.println("");
                }
            }

        }
}
