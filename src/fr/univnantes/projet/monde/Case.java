package fr.univnantes.projet.monde;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

public class Case {
	private Position position_;
	private Color couleur_;
	private boolean etoile_;
	private Case pere_;
	private List<Case> fils_;
	
	public Case(Position position)
	{
		position_ = position;
		couleur_ = Color.white;
		etoile_ = false;
		pere_ = null;
		fils_ = new ArrayList<Case>();
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
	
    public boolean positionEgale(Case autre){
    	return position_.getX() == autre.getPosition().getX() && position_.getY() == autre.getPosition().getY();
    }
	
}
