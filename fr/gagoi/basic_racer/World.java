package fr.gagoi.basic_racer;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

import fr.gagoi.pwal.app.AppElement;
import fr.gagoi.pwal.graphics.window.GraphicElement;
import fr.gagoi.pwal.utils.Vec2D;
import fr.gagoi.pwal.utils.VecUtils;

public class World implements AppElement, GraphicElement {

	private int height = 600;
	private long timer, carID;
	private Vector<Car> cars = new Vector<>();

	@Override
	public void render(Graphics g) {
		g.setColor(Color.GREEN);
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
			g.fillRect((int) (49 + car.getHitbox().getPos().getValue(0) * 34),
					(int) car.getHitbox().getPos().getValue(1), (int) car.getHitbox().getSize().getValue(0),
					(int) car.getHitbox().getSize().getValue(0));
		}
	}

	@Override
	public void update() {
		for (int i = 0; i < cars.size(); i++) {
			Car car = cars.get(i);
			car.getHitbox().setPos(VecUtils.summOf(car.getHitbox().getPos(), car.getHitbox().getSpeed()));
			if (car.getHitbox().getPos().getValue(1) >= 600) {
				cars.remove(car);
			}
		}
		if (timer % 150 == 0) {
			cars.add(new Car(carID, new Vec2D(0, 1)));
			carID++;
			timer = 0;
		}
		timer++;
	}
}
