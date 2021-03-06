package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class HardEnemy extends GameObject{
	
	private Handler handler;
	
	private Random r = new Random();

	public HardEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 6;
		velY = 6;
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int) x, (int) y, 16, 16);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		if(x <= 0 || x >= Game.width - 18){
			if(velX < 0){
				velX = r.nextInt(7)+1;
			}else{
				velX = (r.nextInt(7)+1)*-1;

			}
		}
		if(y <= 0 || y >= Game.height - 52){
			if(velY < 0){
				velY = r.nextInt(7)+1;
			}else{
				velY = (r.nextInt(7)+1)*-1;

			}
		}
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.yellow, 16, 16, 0.1f, handler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect((int) x, (int) y, 16, 16);
	}

}
