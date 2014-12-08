package br.udc.edu.sistemas.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import br.udc.edu.sistemas.entity.Marca;
import br.udc.edu.sistemas.entity.Modelo;
import br.udc.edu.sistemas.util.Database;

public class DaoModelo {
	
private Database database;
	
	public DaoModelo() {//constructor
		this.database = new Database();
	}

	public void save(Modelo obj) throws SQLException {
		if (obj != null) {
			Modelo oldObj = this.findByPrimaryKey(obj);
			String sql;
			if (oldObj == null) {
				sql = "insert into Modelo (id,descricao,idModelo) " +
					  "values(" + obj.getId() + "," + obj.getDescricao() + "," + obj.getMarca().getId() + ")";			
			} else {
				sql = "update Modelo set " +
					  "descricao = " + obj.getDescricao() + " " +
					  "idmarca = " + obj.getMarca().getId() + " " +
					  "where id = " + obj.getId();
						
			}
			System.out.println(sql);
			Connection con = null;
			try {
				con = this.database.getConnection();				
				Statement stmt = con.createStatement();			
				stmt.executeUpdate(sql);
				con.commit();
				stmt.close();
			} catch (SQLException e) {
				try {
					con.rollback();
				} catch (Exception e2) {
				}
				throw e;
			}
		}
	}

	public void delete(Modelo obj) throws SQLException {
		if (obj != null) {
			String sql = "delete from Modelo " +
						 "where id = " + obj.getId();
			System.out.println(sql);			
			Connection con = null;
			try {
				con = this.database.getConnection();				
				Statement stmt = con.createStatement();			
				stmt.executeUpdate(sql);
				con.commit();
				stmt.close();
			} catch (SQLException e) {
				try {
					con.rollback();
				} catch (Exception e2) {
				}
				throw e;
			}
		}
	}
	
	public Modelo[] find(Modelo obj) throws SQLException {
		String sql = "select id,descricao from Modelo";		
		if (obj != null) {
			boolean bWhere = false;
			if (obj.getId() != null) {			
				sql += " where id = " + obj.getId();
				bWhere = true;
			}
			if ((obj.getDescricao() != null) &&
				(!obj.getDescricao().trim().equals(""))) {
				if (bWhere) {
					sql += " and";
				} else {
					sql += " where";
					bWhere = true;
				}
				sql += " descricao like '%" + obj.getDescricao().trim() + "%'";
			}
			
			if ((obj.getMarca() != null) &&
					(obj.getMarca().getId() != null)) {
					if (bWhere) {
						sql += " and";
					} else {
						sql += " where";
						bWhere = true;
					}
					sql += " idmarca = " + obj.getMarca().getId();
				}
		}
		System.out.println(sql);
		Connection con = null;
		con = this.database.getConnection();				
		Statement stmt = con.createStatement();			
		ResultSet rst = stmt.executeQuery(sql);
		
		Vector <Modelo> lista = new Vector <Modelo> ();
		while (rst.next()) {
			Modelo modelo = new Modelo();
			modelo.setId(rst.getInt("id"));
			modelo.setDescricao(rst.getString("descricao"));
			
			modelo.setMarca(new Marca());
			modelo.getMarca().setId(rst.getInt("idmarca"));
			lista.add(modelo);
		}
		rst.close();
		
		return lista.toArray(new Modelo[lista.size()]);
	}
	

	public Modelo findByPrimaryKey(Modelo obj) throws SQLException {
		if ((obj != null) && (obj.getId() != null)) {
			Modelo Modelo = new Modelo(); 
			Modelo.setId(obj.getId());
			Modelo lista[] = this.find(Modelo);
			if (lista.length > 0) {
				return lista[0];
			}
		}
		return null;
	}

}
