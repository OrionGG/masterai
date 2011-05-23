

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



public class CreateNewDecisionTask extends Task{

 public CreateNewDecisionTask(String id){
  super(id,"CreateNewDecision");
 }



 public void execute() throws TaskException{


        Pilot_Mind  eiPilot_Mind=(Pilot_Mind)this.getFirstInputOfType("Pilot_Mind");             










  		Vector<TaskOutput> outputs = this.getOutputs();
  		TaskOutput defaultOutput= outputs.firstElement();
  		
  		  	
  		TaskOutput	outputsdefault=findOutputAlternative("default",
  																			outputs);
  		
		
		CanCreateNewDecision outputsdefaultCanCreateNewDecision=
			(CanCreateNewDecision)
				outputsdefault.getEntityByType("CanCreateNewDecision");
		
		
		
        YellowPages yp=null; // only available for initiators of interactions


//#start_node:INGENIASCodeComponent20 <--- DO NOT REMOVE THIS	
        boolean bCanCreateNewDecisionDeleted = false;
        yp=(YellowPages)this.getApplication("YellowPages");
        if(((ingenias.jade.agents.PilotJADEAgent)yp.ja).
        		getMSM().getMentalEntityByType("CanCreateNewDecision").size() > 0){
        	outputsdefault.remove(outputsdefaultCanCreateNewDecision);
        	bCanCreateNewDecisionDeleted = true;
        }
        if(!bCanCreateNewDecisionDeleted){
        	//id the last decision was fewer than a minute ago => sleep 
	        Date oLastDecisionDate = eiPilot_Mind.getLastDecisionDate();
	        Date oNow = new Date();
	        long lDifferOfMiliseconds = oNow.getTime() - oLastDecisionDate.getTime();
	        if(lDifferOfMiliseconds < Simulation.SimulationVars.iSleepTime * 2){
	        	/*global.GlobalVarsAndMethods.sleep(Simulation.SimulationVars.iSleepTime/4);
	        	oNow = new Date();
	            lDifferOfMiliseconds = oNow.getTime() - oLastDecisionDate.getTime();*/
	        	outputsdefault.remove(outputsdefaultCanCreateNewDecision);
	        }
        }
//#end_node:INGENIASCodeComponent20 <--- DO NOT REMOVE THIS

 }
 
}

 