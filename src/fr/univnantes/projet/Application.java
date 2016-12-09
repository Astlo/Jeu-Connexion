package fr.univnantes.projet;

import fr.univnantes.projet.ig.Fenetre;
import fr.univnantes.projet.ig.Grille;
import fr.univnantes.projet.ig.Menu;
import fr.univnantes.projet.monde.Joueur;
import fr.univnantes.projet.monde.Monde;

import java.awt.Color;

/**
 * Classe applicative montrant un EXEMPLE de création d'une grille
 * et de son affichage dans une fenêtre graphique.
 */
public class Application 
{
	private Monde monde_;
	
	private void nouveauDamier(Joueur joueur1, Joueur joueur2, int n, int k) {
		monde_ = new Monde(joueur1, joueur2, n, k);
	}
	
	private boolean testJeuTermine(Joueur courant) {
		int vainqueur = monde_.getVainqueur(courant);
		if(vainqueur == 1)
		{
			System.out.println("Le joueur 1 a gagné la partie !");
			return true;
		}
		else if(vainqueur == 2)
		{
			System.out.println("Le joueur 2 a gagné la partie !");
			return true;
		}
		return false;
	}
	
	public void joueOrdiHumain()
	{
		monde_.creationDuMonde();
		jouerPartie(true);
		
		
	}
	
	public void joueDeuxHumains()
	{
		monde_.creationDuMonde();
		jouerPartie(false);
		
	}
	
	private void jouerPartie(boolean partieOrdi)
	{
		Grille grille = new Grille(monde_.getN(), monde_);
		Fenetre fenetre = new Fenetre("Jeu  connexion",grille, monde_, partieOrdi);
	}
	
	private void menu() 
	{
		monde_.initialiseCarte();
		Grille grille = new Grille(monde_.getN(), monde_);
		Menu menu = new Menu("Jeu  connexion", grille, this);
	}
	
	/**
	 * Point d'entrée du programme exécutable
	 * @param args Paramètre non utilisé
	 */
	public static void main(String[] args)
	{
		Joueur moi = new Joueur("Keurl",Color.red);
		Joueur toi = new Joueur("Astlo",Color.blue);
		
		try {
			int n = Input.lireEntierDepuisConsole("Dimension du damier n=", "Entrer un entier compris entre "
					+ 3 + " et " + 10, 3, 10);
			int k = Input.lireEntierDepuisConsole("Nombre d'étoiles initiales k=", "Entrer un entier compris entre "
					+ 2 + " et " + 10, 2, 10);

			Application app = new Application();

			app.nouveauDamier(moi, toi, n, k);
			app.menu();
		} catch (AbandonException e) {
			// Sortie du programme demandée.
			System.err.println(e.getMessage());
		}
		
		return;
	}
}
