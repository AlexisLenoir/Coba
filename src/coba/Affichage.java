package coba;

public class Affichage 
{
	public static void main (String [] args)
	{
		Village mazan = new Village();
		
		for (int i = 0; i < 20; i++)
		{
			MiseAJour.tourSuivant(mazan);
			mazan.affiche_village();
			try {
	            // thread to sleep for 1000 milliseconds
	            Thread.sleep(1000);
	         } catch (Exception e) {
	            System.out.println(e);
	         }
		}
	}

}
