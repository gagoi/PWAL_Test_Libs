package fr.gagoi.basic_racer;

import java.util.Random;

import fr.gagoi.pwal.entities.Entity2D_Rectangle;
import fr.gagoi.pwal.utils.Vec2D;

public class Car extends Entity2D_Rectangle {

	// Les voitures peuvent avoir que 3 positions (0, 1, 2) en X et la position
	// en Y représente la hauteur à l'écran, une fois sortie de l'ecran on peut
	// la supprimer.
	// Elles ont une taille de 25*50.
	// On peut les traverser, si on le fais on va mourir.
	
	private long id;
	
	public Car(long carID, Vec2D speed) {
		super("<Car:" + carID + ">", new Vec2D(new Random().nextInt(3), 0), new Vec2D(20, 40), speed, false);
		this.id = carID;
	}

	@Override
	public String toString() {
		return super.toString() + "{id=" + id + "; x=" + getHitbox().getPos().getValue(0) + "; y=" + getHitbox().getPos().getValue(1) + "; speed=" + getHitbox().getSpeed() + "}";
	}
}
