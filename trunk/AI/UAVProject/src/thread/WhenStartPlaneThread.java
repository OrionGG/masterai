package thread;

import java.util.Date;

import ingenias.exception.InvalidEntity;
import ingenias.jade.agents.PilotJADEAgent;
import ingenias.jade.components.OutputEntity;
import ingenias.jade.components.TaskOperations;
import ingenias.jade.components.WhenStartPlaneAppImp;
import ingenias.jade.mental.InitiateStartPlane;

public class WhenStartPlaneThread implements Runnable {
	PilotJADEAgent owner;
	Date DepartureTime;

	public WhenStartPlaneThread(PilotJADEAgent owner, Date oDepartureTime) {
		this.owner = owner;
		this.DepartureTime = oDepartureTime;
	}

	@Override
	public void run() {
		while(DepartureTime.after(new Date())){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

    	InitiateStartPlane oInitiateStartPlane = new InitiateStartPlane();
    	try {
			this.owner.getMSM().addMentalEntity(oInitiateStartPlane);
		} catch (InvalidEntity e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
