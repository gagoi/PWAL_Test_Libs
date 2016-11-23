package fr.gagoi.basic_racer;

import fr.gagoi.pwal.entities.EntityBase;
import fr.gagoi.pwal.hitboxes.Hitbox2D_Rectangle;
import fr.gagoi.pwal.hitboxes.IHitbox;
import fr.gagoi.pwal.utils.Vec2D;

public class Car extends EntityBase {
	private long id;

	public Car(long carID, Vec2D pos, Vec2D speed) {
		super("<Car:" + carID + ">");
		this.hitbox = new Hitbox2D_Rectangle(pos, new Vec2D(20, 40), speed, false) {
			@Override
			public boolean collide(IHitbox hitbox) {
				for (int y = (int) getPos().getValue(1); y < getPos().getValue(1) + getSize().getValue(1); y++) {
					if (hitbox.contain(new Vec2D((int) getPos().getValue(0), y)))
						return true;
				}

				return false;
			}
		};
		this.id = carID;

	}

	@Override
	public String toString() {
		return super.toString() + "{id=" + id + "; x=" + getHitbox().getPos().getValue(0) + "; y="
				+ getHitbox().getPos().getValue(1) + "; speed=" + getHitbox().getSpeed() + "}";
	}

	public long getId() {
		return this.id;
	}
}
