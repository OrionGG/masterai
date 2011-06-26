

/*
    Copyright (C) 2005 Jorge Gomez Sanz

    This file is part of INGENIAS Agent Framework, an agent infrastructure linked
    to the INGENIAS Development Kit, and availabe at http://grasia.fdi.ucm.es/ingenias or
    http://ingenias.sourceforge.net. 

    INGENIAS Agent Framework is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    INGENIAS Agent Framework is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with INGENIAS Agent Framework; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

*/


package ingenias.jade.components;

import java.util.*;
import ingenias.jade.exception.*;
import ingenias.jade.comm.*;
import ingenias.jade.mental.*;
import ingenias.editor.entities.*;



public class IsANewConflictDetectedTask extends Task{

 public IsANewConflictDetectedTask(String id){
  super(id,"IsANewConflictDetected");
 }



 public void execute() throws TaskException{


        PlanesInConflict  eiPlanesInConflict=(PlanesInConflict)this.getFirstInputOfType("PlanesInConflict");             

        ControllerMind  eiControllerMind=(ControllerMind)this.getFirstInputOfType("ControllerMind");             





			
        CheckDistanceBetweenPlanesInConflictApp eaCheckDistanceBetweenPlanesInConflict=(CheckDistanceBetweenPlanesInConflictApp)this.getApplication("CheckDistanceBetweenPlanesInConflict");





  		Vector<TaskOutput> outputs = this.getOutputs();
  		TaskOutput defaultOutput= outputs.firstElement();
  		
  		  	
  		TaskOutput	outputsdefault=findOutputAlternative("default",
  																			outputs);
  		
		
		CanStartSendOrder outputsdefaultCanStartSendOrder=
			(CanStartSendOrder)
				outputsdefault.getEntityByType("CanStartSendOrder");
		
		
		
        YellowPages yp=null; // only available for initiators of interactions


//#start_node:INGENIASCodeComponent30 <--- DO NOT REMOVE THIS	
        boolean bAlreadyProcessed = global.GlobalVarsAndMethods.isAlreadyConflictProcessed(eiPlanesInConflict, eiControllerMind);

        if(bAlreadyProcessed){
        	outputsdefault.remove(outputsdefaultCanStartSendOrder);
        }
        else{

        	Hashtable<Integer, ArrayList<jade.core.AID>> aConflictsAttended =
 	    		eiControllerMind.getConflictsAttended();
        	

 	        ArrayList<jade.core.AID> lPlanesInConflict = 
       		eiPlanesInConflict.getPlanesInConflict();
        	
        	int iAnyPlaneInConflict = global.GlobalVarsAndMethods.isAnyPlaneInConflict(lPlanesInConflict, aConflictsAttended);
        		
 	        	
        	
        	int iConflictNumber = -1;
 	        if(iAnyPlaneInConflict == -1){
 	        	iConflictNumber =  eiControllerMind.getTotalConflictNumber();
 	 	    	aConflictsAttended.put(iConflictNumber, lPlanesInConflict);
 	        }
 	        else{
 	        	iConflictNumber = iAnyPlaneInConflict;
 	        	global.GlobalVarsAndMethods.AddPlanesToConflictsAttended(iConflictNumber, lPlanesInConflict, aConflictsAttended);
 	        }
 	    
 	        
 	        
 	    	eiControllerMind.setConflictsAttended(aConflictsAttended);
 	    	
 	    	yp=(YellowPages)this.getApplication("YellowPages");
 	    	ingenias.jade.agents.ControllerJADEAgent oController = (ingenias.jade.agents.ControllerJADEAgent)yp.ja;

 	        eaCheckDistanceBetweenPlanesInConflict.start(eiControllerMind, iConflictNumber, oController);
 	    	
	    	eiControllerMind.setTotalConflictNumber(iConflictNumber + 1);
	    	
        	global.GlobalVarsAndMethods.putPlanesInRisk(eiPlanesInConflict.getPlanesInConflict(), 9);
        	
        	outputsdefaultCanStartSendOrder.setPlanesInConflict(lPlanesInConflict);
        	
        }
//#end_node:INGENIASCodeComponent30 <--- DO NOT REMOVE THIS

 }
 
}

 