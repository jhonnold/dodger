package entities;

import java.awt.Graphics2D;

public class Enemy {
	
	private double x = 0, y = 0;
	private int width, height;
	private double vx, vy;
	
	public Enemy(double x, double y) {
		this.x = x;
		this.y = y;
		
		// Size of the enemy (10 - 50)
		width = 10 + (int)(Math.random() * 40);
		height = width;
		
		// Velocities of the enemy (-2.5, 2.5)
		vx = Math.random() * 5 - 2.5;
		vy = Math.random() * 5 - 2.5;
		
	}
	
	public void draw(Graphics2D g2) {
		// Draw rect
		g2.fillRect((int)x, (int)y, width, height);
	}
	
	public boolean update(int mx, int my) {
		// Adjust based on velocity
		x += vx;
		y += vy;
		
		// If @ edge then force flush and
		// reverse its direction
		if (x < 0) {
			x = 0;
			vx *= -1;
		}
		
		if (y < 0) {
			y = 0;
			vy *= -1;
		}
		
		if (x + width > 1920) {
			x = 1920 - width;
			vx *= -1;
		}
		
		if (y + height > 1080) {
			y = 1080 - height;
			vy *= -1;
		}
		
		// Return collision
		return (collided(mx, my));
	}
	
	private boolean collided(int mx, int my) {
		// Collision checking algorithm
		// R1.X1 < R2.X2 && R1.X2 > R2.X1 && R1.Y1 < R2.Y2 && R2.Y2 > R2.Y1
		return (mx < x + width && mx + 30 > x && my < y + height && my + 30 > y);
	}
	
}
