package thread;

import gov.nasa.worldwind.geom.Position;
import ingenias.jade.components.Plane_Position_ServiceAppImp;
import jade.core.AID;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class CheckDistanceBetweenPlanes implements Runnable{
	ArrayList<AID> aPlanesConflictFinished;
	Hashtable<Integer, ArrayList<AID>> aConflictsAttended;
	int iWhereIsConflictAttended;

	public CheckDistanceBetweenPlanes(
			ArrayList<AID> aPlanesConflictFinished2,
			Hashtable<Integer, ArrayList<AID>> aConflictsAttended2, int iWhereIsConflictAttended) {
		this.aPlanesConflictFinished = aPlanesConflictFinished2;
		this.aConflictsAttended = aConflictsAttended2;
		this.iWhereIsConflictAttended = iWhereIsConflictAttended;
	}

	@Override
	public void run() {
		boolean bFinish = false;
		ArrayList<jade.core.AID> aPlanesAIDs = 
			aConflictsAttended.get(iWhereIsConflictAttended);
		
		while(!bFinish){
			bFinish = true;
			ArrayList<jade.core.AID> aPlanesFreeOfConflict = global.GlobalVarsAndMethods.getPlanesFreeOfConflict(aPlanesAIDs);

			for (AID aid : aPlanesFreeOfConflict) {
				aPlanesConflictFinished.remove(aid);
			}
			global.GlobalVarsAndMethods.putPlanesInRisk(aPlanesFreeOfConflict, 0);
        	
			
			try {
				Thread.sleep((long) (Math.random()*2000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		aConflictsAttended.remove(iWhereIsConflictAttended);
		


	}



}
