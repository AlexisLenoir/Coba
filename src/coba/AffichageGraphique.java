package coba;




import java.io.File;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;



public class AffichageGraphique extends Application 
{
	
	private Integer choixAchat = 0;
	
	public static void main (String [] args)
	{
		Application.launch(args);
	}
	
	public void start (Stage primaryStage)
	{
		primaryStage.setTitle("City Challenge");
		
		//Scenes and Groups
		Group rootJeux= new Group();
		Scene sceneJeux = new Scene(rootJeux, 800, 800, Color.FLORALWHITE);
		
		Group rootMenu = new Group();
		Scene sceneMenu = new Scene(rootMenu, 800, 800, Color.FLORALWHITE);
		
		Group rootProduction = new Group();
		Scene sceneProduction = new Scene (rootProduction, 800, 800,Color.FLORALWHITE);
		
		//Menu
		
		String musicFileMenu = "soft-jazz-aint-no-sunshine-when-shes-gone.mp3";
		Media soundMenu = new Media(new File(musicFileMenu).toURI().toString());
		MediaPlayer mediaPlayerMenu = new MediaPlayer(soundMenu);
				
		ImageView mon_imageMenu = new ImageView(new Image(AffichageGraphique.class.getResourceAsStream("images/TorontoSkyline-min.jpg")));
		mon_imageMenu.setFitWidth(800);
		mon_imageMenu.setFitHeight(800);
		mon_imageMenu.setSmooth(true);
		mon_imageMenu.setCache(true);
		rootMenu.getChildren().add(mon_imageMenu);
		
		Button boutonJouer = new Button("JOUER");
		boutonJouer.setPrefSize(200, 40);
		boutonJouer.setTranslateX(300);
        boutonJouer.setTranslateY(350);
		rootMenu.getChildren().add(boutonJouer);
		
		Button boutonQuitter = new Button("QUITTER");
		boutonQuitter.setPrefSize(200, 40);
		boutonQuitter.setTranslateX(300);
        boutonQuitter.setTranslateY(500);
        rootMenu.getChildren().add(boutonQuitter);
		
		Text titre = new Text("City challenge");
		titre.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR,40));
		titre.setTranslateX(350);
		titre.setTranslateY(100);
		rootMenu.getChildren().add(titre);
		
		Text version = new Text("V5.0 - LENOIR - NACEREDDINE @COPYRIGHT");
		version.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR,12));
		version.setTranslateX(10);
		version.setTranslateY(750);
		rootMenu.getChildren().add(version);
		
		
        QuitteJeux monQuitteJeux = new QuitteJeux();
        boutonQuitter.setOnAction(monQuitteJeux);
        
        
        
        //FinMenu
		
        
        //DebutJeux
        
        //terrain 600x600
        Village mazan = new Village();
		mazan.setTranslateX(100);
		mazan.setTranslateY(100);
		rootJeux.getChildren().add(mazan);
		
		String musicFileJeux = "tes-v-skyrim-soundtrack-far-horizons.mp3";
		Media soundJeux = new Media(new File(musicFileJeux).toURI().toString());
		MediaPlayer mediaPlayerJeux = new MediaPlayer(soundJeux);
		
        String s = "Nombre d'habitants: ";
		Text nombre_habitant = new Text();
		nombre_habitant.setFont(Font.font("copperplate", FontWeight.BOLD, FontPosture.REGULAR,14));
		nombre_habitant.setTranslateX(350);
		nombre_habitant.setTranslateY(20);
		rootJeux.getChildren().add(nombre_habitant);
		nombre_habitant.setText(s+mazan.get_nombre_habitant());
		
		Text gameover = new Text();
		gameover.setFont(Font.font("copperplate", FontWeight.BOLD, FontPosture.REGULAR,60));
		gameover.setFill(Color.ROYALBLUE);
		gameover.setText("Vous avez perdu!");
		gameover.setTranslateX(150+800);
		gameover.setTranslateY(300);
		rootJeux.getChildren().add(gameover);
		
		Text indicManqueArgent = new Text();
		indicManqueArgent.setFont(Font.font("copperplate", FontWeight.BOLD, FontPosture.REGULAR,20));
		indicManqueArgent.setText("Manque d'argent !");
		indicManqueArgent.setTranslateX(20);
		indicManqueArgent.setTranslateY(730);
		indicManqueArgent.setFill(Color.GOLDENROD);
		indicManqueArgent.setOpacity(0);
		rootJeux.getChildren().add(indicManqueArgent);
		
		Achat achat = new Achat (mazan,indicManqueArgent );
		mazan.setOnMouseClicked(achat);
		
		
		
		Text niveau_satisfaction = new Text();
		niveau_satisfaction.setFont(Font.font("copperplate", FontWeight.BOLD, FontPosture.REGULAR,14));
		niveau_satisfaction.setTranslateX(350);
		niveau_satisfaction.setTranslateY(40);
		rootJeux.getChildren().add(niveau_satisfaction);
		niveau_satisfaction.setText("Niveau de Satisfaction: "+mazan.get_satisfaction());
		
		Text niveau_pollution = new Text();
		niveau_pollution.setFont(Font.font("copperplate", FontWeight.BOLD, FontPosture.REGULAR,14));
		niveau_pollution.setTranslateX(350);
		niveau_pollution.setTranslateY(60);
		rootJeux.getChildren().add(niveau_pollution);
		niveau_pollution.setText("Niveau de Pollution: "+mazan.get_pollution());
		
		
		Text niveau_argent = new Text();
		niveau_argent.setFont(Font.font("copperplate", FontWeight.BOLD, FontPosture.REGULAR,14));
		niveau_argent.setTranslateX(550+20);
		niveau_argent.setTranslateY(20);
		rootJeux.getChildren().add(niveau_argent);
		niveau_argent.setText("Niveau d'Argent: "+mazan.get_argent());
		
		Text jours = new Text();
		jours.setFont(Font.font("copperplate", FontWeight.BOLD, FontPosture.REGULAR,14));
		jours.setTranslateX(570);
		jours.setTranslateY(80);
		rootJeux.getChildren().add(jours);
		jours.setText("Jours :");
		
		Text niveau_energie = new Text();
		niveau_energie.setFont(Font.font("copperplate", FontWeight.BOLD, FontPosture.REGULAR,14));
		niveau_energie.setTranslateX(550+20);
		niveau_energie.setTranslateY(40);
		rootJeux.getChildren().add(niveau_energie);
		niveau_energie.setText("Niveau d'Enegie: "+mazan.get_energie());
		
		Text niveau_nourriture = new Text();
		niveau_nourriture.setFont(Font.font("copperplate", FontWeight.BOLD, FontPosture.REGULAR,14));
		niveau_nourriture.setTranslateX(550+20);
		niveau_nourriture.setTranslateY(60);
		rootJeux.getChildren().add(niveau_nourriture);
		niveau_nourriture.setText("Niveau de Nourriture: "+mazan.get_nourriture());
		
		
		
		
		//Animation
		RafraichirPage rafraichirPage = new RafraichirPage(mazan, nombre_habitant,niveau_satisfaction,niveau_argent,niveau_energie,niveau_pollution,niveau_nourriture, jours, gameover);
		
		final Timeline timeline = new Timeline( new KeyFrame(Duration.ZERO, rafraichirPage), new KeyFrame(Duration.millis(1000)) ); // 3 sec entre chaque tours
  		timeline.setCycleCount(Timeline.INDEFINITE);
  		timeline.setDelay(new Duration(4000));
		
		changeScene lanceJeux = new changeScene(primaryStage,sceneJeux,mediaPlayerJeux,mediaPlayerMenu, timeline, true);
		changeScene retourJeux = new changeScene(primaryStage,sceneJeux,mediaPlayerJeux,mediaPlayerMenu, timeline, false);
        boutonJouer.setOnAction(lanceJeux);
        
        changeScene lanceMenu = new changeScene(primaryStage,sceneMenu,mediaPlayerMenu,mediaPlayerJeux,timeline, false);
        Button boutonMenu = new Button("MENU");
		boutonMenu.setPrefSize(100,20);
		rootJeux.getChildren().add(boutonMenu);
		boutonMenu.setOnAction(lanceMenu);
		
		changeScene lanceProduction = new changeScene(primaryStage,sceneProduction,mediaPlayerJeux,mediaPlayerMenu,timeline, false);
		Button boutonProduction = new Button("PRODUCTION");
		boutonProduction.setPrefSize(150,20);
		boutonProduction.setTranslateX(100);
		rootJeux.getChildren().add(boutonProduction);
		boutonProduction.setOnAction(lanceProduction);
		
		Text enPause = new Text("EN PAUSE");
		enPause.setTranslateX(230);
		enPause.setTranslateY(50);
		enPause.setFont( new Font(20));
		enPause.setFill(Color.RED);
		enPause.setOpacity(0);
		rootJeux.getChildren().add(enPause);
		
		Button boutonPause = new Button("PAUSE");
		boutonPause.setPrefSize(90,20);
		boutonPause.setTranslateX(250);
		rootJeux.getChildren().add(boutonPause);
		MettrePause mettrePause = new MettrePause(timeline, enPause);
		boutonPause.setOnAction(mettrePause);
        //FinJeux
		
		
		//DebutProduction
		Button boutonJeux = new Button("JEUX");
		boutonJeux.setPrefSize(100,20);
		boutonJeux.setOnAction(retourJeux);
		rootProduction.getChildren().add(boutonJeux);
		
		Text consommation_energie = new Text();
		consommation_energie.setFont(new Font(15));
		consommation_energie.setTranslateX(100);
		consommation_energie.setTranslateY(100);
		rootProduction.getChildren().add(consommation_energie);
		consommation_energie.setText("Consommation d'energie par habitant (Par tour): 1");
		
		Text consommation_nourriture = new Text();
		consommation_nourriture.setFont(new Font(15));
		consommation_nourriture.setTranslateX(100);
		consommation_nourriture.setTranslateY(150);
		rootProduction.getChildren().add(consommation_nourriture);
		consommation_nourriture.setText("Consommation de nourriture par habitant (Par tour): 1");
		
		Text taux_pollution = new Text();
		taux_pollution.setFont(new Font(15));
		taux_pollution.setTranslateX(100);
		taux_pollution.setTranslateY(200);
		rootProduction.getChildren().add(taux_pollution);
		taux_pollution.setText("Taux de pollution recommandé par habitant (Par tour): " + Satisfaction.getPollution());
		
		Text taux_commerce = new Text();
		taux_commerce.setFont(new Font(15));
		taux_commerce.setTranslateX(100);
		taux_commerce.setTranslateY(250);
		rootProduction.getChildren().add(taux_commerce);
		taux_commerce.setText("Taux de commerce recommandé par habitant (Par tour): " + Satisfaction.getCommerce());
		
		Text production = new Text();
		production.setFont(new Font (20));
		production.setTranslateX(50);
		production.setTranslateY(400);
		production.setText("PRODUCTION PAR TOUR :");
		rootProduction.getChildren().add(production);
			
		Text production_commerce = new Text();
		production_commerce.setFont(new Font(15));
		production_commerce.setTranslateX(50);
		production_commerce.setTranslateY(450);
		rootProduction.getChildren().add(production_commerce);
		production_commerce.setText("Production commerce: 1 unité -> " + MiseAJour.production_argent_commerce+ " argent.");
		
		Text production_eolienne = new Text();
		production_eolienne.setFont(new Font(15));
		production_eolienne.setTranslateX(50);
		production_eolienne.setTranslateY(500);
		rootProduction.getChildren().add(production_eolienne);
		production_eolienne.setText("Production éolienne: 1 unité -> " + MiseAJour.production_energie_eolienne+ " energie, " + MiseAJour.production_pollution_eolienne +"pollution");
		
		Text production_centralecharbon = new Text();
		production_centralecharbon.setFont(new Font(15));
		production_centralecharbon.setTranslateX(50);
		production_centralecharbon.setTranslateY(550);
		rootProduction.getChildren().add(production_centralecharbon);
		production_centralecharbon.setText("Production centrale à charbon: 1 unité -> " + MiseAJour.production_energie_usine+ " energie, " + MiseAJour.production_pollution_usine+"pollution");
		
		Text production_ferme = new Text();
		production_ferme.setFont(new Font(15));
		production_ferme.setTranslateX(50);
		production_ferme.setTranslateY(600);
		rootProduction.getChildren().add(production_ferme);
		production_ferme.setText("Production ferme: 1 unité -> " + MiseAJour.production_nourriture_ferme + " nourriture. ");
		
		
		
		//FinProduction
		
		
		
		//Achat
		
		
		Rectangle choixEolienne = new Rectangle (200+30,705,80,80);
		choixEolienne.setFill(Color.WHITE);
		choixEolienne.setStroke(Color.DARKGOLDENROD);
		rootJeux.getChildren().add(choixEolienne);
		
		
		//Eolienne
		Text eolienneInfo = new Text();
		eolienneInfo.setText("Éolienne");
		eolienneInfo.setTranslateX(100);
		eolienneInfo.setFont(Font.font("copperplate", FontWeight.BOLD, FontPosture.REGULAR,20));
		eolienneInfo.setTranslateY(730);
		eolienneInfo.setOpacity(0);
		rootJeux.getChildren().add(eolienneInfo);
		
		Text eolienneCout = new Text ();
		eolienneCout.setText("Coût: "+MiseAJour.cout_Eolienne);
		eolienneCout.setTranslateX(100);
		eolienneCout.setFont(Font.font("copperplate", FontWeight.BOLD, FontPosture.REGULAR,20));
		eolienneCout.setTranslateY(750);
		eolienneCout.setOpacity(0);
		rootJeux.getChildren().add(eolienneCout);
		
		
		ImageView choixEolienneImage = new ImageView (ImageJeux.imageEolienne);
		choixEolienneImage.setFitWidth(60);
		choixEolienneImage.setFitHeight(60);
		choixEolienneImage.setSmooth(true);
		choixEolienneImage.setCache(true);
		choixEolienneImage.setTranslateX(200+40);
		choixEolienneImage.setTranslateY(705+10);
		rootJeux.getChildren().add(choixEolienneImage);
		
		Rectangle transparentEolienne = new Rectangle (200+30,705,80,80);
		transparentEolienne.setOpacity(0);
		rootJeux.getChildren().add(transparentEolienne);
		
		//Centrale
		
		Rectangle choixCentrale = new Rectangle (290+30,705,80,80);
		choixCentrale.setFill(Color.WHITE);
		choixCentrale.setStroke(Color.DARKGOLDENROD);
		rootJeux.getChildren().add(choixCentrale);
		ImageView choixCentraleImage = new ImageView (ImageJeux.imageCentraleCharbon);
		choixCentraleImage.setFitWidth(60);
		choixCentraleImage.setFitHeight(60);
		choixCentraleImage.setSmooth(true);
		choixCentraleImage.setCache(true);
		choixCentraleImage.setTranslateX(290+40);
		choixCentraleImage.setTranslateY(705+10);
		rootJeux.getChildren().add(choixCentraleImage);
		
		Text centraleInfo = new Text();
		centraleInfo.setText("Centrale à charbon");
		centraleInfo.setTranslateX(10);
		centraleInfo.setFont(Font.font("copperplate", FontWeight.BOLD, FontPosture.REGULAR,20));
		centraleInfo.setTranslateY(730);
		centraleInfo.setOpacity(0);
		rootJeux.getChildren().add(centraleInfo);
		
		Text centraleCout = new Text ();
		centraleCout.setText("Coût: "+MiseAJour.cout_Centrale);
		centraleCout.setTranslateX(60);
		centraleCout.setFont(Font.font("copperplate", FontWeight.BOLD, FontPosture.REGULAR,20));
		centraleCout.setTranslateY(750);
		centraleCout.setOpacity(0);
		rootJeux.getChildren().add(centraleCout);
		
		Rectangle transparentCentrale= new Rectangle (290+30,705,80,80);
		transparentCentrale.setOpacity(0);
		rootJeux.getChildren().add(transparentCentrale);
		
		
		//Ferme
		Rectangle choixFerme = new Rectangle (380+30,705,80,80);
		choixFerme.setFill(Color.WHITE);
		choixFerme.setStroke(Color.DARKGOLDENROD);
		rootJeux.getChildren().add(choixFerme);
		ImageView choixFermeImage = new ImageView (ImageJeux.imageFerme);
		choixFermeImage.setFitWidth(60);
		choixFermeImage.setFitHeight(60);
		choixFermeImage.setSmooth(true);
		choixFermeImage.setCache(true);
		choixFermeImage.setTranslateX(380+40);
		choixFermeImage.setTranslateY(705+10);
		rootJeux.getChildren().add(choixFermeImage);
		
		Text fermeInfo = new Text();
		fermeInfo.setText("Ferme");
		fermeInfo.setTranslateX(100);
		fermeInfo.setFont(Font.font("copperplate", FontWeight.BOLD, FontPosture.REGULAR,20));
		fermeInfo.setTranslateY(730);
		fermeInfo.setOpacity(0);
		rootJeux.getChildren().add(fermeInfo);
		
		Text fermeCout = new Text ();
		fermeCout.setText("Coût: "+MiseAJour.cout_Ferme);
		fermeCout.setTranslateX(100);
		fermeCout.setFont(Font.font("copperplate", FontWeight.BOLD, FontPosture.REGULAR,20));
		fermeCout.setTranslateY(750);
		fermeCout.setOpacity(0);
		rootJeux.getChildren().add(fermeCout);
		
		Rectangle transparentFerme = new Rectangle (380+30,705,80,80);
		transparentFerme.setOpacity(0);
		rootJeux.getChildren().add(transparentFerme);
		
		//Commerce
		
		Rectangle choixCommerce = new Rectangle (470+30,705,80,80);
		choixCommerce.setFill(Color.WHITE);
		choixCommerce.setStroke(Color.DARKGOLDENROD);
		rootJeux.getChildren().add(choixCommerce);
		ImageView choixCommerceImage = new ImageView (ImageJeux.imageCommerce);
		choixCommerceImage.setFitWidth(60);
		choixCommerceImage.setFitHeight(60);
		choixCommerceImage.setSmooth(true);
		choixCommerceImage.setCache(true);
		choixCommerceImage.setTranslateX(470+40);
		choixCommerceImage.setTranslateY(705+10);
		rootJeux.getChildren().add(choixCommerceImage);
		
		
		Text commerceInfo = new Text();
		commerceInfo.setText("Commerce");
		commerceInfo.setTranslateX(100);
		commerceInfo.setFont(Font.font("copperplate", FontWeight.BOLD, FontPosture.REGULAR,20));
		commerceInfo.setTranslateY(730);
		commerceInfo.setOpacity(0);
		rootJeux.getChildren().add(commerceInfo);
		
		Text commerceCout = new Text ();
		commerceCout.setText("Coût: "+MiseAJour.cout_Commerce);
		commerceCout.setTranslateX(100);
		commerceCout.setFont(Font.font("copperplate", FontWeight.BOLD, FontPosture.REGULAR,20));
		commerceCout.setTranslateY(750);
		commerceCout.setOpacity(0);
		rootJeux.getChildren().add(commerceCout);
		
		Rectangle transparentCommerce = new Rectangle (470+30,705,80,80);
		transparentCommerce.setOpacity(0);
		rootJeux.getChildren().add(transparentCommerce);
		
		
		SelectionnerBatiment selectionnerEolienne = new SelectionnerBatiment(choixEolienne,choixCommerce,choixCentrale,choixFerme,1);
		transparentEolienne.setOnMouseClicked(selectionnerEolienne);
		InfoAchat surEolienne = new InfoAchat(eolienneInfo,eolienneCout, true,indicManqueArgent);
		InfoAchat horsEolienne = new InfoAchat(eolienneInfo,eolienneCout, false,indicManqueArgent);
		transparentEolienne.setOnMouseEntered(surEolienne);
		transparentEolienne.setOnMouseExited(horsEolienne);
	
		
		SelectionnerBatiment selectionnerCentrale = new SelectionnerBatiment(choixEolienne,choixCommerce,choixCentrale,choixFerme,2);
		transparentCentrale.setOnMouseClicked(selectionnerCentrale);
		InfoAchat surCentrale = new InfoAchat(centraleInfo,centraleCout, true,indicManqueArgent);
		transparentCentrale.setOnMouseEntered(surCentrale);
		InfoAchat horsCentrale = new InfoAchat(centraleInfo,centraleCout, false,indicManqueArgent);
		transparentCentrale.setOnMouseExited(horsCentrale);
		
		SelectionnerBatiment selectionnerCommerce = new SelectionnerBatiment(choixEolienne,choixCommerce,choixCentrale,choixFerme,3);
		transparentCommerce.setOnMouseClicked(selectionnerCommerce);
		InfoAchat surCommerce = new InfoAchat(commerceInfo,commerceCout, true,indicManqueArgent);
		transparentCommerce.setOnMouseEntered(surCommerce);
		InfoAchat horsCommerce= new InfoAchat(commerceInfo,commerceCout, false,indicManqueArgent);
		transparentCommerce.setOnMouseExited(horsCommerce);
		
		SelectionnerBatiment selectionnerFerme = new SelectionnerBatiment(choixEolienne,choixCommerce,choixCentrale,choixFerme,4);
		transparentFerme.setOnMouseClicked(selectionnerFerme);
		InfoAchat surFerme = new InfoAchat(fermeInfo,fermeCout, true,indicManqueArgent);
		transparentFerme.setOnMouseEntered(surFerme);
		InfoAchat horsFerme= new InfoAchat(fermeInfo,fermeCout, false,indicManqueArgent);
		transparentFerme.setOnMouseExited(horsFerme);
		
		
		//Fin Achat
	
	
		
		mediaPlayerMenu.play();
  		mediaPlayerMenu.setCycleCount(MediaPlayer.INDEFINITE);
  		
  		
		primaryStage.setResizable(true);
		primaryStage.setScene(sceneMenu);
		primaryStage.show();
		
		
	}
	
	
	public class RafraichirPage implements EventHandler<ActionEvent>
	{

		Village v;
		Text habs;
		Text satisfact;
		Text argent;
		Text energie;
		Text pollution;
		Text nourriture;
		Text jours;
		Text perdu;
		
		
		int i;
		
		public RafraichirPage (Village v, Text h,Text s, Text a, Text e, Text p, Text n, Text j, Text perdu)
		{
			this.v = v;
			habs = h;
			satisfact = s;
			argent  = a;
			energie = e;
			pollution = p;
			nourriture = n;
			i = 0;
			jours = j;
			this.perdu = perdu;
			
		}
		
		@Override
		public void handle(ActionEvent event) 
		{
			
			if (v.get_nombre_habitant() <= 0)
			{
				perdu.setTranslateX(150);
				v.setOpacity(0.5);
				return;
			}
			
			MiseAJour.tourSuivant(v);
			jours.setText("Jours : " +i);
			nourriture.setText("Niveau de Nourriture: "+v.get_nourriture());
			energie.setText("Niveau d'Enegie: "+ Math.round(v.get_energie()));
			argent.setText("Niveau d'Argent: "+v.get_argent());
			pollution.setText("Niveau de Pollution: "+v.get_pollution());
			satisfact.setText("Niveau de Satisfaction: "+v.get_satisfaction());
			habs.setText("Nombre d'habitants: "+v.get_nombre_habitant());
			i++;
			
		}
		
	}
	
	public class MettrePause implements EventHandler<ActionEvent>
	{
		Timeline t;
		Text text;
		boolean enPause = false;
		
		public MettrePause (Timeline t, Text text)
		{
			this.t = t;
			this.text = text;
		}
		
		@Override
		public void handle(ActionEvent event) 
		{
			if (enPause)
			{
				t.play();
				text.setOpacity(0);
				enPause = false;
				return;
			}
			
			text.setOpacity(1);
			t.pause();
			enPause = true;
			
		}
		
	}
	public class InfoAchat implements EventHandler<MouseEvent>
	{

		private Text nom;
		private Text cout;
		boolean enter;
		Text alerte;
	
		public InfoAchat (Text nom, Text cout, Boolean b, Text alerte)
		{
			this.nom  = nom;
			this.cout = cout;
			enter = b;
			this.alerte = alerte;
		}
		
		@Override
		public void handle(MouseEvent event) 
		{
			alerte.setOpacity(0);
			if (enter)
			{
				nom.setOpacity(1);
				cout.setOpacity(1);
			}
			else
			{
				nom.setOpacity(0);
				cout.setOpacity(0);
			}
		}
		
	}
	public class Achat implements EventHandler<MouseEvent>
	{
		
		int x, y;
		
		Village village;
		Text manqueArgent;
		
		public Achat (Village v, Text alerte)
		{
			
			village = v;
			manqueArgent = alerte;
		}
		
		@Override
		public void handle(MouseEvent event) 
		{
		
			y = (int)( event.getX());
			x = (int)( event.getY());
			
			x /= 30;
			y /= 30;
			
			switch (choixAchat)
			{
				case 1:
					if (village.get_argent() -MiseAJour.cout_Eolienne < 0)
					{
						manqueArgent.setOpacity(1);
						return;
					}
					village.ajouterBatiment(new Eolienne(y,x));
					village.set_niveau_argent(-MiseAJour.cout_Eolienne);
					break;
				case 2:
					if (village.get_argent() -MiseAJour.cout_Centrale < 0)
					{
						manqueArgent.setOpacity(1);
						return;
					}
					village.ajouterBatiment(new CentraleCharbon(y,x));
					village.set_niveau_argent(-MiseAJour.cout_Centrale);
					break;
				case 3:
					if (village.get_argent() -MiseAJour.cout_Commerce < 0)
					{
						manqueArgent.setOpacity(1);
						return;
					}
					village.ajouterBatiment(new Commerce(y,x));
					village.set_niveau_argent(-MiseAJour.cout_Commerce);
					break;
				case 4:
					if (village.get_argent() -MiseAJour.cout_Ferme < 0)
					{
						manqueArgent.setOpacity(1);
						return;
					}
					village.ajouterBatiment(new Ferme(y,x));
					village.set_niveau_argent(-MiseAJour.cout_Ferme);
					break;
			}
			
		}
		
	}
	
	public class SelectionnerBatiment implements EventHandler<MouseEvent>
	{
		Rectangle eolienne;
		Rectangle centrale;
		Rectangle commerce;
		Rectangle ferme;
		int choix; //0 par défaut, 1 eolienne, 2 centrale, 3 commerce,  4 ferme
		
		
		public SelectionnerBatiment (Rectangle e, Rectangle co, Rectangle ce, Rectangle f, int c)
		{
			eolienne = e;
			centrale = ce;
			commerce = co;
			ferme =f;
			choix = c;
			
		}
		
		@Override
		public void handle(MouseEvent event) 
		{
			switch (choix)
			{
				case 1:
					commerce.setStroke(Color.DARKGOLDENROD);
					ferme.setStroke(Color.DARKGOLDENROD);
					centrale.setStroke(Color.DARKGOLDENROD);
					eolienne.setStroke(Color.RED);
					choixAchat = 1;
					break;
				case 2:
					commerce.setStroke(Color.DARKGOLDENROD);
					ferme.setStroke(Color.DARKGOLDENROD);
					centrale.setStroke(Color.RED);
					eolienne.setStroke(Color.DARKGOLDENROD);
					choixAchat = 2;
					break;
				case 3:
					commerce.setStroke(Color.RED);
					ferme.setStroke(Color.DARKGOLDENROD);
					centrale.setStroke(Color.DARKGOLDENROD);
					eolienne.setStroke(Color.DARKGOLDENROD);
					choixAchat = 3;
					break;
				case 4:
					commerce.setStroke(Color.DARKGOLDENROD);
					ferme.setStroke(Color.RED);
					centrale.setStroke(Color.DARKGOLDENROD);
					eolienne.setStroke(Color.DARKGOLDENROD);
					choixAchat = 4;
					break;
					
					
					
			}

		}
		
	}
	
	public class changeScene implements EventHandler<ActionEvent>
	 {
		  Stage sta;
		  Scene sce;
		  MediaPlayer mediaPlayerNouveau;
		  MediaPlayer mediaPlayerAncien;
		  Village v;
		  Timeline t;
		  boolean lancejeux;
		  
		  public changeScene (Stage sta1, Scene sce, MediaPlayer mpn,MediaPlayer mpa, Timeline t, boolean lancejeux)
		  {
			  sta = sta1;
			  this.sce = sce;
			  mediaPlayerNouveau = mpn;
			  mediaPlayerAncien = mpa;
			  this.t = t;
			  this.lancejeux = lancejeux;
			  
		}
		   
		   
		    public void handle (ActionEvent ev)
		    {
		    		sta.setScene(sce);
		    		sta.show();
		    		mediaPlayerAncien.stop();
		    		mediaPlayerNouveau.play();
		    		mediaPlayerNouveau.setCycleCount(MediaPlayer.INDEFINITE);
		    		if (lancejeux)
				{
						  t.play();
				}
			   
			}
	 }
	
	public class QuitteJeux implements EventHandler<ActionEvent>
	 {
		  public void handle (ActionEvent ev)
		    {
			   Platform.exit();
			}
	 }

}
