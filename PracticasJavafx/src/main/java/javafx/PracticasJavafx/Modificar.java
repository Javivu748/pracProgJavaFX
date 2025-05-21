package javafx.PracticasJavafx;

import java.sql.Connection;
import java.util.ArrayList;

import Practica.sql.UtilsBD;
import Practica.sql.libroDAO;
import Practica.sql.libroDO;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;

public class Modificar extends GridPane{
	
	private Label IdLibro = new Label("");
	private Label Titulo = new Label("");
	private Label Genero = new Label("");
	private Label Autor = new Label("");
	private Label Dispo = new Label("");
	private Label Portada = new Label("");
	private Label AnioPubli = new Label("");
	
	
	private Connection con =UtilsBD.ConectarBD();
	
	private Button btnEditar = new Button("Editar libro");
	
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
		
		
		
		Button btnEliminar =new Button("Eliminar libro");
		
		
		this.add(lblSelecionar, 0, 0);
		this.add(cBoxLibro, 0, 1);
		
		
		this.add(lblIdLibro, 0, 2);
		this.add(lblTitulo, 1, 2);
		this.add(lblGenero, 2, 2);
		this.add(lblAutor, 3, 2);
		this.add(lblDispo, 4, 2);
		this.add(lblPortada, 5, 2);
		this.add(lblAnioPubli, 6, 2);
		
		this.add(btnEliminar, 0, 4);
		this.add(btnEditar, 1, 4);
		
		
	
		cBoxLibro.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			
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

				
		btnEliminar.setOnAction(e->{
			
			Alert avisoo =new Alert(AlertType.CONFIRMATION);
			avisoo.setContentText("¿Estas seguro que quieres eliminarlo?");
			avisoo.setTitle("AVISO");
			avisoo.showAndWait().ifPresent(respuesta->{
				if(respuesta ==ButtonType.OK) {
					libroDAO.eliminar(Integer.parseInt(IdLibro.getText()), con);
				}
			});
			

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

	public Button getBtnEditar() {
		return btnEditar;
	}

	public void setBtnEditar(Button btnEditar) {
		this.btnEditar = btnEditar;
	}

	public Label getIdLibro() {
		return IdLibro;
	}

	public void setIdLibro(Label idLibro) {
		IdLibro = idLibro;
	}

	public Label getTitulo() {
		return Titulo;
	}

	public void setTitulo(Label titulo) {
		Titulo = titulo;
	}

	public Label getGenero() {
		return Genero;
	}

	public void setGenero(Label genero) {
		Genero = genero;
	}

	public Label getAutor() {
		return Autor;
	}

	public void setAutor(Label autor) {
		Autor = autor;
	}

	public Label getDispo() {
		return Dispo;
	}

	public void setDispo(Label dispo) {
		Dispo = dispo;
	}

	public Label getPortada() {
		return Portada;
	}

	public void setPortada(Label portada) {
		Portada = portada;
	}

	public Label getAnioPubli() {
		return AnioPubli;
	}

	public void setAnioPubli(Label anioPubli) {
		AnioPubli = anioPubli;
	}
	
	
}
