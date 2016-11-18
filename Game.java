import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import fr.gagoi.pwal.graphics.window.Display;
import fr.gagoi.pwal.graphics.window.GraphicElement;
import fr.gagoi.pwal.graphics.window.Layer;

public class Game implements Runnable {

	int x = 0, y = 0, a = 1280, b = 720;
	Display d = new Display("Test", 1280, 720, 60);
	Layer game = new Layer(d, 0, 0);
	Layer hud = new Layer(d, 0, 0);

	ArrayList<Snow> snows = new ArrayList<>();

	public Game() {
		d.getLayers().add(game);
		// d.getLayers().add(hud);
	}

	public static void main(String args[]) {
		new Thread(new Game()).start();
	}

	@Override
	public void run() {
		long z = 0;
		while (true) {
			if (z % 1_000 == 0) {
				for (Snow snow : snows) {
					snow.moove();
				}
			}
			if (z % 10_000 == 0) {
				Snow e = new Snow();
				snows.add(e);
				game.add(e);
			}
			z++;
		}
	}
}
