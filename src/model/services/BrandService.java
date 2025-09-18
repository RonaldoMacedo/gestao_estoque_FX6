package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Brand;

public class BrandService {
	
	public List<Brand> findAll(){
		List<Brand> list = new ArrayList<>();
		list.add(new Brand(1, "Descarpack"));
		list.add(new Brand(2, "Medix"));
		return list;
	}

}
