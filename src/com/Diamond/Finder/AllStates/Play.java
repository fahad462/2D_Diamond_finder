package com.Diamond.Finder.AllStates;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.Diamond.Finder.*;
import com.Diamond.Finder.GameMap.TilesMap;
import com.Diamond.Finder.Graphics.Entity;
import com.Diamond.Finder.Graphics.Items;
import com.Diamond.Finder.Graphics.MistStudent;
import com.Diamond.Finder.Graphics.PlayAnime;
import com.Diamond.Finder.Graphics.Object;
import com.Diamond.Finder.PlayerLoc.PlayerLoc;
import com.Diamond.Finder.States.AudioPlayer;
import com.Diamond.Finder.States.Data;
import com.Diamond.Finder.States.GameState;
import com.Diamond.Finder.States.Keys;
import com.Diamond.Finder.UI.GamePane;

public class Play extends States {

	private MistStudent player;

	private TilesMap tileMap;

	private ArrayList<Object> diamonds;
	private ArrayList<Items> items;
	private ArrayList<PlayAnime> sparkles;

	private int xsector;
	private int ysector;
	private int sectorSize;

	private PlayerLoc hud;

	private boolean blockInput;
	private boolean eventStart;
	private boolean eventFinish;
	private int eventTick;

	private ArrayList<Rectangle> boxes;

	public Play(GameState gsm) {
		super(gsm);
	}

	public void init() {
		diamonds = new ArrayList<Object>();
		sparkles = new ArrayList<PlayAnime>();
		items = new ArrayList<Items>();
		tileMap = new TilesMap(16);
		tileMap.loadTiles("/Tilesets/testtileset1.png");
		tileMap.loadMap("/Maps/testmap.map");
		player = new MistStudent(tileMap);
		populateDiamonds();
		populateItems();
		player.setTilePosition(17, 17);
		player.setTotalDiamonds(diamonds.size());
		sectorSize = GamePane.WIDTH;
		xsector = player.getx() / sectorSize;
		ysector = player.gety() / sectorSize;
		tileMap.setPositionImmediately(-xsector * sectorSize, -ysector * sectorSize);
		hud = new PlayerLoc(player, diamonds);
		/*
		 * // load music JukeBox.load("/Music/bgmusic.mp3", "music1");
		 * JukeBox.setVolume("music1", -10); JukeBox.loop("music1", 1000, 1000,
		 * JukeBox.getFrames("music1") - 1000); JukeBox.load("/Music/finish.mp3",
		 * "finish"); JukeBox.setVolume("finish", -10);
		 * 
		 * // load sfx JukeBox.load("/SFX/collect.wav", "collect");
		 * JukeBox.load("/SFX/mapmove.wav", "mapmove");
		 * JukeBox.load("/SFX/tilechange.wav", "tilechange");
		 * JukeBox.load("/SFX/splash.wav", "splash");
		 */
		// start event
		boxes = new ArrayList<Rectangle>();
		eventStart = true;
		eventStart();

	}

	private void populateDiamonds() {

		com.Diamond.Finder.Graphics.Object d;

		d = new Object(tileMap);
		d.setTilePosition(20, 20);
		d.addChange(new int[] { 23, 19, 1 });
		d.addChange(new int[] { 23, 20, 1 });
		diamonds.add(d);
		d = new Object(tileMap);
		d.setTilePosition(12, 36);
		d.addChange(new int[] { 31, 17, 1 });
		diamonds.add(d);
		d = new Object(tileMap);
		d.setTilePosition(28, 4);
		d.addChange(new int[] { 27, 7, 1 });
		d.addChange(new int[] { 28, 7, 1 });
		diamonds.add(d);
		d = new Object(tileMap);
		d.setTilePosition(4, 34);
		d.addChange(new int[] { 31, 21, 1 });
		diamonds.add(d);

		d = new Object(tileMap);
		d.setTilePosition(28, 19);
		diamonds.add(d);
		d = new Object(tileMap);
		d.setTilePosition(35, 26);
		diamonds.add(d);
		d = new Object(tileMap);
		d.setTilePosition(38, 36);
		diamonds.add(d);
		d = new Object(tileMap);
		d.setTilePosition(27, 28);
		diamonds.add(d);
		d = new Object(tileMap);
		d.setTilePosition(20, 30);
		diamonds.add(d);
		d = new Object(tileMap);
		d.setTilePosition(14, 25);
		diamonds.add(d);
		d = new Object(tileMap);
		d.setTilePosition(4, 21);
		diamonds.add(d);
		d = new Object(tileMap);
		d.setTilePosition(9, 14);
		diamonds.add(d);
		d = new Object(tileMap);
		d.setTilePosition(4, 3);
		diamonds.add(d);
		d = new Object(tileMap);
		d.setTilePosition(20, 14);
		diamonds.add(d);
		d = new Object(tileMap);
		d.setTilePosition(13, 20);
		diamonds.add(d);

	}

