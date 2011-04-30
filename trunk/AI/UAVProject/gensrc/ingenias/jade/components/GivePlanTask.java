

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



public class GivePlanTask extends Task{

 public GivePlanTask(String id){
  super(id,"GivePlan");
 }



 public void execute() throws TaskException{


        Flight_Plan  eiFlight_Plan=(Flight_Plan)this.getFirstInputOfType("Flight_Plan");             










  		Vector<TaskOutput> outputs = this.getOutputs();
  		TaskOutput defaultOutput= outputs.firstElement();
  		
  		  	
  		TaskOutput	outputsdefault=findOutputAlternative("default",
  																			outputs);
  		
		RuntimeConversation outputsdefaultFlightPlannerPilotInteraction=
			(RuntimeConversation)
				outputsdefault.getEntityByType("FlightPlannerPilotInteraction");
		
		
		PlanAnswer outputsdefaultPlanAnswer=
			(PlanAnswer)
				outputsdefault.getEntityByType("PlanAnswer");
		
		
		
        YellowPages yp=null; // only available for initiators of interactions

		// This task can produce an interaction of type FlightPlannerPilotInteraction by working with its conversation object
        
        // To define manually who are the collaborator involved. Your selection will be verified
        // in runtime. Pay attention to log messages to detect errors. You can use the yello pages
        // service to locate other agents
        yp=(YellowPages)this.getApplication("YellowPages");

        //  Uncomment the following and write down a proper local id of the agent
        // Find an agent playing the role "PilotColaborator"
      	//eoFlightPlannerPilotInteraction.addCollaborators("Local ID of the collaborator");
       	


//#start_node:CodeForCreatingRandomPlan <--- DO NOT REMOVE THIS	
        outputsdefaultFlightPlannerPilotInteraction.addCollaborators(eiFlight_Plan.getPilotID());
        List<jade.domain.FIPAAgentManagement.DFAgentDescription> lDFPilotsAgentDescription = new java.util.ArrayList();
        List<jade.domain.FIPAAgentManagement.DFAgentDescription> lDFPlanesAgentDescription = new java.util.ArrayList();
		try {
			jade.domain.FIPAAgentManagement.DFAgentDescription[] aDFPilotsAgentDescription  = yp.getAgents("PilotColaborator");
			lDFPilotsAgentDescription = Arrays.asList(aDFPilotsAgentDescription);
			jade.domain.FIPAAgentManagement.DFAgentDescription[] aDFPlanesAgentDescription = yp.getAgents("PlaneColaborator");
			lDFPlanesAgentDescription = Arrays.asList(aDFPlanesAgentDescription);
		} catch (jade.domain.FIPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        jade.domain.FIPAAgentManagement.DFAgentDescription[] descr;
		try {
			descr = yp.getAgentFromLocalID(eiFlight_Plan.getPilotID());
		} catch (jade.domain.FIPAException e) {
			e.printStackTrace();
		}
        outputsdefaultPlanAnswer.setFlightPlan(eiFlight_Plan);
//#end_node:CodeForCreatingRandomPlan <--- DO NOT REMOVE THIS

 }
 
}

 