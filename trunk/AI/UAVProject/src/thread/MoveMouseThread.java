package thread;

import gov.nasa.worldwind.awt.WorldWindowGLCanvas;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;


public class MoveMouseThread implements Runnable{
	WorldWindowGLCanvas wwd;
	

	public MoveMouseThread(WorldWindowGLCanvas wwd) {
		this.wwd = wwd;
	}

	@Override
	public void run() {
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean finish = true;
		int i = 0;
		while(!finish){
			if(robot != null)
			{
				try {
					Point p = wwd.getLocationOnScreen();
					
					robot.mouseMove((int) (p.x + wwd.getSize().width - ((i%2)+1)), p.y + wwd.getSize().height);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				finish = true;
			}
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
		}
		
	}
	
}
