package br.com.sop.controlefinanceiro.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public class ServiceMaster <T, R extends JpaRepository<T, Integer>>  implements ServiceInterface<T> {

    @Autowired
    private R repository;

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<T> getById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public T create(T object) {
        return repository.save(object);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(T object) {
        repository.delete(object);
    }

    public R getRepository() {
        return repository;
    }
}