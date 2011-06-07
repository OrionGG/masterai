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
			for (int i = 0; i < aPlanesAIDs.size(); i++) {
				jade.core.AID oPlaneAIDi = aPlanesAIDs.get(i);
				Plane_Position_ServiceAppImp plane_Position_ServiceAppImpi = 
					global.GlobalVarsAndMethods.PlanesPositionApps.get(oPlaneAIDi);
				Position oPositioni = plane_Position_ServiceAppImpi.getCurrentPosition();

				for (int j = i+1; j < aPlanesAIDs.size(); j++) {
					jade.core.AID oPlaneAIDj =aPlanesAIDs.get(j);
					Plane_Position_ServiceAppImp plane_Position_ServiceAppImpj = 
						global.GlobalVarsAndMethods.PlanesPositionApps.get(oPlaneAIDj);
					Position oPositionj = plane_Position_ServiceAppImpj.getCurrentPosition();
					double dDistance = BasicFlightDynamics.BFD.getDistance(oPositioni, oPositionj);
					if(dDistance < global.GlobalVarsAndMethods.dAwarenessDistance * Simulation.SimulationVars.x){//6 miles
						bFinish = false;
						break;
					}
				}
				if(!bFinish){
					break;
				}
			}

			try {
				Thread.sleep((long) (Math.random()*2000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (AID aid : aPlanesAIDs) {
			aPlanesConflictFinished.remove(aid);
		}

		aConflictsAttended.remove(iWhereIsConflictAttended);


	}



}
