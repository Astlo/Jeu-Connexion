package fr.univnantes.projet;

import fr.univnantes.projet.ig.Fenetre;
import fr.univnantes.projet.ig.Grille;
import fr.univnantes.projet.monde.Joueur;
import fr.univnantes.projet.monde.Monde;
import fr.univnantes.projet.monde.Position;

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
		int n = monde_.getN();
		boolean fini = false;
		Joueur courant = monde_.getJoueur1();

		while (!fini) {
			
		}
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
					+ 0 + " et " + 10, 0, 10);
			int k = Input.lireEntierDepuisConsole("Nombre d'étoiles initiales k=", "Entrer un entier compris entre "
					+ 0 + " et " + 10, 0, 10);

			Application app = new Application();
			app.nouveauDamier(moi, toi, n, k);
			//app.menu();
		} catch (AbandonException e) {
			// Sortie du programme demandée.
			System.err.println(e.getMessage());
		}
		
		//Création d'un monde vide
		//Monde monde = new Monde(moi, toi);
		//Initialisation du monde
		//monde.creationDuMonde();
		//Grille grille = new Grille(Constante.N, monde);
		//Fenetre fenetre = new Fenetre("Jeu  connexion",grille, monde);
		//grille.dessiner();
		
		return;
	}
}
