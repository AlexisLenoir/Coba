package coba;

public class Commerce extends BatimentVille implements Production
{

	private static int nombreCommerce = 0;
	private double tauxProductionOr;
	
	public Commerce(int x, int y) 
	{
		super(x, y);
		nombreCommerce++;
		tauxProductionOr = MiseAJour.production_argent_commerce;
	}
	
	public static int getNombreCommerce() 
	{
		return nombreCommerce;
	}

	public String getType()
	{
		return "commerce";
	}

	public double getTauxProduction() 
	{
		return tauxProductionOr;
	}

	
	public void setTauxProductionPollue() 
	{
		tauxProductionOr -= (tauxProductionOr*20)/100;
	}

}
