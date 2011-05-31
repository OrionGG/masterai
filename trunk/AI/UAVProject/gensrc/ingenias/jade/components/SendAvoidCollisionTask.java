

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



public class SendAvoidCollisionTask extends Task{

 public SendAvoidCollisionTask(String id){
  super(id,"SendAvoidCollision");
 }



 public void execute() throws TaskException{


        StartAvoidCollision  eiStartAvoidCollision=(StartAvoidCollision)this.getFirstInputOfType("StartAvoidCollision");             

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
        
        	Hashtable<jade.core.AID, gov.nasa.worldwind.geom.Position> hPlanesPositionInConflict = eiStartAvoidCollision.getPlanesInConflict();
	        
        	ArrayList<Hashtable<jade.core.AID, gov.nasa.worldwind.geom.Position>> aConflictsAttended =
        	eiControllerMind.getConflictsAttended();
        	aConflictsAttended.add(hPlanesPositionInConflict);
        	eiControllerMind.setConflictsAttended(aConflictsAttended);
        	
	        Hashtable<jade.core.AID, Flight_Leg> hPilotNewFlightLeg = 
	        	new Hashtable<jade.core.AID, Flight_Leg>();
	        	
	        gov.nasa.worldwind.geom.Position[] aPlanesPositions = 
	        	hPlanesPositionInConflict.values().toArray(new gov.nasa.worldwind.geom.Position[hPlanesPositionInConflict.size()]);
	        	
	        jade.core.AID[] aPlanesAIDs = 
		        	hPlanesPositionInConflict.keySet().toArray(new jade.core.AID[hPlanesPositionInConflict.size()]);
		        	
	        	

	        for (int i = 0; i < hPlanesPositionInConflict.size(); i++) {
		        	
	        //for (Map.Entry<jade.core.AID, gov.nasa.worldwind.geom.Position> oPlanePositionInConflict: 
	        	//	hPlanesPositionInConflict.entrySet()) {
	        	
	        	jade.core.AID oPlaneAID = aPlanesAIDs[i];
	        
	        	Flight_Leg oFlightLeg = new Flight_Leg();
	        	
	        	oFlightLeg.setSpeedKMH(global.GlobalVarsAndMethods.dCruiseSpeedKMH/2);
	        	double dNewAltitude = global.GlobalVarsAndMethods.dCruiseAltitudeKM 
    									+ (0.610 * ((i/2)+1) * Math.pow(-1, i));
	        	oFlightLeg.setAltitudeKM(dNewAltitude);
	        	
	        	//oFlightLeg.setHead(null);
	        	int j = i+1;
	        	if(j >= hPlanesPositionInConflict.size()){
	        		j =0;
	        	}
	        	
	        	gov.nasa.worldwind.geom.Position oStartPosition = aPlanesPositions[i];
	        	gov.nasa.worldwind.geom.Position oEndPosition = aPlanesPositions[j];
	        	
	        	/*gov.nasa.worldwind.geom.LatLon oMidLatLon = 
	        		BasicFlightDynamics.BFD.getMidpoint(oStartPosition, oEndPosition);
	        	*/
	        	oFlightLeg.setStartPoint(oStartPosition);
	        	/*gov.nasa.worldwind.geom.Position  oMidPosition = 
	        		new gov.nasa.worldwind.geom.Position(oMidLatLon, dNewAltitude);*/
	        	oFlightLeg.setEndPoint(oEndPosition);
	        	
	        	ingenias.jade.AgentExternalDescription oPlaneAgentExternalDescription = 
	        		new ingenias.jade.AgentExternalDescription(oPlaneAID, "PlaneColaborator");
	        	
	        	oFlightLeg.setPlaneID(oPlaneAgentExternalDescription);
	        	oFlightLeg.setIsFromControllerOrder(true);
	        	
	        	
	        	ingenias.jade.AgentExternalDescription oPilotAgentExternalDescription = 
	        		global.GlobalVarsAndMethods.PlaneIdToPilotId.get(oPlaneAID);
	        	
	        	hPilotNewFlightLeg.put(oPilotAgentExternalDescription.id, oFlightLeg);
	        	outputsdefaultOrder.setPilotNewFlightLeg(hPilotNewFlightLeg);
	        	
	        	outputsdefaultControllerPilotInteracion.addCollaborators(oPilotAgentExternalDescription);
			}
        
//#end_node:INGENIASCodeComponent21 <--- DO NOT REMOVE THIS

 }
 
}

 