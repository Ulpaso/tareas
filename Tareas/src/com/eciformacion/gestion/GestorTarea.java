package com.eciformacion.gestion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.eciformacion.tareas.Detalle;
import com.eciformacion.tareas.Tareas;
import com.eciformacion.tareas.excepciones.NoSeEncuentraTareaException;

public class GestorTarea {
	private Connection conn;

	public GestorTarea(Connection conn) {
		super();
		this.conn = conn;
	}
	public Tareas buscarTareaId(int id) throws NoSeEncuentraTareaException {
		Tareas tarea = new Tareas(id, this.conn);
		if(tarea.getNombre() == null) throw new NoSeEncuentraTareaException("No hay la tarea con id "+id);
		return tarea; 
	}
	public ArrayList<Tareas> buscarTareaNombre(String string) throws NoSeEncuentraTareaException {
		ArrayList<Tareas> tareas = new ArrayList<>();
		PreparedStatement ps= null;
		try {
			ps = this.conn.prepareStatement("SELECT * FROM tareas WHERE nombre=?");
			ps.setString(1, string);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				tareas.add(new Tareas(rs.getInt(1), conn)); 
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		if(tareas.isEmpty()) throw new NoSeEncuentraTareaException("No hay la tareas "+string);
		return tareas;
	}
	public void crearTarea(String nombre) {
		new Tareas(nombre, this.conn);
	}
	public void actualizarTarea(int id, String update) {
		PreparedStatement ps= null;
		try {
			ps = this.conn.prepareStatement("UPDATE tareas SET nombre=? WHERE idai=?");
			ps.setString(1, update);
			ps.setInt(2, id);
			ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
			}catch (Exception e) {
				
			}
		}
	}
	public void añadirDetalle(int idTarea, String nombre, boolean realizado) {
		try {
			Tareas t = this.buscarTareaId(idTarea);
			t.añadirDetalle(nombre, realizado);
		} catch (NoSeEncuentraTareaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void leerTarea(int id) {
		 try {
			Tareas t = this.buscarTareaId(id);
			System.out.println("Id: "+t.getIDAI()+" Nombre: "+t.getNombre());
			//System.out.println(t.getDetalles().toString());
			t.getDetalles().stream().forEach(p -> {
				System.out.println(p.toString());
			});
		} catch (NoSeEncuentraTareaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
	public void completarDetalle(int id) {
		Detalle d = buscarDetalle(id);
		d.setRealizado(true);
	}
	public Detalle buscarDetalle(int id) {
		Detalle d = new Detalle(id, conn);
		return d;
	}
	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
	
}
