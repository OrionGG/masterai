

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
        outputsdefaultThrow_Instruction.setSpeedChange(0);
        outputsdefaultThrow_Instruction.setAltitudeChange(0);
        outputsdefaultThrow_Instruction.setHeadChange(null);
        
        
        List<Throw_Instruction> oInstructionsRunning = eiPilot_Mind.getInstructionRunning();

        if(oInstructionsRunning.size() != 0){
        	int iInstructionsRunningPriority = global.GlobalVarsAndMethods.getMaxPriority(oInstructionsRunning);
	        if(iInstructionsRunningPriority < eiDecision.getPriority()){
	        	
	        }else if(iInstructionsRunningPriority == eiDecision.getPriority()){
	        	if(global.GlobalVarsAndMethods.containsSimilarInstrucctions(oInstructionsRunning, eiDecision)){
	        		outputsdefault.remove(outputsdefaultThrow_Instruction);
	            	return;
	        	}
	        }else if(iInstructionsRunningPriority > eiDecision.getPriority()){
	        	outputsdefault.remove(outputsdefaultThrow_Instruction);
	        	return;
	        } 
        }
       
        double dSpeedChange = eiDecision.getSpeedChange();
        if(dSpeedChange != 0){
        	global.GlobalVarsAndMethods.CreateSpeedInstruction(outputsdefaultThrow_Instruction,
					dSpeedChange);
        }
        
        double dAltitudeChange = eiDecision.getAltitudeChange();
        if(dAltitudeChange != 0){
        	global.GlobalVarsAndMethods.CreateAltitudeInstruction(outputsdefaultThrow_Instruction,
					dAltitudeChange);
        	
        }
        
        gov.nasa.worldwind.geom.Angle oHeadChange = eiDecision.getHeadChange();
        if(oHeadChange != null && oHeadChange.degrees != 0){
        	global.GlobalVarsAndMethods.CreateHeadInstruction(outputsdefaultThrow_Instruction, oHeadChange);
        }


    	outputsdefaultThrow_Instruction.setPriority(eiDecision.getPriority());
//#end_node:INGENIASCodeComponent18 <--- DO NOT REMOVE THIS

 }
 
}

 