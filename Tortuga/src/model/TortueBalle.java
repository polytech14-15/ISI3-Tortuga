package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class TortueBalle extends Tortue{
	
	public TortueBalle(){
		super();
	}
	
	public TortueBalle(int color, int newX, int newY){
		super(color, newX, newY);
	}
	
	@Override
	public void draw(Graphics graph) {
		Point p = new Point(this.getX(), this.getY());
		graph.setColor(Color.YELLOW);
		graph.fillOval(p.x, p.y, 10, 10);
	}

}
