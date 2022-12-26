package coba;

public class CentraleCharbon extends Industrie implements Production 
{

	private static int nombreCentraleCharbon = 0;
	
	
	public CentraleCharbon(int x, int y) 
	{
		super(x, y, MiseAJour.production_energie_usine, 100);
		production_energie = MiseAJour.production_energie_usine;
		nombreCentraleCharbon++;
	}
	
	public static int getNombreCentraleCharbon()
	{
		return nombreCentraleCharbon;
	}

	public void pollue(Village village) 
	{
		Terrain [][] carte = village.get_Terrain();
		int x2,y2;
		
		for (int i = -1; i < 2; i++)
		{
			for (int j = -1; j < 2; j++)
			{
				x2 = Village.coord_Valable(x+i);
				y2 = Village.coord_Valable(y+j);
				carte[x2][y2].devientPollue();
			}
		}
	}
	
	public String getType() 
	{
		return "centralecharbon";
	}

	public double getTauxProduction() 
	{
		return production_energie;
	}

	public void setTauxProductionPollue() 
	{
		production_energie -= (production_energie*20)/100;
	}

}
