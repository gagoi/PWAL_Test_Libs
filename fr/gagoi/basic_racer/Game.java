package fr.gagoi.basic_racer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import fr.gagoi.pwal.app.Application;
import fr.gagoi.pwal.graphics.window.Display;
import fr.gagoi.pwal.graphics.window.Layer;

public class Game implements Runnable{

	private Display d = new Display("Basic Racer", 186, 600, 60);
	private Application app = new Application("Basic Racer", 120);
	private Layer game = new Layer(d, 0, 0);
	private PlayerCar p = new PlayerCar(-1);
	
	private boolean isRunning = true;

	// B = Bordure, T = Ligne blanche, V = Voie;
	// B-T-V-T-V-T-V-T-B
	// 40-4-30-4-30-4-30-4-40
	private World w = new World(p);

	public Game() {
		d.getLayers().add(game);
		d.addKeyListener(w);
		game.add(w);
		app.add(w);
	}

	@Override
	public void run() {
		while (isRunning) {
			System.out.println("");
			if (!app.getElements().contains(w)) {
				JOptionPane.showMessageDialog(d, "Score : " + p.getScore());
				isRunning = false;
			}
		}
	}
}
