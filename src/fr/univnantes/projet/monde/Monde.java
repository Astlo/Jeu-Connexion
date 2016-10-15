package fr.univnantes.projet.monde;

import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import fr.univnantes.projet.Constante;

import java.awt.Color;

public class Monde
{
    /**
     * L'emplacement des choses sur une carte à deux dimensions du Monde
     */
	private String[][] carte_;

	/**
	 * Constructeur
     */
	public Monde()
	{
        carte_ = new String[Constante.N][Constante.N];
	}


	/**
     * Accesseur
     */
    public String[][] getCarte()
    {
        return carte_;
    }

    /**
     * Mutateur
     */
    public void setX(String[][] carte)
    {
        carte_=carte;
    }

    public void colorerCase(Position position, Joueur1 joueur)
    {
    	if(carte_[position.getY()][position.getX()]==".")
    		//if(carte_[position.getY()][position.getX()]==Color.white)
    	{
    		carte_[position.getY()][position.getX()]=joueur.getPseudo();
    		//carte_[position.getY()][position.getX()]=joueur.getCouleur();
    		miseAJourChemin(position,joueur);
       	}
    	else
    	{
    		boolean test = false;
    		for(Chemin chemin : joueur.getLChemin())
    		{
    			
    			for(Position autre : chemin.getClasse())
    			{
    				
    				if(position.positionAdjacente(autre))
    				{
    					test = true;
    					
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
				for (Position autre : joueur.getChemin(k).getClasse()) 
				{
					if(position.positionAdjacente(autre))
					{
						for(Position bis : joueur.getChemin(k).getClasse())
						{
							joueur.getChemin(i-1).ajouterPosition(bis);
						}
					
						joueur.supprimerChemin(joueur.getChemin(k));
					}
				}
			
			}
		}
    }
        
    /*public Position donneCaseVideAleatoire(){
    	
    }*/
    
    /**
     * Initialise le Monde
     */
    /*public void creationDuMonde(){
        creation();
        placementInitial();
    }*/
    
    public void creation()
    {
        for(int i = 0;i<Constante.N;++i)
        {
            for(int j = 0;j<Constante.N ;++j)
            {
                carte_[i][j]=".";
            }
        }
    }

    @Override
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