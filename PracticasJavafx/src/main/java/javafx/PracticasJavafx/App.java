package javafx.PracticasJavafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {
	
	private TabPane tabPrincipal =new TabPane();
	
	public TabPane getTabPrincipal() {
		return tabPrincipal;
	}


	public void setTabPrincipal(TabPane tabPrincipal) {
		this.tabPrincipal = tabPrincipal;
	}


	public Tab tNuevoLibro;
	public Tab tModificar;
	public Tab tListado;
	

    @Override
    public void start(Stage stage) {
       
    	
    	
    	//metemos todo el contenido en un borderPane para dividir mejor la estructura
    	BorderPane panelPrincipal =new BorderPane();
    	
    	//Metemos los GridPane creados en las distintas clases
    	FormLibro formLibro =new FormLibro();
    	Modificar modPane =new Modificar();
    	listado listadoPane =new listado();
    	
        Menu menu1 = new Menu("Archivo");
		//creamos items para meterlo luego en cada menu
		MenuItem itNuevo =new MenuItem("Nuevo");
		MenuItem itExportar =new MenuItem("Exportar");
		MenuItem itSalir =new MenuItem("Salir");
		
		
		
		
		
		menu1.getItems().addAll(itNuevo,itExportar,itSalir);
		
		Menu menu2 = new Menu("Edicion");
		
		MenuItem itBuscar =new MenuItem("Buscar Libro");
		MenuItem itListar =new MenuItem("Listado Libros");
		
		menu2.getItems().addAll(itBuscar,itListar);
		
		Menu menu3 = new Menu("Ayuda");
		
		MenuItem itSobre =new MenuItem("Sobre la app");
		
		menu3.getItems().add(itSobre);
		
		//Creamos una barra de menu para meter los menus
		MenuBar barraMenu = new MenuBar(menu1,menu2,menu3);
		//Creamos un tabPane para meter los tabs
		//Creamos los tabs para cada seccion
		tNuevoLibro =new Tab("Nuevo Libro");
		tModificar =new Tab("Modificar/Borrar");
		tListado =new Tab("Listado");
		
		tNuevoLibro.setClosable(false);
		tModificar.setClosable(false);
		tListado.setClosable(false);
		
		tNuevoLibro.setContent(formLibro);
		tModificar.setContent(modPane);
		tListado.setContent(listadoPane);
		
		tabPrincipal.getTabs().addAll(tNuevoLibro,tModificar,tListado);
		
		
		panelPrincipal.setTop(barraMenu);
		panelPrincipal.setCenter(tabPrincipal);
    
		/****************
		 * ESCENA
		 ****************/
    	
        var scene = new Scene(panelPrincipal, 640, 480);
        stage.setScene(scene);
        stage.show();
        
        /*************************
         * BOTONES/ACCIONES
         *************************/
        
        itSobre.setOnAction(e->{
        	Alert alertaAyuda =new Alert(AlertType.INFORMATION);
        	alertaAyuda.setTitle("INFORMACION");
        	alertaAyuda.setHeaderText("Sobre la aplicación...");
        	alertaAyuda.setContentText("La aplicacion consta de varias secciones");
        	alertaAyuda.showAndWait();
        });
        
        
        itNuevo.setOnAction(e->{
        	cambiarTab(0);
        	//Limpiamos el formulario anterior para que quite todo los que hay en el gridPane
        	formLibro.getChildren().clear();
        	//Creamos un nuevo formulario y lo metemos en el tab de Nuevo Libro
        	FormLibro formLibro2 =new FormLibro();
        	tNuevoLibro.setContent(formLibro2);
		});
		
		itSalir.setOnAction(e ->{
			stage.close();
		});
		
		itBuscar.setOnAction(e->{
			tabPrincipal.getSelectionModel().select(1);
			modPane.getChildren().clear();
			Modificar modPane2 =new Modificar();
			tModificar.setContent(modPane2);
		});
		
		modPane.getBtnEditar().setOnAction(e->{
			cambiarTab(0);
			formLibro.getTxfTitulo().setText(modPane.getTitulo().getText());
			
			
			if(modPane.getGenero().getText().equals("Novela")) {
				formLibro.getcBoxGenero().getSelectionModel().select(0);
			}
			if(modPane.getGenero().getText().equals("Ciencia Ficción")) {
				formLibro.getcBoxGenero().getSelectionModel().select(1);
			}
			if(modPane.getGenero().getText().equals("Historia")) {
				formLibro.getcBoxGenero().getSelectionModel().select(2);
			}
			if(modPane.getGenero().getText().equals("Infantil")) {
				formLibro.getcBoxGenero().getSelectionModel().select(3);
			}
			
			formLibro.getTxfAutor().setText(modPane.getAutor().getText());
			
			formLibro.getSldanioPubli().setValue(Integer.parseInt(modPane.getAnioPubli().getText()));
			
			if(modPane.getDispo().getText().equals("S")) {
				formLibro.getChbDispo().setSelected(true);
			}
			if(modPane.getDispo().getText().equals("N")) {
				formLibro.getChbDispo().setSelected(false);
			}
			
			formLibro.getRutaPortadaField().setText(modPane.getPortada().getText());
			
			formLibro.getBtnActualizar().setDisable(false);
			
			
		});
        
    }


	public static void main(String[] args) {
        launch();
    }

	public void cambiarTab(int tabIndex) {
        tabPrincipal.getSelectionModel().select(tabIndex);
    }
	
}