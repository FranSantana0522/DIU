package ch.makery.address.model.repository.impl;



import ch.makery.address.model.ExcepcionPerson;
import ch.makery.address.model.PersonVO;
import ch.makery.address.model.repository.PersonRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class PersonRepositoryImpl implements PersonRepository {
    private final ConexionJDBC conexion = new ConexionJDBC();
    private Statement stmt;
    private String sentencia;
    private ArrayList<PersonVO> personas;
    private PersonVO persona;

    public PersonRepositoryImpl() {
    }

    public ArrayList<PersonVO> ObtenerListaPersona() throws ExcepcionPerson {
        try {
            Connection conn = this.conexion.conectarBD();
            this.personas = new ArrayList();
            this.stmt = conn.createStatement();
            this.sentencia = "SELECT * FROM Persona";
            ResultSet rs = this.stmt.executeQuery(this.sentencia);

            while(rs.next()) {
                String n = rs.getString("Nombre");
                String a = rs.getString("Apellido");
                String c = rs.getString("Calle");
                Integer cp = rs.getInt("Codigo_Postal");
                String ciu = rs.getString("Ciudad");
                LocalDate f= rs.getDate("Año_nacimiento").toLocalDate();
                this.persona = new PersonVO(n, a,c,cp,ciu,f);
                this.personas.add(this.persona);
            }

            this.conexion.desconectarBD(conn);
            return this.personas;
        } catch (SQLException var6) {
            throw new ExcepcionPerson("No se puede");
        }
    }

    public void addPerson(PersonVO m) throws ExcepcionPerson {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            this.sentencia = "INSERT INTO Persona (Nombre, Apellido, Calle, Codigo_Postal, Ciudad, Año_nacimiento) VALUES ('"+ m.getNombre() + "','" + m.getApellido() + "','"+ m.getCalle()+ "','" + m.getCodigoPostal()+ "','"+ m.getCiudad()+"','"+m.getNacimiento()+ "');";
            this.stmt.executeUpdate(this.sentencia);
            this.stmt.close();
            this.conexion.desconectarBD(conn);
        } catch (SQLException var3) {
            throw new ExcepcionPerson("No se ha podido realizar la operación");
        }
    }

    public void deletePerson(Integer idPersona) throws ExcepcionPerson {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            Statement comando = conn.createStatement();
            String sql = String.format("DELETE FROM Persona WHERE Identificador = %d", idPersona);
            comando.executeUpdate(sql);
            this.conexion.desconectarBD(conn);
        } catch (SQLException var5) {
            throw new ExcepcionPerson("No se ha podido relaizr la eliminación");
        }
    }

    public void editPerson(PersonVO PersonVO) throws ExcepcionPerson {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            String sql = String.format("UPDATE Persona SET Nombre = '%s', Apellido = '%s', Calle = '%s', Codigo_Postal = '%s', Ciudad = '%s', Año_nacimiento = '%s' WHERE Identificador = %d", PersonVO.getNombre(), PersonVO.getApellido(),PersonVO.getCalle(),PersonVO.getCodigoPostal(),PersonVO.getCiudad(),PersonVO.getNacimiento(),PersonVO.getId());
            this.stmt.executeUpdate(sql);
        } catch (Exception var4) {
            throw new ExcepcionPerson("No se ha podido relaizr la edición");
        }
    }

    public int lastId() throws ExcepcionPerson {
        int lastPersonId = 0;

        try {
            Connection conn = this.conexion.conectarBD();
            Statement comando = conn.createStatement();

            for(ResultSet registro = comando.executeQuery("SELECT Identificador FROM Persona ORDER BY Identificador DESC LIMIT 1"); registro.next(); lastPersonId = registro.getInt("Identificador")) {
            }

            return lastPersonId;
        } catch (SQLException var5) {
            throw new ExcepcionPerson("No se ha podido realizar la busqueda del ID");
        }
    }
}
