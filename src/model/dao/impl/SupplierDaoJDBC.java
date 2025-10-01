package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SupplierDao;
import model.entities.Supplier;

public class SupplierDaoJDBC implements SupplierDao {
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	private Connection conn;
	
	public SupplierDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Supplier obj) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("insert into fornecedor(razao_social, apelido, cnpj, data_cadastro, situacao)\r\n"
					+ "	values(?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, obj.getRazaoSocial());
			ps.setString(2, obj.getApelido());
			ps.setString(3, obj.getCnpj());
			ps.setString(4, obj.getDataCadastro().toString());
			ps.setString(5, obj.getSituacao().toString());
			
			int rowsAffected = ps.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setIdFornecedor(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Unexpected error! No rows affected...");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
		}
		
	}

	@Override
	public void update(Supplier obj) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("update fornecedor "
					+ "set razao_social=?, apelido=?, cnpj=?, situacao=? "
					+ "where id_fornecedor=?");
			ps.setString(1, obj.getRazaoSocial());
			ps.setString(2, obj.getApelido());
			ps.setString(3, obj.getCnpj());
			ps.setString(4, obj.getSituacao());
			ps.setInt(5, obj.getIdFornecedor());
			
			ps.executeUpdate();
				
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
		}
		
	}
	
	private Supplier instantiateSupplier(ResultSet rs) throws SQLException {
		Supplier obj = new Supplier();
		obj.setIdFornecedor(Integer.valueOf(rs.getString("id_fornecedor")));
		obj.setRazaoSocial(rs.getString("razao_social"));
		obj.setApelido(rs.getString("apelido"));
		obj.setCnpj(rs.getString("cnpj"));
		obj.setDataCadastro(LocalDateTime.parse(rs.getString("data_cadastro"),dtf));
		obj.setSituacao(rs.getString("situacao"));
		return obj;
	}

	@Override
	public List<Supplier> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("select * from fornecedor");
			rs = ps.executeQuery();
			List<Supplier> list = new ArrayList<>();
			Map<Integer, Supplier> map = new HashMap<>();
			
			while(rs.next()) {
				Supplier obj = map.get(rs.getInt("id_fornecedor"));
				
				if(obj == null) {
					obj = instantiateSupplier(rs);
					map.put(rs.getInt("id_fornecedor"), obj);
				}
				
				obj = instantiateSupplier(rs);
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

}
