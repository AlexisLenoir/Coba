package coba;

public class Maison extends BatimentVille implements Production
{
	
	private static int  nombreMaison = 0;
	private int nombreHabitant;
	
	
	public Maison (int x, int y)
	{
		super(x,y);
		
		nombreMaison++;
		nombreHabitant = 10;
	}

	
	public String getType() 
	{
		return "maison";
	}

	public static int getNombreMaison()
	{
		return nombreMaison;
	}
	
	public static void enleverMaison()
	{
		if (nombreMaison >= 1 ) nombreMaison--;
	}
	
	public double getTauxProduction() 
	{
		return nombreHabitant;
	}
	

	public void setTauxProductionPollue() 
	{
		nombreHabitant -= (nombreHabitant*20)/100;
	}
	
	

}
