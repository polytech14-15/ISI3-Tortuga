package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

import model.*;
import view.*;

public class TortugaController implements ActionListener, MouseListener  {
	
	private static final int MARGIN_ERROR = 10;

	private Tortue tortugaCourante;
	private TortueBalle ball;
	
	private SimpleLogo vue;
	private Jeu jeu;
	
	private Integer lastDistanceCalculated;
	
	public TortugaController(SimpleLogo vue, Jeu jeu){
		this.vue = vue;
		this.jeu = jeu;
		
		this.tortugaCourante = new TortueAmelioree();
		this.tortugaCourante.addObserver(this.vue);
		this.jeu.addObserver(this.vue);
		
		this.jeu.addTortue(tortugaCourante);
		
		reset();
	}
	
	public Tortue getTortugaCourante(){
		return this.tortugaCourante;
	}
	
	public Integer getLastDistanceCalculated(){
		return this.lastDistanceCalculated;
	}
	
	public void reset() {
		// on initialise la position de la tortue
		for (Tortue t : jeu.getTortues()){
			t.reset();
			t.setPosition(vue.getFeuille().getSize().width/2, vue.getFeuille().getSize().height/2);
		}
  	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String c = e.getActionCommand();
		this.lastDistanceCalculated = null;
//		System.out.println("Command: " + c);
		// actions des boutons du haut
		if (c.equals("Avancer")) {
			try {
				int v = Integer.parseInt(vue.getInputDistance());
				this.tortugaCourante.avancer(v);
				if (this.tortugaCourante instanceof TortueAmelioree){
					((TortueAmelioree) this.tortugaCourante).checkProximity(this.jeu.getTortues());
				}
			} catch (NumberFormatException ex){
				System.err.println("ce n'est pas un nombre : " + vue.getInputDistance());
			}
			
		}
		else if (c.equals("Droite")) {
			try {
				int v = Integer.parseInt(vue.getInputDistance());
				this.tortugaCourante.droite(v);
			} catch (NumberFormatException ex){
				System.err.println("ce n'est pas un nombre : " + vue.getInputDistance());
			}
		}
		else if (c.equals("Gauche")) {
			try {
				int v = Integer.parseInt(vue.getInputDistance());
				this.tortugaCourante.gauche(v);
			} catch (NumberFormatException ex){
				System.err.println("ce n'est pas un nombre : " + vue.getInputDistance());
			}
		} else if (c.equals("Ajouter")){
			Tortue t = new TortueAmelioree(vue.getColorIndex(), vue.getFeuille().getSize().width/2, vue.getFeuille().getSize().height/2, vue.getInputName());
			this.tortugaCourante = t;
			this.jeu.addTortue(t);
			this.tortugaCourante.addObserver(this.vue);
		} else if (c.equals("Effacer")) {
			reset();
		} else if (c.equals("Quitter")){
			vue.quitter();
		} else if (c.equals("Lancer la simulation")){
			//TODO
			this.ball = new TortueBalle(11, tortugaCourante.getX()+3, tortugaCourante.getY()+3);
			this.jeu.addTortue(ball); // pas sûr de ça
//			this.ball.draw(graph);
		}
	}
	
		@Override
		public void mouseClicked(MouseEvent e) {
			int xMouse = e.getX();
			int yMouse = e.getY();
			
			for (Tortue t : this.jeu.getTortues()){
				if ( t != this.tortugaCourante && t instanceof TortueAmelioree
						&& xMouse >= t.getX() - MARGIN_ERROR && xMouse <= t.getX() + MARGIN_ERROR 
						&& yMouse >= t.getY() - MARGIN_ERROR && yMouse <= t.getY() + MARGIN_ERROR){

					if(SwingUtilities.isLeftMouseButton(e)){
						this.tortugaCourante = t;
						this.tortugaCourante.setColor(this.tortugaCourante.getColor());//simule un touch pour mettre a jour la vue
					} else if (SwingUtilities.isRightMouseButton(e)){
						// Add friend
						if (this.tortugaCourante instanceof TortueAmelioree && !((TortueAmelioree)this.tortugaCourante).getFriends().contains(t)){
							((TortueAmelioree)this.tortugaCourante).addFriend(t);
						} else {
							((TortueAmelioree)this.tortugaCourante).removeFriend(t);
						}
					} else if (SwingUtilities.isMiddleMouseButton(e)){
						if (this.tortugaCourante instanceof TortueAmelioree){
							this.lastDistanceCalculated = ((TortueAmelioree)this.tortugaCourante).distanceEuclidienne(t);
							this.tortugaCourante.setColor(this.tortugaCourante.getColor());//simule un touch pour mettre a jour la vue
						}
					}
					break;
				}
			}

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

	
}
