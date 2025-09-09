package model.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.entities.Product;

public class ProductService {

	public List<Product> findAll() {
		//Mock dos dados
		List<Product> list = new ArrayList<>();
		list.add(new Product(1, "Luva de latex pp", LocalDateTime.now(), "Coleta", "Ativo", 48));
		list.add(new Product(2, "Agulha 25x8 a vacuo", LocalDateTime.now(), "Coleta", "Ativo", 4700));
		list.add(new Product(3, "Ferritina", LocalDateTime.now(), "Tecnica", "Ativo", 2));
		return list;
	}

}
