package thread;

import gov.nasa.worldwind.geom.Angle;
import ingenias.jade.mental.Manoeuvre;
import ingenias.jade.mental.Plane_Mind;

import java.util.ArrayList;
import java.util.Date;

public class RunManoeuvreTurnHead  implements Runnable{
	Manoeuvre eiManoeuvre;
	Plane_Mind eiPlane_Mind;

	public RunManoeuvreTurnHead(Manoeuvre eiManoeuvre, Plane_Mind eiPlane_Mind) {
		super();
		this.eiManoeuvre = eiManoeuvre;
		this.eiPlane_Mind = eiPlane_Mind;
	}



	public void run() {

		Date oLastUpdateHead =new Date();

		sleep(1000);
		while(eiPlane_Mind.getRunningManoeuvres().contains(eiManoeuvre)){
			//while Manoeuvre to run is that we are running
			gov.nasa.worldwind.geom.Angle oAngle = BasicFlightDynamics.BFD.TurnHead( eiPlane_Mind, eiManoeuvre, oLastUpdateHead);
			if(oAngle.degrees == eiPlane_Mind.getHead().degrees){
				break;
			}
			eiPlane_Mind.setHead(oAngle);
			oLastUpdateHead = new Date();
			sleep(1000);
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
			Plane_Mind eiPlane_Mind) {
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
			RunManoeuvreTurnHead oRunManoeuvreTurnHead = new RunManoeuvreTurnHead(eiManoeuvre, eiPlane_Mind);
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
