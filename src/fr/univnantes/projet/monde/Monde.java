package fr.univnantes.projet.monde;

import java.lang.String;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import fr.univnantes.projet.Constante;

import java.awt.Color;

public class Monde
{
	/**
	 * L'emplacement des joueurs sur une carte à deux dimensions du Monde
	 */
	private Case[][] carte_;
	private Joueur joueur1_;
	private Joueur joueur2_;

	/**
	 * Constructeur
	 */
	public Monde(Joueur joueur1, Joueur joueur2)
	{
		carte_ = new Case[Constante.N][Constante.N+2];
		joueur1_ = joueur1;
		joueur2_ = joueur2;
	}


	/**
	 * Accesseur
	 */
	public Case[][] getCase()
	{
		return carte_;
	}
	
	public Case getOneCase(Position position)
	{
		return carte_[position.getY()][position.getX()];
	}
	
	public Case getOneCase(int x, int y)
	{
		return carte_[y][x];
	}

	/**
	 * Mutateur
	 */
	public void setCase(Case[][] carte)
	{
		carte_=carte;
	}
	
	/**
	 * Initialise le Monde
	 */
	public void creationDuMonde(){
		initialiseCarte();
		placementInitial();
	}
	
	/**
	 * Initialise le plateau de jeu
	 */
	public void initialiseCarte() {
		for(int i = 0 ; i < Constante.N ; i++ )
		{
			for(int j = 0 ; j < Constante.N ; j++ )
			{
				carte_[j][i] = new Case(new Position(i,j));
			}
		}
	}
	
	/**
	 * Initialise le début du jeu
	 */
	public void placementInitial() {
		int ligne, ligne2, colonne, colonne2;
		for(int i = 0 ; i<Constante.K ; i++)
		{
			do
			{
				ligne = (int)(Math.random() * (Constante.N));
				colonne = (int)(Math.random() * (Constante.N));
				
			}while(caseOccupee(new Position(colonne, ligne)));
			carte_[ligne][colonne].setCouleur(joueur1_.getCouleur()); 
			carte_[ligne][colonne].estEtoile();
			//miseAJour(new Position(colonne, ligne));
			//joueur1_.ajouterComposante(carte_[ligne][colonne]);
						
			do
			{
				ligne2 = (int)(Math.random() * (Constante.N));
				colonne2 = (int)(Math.random() * (Constante.N));
			}while(caseOccupee(new Position(colonne2, ligne2)));
			carte_[ligne2][colonne2].setCouleur(joueur2_.getCouleur()); 
			carte_[ligne2][colonne2].estEtoile();
			//miseAJour(new Position(colonne, ligne));
			//joueur2_.ajouterComposante(carte_[ligne][colonne]);
		}
	}
	
	public void miseAJour(Position position)
	{
		int i = position.getY();
		int j = position.getI();
		List<Case> casesCandidates = new ArrayList<Case>();
		
		for(int k = i-1 ; k <= i+1 ; k++ )
		{
			for(int l = j-1 ; l <= j+1 ; l++ )
			{
				if(carte_[k][l].getCouleur() == carte_[i][j].getCouleur() && i != k && j != l){
					casesCandidates.add(carte_[k][l]);
				}
			}
		}
		Collections.sort(casesCandidates, new ComparatorCase());
		Case c = casesCandidates.get(0);
		for(int m = 1 ; m < casesCandidates.size() ; m++)
		{
			c.classe();
		}
	}
	
	public boolean aCaseAdjacent()
	{
		boolean test = false;
		
		for(int i = 0 ; i < Constante.N ; i++ )
		{
			for(int j = 0 ; j < Constante.N ; j++ )
			{
				if(carte_[i][j]){
					
				}
				carte_[i][j] = new Case(new Position(i,j));
			}
		}
		
		return true;
	}
	
	
	public void choixCase(Position position, Joueur joueur)
	{
		if(caseOccupee(position))
		{
			System.out.println("Cette case est déjà occupé, choissisez en une autre.");
			// demandez autre position
			Position autre;
			choixCase(autre, joueur);
		}
		else
		{
			colorerCase(position, joueur);
			joueur.miseAJourChemin(carte_[position.getX()][position.getY()]);
		}
	}
	
	public void colorerCase(Position position, Joueur joueur)
	{
		if(caseOccupee(position))
		{
			System.out.println("Cette case est déjà occupé, choissisez en une autre.");
			// demandez autre position
			//colorerCase(autre ,joueur);
		}
		else
		{
			carte_[position.getY()][position.getX()].setCouleur(joueur.getCouleur());
			
		}
	}
	
