package fr.gagoi.snow;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import fr.gagoi.pwal.app.AppElement;
import fr.gagoi.pwal.graphics.window.GraphicElement;

public class Snow implements GraphicElement, AppElement {

	private int[] size;
	private int posX, posY, speed;

	private int timer;

	public Snow() {
		Random r = new Random();
		posX = r.nextInt(1280);
		int i = r.nextInt(2) + 1;
		speed = r.nextInt(3) + 1;
		size = new int[] { i, i };
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(posX, posY, size[0], size[1]);
	}

	@Override
	public void update() {
		if (timer % 2 == 0)
			posY += speed;
		timer++;
		// System.out.println(posY);
	}

	public int getPosY() {
		return posY;
	}
}
