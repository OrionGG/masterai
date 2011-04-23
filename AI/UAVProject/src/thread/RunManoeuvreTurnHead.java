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

	public static void runManoeuvreTurnHead(Manoeuvre eiManoeuvre,
			Plane_Mind eiPlane_Mind, 
			UpdatePlaneStatusApp oUpdatePlaneStatusAppImp) {
		if(eiPlane_Mind.getRunningManoeuvres().size()!=0){
			if(!containsSimilarManoeuvres(eiPlane_Mind.getRunningManoeuvres(), eiManoeuvre)){
				Manoeuvre  oRunningManoeuvre = eiPlane_Mind.getRunningManoeuvres().get(0);
				if (oRunningManoeuvre.getPriority() == eiManoeuvre.getPriority()){
					eiPlane_Mind.getRunningManoeuvres().add(eiManoeuvre);
				}
				if (oRunningManoeuvre.getPriority() < eiManoeuvre.getPriority()){
					eiPlane_Mind.getRunningManoeuvres().clear();
					eiPlane_Mind.getRunningManoeuvres().add(eiManoeuvre);
				}
			}
		}
		else{
			eiPlane_Mind.getRunningManoeuvres().add(eiManoeuvre);
		}

		

		if(eiPlane_Mind.getRunningManoeuvres().contains(eiManoeuvre)){
			RunManoeuvreTurnHead oRunManoeuvreTurnHead = new RunManoeuvreTurnHead(
					eiManoeuvre, eiPlane_Mind, oUpdatePlaneStatusAppImp);
			Thread thread = new Thread(oRunManoeuvreTurnHead);
			thread.start();
		}
	}



	private static boolean checkSimilarManoeuvres(
			ArrayList<Manoeuvre> runningManoeuvres, Manoeuvre eiManoeuvre2) {
		// TODO Auto-generated method stub
		return false;
	}



	private static boolean containsSimilarManoeuvres(ArrayList<Manoeuvre> arrayList,
			Manoeuvre eiManoeuvre) {
		boolean bResult = false;

		for (Manoeuvre oRunningManoeuvre : arrayList) {
			double dAltitudeChangeRunning = oRunningManoeuvre.getAltitudeChange();
			double dAltitudeChange = eiManoeuvre.getAltitudeChange();
			if((dAltitudeChangeRunning!=0)&& (dAltitudeChange!=0)){
				bResult = true;
				break;
			}

			double dSpeedChangeRunning = oRunningManoeuvre.getSpeedChange();
			double dSpeedChange = eiManoeuvre.getSpeedChange();
			if((dSpeedChangeRunning!=0)&& (dSpeedChange!=0)){
				bResult = true;
				break;
			}

			Angle oHeadChangeRunning = oRunningManoeuvre.getHeadChange();
			Angle oHeadChange = eiManoeuvre.getHeadChange();
			if((oHeadChangeRunning!=null) && (oHeadChange!=null)){
				bResult = true;
				break;
			}
		}

		return bResult;
	}




}
