package model.dao;

import java.util.List;

import model.entities.Unit;

public interface UnitDao {
	
	void insert(Unit obj);
	void update(Unit obj);
	List<Unit> findAll();

}