	public void afficheComposante()
	{
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		int y = sc.nextInt();
		sc.close();
		
		Case parcours = carte_[x][y];
		
		if(parcours.getCouleur() != Color.white)
		{
			parcours = parcours.getRacine();

			parcours.parcoursPrefixe();
		} 
		else
		{
			System.out.println("ça appartient a aucune composante");
		}
		
	}
	
	public void existeCheminCases()
	{
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		int y = sc.nextInt();
		Case case1 = carte_[x][y];
		x = sc.nextInt();
		y = sc.nextInt();
		Case case2 = carte_[x][y];
		sc.close();
		
		if(case1.getCouleur() == case2.getCouleur())
		{

			if( case1.getRacine().equals(case2.getRacine()) )
			{
				System.out.println("il existe un chemin");
			} else {
				System.out.println("il sont pas relié");
			}

		} else {
			System.out.println("ça appartient a aucune composante");
		}
		
	}

		// gros boulot à finir Inondation demander à Ivan en cas d'oublie
	/*public int relierCasesMin()
	{

		Scanner sc = new Scanner(System.in);		
		int x = sc.nextInt();
		int y = sc.nextInt();

		Case c1 = carte_[x][y];
		
		int z = sc.nextInt();
		int t = sc.nextInt();

		Case c2 = carte_[x][y];

			
		sc.close();
	}*/

	public int nombreEtoiles(){
		int cpt = 0; // compteur du nombre d'étoiles

		Scanner sc = new Scanner(System.in);		
		int x = sc.nextInt();
		int y = sc.nextInt();
		sc.close();

		Case c1 = carte_[x][y];
		c1 = c1.getRacine();			// en O(h(c1))

		return c1.parcoursEtoile(cpt);  // en têta de n avec n le nombre de noeuds
										// de la composante
	
	}
	
	public int nombreEtoiles(Case c){

		int cpt = 0; // compteur du nombre d'étoiles
	
		c = c.getRacine();			// en O(h(c1))
	
		return c.parcoursEtoile(cpt);  // en têta de n avec n le nombre de noeuds
										// de la composante
		
	}

	public void afficherScores()
	{

		System.out.println("Score joueur 1 :");
		int score1 = 0;

		for (Case c : joueur1_.getComposante()){

			int score = nombreEtoiles(c);

			if(score1 < score ){

				score1 = score;

			}
		}

		System.out.println( score1 + " étoiles reliées max");


		System.out.println("Score joueur 2 :");

		int score2 = 0;
		int score;
		for (Case c : joueur2_.getComposante()){

			score = nombreEtoiles(c);

			if(score2 < score ){

				score2 = score;

			}
			
		}

		System.out.println( score2 + " étoiles reliées max");
	}
	
	public boolean relieComposantes(Color couleur)
	{
	
		int liaisons = 0;
		Scanner sc = new Scanner(System.in);		
		int x = sc.nextInt();
		int y = sc.nextInt();
		sc.close();
		
			for (int i = -1; i < 1; ++i)
			{
				for( int j = -1; i < 1; ++j)
				{
					if((x+i >= 0) && (y+j>=0) && (x+i <=Constante.N) && (y+j<=Constante.N)){
						if(carte_[x][y].getCouleur() == couleur){

							liaisons++;
						}
					}
				}	
			}
			
		return (liaisons >= 2);
	
	}

	public boolean caseOccupee(Position position)
	{
		return carte_[position.getY()][position.getX()].getCouleur() != Color.white;
	}
	
	public void miseAJourC(Case c, Joueur joueur)
	{
		for(int i = c.getPosition().getX()-1; i<c.getPosition().getX()+1; ++i)
		{
			for(int j = c.getPosition().getY()-1; j<c.getPosition().getY()+1; ++j)
			{
				if(i < 0 || j < 0 || i >= Constante.N || j >= Constante.N)
				{
					if(carte_[i][j].getCouleur() == c.getCouleur() && carte_[i][j] != c)
					{
						
					}
				}
			}
		}
	}
		
	/*public Position donneCaseVideAleatoire(){
		
	}*/
	
	public void affichage(){
		for(int i = 0 ; i < Constante.N ; i++ )
		{
			for(int j = 0 ; j < Constante.N ; j++ )
			{
				System.out.print(carte_[i][j]);
			}
			System.out.println();
		}
	}
	
}