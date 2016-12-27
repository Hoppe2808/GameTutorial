package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject{
	
	Random r = new Random();
	Handler handler;

	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}
	public Rectangle getBounds(){
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;

		x = Game.clamp(x, 0, Game.width - 38);
		y = Game.clamp(y, 0, Game.height - 72);
		
		collision();
	}
	
	private void collision(){
		for (int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy
					|| tempObject.getId() == ID.SmartEnemy || tempObject.getId() == ID.HardEnemy){
				if(getBounds().intersects(tempObject.getBounds())){
					HUD.health -= 2;
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {		
		g.setColor(Color.white);
		g.fillRect((int) x, (int) y, 32, 32);
	}

}
