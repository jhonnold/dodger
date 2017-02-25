package game;

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import ui.Arena;

import javax.swing.JFrame;

public class Game implements Runnable, ActionListener {
	
	// Stuff needed for the main
	private boolean collision = false;
	private JFrame f;
	private Arena arena;
	private Timer t = new Timer(10, this);
	
	// Constants for ticks and keeping on track
	private final int TICKS_PER_SECOND = 200;
	private final int SKIP_TICKS = 1000 / TICKS_PER_SECOND;
	private final int MAX_FRAMESKIP = 5;
	
	public Game() {
		f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Make the cursor invisible
		f.setCursor(f.getToolkit().createCustomCursor(
	            new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0),
	            "null"));
		
		f.add(arena = new Arena());
		f.pack();	// Properly size the frame
		
		f.setResizable(false);
		f.setVisible(true);
		t.start();
	}
	
	@Override
	public void run() {	
		double next_game_tick = System.currentTimeMillis();
		int loops;
		
		// Game Loop
		while (!collision) {
			loops = 0;
			// Update loop
			while (System.currentTimeMillis() > next_game_tick && loops < MAX_FRAMESKIP) {
				
				collision = arena.update();
				
				next_game_tick += SKIP_TICKS;
				loops++;
			}
		}
		
		// Draw the last collision and close
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {}
		System.exit(1);
		
	}

	// The redraw on a 100 FPS thread
	// The timer that triggers this is on a separate thread
	// from that of the draw so those remain independent
	@Override
	public void actionPerformed(ActionEvent arg0) {
		f.repaint();
		Toolkit.getDefaultToolkit().sync();
	}
	
	public static void main(String[] args) {
		new Thread(new Game()).start();
	}
	
}
