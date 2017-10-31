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
	private Connection conn;
	
	//constructor para añadir en la base de datos
	public Tareas(String nombre,Connection conn) {
		this.conn = conn;
		this.nombre = nombre;
		this.detalles = new ArrayList<>();
		PreparedStatement ps= null;
		try {
			ps =conn.prepareStatement("INSERT INTO tareas values(null,?)");
			ps.setString(1, this.nombre);
			ps.executeUpdate();
			ps = this.conn.prepareStatement("SELECT * FROM tareas WHERE nombre=?");
			ps.setString(1, this.nombre);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) this.IDAI = rs.getInt(1); 
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			ps = this.conn.prepareStatement("SELECT * FROM detalle WHERE idtarea=?");
			ps.setInt(1, this.IDAI);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				this.detalles.add(new Detalle(rs.getInt(1), this.conn));
			}
			rs.close();
		}catch(Exception e) {
				
			}finally {
				try {
					ps.close();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		
	}
	
	//constructor para buscar en la base de datos
	public Tareas(int IDAI, Connection conn) {
		this.IDAI = IDAI;
		this.conn = conn;
		this.detalles = new ArrayList<>();
		PreparedStatement ps = null;
		try {
			ps = this.conn.prepareStatement("SELECT * FROM tareas WHERE idai=?");
			ps.setInt(1, IDAI);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) this.nombre = rs.getString(2);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try{
			ps.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		try {
			ps = this.conn.prepareStatement("SELECT * FROM detalle WHERE idtarea=?");
			ps.setInt(1, this.IDAI);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				this.detalles.add(new Detalle(rs.getInt(1), this.conn));
			}
			rs.close();
		}catch(Exception e) {
				
			}finally {
				try {
					ps.close();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
	}
	
	public void añadirDetalle(String nombre, boolean realizado) {
		this.detalles.add(new Detalle(this.IDAI, nombre, realizado, conn));
	}
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
	public Connection getConn() {
		return conn;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	public ArrayList<Detalle> getDetalles() {
		return detalles;
	}
	public void setDetalles(ArrayList<Detalle> detalles) {
		this.detalles = detalles;
	}
	

}
