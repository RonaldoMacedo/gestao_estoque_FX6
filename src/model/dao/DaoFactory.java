package model.dao;

import db.DB;
import model.dao.impl.BrandDaoJDBC;
import model.dao.impl.ProductDaoJDBC;
import model.dao.impl.SupplierDaoJDBC;
import model.dao.impl.UnitDaoJDBC;

public class DaoFactory {
	
	public static ProductDao createProductDao() {
		return new ProductDaoJDBC(DB.getConnection());
	}
	
	public static BrandDao createBrandDao() {
		return new BrandDaoJDBC(DB.getConnection());
	}
	
	public static SupplierDao createSupplierDao() {
		return new SupplierDaoJDBC(DB.getConnection());
	}
	
	public static UnitDao createUnitDao() {
		return new UnitDaoJDBC(DB.getConnection());
	}

}
