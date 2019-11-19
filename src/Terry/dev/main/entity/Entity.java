package Terry.dev.main.entity;

import java.util.Random;

import Terry.dev.main.gfx.Render;
import Terry.dev.main.level.Level;

public class Entity {

	public double x, y;
	public boolean removed = false;
	protected Level level;
	protected static Random random = new Random();

	public void tick() {

	}

	public void render(Render render) {

	}

	public void remove() {
		removed = true;
	}

	public boolean isRemoved() {
		return removed;
	}

	public void init(Level level) {
		this.level = level;
	}

	public double getX() {
		return x;
	}
	
	public double getXForChaser() {
		return x+10;
	}
	public double getY() {
		return y;
	}

	public void hurt(int damage) {
		
	}

}
