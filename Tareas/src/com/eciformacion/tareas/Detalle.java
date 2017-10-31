package com.eciformacion.tareas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Detalle {
	private int idAi;
	private int idTarea;
	private String nombre;
	private boolean realizado;
	
	public Detalle(int idAi, int idTarea, String nombre, boolean realizado) {
		super();
		this.idAi = idAi;
		this.idTarea = idTarea;
		this.nombre = nombre;
		this.realizado = realizado;
	}



	
	



	@Override
	public String toString() {
		return "Detalle [idAi=" + idAi + ", idTarea=" + idTarea + ", nombre=" + nombre + ", realizado=" + realizado
				+ "]";
	}


	public int getIdAi() {
		return idAi;
	}
	public void setIdAi(int idAi) {
		this.idAi = idAi;
	}
	public int getIdTarea() {
		return idTarea;
	}
	public void setIdTarea(int idTarea) {
		this.idTarea = idTarea;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public boolean isRealizado() {
		return realizado;
	}
	public void setRealizado(boolean realizado) {
		this.realizado = realizado;
	}
}
