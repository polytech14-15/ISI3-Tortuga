package model;

import java.awt.Graphics;
import java.awt.Point;

public class TortueBalle extends Tortue{
	
	private Tortue mamanTortue;
	
	public TortueBalle(){
		super();
	}
	
	public TortueBalle(int color, Tortue t){
		super(color, t.getX(), t.getY());
		this.mamanTortue = t;
	}
	
	@Override
	public void draw(Graphics graph) {
		Point p = new Point(mamanTortue.getX(), mamanTortue.getY());
		graph.setColor(ColorUtil.decodeColor(this.coul));
		graph.fillOval(p.x-5, p.y+10, 10, 10);
	}
	
	public Tortue getMamanTortue(){
		return mamanTortue;
	}
	
	public void setMamanTortue(Tortue mamanTortue){
		this.mamanTortue = mamanTortue;
	}

}
