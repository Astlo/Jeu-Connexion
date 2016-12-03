package fr.univnantes.projet.monde;
import java.util.Comparator;

/**
 * Permet de comparer deux cases
 * @author LE BARS Yannis & HUNAULT Marion
 *
 */
public class ComparatorCase implements Comparator<Case> {
	
	@Override
	public int compare(Case c1, Case c2) {
		return Integer.compare(c2.getNbDescendant() , c1.getNbDescendant());
	}
}