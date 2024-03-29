package thread;

import java.util.ArrayList;

import jade.core.AID;
import ingenias.exception.InvalidEntity;
import ingenias.jade.AgentExternalDescription;
import ingenias.jade.agents.ControllerJADEAgent;
import ingenias.jade.mental.ControllerMind;
import ingenias.jade.mental.PlaneNoMoreInConflict;

public class CheckDistanceBetweenPlanesInConflictThread implements Runnable {
	
	ControllerJADEAgent owner;
	private ArrayList<AID> lPlanesInConflict;

	boolean bFinish = false;


	public void setbFinish(boolean bFinish) {
		this.bFinish = bFinish;
	}



	public CheckDistanceBetweenPlanesInConflictThread(
			ControllerMind eiControllerMind, int iConflictNumber, ControllerJADEAgent oController) {
		
	}



	public CheckDistanceBetweenPlanesInConflictThread(
			ArrayList<AID> lPlanesInConflict, ControllerJADEAgent oController) {
		super();
		this.lPlanesInConflict = lPlanesInConflict;
		owner = oController;
	}



	@Override
	public void run() {
		ArrayList<jade.core.AID> aTotalPlanesFreeOfConflict = new ArrayList<AID>();
		while(!bFinish){
			java.util.ArrayList<jade.core.AID> aConflictsAttended = lPlanesInConflict;
			if(aConflictsAttended == null){
				bFinish = true;
			}
			else{
				aConflictsAttended.removeAll(aTotalPlanesFreeOfConflict);
				ArrayList<jade.core.AID> aPlanesFreeOfConflict = global.GlobalVarsAndMethods.getPlanesFreeOfConflict(aConflictsAttended);
				
				for (AID aid : aPlanesFreeOfConflict) {
					PlaneNoMoreInConflict oPlaneNoMoreInConflict = new PlaneNoMoreInConflict();
					oPlaneNoMoreInConflict.setPlaneID(new AgentExternalDescription(aid, "PlaneColaborator"));
					try {
						owner.getMSM().addMentalEntity(oPlaneNoMoreInConflict);
					} catch (InvalidEntity e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				global.GlobalVarsAndMethods.putPlanesInRisk(aPlanesFreeOfConflict, 0);
				aTotalPlanesFreeOfConflict.addAll(aPlanesFreeOfConflict);
			}
			
			try {
				Thread.sleep((long) (Math.random()*1000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
