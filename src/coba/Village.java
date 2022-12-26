package coba;

import java.util.ArrayList;

import javafx.scene.Parent;

public class Village extends Parent 
{
	
	private Terrain [][] carte;
	private static int dimension = 20;
	private ArrayList<Industrie> industries;
	private ArrayList<BatimentVille> batimentVilles;
	private ArrayList<Ferme> fermes;
	private ArrayList<Villageois> villageois;
	private double niveau_Argent;
	private double niveau_Energie;
	private double niveau_Pollution; 
	private double niveau_Nourriture;
	private int nombre_habitant;
	private int niveau_satisfaction;

	
	
	public Village ()
	{
		carte = new Terrain [dimension][dimension];
		industries = new ArrayList<Industrie>();
		batimentVilles = new ArrayList<BatimentVille>();
		fermes = new ArrayList<Ferme>();
		villageois = new ArrayList<Villageois>();
		
		for (int i = 0; i < dimension; i ++)
		{
			for (int j = 0; j < dimension; j++)
			{
				carte[i][j] = new Terrain();
				carte[i][j].setTranslateX(i*30);
				carte[i][j].setTranslateY(j*30);
				this.getChildren().add(carte[i][j]);
			}
		}
		
			
		nombre_habitant = 50;
		niveau_Argent = 100;
		niveau_Pollution = 0;
		niveau_Energie = 0;
		niveau_Nourriture = 0;
		niveau_satisfaction = 100;
		
	
		ajouterBatiment (new Maison(10,10) );
		ajouterBatiment (new Maison(11,10));
		ajouterBatiment (new Maison(12,11));
		ajouterBatiment (new Maison(9,11));
		ajouterBatiment (new Maison(10,9));
		
		ajouterBatiment (new Ferme(14,14));
		
		
	}
	
	
	public void set_niveau_argent (double a)
	{
		niveau_Argent += a;
		if (niveau_Argent < 0)
		{
			niveau_Argent = 0;
		}
	}
	
	public void set_niveau_energie (double e)
	{
		niveau_Energie += e;
		if (niveau_Energie < 0)
		{
			niveau_Energie = 0;
		}
	}
	
	public void set_niveau_nourriture(double n)
	{
		niveau_Nourriture += n;
		if (niveau_Nourriture < 0)
		{
			niveau_Nourriture = 0;
		}
	}
	
	public void set_niveau_pollution (double p)
	{
		niveau_Pollution = p;
	}
	
	public void set_niveau_satisfaction (int niveau)
	{
		niveau_satisfaction = niveau;
	}
	
	public void set_nombre_habitant (int habs)
	{
		nombre_habitant = habs;
	}
	
	public Terrain[][] get_Terrain ()
	{
		return carte;
	}
	
	public ArrayList<Industrie> get_Industries()
	{
		return industries;
	}
	
	public ArrayList<BatimentVille> get_BatimentVilles()
	{
		return batimentVilles;
	}
	
	public ArrayList<Ferme> get_Fermes()
	{
		return fermes;
	}
	
	public int get_nombre_habitant()
	{
		return nombre_habitant;
	}
	
	public double get_energie ()
	{
		return niveau_Energie;
	}
	
	public double get_pollution()
	{
		return niveau_Pollution;
	}
	
	public double get_nourriture()
	{
		return niveau_Nourriture;
	}
	
	public double get_argent()
	{
		return niveau_Argent;
	}
	
