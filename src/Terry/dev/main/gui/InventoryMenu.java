package Terry.dev.main.gui;

import java.awt.List;
import java.util.ArrayList;
import java.util.Random;

import Terry.dev.main.entity.Entity;
import Terry.dev.main.entity.mob.Player;
import Terry.dev.main.gfx.Font;
import Terry.dev.main.gfx.Render;
import Terry.dev.main.gfx.Sprite;

public class InventoryMenu extends Menu {

	// private String[] selections = { "return", "Volume" };
	public Sprite sprite;
	private int time = 0;
	private int anim = 0;
	private int drop = 140;
	private Random random = new Random();
	private int selections = 0;
	private ArrayList<Entity> entities = new ArrayList<Entity>();

	public boolean empty = false;

	public InventoryMenu() {
		selected = 0;
		sprite = Sprite.title;

	}

	public void returnB() {
		game.setMenu(null);
	}

	public void volume() {

	}

	public void tick() {
		time++;
		selections = 3;
		if (selected >= selections) selected = selections;
		if (!Player.inventoryActivated) game.setMenu(null);
		selector();
		if (input.space.clicked) select(selected);
//System.out.println(selected);
		if (time % 10 == 5) anim++;
		if (anim > 3) anim = 0;
		if (anim == 0) sprite = Sprite.title;
		if (anim == 1) {
			drop = 140;
			sprite = Sprite.title1;
		}
		if (anim == 2) sprite = Sprite.title2;
		if (anim == 3) sprite = Sprite.title3;
		if (anim == 3 || anim == 0) {
			if (random.nextInt(2) == 0) {
				drop += 6;
			} else {
				drop += 10;
			}
		}
		if (input.inventory.clicked) {
			game.setMenu(null);
			Player.inventoryActivated = false;
		}
	}

	public void select(int selected) {
		if (selected == 0) volume();
		if (selected == 1) returnB();
	}

	public static final int SELECT_TIME = 200;

	int yp = 16;
	int selected = 1;

	// selected = 0 - Play
	// selected = 1 - options
	// selected = 2 - help
	// selected = 3 - exit

	public void render(Render render) {
		int yy = 185;
		int gap = 15;
		{
			render.renderRect(5, 11, 6 * 16, 9 * 16, 0x848484, false);
			render.renderIcon(-6, 6, Sprite.guiCorner, false, false, false);
			render.renderIcon((7 * 16) - 6, 6, Sprite.guiCorner, true, false, false);

			render.renderIcon(-6, 166, Sprite.guiCorner, false, true, false);
			render.renderIcon((7 * 16) - 6, 166, Sprite.guiCorner, true, true, false);

			for (int i = 0; i < 6; i++) {
				render.renderIcon(16 * i + 10, 6, Sprite.guiTop, false, false, false);
			}

			for (int i = 0; i < 6; i++) {
				render.renderIcon(16 * i + 10, 166, Sprite.guiTop, false, true, false);
			}

			for (int i = 0; i < 9; i++) {
				render.renderIcon(-6, 16 * i + 22, Sprite.guiSide, false, true, false);
			}
			for (int i = 0; i < 9; i++) {
				render.renderIcon(6 * 16 + 10, 16 * i + 22, Sprite.guiSide, true, false, false);
			}
		}
		if (Player.inv_logs > 0) {
			addSelection();
			render.renderIcon(16, 16 * 2, Sprite.logParticle, false, false, false);
			Font.draw(Integer.toString(Player.inv_logs), render, 16 + 20, 16 * 2 + 7, 0x363636, false);
			Font.draw(Integer.toString(Player.inv_logs), render, 16 + 20, 16 * 2 + 6, 0xEFF589, false);
		}
		if (Player.inv_saplings > 0) {
			render.renderIcon(16, 16 * 3, Sprite.sapling, false, false, false);
			Font.draw(Integer.toString(Player.inv_saplings), render, 16 + 20, 16 * 3 + 7, 0x363636, false);
			Font.draw(Integer.toString(Player.inv_saplings), render, 16 + 20, 16 * 3 + 6, 0xEFF589, false);

		}
		String inv = "INVENTORY";
		Font.draw(inv, render, (16 * 2) - inv.length(), 25, 0x5E3F4E, false);
		Font.draw(inv, render, (16 * 2) - inv.length(), 25 - 1, 0xDB76A5, false);

		for (int i = 0; i < 6; i++) {
			render.renderIcon(10 + i * 16, yp, Sprite.guiSelector, false, false, false);
		}
		if (selected == 0) {
			yp = 16;
		}

		if (selected == 1) {
			yp = 32;
		}
	}

	private void addSelection() {
	}

}
