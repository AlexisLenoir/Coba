package coba;

public abstract class Industrie extends Batiment 
{
	
	protected double production_energie;
	protected double production_pollution;
	
	public Industrie(int x, int y, double pe, double pp) 
	{
		super(x, y);
		production_energie = pe;
		production_pollution = pp;
		
	}
	
	public abstract void pollue (Village village);
	
	public String getType() 
	{
		return "industrie";
	}

	public double getTauxProduction() 
	{
		return production_energie;
	}
	
}
