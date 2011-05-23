

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



public class CreateEmptyPlansTask extends Task{

 public CreateEmptyPlansTask(String id){
  super(id,"CreateEmptyPlans");
 }



 public void execute() throws TaskException{


        CreatingEmptyPlans  eiCreatingEmptyPlans=(CreatingEmptyPlans)this.getFirstInputOfType("CreatingEmptyPlans");             










  		Vector<TaskOutput> outputs = this.getOutputs();
  		TaskOutput defaultOutput= outputs.firstElement();
  		
  		  	
  		TaskOutput	outputsdefault=findOutputAlternative("default",
  																			outputs);
  		
		
		
		
        YellowPages yp=null; // only available for initiators of interactions


//#start_node:INGENIASCodeComponent16 <--- DO NOT REMOVE THIS	
        //outputsdefault.remove(outputsdefaultFlight_Plan);
        yp=(YellowPages)this.getApplication("YellowPages");
        String PilotRole = "PilotColaborator";
        String PlaneRole = "PlaneColaborator";
        List<jade.domain.FIPAAgentManagement.DFAgentDescription> lDFPilotsAgentDescription = new java.util.ArrayList();
        List<jade.domain.FIPAAgentManagement.DFAgentDescription> lDFPlanesAgentDescription = new java.util.ArrayList();
		try {
			jade.domain.FIPAAgentManagement.DFAgentDescription[] aDFPilotsAgentDescription  = yp.getAgents(PilotRole);
			lDFPilotsAgentDescription = Arrays.asList(aDFPilotsAgentDescription);
			jade.domain.FIPAAgentManagement.DFAgentDescription[] aDFPlanesAgentDescription = yp.getAgents(PlaneRole);
			lDFPlanesAgentDescription = Arrays.asList(aDFPlanesAgentDescription);
		} catch (jade.domain.FIPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 0; i < lDFPilotsAgentDescription.size(); i++) {
			jade.domain.FIPAAgentManagement.DFAgentDescription dfPilotAgentDescription = lDFPilotsAgentDescription.get(i); 
			if(i >= lDFPlanesAgentDescription.size()){
				break;
			}
			jade.domain.FIPAAgentManagement.DFAgentDescription dfPlaneAgentDescription = lDFPlanesAgentDescription.get(i); 
			Flight_Plan oFlightPlan = global.GlobalVarsAndMethods.CreateNewPlan();
			ingenias.jade.AgentExternalDescription oPilotAgentExternalDescription = new ingenias.jade.AgentExternalDescription(dfPilotAgentDescription.getName(), PilotRole);
			oFlightPlan.setPilotID(oPilotAgentExternalDescription);
			ingenias.jade.AgentExternalDescription oPlaneAgentExternalDescription = new ingenias.jade.AgentExternalDescription(dfPlaneAgentDescription.getName(), PlaneRole);
			oFlightPlan.setPlaneID(oPlaneAgentExternalDescription);
			global.GlobalVarsAndMethods.PlaneIdToPilotId.put(dfPlaneAgentDescription.getName(), oPilotAgentExternalDescription);

			outputsdefault.add( new OutputEntity(oFlightPlan, TaskOperations.CreateWF));
			
		}
//#end_node:INGENIASCodeComponent16 <--- DO NOT REMOVE THIS

 }
 
}

 