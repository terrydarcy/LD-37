package Terry.dev.main.entity;

import java.util.List;
import Terry.dev.main.Game;
import Terry.dev.main.entity.mob.Player;
import Terry.dev.main.gfx.Render;
import Terry.dev.main.gfx.Sprite;
import Terry.dev.main.input.Input;
import Terry.dev.main.level.Level;
import Terry.dev.main.level.Tile;
import Terry.dev.main.util.Vector2i;

public class CashEntity extends Entity {

	public static int amount = 0;
	public static boolean removed = false;
	public static double xx, yy;
	private int time = 0;
	private boolean above = true;

	public CashEntity(double x, double y, Level level) {
		this.x = x;
		this.y = y;
		amount = random.nextInt(40) + 3;
		removed = false;
	}

	public CashEntity(Vector2i vector, Level level, Input input) {
		this.x = vector.x;
		this.y = vector.y;
		amount = random.nextInt(40) + 3;
		removed = false;
	}

	public void tick() {
		time++;
		List<Player> ps = level.getPlayers(this, 13);
		if (ps.size() > 0) {
			Player.addCash(amount);
			removed = true;
			level.remove(this);
		}
		if (level.getTile((int) x / 16, (int) y / 16) == Tile.voidTile || level.getTile((int) x / 16, (int) y / 16) == Tile.water) {
			x-=0.5;
			if (time % 10 == 0) {
				y += 0.3;
				above = true;
			} else if(time %5 == 0){
				y -= 0.3;
				above = false;
			}
		}
	}

	public void render(Render render) {
		render.render((int) x - 5, (int) y, Sprite.CashEntity, false, false);
	}
}
