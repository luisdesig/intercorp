package com.intercorp.prueba.dao;

import org.springframework.data.repository.CrudRepository;

import com.intercorp.prueba.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Long> {

}