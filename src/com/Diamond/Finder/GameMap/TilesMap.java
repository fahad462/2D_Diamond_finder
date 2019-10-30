package com.Diamond.Finder.GameMap;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import com.*;
import com.Diamond.Finder.UI.GamePane;

public class TilesMap {

	int x, y;
	int desX, desY;
	int speed;
	Boolean isMoving;

	int minX, minY;
	int maxX, maxY;

	int[][] grid;
	int width, height;
	int Rows, Columns;
	int sizeOfTile;

	BufferedImage tileset;
	int numOfTilesSurrounded;
	Tiles[][] tiles;

	int row_bakiase, column_bakiase;
	int rows_drawKorteHobe, column_drawKorteHobe;

	public TilesMap(int sizeOfTile) {
		this.sizeOfTile = sizeOfTile;
		rows_drawKorteHobe = GamePane.HEIGHT / sizeOfTile + 2;
		column_drawKorteHobe = GamePane.WIDTH / sizeOfTile + 2;
		speed = 4;
	}

	public void loadTiles(String s) {
		try {

			tileset = ImageIO.read(getClass().getResourceAsStream(s));
			numOfTilesSurrounded = tileset.getWidth() / sizeOfTile;

			tiles = new Tiles[2][numOfTilesSurrounded];

			BufferedImage subimage;
			for (int col = 0; col < numOfTilesSurrounded; col++) {
				subimage = tileset.getSubimage(col * sizeOfTile, 0, sizeOfTile, sizeOfTile);
				tiles[0][col] = new Tiles(subimage, Tiles.OPEN);
				subimage = tileset.getSubimage(col * sizeOfTile, sizeOfTile, sizeOfTile, sizeOfTile);
				tiles[1][col] = new Tiles(subimage, Tiles.BLOCKED);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void loadMap(String s) {

		try {

			InputStream in = getClass().getResourceAsStream(s);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			Columns = Integer.parseInt(br.readLine());
			Rows = Integer.parseInt(br.readLine());
			grid = new int[Rows][Columns];
			width = Columns * sizeOfTile;
			height = Rows * sizeOfTile;

			minX = GamePane.WIDTH - width;
			minX = -width;
			maxX = 0;
			minY = GamePane.HEIGHT - height;
			minY = -height;
			maxY = 0;

			String delims = "\\s+";
			for (int row = 0; row < Rows; row++) {
				String line = br.readLine();
				String[] tokens = line.split(delims);
				for (int col = 0; col < Columns; col++) {
					grid[row][col] = Integer.parseInt(tokens[col]);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public int getTileSize() {
		return sizeOfTile;
	}

	public int getx() {
		return x;
	}

	public int gety() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getNumRows() {
		return Rows;
	}

	public int getNumCols() {
		return Columns;
	}

	public int getType(int row, int col) {
		int rc = grid[row][col];
		int r = rc / numOfTilesSurrounded;
		int c = rc % numOfTilesSurrounded;
		return tiles[r][c].getType();
	}

	public int getIndex(int row, int col) {
		return grid[row][col];
	}

	public boolean isMoving() {
		return isMoving;
	}

	public void setTile(int row, int col, int index) {
		grid[row][col] = index;
	}

	public void replace(int i1, int i2) {
		for (int row = 0; row < Rows; row++) {
			for (int col = 0; col < Columns; col++) {
				if (grid[row][col] == i1)
					grid[row][col] = i2;
			}
		}
	}

	public void setPosition(int x, int y) {
		desX = x;
		desY = y;
	}

	public void setPositionImmediately(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void fixBounds() {
		if (x < minX)
			x = minX;
		if (y < minY)
			y = minY;
		if (x > maxX)
			x = maxX;
		if (y > maxY)
			y = maxY;
	}

	public void update() {
		if (x < desX) {
			x += speed;
			if (x > desX) {
				x = desX;
			}
		}
		if (x > desX) {
			x -= speed;
			if (x < desX) {
				x = desX;
			}
		}
		if (y < desY) {
			y += speed;
			if (y > desY) {
				y = desY;
			}
		}
		if (y > desY) {
			y -= speed;
			if (y < desY) {
				y = desY;
			}
		}

		fixBounds();

		column_bakiase = -this.x / sizeOfTile;
		row_bakiase = -this.y / sizeOfTile;

		if (x != desX || y != desY)
			isMoving = true;
		else
			isMoving = false;

	}

	public void draw(Graphics2D g) {

		for (int row = row_bakiase; row < row_bakiase + rows_drawKorteHobe; row++) {

			if (row >= Rows)
				break;

			for (int col = column_bakiase; col < column_bakiase + column_drawKorteHobe; col++) {

				if (col >= Columns)
					break;
				if (grid[row][col] == 0)
					continue;

				int rc = grid[row][col];
				int r = rc / numOfTilesSurrounded;
				int c = rc % numOfTilesSurrounded;

				g.drawImage(tiles[r][c].getImage(), x + col * sizeOfTile, y + row * sizeOfTile, null);

			}

		}

	}

}
