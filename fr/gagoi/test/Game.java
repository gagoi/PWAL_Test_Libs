package fr.gagoi.test;

import java.util.ArrayList;

import fr.gagoi.pwal.app.Application;
import fr.gagoi.pwal.graphics.window.Display;
import fr.gagoi.pwal.graphics.window.Layer;

public class Game implements Runnable {

	private Display d = new Display("Test", 1280, 720, 60);
	private Application app = new Application("Test", 120);
	private Layer game = new Layer(d, 0, 0);

	private ArrayList<Snow> snows = new ArrayList<>();
	
	public Game() {
		for (int i = 0; i < 10; i++) {
		}
		d.getLayers().add(game);
	}

	public static void main(String args[]) {
		new Thread(new Game()).start();
	}

	@Override
	public void run() {
		long x = 0;
		while (true) {
			if (x % 1_000_000 == 0){
				
				for (int i = 0; i < snows.size(); i++) {
					Snow s = snows.get(i);
					if(s.getPosY() >= 1000) {
						snows.remove(s);
						app.remove(s);
						game.remove(s);
					}
				}
				
				Snow s = new Snow();
				snows.add(s);
				app.add(s);
				game.add(s);
			}
			x++;
		}
	}
}
