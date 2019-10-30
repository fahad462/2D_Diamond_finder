package com.Diamond.Finder.AllStates;

import java.awt.Graphics2D;

import com.Diamond.Finder.States.GameState;

public abstract class States {
	protected GameState gsm;

	public States(GameState gsm) {
		this.gsm = gsm;
	}

	public abstract void init();

	public abstract void update();

	public abstract void draw(Graphics2D g);

	public abstract void handleInput();
}
