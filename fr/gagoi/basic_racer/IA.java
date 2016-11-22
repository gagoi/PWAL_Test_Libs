package fr.gagoi.basic_racer;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Vector;

import fr.fixyneko.neuralIA.Generation;
import fr.gagoi.pwal.app.AppElement;

public class IA implements AppElement {

	Generation gen;
	World[] worlds;
	int brains = 20;
	int inputsNum = 3;
	int[] neurons = { 5, 7, 4, 3 };

	private int[][] inputs;

	public IA() {
		this.gen = new Generation(brains, inputsNum, neurons);
		this.worlds = new World[brains];

		for (World w : worlds) {
			Vector<Car> cars = w.getEntities();
			long[] ids = new long[brains];
			for (int i = 0; i < brains; i++) {
				ids[i] = cars.get(i).getId();
			}
			Arrays.sort(ids);
			if(ids[0] == ids[1])
				ids = new long[] {ids[0], ids[1]};
			else
				ids = new long[] {ids[0]};
		}
	}
	
	@Override
	public void update() {
	}

	@Override
	public boolean isRunning() {
		return false;
	}
}
