package thread;

import gov.nasa.worldwind.geom.Angle;
import ingenias.editor.entities.MentalEntity;
import ingenias.jade.components.UpdatePlaneStatusApp;
import ingenias.jade.mental.Manoeuvre;
import ingenias.jade.mental.Plane_Mind;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import Simulation.SimulationVars;


public class RunManoeuvreTurnHead  implements Runnable{
	Manoeuvre eiManoeuvre;
	Plane_Mind eiPlane_Mind;
	UpdatePlaneStatusApp oUpdatePlaneStatusApp;

	public RunManoeuvreTurnHead(Manoeuvre eiManoeuvre, Plane_Mind eiPlane_Mind, UpdatePlaneStatusApp oUpdatePlaneStatusApp) {
		super();
		this.eiManoeuvre = eiManoeuvre;
		this.eiPlane_Mind = eiPlane_Mind;
		this.oUpdatePlaneStatusApp = oUpdatePlaneStatusApp;
	}



	public void run() {

		long lMiliseconds = SimulationVars.iSleepTime * SimulationVars.x;
		sleep(SimulationVars.iSleepTime);
		while(eiPlane_Mind.getRunningManoeuvres().contains(eiManoeuvre)){
			//while Manoeuvre to run is that we are running
			gov.nasa.worldwind.geom.Angle oNewAngle = BasicFlightDynamics.BFD.TurnHead( eiPlane_Mind, eiManoeuvre, lMiliseconds);
			if(oNewAngle.degrees == eiPlane_Mind.getHead().degrees){
				break;
			}
			//eiPlane_Mind.setHead(oAngle);
			
			ingenias.jade.mental.Change_Plane_Status event=new ingenias.jade.mental.Change_Plane_Status();
	        event.setNewHead(oNewAngle);
			try {
				Vector<MentalEntity> lChange_Plane_Status = oUpdatePlaneStatusApp.getOwner().getMSM().getMentalEntityByType("Change_Plane_Status");
				
				if(lChange_Plane_Status.size()<=0){
					oUpdatePlaneStatusApp.getOwner().getMSM().addMentalEntity(event);
				}
				else{
					ingenias.jade.mental.Change_Plane_Status oChange_Plane_Position = (ingenias.jade.mental.Change_Plane_Status)lChange_Plane_Status.get(0);
					oChange_Plane_Position.setNewHead(event.getNewHead());
				}
				
			} catch (ingenias.exception.InvalidEntity e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//oLastUpdateHead = new Date();
			sleep(SimulationVars.iSleepTime);
		}
		if(eiPlane_Mind.getRunningManoeuvres().contains(eiManoeuvre)){
			eiPlane_Mind.getRunningManoeuvres().remove(eiManoeuvre);
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

	public static boolean runManoeuvreTurnHead(Manoeuvre eiManoeuvre,
			Plane_Mind eiPlane_Mind, 
			UpdatePlaneStatusApp oUpdatePlaneStatusAppImp) {
		
		boolean bResult = false;
		bResult = global.GlobalVarsAndMethods.addManoeuvre(eiManoeuvre, eiPlane_Mind);

		

		if(eiPlane_Mind.getRunningManoeuvres().contains(eiManoeuvre)){
			RunManoeuvreTurnHead oRunManoeuvreTurnHead = new RunManoeuvreTurnHead(
					eiManoeuvre, eiPlane_Mind, oUpdatePlaneStatusAppImp);
			Thread thread = new Thread(oRunManoeuvreTurnHead);
			thread.start();
		}
		return bResult;
	}




}
