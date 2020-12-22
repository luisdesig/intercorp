package com.intercorp.prueba.entity;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

import jdk.jfr.Name;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	static private int EstimaVida = 74;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombres;
	private String apellidos;
	private Date fechanac;

	
	@Transient
	private String fechaNacimiento;
	@Transient
	private Date FecEstimMuerte;
	@Transient
	private int edad;
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		Date fecha = null;
		try {
			System.out.println(fechaNacimiento);
			fecha = new SimpleDateFormat("dd/MM/yyyy").parse(fechaNacimiento);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.fechanac = fecha;
		this.fechaNacimiento = fechaNacimiento;
	}

	@JsonProperty("fechaNacimiento")
	public Date getFechanac() {
		return fechanac;
	}

	public void setFechanac() {		
		System.out.println("--------");
	}

	public void setFecEstimMuerte(Date fecEstimMuerte) {
		FecEstimMuerte = sumarFecha(fechanac);
	}

	public int getEdad() {
		return calculaEdad(fechanac);
	}
	
	public Date getFecEstimMuerte() {
		return sumarFecha(fechanac);
	}
	
	public static Date sumarFecha(Date fec) {
		Calendar c = Calendar.getInstance();
		c.setTime(fec);
		c.add(Calendar.YEAR, EstimaVida);

		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.FRENCH);
		String fec1 = df.format(c.getTime());
		Date fecha = null;
		try {
			fecha = new SimpleDateFormat("dd/MM/yyyy").parse(fec1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fecha;
	}
	
    public static int calculaEdad(Date fecha) {
    	Calendar fechaNac = Calendar.getInstance();
    	fechaNac.setTime(fecha);    	
        Calendar fechaActual = Calendar.getInstance();
 
        int anios = fechaActual.get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR);
        int meses = fechaActual.get(Calendar.MONTH) - fechaNac.get(Calendar.MONTH);
        int dias = fechaActual.get(Calendar.DAY_OF_MONTH) - fechaNac.get(Calendar.DAY_OF_MONTH);
        if(meses < 0
           || (meses==0 && dias < 0)) {
            anios--;
        }
        return anios;
    }

}
