package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.UnitDao;
import model.entities.Unit;

public class UnitService {
	
	private UnitDao dao = DaoFactory.createUnitDao();
	
	public List<Unit> findAll() {
		return dao.findAll();
	}

}
