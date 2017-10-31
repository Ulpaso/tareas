package com.eciformacion.tareas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Tareas {
	private int IDAI;
	private String nombre;
	private ArrayList<Detalle> detalles;

	
	public Tareas(int iDAI, String nombre, ArrayList<Detalle> detalles) {
		super();
		IDAI = iDAI;
		this.nombre = nombre;
		this.detalles = detalles;
	}

	//constructor para añadir en la base de datos

	
	//constructor para buscar en la base de datos

	

	public int getIDAI() {
		return IDAI;
	}
	public void setIDAI(int iDAI) {
		IDAI = iDAI;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Detalle> getDetalles() {
		return detalles;
	}
	public void setDetalles(ArrayList<Detalle> detalles) {
		this.detalles = detalles;
	}
	

}
