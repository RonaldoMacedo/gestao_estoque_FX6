package model.dao;

import java.util.List;

import model.entities.Product;

public interface ProductDao {
	
	void insert(Product obj);
	void update(Product obj);
	void deleteByid(Integer id);
	Product findById(Integer id);
	List<Product> findAll();

}
