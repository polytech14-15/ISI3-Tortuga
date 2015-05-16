package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class TortueBalle extends Tortue{

	@Override
	public void draw(Graphics graph) {
		Point p = new Point(this.getX(), this.getY());
		graph.setColor(Color.YELLOW);
		graph.drawOval(p.x, p.y, 10, 10);
	}

}
