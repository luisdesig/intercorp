package com.intercorp.prueba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intercorp.prueba.dao.IClienteDao;
import com.intercorp.prueba.entity.Cliente;

@Service
public class ClienteService implements IClienteService {
	
	@Autowired
	private IClienteDao clienteDao;

	public List<Cliente> getClientes() {
		System.out.println("Welcome");
		return null;
		//return (List<Cliente>) clienteDao.findAll();
	}
}