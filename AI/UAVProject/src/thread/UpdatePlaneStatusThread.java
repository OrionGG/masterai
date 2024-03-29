package thread;

import jade.core.AID;

import java.util.Date;
import java.util.Vector;

import views.PlaneView;

import controllers.EntitiesController;


import Simulation.SimulationVars;


import ingenias.editor.entities.MentalEntity;
import ingenias.jade.agents.PlaneJADEAgent;
import ingenias.jade.components.UpdatePlaneStatusAppImp;
import ingenias.jade.mental.Plane_Mind;

public class UpdatePlaneStatusThread  implements Runnable{

	Plane_Mind eiPlane_Mind;
	UpdatePlaneStatusAppImp oUpdatePlaneStatusAppImp;
	



	public UpdatePlaneStatusThread(Plane_Mind eiPlane_Mind, UpdatePlaneStatusAppImp oUpdatePlaneStatusAppImp) {
		super();
		this.eiPlane_Mind = eiPlane_Mind;
		this.oUpdatePlaneStatusAppImp = oUpdatePlaneStatusAppImp;
		gov.nasa.worldwind.geom.Position oPosition = new gov.nasa.worldwind.geom.Position(
				eiPlane_Mind.getLatLonPosition(),
				eiPlane_Mind.getAltitudeKM());
		
		EntitiesController.getInstance().addPlaneView(
				new PlaneView((PlaneJADEAgent)oUpdatePlaneStatusAppImp.getOwner(), oUpdatePlaneStatusAppImp.getOwner().getLocalName(), oPosition));


		AID aid = oUpdatePlaneStatusAppImp.getOwner().getAID();
		EntitiesController.getInstance().renderPlane(aid);
	}

	@Override
	public void run() {

		long lMiliseconds = SimulationVars.iSleepTime * SimulationVars.x;
		
    	double speed = eiPlane_Mind.getSpeedKMH();
		while (speed != 0){

			global.GlobalVarsAndMethods.sleepRandom(SimulationVars.iSleepTime);

			gov.nasa.worldwind.geom.Position oPosition = new gov.nasa.worldwind.geom.Position(
					eiPlane_Mind.getLatLonPosition(),
					eiPlane_Mind.getAltitudeKM()*1000);
			
			AID aid = oUpdatePlaneStatusAppImp.getOwner().getAID();
			
			EntitiesController.getInstance().updatePlane(aid, oPosition, global.GlobalVarsAndMethods.getRangeOfRisk(aid));
			
			
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
	        
	        try {
				Vector<MentalEntity> lChange_Plane_Status = oUpdatePlaneStatusAppImp.getOwner().getMSM().getMentalEntityByType("Change_Plane_Status");
				
				if(lChange_Plane_Status.size()<=0){
					oUpdatePlaneStatusAppImp.getOwner().getMSM().addMentalEntity(event);
				}
				else{
					ingenias.jade.mental.Change_Plane_Status oChange_Plane_Status = (ingenias.jade.mental.Change_Plane_Status)lChange_Plane_Status.get(0);
					oChange_Plane_Status.setNewLatLonPosition(event.getNewLatLonPosition());
				}
				
			} catch (ingenias.exception.InvalidEntity e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}			
		}
		
		
	}

}
