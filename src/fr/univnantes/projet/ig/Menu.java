package fr.univnantes.projet.ig;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.univnantes.projet.Application;
import fr.univnantes.projet.monde.Monde;
import fr.univnantes.projet.monde.Position;
/**
 * Classe dont les instances sont des fenêtres graphiques
 * dérivées de JFrame.
 */
public class Menu extends JFrame implements ActionListener{
	
	/**
	 * Construteur.
	 * @param titre Titre de la fenetre afficé dans le bandeau
	 * @param panel Contenu de la fenetre
	 */
	private JButton joueOrdiHumain = new JButton("joueOrdiHumain");
	private JButton joueDeuxHumains = new JButton("joueDeuxHumains");
	private Application app_;
	
	public Menu(String titre, JPanel panel, Application app) 
	{
		
		// instanciation de l'instance de JFrame et de son contenu

		
		super(titre);		
		app_ = app;
		Box b = new Box(BoxLayout.PAGE_AXIS);
		setLayout(new BorderLayout());
		getContentPane().add(panel, BorderLayout.CENTER);

		// paramétrage de la fenêtre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		setResizable(true);
		
		// ajout des boutons

		
		joueOrdiHumain.addActionListener(this);
		joueDeuxHumains.addActionListener(this);
		
		b.add(joueOrdiHumain);
		b.add(joueDeuxHumains);
		
		getContentPane().add(b, BorderLayout.EAST);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent arg0)
	{
		
		if(arg0.getSource() == joueOrdiHumain)
		{
			app_.joueOrdiHumain();
			setVisible(false);
		}
		if(arg0.getSource() == joueDeuxHumains)
		{
			app_.joueDeuxHumains();
			setVisible(false);
			
		}
	}

}