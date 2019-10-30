package com.Diamond.Finder.Graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.Diamond.Finder.GameMap.TilesMap;
import com.Diamond.Finder.States.Content;

public class Object extends Entity {

	BufferedImage sprites[];
	ArrayList<int[]> tileChanges;

	public Object(TilesMap map) {
		super(map);
		width = 16;
		height = 16;
		cwidth = 12;
		cheight = 12;
		
		sprites = Content.DIAMOND[0];
		animation.loadImages(sprites);
		animation.setDelay(10);

		tileChanges = new ArrayList<int[]>();
	}
	
	public void addChange(int[] i) {
		tileChanges.add(i);
	}
	public ArrayList<int[]> getChanges() {
		return tileChanges;
	}
	
	public void update() {
		animation.update();
	}
	
	public void draw(Graphics2D g) {
		super.draw(g);
	}

}
