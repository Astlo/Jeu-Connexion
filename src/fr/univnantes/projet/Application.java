package fr.univnantes.projet;

//import fr.univnantes.projet.ig.Fenetre;
//import fr.univnantes.projet.ig.Grille;
import fr.univnantes.projet.monde.Chemin;
import fr.univnantes.projet.monde.Joueur1;
import fr.univnantes.projet.monde.Monde;
import fr.univnantes.projet.monde.Position;

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
		//monde.creationDuMonde();
		monde.creation();
		
		Joueur1 moi = new Joueur1("X", Color.red);
		Joueur1 toi = new Joueur1("O", Color.blue);
		monde.colorerCase(new Position(0,0), moi);
		monde.colorerCase(new Position(0,1), toi);
		monde.colorerCase(new Position(0,2), moi);
		monde.colorerCase(new Position(0,4), moi);
		monde.colorerCase(new Position(0,3), moi);
	//	monde.remplirCase(new Position(1,0), moi);
		//monde.remplirCase(new Position(1,1), moi);
		System.out.println(monde);
		for(Chemin chemin : moi.getLChemin()){

			System.out.println(chemin);
		}

	}
}
