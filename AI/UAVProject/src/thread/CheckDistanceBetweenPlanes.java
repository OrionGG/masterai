package thread;

import gov.nasa.worldwind.geom.Position;
import ingenias.jade.components.Plane_Position_ServiceAppImp;
import jade.core.AID;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class CheckDistanceBetweenPlanes implements Runnable{
	Hashtable<AID, Position> aPlanesConflictFinished;
	ArrayList<Hashtable<AID, Position>> aConflictsAttended;
	int iWhereIsConflictAttended;

	public CheckDistanceBetweenPlanes(
			Hashtable<AID, Position> aPlanesConflictFinished,
			ArrayList<Hashtable<AID, Position>> aConflictsAttended, int iWhereIsConflictAttended) {
		this.aPlanesConflictFinished = aPlanesConflictFinished;
		this.aConflictsAttended = aConflictsAttended;
		this.iWhereIsConflictAttended = iWhereIsConflictAttended;
	}

	@Override
	public void run() {
		boolean bFinish= false;
		jade.core.AID[] aPlanesAIDs = 
			aConflictsAttended.get(iWhereIsConflictAttended).keySet().toArray(new jade.core.AID[aConflictsAttended.size()]);
		
		while(!bFinish){
			bFinish = true;
			for (int i = 0; i < aPlanesAIDs.length; i++) {
				jade.core.AID oPlaneAIDi = aPlanesAIDs[i];
				Plane_Position_ServiceAppImp plane_Position_ServiceAppImpi = 
					global.GlobalVarsAndMethods.PlanesPositionApps.get(oPlaneAIDi);
				Position oPositioni = plane_Position_ServiceAppImpi.getCurrentPosition();

				for (int j = i+1; j < aPlanesAIDs.length; j++) {
					jade.core.AID oPlaneAIDj = aPlanesAIDs[j];
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

		for (int i = 0; i < aPlanesAIDs.length; i++) {
			jade.core.AID oPlaneAIDi = aPlanesAIDs[i];
			aPlanesConflictFinished.remove(oPlaneAIDi);
		}

		aConflictsAttended.remove(iWhereIsConflictAttended);


	}



}
