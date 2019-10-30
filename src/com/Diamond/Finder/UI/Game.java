package com.Diamond.Finder.UI;

import javax.swing.*;

public class Game {
	
	public static void main(String[] args) {
		JFrame game = new JFrame ();
		game.setResizable(false);
		game.add(new GamePane());
		game.pack();
		game.setLocationRelativeTo(null);
		game.setVisible(true);
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
