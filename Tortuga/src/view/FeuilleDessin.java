package view;

//import java.awt.*;

import javax.swing.*;

import model.Jeu;
import model.Tortue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.*;

/**
 * Titre :        Logo
 * Description :  Un exemple de programme graphique utilisant la celebre Tortue Logo
 * Copyright :    Copyright (c) 2000
 * Societe :      LIRMM
 * @author J. Ferber
 * @version 2.0
 */

public class FeuilleDessin extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6111575103973860248L;
	
	private Jeu jeu;
	private List<Tortue> tortues; // la liste des tortues enregistrees
	
	public FeuilleDessin(Jeu jeu) {
		this.jeu = jeu;
	}

	public void setTortues(List<Tortue> tortues){
		this.tortues = tortues;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Color c = g.getColor();
		
		Dimension dim = getSize();
		g.setColor(Color.white);
		g.fillRect(0,0,dim.width, dim.height);
		g.setColor(c);

		showTurtles(g);
	}
	
	public void showTurtles(Graphics g) {
		for(Iterator it = tortues.iterator();it.hasNext();) {
			Tortue t = (Tortue) it.next();
			t.draw(g);
		}
	}
}
