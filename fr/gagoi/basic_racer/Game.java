package fr.gagoi.basic_racer;

import fr.gagoi.pwal.app.Application;
import fr.gagoi.pwal.graphics.window.Display;
import fr.gagoi.pwal.graphics.window.Layer;

public class Game implements Runnable {

	private Display d = new Display("Basic Racer", 186, 600, 60);
	private Application app = new Application("Basic Racer", 120);
	private Layer game = new Layer(d, 0, 0);
	private PlayerCar player = new PlayerCar(0);
	
	// B = Bordure, T = Ligne blanche, V = Voie;
	// B-T-V-T-V-T-V-T-B
	// 40-4-30-4-30-4-30-4-40
	private World w = new World(player);
	
	public Game() {
		d.getLayers().add(game);
		game.add(w);
		app.add(w);
	}


	@Override
	public void run() {
		long x = 0;
		while (true) {
			if (x % 1_000_000 == 0){
//				
//				for (int i = 0; i < snows.size(); i++) {
//					Snow s = snows.get(i);
//					if(s.getPosY() >= 1000) {
//						snows.remove(s);
//						app.remove(s);
//						game.remove(s);
//					}
//				}
//				
//				Snow s = new Snow();
//				snows.add(s);
//				app.add(s);
//				game.add(s);
			}
			x++;
		}
	}
}
