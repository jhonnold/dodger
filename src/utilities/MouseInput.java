package utilities;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseMotionListener {

	private int x = 0, y = 0;
	
	public int getX() { return x; }
	public int getY() { return y; }
	
	@Override
	public void mouseDragged(MouseEvent e) {}
	
	// Simply update its coordinates in the tracking variables
	@Override
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}

		
	
}
