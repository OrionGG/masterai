package thread;

import ingenias.exception.InvalidEntity;
import ingenias.jade.agents.PilotJADEAgent;
import ingenias.jade.components.YellowPages;
import ingenias.jade.mental.CanCreateNewDecision;
import ingenias.jade.mental.Pilot_Mind;

import java.util.Date;

public class CreateNewDecisionThread implements Runnable{
	PilotJADEAgent pilotJADEAgent;
	Pilot_Mind eiPilot_Mind;

	public CreateNewDecisionThread(PilotJADEAgent oPilotJADEAgent, Pilot_Mind oPilot_Mind) {
		this.pilotJADEAgent = oPilotJADEAgent;
		this.eiPilot_Mind = oPilot_Mind;
	}

	@Override
	public void run() {
        	//id the last decision was fewer than a minute ago => sleep 
	        Date oLastDecisionDate = eiPilot_Mind.getLastDecisionDate();
	        Date oNow = new Date();
	        long lDifferOfMiliseconds = oNow.getTime() - oLastDecisionDate.getTime();
	        long iRangeTime =(long) (Simulation.SimulationVars.iSleepTime * Math.pow(global.GlobalVarsAndMethods.PlaneIdToPilotId.size(),2));
	        if(lDifferOfMiliseconds < iRangeTime){
	        	try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		        oNow = new Date();
				lDifferOfMiliseconds = oNow.getTime() - oLastDecisionDate.getTime();
	        }
	        try {
				pilotJADEAgent.getMSM().addMentalEntity(new CanCreateNewDecision());
			} catch (InvalidEntity e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}