	public int get_satisfaction()
	{
		return niveau_satisfaction;
	}
	
	
	public void ajouterBatiment (Batiment t)
	{
		
		Villageois myvillageois;
		int x = t.getX();
		int y = t.getY();
		carte[x][y].ajouterBatiment(t);
		
		switch (t.getType())
		{
			
			
			case "maison":
				batimentVilles.add((BatimentVille)t);
				myvillageois = new Villageois(x,y);
				myvillageois.setTranslateY(y*30+12);
				myvillageois.setTranslateX(x*30+8);
				this.getChildren().add(myvillageois);
				villageois.add(myvillageois);
				break;
				
			case "commerce":
				batimentVilles.add((BatimentVille)t);
				break;
			case "eolienne":
				industries.add((Industrie)t);
				break;
			case "centralecharbon":
				industries.add((Industrie)t);
				break;
			case "ferme":
				fermes.add((Ferme) t);
		}
		
	}
	
	
	public int cherche_maison()
	{
		int i;
		
		do
		{
			i = (int) (Math.random()*(batimentVilles.size()));
			
		}
		while ( !(batimentVilles.get(i).getType().equals("maison")) );
		return i;
	}
	
	public void nouvelle_maison()
	{
		
		int x,y, xr, yr;
		
		int i = cherche_maison();
				
		x = batimentVilles.get(i).getX();
		y = batimentVilles.get(i).getY();
		
		do
		{
			xr = (int) (Math.random()*6 - 3);
			yr = (int) (Math.random()*6 - 3);
					
			xr = coord_Valable(x+xr);
			yr = coord_Valable(y+yr);
			
		}while (!carte[xr][yr].estVide());
		
		Batiment maison = new Maison(xr,yr);
		
		ajouterBatiment(maison);
	}
	
	public static int coord_Valable (int x)
	{
		if ( x < 0)
		{
			return dimension + x;
		}
		else if ( x >= dimension)
		{
			return x - dimension;
		}
		else
		{
			return x;
		}
	}
	
	
	public void enlever_maison()
	{
		
		if (nombre_habitant <= 0)
		{
			return;
		}
		
		for (int i = 0; i < batimentVilles.size(); i++)
		{
			
			if (batimentVilles.get(i).getType().equals("maison") )
			{
				
				BatimentVille bv = batimentVilles.remove(i);
				
				carte[bv.getX()][bv.getY()].enleverBatiment();
				this.getChildren().remove(villageois.remove(0));
				return;
			}
		}
	} 
	
	public void mettreAJourVillageois()
	{
		int xr, yr, x, y;
		for (Villageois v : villageois)
		{
			
			xr =  (int) (Math.random()*3) -1;
			yr =  (int) (Math.random()*3) -1;
			
			x = coord_Valable(v.getX() + xr);
			y = coord_Valable(v.getY() + yr);
			v.setTranslateX(x*30);
			v.setTranslateY(y*30);
			
			v.setX(x);
			v.setY(y);
			
		}
	}
	
	public String toString()
	{
		String s ="";
		s += "Le nombre d'habitant est " + nombre_habitant + "\n";
		s += " Niveau de satisfaction " + niveau_satisfaction + " Niveau Pollution " + niveau_Pollution +"\n";
		s += "Niveau Argent " + niveau_Argent + " Niveau Energie " + niveau_Energie + " Niveau Nourriture " + niveau_Nourriture + "\n";
		s += "Nombre de Centrale à Charbon " + CentraleCharbon.getNombreCentraleCharbon()+ " Nombre d'eolienne "+  Eolienne.getNombreEolienne() + " Nombre de commerce " + Commerce.getNombreCommerce()+ " Nombre de ferme " + Ferme.getNombreFerme()+ "\n";
		
		for (int i = 0; i < dimension; i++)
		{
			for (int j = 0; j < dimension; j++)
			{
				if (carte[i][j].estVide())
				{
					if (carte[i][j].estPolluee())
					{
						s += "!";
					}
					else
					{
						s += ".";
					}
					
				}
				else
				{
					switch (carte[i][j].getBatiment().getType())
					{
						case "ferme":
							s += "f";
							break;
						case "eolienne":
							s += "e";
							break;
						case "commerce":
							s += "c";
							break;
						case "centralecharbon":
							s += "u"; //pour usine
							break;
						case "maison":
							s += "*";
							break;
						default :
							s += "?";
					}
				}
				
			}
			
			s += "\n";
		}
		return s;
	}
	
	public void affiche_village()
	{
		System.out.println( toString());
	}

}
