package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SupplierDao;
import model.entities.Supplier;

public class SupplierService {
	
	private SupplierDao dao = DaoFactory.createSupplierDao();

	public List<Supplier> findAll() {
		return dao.findAll();
	}
	
	public void saveOrUpdate(Supplier obj) {
		if(obj.getIdFornecedor() == null) {
			dao.insert(obj);
		}else {
			dao.update(obj);
		}
	}

}
