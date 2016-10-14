package fr.univnantes.projet.monde;

import java.util.ArrayList;
import java.lang.String;

import fr.univnantes.projet.Constante;

public class Monde
{
    /**
     * L'emplacement des choses sur une carte à deux dimensions du Monde
     */
	private Joueur1[][] carte_;

	/**
	 * Constructeur
     */
	public Monde(int taille)
	{
        carte_ = new Joueur1[Constante.N][Constante.N];
	}


	/**
     * Accesseur
     */
    public Joueur1[][] getCarte()
    {
        return carte_;
    }

    /**
     * Mutateur
     */
    public void setX(Joueur1[][] carte)
    {
        carte_=carte;
    }

    public void remplirCase(Position position, Joueur1 joueur)
    {
    	carte_[position.getX()][position.getY()]=joueur;
    }

    /**
     * Initialise le Monde
     */
    /*public void creationDuMonde(){
        creationPopulation();
        placementInitial();
    }*/

    @Override
	public String toString()
	{
		String str = "" ;
		for(int i = 0;i<taille_;++i)
		{
			for(int j = 0;j<taille_ ;++j)
			{
				str = str.concat(carte_[i][j].getPseudo());
			}
			str = str.concat("\n");
		}
		return str;
	}
}