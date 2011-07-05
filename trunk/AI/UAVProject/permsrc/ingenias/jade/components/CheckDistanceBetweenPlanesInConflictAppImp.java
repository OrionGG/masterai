

/**
 *
 *
 *  Description of the task /
 *
 * 
 *@author     Jorge J. Gomez
 *@version    1.0
 */

package ingenias.jade.components;

import jade.core.AID;

import java.util.*;

import ingenias.jade.agents.ControllerJADEAgent;
import ingenias.jade.exception.*;
import ingenias.jade.mental.ControllerMind;



public  class CheckDistanceBetweenPlanesInConflictAppImp extends CheckDistanceBetweenPlanesInConflictApp{

	HashMap<Integer, thread.CheckDistanceBetweenPlanesInConflictThread> lHashCheckThreads = new HashMap<Integer, thread.CheckDistanceBetweenPlanesInConflictThread>();
	
 public CheckDistanceBetweenPlanesInConflictAppImp(){
  super();
 }


 public void start(ArrayList<AID> lPlanesInConflict, int iConflictNumber, ControllerJADEAgent oController){
	 thread.CheckDistanceBetweenPlanesInConflictThread tOldThread= lHashCheckThreads.get(iConflictNumber);
	 if(tOldThread != null){
		 tOldThread.setbFinish(true);
	 }
	 
	 thread.CheckDistanceBetweenPlanesInConflictThread oCheckDistanceBetweenPlanesInConflict =
		 new thread.CheckDistanceBetweenPlanesInConflictThread(lPlanesInConflict, oController);

	 Thread oThread = new Thread(oCheckDistanceBetweenPlanesInConflict);

	 oThread.start();
	 lHashCheckThreads.put(iConflictNumber, oCheckDistanceBetweenPlanesInConflict);
	 
} 
 
}

 