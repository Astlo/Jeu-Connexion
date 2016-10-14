import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Classe dont les instances sont des fenêtres graphiques
 * dérivées de JFrame.
 */
public class Fenetre extends JFrame {
	
	/**
	 * Construteur.
	 * @param titre Titre de la fenêtre afficé dans le bandeau
	 * @param panel Contenu de la fenêtre
	 */
	public Fenetre(String titre, JPanel panel) {
		// instanciation de l'instance de JFrame et de son contenu
		super(titre);
		getContentPane().add(panel);

		// paramétrage de la fenêtre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		setResizable(false);
		setLocationRelativeTo(null);

		// affichage
		pack();
		setVisible(true);
	}

}