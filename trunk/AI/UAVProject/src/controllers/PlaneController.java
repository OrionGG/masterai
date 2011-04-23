package Controllers;

import views.PlaneView;
import entities.PlaneEntity;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;

public class PlaneController implements Runnable {
	
	public static PlaneController oPlaneController;

	public static PlaneEntity OPlaneEntity;
	public static PlaneView OPlaneView;
	
	public PlaneController(String sPlaneName, Position oPosition) 
	{
		OPlaneEntity = new PlaneEntity(oPosition);
		OPlaneView = new PlaneView(OPlaneEntity, sPlaneName);
		oPlaneController = this;
	}
	
	public void run() {
		while(!arriveToDestination()){
			Position oPos = OPlaneEntity.getoPosition();
			Position oNewPos = new Position(oPos.latitude, Angle.fromDegrees(oPos.longitude.degrees - 0.0001), oPos.elevation);
			setPosition(oNewPos);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void setPosition(Position oNewPos) {
		OPlaneEntity.setoPosition(oNewPos);
		OPlaneView.setNewPosition(oNewPos);
	}
	
	private boolean arriveToDestination() {
		return (OPlaneEntity.getoPosition() == OPlaneEntity.getoDestination());
	}

	private static Thread oThread;

	public static void start() {
		//java.awt.EventQueue.invokeLater(oPlaneController);
		oThread = new Thread(oPlaneController);
		oThread.start();
	}
}
