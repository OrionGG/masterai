package thread;

import ingenias.jade.components.UpdatePlaneStatusApp;
import ingenias.jade.mental.Manoeuvre;
import ingenias.jade.mental.Plane_Mind;

public class RunManoeuvreChangeSpeed   implements Runnable{

	Manoeuvre eiManoeuvre;
	Plane_Mind eiPlane_Mind;
	UpdatePlaneStatusApp oUpdatePlaneStatusApp;
	
	
	private RunManoeuvreChangeSpeed(Manoeuvre eiManoeuvre,
			Plane_Mind eiPlane_Mind, UpdatePlaneStatusApp oUpdatePlaneStatusApp) {
		super();
		this.eiManoeuvre = eiManoeuvre;
		this.eiPlane_Mind = eiPlane_Mind;
		this.oUpdatePlaneStatusApp = oUpdatePlaneStatusApp;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		eiPlane_Mind.setSpeedKMH(eiManoeuvre.getSpeedChange());
		

		if(eiPlane_Mind.getRunningManoeuvres().contains(eiManoeuvre)){
			eiPlane_Mind.getRunningManoeuvres().remove(eiManoeuvre);
		}
	}


	public static void runManoeuvre(Manoeuvre eiManoeuvre,
			Plane_Mind eiPlane_Mind, UpdatePlaneStatusApp eaUpdatePlaneStatus) {

		global.GlobalVarsAndMethods.addManoeuvre(eiManoeuvre, eiPlane_Mind);

		

		if(eiPlane_Mind.getRunningManoeuvres().contains(eiManoeuvre)){
			RunManoeuvreChangeSpeed oRunManoeuvreChangeSpeed = new RunManoeuvreChangeSpeed(
					eiManoeuvre, eiPlane_Mind, eaUpdatePlaneStatus);
			Thread thread = new Thread(oRunManoeuvreChangeSpeed);
			thread.start();
		}
		
	}

}
