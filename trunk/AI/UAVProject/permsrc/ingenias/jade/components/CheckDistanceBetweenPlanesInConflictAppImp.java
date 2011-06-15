

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

import java.util.*;

import ingenias.jade.agents.ControllerJADEAgent;
import ingenias.jade.exception.*;
import ingenias.jade.mental.ControllerMind;



public  class CheckDistanceBetweenPlanesInConflictAppImp extends CheckDistanceBetweenPlanesInConflictApp{

 public CheckDistanceBetweenPlanesInConflictAppImp(){
  super();
 }


 public void start(ControllerMind eiControllerMind, int iConflictNumber, ControllerJADEAgent oController){
		thread.CheckDistanceBetweenPlanesInConflictThread oCheckDistanceBetweenPlanesInConflict =
			new thread.CheckDistanceBetweenPlanesInConflictThread(eiControllerMind, iConflictNumber, oController);
	
		Thread oThread = new Thread(oCheckDistanceBetweenPlanesInConflict);
		oThread.start();
} 
 
}

 