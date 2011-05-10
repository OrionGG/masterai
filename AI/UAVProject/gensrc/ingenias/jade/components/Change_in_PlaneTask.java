

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



public class Change_in_PlaneTask extends Task{

 public Change_in_PlaneTask(String id){
  super(id,"Change_in_Plane");
 }



 public void execute() throws TaskException{


        Manoeuvre  eiManoeuvre=(Manoeuvre)this.getFirstInputOfType("Manoeuvre");             

        Plane_Mind  eiPlane_Mind=(Plane_Mind)this.getFirstInputOfType("Plane_Mind");             





			
        UpdatePlaneStatusApp eaUpdatePlaneStatus=(UpdatePlaneStatusApp)this.getApplication("UpdatePlaneStatus");




        // This means that the task participates in the interaction PilotPlaneInteraction
        RuntimeConversation  conversationContextPilotPlaneInteraction=this.getConversationContext();


  		Vector<TaskOutput> outputs = this.getOutputs();
  		TaskOutput defaultOutput= outputs.firstElement();
  		
  		  	
  		TaskOutput	outputsdefault=findOutputAlternative("default",
  																			outputs);
  		
		
		Plane_Change outputsdefaultPlane_Change=
			(Plane_Change)
				outputsdefault.getEntityByType("Plane_Change");
		
		
		
        YellowPages yp=null; // only available for initiators of interactions


//#start_node:INGENIASCodeComponent11 <--- DO NOT REMOVE THIS	
        boolean runManoeuvre = false;
        if(eiManoeuvre.getSpeedChange()!= -1){
        	//runManoeuvre = thread.RunManoeuvreChangeSpeed.runManoeuvre(eiManoeuvre, eiPlane_Mind,eaUpdatePlaneStatus);

			runManoeuvre = true;
        }if(eiManoeuvre.getAltitudeChange()!= -1){
        	//thread.RunManoeuvreChangeAltitude.runManoeuvreTurnHead(eiManoeuvre, eiPlane_Mind,eaUpdatePlaneStatus);
        	
			runManoeuvre = true;
        }
        if(eiManoeuvre.getHeadChange()!= null){
        	
        	gov.nasa.worldwind.geom.Angle oNewAngle = eiPlane_Mind.getHead().add(eiManoeuvre.getHeadChange());
			oNewAngle = gov.nasa.worldwind.geom.Angle.fromDegrees((oNewAngle.degrees+360)%360);
						
			ingenias.jade.mental.Change_Plane_Status event=new ingenias.jade.mental.Change_Plane_Status();
	        event.setNewHead(oNewAngle);
			try {
				Vector<MentalEntity> lChange_Plane_Status = eaUpdatePlaneStatus.getOwner().getMSM().getMentalEntityByType("Change_Plane_Status");
				
				if(lChange_Plane_Status.size()<=0){
					eaUpdatePlaneStatus.getOwner().getMSM().addMentalEntity(event);
				}
				else{
					ingenias.jade.mental.Change_Plane_Status oChange_Plane_Position = (ingenias.jade.mental.Change_Plane_Status)lChange_Plane_Status.get(0);
					oChange_Plane_Position.setNewHead(event.getNewHead());
				}
				
			} catch (ingenias.exception.InvalidEntity e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			runManoeuvre = true;
        }
        
        if(runManoeuvre){
	        try {
				Thread.sleep(Simulation.SimulationVars.iSleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}       
        }
		
	
//#end_node:INGENIASCodeComponent11 <--- DO NOT REMOVE THIS

 }
 
}

 