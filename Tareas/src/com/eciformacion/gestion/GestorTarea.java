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
		PreparedStatement ps = null;
		Tareas t = null;
		int idai = 0;
		String nombre = null;
		ArrayList<Detalle> d = new ArrayList<>();
		try {
			ps = this.conn.prepareStatement("SELECT * FROM tareas WHERE idai=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				idai = rs.getInt(1);
				nombre = rs.getString(2);
			}
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
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				d.add(new Detalle(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getBoolean(4)));
				//System.out.println("check "+rs.getInt(1)+" "+rs.getInt(2)+" "+rs.getString(3)+" "+rs.getBoolean(4));
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
		t = new Tareas(idai,nombre, d );
		if(t.getNombre() == null) throw new NoSeEncuentraTareaException("No hay la tarea con id "+id);
		return t; 
	}
	public ArrayList<Tareas> buscarTareaNombre(String string) throws NoSeEncuentraTareaException {
		ArrayList<Tareas> tareas = new ArrayList<>();
		PreparedStatement ps= null;
		try {
			ps = this.conn.prepareStatement("SELECT * FROM tareas WHERE nombre=?");
			ps.setString(1, string);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ArrayList<Detalle> detalle = new ArrayList<>();
				//System.out.println(rs.getInt(1)+" //"+rs.getString(2));
				PreparedStatement ps2 = this.conn.prepareStatement("SELECT * FROM detalle WHERE idtarea=?");
				ps2.setInt(1, rs.getInt(1));
				ResultSet rs2 = ps2.executeQuery();
				while(rs2.next()) {
					//System.out.println(rs2.getInt(1)+" "+rs2.getInt(2)+" "+rs2.getString(3)+" "+rs2.getBoolean(4));
					detalle.add(new Detalle(rs2.getInt(1), rs2.getInt(2), rs2.getString(3), rs2.getBoolean(4)));
				}
				tareas.add(new Tareas(rs.getInt(1), rs.getString(2), detalle )); 
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
		PreparedStatement ps= null;
		int idai;
		try {
			ps =conn.prepareStatement("INSERT INTO tareas values(null,?)");
			ps.setString(1, nombre);
			ps.executeUpdate();
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
		PreparedStatement ps= null;
		try {
			ps =conn.prepareStatement("INSERT INTO detalle values(null,?,?,?)");
			ps.setInt(1, idTarea);
			ps.setString(2, nombre);
			ps.setBoolean(3, realizado);
			ps.executeUpdate();
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
		PreparedStatement ps= null;
		try {
			ps = this.conn.prepareStatement("UPDATE detalle SET realizado=? WHERE idai=?");
			ps.setBoolean(1, true);
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
	public Detalle buscarDetalle(int id) {
		Detalle d = null;
		try {
			PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM detalle WHERE idai=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				d = new Detalle(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getBoolean(4));
			}
		}catch(Exception e) {
				
			}
		return d;
	}
	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
	
}
