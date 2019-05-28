package it.polito.tdp.bar.model;

public class Model 
{
	private Simulatore sim;
	
	public Model()
	{
		sim = new Simulatore();
	}

	public void simula() 
	{
		sim.init();
		sim.run();
	}

	public int getSoddisfatti() 
	{
		
		return sim.getSoddisfatti();
	}

	public int getInsoddisfatti() {
		return sim.getInsoddisfatti();
	}
	
	

}
