package coba;

public abstract class Batiment 
{
	
	protected int x;
	protected int y;
	
	public Batiment (int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public String getType()
	{
		return "batiment";
	}
	
	public void setTauxProductionPollue() 
	{
		
	}
	

}
