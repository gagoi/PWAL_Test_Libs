import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import fr.gagoi.pwal.graphics.window.GraphicElement;

public class Snow implements GraphicElement{

	private int[] size;
	private int posX, posY = 0;

	public Snow() {
		Random r = new Random();
		posX = r.nextInt(1280);
		size = new int[] { r.nextInt(1) + 1, r.nextInt(1) + 1 };
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(posX, posY, size[0], size[1]);
	}

	public void moove() {
		posY++;		
	}
}
