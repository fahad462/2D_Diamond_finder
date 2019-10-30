package com.Diamond.Finder.States;

import java.awt.Graphics2D;

import com.Diamond.*;
import com.Diamond.Finder.AllStates.GameOver;
import com.Diamond.Finder.AllStates.Menu;
import com.Diamond.Finder.AllStates.Pause;
import com.Diamond.Finder.AllStates.Play;
import com.Diamond.Finder.AllStates.States;
import com.Diamond.Finder.AllStates.startState;

public class GameState {

	private boolean paused;
	private Pause pauseState;

	private States[] gameStates;
	private int currentState;
	private int previousState;

	public static final int NUM_STATES = 4;
	public static final int INTRO = 0;
	public static final int MENU = 1;
	public static final int PLAY = 2;
	public static final int GAMEOVER = 3;

	public GameState() {

		// JukeBox.init();

		paused = false;
		pauseState = new Pause(this);

		gameStates = new States[NUM_STATES];
		setState(INTRO);

	}

	public void setState(int i) {
		previousState = currentState;
		unloadState(previousState);
		currentState = i;
		if (i == INTRO) {
			gameStates[i] = new startState(this);
			gameStates[i].init();
		} else if (i == MENU) {
			gameStates[i] = new Menu(this);
			gameStates[i].init();
		} else if (i == PLAY) {
			gameStates[i] = new Play(this);
			gameStates[i].init();
		} else if (i == GAMEOVER) {
			gameStates[i] = new GameOver(this);
			gameStates[i].init();
		}
	}

	public void unloadState(int i) {
		gameStates[i] = null;
	}

	public void setPaused(boolean b) {
		paused = b;
	}

	public void update() {
		if (paused) {
			pauseState.update();
		} else if (gameStates[currentState] != null) {
			gameStates[currentState].update();
		}
	}

	public void draw(Graphics2D g) {
		if (paused) {
			pauseState.draw(g);
		} else if (gameStates[currentState] != null) {
			gameStates[currentState].draw(g);
		}
	}

}
