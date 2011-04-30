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
				eiPlane_Mind.getLatitude(), eiPlane_Mind.getLongitude(), eiPlane_Mind.getAltitudeKM());
		oPlaneView = new PlaneView((PlaneJADEAgent)oUpdatePlaneStatusAppImp.getOwner(), "Plane", oPosition);
		Simulation.SimulationVars.lPlanesFlying.add(oPlaneView);
		//oPlaneView.render(Simulation.SimulationVars.layer);
	}

	@Override
	public void run() {

		long lMiliseconds = SimulationVars.iSleepTime * SimulationVars.x;
		sleep(SimulationVars.iSleepTime);
    	double speed = eiPlane_Mind.getSpeedKMH();
		while (speed != 0){
			
			double lat1 = eiPlane_Mind.getLatitude().radians;
	    	double lon1 = eiPlane_Mind.getLongitude().radians;
	    	double brng = eiPlane_Mind.getHead().radians;
	    	speed = eiPlane_Mind.getSpeedKMH();
	        //Date oLastUpdateDate = eiPlane_Mind.getLastUpdatePosition();
	    	    	
	    	
	        gov.nasa.worldwind.geom.Position oNewPosition = 
	        	BasicFlightDynamics.BFD.getNextPos(lat1, lon1, brng, speed, lMiliseconds);
	        
	        
	        ingenias.jade.mental.Change_Plane_Status event=new ingenias.jade.mental.Change_Plane_Status();
	        event.setNewPosition(oNewPosition);

			try {
				Vector<MentalEntity> lChange_Plane_Status = oUpdatePlaneStatusAppImp.getOwner().getMSM().getMentalEntityByType("Change_Plane_Status");
				
				if(lChange_Plane_Status.size()<=0){
					oUpdatePlaneStatusAppImp.getOwner().getMSM().addMentalEntity(event);
				}
				else{
					ingenias.jade.mental.Change_Plane_Status oChange_Plane_Status = (ingenias.jade.mental.Change_Plane_Status)lChange_Plane_Status.get(0);
					oChange_Plane_Status.setNewPosition(event.getNewPosition());
				}
				
			} catch (ingenias.exception.InvalidEntity e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			gov.nasa.worldwind.geom.Position oPosition = new gov.nasa.worldwind.geom.Position(
					eiPlane_Mind.getLatitude(), eiPlane_Mind.getLongitude(), eiPlane_Mind.getAltitudeKM()*1000);
			
			oPlaneView.setNewPosition(oPosition);
			
			//Date oLastUpdateDate = new Date();
			sleep(SimulationVars.iSleepTime);
		}
		
		
	}
	
	public void sleep(int miliseconds) {
		try {
			Thread.sleep(miliseconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
