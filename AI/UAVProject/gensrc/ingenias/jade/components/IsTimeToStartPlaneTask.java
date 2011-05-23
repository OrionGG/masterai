

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



public class IsTimeToStartPlaneTask extends Task{

 public IsTimeToStartPlaneTask(String id){
  super(id,"IsTimeToStartPlane");
 }



 public void execute() throws TaskException{


        CanInitiateStartPlane  eiCanInitiateStartPlane=(CanInitiateStartPlane)this.getFirstInputOfType("CanInitiateStartPlane");             

        Pilot_Mind  eiPilot_Mind=(Pilot_Mind)this.getFirstInputOfType("Pilot_Mind");             










  		Vector<TaskOutput> outputs = this.getOutputs();
  		TaskOutput defaultOutput= outputs.firstElement();
  		
  		  	
  		TaskOutput	outputsdefault=findOutputAlternative("default",
  																			outputs);
  		
		
		InitiateStartPlane outputsdefaultInitiateStartPlane=
			(InitiateStartPlane)
				outputsdefault.getEntityByType("InitiateStartPlane");
		
		
		
        YellowPages yp=null; // only available for initiators of interactions


//#start_node:INGENIASCodeComponent29 <--- DO NOT REMOVE THIS	
        Date oDate = eiPilot_Mind.getPilotFlightPlan().getDepartureTime();
        /*while(oDate.after(new Date())){
        	try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }*/
        if(oDate.after(new Date())){
        	outputsdefault.remove(outputsdefaultInitiateStartPlane);
            /*yp=(YellowPages)this.getApplication("YellowPages");
            ((ingenias.jade.agents.PilotJADEAgent)yp.ja).getMSM().addMentalEntity(eiCanInitiateStartPlane);*/
        	outputsdefault.add(new OutputEntity(eiCanInitiateStartPlane, TaskOperations.CreateWF));
        	try {
				Thread.sleep((long)(1000 * Math.random()));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

//#end_node:INGENIASCodeComponent29 <--- DO NOT REMOVE THIS

 }
 
}

 