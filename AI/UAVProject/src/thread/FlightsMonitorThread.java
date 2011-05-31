package thread;

import ingenias.jade.components.FlightsMonitorAppImp;
import ingenias.jade.components.Plane_Position_ServiceAppImp;
import ingenias.jade.components.Plane_Position_ServiceInit;

import java.util.*;

public class FlightsMonitorThread  implements Runnable{
	FlightsMonitorAppImp flightsMonitorAppImp;

	public FlightsMonitorThread(FlightsMonitorAppImp flightsMonitorAppImp) {
		this.flightsMonitorAppImp = flightsMonitorAppImp;
	}

	@Override
	public void run() {
		while(true){

			Vector<Plane_Position_ServiceAppImp> oVector = Plane_Position_ServiceInit.getAppsInitialised();
			for (int i = 0; i < oVector.size()-1; i++) {

				Plane_Position_ServiceAppImp oFirstService = oVector.get(i);
				
				gov.nasa.worldwind.geom.Position oPositionFirstService = null;
				gov.nasa.worldwind.geom.Angle oHeadFirstService = null;
				try{
					oPositionFirstService = oFirstService.getCurrentPosition();
					oHeadFirstService = oFirstService.getCurrentHead();
					
				}
				catch(Exception ex){
					continue;
				}

				for (int j = i+1; j < oVector.size(); j++) {
					Plane_Position_ServiceAppImp oSecondService = oVector.get(j);
					double dDistance = Double.MAX_VALUE;
					gov.nasa.worldwind.geom.Position oPositionSecondService = null;
					gov.nasa.worldwind.geom.Angle oHeadSecondService = null;
					try{
						oPositionSecondService = oSecondService.getCurrentPosition();
						oHeadSecondService = oSecondService.getCurrentHead();
						dDistance = BasicFlightDynamics.BFD.getDistance(
								oPositionFirstService, oPositionSecondService);
					}
					catch(Exception ex){
						
					}
					if(dDistance < global.GlobalVarsAndMethods.dAwarenessDistance * Simulation.SimulationVars.x){//6 miles
						ingenias.jade.mental.PlanesInConflict oPlanesInConflict = 
							new ingenias.jade.mental.PlanesInConflict();
						//Map.Entry<gov.nasa.worldwind.geom.Position, gov.nasa.worldwind.geom.Angle> oEntry;
						Hashtable<jade.core.AID, gov.nasa.worldwind.geom.Position> listPlanesInConflict = 
							new Hashtable<jade.core.AID, gov.nasa.worldwind.geom.Position>();

						listPlanesInConflict.put(oFirstService.getOwner().getAID(), oPositionFirstService);
						listPlanesInConflict.put(oSecondService.getOwner().getAID(), oPositionSecondService);
						oPlanesInConflict.setPlanesInConflict(listPlanesInConflict);

						try {
							flightsMonitorAppImp.getOwner().getMSM().addMentalEntity(oPlanesInConflict);

						} catch (ingenias.exception.InvalidEntity e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

				}
			}
			long iRangeTime =(long) (Simulation.SimulationVars.iSleepTime * Math.pow(global.GlobalVarsAndMethods.PlaneIdToPilotId.size(),2));
	        
			global.GlobalVarsAndMethods.sleepRandom(iRangeTime);
		}
		
	}

}
