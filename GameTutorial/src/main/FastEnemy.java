package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class FastEnemy extends GameObject{
	
	private Handler handler;

	public FastEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 9;
		velY = 9;
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int) x, (int) y, 16, 16);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		if(x <= 0 || x >= Game.width - 18){
			velX *= -1;
		}
		if(y <= 0 || y >= Game.height - 52){
			velY *= -1;
		}
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.cyan, 16, 16, 0.1f, handler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect((int) x, (int) y, 16, 16);
	}

}
