

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

import ingenias.editor.entities.MentalEntity;
import ingenias.jade.agents.PlaneJADEAgent;
import ingenias.jade.exception.*;
import ingenias.jade.mental.ControllerMind;



public  class ConflictAttendedCheckerAppImp extends ConflictAttendedCheckerApp{

 public ConflictAttendedCheckerAppImp(){
  super();
 }


 public boolean isConflictAttended(Hashtable<jade.core.AID, gov.nasa.worldwind.geom.Position>  aPlanesInConflict){

	 int iWhereIsConflictAttended = -1;
	 boolean bIsConflictAttended = false;
	 
	 Vector<MentalEntity> vMentalEntities =this.getOwner().getMSM().getMentalEntityByType("ControllerMind");
	 for (MentalEntity mentalEntity : vMentalEntities) {
		 ControllerMind oControllerMind = (ControllerMind) mentalEntity;
		 ArrayList<Hashtable<jade.core.AID, gov.nasa.worldwind.geom.Position>> aConflictsAttended =
		 oControllerMind.getConflictsAttended();
		 
		 iWhereIsConflictAttended = global.GlobalVarsAndMethods.whereIsConflictAttended(
				 aPlanesInConflict, aConflictsAttended);
		 if(iWhereIsConflictAttended != -1){
			 bIsConflictAttended = true;
			 break;
		 }
		 
	}
	
	 return bIsConflictAttended;
}



 
}

 