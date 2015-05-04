package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.*;
import view.*;

public class TortugaController implements ActionListener  {

	private Tortue tortugaCourante;
//	private ArrayList<Tortue> tortues;
	
	private SimpleLogo vue;
	
	public TortugaController(SimpleLogo vue){
		this.vue = vue;
//		tortues = new ArrayList<>();
		this.tortugaCourante = new Tortue(vue);
		tortugaCourante.addObserver(vue.getFeuille());
//		tortues.add(tortugaCourante);
		reset();
		
//		vue.getFeuille().setTortue(tortues);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String c = e.getActionCommand();

		// actions des boutons du haut
		if (c.equals("Avancer")) {
			System.out.println("command avancer");
			try {
				int v = Integer.parseInt(vue.getInputValue());
				avancer(v);
			} catch (NumberFormatException ex){
				System.err.println("ce n'est pas un nombre : " + vue.getInputValue());
			}
			
		}
		else if (c.equals("Droite")) {
			try {
				int v = Integer.parseInt(vue.getInputValue());
				droite(v);
			} catch (NumberFormatException ex){
				System.err.println("ce n'est pas un nombre : " + vue.getInputValue());
			}
		}
		else if (c.equals("Gauche")) {
			try {
				int v = Integer.parseInt(vue.getInputValue());
				gauche(v);
			} catch (NumberFormatException ex){
				System.err.println("ce n'est pas un nombre : " + vue.getInputValue());
			}
		}
//		else if (c.equals("Lever")) 
//			courante.leverCrayon();
//		else if (c.equals("Baisser"))
//			courante.baisserCrayon();
		// actions des boutons du bas
//		else if (c.equals("Proc1"))
//			proc1();
//		else if (c.equals("Proc2"))
//			proc2();
//		else if (c.equals("Proc3"))
//			proc3();
		else if (c.equals("Effacer")) {
			for (Tortue t : tortues){
				reset(t);
			}
			
		}
			effacer();
		else if (c.equals("Quitter"))
			quitter();

		feuille.repaint();
		
	}
	
	public void reset() {
		// on initialise la position de la tortue
		for (Tortue t : jeu.getTortues){
			t.reset();
		}
		
		// plus besoin de ça
//		crayon = true;
//		listSegments.clear();
  	}
	
	// avancer de n pas
		public void avancer(int dist) {
			int newX = (int) Math.round(tortugaCourante.getX()+dist*Math.cos(tortugaCourante.ratioDegRad*tortugaCourante.getDirection()));
			int newY = (int) Math.round(tortugaCourante.getY()+dist*Math.sin(tortugaCourante.ratioDegRad*tortugaCourante.getDirection()));
			
			// plus besoin de ça
//			if (crayon==true) {
//				Segment seg = new Segment();
//				
//				seg.ptStart.x = x;
//				seg.ptStart.y = y;
//				seg.ptEnd.x = newX;
//				seg.ptEnd.y = newY;
//				seg.color = decodeColor(coul);
//		
//				listSegments.add(seg);
//			}
			
			tortugaCourante.setPosition(newX, newY);
		}

		// aller a droite
		public void droite(int ang) {
			tortugaCourante.setDirection((tortugaCourante.getDirection() + ang) % 360);
		}

		// aller a gauche
		public void gauche(int ang) {
			tortugaCourante.setDirection((tortugaCourante.getDirection() - ang) % 360);
		}

	
}
