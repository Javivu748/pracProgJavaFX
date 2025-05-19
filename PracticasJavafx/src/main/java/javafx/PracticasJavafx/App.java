package javafx.PracticasJavafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
       
    	//metemos todo el contenido en un borderPane para dividir mejor la estructura
    	BorderPane panelPrincipal =new BorderPane();
    	
    	//Metemos los GridPane creados en las distintas clases
    	FormLibro formLibro =new FormLibro();
    	Modificar modPane =new Modificar();
    	
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
		//Creamos una barra de menu para meter los menus
		MenuBar barraMenu = new MenuBar(menu1,menu2,menu3);
		//Creamos un tabPane para meter los tabs
		TabPane tabPrincipal =new TabPane();
		//Creamos los tabs para cada seccion
		Tab tNuevoLibro =new Tab("Nuevo Libro");
		Tab tModificar =new Tab("Modificar/Borrar");
		Tab tListado =new Tab("Listado");
		
		tNuevoLibro.setClosable(false);
		tModificar.setClosable(false);
		tListado.setClosable(false);
		
		tNuevoLibro.setContent(formLibro);
		tModificar.setContent(modPane);
		
		tabPrincipal.getTabs().addAll(tNuevoLibro,tModificar,tListado);
		
		
		panelPrincipal.setTop(barraMenu);
		panelPrincipal.setCenter(tabPrincipal);
    
    
		
    	
    	
        var scene = new Scene(panelPrincipal, 640, 480);
        stage.setScene(scene);
        stage.show();
        
        itNuevo.setOnAction(e->{
        	tabPrincipal.getSelectionModel().select(0);
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
		
		
        
    }

    public static void main(String[] args) {
        launch();
    }

}