package fr.univnantes.projet.monde;

import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.univnantes.projet.Constante;

import java.awt.Color;

public class Monde
{
    /**
     * L'emplacement des joueurs sur une carte Ã  deux dimensions du Monde
     */
	private Case[][] carte_;
	private Joueur joueur1_;
	private Joueur joueur2_;

	/**
	 * Constructeur
     */
	public Monde(Joueur joueur1, Joueur joueur2)
	{
        carte_ = new Case[Constante.N][Constante.N];
        joueur1_ = joueur1;
        joueur2_ = joueur2;
	}


	/**
     * Accesseur
     */
    public Case[][] geCarte()
    {
        return carte_;
    }
    
    public Case getOneCase(Position position)
    {
    	return carte_[position.getX()][position.getY()];
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
				carte_[i][j] = new Case(new Position(i,j));
			}
		}
	}
	
	/**
	 * Initialise le dÃ©but du jeu
	 */
	public void placementInitial() {
		int ligne, ligne2, colonne, colonne2;
		for(int i = 0 ; i<Constante.K ; i++)
		{
			do
			{
				ligne = (int)(Math.random() * (Constante.N));
				colonne = (int)(Math.random() * (Constante.N));
			}while(caseOccupee(new Position(ligne, colonne)));
			carte_[ligne][colonne].setCouleur(joueur1_.getCouleur()); 
			carte_[ligne][colonne].estEtoile();
			
			joueur1_.ajouterComposante(carte_[ligne][colonne]);
						
			do
			{
				ligne2 = (int)(Math.random() * (Constante.N));
				colonne2 = (int)(Math.random() * (Constante.N));
			}while(caseOccupee(new Position(ligne2, colonne2)));
			carte_[ligne][colonne].setCouleur(joueur2_.getCouleur()); 
			carte_[ligne][colonne].estEtoile();
			joueur2_.ajouterComposante(carte_[ligne][colonne]);
		}
	}
	
