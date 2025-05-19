package Practica.sql;

import java.util.Date;

public class libroDO {
	
	private int idLibro;
	private String titulo;
	private String genero;
	private String autor;
	private int anioPublicacion;
	private char disponible;
	private String portada;
	
	/*******************************
	 * GETTERS AND SETTERS
	 *******************************/
	
	
	public int getIdLibro() {
		return idLibro;
	}
	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public int getAnioPublicacion() {
		return anioPublicacion;
	}
	public void setAnioPublicacion(int anioPublicacion) {
		this.anioPublicacion = anioPublicacion;
	}
	public char getDisponible() {
		return disponible;
	}
	public void setDisponible(char disponible) {
		this.disponible = disponible;
	}
	public String getPortada() {
		return portada;
	}
	public void setPortada(String portada) {
		this.portada = portada;
	}
	
	/**
	 * 
	 * @param idLibro
	 * @param titulo
	 * @param genero
	 * @param autor
	 * @param anioPublicacion
	 * @param disponible
	 * @param portada
	 */
	public libroDO(int idLibro, String titulo, String genero, String autor, int anioPublicacion, char disponible,
			String portada) {
		super();
		this.idLibro = idLibro;
		this.titulo = titulo;
		this.genero = genero;
		this.autor = autor;
		this.anioPublicacion = anioPublicacion;
		this.disponible = disponible;
		this.portada = portada;
	}
	
	public libroDO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "libroDO [idLibro=" + idLibro + ", titulo=" + titulo + ", genero=" + genero + ", autor=" + autor
				+ ", anioPublicacion=" + anioPublicacion + ", disponible=" + disponible + ", portada=" + portada + "]";
	}
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
}
