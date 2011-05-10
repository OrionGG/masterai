

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



public class DescomposeDecisionTask extends Task{

 public DescomposeDecisionTask(String id){
  super(id,"DescomposeDecision");
 }



 public void execute() throws TaskException{


        Decision  eiDecision=(Decision)this.getFirstInputOfType("Decision");             

        Pilot_Mind  eiPilot_Mind=(Pilot_Mind)this.getFirstInputOfType("Pilot_Mind");             










  		Vector<TaskOutput> outputs = this.getOutputs();
  		TaskOutput defaultOutput= outputs.firstElement();
  		
  		  	
  		TaskOutput	outputsdefault=findOutputAlternative("default",
  																			outputs);
  		
		
		Throw_Instruction outputsdefaultThrow_Instruction=
			(Throw_Instruction)
				outputsdefault.getEntityByType("Throw_Instruction");
		
		
		
        YellowPages yp=null; // only available for initiators of interactions


//#start_node:INGENIASCodeComponent18 <--- DO NOT REMOVE THIS	
        outputsdefaultThrow_Instruction.setSpeedChange(-1);
        outputsdefaultThrow_Instruction.setAltitudeChange(-1);
        outputsdefaultThrow_Instruction.setHeadChange(null);
        
        
        Throw_Instruction oInstructionsRunning = eiPilot_Mind.getInstructionRunning();

        if(oInstructionsRunning != null){
	        if(oInstructionsRunning.getPriority() < eiDecision.getPriority()){
	        	oInstructionsRunning = outputsdefaultThrow_Instruction;
	        }else if(oInstructionsRunning.getPriority() == eiDecision.getPriority()){
	        	if(global.GlobalVarsAndMethods.containsSimilarInstrucctions(oInstructionsRunning, eiDecision)){
	        		outputsdefault.remove(outputsdefaultThrow_Instruction);
	            	return;
	        	}
	        }else if(oInstructionsRunning.getPriority() > eiDecision.getPriority()){
	        	outputsdefault.remove(outputsdefaultThrow_Instruction);
	        	return;
	        } 
        }
       
        if(eiDecision.getSpeedChange()!= -1){
        	
        }
        
        if(eiDecision.getAltitudeChange()!= -1){
        	
        }
        
        gov.nasa.worldwind.geom.Angle oHeadChange = eiDecision.getHeadChange();
        if(oHeadChange != null){
        	long lMiliseconds = Simulation.SimulationVars.iSleepTime * Simulation.SimulationVars.x;
        	//degrees per milisecond (3 degrees per second)
    		double dDPS = ((double)global.GlobalVarsAndMethods.nDegress/1000)*lMiliseconds;
    		
        	int dNDegreeAngle = (int) (oHeadChange.degrees/dDPS);
        	if(Math.abs(dNDegreeAngle)> 0){
        		
        		double dAngleTurn = dDPS * (dNDegreeAngle/ Math.abs(dNDegreeAngle));
        		outputsdefaultThrow_Instruction.setHeadChange(gov.nasa.worldwind.geom.Angle.fromDegrees(dAngleTurn));
        		

			}
        	else{
        		double dRestDegreeAngle = (oHeadChange.degrees%dDPS);
	        	outputsdefaultThrow_Instruction.setHeadChange(gov.nasa.worldwind.geom.Angle.fromDegrees(dRestDegreeAngle));
	        }

        	outputsdefaultThrow_Instruction.setPriority(eiDecision.getPriority());
        }
//#end_node:INGENIASCodeComponent18 <--- DO NOT REMOVE THIS

 }
 
}

 