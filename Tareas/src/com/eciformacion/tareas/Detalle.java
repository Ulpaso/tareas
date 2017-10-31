package com.eciformacion.tareas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Detalle {
	private int idAi;
	private int idTarea;
	private String nombre;
	private boolean realizado;
	private Connection conn;
	
	public Detalle(int idAi, Connection conn) {
		super();
		this.idAi = idAi;
		//this.idTarea = idTarea;
		//this.nombre = nombre;
		//this.rechazado = rechazado;
		this.conn = conn;
		try {
			PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM detalle WHERE idai=?");
			ps.setInt(1, idAi);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				this.idTarea = rs.getInt(2);
				this.nombre = rs.getString(3);
				this.realizado = rs.getBoolean(4);
			}
		}catch(Exception e) {
				
			}
	}
	
	
	public Detalle( int idTarea, String nombre, boolean realizado, Connection conn) {
		super();
		//this.idAi = idAi;
		this.idTarea = idTarea;
		this.nombre = nombre;
		this.realizado = realizado;
		this.conn = conn;
		PreparedStatement ps= null;
		try {
			ps =conn.prepareStatement("INSERT INTO detalle values(null,?,?,?)");
			ps.setInt(1, this.idTarea);
			ps.setString(2, this.nombre);
			ps.setBoolean(3, this.realizado);
			ps.executeUpdate();
			ps = this.conn.prepareStatement("SELECT idai FROM detalle WHERE idtarea=? AND nombre=? AND realizado=? ");
			ps.setInt(1, this.idTarea);
			ps.setString(2, this.nombre);
			ps.setBoolean(3, this.realizado);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) this.idAi = rs.getInt(1); 
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
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
		PreparedStatement ps= null;
		try {
			ps = this.conn.prepareStatement("UPDATE detalle SET realizado=? WHERE idai=?");
			ps.setBoolean(1, realizado);;
			ps.setInt(2, this.idAi);
			ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
			}catch (Exception e) {
				
			}
		}
		this.realizado = realizado;
	}
}
