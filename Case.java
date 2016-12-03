package fr.univnantes.projet.monde;

import java.awt.Color;
import java.util.List;

import fr.univnantes.projet.Constante;

import java.util.ArrayList;

public class Case {
	private Position position_;
	private Color couleur_;
	private boolean etoile_;
	private Case pere_;
	private int nbDescendant_;
	private List<Case> fils_;
	
	public Case(Position position)
	{
		position_ = position;
		couleur_ = Color.white;
		etoile_ = false;
		pere_ = null;
		fils_ = new ArrayList<Case>();
		nbDescendant_ = 0;
	}

	public Position getPosition() {
		return position_;
	}

	public void setPosition(Position position) {
		position_ = position;
	}

	public Color getCouleur() {
		return couleur_;
	}

	public void setCouleur(Color couleur) {
		couleur_ = couleur;
	}

	public boolean getEtoile() {
		return etoile_;
	}

	public void estEtoile() {
		etoile_ = true;
	}
	public int getNbDescendant()
	{
		return nbDescendant_;
	}
	public Case getRacine()
	{
		Case parcours = this;
		while(parcours.getPere() != null)
		{

			parcours = parcours.getPere();

		}

		return parcours;
	}

	public Case getPere() {
		return pere_;
	}

	public void setPere(Case pere) {
		pere_ = pere;
	}

	public List<Case> getFils() {
		return fils_;
	}

	public void setFils(List<Case> fils) {
		fils_ = fils;
	}
	
	public void parcoursPrefixe()
	{

		System.out.println(position_.toString());

		if(fils_.size() != 0)
		{
			for(Case c : fils_){
				
				c.parcoursPrefixe();

			}
		}
	}

	public int parcoursEtoile(int cpt)
	{
		if(etoile_)
		{
			cpt++;
		}
		
		if(fils_.size() != 0)
		{
			for(Case c : fils_)
			{
				
				c.parcoursEtoile(cpt);

			}
		}
		return cpt;
	}
	
	public void analysePeripherieComposante(boolean[][] visitee, ArrayList<Case> peripherie, boolean[][] inaccessible,Case[][] carte)
	{
		visitee[position_.getX()][position_.getY()] = true;
		if(fils_.size()>0){
			
			for(Case fils : fils_){
				
				fils.analysePeripherieComposante(visitee,peripherie,inaccessible,carte);
				
			}		
		}
		
		for (int i = -1; i < 1; ++i)
		{
			for( int j = -1; i < 1; ++j)
			{	
				int x1 = position_.getX()+i; // abscisse de la case adjacente
				int y1 = position_.getY()+j; // ordonn�e de la case adjacente
				Case adj = carte[x1][y1]; // Case adjacente en cours d'analyse
				// on analyse la p�riph�rie de chaque case de la composante
				if((x1 >= 0) && (y1>=0) && (x1 <=Constante.N) && (y1<=Constante.N) && !visitee[x1][y1] && !inaccessible[x1][y1]){
					if(adj.getCouleur() == Color.white){
						peripherie.add(adj);
					} else {
						inaccessible[x1][y1] = true;
					}
				}
			}
		}
	}


	public boolean caseAdjacent(Case autre)
	{
    	boolean test = false;
    	if(position_.getX() == autre.getPosition().getX()-1 || position_.getX() == autre.getPosition().getX() || position_.getX() == autre.getPosition().getX()+1)
    	{
    		if(position_.getY() == autre.getPosition().getY()-1 || position_.getY() == autre.getPosition().getY() || position_.getY() == autre.getPosition().getY()+1)
    		{
    			if(!positionEgale(autre))
    			{
    				test = true;
    			}
    		}
    	}
    	return test;	
	}

	public void ajoutFils(Case c)
	{

		fils_.add(c);
		nbDescendant_ = nbDescendant_ + 1 + c.getNbDescendant();
		c.setPere(this);

	}
	
    public boolean positionEgale(Case autre){
    	return position_.getX() == autre.getPosition().getX() && position_.getY() == autre.getPosition().getY();
    }
	
}
