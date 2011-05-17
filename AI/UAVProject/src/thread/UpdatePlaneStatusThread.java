package thread;

import java.util.Date;
import java.util.Vector;

import views.PlaneView;

import Simulation.SimulationVars;


import ingenias.editor.entities.MentalEntity;
import ingenias.jade.agents.PlaneJADEAgent;
import ingenias.jade.components.UpdatePlaneStatusAppImp;
import ingenias.jade.mental.Plane_Mind;

public class UpdatePlaneStatusThread  implements Runnable{

	Plane_Mind eiPlane_Mind;
	UpdatePlaneStatusAppImp oUpdatePlaneStatusAppImp;
	PlaneView oPlaneView;
	



	public UpdatePlaneStatusThread(Plane_Mind eiPlane_Mind, UpdatePlaneStatusAppImp oUpdatePlaneStatusAppImp) {
		super();
		this.eiPlane_Mind = eiPlane_Mind;
		this.oUpdatePlaneStatusAppImp = oUpdatePlaneStatusAppImp;
		gov.nasa.worldwind.geom.Position oPosition = new gov.nasa.worldwind.geom.Position(
				eiPlane_Mind.getLatLonPosition(),
				eiPlane_Mind.getAltitudeKM());
		oPlaneView = new PlaneView((PlaneJADEAgent)oUpdatePlaneStatusAppImp.getOwner(), "Plane", oPosition);
		Simulation.SimulationVars.lPlanesFlying.add(oPlaneView);
		oPlaneView.render(Simulation.SimulationVars.layer);
	}

	@Override
	public void run() {

		long lMiliseconds = SimulationVars.iSleepTime * SimulationVars.x;
		
    	double speed = eiPlane_Mind.getSpeedKMH();
		while (speed != 0){

			global.GlobalVarsAndMethods.sleep(SimulationVars.iSleepTime);

			gov.nasa.worldwind.geom.Position oPosition = new gov.nasa.worldwind.geom.Position(
					eiPlane_Mind.getLatLonPosition(),
					eiPlane_Mind.getAltitudeKM()*1000);
			
			oPlaneView.setNewPosition(oPosition);

			
			double lat1 = eiPlane_Mind.getLatLonPosition().getLatitude().radians;
	    	double lon1 = eiPlane_Mind.getLatLonPosition().getLongitude().radians;
	    	double brng = eiPlane_Mind.getHead().radians;
	    	speed = eiPlane_Mind.getSpeedKMH();
	    	    	
	    	Date oLastUpdateDate = eiPlane_Mind.getLastUpdatePosition();
	        Date oNow = new Date();
	        long lDifferOfMiliseconds = oNow.getTime() - oLastUpdateDate.getTime();
	        
	        lMiliseconds = lDifferOfMiliseconds * SimulationVars.x;
	    	
	        gov.nasa.worldwind.geom.LatLon oNewLatLonPosition = 
	        	BasicFlightDynamics.BFD.getNextPos(lat1, lon1, brng, speed, lMiliseconds);
	        
	        
	        ingenias.jade.mental.Change_Plane_Status event=new ingenias.jade.mental.Change_Plane_Status();
	        
	        event.setNewLatLonPosition(oNewLatLonPosition);
	        event.setNewHead(null);
	        event.setNewAltitudeKM(-1);

			try {
				oUpdatePlaneStatusAppImp.getOwner().getMSM().addMentalEntity(event);
				
			} catch (ingenias.exception.InvalidEntity e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
		}
		
		
	}

}
