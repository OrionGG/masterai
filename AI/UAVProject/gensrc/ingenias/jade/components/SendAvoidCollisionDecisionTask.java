

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



public class SendAvoidCollisionDecisionTask extends Task{

 public SendAvoidCollisionDecisionTask(String id){
  super(id,"SendAvoidCollisionDecision");
 }



 public void execute() throws TaskException{


        PlanesInConflict  eiPlanesInConflict=(PlanesInConflict)this.getFirstInputOfType("PlanesInConflict");             

        ControllerMind  eiControllerMind=(ControllerMind)this.getFirstInputOfType("ControllerMind");             










  		Vector<TaskOutput> outputs = this.getOutputs();
  		TaskOutput defaultOutput= outputs.firstElement();
  		
  		  	
  		TaskOutput	outputsdefault=findOutputAlternative("default",
  																			outputs);
  		
		RuntimeConversation outputsdefaultControllerPilotInteracion=
			(RuntimeConversation)
				outputsdefault.getEntityByType("ControllerPilotInteracion");
		
		
		Order outputsdefaultOrder=
			(Order)
				outputsdefault.getEntityByType("Order");
		
		
		
        YellowPages yp=null; // only available for initiators of interactions

		// This task can produce an interaction of type ControllerPilotInteracion by working with its conversation object
        
        // To define manually who are the collaborator involved. Your selection will be verified
        // in runtime. Pay attention to log messages to detect errors. You can use the yello pages
        // service to locate other agents
        yp=(YellowPages)this.getApplication("YellowPages");

        //  Uncomment the following and write down a proper local id of the agent
        // Find an agent playing the role "PilotColaborator"
      	//eoControllerPilotInteracion.addCollaborators("Local ID of the collaborator");
       	


//#start_node:INGENIASCodeComponent21 <--- DO NOT REMOVE THIS	
        
        String ControllerRole = "ControllerInitiator";
        String PilotRole = "PilotColaborator";
        String PlaneRole = "PlaneColaborator";
        
        boolean bProcessedYet = global.GlobalVarsAndMethods.isConflictYetProcessed(eiPlanesInConflict);

        if(bProcessedYet){
        	outputsdefault.remove(outputsdefaultOrder);
        	outputsdefaultControllerPilotInteracion.setState("FINISH");
        }
        else{
        	eiControllerMind.getConflictsAttended();
        	
	        Hashtable<jade.core.AID, gov.nasa.worldwind.geom.Position> hPilotGoTo = 
	        	new Hashtable<jade.core.AID, gov.nasa.worldwind.geom.Position>();
	        

	        ArrayList<ingenias.jade.agents.PlaneJADEAgent> aPlanesInConflict = eiPlanesInConflict.getPlanesInConflict();
	        for (ingenias.jade.agents.PlaneJADEAgent planeJADEAgent : aPlanesInConflict) {
	        	jade.core.AID oPlaneAID = planeJADEAgent.getAID();
	        	ingenias.jade.AgentExternalDescription oPilotAgentExternalDescription = global.GlobalVarsAndMethods.PlaneIdToPilotId.get(oPlaneAID);
	        	
	        	
	        	//hPilotGoTo.put(oPilotAgentExternalDescription, value);
	        	
	        	outputsdefaultControllerPilotInteracion.addCollaborators(oPilotAgentExternalDescription);
			}
        }
//#end_node:INGENIASCodeComponent21 <--- DO NOT REMOVE THIS

 }
 
}

 