

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



public class Execute_DecisionTask extends Task{

 public Execute_DecisionTask(String id){
  super(id,"Execute_Decision");
 }



 public void execute() throws TaskException{


        Throw_Instruction  eiThrow_Instruction=(Throw_Instruction)this.getFirstInputOfType("Throw_Instruction");             

        Pilot_Mind  eiPilot_Mind=(Pilot_Mind)this.getFirstInputOfType("Pilot_Mind");             










  		Vector<TaskOutput> outputs = this.getOutputs();
  		TaskOutput defaultOutput= outputs.firstElement();
  		
  		  	
  		TaskOutput	outputsdefault=findOutputAlternative("default",
  																			outputs);
  		
		RuntimeConversation outputsdefaultPilotPlaneInteraction=
			(RuntimeConversation)
				outputsdefault.getEntityByType("PilotPlaneInteraction");
		
		
		Manoeuvre outputsdefaultManoeuvre=
			(Manoeuvre)
				outputsdefault.getEntityByType("Manoeuvre");
		
		
		
        YellowPages yp=null; // only available for initiators of interactions

		// This task can produce an interaction of type PilotPlaneInteraction by working with its conversation object
        
        // To define manually who are the collaborator involved. Your selection will be verified
        // in runtime. Pay attention to log messages to detect errors. You can use the yello pages
        // service to locate other agents
        yp=(YellowPages)this.getApplication("YellowPages");

        //  Uncomment the following and write down a proper local id of the agent
        // Find an agent playing the role "PlaneColaborator"
      	//eoPilotPlaneInteraction.addCollaborators("Local ID of the collaborator");
       	


//#start_node:INGENIASCodeComponent10 <--- DO NOT REMOVE THIS	
        outputsdefaultManoeuvre.setSpeedChange(eiThrow_Instruction.getSpeedChange());
        outputsdefaultManoeuvre.setAltitudeChange(eiThrow_Instruction.getAltitudeChange());
        outputsdefaultManoeuvre.setHeadChange(eiThrow_Instruction.getHeadChange());
        outputsdefaultManoeuvre.setPriority(eiThrow_Instruction.getPriority());
        outputsdefaultPilotPlaneInteraction.addCollaborators(eiPilot_Mind.getPilotFlightPlan().getPlaneID());
//#end_node:INGENIASCodeComponent10 <--- DO NOT REMOVE THIS

 }
 
}

 