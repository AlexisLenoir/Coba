package coba;

import javafx.scene.Parent;
import javafx.scene.image.ImageView;

public class Villageois extends Parent
{
	private ImageView image = new ImageView(ImageJeux.imagevillageois);;
	private int x;
	private int y;
	
	
	public Villageois(int x, int y)
	{
		this.x = x;
		this.y = y;
		image.setFitWidth(10);
		image.setFitHeight(10);
		image.setSmooth(true);
		image.setCache(true);
		this.getChildren().add(image);
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public void setY (int y)
	{
		this.y = y;
	}
	
}
