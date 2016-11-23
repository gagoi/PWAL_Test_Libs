package fr.gagoi.basic_racer;

import java.awt.Color;
import java.awt.Graphics;

import fr.gagoi.pwal.app.Application;
import fr.gagoi.pwal.graphics.window.Display;
import fr.gagoi.pwal.graphics.window.GraphicElement;
import fr.gagoi.pwal.graphics.window.Layer;

public class Game {

	private Display d = new Display("Basic Racer", 7*186, 600, 60);
	private Application app = new Application("Basic Racer", 120);
	private Layer game[] = { new Layer(d, 0, 0), new Layer(d, 1*186, 0), new Layer(d, 2*186, 0), new Layer(d, 3*186, 0),
			new Layer(d, 4*186, 0), new Layer(d, 5*186, 0), new Layer(d, 6*186, 0), new Layer(d, 7*186, 0) },
			hud[] = { new Layer(d, 0, 0), new Layer(d, 1*186, 0), new Layer(d, 2*186, 0), new Layer(d, 3*186, 0),
					new Layer(d, 4*186, 0), new Layer(d, 5*186, 0), new Layer(d, 6*186, 0), new Layer(d, 7*186, 0) };
					
//	private Layer game[] = { new Layer(d,0,0)}, hud[] = {new Layer(d,0,0)};
	private PlayerCar player = new PlayerCar(0);

	// B = Bordure, T = Ligne blanche, V = Voie;
	// B-T-V-T-V-T-V-T-B
	// 40-4-30-4-30-4-30-4-40
	// private World w = new World(player, d.getKeyboard());
	private IA ia = new IA(game.length);

	public Game() {
		for (int i = 0; i < game.length; i++) {
			d.getLayers().add(game[i]);
			d.getLayers().add(hud[i]);
			hud[i].add(new GraphicElement() {

				@Override
				public void render(Graphics g) {
					g.setColor(Color.BLACK);
					g.fillRect(0, 0, 200, 15);
					g.setColor(Color.RED);
					g.drawString("Score : " + player.getScore(), 10, 10);
				}
			});
		}
		for (int i = 0; i < game.length; i++) {
			app.add(ia.getWorlds()[i]);
			game[i].add(ia.getWorlds()[i]);
		}
		app.add(ia);
	}
}
