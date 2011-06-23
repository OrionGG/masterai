

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



public class CreateNewOrdersTask extends Task{

 public CreateNewOrdersTask(String id){
  super(id,"CreateNewOrders");
 }



 public void execute() throws TaskException{


        CanStartSendOrder  eiCanStartSendOrder=(CanStartSendOrder)this.getFirstInputOfType("CanStartSendOrder");             










  		Vector<TaskOutput> outputs = this.getOutputs();
  		TaskOutput defaultOutput= outputs.firstElement();
  		
  		  	
  		TaskOutput	outputsdefault=findOutputAlternative("default",
  																			outputs);
  		
		
		StartAvoidCollision outputsdefaultStartAvoidCollision=
			(StartAvoidCollision)
				outputsdefault.getEntityByType("StartAvoidCollision");
		
		
		
        YellowPages yp=null; // only available for initiators of interactions


//#start_node:INGENIASCodeComponent28 <--- DO NOT REMOVE THIS	
        	outputsdefault.remove(outputsdefaultStartAvoidCollision);
        	ArrayList<jade.core.AID> lPlanesInConflict = eiCanStartSendOrder.getPlanesInConflict();
	
	        for (int i = 0; i < lPlanesInConflict.size(); i++) {
	        	
		        	jade.core.AID oPlaneAIDi = lPlanesInConflict.get(i);
		        
		        	Flight_Leg oFlightLeg = new Flight_Leg();
		        	oFlightLeg.setPlaneID(new ingenias.jade.AgentExternalDescription(oPlaneAIDi, "PlaneColaborator"));
		        	
		        	oFlightLeg.setSpeedKMH(global.GlobalVarsAndMethods.dCruiseSpeedKMH * Math.pow(0.8, i+1));
		        	double dNewAltitude = global.GlobalVarsAndMethods.dCruiseAltitudeKM 
	    									+ (0.610 * ((i/2)+1) * Math.pow(-1, i));
		        	oFlightLeg.setAltitudeKM(dNewAltitude);
		        	
		        	int j = i+1;
		        	if(j >= lPlanesInConflict.size()){
		        		j =0;
		        	}
		        	
		        	jade.core.AID oPlaneAIDj = lPlanesInConflict.get(j);
		        	
		        	gov.nasa.worldwind.geom.Position oStartPosition = 
		        		global.GlobalVarsAndMethods.PlanesPositionApps.get(oPlaneAIDi).getCurrentPosition();
		        	gov.nasa.worldwind.geom.Position oEndPosition = 
		        		global.GlobalVarsAndMethods.PlanesPositionApps.get(oPlaneAIDj).getCurrentPosition();
		        	
		        	oFlightLeg.setStartPoint(oStartPosition);
		        	//oFlightLeg.setEndPoint(oEndPosition);

		        	/*gov.nasa.worldwind.geom.LatLon oMidLatLon = 
		        		BasicFlightDynamics.BFD.getMidpoint(oStartPosition, oEndPosition);
		        	
		        	gov.nasa.worldwind.geom.Position  oMidPosition = 
		        		new gov.nasa.worldwind.geom.Position(oMidLatLon, dNewAltitude);*/
		        	//oFlightLeg.setEndPoint(oMidPosition);
		        	
		        	
		        	oFlightLeg.setIsFromControllerOrder(true);
		        	
		        	ingenias.jade.AgentExternalDescription oPilotAgentExternalDescription = 
		        		global.GlobalVarsAndMethods.PlaneIdToPilotId.get(oPlaneAIDi);
		        	
		        	StartAvoidCollision oStartAvoidCollision = new StartAvoidCollision();
		        	oStartAvoidCollision.setPilotID(oPilotAgentExternalDescription);
		        	oStartAvoidCollision.setNewFlightLeg(oFlightLeg);
		        	
		        	outputsdefault.add(new OutputEntity(oStartAvoidCollision, TaskOperations.CreateWF));
	        }
	        
//#end_node:INGENIASCodeComponent28 <--- DO NOT REMOVE THIS

 }
 
}

 