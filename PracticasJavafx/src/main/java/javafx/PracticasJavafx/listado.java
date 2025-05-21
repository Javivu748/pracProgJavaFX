package javafx.PracticasJavafx;

import java.sql.Connection;
import java.util.ArrayList;

import Practica.sql.UtilsBD;
import Practica.sql.libroDAO;
import Practica.sql.libroDO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

public class listado extends GridPane {
    
    private Connection con = UtilsBD.ConectarBD();
    
    private TableView<libroDO> tablaListado = new TableView<>();
    
    public listado() {
        // Configurar columnas
        configurarColumnas();
        
        // Cargar datos
        cargarDatos();
        
        // Añadir tabla al layout
        this.add(tablaListado, 0, 0);
    }
    
    private void configurarColumnas() {
        // Crear columnas con tipo genérico correcto
        TableColumn<libroDO, String> colTitulo = new TableColumn<>("Título");
        TableColumn<libroDO, String> colGenero = new TableColumn<>("Género");
        TableColumn<libroDO, String> colAutor = new TableColumn<>("Autor");
        TableColumn<libroDO, Integer> colAnio = new TableColumn<>("Año Publicación");
        TableColumn<libroDO, String> colPortada = new TableColumn<>("Portada");
        
        // Configurar cell value factories usando los nombres de las propiedades (no los valores)
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        colAnio.setCellValueFactory(new PropertyValueFactory<>("anioPublicacion"));
        colPortada.setCellValueFactory(new PropertyValueFactory<>("portada"));
        
        // Añadir columnas a la tabla
        tablaListado.getColumns().addAll(colTitulo, colGenero, colAutor, colAnio, colPortada);
    }
    
    private void cargarDatos() {
        ArrayList<libroDO> listaLibros = libroDAO.getLibrosDO(con);
        ObservableList<libroDO> datosObservables = FXCollections.observableArrayList(listaLibros);
        tablaListado.setItems(datosObservables);
    }
}