package com.Diamond.Finder.AllStates;

import java.awt.Graphics2D;

import com.Diamond.Finder.States.AudioPlayer;
import com.Diamond.Finder.States.Content;
import com.Diamond.Finder.States.GameState;
import com.Diamond.Finder.States.Keys;


public class Pause extends States{

	public Pause(GameState gsm) {
		super(gsm);
	}
	
	public void init() {}
	
	public void update() {
		handleInput();
	}
	
	public void draw(Graphics2D g) {
		
		Content.drawString(g, "paused", 40, 30);
		
		Content.drawString(g, "arrow", 12, 76);
		Content.drawString(g, "keys", 16, 84);
		Content.drawString(g, ": move", 52, 80);
		
		Content.drawString(g, "space", 12, 96);
		Content.drawString(g, ": action", 52, 96);
		
		Content.drawString(g, "F1:", 36, 112);
		Content.drawString(g, "return", 68, 108);
		Content.drawString(g, "to menu", 68, 116);
		
	}
	public void handleInput() {
		if(Keys.isPressed(Keys.ESCAPE)) {
			gsm.setPaused(false);
			//AudioPlayer.resumeLoop("music1");
		}
		if(Keys.isPressed(Keys.F1)) {
			gsm.setPaused(false);
			gsm.setState(GameState.MENU);
		}
	}
	
}
