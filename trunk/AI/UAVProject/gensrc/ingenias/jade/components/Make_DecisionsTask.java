

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



public class Make_DecisionsTask extends Task{

 public Make_DecisionsTask(String id){
  super(id,"Make_Decisions");
 }



 public void execute() throws TaskException{


        CanCreateNewDecision  eiCanCreateNewDecision=(CanCreateNewDecision)this.getFirstInputOfType("CanCreateNewDecision");             

        Flight_Leg  eiFlight_Leg=(Flight_Leg)this.getFirstInputOfType("Flight_Leg");             

        Pilot_Mind  eiPilot_Mind=(Pilot_Mind)this.getFirstInputOfType("Pilot_Mind");             










  		Vector<TaskOutput> outputs = this.getOutputs();
  		TaskOutput defaultOutput= outputs.firstElement();
  		
  		  	
  		TaskOutput	outputsdefault=findOutputAlternative("default",
  																			outputs);
  		
		
		Decision outputsdefaultDecision=
			(Decision)
				outputsdefault.getEntityByType("Decision");
		
		
		
        YellowPages yp=null; // only available for initiators of interactions


//#start_node:INGENIASCodeComponent7 <--- DO NOT REMOVE THIS	
        outputsdefaultDecision.setSpeedChange(-1);
        outputsdefaultDecision.setAltitudeChange(-1);
        outputsdefaultDecision.setHeadChange(null);
        outputsdefaultDecision.setPriority(0);
        //outputsdefault.remove(outputsdefaultDecision);

		Vector<Plane_Position_ServiceAppImp> oVector = Plane_Position_ServiceInit.getAppsInitialised();
		
		for (Plane_Position_ServiceAppImp plane_Position_ServiceAppImp : oVector) {
			jade.core.AID oPlaneAID = eiFlight_Leg.getPlaneID().id;
			if(oPlaneAID.equals(plane_Position_ServiceAppImp.getOwner().getAID())){
				
				gov.nasa.worldwind.geom.Position oPosition = plane_Position_ServiceAppImp.getCurrentPosition();

				gov.nasa.worldwind.geom.Position oEndPoint = eiFlight_Leg.getEndPoint();
				
				//Calculating speed
				Decision oDecisionSpeedChange =
					new Decision();
				oDecisionSpeedChange.setSpeedChange(-1);
				oDecisionSpeedChange.setAltitudeChange(-1);
				oDecisionSpeedChange.setHeadChange(null);
				oDecisionSpeedChange.setPriority(0);
				
				double dCruisingSpeedKMH = eiFlight_Leg.getSpeedKMH();
				double dCurrentSpeed = plane_Position_ServiceAppImp.getCurrentSpeed();
				
				oDecisionSpeedChange.setSpeedChange(dCruisingSpeedKMH - dCurrentSpeed);
				//outputsdefault.add(new OutputEntity(oDecisionSpeedChange,TaskOperations.CreateWF));
				outputsdefaultDecision.setSpeedChange(dCruisingSpeedKMH - dCurrentSpeed);
				
				//Calculating High to change
				double alt1 = oPosition.getAltitude();
				double alt2 = eiFlight_Leg.getAltitudeKM()*1000;
				Decision oDecisionAltitudeChange =
					new Decision();
				oDecisionAltitudeChange.setSpeedChange(-1);
				oDecisionAltitudeChange.setAltitudeChange(-1);
				oDecisionAltitudeChange.setHeadChange(null);
				oDecisionAltitudeChange.setPriority(0);
				
				oDecisionAltitudeChange.setAltitudeChange(alt2-alt1); 
				//outputsdefault.add(new OutputEntity(oDecisionAltitudeChange,TaskOperations.CreateWF));
				outputsdefaultDecision.setAltitudeChange(alt2-alt1);
				
				//Calculating Angle to turn
				Decision oDecisionHeadChange =
					new Decision();
				oDecisionHeadChange.setSpeedChange(-1);
				oDecisionHeadChange.setAltitudeChange(-1);
				oDecisionHeadChange.setHeadChange(null);
				oDecisionHeadChange.setPriority(0);
				
				double lat1 = oPosition.getLatitude().radians;
				double lon1 = oPosition.getLongitude().radians;
				double lat2 = oEndPoint.getLatitude().radians;
				double lon2 = oEndPoint.getLongitude().radians;
				
				gov.nasa.worldwind.geom.Angle oAngle = BasicFlightDynamics.BFD.getHead(lat1, lon1, lat2, lon2);

				if(oAngle.degrees < 0){
					oAngle = oAngle.addDegrees(360);
				}
				
				gov.nasa.worldwind.geom.Angle oCurrentHead = plane_Position_ServiceAppImp.getCurrentHead();
				if(oCurrentHead.degrees != oAngle.degrees){
					gov.nasa.worldwind.geom.Angle oAngleToTurn = BasicFlightDynamics.BFD.AngleToTurn(oCurrentHead, oAngle);
					oDecisionHeadChange.setHeadChange(oAngleToTurn); 
					outputsdefaultDecision.setHeadChange(oAngleToTurn); 
				}
				
				//outputsdefault.add(new OutputEntity(oDecisionHeadChange,TaskOperations.CreateWF));

		        
				break;
			}

		}
	
//#end_node:INGENIASCodeComponent7 <--- DO NOT REMOVE THIS

 }
 
}

 