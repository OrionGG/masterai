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
	        long iRangeTime = (long) (Simulation.SimulationVars.iSleepTime * global.GlobalVarsAndMethods.PlaneIdToPilotId.size() * 2);
	        long iRangeTimeForWait = iRangeTime + getAnwserTime();
			
	        while(lDifferOfMiliseconds < iRangeTimeForWait){
				
	        	try {
	        		if((iRangeTimeForWait - lDifferOfMiliseconds) > 0){
	        			Thread.sleep(iRangeTimeForWait - lDifferOfMiliseconds);
	        		}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		        oNow = new Date();
				lDifferOfMiliseconds = oNow.getTime() - oLastDecisionDate.getTime();
				
				updatePilotMindValues();
				iRangeTimeForWait = iRangeTime + getAnwserTime();
	        }
	        try {
				pilotJADEAgent.getMSM().addMentalEntity(new CanCreateNewDecision());
			} catch (InvalidEntity e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	private void updatePilotMindValues() {
		double dExperience = eiPilot_Mind.getExperience();
		dExperience = Math.pow(dExperience, 1/1.001);
		eiPilot_Mind.setExperience((float) dExperience);
		
		double dFatigue = eiPilot_Mind.getFatigue();
		dFatigue = Math.pow(dFatigue, 1/1.001);
		eiPilot_Mind.setFatigue((float) dFatigue);
		
		
		double dStress = eiPilot_Mind.getStress();
		dStress = Math.pow(dStress, 1/1.001);
		eiPilot_Mind.setStress((float) dStress);
		
		
		
		
	}

	public long getAnwserTime() {
		double dExperience = eiPilot_Mind.getExperience();
		double dFatigue = eiPilot_Mind.getFatigue();
		double dStress = eiPilot_Mind.getStress();
		
		long lAnwserTime = (long) (((dFatigue + dStress)/2) * (1/dExperience) * Simulation.SimulationVars.iSleepTime);
		return lAnwserTime;
	}

}
