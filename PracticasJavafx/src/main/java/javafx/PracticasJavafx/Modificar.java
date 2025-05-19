package javafx.PracticasJavafx;

import java.sql.Connection;
import java.util.ArrayList;

import Practica.sql.UtilsBD;
import Practica.sql.libroDAO;
import Practica.sql.libroDO;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Modificar extends GridPane{
	
	private Connection con =UtilsBD.ConectarBD();
	
	public Modificar() {
		Label lblSelecionar =new Label("Seleciona el libro: ");
		ComboBox cBoxLibro = libroDAO.cargarCh(con);
		
		
		Label lblIdLibro = new Label("Id Libro");
		Label lblTitulo = new Label("Titulo");
		Label lblGenero = new Label("Genero");
		Label lblAutor = new Label("Autor");
		Label lblDispo = new Label("Disponibilidad");
		Label lblPortada = new Label("Portada");
		Label lblAnioPubli = new Label("Año Publicacion");
		
		
		Label IdLibro = new Label();
		Label Titulo = new Label();
		Label Genero = new Label();
		Label Autor = new Label();
		Label Dispo = new Label();
		Label Portada = new Label();
		Label AnioPubli = new Label();
		
		
		
		this.add(lblSelecionar, 0, 0);
		this.add(cBoxLibro, 0, 1);
		
		
		this.add(lblIdLibro, 0, 2);
		this.add(lblTitulo, 1, 2);
		this.add(lblGenero, 2, 2);
		this.add(lblAutor, 3, 2);
		this.add(lblDispo, 4, 2);
		this.add(lblPortada, 5, 2);
		this.add(lblAnioPubli, 6, 2);
		
		
		
		
	
		cBoxLibro.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> {
					Titulo.setText((String)cBoxLibro.getSelectionModel().getSelectedItem());
					
					if((String)cBoxLibro.getValue()==Titulo.getText()) {
					
					libroDO libro = libroDAO.cargar(con, Titulo.getText());
					
					IdLibro.setText(Integer.toString(libro.getIdLibro()));
					Genero.setText(libro.getGenero());
					Autor.setText(libro.getAutor());
					Dispo.setText(Character.toString(libro.getDisponible()));
					Portada.setText(libro.getPortada());
					AnioPubli.setText(Integer.toString(libro.getAnioPublicacion()));
					}
		});

				
				
			
				
			
				this.add(IdLibro, 0, 3);
				this.add(Titulo, 1, 3);
				this.add(Genero, 2, 3);
				this.add(Autor, 3, 3);
				this.add(Dispo, 4, 3);
				this.add(Portada, 5, 3);
				this.add(AnioPubli, 6, 3);
				
		
		
		this.setVgap(10);
		this.setHgap(10);
		this.setPadding(new Insets(10, 10, 10, 10));
		
		
		
	}
	
}
