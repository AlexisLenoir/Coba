package coba;

public class BatimentVille extends Batiment
{
	
	private double consommation_energie;
	
	public BatimentVille (int x, int y)
	{
		super(x,y);
	}
	
	public String getType()
	{
		return "batimentville";
	}
	
	public double getTauxProduction() 
	{
		return 0;
	}

}
