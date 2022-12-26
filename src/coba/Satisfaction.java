package coba;

public class Satisfaction 
{
	
	private final static double taux_pollution = 3;
	private final static double taux_commerce = 2;
	
	
	public static double getPollution()
	{
		return taux_pollution;
	}
	
	public static double getCommerce()
	{
		return taux_commerce;
	}
	
	
	public static int calcul_satisfaction(Village village, int niveau)
	{
		
		double nombre_habitant = village.get_nombre_habitant();
		if (nombre_habitant == 0) return 0;
		
		if (Commerce.getNombreCommerce()/nombre_habitant < taux_commerce)
		{
			niveau--;
		}
		else if (Commerce.getNombreCommerce()/nombre_habitant < taux_commerce)
		{
			niveau++;
		}
		
		
		if (village.get_pollution()/nombre_habitant < taux_pollution)
		{
			niveau -= 5;
		}
		else if (village.get_pollution()/nombre_habitant > taux_pollution)
		{
			niveau++;
		}
	
		if (village.get_nourriture() <= 0 || village.get_energie() <= 0 )
		{
			niveau -= 5;
		}
		
		if (niveau <= 0) niveau = 0;
		
		return niveau;
		
	}

}
