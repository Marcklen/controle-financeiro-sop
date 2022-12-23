package br.com.sop.controlefinanceiro.services.impl;

import java.util.List;
import java.util.Optional;

// Interface Generica que utilizar o CRUD nos 3 serviços e controllers
public interface ServiceInterface<T> {

	List<T> getAll();
	Optional<T> getById(Integer id);
	T create(T object);
	void deleteById(Integer id);
	void delete(T object);

}