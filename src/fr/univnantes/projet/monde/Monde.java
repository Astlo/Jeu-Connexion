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
	private int numTour_;

	/**
	 * Constructeur
	 */
	public Monde(Joueur joueur1, Joueur joueur2)
	{
		carte_ = new Case[Constante.N][Constante.N+2];
		joueur1_ = joueur1;
		joueur2_ = joueur2;
		numTour_ = 0;
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
	
	public Joueur getJoueur1() {
		return joueur1_;
	}


	public void setJoueur1(Joueur joueur1) 
	{
		joueur1_ = joueur1;
	}


	public Joueur getJoueur2() {
		return joueur2_;
	}


	public void setJoueur2(Joueur joueur2) 
	{
		joueur2_ = joueur2;
	}
	
	public int getNumTour() {
		return numTour_;
	}


	public void setNumTour(int numTour) {
		numTour_ = numTour;
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
			miseAJour(new Position(colonne, ligne), joueur1_);
						
			do
			{
				ligne2 = (int)(Math.random() * (Constante.N));
				colonne2 = (int)(Math.random() * (Constante.N));
			}while(caseOccupee(new Position(colonne2, ligne2)));
			carte_[ligne2][colonne2].setCouleur(joueur2_.getCouleur()); 
			carte_[ligne2][colonne2].estEtoile();
			miseAJour(new Position(colonne2, ligne2), joueur2_);
		}
	}
	
	public void miseAJour(Position position, Joueur joueur)
	{
		int i = position.getY();
		int j = position.getX();
		List<Case> casesCandidates = new ArrayList<Case>();
		
		for(int k = i-1 ; k <= i+1 ; k++ )
		{
			for(int l = j-1 ; l <= j+1 ; l++ )
			{
				if(k >= 0 && l >= 0 && k < Constante.N && l < Constante.N)
				{
					if(carte_[k][l].getCouleur() == carte_[i][j].getCouleur())
					{
						if(i != k || j != l)
						{
							casesCandidates.add(carte_[k][l]);
						}
					}
					
				}
			}
		}
		if(!casesCandidates.isEmpty())
		{
			Collections.sort(casesCandidates, new ComparatorCase());
			Case c = casesCandidates.get(0);	
			c.union(carte_[i][j], joueur);
			for(int m = 1 ; m < casesCandidates.size() ; m++)
			{
				c.union(casesCandidates.get(m).classe(), joueur);
			}
		}
		else
		{
			joueur.ajouterComposante(carte_[i][j]);
		}

		System.out.println(joueur.getComposante().size());
	}
		
    public void colorerCase(Joueur joueur)
    {
    	Scanner sc = new Scanner(System.in);		
		int x = sc.nextInt();
		int y = sc.nextInt();
		Position position = new Position(x,y);
    	if(caseOccupee(position))
    	{
			System.out.println("Cette case est déjà occupé, choissisez en une autre.");	

    		colorerCase(joueur);
       	}
    	else
    	{
       		carte_[y][x].setCouleur(joueur.getCouleur());
			miseAJour(position, joueur);
       	}
    }
    
    public void colorerCase(Position position, Joueur joueur)
    {
    	/*Scanner sc = new Scanner(System.in);		
		int x = sc.nextInt();
		int y = sc.nextInt();
		Position position = new Position(x,y);*/
    	if(caseOccupee(position))
    	{
			System.out.println("Cette case est déjà occupé, choissisez en une autre.");	

    		colorerCase(joueur);
       	}
    	else
    	{
       		carte_[position.getY()][position.getX()].setCouleur(joueur.getCouleur());
			miseAJour(position, joueur);
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

	public int relierCasesMin()
	{
		// variables
	
		boolean bloque = false; // vrai si aucun chemin ne mène à la case finale
		boolean trouve = false; // vrai si la case finale est atteinte
		
		boolean[][] visitee = new boolean[Constante.N][Constante.N];
		ArrayList<Case> peripherie = new ArrayList<Case>();
		boolean[][] inaccessible = new boolean[Constante.N][Constante.N];

		Scanner c = new Scanner(System.in);		
		int x = c.nextInt();
		int y = c.nextInt();
	
		Case c1 = carte_[y][x];

		int z = c.nextInt();
		int t = c.nextInt();
	
		c.close();
		
		int cpt = 0;
		
		// debut inondation
		
		visitee[y][x] = true;  // on indique la case de départ comme déjà visitée
		// et on commence a parcourir sa périphérie
		

		for (int i = -1; i < 2; ++i)
		{
			for( int j = -1; j < 2; ++j)
			{	
				int x1 = x+i; // abscisse de la case adjacente
				int y1 = y+j; // ordonnée de la case adjacente
				
				if(x1 == x && y1 == y){    // ne fait rien lorsque la boucle passe sur la case centrale
					
				} else
				if(x1 == z && y1 == t){
					trouve = true;			// si la case finale se trouve dans la périphérie, fin de l'exec et on renvoie le résultat
					return cpt;
				} else
				if((x1 >= 0) && (y1>=0) && (x1 <=Constante.N-1) && (y1<=Constante.N-1)){
					
					Case adj = carte_[y1][x1]; // Case adjacente en cours d'analyse					
					
					if(adj.getCouleur() == c1.getCouleur()){
						
						adj = adj.getRacine();
						adj.analysePeripherieComposante(visitee, peripherie, inaccessible, carte_);																				// on analyse les cases en périphérie
																					// de la case actuelle et on les place
					} else if (adj.getCouleur() == Color.white){					// dans les tableaux qui correspondent à
																					// leur statut
						peripherie.add(adj);										// si on rencontre une composante de la même couleur,
						visitee[y1][x1] = true;										// alors analysePeripherieComposante analyse toutes les cases
																					// en périphérie du bloc
					} else {
						
						inaccessible[y1][x1] = true;
						
					}
				}
			}
		}
		if(peripherie.size() == 0){
			
			bloque = true;
			
		} else {
			cpt++;
		}
		
		ArrayList<Case> peripherie2 = new ArrayList<Case>();
		while(!trouve && !bloque){

			for(Case caseperiph : peripherie) {
				
				for (int i = -1; i < 2; ++i)
				{
					for( int j = -1; j < 2; ++j)
					{	
						
						int x1 = caseperiph.getPosition().getX()+i; // abscisse de la case adjacente
						int y1 = caseperiph.getPosition().getY()+j; // ordonnée de la case adjacente

						if(x1 == z && y1 == t){
							trouve = true;			// si la case finale se trouve dans la périphérie, fin de l'exec et on renvoie le résultat
							return cpt;
						} else
						if (x1 == caseperiph.getPosition().getX() && y1 == caseperiph.getPosition().getY()){
							
						} else
						if((x1 >= 0) && (y1>=0) && (x1 <=Constante.N-1) && (y1<=Constante.N-1)){ 
							
							if(!visitee[y1][x1] && !inaccessible[y1][x1]){
						
							Case adj = carte_[y1][x1]; // Case adjacente en cours d'analyse
								if(adj.getCouleur() == c1.getCouleur()){
									
									adj = adj.getRacine();
									adj.analysePeripherieComposante(visitee, peripherie2, inaccessible, carte_);
									
								} else if (adj.getCouleur() == Color.white){
									
									peripherie2.add(adj);
									visitee[y1][x1] = true;
									
								} else {
									
									inaccessible[y1][x1] = true;
									
								}
							}
						}
					}	
				}
			}
			
			if(peripherie2.size() == 0){

				bloque = true;
				
			} else {
				
			peripherie = peripherie2;
			peripherie2 = new ArrayList<Case>();
			cpt++;
			
			}
		}
		if(!bloque){
			return cpt;
		} else {
			return -1;
		}
	}

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
	
	public int ScoreMax(){
		int score1 = 0;

		for (Case c : joueur1_.getComposante()){
			
			int score = nombreEtoiles(c);

			if(score1 < score ){

				score1 = score;

			}
		}

		int score2 = 0;

		for (Case c : joueur2_.getComposante()){

			int score = nombreEtoiles(c);

			if(score2 < score ){

				score2 = score;

			}			
		}
		return Math.max(score1,score2);
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