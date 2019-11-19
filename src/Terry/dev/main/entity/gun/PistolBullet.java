package Terry.dev.main.entity.gun;

import java.util.List;
import Terry.dev.main.Game;
import Terry.dev.main.entity.TreeEntity;
import Terry.dev.main.entity.Emitter.ParticleEmitter;
import Terry.dev.main.entity.mob.ChasingZombie;
import Terry.dev.main.entity.mob.Zombie;
import Terry.dev.main.gfx.Render;
import Terry.dev.main.gfx.Sprite;
import Terry.dev.main.level.tiles.TreeTrunk;

public class PistolBullet extends Projectile {

	public static final int FIRERATE = 17;
	public static final int CLIP = 20;
	public static final int AMMO = 100;

	public PistolBullet(double x, double y, double dir) {
		super(x, y, dir);
		range = random.nextInt(300) + 50;
		speed = 8;
		damage = 17;

		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}

	public void tick() {
		List<Zombie> zombies = level.getZombies((int) x + (int) nx + 10, (int) y + (int) ny + 10, 20);
		List<ChasingZombie> chasers = level.getChaserZombies((int) x + (int) nx + 10, (int) y + (int) ny + 10, 20);
		List<TreeEntity> trees = level.getTrees(((int) x + (int) nx) -2, ((int) y + (int) ny)-5, 24);

		if(trees.size() > 0) {
			TreeEntity tree = trees.get(0);
			tree.chop();
		}
		
		if (zombies.size() > 0) {
			remove();
			Zombie zombie = zombies.get(0);
			zombie.hurt(damage);
		}

		if (chasers.size() > 0) {
			remove();
			ChasingZombie chaser = chasers.get(0);
			chaser.hurt(damage);
			Game.playSound("/sounds/hit.wav", -15.0f);
		}

		move();

		if (level.tileCollision((int) (x + nx), (int) (y + ny), 4, 5, 6)) {
			level.add(new ParticleEmitter((int) x, (int) y, 40, 200, level, Sprite.particle));
			Game.playSound("/sounds/hit.wav", -10.0f);
			remove();
		}
	}

	

	public void render(Render render) {
		render.render((int) x, (int) y, Sprite.projectile, false, false);
	}

}
