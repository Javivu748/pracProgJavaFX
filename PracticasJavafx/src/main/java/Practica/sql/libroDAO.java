package Practica.sql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;



public class libroDAO {
	
	
	
	
	public static int insertar(libroDO libro, Connection con) {
	    String sql = "INSERT INTO libro (titulo, genero, autor, anioPublicacion, disponibilidad, portada) VALUES (?, ?, ?, ?, ?, ?)";
	    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
	                
	        pstmt.setString(1, libro.getTitulo());
	        pstmt.setString(2, libro.getGenero());
	        pstmt.setString(3, libro.getAutor());
	        pstmt.setInt(4, libro.getAnioPublicacion()); // Insertar como entero
	        pstmt.setString(5, Character.toString(libro.getDisponible()));
	        pstmt.setString(6, libro.getPortada());

	        return pstmt.executeUpdate() > 0 ? 0 : -1;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return -1;
	    }
	}
	
	public static int getYearFromDate(Date date) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    return calendar.get(Calendar.YEAR);
	}
	
	
	public static ComboBox cargarCh(Connection con) {
		ComboBox ch2 = new ComboBox();
		try {
		
		ResultSet rs = getLibros(con);
		
		while(rs.next()) {
			ch2.getItems().add(rs.getString("titulo"));
		}
		
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ch2;
		
	}
	
	
	public static ResultSet getLibros(Connection con) {
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("select titulo from libro");
			
			return rs;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static ArrayList<libroDO> getLibrosDO(Connection con) {

		try {

			ArrayList<libroDO> listaLibros = new ArrayList<libroDO>();

			//Con la conexion ya activa (con) crea una sentencia para poder ejecutar
			//sentencias sql
			Statement stmt = con.createStatement();

			//Directamente ejecutamos la sentencia
			//Si es una sentenci tipo select se hace con executeQuery
			//Esta nos devuelve un objeto de tipo ResultSet con los datos de la query
			ResultSet rs = stmt.executeQuery("select * from libro");

			//Recorremos el resultset
			while (rs.next()) {

				//Creamos un animalDO
				libroDO libro = new libroDO();

				//Cargamos en el animalDO los datos del registro actual del resultset
				libro.setIdLibro(rs.getInt("idlibro"));
				libro.setTitulo(rs.getString("titulo"));
				libro.setGenero(rs.getString("genero"));
				libro.setAutor(rs.getString("autor"));
				libro.setDisponible(rs.getString("disponibilidad").charAt(0));
				libro.setPortada(rs.getString("portada"));
				libro.setAnioPublicacion(rs.getInt("anioPublicacion"));

				//Ponemos el AnimalDO en la arraylist
				listaLibros.add(libro);
			}

			return listaLibros;

		} catch (SQLException e) {
			e.printStackTrace();

		}

		return null;
	}

	
	public static libroDO cargar(Connection con, String titulo) {
	    libroDO libro = null;
	    String sql = "SELECT * FROM libro WHERE titulo = ?";
	    
	    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
	        pstmt.setString(1, titulo);
	        
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                // Crear nueva instancia de libroDO
	                libro = new libroDO();
	                
	                // Asignar valores desde el ResultSet
	                libro.setIdLibro(rs.getInt("idlibro"));
	                libro.setTitulo(rs.getString("titulo"));
	                libro.setGenero(rs.getString("genero"));
	                libro.setAutor(rs.getString("autor"));
	                
	                // Manejar disponibilidad (asegurando que no sea nulo)
	                String disponibilidad = rs.getString("disponibilidad");
	                libro.setDisponible(disponibilidad.charAt(0));
	                
	                libro.setPortada(rs.getString("portada"));
	                libro.setAnioPublicacion(rs.getInt("anioPublicacion"));
	            }
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al cargar libro por título: " + e.getMessage());
	        e.printStackTrace();
	    }
	    
	    return libro;
	}
	
	
	
}
