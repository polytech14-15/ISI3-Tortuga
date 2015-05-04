package view;

//import java.awt.*;

import javax.swing.*;

import model.Jeu;
import model.Tortue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.*;
import java.util.*;
import java.io.*;

/**
 * Titre :        Logo
 * Description :  Un exemple de programme graphique utilisant la celebre Tortue Logo
 * Copyright :    Copyright (c) 2000
 * Societe :      LIRMM
 * @author J. Ferber
 * @version 2.0
 */

public class FeuilleDessin extends JPanel implements Observer{
	
	private Jeu jeu;
	private List<Tortue> tortues; // la liste des tortues enregistrees
	
	public FeuilleDessin(Jeu jeu) {
		this.jeu = jeu;
	}

//	public void addTortue(Tortue o) {
//		tortues.add(o);
//	}
	
	public void setTortues(List<Tortue> tortues){
		this.tortues = tortues;
	}

	public void reset() {
		for (Iterator it = tortues.iterator();it.hasNext();) {
			Tortue t = (Tortue) it.next();
			t.reset();
		}
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
			drawTurtle(g, t);
		}
	}

	public void drawTurtle (Graphics graph, Tortue t) {
		if (graph==null)
			return;
		
		// Dessine les segments
//		for(Iterator it = listSegments.iterator();it.hasNext();) {
//			Segment seg = (Segment) it.next();
//			seg.drawSegment(graph);
//		}

		//Calcule les 3 coins du triangle a partir de
		// la position de la tortue p
		Point p = new Point(t.getX(),t.getY());
		Polygon arrow = new Polygon();

		//Calcule des deux bases
		//Angle de la droite
		double theta=Tortue.ratioDegRad*(-t.getDirection());
		//Demi angle au sommet du triangle
		double alpha=Math.atan( (float)Tortue.rb / (float)Tortue.rp );
		//Rayon de la fleche
		double r=Math.sqrt( Tortue.rp*Tortue.rp + Tortue.rb*Tortue.rb );
		//Sens de la fleche

		//Pointe
		Point p2=new Point((int) Math.round(p.x+r*Math.cos(theta)),
						 (int) Math.round(p.y-r*Math.sin(theta)));
		arrow.addPoint(p2.x,p2.y);
		arrow.addPoint((int) Math.round( p2.x-r*Math.cos(theta + alpha) ),
		  (int) Math.round( p2.y+r*Math.sin(theta + alpha) ));

		//Base2
		arrow.addPoint((int) Math.round( p2.x-r*Math.cos(theta - alpha) ),
		  (int) Math.round( p2.y+r*Math.sin(theta - alpha) ));

		arrow.addPoint(p2.x,p2.y);
		graph.setColor(Color.green);
		graph.fillPolygon(arrow);
    }
	
	@Override
	public void update(Observable o, Object arg) {
		this.setTortues(jeu.getTortues());
		this.repaint();
	}
}
