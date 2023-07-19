package com.universidad.service.modelos;

public class Presencial {

	private String nombre;
	private int creditos;
	private int universidadId;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCreditos() {
		return creditos;
	}

	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}

	public int getUniversidadId() {
		return universidadId;
	}

	public void setUniversidadId(int universidadId) {
		this.universidadId = universidadId;
	}

	public Presencial() {
		super();
	}
}
