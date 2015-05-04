package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Tortue;

public class TortugaController implements ActionListener  {

	private Tortue tortuga;
	
	public TortugaController(){
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void reset() {
		// on initialise la position de la tortue
		tortuga.setPosition(0, 0);
		tortuga.setDirection(-90);
		tortuga.setColor(0);
		
		// plus besoin de ça
//		crayon = true;
//		listSegments.clear();
  	}
	
	// avancer de n pas
		public void avancer(int dist) {
			int newX = (int) Math.round(tortuga.getX()+dist*Math.cos(tortuga.ratioDegRad*tortuga.getDirection()));
			int newY = (int) Math.round(tortuga.getY()+dist*Math.sin(tortuga.ratioDegRad*tortuga.getDirection()));
			
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
			
			tortuga.setPosition(newX, newY);
		}

		// aller a droite
		public void droite(int ang) {
			tortuga.setDirection((tortuga.getDirection() + ang) % 360);
		}

		// aller a gauche
		public void gauche(int ang) {
			tortuga.setDirection((tortuga.getDirection() - ang) % 360);
		}

	
}
