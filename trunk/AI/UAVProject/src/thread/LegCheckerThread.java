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

	boolean bLegCompleted = false;



	public boolean isbLegCompleted() {
		return bLegCompleted;
	}



	public void setbLegCompleted(boolean bLegCompleted) {
		this.bLegCompleted = bLegCompleted;
	}



	public LegCheckerThread(Leg_CheckerAppImp oLeg_CheckerAppImp,
			Flight_Leg eiFlight_Leg) {
		super();
		this.oLeg_CheckerAppImp = oLeg_CheckerAppImp;
		this.eiFlight_Leg = eiFlight_Leg;
		bLegCompleted = false;
	}



	@Override
	public void run() {

		while(!bLegCompleted){

			long iRangeTime =(long) (Simulation.SimulationVars.iSleepTime);
			global.GlobalVarsAndMethods.sleepRandom(iRangeTime);

			jade.core.AID oPlaneAID = eiFlight_Leg.getPlaneID().id;
			Plane_Position_ServiceAppImp plane_Position_ServiceAppImp = global.GlobalVarsAndMethods.PlanesPositionApps.get(oPlaneAID);
			gov.nasa.worldwind.geom.Position oPosition = plane_Position_ServiceAppImp.getCurrentPosition();
			
			if(eiFlight_Leg.getEndPoint()!= null){
				double dDistance = BasicFlightDynamics.BFD.getDistance(eiFlight_Leg.getEndPoint(), oPosition);
				if(dDistance < 10){
					bLegCompleted = true;
					try {
						oLeg_CheckerAppImp.getOwner().getMSM().addMentalEntity(new ingenias.jade.mental.LegCompleted());
					} catch (InvalidEntity e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			else{
				double dDistanceAltitude = Math.abs(eiFlight_Leg.getAltitudeKM()*1000 - oPosition.getAltitude());
				if(dDistanceAltitude < 100)
				{
					bLegCompleted = true;
					try {
						oLeg_CheckerAppImp.getOwner().getMSM().addMentalEntity(new ingenias.jade.mental.LegCompleted());
					} catch (InvalidEntity e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			try {
				Thread.sleep((long) (Math.random()*1000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}

	}

}
