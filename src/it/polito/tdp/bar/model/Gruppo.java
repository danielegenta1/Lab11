package it.polito.tdp.bar.model;

import java.time.LocalTime;

public class Gruppo
{
	private int id_gruppo;
	private int numerosita;
	private LocalTime time;
	private int durata;
	private float tolleranza;
	
	private String tavoloAssegnato;
	
	public Gruppo(int id_gruppo, int numerosita, LocalTime time, int durata, float tolleranza) {
		super();
		this.id_gruppo = id_gruppo;
		this.numerosita = numerosita;
		this.time = time;
		this.durata = durata;
		this.tolleranza = tolleranza;
		
		this.setTavoloAssegnato(null);
	}


	public int getId_gruppo() {
		return id_gruppo;
	}


	public void setId_gruppo(int id_gruppo) {
		this.id_gruppo = id_gruppo;
	}


	public int getNumerosita() {
		return numerosita;
	}


	public void setNumerosita(int numerosita) {
		this.numerosita = numerosita;
	}


	public LocalTime getTime() {
		return time;
	}


	public void setTime(LocalTime time) {
		this.time = time;
	}


	public int getDurata() {
		return durata;
	}


	public void setDurata(int durata) {
		this.durata = durata;
	}


	public float getTolleranza() {
		return tolleranza;
	}


	public void setTolleranza(float tolleranza) {
		this.tolleranza = tolleranza;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_gruppo;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gruppo other = (Gruppo) obj;
		if (id_gruppo != other.id_gruppo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Gruppo [id_gruppo=" + id_gruppo + ", numerosita=" + numerosita + ", time=" + time + ", durata=" + durata
				+ ", tolleranza=" + tolleranza + ", tavoloAssegnato=" + tavoloAssegnato + "]";
	}


	public String getTavoloAssegnato() {
		return tavoloAssegnato;
	}


	public void setTavoloAssegnato(String tavoloAssegnato) {
		this.tavoloAssegnato = tavoloAssegnato;
	}
	
	
	
	
	

}
