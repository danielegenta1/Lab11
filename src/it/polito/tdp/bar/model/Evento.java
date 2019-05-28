package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Evento implements Comparable<Evento>
{
	public enum TIPO
	{
		ARRIVO_GRUPPO_CLIENTI,
		PARTENZA_GRUPPO_CLIENTI,
	}
	
	private LocalTime time;
	private TIPO tipo;
	private Gruppo gruppo;
	
	
	public Evento(LocalTime time, TIPO tipo, Gruppo gruppo) {
		super();
		this.time = time;
		this.tipo = tipo;
		this.gruppo = gruppo;
	}


	public LocalTime getTime() {
		return time;
	}


	public void setTime(LocalTime time) {
		this.time = time;
	}


	public TIPO getTipo() {
		return tipo;
	}


	public void setTipo(TIPO tipo) {
		this.tipo = tipo;
	}


	public Gruppo getGruppo() {
		return gruppo;
	}


	public void setGruppo(Gruppo gruppo) {
		this.gruppo = gruppo;
	}


	@Override
	public int compareTo(Evento o) 
	{
		return this.time.compareTo(o.time);
	}
	
	
	
	
	
	
	//private String tavoloAssegnato; //TAVOLO_DIMENSIONE o bancone o NA
	
	
	
	//variabili interne
	//sistemazione
	
	
	
	
	
	

}
