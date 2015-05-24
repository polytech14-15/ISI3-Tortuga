package view;

import java.awt.Graphics;
import java.awt.Point;

import model.*;

public class VueTortueBalle extends IVueTortue {

	public VueTortueBalle(Tortue tortue) {
		super(tortue);
	}

	@Override
	public void draw(Graphics graph) {
		TortueBalle ball = (TortueBalle) getTortue();
		Point p = new Point(ball.getMamanTortue().getX(), ball.getMamanTortue().getY());
		graph.setColor(ColorUtil.decodeColor(ball.getMamanTortue().getColor()));
		graph.fillOval(p.x-5, p.y+10, 10, 10);
	}
}
