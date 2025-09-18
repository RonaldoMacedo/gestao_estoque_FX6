package model.services;

import java.util.List;

import model.dao.BrandDao;
import model.dao.DaoFactory;
import model.entities.Brand;

public class BrandService {
	
	private BrandDao dao = DaoFactory.createBrandDao();
	
	public List<Brand> findAll(){
		return dao.findAll();
	}

}
