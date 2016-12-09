package fr.univnantes.projet.monde;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.univnantes.projet.Input;

import java.awt.Color;

public class Monde
{
	/**
	 * L'emplacement des joueurs sur une carte à deux dimensions du Monde
	 */
	private Case[][] carte_;
	private Joueur joueur1_;
	private Joueur joueur2_;
	private int n_;
	private int k_;

	/**
	 * Constructeur
	 */
	public Monde(Joueur joueur1, Joueur joueur2, int n, int k)
	{
		n_ = n;
		k_ = k;
		carte_ = new Case[n][n];
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
	
	public Joueur getJoueur1() 
	{
		return joueur1_;
	}


	public void setJoueur1(Joueur joueur1) 
	{
		joueur1_ = joueur1;
	}


	public Joueur getJoueur2() 
	{
		return joueur2_;
	}


	public void setJoueur2(Joueur joueur2) 
	{
		joueur2_ = joueur2;
	}
	
	public int getN() 
	{
		return n_;
	}


	public void setN(int n)
	{
		n_ = n;
	}

	public int getK() 
	{
		return k_;
	}


	public void setK(int k) 
	{
		k_ = k;
	}


	/**
	 * Initialise le Monde
	 */
	public void creationDuMonde()
	{
		initialiseCarte();
		placementInitial();
	}
	
	/**
	 * Initialise le plateau de jeu
	 */
	public void initialiseCarte() {
		for(int i = 0 ; i < n_ ; i++ )
		{
			for(int j = 0 ; j < n_; j++ )
			{
				carte_[j][i] = new Case(new Position(i,j));
			}
		}
	}
	
	/**
	 * Initialise le début du jeu
	 */
	public void placementInitial() 
	{
		int ligne, ligne2, colonne, colonne2;
		for(int i = 0 ; i<k_ ; i++)
		{
			do
			{
				ligne = (int)(Math.random() * (n_));
				colonne = (int)(Math.random() * (n_));
				
			}while(caseOccupee(new Position(colonne, ligne)));
			carte_[ligne][colonne].setCouleur(joueur1_.getCouleur()); 
			carte_[ligne][colonne].estEtoile();
			miseAJour(new Position(colonne, ligne), joueur1_);
						
			do
			{
				ligne2 = (int)(Math.random() * (n_));
				colonne2 = (int)(Math.random() * (n_));
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
				if(dansCarte(k, l))
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
	}
		
    public void colorerCase(Joueur joueur)
    {		
		int y = Input.lireLigneDepuisConsole(n_, "");
		int x = Input.lireColonneDepuisConsole(n_, "");
		x--;
		y--;
		Position position = new Position(x,y);
		if(dansCarte(x, y))
		{
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
		else
		{
			System.out.println("Cette case est hors carte, choissisez en une autre.");	
    		colorerCase(joueur);
		}
    }
    
    public void colorerCaseAleatoire(Joueur joueur)
    {		
    	int x,y;
		do
		{
			x = (int)(Math.random() * (n_));
			y = (int)(Math.random() * (n_));
			
		}while(caseOccupee(new Position(y, x)));
		Position position = new Position(x,y);
		carte_[y][x].setCouleur(joueur.getCouleur());
		miseAJour(position, joueur);
	    
    }
	
	public void afficheComposante()
	{

		int x = Input.lireLigneDepuisConsole(n_, "");
		int y = Input.lireColonneDepuisConsole(n_, "");
		x--;
		y--;
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

		int x = Input.lireLigneDepuisConsole(n_, "1");
		int y = Input.lireColonneDepuisConsole(n_, "1");
		x--;
		y--;
		Case case1 = carte_[x][y];
		x = Input.lireLigneDepuisConsole(n_, "2");
		y = Input.lireColonneDepuisConsole(n_, "2");
		x--;
		y--;
		Case case2 = carte_[x][y];
		
		if(case1.getCouleur() == case2.getCouleur())
		{

			if( case1.getRacine().equals(case2.getRacine()) )
			{
				System.out.println("il existe un chemin");
			} else {
				System.out.println("il sont pas relié");
			}

		} else {
			System.out.println("Les deux cases ne sont pas de la même couleur.");
		}
		
	}

	public int relierCasesMin()
	{
		// variables
	
		boolean bloque = false; // vrai si aucun chemin ne mène à la case finale
		boolean trouve = false; // vrai si la case finale est atteinte
		
		boolean[][] visitee = new boolean[n_][n_];
		ArrayList<Case> peripherie = new ArrayList<Case>();
		boolean[][] inaccessible = new boolean[n_][n_];

		int x = Input.lireLigneDepuisConsole(n_, "1");
		int y = Input.lireColonneDepuisConsole(n_, "1");
		x--;
		y--;
	
		Case c1 = carte_[x][y];

		int z = Input.lireLigneDepuisConsole(n_, "2");
		int t = Input.lireColonneDepuisConsole(n_, "2");
		z--;
		t--;
	
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
				if((x1 >= 0) && (y1>=0) && (x1 <=n_-1) && (y1<=n_-1)){
					
					Case adj = carte_[y1][x1]; // Case adjacente en cours d'analyse					
					
					if(adj.getCouleur() == c1.getCouleur()){
						
						adj = adj.getRacine();
						adj.analysePeripherieComposante(visitee, peripherie, inaccessible, carte_, n_);																				// on analyse les cases en périphérie
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
						if((x1 >= 0) && (y1>=0) && (x1 <=n_-1) && (y1<n_-1)){ 
							
							if(!visitee[y1][x1] && !inaccessible[y1][x1]){
						
							Case adj = carte_[y1][x1]; // Case adjacente en cours d'analyse
								if(adj.getCouleur() == c1.getCouleur()){
									
									adj = adj.getRacine();
									adj.analysePeripherieComposante(visitee, peripherie2, inaccessible, carte_, n_);
									
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

	public int nombreEtoiles()
	{
		int cpt = 0; // compteur du nombre d'étoiles

		int x = Input.lireLigneDepuisConsole(n_, "");
		int y = Input.lireColonneDepuisConsole(n_, "");
		x--;
		y--;

		Case c1 = carte_[x][y];
		c1 = c1.getRacine();			// en O(h(c1))

		return c1.parcoursEtoile(cpt);  // en têta de n avec n le nombre de noeuds
										// de la composante
	
	}
	
	public int nombreEtoiles(Case c)
	{

		int cpt = 0; // compteur du nombre d'étoiles
	
		c = c.getRacine();			// en O(h(c1))
	
		return c.parcoursEtoile(cpt);  // en têta de n avec n le nombre de noeuds
										// de la composante
		
	}

	public void afficherScores()
	{

		System.out.println("Score joueur 1 :");
		int score1 = 0;

		for (Case c : joueur1_.getComposante())
		{

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
	
	public int ScoreMax()
	{
		int score1 = 0;

		for (Case c : joueur1_.getComposante())
		{
			
			int score = nombreEtoiles(c);

			if(score1 < score )
			{

				score1 = score;

			}
		}

		int score2 = 0;

		for (Case c : joueur2_.getComposante())
		{

			int score = nombreEtoiles(c);

			if(score2 < score ){

				score2 = score;

			}			
		}
		return Math.max(score1,score2);
	}
	
	public boolean relieComposantes(Color couleur)
	{
		int y = Input.lireLigneDepuisConsole(n_, "");
		int x = Input.lireColonneDepuisConsole(n_, "");
		x--;
		y--;
		List<Case> composante = new ArrayList<Case>();
		if(carte_[y][x].getCouleur() == Color.white){
			for (int i = -1; i <= 1; ++i)
			{
				for( int j = -1; j <= 1; ++j)
				{
					System.out.println(i + " " + j);
					if(dansCarte(x+i,y+j))
					{
						if(carte_[y+j][x+i].getCouleur() == couleur)
						{
							System.out.println("test3");
							if(composante.size() != 0)
							{
								for(Case c : new ArrayList<Case>(composante))
								{
									System.out.println("test6");
									if(c.getRacine() != carte_[y+j][x+i].getRacine())
									{
										System.out.println("test5");
										composante.add(carte_[y+j][x+i]);
									}
								}
							}
							else
							{
								System.out.println("test4");
								composante.add(carte_[y+j][x+i]);
							}
						}
					}
				}	
			}
		}
		
			
		return (composante.size() >= 2);
	
	}

	public boolean caseOccupee(Position position)
	{
		return carte_[position.getY()][position.getX()].getCouleur() != Color.white;
	}
	
	public boolean dansCarte(int x, int y)
	{
		if(x >= 0 && y >= 0 && x < n_ && y < n_)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
				
	public void affichage(){
		for(int i = 0 ; i < n_ ; i++ )
		{
			for(int j = 0 ; j < n_ ; j++ )
			{
				System.out.print(carte_[i][j]);
			}
			System.out.println();
		}
	}

	
	public int getVainqueur(Joueur courant) 
	{
		// Déterminer s'il y a un vainqueur.
		for (Case c : courant.getComposante()) 
		{
			if (nombreEtoiles(c) == k_) {
				if(courant == joueur1_)
				{
					return 1;
				} 
				else
				{
					return 2;
				}
			}
		}
		if (toutRempli()) 
		{
			int maximum1 = 0;
			int maximum2 = 0;
			for (Case c : joueur1_.getComposante()) 
			{
				if(c.getNbDescendant() > maximum1)
				{
					maximum1 = c.getNbDescendant();
				}
			}
			for (Case c : joueur2_.getComposante()) 
			{
				if(c.getNbDescendant() > maximum2)
				{
					maximum2 = c.getNbDescendant();
				}
			}
			if(maximum1 == maximum2 || maximum1 > maximum2)
			{
				return 1;
			}
			else
			{
				return 2;
			}
		}
		return 0;
	}
	
	public boolean testJeuTermine(Joueur courant) {
		int vainqueur = getVainqueur(courant);
		if(vainqueur == 1)
		{
			System.out.println("Le joueur " + courant.getPseudo() + " a gagné la partie !");
			return true;
		}
		else if(vainqueur == 2)
		{
			System.out.println("Le joueur " + courant.getPseudo() + " a gagné la partie !");
			return true;
		}
		return false;
	}
	
	public boolean toutRempli() 
	{
		int nbCaseCouleur = 0;
		for (Case c : joueur1_.getComposante()) 
		{
			nbCaseCouleur = nbCaseCouleur + c.getNbDescendant() + 1;
		}

		for (Case c : joueur2_.getComposante()) 
		{
			nbCaseCouleur = nbCaseCouleur + c.getNbDescendant() + 1;
		}
		
		return nbCaseCouleur == n_*n_;
	}
	
}