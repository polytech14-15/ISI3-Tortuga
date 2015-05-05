package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.*;
import view.*;

public class TortugaController implements ActionListener  {

	private Tortue tortugaCourante;
	
	private SimpleLogo vue;
	private Jeu jeu;
	
	public TortugaController(SimpleLogo vue, Jeu jeu){
		this.vue = vue;
		this.jeu = jeu;
		
		this.tortugaCourante = new Tortue();
		tortugaCourante.addObserver(this.vue);
		
		this.jeu.addTortue(tortugaCourante);
		
		reset();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String c = e.getActionCommand();
		System.out.println("Command: " + c);
		// actions des boutons du haut
		if (c.equals("Avancer")) {
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
			reset();
//			effacer();
		}
		else if (c.equals("Quitter")){
			vue.quitter();
		} else if(c.equals("choixCouleur")){
			this.tortugaCourante.setColor(vue.getColorIndex());
		}
	}
	
	public void reset() {
		// on initialise la position de la tortue
		for (Tortue t : jeu.getTortues()){
			t.reset();
			t.setPosition(vue.getFeuille().getSize().width/2, vue.getFeuille().getSize().height/2);
		}
  	}
	
	// avancer de n pas
		public void avancer(int dist) {
			int newX = (int) Math.round(tortugaCourante.getX()+dist*Math.cos(tortugaCourante.ratioDegRad*tortugaCourante.getDirection()));
			int newY = (int) Math.round(tortugaCourante.getY()+dist*Math.sin(tortugaCourante.ratioDegRad*tortugaCourante.getDirection()));
			
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
