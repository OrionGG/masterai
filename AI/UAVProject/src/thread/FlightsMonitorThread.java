package thread;

import ingenias.jade.components.FlightsMonitorAppImp;
import ingenias.jade.components.Plane_Position_ServiceAppImp;
import ingenias.jade.components.Plane_Position_ServiceInit;

import java.util.ArrayList;
import java.util.Vector;

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


				for (int j = i+1; j < oVector.size(); j++) {
					Plane_Position_ServiceAppImp oSecondService = oVector.get(j);
					double dDistance = Double.MAX_VALUE;
					try{
						dDistance = BasicFlightDynamics.BFD.getDistance(
							oFirstService.getCurrentPosition(), oSecondService.getCurrentPosition());
					}
					catch(Exception ex){
						
					}
					if(dDistance < 10){
						ingenias.jade.mental.PlanesInConflict oPlanesInConflict = 
							new ingenias.jade.mental.PlanesInConflict();

						ArrayList<ingenias.jade.agents.PlaneJADEAgent> listPlanesInConflict = 
							new java.util.ArrayList<ingenias.jade.agents.PlaneJADEAgent>();

						listPlanesInConflict.add(
								(ingenias.jade.agents.PlaneJADEAgent)oFirstService.getOwner());
						listPlanesInConflict.add(
								(ingenias.jade.agents.PlaneJADEAgent)oSecondService.getOwner());
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
			global.GlobalVarsAndMethods.sleep(Simulation.SimulationVars.iSleepTime);
		}
		
	}

}
