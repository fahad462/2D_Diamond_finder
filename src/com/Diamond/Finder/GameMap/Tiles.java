package com.Diamond.Finder.GameMap;

import java.awt.image.BufferedImage;

public class Tiles {

	BufferedImage image;
	int type;
	public static int OPEN = 0;
	public static int BLOCKED = 1;

	Tiles(BufferedImage image, int type) {
		this.image = image;
		this.type = type;
	}

	public BufferedImage getImage() {
		return image;
	}

	public int getType() {
		return type;
	}
}
