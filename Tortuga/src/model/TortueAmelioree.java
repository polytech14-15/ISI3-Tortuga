package model;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

public class TortueAmelioree extends Tortue{

	private static final int PROXIMITY = 30;
	
	private String name;
	private boolean isControlled; 
	private List<Tortue> friends;
	
	public TortueAmelioree(){
		super();
		this.name = "Tortue" + Tortue.NB_TORTUE;
		this.friends = new ArrayList<>();
		this.isControlled = false;
	}
	
	public TortueAmelioree(int color, int newX, int newY, String name){
		super(color, newX, newY);
		this.name = name == null || name.isEmpty() ? "Tortue" + Tortue.NB_TORTUE : name;
		this.friends = new ArrayList<>();
		this.isControlled = false;
	}
	
	public String getName(){
		return this.name;
	}
	
	public List<Tortue> getFriends(){
		return this.friends;
	}
	
	public void setFriends(List<Tortue> l){
		this.friends = l;
	}
	
	public void addFriend(Tortue t){
		this.friends.add(t);
	}
	
	public void removeFriend(Tortue t){
		this.friends.remove(t);
	}
	
	public boolean getIsControlled(){
		return this.isControlled;
	}
	
	public void setIsControlled(boolean b){
		this.isControlled = b;
	}
	
	public void checkProximity(List<Tortue> l){
		for (Tortue t : l){
			if (this.getFriends().contains(t) && this.distanceEuclidienne(t) <= TortueAmelioree.PROXIMITY){
				System.out.println(this.name+" said: Hey "+((TortueAmelioree)t).getName()+"! Go away!");
				t.droite(45); t.avancer(10); //Deplacement de son pote
			}
		}
		setChanged();
		notifyObservers();
	}
	
	public int distanceEuclidienne(Tortue t) {
        return Integer.valueOf((int) Math.sqrt(Math.pow(t.getX() - this.getX(), 2) + Math.pow(t.getY() - this.getY(), 2)));
    }

	@Override
	public void draw(Graphics graph) {
		if (graph==null)
			return;

		//Calcule les 3 coins du triangle a partir de
		// la position de la tortue p
		Point p = new Point(this.getX(),this.getY());
		Polygon arrow = new Polygon();

		//Calcule des deux bases
		//Angle de la droite
		double theta=Tortue.ratioDegRad*(-this.getDirection());
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
		
		graph.setColor(ColorUtil.decodeColor(this.getColor()));
		graph.fillPolygon(arrow);
		
		graph.drawString(this.name, this.getX()+10, this.getY());
		
	}
	
}
