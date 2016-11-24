package fr.gagoi.basic_racer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import fr.fixyneko.neuralIA.Generation;
import fr.gagoi.pwal.app.AppElement;
import fr.gagoi.pwal.inputs.Keyboard;

public class IA implements AppElement {

	private boolean isRunning = true;
	private Generation gen;
	private World[] worlds;
	private PlayerCar[] players;
	private int gamesNum;

	private int inputsNum = 3;
	private int[] neurons = { 5, 7, 4, 3, 1 };
	private double mutation = 0.10;
	private boolean resetting = false;

	private double[][] inputs = { {} };
	private double[][] outs;

	public IA(int gamesNum) {
		this.gamesNum = gamesNum;
		this.gen = new Generation(this.gamesNum, inputsNum, neurons);
		this.worlds = new World[this.gamesNum];
		this.players = new PlayerCar[this.gamesNum];

		initWorlds();

		inputs = new double[this.gamesNum][inputsNum];
		this.resetting = true;
	}

	private void initWorlds() {
		for (int i = 0; i < this.worlds.length; i++) {
			this.players[i] = new PlayerCar(-1);
			this.worlds[i] = new World(this.players[i], new Keyboard());
		}
	}

	@Override
	public void update() {
		if (!this.resetting) {
			// System.out.println("update IA");

			// test le isRunning des worlds
			boolean flag = false;
			for (World w : worlds)
				if (w.isRunning())
					flag = true;

			if (!flag) {
				for (int s = 0; s < players.length; s++) {
					gen.getBrain(s).setScore(players[s].getScore());
				}
				gen.evolve(mutation);
				initWorlds();
				this.resetting = true;
				return;
			}

			// Calculs des outputs en fonction des inputs
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
					ids = new long[] { ids[0], ids[1] };

					ArrayList<Car> validCars = new ArrayList<Car>();
					for (int i = 0; i < cars.size(); i++)
						if (ids[0] == cars.get(i).getId())
							validCars.add(cars.get(i));

					for (int i = 0; i < cars.size(); i++)
						if (cars.get(i).getHitbox().getPos().getValue(1) == validCars.get(0).getHitbox().getPos()
								.getValue(1))
							validCars.add(cars.get(i));
					for (Car car : validCars)
						// places (index)= ({49, 83, 117}-49) /34
						inputs[w][(int) (car.getHitbox().getPos().getValue(0) - 49) / 34] = 1;
					// calcul d'inputs done
				} catch (Exception e) {
//					System.out.println("inputs calc exception");
				}
			}
			if (inputs.length > 0)
				outs = this.gen.compute(inputs);

//			for (double[] out : outs)
//				System.out.print(out[0] + " ; ");
//			System.out.println();

			for (int i = 0; i < outs.length; i++) {
				int lane = 1;
				if (outs[i][0] > 0.66)
					lane = 2;
				else if (outs[i][0] < 0.33)
					lane = 0;
				worlds[i].move(lane);
			}
		}
	}

	public World[] getWorlds() {
		return this.worlds;
	}
	
	public Generation getGen() {
		return this.gen;
	}

	@Override
	public boolean isRunning() {
		return isRunning;
	}

	public boolean readyToReset() {
		return this.resetting;
	}

	public void reset() {
		this.resetting = false;
		;
	}
}
