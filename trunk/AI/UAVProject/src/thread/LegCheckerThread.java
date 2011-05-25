package thread;

import java.util.Vector;

import ingenias.exception.InvalidEntity;
import ingenias.jade.components.Leg_CheckerAppImp;
import ingenias.jade.components.Plane_Position_ServiceAppImp;
import ingenias.jade.components.Plane_Position_ServiceInit;
import ingenias.jade.mental.Flight_Leg;

public class LegCheckerThread implements Runnable{
	Leg_CheckerAppImp oLeg_CheckerAppImp;
	Flight_Leg eiFlight_Leg;

	
	
	public LegCheckerThread(Leg_CheckerAppImp oLeg_CheckerAppImp,
			Flight_Leg eiFlight_Leg) {
		super();
		this.oLeg_CheckerAppImp = oLeg_CheckerAppImp;
		this.eiFlight_Leg = eiFlight_Leg;
	}



	@Override
	public void run() {

		boolean bLegCompleted = false;
		while(!bLegCompleted){
			Vector<Plane_Position_ServiceAppImp> oVector = Plane_Position_ServiceInit.getAppsInitialised();
	
			for (Plane_Position_ServiceAppImp plane_Position_ServiceAppImp : oVector) {
				jade.core.AID oPlaneAID = eiFlight_Leg.getPlaneID().id;
				if(oPlaneAID.equals(plane_Position_ServiceAppImp.getOwner().getAID())){
	
					gov.nasa.worldwind.geom.Position oPosition = plane_Position_ServiceAppImp.getCurrentPosition();
					double dDistance = BasicFlightDynamics.BFD.getDistance(eiFlight_Leg.getEndPoint(), oPosition);
					if(dDistance < 20){
						bLegCompleted = true;
						try {
							oLeg_CheckerAppImp.getOwner().getMSM().addMentalEntity(new ingenias.jade.mental.LegCompleted());
						} catch (InvalidEntity e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					break;
				}
			}
			
			 long iRangeTime =(long) (Simulation.SimulationVars.iSleepTime * Math.pow(global.GlobalVarsAndMethods.PlaneIdToPilotId.size(),2));
		     global.GlobalVarsAndMethods.sleepRandom(iRangeTime);
		        	

		}
		
	}

}
