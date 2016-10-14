package fr.univnantes.projet;

import fr.univnantes.projet.ig.Fenetre;
import fr.univnantes.projet.ig.Grille;
import fr.univnantes.projet.monde.Monde;

import java.util.*;
import java.awt.Color;

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
		//Création d'un monde vide
		Monde monde = new Monde();
		//Initialisation du monde
		monde.creationDuMonde();
	
		Joueur1 moi = new Joueur1("X", Color.red);
		Joueur1 toi = new Joueur1("O", Color.blue);
		monde.remplirCase(new Position(1,1), moi);
		System.out.println(monde);

	}
}