/*	public boolean aCaseAdjacent()
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
    		System.out.println("Cette case est dÃ©jÃ  occupÃ©, choissisez en une autre.");
    		// demandez autre position
    		Position autre;
    		choixCase(autre, joueur);
    	}
        else
        {
    		colorerCase(position, joueur);
    		joueur.miseAJourChemin(carte_[position.getX()][position.getY()]);
       	}
    }*/

    public void colorerCase(Position position, Joueur joueur)
    {
    	if(caseOccupee(position))
    	{
    		System.out.println("Cette case est dÃ©jÃ  occupÃ©e, choissisez en une autre.");
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
		Scanner c = new Scanner(System.in);		
		int x = c.nextInt();
		int y = c.nextInt();

		Case parcours = carte_[x][y];

		if (parcours.getCouleur() != Color.white)
		{			
			parcours = parcours.getRacine();

			parcours.parcoursPrefixe();
		}

	c.close();
	}

	public boolean existeCheminCases(Color couleur){

	Scanner sc = new Scanner(System.in);		
	int x = sc.nextInt();
	int y = sc.nextInt();

	Case c1 = carte_[x][y];
	
	x = sc.nextInt();
	y = sc.nextInt();

	Case c2 = carte_[x][y];
	
	sc.close();
	
	if(c2.getCouleur() == couleur && c1.getCouleur() == couleur)
	{

		if( c1.getRacine().equals(c2.getRacine()) )
		{
			return true;
		} else {

			return false;
		}

	} else {
			
		return false;

	}
	}

	// gros boulot Ã  finir Inondation demander Ã  Ivan en cas d'oublie
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
	
		Case c1 = carte_[x][y];
		
		int z = c.nextInt();
		int t = c.nextInt();
	
		c.close();
		
		int cpt = 0;
		
		// debut inondation
		
		visitee[x][y] = true;  // on indique la case de départ comme déjà visitée
		// et on commence a parcourir sa périphérie
		for (int i = -1; i < 1; ++i)
		{
			for( int j = -1; i < 1; ++j)
			{	
				int x1 = x+i; // abscisse de la case adjacente
				int y1 = y+j; // ordonnée de la case adjacente
				Case adj = carte_[x1][y1]; // Case adjacente en cours d'analyse
				
				if(x1 == z && y1 == t){			// si la case finale se trouve dans le périphérie, fin de l'exec et on renvoie le résultat
					return cpt;
				} else
				if((x1 >= 0) && (y1>=0) && (x1 <=Constante.N) && (y1<=Constante.N)){
					
					if(adj.getCouleur() == c1.getCouleur()){
						
						adj.getRacine();
						adj.analysePeripherieComposante(visitee, peripherie, inaccessible, carte_);																				// on analyse les cases en périphérie
																					// de la case actuelle et on les places
					} else if (adj.getCouleur() == Color.white){					// dans les tableaux qui correspondent à
																					// leur statut
						peripherie.add(adj);										// si on rencontre une composante de la même couleur,
						visitee[x1][y1] = true;										// alors analysePeripherieComposante analyse toutes les cases
																					// en périphérie du bloc
					} else {
						
						inaccessible[x1][y1] = true;
						
					}
				}
			}
		}
		ArrayList<Case> peripherie2 = new ArrayList<Case>();
		while(!trouve && !bloque){
		
			for(Case caseperiph : peripherie) {
				for (int i = -1; i < 1; ++i)
				{
					for( int j = -1; i < 1; ++j)
					{	
						int x1 = caseperiph.getPosition().getX()+i; // abscisse de la case adjacente
						int y1 = caseperiph.getPosition().getY()+j; // ordonnée de la case adjacente
						Case adj = carte_[x1][y1]; // Case adjacente en cours d'analyse
						if((x1 >= 0) && (y1>=0) && (x1 <=Constante.N) && (y1<=Constante.N) && !visitee[x1][y1] && !inaccessible[x1][y1]){
							
							if(adj.getCouleur() == c1.getCouleur()){
								
								adj.getRacine();
								adj.analysePeripherieComposante(visitee, peripherie2, inaccessible, carte_);
								
							} else if (adj.getCouleur() == Color.white){
								
								peripherie2.add(adj);
								visitee[x1][y1] = true;
								
							} else {
								
								inaccessible[x1][y1] = true;
								
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

	int cpt = 0; // compteur du nombre d'Ã©toiles

	Scanner c = new Scanner(System.in);		
	int x = c.nextInt();
	int y = c.nextInt();

	Case c1 = carte_[x][y];
	c1 = c1.getRacine();			// en O(h(c1))

	c.close();
	return c1.parcoursEtoile(cpt);  // en teta de n avec n le nombre de noeuds
									// de la composante
	
	
	}

	public int nombreEtoiles(Case c){

	int cpt = 0; // compteur du nombre d'étoiles

	c = c.getRacine();			// en O(h(c1))

	return c.parcoursEtoile(cpt);  // en tÃªta de n avec n le nombre de noeuds
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

		System.out.println( score1 + " Ã©toiles reliÃ©es max");


		System.out.println("Score joueur 2 :");

		int score2 = 0;

		for (Case c : joueur2_.getComposante()){

			int score = nombreEtoiles(c);

			if(score2 < score ){

				score2 = score;

			}
			
		}

		System.out.println( score2 + " Ã©toiles reliÃ©es max");
	}

	public boolean relieComposantes(Color couleur){
	
	int liaisons = 0;
	Scanner c = new Scanner(System.in);		
	int x = c.nextInt();
	int y = c.nextInt();

	
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
		c.close();
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
    
    
    
 /*   public void miseAJourChemin(Position position, Joueur1 joueur)
    {
		int i = 0;
		boolean test = false;
		while(i<joueur.nbChemin() && test == false)
		{
			int j = 0;
			Chemin chemin = joueur.getChemin(i);
			while(j<chemin.nbElement() && test == false)
			{

				//System.out.println(i+ " "+j);
				if(position.positionAdjacente(chemin.getElement(j)))
				{
					chemin.ajouterPosition(position);
					test = true;
				}
				++j;
			}
			++i;
		}
		if(test == false)
		{
			joueur.ajouterChemin(new Chemin(position));
			//ajouter un chemin avec la position
		}
		else
		{
			List<Chemin> copie = new ArrayList<Chemin>(joueur.getLChemin());
			for(int k = i ; k<copie.size() ; ++k)
			{
				if(joueur.getChemin(i-1).cheminAdjacent(joueur.getChemin(k), position))
				{
					joueur.getChemin(i-1).cheminFusion(joueur.getChemin(k), position);
					joueur.supprimerChemin(joueur.getChemin(k));
				}			
			}
		}
    }*/
        
    
	public String toString()
	{
		String str = "" ;
		for(int i = 0;i<Constante.N;++i)
		{
			for(int j = 0;j<Constante.N ;++j)
			{
				str = str.concat(carte_[i][j].getPosition().toString());
			}
			str = str.concat("\n");
		}
		return str;
	}
}
