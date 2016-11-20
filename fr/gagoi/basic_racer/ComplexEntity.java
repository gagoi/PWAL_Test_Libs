package fr.gagoi.basic_racer;

import fr.fixyneko.neuralIA.IA;
import fr.gagoi.pwal.entities.Entity2D_Rectangle;
import fr.gagoi.pwal.utils.Vec2D;

public class ComplexEntity extends Entity2D_Rectangle {
	
	private IA ia;

	public ComplexEntity(String name, Vec2D pos, Vec2D size, Vec2D speed, boolean isHard) {
		super(name, pos, size, speed, isHard);
	}

	public void think() {

	}

}
