package model.dao;

import db.DB;
import model.dao.impl.BrandDaoJDBC;
import model.dao.impl.ProductDaoJDBC;

public class DaoFactory {
	
	public static ProductDao createProductDao() {
		return new ProductDaoJDBC(DB.getConnection());
	}
	
	public static BrandDao createBrandDao() {
		return new BrandDaoJDBC(DB.getConnection());
	}

}
