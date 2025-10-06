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
import model.dao.UnitDao;
import model.entities.Supplier;
import model.entities.Unit;

public class UnitDaoJDBC implements UnitDao {
	
	private Connection conn;
	
	public UnitDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Unit obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Unit obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Unit> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("select * from unidade");
			rs = ps.executeQuery();
			List<Unit> list = new ArrayList<>();
			Map<Integer, Unit> map = new HashMap<>();
			
			while(rs.next()) {
				Unit obj = map.get(rs.getInt("id_unidade"));
				
				if(obj == null) {
					obj = instantiateUnit(rs);
					map.put(rs.getInt("id_unidade"), obj);
				}
				
				obj = instantiateUnit(rs);
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

	private Unit instantiateUnit(ResultSet rs) throws SQLException {
		Unit obj = new Unit();
		obj.setIdUnidade(Integer.valueOf(rs.getString("id_unidade")));
		obj.setNomeUnidade(rs.getString("nome"));
		obj.setCidade(rs.getString("cidade"));
		obj.setSituacao(rs.getString("situacao"));
		return obj;
	}

}
