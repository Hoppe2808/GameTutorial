package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBoss extends GameObject{
	
	private Handler handler;
	private int timer = 80;
	private int shootTimer = 50;
	private Random r = new Random();

	public EnemyBoss(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 0;
		velY = 2;
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int) x, (int) y, 96, 96);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		if(timer <= 0){
			velY = 0;
			shootTimer--;
		}else{
			timer--;
		}
		
		if(shootTimer <= 0){
			if(velX == 0){
				velX = 2;
			}
			int spawn = r.nextInt(2);
			if(spawn == 0){
				handler.addObject(new EnemyBossBullet((int) x+48, (int) y+48, ID.BasicEnemy, handler));
			}
		}
		if(x <= 0 || x >= Game.width - 100){
			velX *= -1;
		}
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.red, 96, 96, 0.1f, handler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int) x, (int) y, 96, 96);
	}

}
