package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import it.polito.tdp.bar.model.Evento.TIPO;

//TODO Punto 4,5
public class Simulatore 
{
	//stato del sistema
	List<Gruppo>gruppi; 
	
	//parametri della simulazione
	private int NUMERO_GRUPPI = 20;  //numero di gruppi arrivanti
	private int TAVOLI_DIECI = 2;
	private int TAVOLI_OTTO = 4;
	private int TAVOLI_SEI = 4;
	private int TAVOLI_QUATTRO = 5;
	private Duration T_ARRIVAL = Duration.ofMinutes(5); //intervallo fra arrivo clienti
	
	// istante temporale di inizio simulazione
	private LocalTime T_inizio = LocalTime.of(8, 0);
	
	//valori in output
	private Statistiche stats;
	
	//coda
	PriorityQueue<Evento> queue = new PriorityQueue<Evento>();
	
	public Simulatore()
	{
		this.gruppi = new LinkedList<Gruppo>();
	}
	
	public void init()
	{
		//azzero
		gruppi.clear();
		stats = new Statistiche(0,0,0);
		stats.setNumero_totale_clienti(NUMERO_GRUPPI);
		LocalTime oraArrivo = T_inizio;
		
		//creare i gruppi
		for (int i = 0; i < NUMERO_GRUPPI; i++)
		{
			Gruppo g = new Gruppo(i+1, generaNumerositaGruppo(), oraArrivo, generaDurata(), generaTolleranza());
			gruppi.add(g);
			System.out.println(g);
			oraArrivo = oraArrivo.plus(T_ARRIVAL);
		}
		
		//creare gli eventi iniziali
		queue.clear();
		for (Gruppo g : gruppi)
		{
			queue.add(new Evento(g.getTime(), TIPO.ARRIVO_GRUPPO_CLIENTI, g));
		}
	}
	
	private float generaTolleranza() 
	{
		return (float) (Math.random());
	}

	private int generaDurata() 
	{
		int durata = (int)(Math.random()*60+60);
		return durata;
	}

	private int generaNumerositaGruppo() 
	{	
		int numPersone = (int)(Math.random()*9+1);
		return numPersone;
	}

	public void run()
	{
		while (!queue.isEmpty()) // non ho limiti di tempo
		{
			Evento ev = queue.poll();
			Gruppo g = ev.getGruppo();
			
			switch (ev.getTipo())
			{
				case ARRIVO_GRUPPO_CLIENTI: 
					// selezionare un tavolo in base a numerosita se disponibile altrimenti bancone
					// aggiornare i tavoli
					// aggiungere evento partenza
					boolean soddisfatto = true;
					LocalTime newTime = ev.getTime().plus(Duration.ofMinutes(g.getDurata()));
					if (g.getNumerosita() <= 4 && TAVOLI_QUATTRO > 0)
					{
						//assegno tavolo da 4 o bancone
						// controllo di soddisfare punto 4 altrimenti (se sono tolleranti), li mando al bancone
						if (g.getNumerosita() >= 2 || g.getTolleranza() < 0.5)
							assegna("TAVOLI_QUATTRO", g);
						// bancone
						else
							assegna("BANCONE", g);
					}
					else if (g.getNumerosita() <= 6 && TAVOLI_SEI > 0)
					{
						//assegno tavolo da 6 o bancone
						// controllo di soddisfare punto 4 altrimenti (se sono tolleranti), li mando al bancone
						if (g.getNumerosita() >= 3 || g.getTolleranza() < 0.5)
							assegna("TAVOLI_SEI", g);
						// bancone
						else
							assegna("BANCONE", g);
					}
					else if (g.getNumerosita() <= 8 && TAVOLI_OTTO > 0)
					{
						//assegno tavolo da 8 o bancone 
						// controllo di soddisfare punto 4 altrimenti (se sono tolleranti), li mando al bancone
						if (g.getNumerosita() >= 4 || g.getTolleranza() < 0.5)
							assegna("TAVOLI_OTTO", g);
						// bancone
						else
							assegna("BANCONE", g);
					}
					else if (g.getNumerosita() <= 10 && TAVOLI_DIECI > 0)
					{
						//assegno tavolo da 10 o bancone
						// controllo di soddisfare punto 4 altrimenti (se sono tolleranti), li mando al bancone
						if (g.getNumerosita() >= 5 || g.getTolleranza() < 0.5)
							assegna("TAVOLI_DIECI", g);
						// bancone
						else
							assegna("BANCONE", g);
					}
					// caso nessun tavolo disponibile idoneo
					else
					{
						//nessun tavolo disponibile, bancone o casa (tolleranza)
						if (g.getTolleranza() > 0.5)
							assegna("BANCONE", g);
						// else cliente va via (segno come insoddisfatto e non assegno tavolo)
						else
							soddisfatto = false;
					}
					
					//aggiorna stats
					if (soddisfatto)
					{
						queue.add(new Evento(newTime, TIPO.PARTENZA_GRUPPO_CLIENTI, g));
						stats.setNumero_clienti_soddisfatti(stats.getNumero_clienti_soddisfatti() + 1);
						
						//diag
						System.out.println("Tavolo assegnato: " + g);
					}
					else
					{
						stats.setNumero_clienti_insoddisfatti(stats.getNumero_clienti_insoddisfatti() + 1);
						
						//diag 
						System.out.println("CLIENTE INSODDISFATTO: " + g);
					}
					break;
				case PARTENZA_GRUPPO_CLIENTI:
					// liberare tavolo
					if (g.getTavoloAssegnato().compareTo("TAVOLI_QUATTRO") == 0)
						TAVOLI_QUATTRO++;
					else if (g.getTavoloAssegnato().compareTo("TAVOLI_SEI") == 0)
						TAVOLI_SEI++;	
					else if (g.getTavoloAssegnato().compareTo("TAVOLI_OTTO") == 0)
						TAVOLI_OTTO++;
					else if (g.getTavoloAssegnato().compareTo("TAVOLI_DIECI") == 0)
						TAVOLI_DIECI++;
					else if (g.getTavoloAssegnato().compareTo("BANCONE") == 0)
					{
						// niente, capienza bancone illimitata
					}
					
					//diag
					System.out.println("TAVOLO RILASCIATO alle:" + ev.getTime() + "-" + g);
					
					
					// aggiorna statistiche
					break;
			}
		}
	}
	
	public void assegna(String daAssegnare, Gruppo g)
	{
		// 
		g.setTavoloAssegnato(daAssegnare);
		
	}

	public int getSoddisfatti()
	{
		// TODO Auto-generated method stub
		return stats.getNumero_clienti_soddisfatti();
	}

	public int getInsoddisfatti() {
		// TODO Auto-generated method stub
		return stats.getNumero_clienti_insoddisfatti();
	}
	
	

}
