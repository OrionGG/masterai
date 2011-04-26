

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



public class Flight_Plan_MonitoringTask extends Task{

 public Flight_Plan_MonitoringTask(String id){
  super(id,"Flight_Plan_Monitoring");
 }



 public void execute() throws TaskException{


        NotAllLegsCompleted  eiNotAllLegsCompleted=(NotAllLegsCompleted)this.getFirstInputOfType("NotAllLegsCompleted");             

        Pilot_Mind  eiPilot_Mind=(Pilot_Mind)this.getFirstInputOfType("Pilot_Mind");             










  		Vector<TaskOutput> outputs = this.getOutputs();
  		TaskOutput defaultOutput= outputs.firstElement();
  		
  		  	
  		TaskOutput	outputsdefault=findOutputAlternative("default",
  																			outputs);
  		
		
		Flight_Leg outputsdefaultFlight_Leg=
			(Flight_Leg)
				outputsdefault.getEntityByType("Flight_Leg");
		
		
		
        YellowPages yp=null; // only available for initiators of interactions


//#start_node:INGENIASCodeComponent5 <--- DO NOT REMOVE THIS	
        int iLegsCompleted = eiPilot_Mind.getLegsCompleted();
        Flight_Plan oFlightPlan = eiPilot_Mind.getPilotFlightPlan();
        gov.nasa.worldwind.geom.Position oStartPoint = null;
        gov.nasa.worldwind.geom.Position oEndPoint = null;
        if(iLegsCompleted == 0 ){
        	oStartPoint = oFlightPlan.getDeparturePoint();
        }
        else{
        	oStartPoint = oFlightPlan.getWaypoints().get(iLegsCompleted-1);
        }
        
        if(oFlightPlan.getWaypoints().size() <= iLegsCompleted){
        	oEndPoint = oFlightPlan.getDestinationPoint();
        }
        else{
        	oEndPoint = oFlightPlan.getWaypoints().get(iLegsCompleted);
        }
        
        outputsdefaultFlight_Leg.setStartPoint(oStartPoint);
        outputsdefaultFlight_Leg.setEndPoint(oEndPoint);
    	
//#end_node:INGENIASCodeComponent5 <--- DO NOT REMOVE THIS

 }
 
}

 