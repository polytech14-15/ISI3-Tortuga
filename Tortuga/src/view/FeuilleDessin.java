package view;

import javax.swing.*;
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

	public static final int FEUILLE_WIDTH = 600;
	public static final int FEUILLE_HEIGHT = 400;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6111575103973860248L;
	
	private List<IVueTortue> tortues; // la liste des tortues enregistrees
	
	public FeuilleDessin(){
		this.tortues = new ArrayList<IVueTortue>(0);
		reset();
	}
	
	public void addTortue(IVueTortue tortue){
			this.tortues.add(tortue);
	}
	
	public void reset() {
		this.tortues.clear();
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
		for (IVueTortue t : tortues) {
			t.draw(g);
		}
	}
}
