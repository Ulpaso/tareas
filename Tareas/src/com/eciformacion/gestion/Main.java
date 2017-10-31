package com.eciformacion.gestion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.eciformacion.tareas.Detalle;
import com.eciformacion.tareas.Tareas;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Tareas> tarea = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return;
		}
		Connection conn = null;
		try {
		conn=DriverManager.getConnection("jdbc:mysql://localhost/tareas", "root", "");
		/*try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM tareas");
			while(rs.next()) {
				tarea.add(new Tareas(rs.getInt(1), conn));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		tarea.stream().forEach(p -> {
			System.out.println(p.getIDAI()+" => "+p.getNombre());
		});*/
		

		GestorTarea gt = new GestorTarea(conn);
		try {
		System.out.println(gt.buscarTareaId(8).getIDAI()+" "+gt.buscarTareaId(8).getNombre()+" "+gt.buscarTareaId(8).getDetalles().toString());
		
		gt.buscarTareaNombre("cocinar").stream().forEach(p -> {
			System.out.println(p.getIDAI());
		});
		
		gt.leerTarea(8);
		
		gt.completarDetalle(12);
		}catch(Exception e) {
			e.printStackTrace();
		}
		gt.crearTarea("elena");
		//gt.añadirDetalle(13, "DominarElMundoMAS", false);
		//gt.completarDetalle(14);
		//Tareas t = new Tareas(4, conn);
		System.out.println(gt.buscarDetalle(14).toString());
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