	private void populateItems() {

		Items item;

		item = new Items(tileMap);
		item.setType(Items.AXE);
		item.setTilePosition(26, 37);
		items.add(item);

		item = new Items(tileMap);
		item.setType(Items.BOAT);
		item.setTilePosition(12, 4);
		items.add(item);

	}

	public void update() {

		
		handleInput();
		if (eventStart)
			eventStart();
		if (eventFinish)
			eventFinish();

		if (player.numDiamonds() == player.getTotalDiamonds()) {
			eventFinish = blockInput = true;
		}

		int oldxs = xsector;
		int oldys = ysector;
		xsector = player.getx() / sectorSize;
		ysector = player.gety() / sectorSize;
		tileMap.setPosition(-xsector * sectorSize, -ysector * sectorSize);
		tileMap.update();

		if (oldxs != xsector || oldys != ysector) {
			//AudioPlayer.play("mapmove");
		}

		if (tileMap.isMoving())
			return;
		player.update();
		for (int i = 0; i < diamonds.size(); i++) {

			Object d = diamonds.get(i);
			d.update();
			if (player.intersects(d)) {
				diamonds.remove(i);
				i--;
				player.collectedDiamond();

				// AudioPlayer.play("collect");

				PlayAnime s = new PlayAnime(tileMap);
				s.setPosition(d.getx(), d.gety());
				sparkles.add(s);
				ArrayList<int[]> ali = d.getChanges();
				for (int[] j : ali) {
					tileMap.setTile(j[0], j[1], j[2]);
				}
				if (ali.size() != 0) {
					// AudioPlayer.play("tilechange");
				}

			}
		}

		for (int i = 0; i < sparkles.size(); i++) {
			PlayAnime s = sparkles.get(i);
			s.update();
			if (s.shouldRemove()) {
				sparkles.remove(i);
				i--;
			}
		}

		for (int i = 0; i < items.size(); i++) {
			Items item = items.get(i);
			if (player.intersects(item)) {
				items.remove(i);
				i--;
				item.collected(player);
				// AudioPlayer.play("collect");
				PlayAnime s = new PlayAnime(tileMap);
				s.setPosition(item.getx(), item.gety());
				sparkles.add(s);
			}
		}

	}

	public void draw(Graphics2D g) {

		tileMap.draw(g);

		player.draw(g);
		for (Object d : diamonds) {
			d.draw(g);
		}
		for (PlayAnime s : sparkles) {
			s.draw(g);
		}
		for (Items i : items) {
			i.draw(g);
		}
		hud.draw(g);
		g.setColor(java.awt.Color.BLACK);
		for (int i = 0; i < boxes.size(); i++) {
			g.fill(boxes.get(i));
		}

	}

	public void handleInput() {
		if (Keys.isPressed(Keys.ESCAPE)) {
			// AudioPlayer.stop("music1");
			gsm.setPaused(true);
		}
		if (blockInput)
			return;
		if (Keys.isDown(Keys.LEFT))
			player.setLeft();
		if (Keys.isDown(Keys.RIGHT))
			player.setRight();
		if (Keys.isDown(Keys.UP))
			player.setUp();
		if (Keys.isDown(Keys.DOWN))
			player.setDown();
		if (Keys.isPressed(Keys.SPACE))
			player.setAction();
	}

	private void eventStart() {
		eventTick++;
		if (eventTick == 1) {
			boxes.clear();
			for (int i = 0; i < 9; i++) {
				boxes.add(new Rectangle(0, i * 16, GamePane.WIDTH, 16));
			}
		}
		if (eventTick > 1 && eventTick < 32) {
			for (int i = 0; i < boxes.size(); i++) {
				Rectangle r = boxes.get(i);
				if (i % 2 == 0) {
					r.x -= 4;
				} else {
					r.x += 4;
				}
			}
		}
		if (eventTick == 33) {
			boxes.clear();
			eventStart = false;
			eventTick = 0;
		}
	}

	private void eventFinish() {
		eventTick++;
		if (eventTick == 1) {
			boxes.clear();
			for (int i = 0; i < 9; i++) {
				if (i % 2 == 0)
					boxes.add(new Rectangle(-128, i * 16, GamePane.WIDTH, 16));
				else
					boxes.add(new Rectangle(128, i * 16, GamePane.WIDTH, 16));
			}
			//AudioPlayer.stop("music1");
			//AudioPlayer.play("finish");
		}
		if (eventTick > 1) {
			for (int i = 0; i < boxes.size(); i++) {
				Rectangle r = boxes.get(i);
				if (i % 2 == 0) {
					if (r.x < 0)
						r.x += 4;
				} else {
					if (r.x > 0)
						r.x -= 4;
				}
			}
		}
//		if (eventTick > 33) {
//			if (!AudioPlayer.isPlaying("finish")) {
//				Data.setTime(player.getTicks());
//				gsm.setState(GameState.GAMEOVER);
//			}
//		}
	}

}
