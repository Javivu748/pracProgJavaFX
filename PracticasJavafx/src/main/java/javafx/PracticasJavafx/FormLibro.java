package javafx.PracticasJavafx;

import java.io.File;
import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;

import Practica.sql.UtilsBD;
import Practica.sql.libroDAO;
import Practica.sql.libroDO;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FormLibro extends GridPane{
	
	private Connection con =UtilsBD.ConectarBD();
	
	private TextField txfTitulo=new TextField();
	
	private ComboBox cBoxGenero=new ComboBox();
	
	private TextField txfAutor=new TextField();
	
	private Slider sldanioPubli =new Slider();
	
	private CheckBox chbDispo = new CheckBox();
	
	private Button seleccionarBtn = new Button("Seleccionar Portada");
	
	private TextField rutaPortadaField;
	
	private Button btnGuardar =new Button("Guardar Libro");
	
	private Button btnActualizar =new Button("Actualizar");
	
	public FormLibro() {
		
		//Añadimos todo los componentes del formulario
		
		Label lblTitulo =new Label("Titulo:");
		
		txfTitulo.setPromptText("titulo...");
		
		Label lblGenero =new Label("Genero:");
		
		cBoxGenero.getItems().addAll("Novela","Ciencia Ficción","Historia","Infantil");
		
		Label lblAutor =new Label("Autor:");
		
		txfAutor.setPromptText("autor...");
		
		Label lblanioPubli = new Label("Año publicacion:");
		
		sldanioPubli.setShowTickLabels(true);
		sldanioPubli.setMin(1900);
		sldanioPubli.setMax(2023);
		sldanioPubli.setMajorTickUnit(1);
		sldanioPubli.setSnapToTicks(true);
		sldanioPubli.setBlockIncrement(1);
		
		sldanioPubli.setOnMouseReleased(e ->{
			lblanioPubli.setText("Año publicacion: " +(int) sldanioPubli.getValue());
		});
		
		Label lblDisponibilidad = new Label("Disponible:");
		
		
		Label lblPortada = new Label("Formulario de Portada");
        
        rutaPortadaField = new TextField();
        rutaPortadaField.setPromptText("Ruta de la portada...");
        
        
        //Metemos la accion al boton para selecionar la portada
        seleccionarBtn.setOnAction(e-> {
        	seleccionarPortada();
        });
        
        btnActualizar.setDisable(true);
        
        btnActualizar.setOnAction(e->{
        	
        	char dispoSelect;
        	
        	if(chbDispo.isSelected()) {
        		dispoSelect='S';
        	}else {
        		dispoSelect='N';
        	}
        	
        	actualizarLibro(txfTitulo.getText(),(String) cBoxGenero.getValue(), txfAutor.getText(), (int) sldanioPubli.getValue(), dispoSelect, rutaPortadaField.getText());
        });
        
        btnGuardar.setOnAction(e-> {
        	
        	//creamos un char para saber si la disponibilidad esta selecionada poniendo S de si y N de no
        	char dispoSelect;
        	
        	//hacemos una condicion para que si esta selecionado diga que esta disponible o no
        	if(chbDispo.isSelected()) {
        		dispoSelect='S';
        	}else {
        		dispoSelect='N';
        	}
        	//ejecutamos una condicion para que cada vez que un campo no este relleno no se pueda ejecutar y salte una alerta
        	if(txfTitulo.getText().trim().isEmpty()||txfAutor.getText().trim().isEmpty()||cBoxGenero.getValue()==null||rutaPortadaField.getText().trim().isEmpty()) {
        		mostrarAlerta();
        		return;
        	}
        	
        	
        	
        	insertarLibro(txfTitulo.getText(),(String) cBoxGenero.getValue(), txfAutor.getText(), (int) sldanioPubli.getValue(), dispoSelect, rutaPortadaField.getText());
        	
        	
        	
        });
        
        this.add(lblTitulo, 0, 0);
        this.add(txfTitulo, 1, 0);
        this.add(lblGenero, 0, 1);
        this.add(cBoxGenero, 1, 1);
        this.add(lblAutor, 0, 2);
        this.add(txfAutor, 1, 2);
        this.add(lblanioPubli, 0, 3);
        this.add(sldanioPubli, 1, 3);
        this.add(lblDisponibilidad, 0, 4);
        this.add(chbDispo, 1, 4);
        this.add(lblPortada, 0, 5);
        this.add(seleccionarBtn, 2, 5);
        this.add(rutaPortadaField, 1, 5);
        this.add(btnGuardar, 0, 6);
        this.add(btnActualizar, 1, 6);
        
        this.setVgap(10);
        this.setHgap(10);
        this.setPadding(new Insets(20));

		
	}
	private void seleccionarPortada() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar imagen de portada");
        
        // Filtros para tipos de imagen
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
            "Archivos de imagen", "*.png", "*.jpg", "*.jpeg", "*.gif");
        fileChooser.getExtensionFilters().add(extFilter);
        
        // Mostrar diálogo y obtener archivo seleccionado
        File archivo = fileChooser.showOpenDialog(null);
        
        if (archivo != null) {
            // Guardar la ruta completa como texto en el TextField
            rutaPortadaField.setText(archivo.getAbsolutePath());
        }
    }
	
	public void insertarLibro(String titulo,String genero,String autor,int anioPublicacion,char disponibilidad,String portada) {
		
		
		
		libroDO libro =new libroDO(1, titulo, genero, autor, anioPublicacion, disponibilidad, portada);
		
		
		
		libroDAO.insertar(libro, con);
		
		
	}
	
    public void actualizarLibro(String titulo,String genero,String autor,int anioPublicacion,char disponibilidad,String portada) {
		
    	Modificar mod =new Modificar();
    	
		libroDO libro =new libroDO(Integer.parseInt(mod.getIdLibro().getText()), titulo, genero, autor, anioPublicacion, disponibilidad, portada);
		
		
		
		libroDAO.actualizar(libro, con);
		
		
	}
	
	private static void mostrarAlerta() {
		
		//añadimos una alerta nueva parra cuando salte un error
		Alert alertaError =new Alert(AlertType.ERROR);
		alertaError.setTitle("Error de aplicacion");
		alertaError.setHeaderText("No se puede ejecutar el formulario");
		alertaError.setContentText("No has rellenado los campos");
		alertaError.showAndWait();
	}
	
	public TextField getTxfTitulo() {
		return txfTitulo;
	}
	public void setTxfTitulo(TextField txfTitulo) {
		this.txfTitulo = txfTitulo;
	}
	public TextField getTxfAutor() {
		return txfAutor;
	}
	public void setTxfAutor(TextField txfAutor) {
		this.txfAutor = txfAutor;
	}
	public Slider getSldanioPubli() {
		return sldanioPubli;
	}
	public void setSldanioPubli(Slider sldanioPubli) {
		this.sldanioPubli = sldanioPubli;
	}
	public CheckBox getChbDispo() {
		return chbDispo;
	}
	public void setChbDispo(CheckBox chbDispo) {
		this.chbDispo = chbDispo;
	}
	public Button getSeleccionarBtn() {
		return seleccionarBtn;
	}
	public void setSeleccionarBtn(Button seleccionarBtn) {
		this.seleccionarBtn = seleccionarBtn;
	}
	public TextField getRutaPortadaField() {
		return rutaPortadaField;
	}
	public void setRutaPortadaField(TextField rutaPortadaField) {
		this.rutaPortadaField = rutaPortadaField;
	}
	public ComboBox getcBoxGenero() {
		return cBoxGenero;
	}
	public void setcBoxGenero(ComboBox cBoxGenero) {
		this.cBoxGenero = cBoxGenero;
	}
	public Button getBtnGuardar() {
		return btnGuardar;
	}
	public void setBtnGuardar(Button btnGuardar) {
		this.btnGuardar = btnGuardar;
	}
	public Button getBtnActualizar() {
		return btnActualizar;
	}
	public void setBtnActualizar(Button btnActualizar) {
		this.btnActualizar = btnActualizar;
	}

}
