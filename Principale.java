import java.util.*;
import java.awt.Color;

public class Principale 
{

	public static void main(String[] args)
	{
	
		Joueur1 moi = new Joueur1("X", Color.red);
		Joueur1 toi = new Joueur1("O", Color.blue);
		Monde test = new Monde(5);
		test.remplirCase(new Position(1,1), moi);
		System.out.println(test);

	}
}
