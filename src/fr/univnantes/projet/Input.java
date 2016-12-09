package fr.univnantes.projet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

import fr.univnantes.projet.AbandonException;

public final class Input {

	private static final PrintStream OUT = System.out;
	private static final InputStream IN = System.in;
	private static final PrintStream ERR = System.err;

	private Input() {
	}

	public static int lireEntierDepuisConsole(String question, String messageErreur, int mini, int maxi) {
		int n = 0;
		while (n == 0) {
			OUT.print(question);
			BufferedReader br = new BufferedReader(new InputStreamReader(IN));
			try {
				String s = br.readLine().trim();
				if ("fin".equalsIgnoreCase(s)) {
					throw new AbandonException();
				}
				n = Integer.parseInt(s);
				if (n < mini || n > maxi) {
					n = 0;
				}
			} catch (IOException e) {
				ERR.println(messageErreur);
			} catch (NumberFormatException e) {
				ERR.println(messageErreur);
			}
		}
		return n;
	}

	public static int lireLigneDepuisConsole(int n, String suffixe) {
		return Input.lireEntierDepuisConsole("ligne" + suffixe + "=", "Entrer un entier compris entre 1 et " + n, 1, n);
	}

	public static int lireColonneDepuisConsole(int n, String suffixe) {
		return Input.lireEntierDepuisConsole("colonne" + suffixe + "=", "Entrer un entier compris entre 1 et " + n, 1, n);
	}

}
