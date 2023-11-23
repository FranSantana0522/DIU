package clases.hotel.gestionhotel.modelo.repository.impl;

import clases.hotel.gestionhotel.modelo.ExceptionGH;
import clases.hotel.gestionhotel.modelo.PersonaVO;
import clases.hotel.gestionhotel.modelo.ReservaVO;
import clases.hotel.gestionhotel.modelo.repository.GestionRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class GestionRepositoryImpl implements GestionRepository {
    private final ConexionJDBC conexion = new ConexionJDBC();
    private Statement stmt;
    private String sentencia;
    private ArrayList<PersonaVO> personas;
    private PersonaVO persona;
    private ArrayList<ReservaVO> reservas;
    private ReservaVO reserva;

    public GestionRepositoryImpl() {
    }

    @Override
    public ArrayList<PersonaVO> ObtenerListaPersonaVO() throws ExceptionGH {
        try {
            Connection conn = this.conexion.conectarBD();
            this.personas = new ArrayList();
            this.stmt = conn.createStatement();
            this.sentencia = "SELECT * FROM Persona ORDER BY Nombre, Apellido";
            ResultSet rs = this.stmt.executeQuery(this.sentencia);

            while(rs.next()) {
                String dni=rs.getString("DNI");
                String n = rs.getString("Nombre");
                String a = rs.getString("Apellido");
                Integer d = rs.getInt("Direccion");
                String lo = rs.getString("Localidad");
                String p= rs.getString("Provincia");
                this.persona = new PersonaVO(dni,n,a,d,lo,p);
                this.personas.add(this.persona);
            }
            this.conexion.desconectarBD(conn);
            return this.personas;
        }catch (SQLException var6) {
            throw new ExceptionGH("No se puede");
        }
    }

    @Override
    public ArrayList<ReservaVO> ObtenerListaReservaVO(String dniC) throws ExceptionGH {
        try {
            Connection conn = this.conexion.conectarBD();
            this.reservas = new ArrayList();
            this.stmt = conn.createStatement();
            String sql = String.format("SELECT * FROM Reserva WHERE DNI_Cliente IN (SELECT DNI FROM Persona WHERE DNI='%s') ORDER BY Fecha_Llegada DESC", dniC);
            ResultSet rs = this.stmt.executeQuery(sql);
            while(rs.next()) {
                Integer id=rs.getInt("Identificador");
                LocalDate llegada = rs.getDate("Fecha_Llegada").toLocalDate();
                LocalDate fin = rs.getDate("Fecha_Fin").toLocalDate();
                Integer numH = rs.getInt("Numero_Habitaciones");
                String tipoH = rs.getString("Tipo_Habitacion");
                Boolean f= rs.getBoolean("Fumador");
                String regA = rs.getString("Regimen_de_alojamiento");
                String DNIC = rs.getString("DNI_Cliente");
                this.reserva = new ReservaVO(llegada,fin,numH,tipoH,f,regA,DNIC);
                reserva.setIdVO(id);
                this.reservas.add(this.reserva);
            }
            this.conexion.desconectarBD(conn);
            return this.reservas;
        }catch (SQLException var6) {
            var6.printStackTrace();
            throw new ExceptionGH("No se puede");
        }
    }

    @Override
    public PersonaVO busquedaPersonaVO(String dniB) throws ExceptionGH {
        try {
            Connection conn = this.conexion.conectarBD();
            this.persona = new PersonaVO();
            this.stmt = conn.createStatement();
            String sql = String.format("SELECT * FROM Persona WHERE DNI='%s'", dniB);
            ResultSet rs = this.stmt.executeQuery(sql);
            while(rs.next()) {
                String dni=rs.getString("DNI");
                String n = rs.getString("Nombre");
                String a = rs.getString("Apellido");
                Integer d = rs.getInt("Direccion");
                String lo = rs.getString("Localidad");
                String p= rs.getString("Provincia");
                this.persona = new PersonaVO(dni,n,a,d,lo,p);
            }
            this.conexion.desconectarBD(conn);
            return this.persona;
        }catch (SQLException var6) {
            throw new ExceptionGH("No se puede");
        }
    }

    @Override
    public void addPersonaVO(PersonaVO m) throws ExceptionGH {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            this.sentencia = "INSERT INTO Persona (DNI, Nombre, Apellido, Direccion, Localidad, Provincia) VALUES ('"+ m.getDNIVO() + "','" + m.getNombreVO() + "','"+ m.getApellidosVO()+ "','" + m.getDireccionVO()+ "','"+ m.getLocalidadVO()+"','"+m.getProvinciaVO()+ "');";
            this.stmt.executeUpdate(this.sentencia);
            this.stmt.close();
            this.conexion.desconectarBD(conn);
        } catch (SQLException var3) {
            throw new ExceptionGH("No se ha podido realizar la operación");
        }
    }

    @Override
    public void addReservaVO(ReservaVO r) throws ExceptionGH {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            Integer fum;
            if(r.getFumadorVO()){
                fum=1;
            }else {
                fum=0;
            }
            this.sentencia = "INSERT INTO Reserva (Fecha_Llegada, Fecha_Fin, Numero_Habitaciones, Tipo_Habitacion, Fumador, Regimen_de_alojamiento, DNI_Cliente) VALUES ('" + r.getFechaLlegadaVO() + "','"+ r.getFechaFinVO()+ "','" + r.getNumHabitacionVO()+ "','"+ r.getTipHabVO()+"','"+ fum+"','"+ r.getRegAlojVO()+"','"+r.getDNICliente()+ "');";
            this.stmt.executeUpdate(this.sentencia);
            this.stmt.close();
            this.conexion.desconectarBD(conn);
        } catch (SQLException var3) {
            var3.printStackTrace();
            throw new ExceptionGH("No se ha podido realizar la operación");
        }
    }

    @Override
    public void deletePersonaVO(String DniP) throws ExceptionGH {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            Statement comando = conn.createStatement();
            String sql = String.format("DELETE FROM Persona WHERE DNI = '%s'", DniP);
            comando.executeUpdate(sql);
            this.conexion.desconectarBD(conn);
        } catch (SQLException var5) {
            throw new ExceptionGH("No se ha podido realizar la eliminación");
        }
    }

    @Override
    public void deleteReservaVO(Integer id) throws ExceptionGH {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            Statement comando = conn.createStatement();
            String sql = String.format("DELETE FROM Reserva WHERE Identificador = %d", id);
            comando.executeUpdate(sql);
            this.conexion.desconectarBD(conn);
        } catch (SQLException var5) {
            throw new ExceptionGH("No se ha podido realizar la eliminación");
        }
    }

    @Override
    public void editPersonaVO(PersonaVO personaVO) throws ExceptionGH {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            String sql = String.format("UPDATE Persona SET Nombre = '%s', Apellido = '%s', Direccion = '%s', Localidad = '%s', Provincia = '%s' WHERE DNI = '%s'", personaVO.getNombreVO(), personaVO.getApellidosVO(),personaVO.getDireccionVO(),personaVO.getLocalidadVO(),personaVO.getProvinciaVO(), personaVO.getDNIVO());
            this.stmt.executeUpdate(sql);
        } catch (Exception var4) {
            throw new ExceptionGH("No se ha podido realizar la edición");
        }
    }

    @Override
    public void editReservaVO(ReservaVO reservaVO) throws ExceptionGH {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            Integer fum;
            if(reservaVO.getFumadorVO()){
                fum=1;
            }else {
                fum=0;
            }
            String sql = String.format("UPDATE Reserva SET Fecha_Llegada = '%s', Fecha_Fin = '%s', Numero_Habitaciones = '%s', Tipo_Habitacion = '%s', Fumador = '%s' , Regimen_de_alojamiento = '%s' , DNI_Cliente = '%s' WHERE Identificador = %d", reservaVO.getFechaLlegadaVO(), reservaVO.getFechaFinVO(),reservaVO.getNumHabitacionVO(),reservaVO.getTipHabVO(),fum,reservaVO.getRegAlojVO(),reservaVO.getDNICliente(), reservaVO.getIdVO());
            this.stmt.executeUpdate(sql);
        } catch (Exception var4) {
            throw new ExceptionGH("No se ha podido realizar la edición");
        }
    }

    @Override
    public int lastIdReservaVO() throws ExceptionGH {
        int lastPersonId = 0;

        try {
            Connection conn = this.conexion.conectarBD();
            Statement comando = conn.createStatement();

            ResultSet resultSet =comando.executeQuery("SELECT AUTO_INCREMENT "+
                    "FROM information_schema.TABLES "+
                    "WHERE TABLE_SCHEMA = 'GestionHoteles' "+
                    "AND TABLE_NAME = 'Reserva'");

            {
                if(resultSet.next()){
                    lastPersonId=resultSet.getInt("AUTO_INCREMENT")-1;
                }
                return lastPersonId;
            }

        } catch (SQLException e) {
            System.out.println(e);
            throw new ExceptionGH("No se ha podido realizar la búsqueda del ID");
        }
    }

    @Override
    public int DNIpersonaVO() throws ExceptionGH {
        return 0;
    }
}