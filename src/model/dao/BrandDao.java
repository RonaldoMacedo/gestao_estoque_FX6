package model.dao;

import java.util.List;

import model.entities.Brand;

public interface BrandDao {
	
	void insert(Brand obj);
	void update(Brand obj);
	List<Brand> findAll();

}
