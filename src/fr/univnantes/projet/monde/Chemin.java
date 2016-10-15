package fr.univnantes.projet.monde;

import java.util.ArrayList;
import java.util.List;

import fr.univnantes.projet.Constante;

public class Chemin {
	 
	private Position identifiant_;
	private List<Position> classe_;

	public Chemin(Position identifiant){
		identifiant_ = identifiant;
		classe_ = new ArrayList<Position>();
		classe_.add(identifiant);
	}

	public Position getIdentifiant() {
		return identifiant_;
	}
	
	/**
	 * Accesseur de la population au rang i
	 * @param i Le ième rang
	 * @return La ième position de classe_
	 */
	public Position getElement(int i){
		return classe_.get(i);
	}

	public int nbElement()
	{
		return classe_.size();
	}
	
	public void setIdentifiant(Position identifiant) {
		identifiant_ = identifiant;
	}

	public List<Position> getClasse() {
		return classe_;
	}

	public void setClasse(List<Position> classe) {
		classe_ = classe;
	}
	
	public void ajouterPosition(Position position)
	{
		classe_.add(position);
	}
	
	public void supprimerPosition(Position position)
	{
		classe_.remove(position);
	}
	
	
	@Override
	public String toString(){
		String str = identifiant_.toString() ;
		str = str.concat("(");
		for (Position position : classe_) 
		{
			str = str.concat(position.toString());
			str = str.concat(", ");
		}
		str = str.concat(")");
		return str;
	}
}
