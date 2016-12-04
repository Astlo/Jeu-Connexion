package fr.univnantes.projet;

import fr.univnantes.projet.ig.Fenetre;
import fr.univnantes.projet.ig.Grille;
import fr.univnantes.projet.monde.Joueur;
import fr.univnantes.projet.monde.Monde;
import fr.univnantes.projet.monde.Position;

import java.awt.Color;
import java.awt.event.ActionEvent;

/**
 * Classe applicative montrant un EXEMPLE de création d'une grille
 * et de son affichage dans une fenêtre graphique.
 */
public class Application 
{
	/**
	 * Point d'entrée du programme exécutable
	 * @param args Paramètre non utilisé
	 */
	
	public static void main(String[] args)
	{

		Joueur moi = new Joueur("Keurl",Color.red);
		Joueur toi = new Joueur("Astlo",Color.blue);
		//Création d'un monde vide
		Monde monde = new Monde(moi, toi);
		//Initialisation du monde

		monde.creationDuMonde();
		
		Grille grille = new Grille(Constante.N, monde);
		
		Fenetre fenetre = new Fenetre("Jeu  connexion",grille, monde);

		System.out.println("c'est au joueur " + monde.getj1().getPseudo() + " de jouer.");
		while(monde.ScoreMax() != Constante.K && !moi.getAbandon() && !toi.getAbandon()){
			
			 
			grille.dessiner();
			
		}
		System.out.println("fin de la partie !");
		return;
	}
}