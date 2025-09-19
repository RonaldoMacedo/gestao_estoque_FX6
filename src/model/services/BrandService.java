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
	
	public void saveOrUpdate(Brand obj) {
		if(obj.getIdMarca() == null) {
			dao.insert(obj);
		}else {
			dao.update(obj);
		}
	}

}
