import java.awt.Color;
import java.util.ArrayList;

class Joueur2 implements Joueur {

	private Color couleur_;
	private ArrayList<Position> chemin_;


	public Joueur2(){

		couleur_ = Color.RED;
		chemin_ = new ArrayList<Case>();

	}

	@Override
	public String toString()
	{
		return "O";
	}
}

//A t'on vraiment besoin de deux joueurs c'est le meme objet, la couleur suffira de la choisir lors du constructeur