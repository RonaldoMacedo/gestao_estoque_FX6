package model.dao;

import java.util.List;

import model.entities.Supplier;

public interface SupplierDao {
	
	void insert(Supplier obj);
	void update(Supplier obj);
	List<Supplier> findAll();
	void deleteByid(Integer id);

}
