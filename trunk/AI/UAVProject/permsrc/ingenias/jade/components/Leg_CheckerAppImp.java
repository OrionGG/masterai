

/**
 *
 *
 *  Description of the task /
 *
 * 
 *@author     Jorge J. Gomez
 *@version    1.0
 */

package ingenias.jade.components;

import java.util.*;
import ingenias.jade.exception.*;



public  class Leg_CheckerAppImp extends Leg_CheckerApp{
	thread.LegCheckerThread oLegCheckerThread;
	

 public Leg_CheckerAppImp(){
  super();
 }


 public boolean isLegCompleted(ingenias.jade.mental.Flight_Leg eiFlight_Leg){
			boolean bResutl = false;
			jade.core.AID oPlaneAID = eiFlight_Leg.getPlaneID().id;
	        Plane_Position_ServiceAppImp plane_Position_ServiceAppImp = global.GlobalVarsAndMethods.PlanesPositionApps.get(oPlaneAID);
				
	        gov.nasa.worldwind.geom.Position oPosition = plane_Position_ServiceAppImp.getCurrentPosition();
	        double dDistance = BasicFlightDynamics.BFD.getDistance(eiFlight_Leg.getEndPoint(), oPosition);
			if(dDistance < 10){
				bResutl =  true;
			}
			
			return bResutl;
} 
 
 public void start(ingenias.jade.mental.Flight_Leg eiFlight_Leg){

	 if(oLegCheckerThread != null){
		 oLegCheckerThread.setbLegCompleted(true);
	 }
	oLegCheckerThread = new thread.LegCheckerThread(this, eiFlight_Leg);
	Thread thread = new Thread(oLegCheckerThread);
	thread.start();

}
 
}

 