package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import entities.Enemy;
import utilities.MouseInput;

@SuppressWarnings("serial")
public class Arena extends JPanel {
	
	private MouseInput input;
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	
	private int mx = 0, my = 0;
	private int newEnemy = 200;
	
	private long startTime;
	
	public Arena() {
		setPreferredSize(new Dimension(1920, 1080));
		addMouseMotionListener(input = new MouseInput());
		
		for (int i = 0; i < 5; i++) {
			enemies.add(new Enemy(Math.random() * 1920, Math.random() * 1080));
		}
		
		startTime = System.currentTimeMillis();
		
	}
	
	@Override
	public void paint(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		g2.setColor(Color.GREEN);
		g2.fillRect(mx, my, 30, 30);
		
		g2.setColor(Color.RED);
		for (Enemy e: enemies) {
			e.draw(g2);
		}
		
		g2.setColor(Color.WHITE);
		g2.drawString("Score: " + (System.currentTimeMillis() - startTime) / 1000, 0, 10);
	}
	
	
	public boolean update() {
		
		boolean collided = false;
		
		mx = input.getX();
		my = input.getY();
		
		for (Enemy e : enemies) {
			collided = e.update(mx, my);
			if (collided) {
				return true;
			}
		}
		
		newEnemy--;
		if (newEnemy <= 0) {
			enemies.add(new Enemy(Math.random() * 1920, Math.random() * 1080));
			newEnemy = 200;
		}
		
		return false;
	}
	
}
