package fr.gagoi.snow;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import fr.gagoi.pwal.app.AppElement;
import fr.gagoi.pwal.graphics.window.GraphicElement;

public class Snow implements GraphicElement, AppElement {

	private int[] size;
	private int posX, posY, speed;
	private boolean isRunning = true;
	private int timer;
	private 
	Random r = new Random();
	
	private double random = r.nextDouble()*Math.PI;

	public Snow() {
		posX = r.nextInt(1480)-100;
		speed = r.nextInt(3) + 1;
		size = new int[] { speed-1, speed-1 };
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(posX, posY, size[0], size[1]);
	}

	@Override
	public void update() {
		if (timer % 2 == 0) {
			posY += speed;
			posX += speed*Math.cos(posY/150 + random);
			if (posY >= 750) isRunning = false;
			timer = 0;
		}
		timer++;
	}

	public int getPosY() {
		return posY;
	}

	@Override
	public boolean isRunning() {
		return isRunning;
	}
}
