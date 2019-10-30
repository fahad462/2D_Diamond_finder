package com.Diamond.Finder.Graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.Diamond.Finder.GameMap.TilesMap;
import com.Diamond.Finder.States.Content;

public class MistStudent extends Entity {

	private BufferedImage[] downSprites;
	private BufferedImage[] leftSprites;
	private BufferedImage[] rightSprites;
	private BufferedImage[] upSprites;
	private BufferedImage[] downBoatSprites;
	private BufferedImage[] leftBoatSprites;
	private BufferedImage[] rightBoatSprites;
	private BufferedImage[] upBoatSprites;

	private final int DOWN = 0;
	private final int LEFT = 1;
	private final int RIGHT = 2;
	private final int UP = 3;
	private final int DOWNBOAT = 4;
	private final int LEFTBOAT = 5;
	private final int RIGHTBOAT = 6;
	private final int UPBOAT = 7;

	private int numDiamonds;
	private int totalDiamonds;
	private boolean hasBoat;
	private boolean hasAxe;
	private boolean onWater;
	private long ticks;

	public MistStudent(TilesMap tm) {

		super(tm);

		width = 16;
		height = 16;
		cwidth = 12;
		cheight = 12;

		moveSpeed = 2;

		numDiamonds = 0;

		downSprites = Content.PLAYER[0];
		leftSprites = Content.PLAYER[1];
		rightSprites = Content.PLAYER[2];
		upSprites = Content.PLAYER[3];
		downBoatSprites = Content.PLAYER[4];
		leftBoatSprites = Content.PLAYER[5];
		rightBoatSprites = Content.PLAYER[6];
		upBoatSprites = Content.PLAYER[7];

		animation.loadImages(downSprites);
		animation.setDelay(10);

	}

	private void setAnimation(int i, BufferedImage[] bi, int d) {
		currentAnimation = i;
		animation.loadImages(bi);
		animation.setDelay(d);
	}

	public void collectedDiamond() {
		numDiamonds++;
	}

	public int numDiamonds() {
		return numDiamonds;
	}

	public int getTotalDiamonds() {
		return totalDiamonds;
	}

	public void setTotalDiamonds(int i) {
		totalDiamonds = i;
	}

	public void gotBoat() {
		hasBoat = true;
		tileMap.replace(22, 4);
	}

	public void gotAxe() {
		hasAxe = true;
	}

	public boolean hasBoat() {
		return hasBoat;
	}

	public boolean hasAxe() {
		return hasAxe;
	}

	public long getTicks() {
		return ticks;
	}

	@Override
	public void setDown() {
		super.setDown();
	}

	@Override
	public void setLeft() {
		super.setLeft();
	}

	@Override
	public void setRight() {
		super.setRight();
	}

	@Override
	public void setUp() {
		super.setUp();
	}

	public void setAction() {
		if (hasAxe) {
			if (currentAnimation == UP && tileMap.getIndex(rowTile - 1, colTile) == 21) {
				tileMap.setTile(rowTile - 1, colTile, 1);
				// JukeBox.play("tilechange");
			}
			if (currentAnimation == DOWN && tileMap.getIndex(rowTile + 1, colTile) == 21) {
				tileMap.setTile(rowTile + 1, colTile, 1);
				// JukeBox.play("tilechange");
			}
			if (currentAnimation == LEFT && tileMap.getIndex(rowTile, colTile - 1) == 21) {
				tileMap.setTile(rowTile, colTile - 1, 1);
				// JukeBox.play("tilechange");
			}
			if (currentAnimation == RIGHT && tileMap.getIndex(rowTile, colTile + 1) == 21) {
				tileMap.setTile(rowTile, colTile + 1, 1);
				// JukeBox.play("tilechange");
			}
		}
	}

	@Override
	public void update() {

		ticks++;
		boolean current = onWater;
		if (tileMap.getIndex(ydest / tileSize, xdest / tileSize) == 4) {
			onWater = true;
		} else {
			onWater = false;
		}
		if (!current && onWater) {
			// JukeBox.play("splash");
		}

		if (down) {
			if (onWater && currentAnimation != DOWNBOAT) {
				setAnimation(DOWNBOAT, downBoatSprites, 10);
			} else if (!onWater && currentAnimation != DOWN) {
				setAnimation(DOWN, downSprites, 10);
			}
		}
		if (left) {
			if (onWater && currentAnimation != LEFTBOAT) {
				setAnimation(LEFTBOAT, leftBoatSprites, 10);
			} else if (!onWater && currentAnimation != LEFT) {
				setAnimation(LEFT, leftSprites, 10);
			}
		}
		if (right) {
			if (onWater && currentAnimation != RIGHTBOAT) {
				setAnimation(RIGHTBOAT, rightBoatSprites, 10);
			} else if (!onWater && currentAnimation != RIGHT) {
				setAnimation(RIGHT, rightSprites, 10);
			}
		}
		if (up) {
			if (onWater && currentAnimation != UPBOAT) {
				setAnimation(UPBOAT, upBoatSprites, 10);
			} else if (!onWater && currentAnimation != UP) {
				setAnimation(UP, upSprites, 10);
			}
		}

		super.update();

	}

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
	}

}
