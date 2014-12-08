package br.udc.edu.sistemas.dao;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Vector;
import br.udc.edu.sistemas.entity.Marca;
import br.udc.edu.sistemas.util.Database;

public class DaoMarca {
	
private Database database;
	
	public DaoMarca() {//constructor
		this.database = new Database();
	}

	public void save(Marca obj) throws SQLException {
		if (obj != null) {
			Marca oldObj = this.findByPrimaryKey(obj);
			String sql;
			if (oldObj == null) {
				sql = "insert into marca (id,descricao) " +
					  "values(" + obj.getId() + ",'" + obj.getDescricao() + "')";			
			} else {
				sql = "update marca set " +
					  "descricao = '" + obj.getDescricao() + "' " +
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

	public void delete(Marca obj) throws SQLException {
		if (obj != null) {
			String sql = "delete from marca " +
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
	
	public Marca[] find(Marca obj) throws SQLException {
		String sql = "select id,descricao from marca";		
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
		}
		System.out.println(sql);
		Connection con = null;
		con = this.database.getConnection();				
		Statement stmt = con.createStatement();			
		ResultSet rst = stmt.executeQuery(sql);
		
		Vector <Marca> lista = new Vector <Marca> ();
		while (rst.next()) {
			Marca marca = new Marca();
			marca.setId(rst.getInt("id"));
			marca.setDescricao(rst.getString("descricao"));
			lista.add(marca);
		}
		rst.close();
		
		return lista.toArray(new Marca[lista.size()]);
	}
	

	public Marca findByPrimaryKey(Marca obj) throws SQLException {
		if ((obj != null) && (obj.getId() != null)) {
			Marca marca = new Marca(); 
			marca.setId(obj.getId());
			Marca lista[] = this.find(marca);
			if (lista.length > 0) {
				return lista[0];
			}
		}
		return null;
	}

}
