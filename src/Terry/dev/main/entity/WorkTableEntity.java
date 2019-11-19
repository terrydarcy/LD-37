package Terry.dev.main.entity;

import java.util.List;

import Terry.dev.main.Game;
import Terry.dev.main.entity.mob.Player;
import Terry.dev.main.gfx.Render;
import Terry.dev.main.gfx.Sprite;
import Terry.dev.main.gui.LootingMenu;
import Terry.dev.main.input.Input;
import Terry.dev.main.level.Level;
import Terry.dev.main.util.Vector2i;

public class WorkTableEntity extends Entity {

	private Sprite sprite;
	public static boolean solid = false;
	public static boolean activated = false;
	private int tick = 0;
	public static boolean inRange = false;
	public static boolean inAir = false;
	public boolean carrying;
	public static boolean pickupRange = false;

	public WorkTableEntity(double x, double y, Level level) {
		this.x = x;
		this.y = y;
		sprite = Sprite.workTable;
	//	level.setTile((int)x, (int)y, Tile.water);
	}

	public WorkTableEntity(Vector2i vector, Level level) {
		this.x = vector.x;
		this.y = vector.y;
		sprite = Sprite.workTable;

	}

	public void tick() {
		
		List<Player> players = level.getPlayers(this, 40);
		
		List<Player> ps = level.getPlayers(this, 23);
		if (ps.size() > 0) {
			Player player = ps.get(0);
			carrying = player.workTableCarrying ;
			pickupRange = true;
			if (player.workTableCarrying) {
				x = player.x - 8;
				y = player.y - 15;
			}
		} else {
			pickupRange = false;
		}
		
		if (players.size() > 0) {
			inRange = true;      	
		} else {
			inRange = false;
			activated = false;
		}
	}

	public void render(Render render) {
		render.render((int) x, (int) y, sprite, false, false);
	}
}
