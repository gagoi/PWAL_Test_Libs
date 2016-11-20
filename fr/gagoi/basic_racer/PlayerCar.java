package fr.gagoi.basic_racer;

import fr.gagoi.pwal.utils.Vec2D;

public class PlayerCar extends Car {
	
	private int score;

	public PlayerCar(long carID) {
		super(carID, new Vec2D(83, 525), new Vec2D(0, 0));
	}
	
	public void increaseScore(){
		score++;
	}
	
	public int getScore(){
		return score;
	}
}
