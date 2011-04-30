

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
import ingenias.jade.mental.Flight_Leg;



public  class Leg_CheckerAppImp extends Leg_CheckerApp{

	public Leg_CheckerAppImp(){
		super();
	}




	public boolean isLegCompleted(Flight_Leg eiFlight_Leg){
		boolean bResutl = false;

		Vector<Plane_Position_ServiceAppImp> oVector = Plane_Position_ServiceInit.getAppsInitialised();

		for (Plane_Position_ServiceAppImp plane_Position_ServiceAppImp : oVector) {
			jade.core.AID oPlaneAID = eiFlight_Leg.getPlaneID().id;
			if(oPlaneAID.equals(plane_Position_ServiceAppImp.getOwner().getAID())){

				gov.nasa.worldwind.geom.Position oPosition = plane_Position_ServiceAppImp.getCurrentPosition();
				double dDistance = BasicFlightDynamics.BFD.getDistance(eiFlight_Leg.getEndPoint(), oPosition);
				if(dDistance < 10){
					bResutl =  true;
				}
				break;
			}

		}
		return bResutl;
	}

}

