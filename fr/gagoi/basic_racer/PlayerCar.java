package fr.gagoi.basic_racer;

import fr.gagoi.pwal.utils.Vec2D;

public class PlayerCar extends Car {

	private int score;
	private int tempScore;

	public PlayerCar(long carID) {
		super(carID, new Vec2D(83, 525), new Vec2D(0, 0));
	}

	public void increaseScore() {
		if (tempScore % 120 == 0) {
			score++;
			tempScore = 0;
		}
		tempScore++;
	}

	public int getScore() {
		return score;
	}
}
