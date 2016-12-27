package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import main.Game.STATE;

public class Menu extends MouseAdapter{
	
	private Game game;
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	public Menu(Game game, Handler handler, HUD hud){
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}

	public void mousePressed(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		
		if(game.gameState == STATE.Menu){
			//Play
			if(mouseOver(mx, my, 325, 150, 400, 128)){
				/*
				game.gameState = STATE.Game;
				handler.object.clear();
				handler.addObject(new Player(Game.width/2-32, Game.height/2-32, ID.Player, handler));
				handler.addObject(new BasicEnemy(r.nextInt(Game.width - 50), r.nextInt(Game.height - 50), ID.BasicEnemy, handler));
				*/
				game.gameState = STATE.Select;
				//Til at sætte klick lyd ind: AudioPlayer.getSound("*name*").play();
				return;
			}
			//Help
			if(mouseOver(mx, my, 325, 300, 400, 128)){
				game.gameState = STATE.Help;
			}
			//Quit
			if(mouseOver(mx, my, 325, 450, 400, 128)){
				System.exit(1);
			}
		}
		if(game.gameState == STATE.Select){
			//Normal
			if(mouseOver(mx, my, 325, 150, 400, 128)){
				game.gameState = STATE.Game;
				handler.object.clear();
				handler.addObject(new Player(Game.width/2-32, Game.height/2-32, ID.Player, handler));
				handler.addObject(new BasicEnemy(r.nextInt(Game.width - 50), r.nextInt(Game.height - 50), ID.BasicEnemy, handler));

				game.diff = 0;
				//Til at sætte klick lyd ind: AudioPlayer.getSound("*name*").play();
			}
			//Hard
			if(mouseOver(mx, my, 325, 300, 400, 128)){
				game.gameState = STATE.Game;
				handler.object.clear();
				handler.addObject(new Player(Game.width/2-32, Game.height/2-32, ID.Player, handler));
				handler.addObject(new HardEnemy(r.nextInt(Game.width - 50), r.nextInt(Game.height - 50), ID.HardEnemy, handler));

				game.diff = 1;
				//Til at sætte klick lyd ind: AudioPlayer.getSound("*name*").play();
			}
			//Back
			if(mouseOver(mx, my, 325, 450, 400, 128)){
				game.gameState = STATE.Menu;
				return;
			}
		}
		
		//Back in Help
		if(mouseOver(mx, my, 325, 450, 400, 128) && game.gameState == STATE.Help){
			game.gameState = STATE.Menu;
			return;
		}
		//Retry button
		if(mouseOver(mx, my, 325, 450, 400, 128) && game.gameState == STATE.End){
			game.gameState = STATE.Menu;
			hud.setLevel(1);
			hud.setScore(0);
			return;
		}
		
	}
	public void mouseReleased(MouseEvent e){
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
		if(mx > x && mx < x + width){
			if(my > y && my < y + height){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	public void tick(){
		
	}
	public void render(Graphics g){
		if(game.gameState == STATE.Menu){
		Font fnt = new Font("arial", 1, 50);
		
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("Wave", 455, 75);
		
		g.drawRect(325, 150, 400, 128);
		g.drawString("Play", 475, 230);
		
		g.drawRect(325, 300, 400, 128);
		g.drawString("Help", 475, 380);
		
		g.drawRect(325, 450, 400, 128);
		g.drawString("Quit", 475, 530);
		}else if(game.gameState == STATE.Help){
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 25);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Help", 455, 75);
			
			g.setFont(fnt2);
			g.drawString("Use WASD to move around the map", 315, 150);
			g.drawString("Dodge the various enemies bouncing around the map", 315, 200);
			g.drawString("Try to stay alive as long as you can", 315, 250);
			
			g.setFont(fnt);
			g.drawRect(325, 450, 400, 128);
			g.drawString("Back", 475, 530);
		}else if(game.gameState == STATE.End){
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 25);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("GAME OVER!", 363, 75);
			
			g.setFont(fnt2);
			g.drawString("You lost with a score of: " + hud.getScore(), 350, 250);
			
			g.setFont(fnt);
			g.drawRect(325, 450, 400, 128);
			g.drawString("Try again", 410, 530);
		}else if(game.gameState == STATE.Select){
			Font fnt = new Font("arial", 1, 50);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("SELECT DIFFICULTY", 285, 75);
			
			g.drawRect(325, 150, 400, 128);
			g.drawString("Normal", 475, 230);
			
			g.drawRect(325, 300, 400, 128);
			g.drawString("Hard", 475, 380);
			
			g.drawRect(325, 450, 400, 128);
			g.drawString("Back", 475, 530);
			}
	}
}
