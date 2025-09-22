package model.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.entities.Supplier;

public class SupplierService {

	public List<Supplier> findAll() {
		List<Supplier> list = new ArrayList<>();
		list.add(new Supplier(1, "Aimara centro e comercio ltda", "Aimara", "405.269.528/0001-30", LocalDateTime.now(), "Ativo"));
		return list;
	}

}
