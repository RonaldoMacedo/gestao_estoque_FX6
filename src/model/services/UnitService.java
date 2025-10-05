package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Unit;

public class UnitService {
	
	public List<Unit> findAll() {
		List<Unit> list = new ArrayList<>();
		list.add(new Unit(1, "Unidade 23 de maio", "Jundiai", "Ativo"));
		return list;
	}

}
