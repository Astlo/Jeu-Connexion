package fr.univnantes.projet.ig;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import fr.univnantes.projet.monde.Monde;
import fr.univnantes.projet.monde.Position;
/**
 * Classe dont les instances sont des fenêtres graphiques
 * dérivées de JFrame.
 */
public class Fenetre extends JFrame implements ActionListener{
	
	/**
	 * Construteur.
	 * @param titre Titre de la fenetre afficé dans le bandeau
	 * @param panel Contenu de la fenetre
	 */
	private JButton colorer = new JButton("colorerCase");
	private JButton composante = new JButton("afficheComposante");
	private JButton chemin = new JButton("existeCheminCases");
	private JButton relierCasesMin = new JButton("relierCasesMin");
	private JButton nbEtoiles = new JButton("nombreEtoiles");
	private JButton Score = new JButton("afficheScores");
	private JButton relieComposantes = new JButton("relieComposantes");
	private JButton abandon = new JButton("Abandonner(Alors, on loose ?)");
	
	
	public Fenetre(String titre, JPanel panel) {
		
		// instanciation de l'instance de JFrame et de son contenu


		super(titre);		
		
		Box b = new Box(BoxLayout.PAGE_AXIS);
		setLayout(new BorderLayout());
		getContentPane().add(panel, BorderLayout.CENTER);

		// paramétrage de la fenêtre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		setResizable(true);
		
		// ajout des boutons

		
		colorer.addActionListener(this);
		composante.addActionListener(this);
		chemin.addActionListener(this);
		relierCasesMin.addActionListener(this);
		nbEtoiles.addActionListener(this);
		Score.addActionListener(this);
		relieComposantes.addActionListener(this);
		abandon.addActionListener(this);
		
		b.add(colorer);
		b.add(composante);
		b.add(chemin);
		b.add(relierCasesMin);
		b.add(nbEtoiles);
		b.add(Score);
		b.add(relieComposantes);
		b.add(abandon);
		getContentPane().add(b, BorderLayout.EAST);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent arg0){
		
		if(arg0.getSource() == colorer){
			
			/*System.out.println(arg0);
			Scanner c = new Scanner(System.in);		
			int x = c.nextInt();
			int y = c.nextInt();
			colorerCase(new Position(x,y),);*/
		}
		if(arg0.getSource() == composante){
			
		}
		if(arg0.getSource() == chemin){
			
		}
		if(arg0.getSource() == relierCasesMin){
			
		}
		if(arg0.getSource() == nbEtoiles){
			
		}
		if(arg0.getSource() == Score){
			
		}
		if(arg0.getSource() == relieComposantes){
			
		}
		if(arg0.getSource() == abandon){
			
			
		}
	}

}