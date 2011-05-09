

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



public class StartPlaneTask extends Task{

 public StartPlaneTask(String id){
  super(id,"StartPlane");
 }



 public void execute() throws TaskException{


        TurningOnPlane  eiTurningOnPlane=(TurningOnPlane)this.getFirstInputOfType("TurningOnPlane");             

        Plane_Mind  eiPlane_Mind=(Plane_Mind)this.getFirstInputOfType("Plane_Mind");             









        // This means that the task participates in the interaction StartPlaneInteraction
        RuntimeConversation  conversationContextStartPlaneInteraction=this.getConversationContext();


  		Vector<TaskOutput> outputs = this.getOutputs();
  		TaskOutput defaultOutput= outputs.firstElement();
  		
  		  	
  		TaskOutput	outputsdefault=findOutputAlternative("default",
  																			outputs);
  		
		InitiateUpdateStatus outputsdefaultInitiateUpdateStatus=
			(InitiateUpdateStatus)
				outputsdefault.getEntityByType("InitiateUpdateStatus");
		
		
		PlaneOn outputsdefaultPlaneOn=
			(PlaneOn)
				outputsdefault.getEntityByType("PlaneOn");
		
		
		
        YellowPages yp=null; // only available for initiators of interactions


//#start_node:INGENIASCodeComponent9 <--- DO NOT REMOVE THIS	
		/*enums.Airport[] oAirportvalues = enums.Airport.values();
		
		//set departure point
		int iIndexDeparture = generator.nextInt(oAirportvalues.length);
		
		enums.Airport oDeparture = oAirportvalues[iIndexDeparture];*/
        enums.Airport oDeparture = eiTurningOnPlane.getInitialAirport();
		eiPlane_Mind.setLatitude(oDeparture.getPosition().latitude);
		eiPlane_Mind.setLongitude(oDeparture.getPosition().longitude);
		eiPlane_Mind.setLastUpdatePosition(new Date());

		eiPlane_Mind.setAltitudeKM(Simulation.SimulationVars.dCruiseAltitudeKM);
		eiPlane_Mind.setSpeedKMH(Simulation.SimulationVars.dCruiseSpeedKMH);
		//set head in departure point
        Random generator = new Random();
		int iHeadDegrees = generator.nextInt(360);
		eiPlane_Mind.setHead(gov.nasa.worldwind.geom.Angle.fromDegrees(iHeadDegrees));
//#end_node:INGENIASCodeComponent9 <--- DO NOT REMOVE THIS

 }
 
}

 