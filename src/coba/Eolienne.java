package coba;

public class Eolienne extends Industrie implements Production
{

	private static int  nombreEolienne = 0;
	
	public Eolienne(int x, int y) 
	{
		super(x,y, MiseAJour.production_energie_eolienne,10);
		
		production_energie = MiseAJour.production_energie_eolienne;
		nombreEolienne++;
	}
	
	public static int getNombreEolienne()
	{
		return nombreEolienne;
	}

	@Override
	public void pollue(Village village) 
	{
		Terrain [][] carte = village.get_Terrain();
		carte[x][y].devientPollue();
		carte[x-1][y].devientPollue();
		carte[x+1][y].devientPollue();
		carte[x][y-1].devientPollue();
		carte[x][y+1].devientPollue();
	}

	public String getType() 
	{
		return "eolienne";
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
