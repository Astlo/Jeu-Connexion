package fr.univnantes.projet.monde;

import java.awt.Color;

public class Joueur1
{

	private String pseudo_;
	private Color couleur_;
	//private ArrayList<Position> chemin_;

	/**
	 * Constructeur
     */
	public Joueur1(String pseudo, Color couleur)
	{
		pseudo_ = pseudo;
		couleur_ = couleur;
		//chemin_ = new ArrayList<Position>();

	}

	/**
     * Accesseur
     */
    public String getPseudo()
    {
        return pseudo_;
    }

    public Color getCouleur()
    {
    	return couleur_;
    }

    /**
     * Mutateur
     */
    public void setPseudo(String pseudo)
    {
        pseudo_=pseudo;
    }

    public void setCouleur(Color couleur)
    {
    	couleur_=couleur;
    }

	@Override
	public String toString()
	{
		return getPseudo();
	}

}