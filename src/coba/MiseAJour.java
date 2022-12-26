package coba;

import java.util.ArrayList;

import javafx.scene.image.ImageView;

public class MiseAJour 
{
	
	public final static int nombre_habitant_par_maison = 10;
	public final static double production_energie_usine = 50;
	public final static double production_pollution_usine = 100;
	public final static double production_energie_eolienne = 5;
	public final static double production_pollution_eolienne = 10;
	public final static double production_argent_commerce = 100;
	public final static double production_nourriture_ferme = 60;
	
	public final static double cout_Eolienne = 60;
	public final static double cout_Centrale = 30;
	public final static double cout_Commerce = 20;
	public final static double cout_Ferme = 40;
	
	
	
	public static void miseAJourRessources(Village village)
	{
		ArrayList<Industrie> industries = village.get_Industries();
		ArrayList<BatimentVille> batimentVilles = village.get_BatimentVilles();
		ArrayList<Ferme> fermes = village.get_Fermes();
		double niveau_Argent  = 0;
		double niveau_Energie = 0;
		double niveau_Pollution = 0;
		double niveau_Nourriture = 0;
		
		for (Industrie b : industries)
		{
			switch (b.getType())
			{
				
				case "eolienne" :
					niveau_Energie += b.getTauxProduction();
					niveau_Pollution += production_pollution_eolienne;
					break;
					
				case "centralecharbon":
					niveau_Energie += b.getTauxProduction();
					niveau_Pollution += production_pollution_usine;
			}
			b.pollue(village);
		
		}

		niveau_Energie -= village.get_nombre_habitant();
		village.set_niveau_energie(niveau_Energie);
		village.set_niveau_pollution(niveau_Pollution);
		
		for (BatimentVille bv : batimentVilles)
		{
				switch (bv.getType())
				{
					case "commerce":
						niveau_Argent += bv.getTauxProduction();
				}
		}
		village.set_niveau_argent(niveau_Argent);
		
		for (Ferme f : fermes)
		{
			niveau_Nourriture += f.getTauxProduction();
		}
		
		niveau_Nourriture -= village.get_nombre_habitant();
		
		village.set_niveau_nourriture(niveau_Nourriture);
		
		
	}
	
	public static void miseAJourHabitant(Village village)
	{
		
		double niveau = village.get_satisfaction();
		
		if (niveau > 75)
		{
			village.nouvelle_maison();
			village.nouvelle_maison();
		}
		else if (niveau > 50)
		{
			village.nouvelle_maison();
		}
		else if (niveau > 25)
		{
			village.enlever_maison();
			Maison.enleverMaison();
		}
		else
		{
			village.enlever_maison();
			village.enlever_maison();
			Maison.enleverMaison();
			Maison.enleverMaison();
		}
		village.set_nombre_habitant(Maison.getNombreMaison()*nombre_habitant_par_maison);
		
		village.mettreAJourVillageois();
		
		
	}
	
	public static void tourSuivant (Village village)
	{
		miseAJourRessources(village);
		village.set_niveau_satisfaction(Satisfaction.calcul_satisfaction(village, village.get_satisfaction()));
		miseAJourHabitant(village);
		
	}
	
	
}
