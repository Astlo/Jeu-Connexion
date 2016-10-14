package fr.univnantes.projet.monde;

import java.lang.String;

import fr.univnantes.projet.Constante;

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
        carte_ = new String[Constante.N-1][Constante.N-1];
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

    public void remplirCase(Position position, Joueur1 joueur)
    {
    	carte_[position.getX()][position.getY()]=joueur.getPseudo();
    }

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