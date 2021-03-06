package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

import model.*;
import model.factory.JeuDeBalleFactory;
import view.*;

public class TortugaController implements ActionListener, MouseListener  {
	
	private static final int MARGIN_ERROR = 10;
	public static boolean SIMULATION_ON = false;

	private SimpleLogo vue;
	private Jeu jeu;
	private JeuDeBalle ballGame;
	
	private Integer lastDistanceCalculated;
	
	public TortugaController(SimpleLogo vue){
		this.vue = vue;
		this.jeu = new Jeu(new TortueAmelioree());
		
		this.jeu.getTortugaCourante().addObserver(this.vue);
		this.jeu.addObserver(this.vue);
		
		updateTortueVue();
		reset();
	}
	
	public Jeu getJeu(){
		return this.jeu;
	}
		
	public Integer getLastDistanceCalculated(){
		return this.lastDistanceCalculated;
	}
	
	private void updateTortueVue(){
		for (Tortue t : this.jeu.getTortues()){
			this.vue.getFeuille().addTortue(new VueTortueAmelioree(t));
		}
		if (this.jeu.getTortugaBall() != null){
			this.vue.getFeuille().addTortue(new VueTortueBalle(this.jeu.getTortugaBall()));
		}
	}
	
	public void reset() {
		// on initialise la position de la tortue
		for (Tortue t : jeu.getTortues()){
			t.reset();
			t.setPosition(FeuilleDessin.FEUILLE_WIDTH/2, FeuilleDessin.FEUILLE_HEIGHT/2);
		}
  	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String c = e.getActionCommand();
		this.lastDistanceCalculated = null;
		// actions des boutons du haut
		if (c.equals("Avancer")) {
			try {
				int v = Integer.parseInt(vue.getInputDistance());
				this.jeu.getTortugaCourante().avancer(v);
				if (this.jeu.getTortugaCourante() instanceof TortueAmelioree){
					((TortueAmelioree) this.jeu.getTortugaCourante()).checkProximity(this.jeu.getTortues());
				}
			} catch (NumberFormatException ex){
				System.err.println("ce n'est pas un nombre : " + vue.getInputDistance());
			}
			
		} else if (c.equals("Droite")) {
			try {
				int v = Integer.parseInt(vue.getInputDistance());
				this.jeu.getTortugaCourante().droite(v);
			} catch (NumberFormatException ex){
				System.err.println("ce n'est pas un nombre : " + vue.getInputDistance());
			}
		} else if (c.equals("Gauche")) {
			try {
				int v = Integer.parseInt(vue.getInputDistance());
				this.jeu.getTortugaCourante().gauche(v);
			} catch (NumberFormatException ex){
				System.err.println("ce n'est pas un nombre : " + vue.getInputDistance());
			}
		} else if (c.equals("Ajouter") && !TortugaController.SIMULATION_ON){
			TortueAmelioree t = new TortueAmelioree(vue.getColorIndex(), vue.getFeuille().getSize().width/2, vue.getFeuille().getSize().height/2, vue.getInputName());
			this.jeu.setTortugaCourante(t);
			this.jeu.addTortue(t);
			updateTortueVue();
			this.jeu.getTortugaCourante().addObserver(this.vue);
		} else if (c.equals("Effacer")) {
			reset();
		} else if (c.equals("Quitter")){
			vue.quitter();
		} else if (c.equals("Lancer la simulation")){
			int nbTortues = 0;
			this.vue.getFeuille().reset();
			if (this.vue.getInputSimu() != null && !this.vue.getInputSimu().isEmpty() ){
				try{
					nbTortues = Integer.parseInt(this.vue.getInputSimu());
				} catch (NumberFormatException ex){}
			}
			ballGame = JeuDeBalleFactory.createJeuDeBalle(nbTortues > 1 ? nbTortues : 2);
			this.jeu = ballGame.getJeu();
			this.jeu.getTortugaCourante().addObserver(this.vue);
			TortugaController.SIMULATION_ON = true;
			updateTortueVue();
			ballGame.addObserver(this.vue);
			new Thread(ballGame).start();
		} else if (c.equals("Stopper la simulation")){
			TortugaController.SIMULATION_ON = false;
		}
	}
	
		@Override
		public void mouseClicked(MouseEvent e) {
			int xMouse = e.getX();
			int yMouse = e.getY();
			
			for (Tortue t : this.jeu.getTortues()){
				if (t != this.jeu.getTortugaCourante() && t instanceof TortueAmelioree
						&& xMouse >= t.getX() - MARGIN_ERROR && xMouse <= t.getX() + MARGIN_ERROR 
						&& yMouse >= t.getY() - MARGIN_ERROR && yMouse <= t.getY() + MARGIN_ERROR){

					if(SwingUtilities.isLeftMouseButton(e)){
						this.jeu.setTortugaCourante(t);
						this.jeu.getTortugaCourante().setColor(this.jeu.getTortugaCourante().getColor());//simule un touch pour mettre a jour la vue
					} else if (!TortugaController.SIMULATION_ON && SwingUtilities.isRightMouseButton(e)){
						// Add friend
						if (this.jeu.getTortugaCourante() instanceof TortueAmelioree && !((TortueAmelioree)this.jeu.getTortugaCourante()).getFriends().contains(t)){
							((TortueAmelioree)this.jeu.getTortugaCourante()).addFriend(t);
							System.out.println("Un ami a �t� ajout�");
						} else {
							((TortueAmelioree)this.jeu.getTortugaCourante()).removeFriend(t);
							System.out.println("Un ami a �t� enlev�");
						}
					} else if (SwingUtilities.isMiddleMouseButton(e)){
						if (this.jeu.getTortugaCourante() instanceof TortueAmelioree){
							this.lastDistanceCalculated = ((TortueAmelioree)this.jeu.getTortugaCourante()).distanceEuclidienne(t);
							this.jeu.getTortugaCourante().setColor(this.jeu.getTortugaCourante().getColor());//simule un touch pour mettre a jour la vue
						}
					}
					break;
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}
}
