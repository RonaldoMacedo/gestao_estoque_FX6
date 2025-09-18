package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.BrandDao;
import model.entities.Brand;

public class BrandDaoJDBC implements BrandDao {
	
	private Connection conn;
	
	public BrandDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Brand obj) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void update(Brand obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Brand> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("select * from marca");
			rs = ps.executeQuery();
			List<Brand> list = new ArrayList<>();
			Map<Integer, Brand> map = new HashMap<>();
			
			while(rs.next()) {
				Brand obj = map.get(rs.getInt("id_marca"));
				
				if(obj == null) {
					obj = instantiateBrand(rs);
					map.put(rs.getInt("id_marca"), obj);
				}
				
				obj = instantiateBrand(rs);
				list.add(obj);
			}
			return list;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

	private Brand instantiateBrand(ResultSet rs) throws SQLException {
		Brand obj = new Brand();
		obj.setIdMarca(Integer.valueOf(rs.getString("id_marca")));
		obj.setNomeFantasia(rs.getString("nome_fantasia"));
		return obj;
	}
	

}
