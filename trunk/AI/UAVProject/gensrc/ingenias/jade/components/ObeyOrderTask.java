

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



public class ObeyOrderTask extends Task{

 public ObeyOrderTask(String id){
  super(id,"ObeyOrder");
 }



 public void execute() throws TaskException{


        CanCreateNewDecision  eiCanCreateNewDecision=(CanCreateNewDecision)this.getFirstInputOfType("CanCreateNewDecision");             

        OrderNewParametersAndLeg  eiOrderNewParametersAndLeg=(OrderNewParametersAndLeg)this.getFirstInputOfType("OrderNewParametersAndLeg");             










  		Vector<TaskOutput> outputs = this.getOutputs();
  		TaskOutput defaultOutput= outputs.firstElement();
  		
  		  	
  		TaskOutput	outputsdefault=findOutputAlternative("default",
  																			outputs);
  		
		
		OrderFinished outputsdefaultOrderFinished=
			(OrderFinished)
				outputsdefault.getEntityByType("OrderFinished");
		
		Decision outputsdefaultDecision=
			(Decision)
				outputsdefault.getEntityByType("Decision");
		
		
		
        YellowPages yp=null; // only available for initiators of interactions


//#start_node:INGENIASCodeComponent27 <--- DO NOT REMOVE THIS	

		Flight_Leg eiFlight_Leg = eiOrderNewParametersAndLeg.getFlightLeg();
		NewParameters oNewParameters = eiOrderNewParametersAndLeg.getOrderNewParameters();
		double oNewParametersgetAltitude = oNewParameters.getAltitudeKM()*1000;
		boolean bOrderDone = true;
		
        Vector<Plane_Position_ServiceAppImp> oVector = Plane_Position_ServiceInit.getAppsInitialised();
		
		for (Plane_Position_ServiceAppImp plane_Position_ServiceAppImp : oVector) {
			jade.core.AID oPlaneAID = eiFlight_Leg.getPlaneID().id;
			if(oPlaneAID.equals(plane_Position_ServiceAppImp.getOwner().getAID())){
				double dSpeedChange = 0;
				gov.nasa.worldwind.geom.Angle oHeadChange = null;
				double dAltitudeChange = 0;
				
				if((oNewParameters.getSpeedKMH() > 0) && 
				(oNewParameters.getSpeedKMH() != plane_Position_ServiceAppImp.getCurrentSpeed())){
					dSpeedChange = oNewParameters.getSpeedKMH() -plane_Position_ServiceAppImp.getCurrentSpeed() ;
					bOrderDone = false;
				}
				
				if((oNewParameters.getHead() != null) && 
				(oNewParameters.getHead() != plane_Position_ServiceAppImp.getCurrentHead())){
					oHeadChange = 
						gov.nasa.worldwind.geom.Angle.fromDegrees(
						oNewParameters.getHead().degrees - plane_Position_ServiceAppImp.getCurrentHead().degrees);
					bOrderDone = false;
				}
				
				if((oNewParametersgetAltitude > 0) && 
						(oNewParametersgetAltitude != plane_Position_ServiceAppImp.getCurrentPosition().getAltitude())){
					dAltitudeChange = oNewParametersgetAltitude - plane_Position_ServiceAppImp.getCurrentPosition().getAltitude();
					bOrderDone = false;
				}
				outputsdefaultDecision.setSpeedChange(dSpeedChange);
				outputsdefaultDecision.setHeadChange(oHeadChange);
				outputsdefaultDecision.setAltitudeChange(dAltitudeChange);
				outputsdefaultDecision.setPriority(9);
				break;
			}
		}
		
		if(bOrderDone){
			outputsdefaultOrderFinished.setFlightLeg(eiFlight_Leg);
			outputsdefault.remove(outputsdefaultDecision);
		}
		else{
			outputsdefault.remove(outputsdefaultOrderFinished);
		}
//#end_node:INGENIASCodeComponent27 <--- DO NOT REMOVE THIS

 }
 
}

 