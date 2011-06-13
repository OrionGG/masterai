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

				ArrayList<jade.core.AID> listPlanesInConflict = 
					new ArrayList<jade.core.AID>();

				Plane_Position_ServiceAppImp oFirstService = oVector.get(i);

				gov.nasa.worldwind.geom.Position oPositionFirstService = null;
				gov.nasa.worldwind.geom.Angle oHeadFirstService = null;
				double dSpeedFirstService = 0;
				try{
					oPositionFirstService = oFirstService.getCurrentPosition();
					oHeadFirstService = oFirstService.getCurrentHead();
					dSpeedFirstService = oFirstService.getCurrentSpeed();

				}
				catch(Exception ex){
					continue;
				}

				if(dSpeedFirstService > 0){

					for (int j = i+1; j < oVector.size(); j++) {
						Plane_Position_ServiceAppImp oSecondService = oVector.get(j);
						double dDistance = Double.MAX_VALUE;
						double dDistanceWithHigh = Double.MAX_VALUE;
						double dDistanceHigh = Double.MAX_VALUE;
						gov.nasa.worldwind.geom.Position oPositionSecondService = null;
						gov.nasa.worldwind.geom.Angle oHeadSecondService = null;
						double dSpeedSecondService = 0;
						
						try{
							oPositionSecondService = oSecondService.getCurrentPosition();
							oHeadSecondService = oSecondService.getCurrentHead();
							dSpeedSecondService = oSecondService.getCurrentSpeed();
							dDistance = BasicFlightDynamics.BFD.getDistance(
									oPositionFirstService, oPositionSecondService);
							
							dDistanceWithHigh = BasicFlightDynamics.BFD.getDistanceWithHigh(
									oPositionFirstService , oPositionSecondService);
							dDistanceHigh = Math.abs(oPositionFirstService.getAltitude() 
									- oPositionSecondService.getAltitude())/1000;
						}
						catch(Exception ex){

						}
						
						if(dSpeedSecondService <= 0){
							continue;
						}
						
						if(dDistance < global.GlobalVarsAndMethods.dAwarenessDistance * Simulation.SimulationVars.x){//6 miles
							listPlanesInConflict.add(oSecondService.getOwner().getAID());
						}
						
						if(dDistanceWithHigh < (global.GlobalVarsAndMethods.dAwarenessDistance * Simulation.SimulationVars.x)/10){//6 miles
							if(dDistanceHigh < 0.5)
							{
								int a = 0;
							}
						}

					}

					if(listPlanesInConflict.size() != 0){
						listPlanesInConflict.add(oFirstService.getOwner().getAID());
						ingenias.jade.mental.PlanesInConflict oPlanesInConflict = 
							new ingenias.jade.mental.PlanesInConflict();

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
