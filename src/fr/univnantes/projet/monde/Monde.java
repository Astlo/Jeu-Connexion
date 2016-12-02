package fr.univnantes.projet.monde;

import java.lang.String;
import java.util.ArrayList;
import java.util.List;

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
    		System.out.println("Cette case est déjà occupée, choissisez en une autre.");
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

		if (parcours.getCouleur() != color.white)
		{			
			parcours = parcours.getRacine();

			parcours.parcoursPrefixe();
		}

	c.close();
	}

	public boolean existeCheminCases(Color couleur){

	Scanner c = new Scanner(System.in);		
	int x = c.nextInt();
	int y = c.nextInt();

	Case c1 = carte_[x][y];
	
	int x = c.nextInt();
	int y = c.nextInt();

	Case c2 = carte_[x][y];

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

	// gros boulot à finir Inondation demander à Ivan en cas d'oublie
	public int relierCasesMin()
	{

	Scanner c = new Scanner(System.in);		
	int x = c.nextInt();
	int y = c.nextInt();

	Case c1 = carte_[x][y];
	
	int z = c.nextInt();
	int t = c.nextInt();

	Case c2 = carte_[x][y];

		
	c.close();
	}

	public int nombreEtoiles(){

	int cpt = 0; // compteur du nombre d'étoiles

	Scanner c = new Scanner(System.in);		
	int x = c.nextInt();
	int y = c.nextInt();

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

		for (Case c : joueur2_.getComposante()){

			score = nombreEtoiles(c);

			if(score2 < score ){

				score2 = score;

			}
			
		}

		System.out.println( score2 + " étoiles reliées max");
	}

	public boolean relieComposantes(Color couleur){
	
	int liaisons = 0;
	Scanner c = new Scanner(System.in);		
	int x = c.nextInt();
	int y = c.nextInt();

	
		for (int = -1; i < 1; ++i)
		{
			for( int j = -1; i < 1; ++j)
			{
				if((x+i >= 0) && (y+j>=0) && (x+i <=Constante.N) && (y+j<=Constante.N)){
					if(carte_[x][y].getCouleur == couleur){

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
    
    
    
    public void miseAJourChemin(Position position, Joueur1 joueur)
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
    }
        
    
	public String toString()
	{
		String str = "" ;
		for(int i = 0;i<Constante.N;++i)
		{
			for(int j = 0;j<Constante.N ;++j)
			{
				str = str.concat(carte_[i][j]);
			}
			str = str.concat("\n");
		}
		return str;
	}
}
