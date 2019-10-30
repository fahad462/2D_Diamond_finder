package com.Diamond.Finder.Graphics;

import java.awt.image.BufferedImage;

public class Animation {

	private BufferedImage swfs[];
	private int currentf;
	int numberf;
	int count;
	int delay;
	int playedtimes;

	Animation() {
		numberf = 0;
	}

	public void loadImages(BufferedImage[] images) {
		this.swfs = images;
		currentf = 0;
		count = 0;
		playedtimes = 0;
		delay = 2;
		numberf = images.length;
	}

	void setDelay(int delay) {
		this.delay = delay;
	}

	void setFrame(int frame) {
		this.currentf = frame;
	}

	void numOfFrames(int numberf) {
		this.numberf = numberf;
	}

	void update() {
		if (delay == -1) {
			return;
		}
		count++;
		if (count == delay) {
			currentf++;
			count = 0;
		}
		if (currentf == numberf) {
			currentf = 0;
			playedtimes++;
		}
	}

	public int getFrame() {
		return currentf;
	}

	public int getCount() {
		return count;
	}

	public BufferedImage getImage() {
		return swfs[currentf];
	}

	public boolean hasPlayedOnce() {
		return playedtimes > 0;
	}

	public boolean hasPlayed(int i) {
		return playedtimes == i;
	}

}
