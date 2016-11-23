package fr.gagoi.basic_racer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.Vector;

import fr.gagoi.pwal.app.AppElement;
import fr.gagoi.pwal.graphics.window.GraphicElement;
import fr.gagoi.pwal.inputs.Keyboard;
import fr.gagoi.pwal.utils.Vec2D;
import fr.gagoi.pwal.utils.VecUtils;

public class World implements AppElement, GraphicElement {

	private int height = 600, timer;
	private long carID;
	private Vector<Car> cars = new Vector<>();
	private PlayerCar p;
	private Random r = new Random();
	private boolean isRunning = true;
	private Keyboard keyboard = new Keyboard();
	private int lane = -1;

	public World(PlayerCar player, Keyboard keyboard) {
		this.p = player;
		this.keyboard = keyboard;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(0x4CBB17));
		g.fillRect(0, 0, 40, height);
		g.fillRect(146, 0, 40, height);
		g.setColor(Color.WHITE);
		g.fillRect(40, 0, 4, height);
		g.fillRect(74, 0, 4, height);
		g.fillRect(108, 0, 4, height);
		g.fillRect(142, 0, 4, height);
		g.setColor(Color.BLACK);
		g.fillRect(44, 0, 30, height);
		g.fillRect(78, 0, 30, height);
		g.fillRect(112, 0, 30, height);

		g.setColor(Color.RED);
		for (int i = 0; i < cars.size(); i++) {
			Car car = cars.get(i);
			g.fillRect((int) (car.getHitbox().getPos().getValue(0)), (int) car.getHitbox().getPos().getValue(1),
					(int) car.getHitbox().getSize().getValue(0), (int) car.getHitbox().getSize().getValue(1));
		}

		g.setColor(Color.BLUE);
		g.fillRect((int) p.getHitbox().getPos().getValue(0), (int) p.getHitbox().getPos().getValue(1),
				(int) p.getHitbox().getSize().getValue(0), (int) p.getHitbox().getSize().getValue(1));
	}

	@Override
	public void update() {
//		System.out.println("update World");
		if (isRunning) {
			for (int i = 0; i < cars.size(); i++) {
				Car car = cars.get(i);
				if (p.getHitbox().collide(car.getHitbox())) {
					isRunning = false;
					return;
				}
				car.getHitbox().setPos(VecUtils.summOf(car.getHitbox().getPos(), car.getHitbox().getSpeed()));
				if (car.getHitbox().getPos().getValue(1) >= height)
					cars.remove(car);

			}

			if (keyboard.isKeyPressed(37) || lane == 0)
				p.getHitbox().setPos(new Vec2D(49, p.getHitbox().getPos().getValue(1)));
			else if (keyboard.isKeyPressed(39) || lane == 2)
				p.getHitbox().setPos(new Vec2D(117, p.getHitbox().getPos().getValue(1)));
			else
				p.getHitbox().setPos(new Vec2D(83, p.getHitbox().getPos().getValue(1)));

			if (timer % (r.nextInt(50) + 125) == 0) {
				switch (r.nextInt(6)) {
				case 0:
					cars.add(new Car(carID, new Vec2D(49, 0), new Vec2D(0, 1)));
					carID++;
					break;
				case 1:
					cars.add(new Car(carID, new Vec2D(83, 0), new Vec2D(0, 1)));
					carID++;
					break;
				case 2:
					cars.add(new Car(carID, new Vec2D(117, 0), new Vec2D(0, 1)));
					carID++;
					break;
				case 3:
					cars.add(new Car(carID, new Vec2D(49, 0), new Vec2D(0, 1)));
					carID++;
					cars.add(new Car(carID, new Vec2D(83, 0), new Vec2D(0, 1)));
					carID++;
					break;
				case 4:
					cars.add(new Car(carID, new Vec2D(49, 0), new Vec2D(0, 1)));
					carID++;
					cars.add(new Car(carID, new Vec2D(117, 0), new Vec2D(0, 1)));
					carID++;
					break;
				case 5:
					cars.add(new Car(carID, new Vec2D(83, 0), new Vec2D(0, 1)));
					carID++;
					cars.add(new Car(carID, new Vec2D(117, 0), new Vec2D(0, 1)));
					carID++;
					break;
				}
				timer = 0;
			}
			timer++;
			p.increaseScore();
		}
	}

	@Override
	public boolean isRunning() {
		return isRunning;
	}

	public Vector<Car> getEntities() {
		return this.cars;
	}

	public void move(int lane) {
		this.lane = lane;
	}
}
