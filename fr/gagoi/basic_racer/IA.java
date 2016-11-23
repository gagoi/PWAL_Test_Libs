package fr.gagoi.basic_racer;

import java.util.Arrays;
import java.util.Vector;

import fr.fixyneko.neuralIA.Generation;
import fr.gagoi.pwal.app.AppElement;
import fr.gagoi.pwal.inputs.Keyboard;

public class IA implements AppElement {

	private Generation gen;
	private World[] worlds;
	int gamesNum;
	private int inputsNum = 3;
	private int[] neurons = { 5, 7, 4, 3, 1 };
	private boolean isRunning = true;

	private double[][] inputs = { {} };
	private double[][] outs;

	public IA(int gamesNum) {
		this.gamesNum = gamesNum;
		this.gen = new Generation(this.gamesNum, inputsNum, neurons);
		this.worlds = new World[this.gamesNum];

		for (int i = 0; i < worlds.length; i++)
			worlds[i] = new World(new PlayerCar(-1), new Keyboard());
		
		inputs = new double[this.gamesNum][inputsNum];
	}

	@Override
	public void update() {
		// System.out.println("update IA");
		long[] ids = null;
		Vector<Car> cars = null;

		for (int i = 0; i < inputs.length; i++)
			for (int j = 0; j < inputs[i].length; j++)
				inputs[i][j] = 0;

		for (int w = 0; w < worlds.length; w++) {
			cars = worlds[w].getEntities();
			ids = new long[cars.size()];

			try {
				for (int i = 0; i < cars.size(); i++) {
					ids[i] = cars.get(i).getId();
				}
				Arrays.sort(ids);
				if (ids[0] == ids[1])
					ids = new long[] { ids[0], ids[1] };
				else
					ids = new long[] { ids[0] };

				for (long id : ids)
					for (int i = 0; i < cars.size(); i++)
						if (id == cars.get(i).getId()){
							// places (index)= ({49, 83, 117}-49) /34
							inputs[w][(int) (cars.get(i).getHitbox().getPos().getValue(0) - 49) / 34] = 1;
						}
				// calcul d'inputs done
			} catch (Exception e) {
				System.out.println("inputs calc exception");
			}
		}
		if (inputs.length > 0)
			outs = this.gen.compute(inputs);

		for( double[] out: outs)
			System.out.print(out[0] + " ; ");
		System.out.println();
		

		for (int i = 0; i < outs.length; i++) {
			int lane = 1;
			if (outs[i][0] > 0.66)
				lane = 2;
			else if (outs[i][0] < 0.33)
				lane = 0;
			worlds[i].move(lane);
		}
	}

	public World[] getWorlds() {
		return this.worlds;
	}

	@Override
	public boolean isRunning() {
		return isRunning;
	}
}
