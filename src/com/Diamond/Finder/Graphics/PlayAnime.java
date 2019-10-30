package com.Diamond.Finder.Graphics;

import java.awt.Graphics2D;

import com.Diamond.Finder.GameMap.TilesMap;
import com.Diamond.Finder.States.Content;

public class PlayAnime extends Entity {

	private boolean remove;

	public PlayAnime(TilesMap tm) {
		super(tm);
		animation.loadImages(Content.SPARKLE[0]);
		animation.setDelay(5);
		width = height = 16;
	}

	public boolean shouldRemove() {
		return remove;
	}

	public void update() {
		animation.update();
		if (animation.hasPlayedOnce())
			remove = true;
	}

	public void draw(Graphics2D g) {
		super.draw(g);
	}

}
