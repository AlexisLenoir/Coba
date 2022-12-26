package coba;

import java.util.ArrayList;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Terrain extends Parent 
{
	private boolean pollue;
	private boolean vide;
	private Batiment batiment;
	private Rectangle terrain;
	private ImageView imageMaison; 
	private ImageView imageEolienne;
	private ImageView imageCentraleCharbon;
	private ImageView imageCommerce;
	private ImageView imageFerme;
	
	
	public Terrain ()
	{
		vide = true;
		pollue = false;
		terrain = new Rectangle (0,0,30,30);
		terrain.setFill(Color.GREENYELLOW);
		this.getChildren().add(terrain);
		
		
		imageMaison = new ImageView(ImageJeux.imageMaison);
		imageEolienne = new ImageView(ImageJeux.imageEolienne);
		imageCentraleCharbon = new ImageView(ImageJeux.imageCentraleCharbon);
		imageCommerce = new ImageView (ImageJeux.imageCommerce);
		imageFerme = new ImageView (ImageJeux.imageFerme);
		
		imageMaison.setFitWidth(25);
		imageMaison.setFitHeight(25);
		imageMaison.setSmooth(true);
		imageMaison.setCache(true);
		
		imageEolienne.setFitWidth(30);
		imageEolienne.setFitHeight(30);
		imageEolienne.setSmooth(true);
		imageEolienne.setCache(true);
		
		imageCentraleCharbon.setFitWidth(25);
		imageCentraleCharbon.setFitHeight(25);
		imageCentraleCharbon.setSmooth(true);
		imageCentraleCharbon.setCache(true);
		
		imageCommerce.setFitWidth(25);
		imageCommerce.setFitHeight(25);
		imageCommerce.setSmooth(true);
		imageCommerce.setCache(true);
		
		imageFerme.setFitWidth(25);
		imageFerme.setFitHeight(25);
		imageFerme.setSmooth(true);
		imageFerme.setCache(true);
		
	}
	
	public boolean estPolluee()
	{
		return pollue;
		
	}
	
	public void devientPollue()
	{
		pollue = true;
		terrain.setFill(Color.rgb(183, 10, 57));
		
		if (!vide)
		{
			batiment.setTauxProductionPollue(); 
		}
		
	}
	
	public void ajouterBatiment (Batiment t)
	{
		
		if(vide)
		{
			vide = false;
			batiment = t;
			switch (t.getType())
			{
				case "maison":		
					this.getChildren().add(imageMaison);
					break;
				case "commerce":
					this.getChildren().add(imageCommerce);
					break;
				case "eolienne":
					this.getChildren().add(imageEolienne);
					break;
				case "centralecharbon":
					this.getChildren().add(imageCentraleCharbon);
					break;
				case "ferme":
					this.getChildren().add(imageFerme);
			}
		}
		
	}
	
	
	public void enleverBatiment()
	{
		vide = true;
		switch (batiment.getType())
		{
			case "maison":
				this.getChildren().remove(imageMaison);
				break;
			case "commerce":
				this.getChildren().remove(imageCommerce);
				break;
			case "eolienne":
				this.getChildren().remove(imageEolienne);
				break;
			case "centralecharbon":
				this.getChildren().remove(imageCentraleCharbon);
				break;
			case "ferme":
				this.getChildren().remove(imageFerme);
		}		
				
	}
	
	public Batiment getBatiment()
	{
		return batiment;
	}
	
	public boolean estVide()
	{
		return vide;
	}
	
	
}
