package coba;

public class Ferme extends Batiment implements Production
{
	private static int nombreFerme = 0;
	private double tauxProductionNourriture = MiseAJour.production_nourriture_ferme;
	
	public Ferme (int x, int y)
	{
		super (x,y);
		tauxProductionNourriture = MiseAJour.production_nourriture_ferme;
		nombreFerme++;
	}
	
	public static int getNombreFerme()
	{
		return nombreFerme;
	}
	
	public String getType() 
	{
		return "ferme";
	}

	public double getTauxProduction() 
	{
		return tauxProductionNourriture;
	}

	public void setTauxProductionPollue() 
	{
		tauxProductionNourriture -= (tauxProductionNourriture*20)/100;
	}

}
