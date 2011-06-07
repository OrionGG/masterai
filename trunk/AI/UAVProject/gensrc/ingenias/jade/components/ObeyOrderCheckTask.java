

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



public class ObeyOrderCheckTask extends Task{

 public ObeyOrderCheckTask(String id){
  super(id,"ObeyOrderCheck");
 }



 public void execute() throws TaskException{


        OrderFinished  eiOrderFinished=(OrderFinished)this.getFirstInputOfType("OrderFinished");             

        OrderOldLeg  eiOrderOldLeg=(OrderOldLeg)this.getFirstInputOfType("OrderOldLeg");             










  		Vector<TaskOutput> outputs = this.getOutputs();
  		TaskOutput defaultOutput= outputs.firstElement();
  		
  		  	
  		TaskOutput	outputsdefault=findOutputAlternative("default",
  																			outputs);
  		
		Flight_Leg outputsdefaultFlight_Leg=
			(Flight_Leg)
				outputsdefault.getEntityByType("Flight_Leg");
		
		StartLegChecker outputsdefaultStartLegChecker=
			(StartLegChecker)
				outputsdefault.getEntityByType("StartLegChecker");
		
		
		OrdenDone outputsdefaultOrdenDone=
			(OrdenDone)
				outputsdefault.getEntityByType("OrdenDone");
		
		
		
        YellowPages yp=null; // only available for initiators of interactions


//#start_node:INGENIASCodeComponent23 <--- DO NOT REMOVE THIS	

    	Flight_Leg oFlightLeg = eiOrderOldLeg.getOldFlightLeg();
    	
        if(eiOrderFinished.getIsBecauseOtherConflict()){
        	outputsdefault.remove(outputsdefaultFlight_Leg);
        	outputsdefault.remove(outputsdefaultStartLegChecker);
        }
        else{
            global.GlobalVarsAndMethods.copyFlightLeg(oFlightLeg, outputsdefaultFlight_Leg);
            
        }        
        
        outputsdefaultOrdenDone.setPlaneID(oFlightLeg.getPlaneID());
//#end_node:INGENIASCodeComponent23 <--- DO NOT REMOVE THIS

 }
 
}

 