package main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	public static float health = 100;
	private float greenValue = 255;
	
	private int score = 0;
	private int level = 1;
	
	public void tick(){
		health = Game.clamp(health, 0, 100);
		greenValue = Game.clamp(greenValue, 0, 255);
		
		greenValue = health*2;
		
		score++;
	}
	public void render(Graphics g){
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200, 32);
		g.setColor(new Color(75, (int) greenValue, 0));
		g.fillRect(15, 15, (int) health*2, 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 32);
		
		g.drawString("Score: " + score, 10, 64);
		g.drawString("Level: " + level, 10, 80);
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	

}
