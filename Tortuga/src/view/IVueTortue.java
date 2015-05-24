package view;

import java.awt.Graphics;

import model.*;

public abstract class IVueTortue {

	private Tortue tortue;

	public Tortue getTortue() {
		return tortue;
	}

	public IVueTortue(Tortue tortue) {
		this.tortue = tortue;
//		la;
	}
	

	public abstract void draw(Graphics graph);

}
