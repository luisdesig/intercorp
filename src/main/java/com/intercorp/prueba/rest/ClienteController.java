package com.intercorp.prueba.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.intercorp.prueba.dao.IClienteDao;
import com.intercorp.prueba.entity.Calculos;
import com.intercorp.prueba.entity.Cliente;

@RestController
public class ClienteController {

	@Autowired
	private IClienteDao clienteDao;

	
	@GetMapping (value = "/listclientes")
	public List<Cliente> listclientes() {
		return (List<Cliente>) clienteDao.findAll();
	}
	
	@PostMapping(value = "/creacliente")
	public Cliente creacliente(@RequestBody Cliente cliente) {
		return clienteDao.save(cliente);
	}
	
	@GetMapping(value = "/kpideclientes")
	public Calculos kpideclientes() {
		List<Cliente> clientes = (List<Cliente>) clienteDao.findAll();

		int totalEdad = 0;
		for (Cliente i: clientes) {
			totalEdad += i.getEdad();
		}
		float prom = (((float)totalEdad / (float)clientes.size()));

		float desviacion;
		double varianza = 0.0;
        for (Cliente i: clientes) {
            double rango;
            rango = Math.pow(i.getEdad() - prom, 2f);
            varianza = varianza + rango;
        }
        varianza = varianza / clientes.size();
        desviacion = (float)Math.sqrt(varianza);

		Calculos calculos = new Calculos();

		calculos.setPromedio(prom);
		calculos.setDesviacionPob(desviacion);
		
		return calculos;
	}
}
