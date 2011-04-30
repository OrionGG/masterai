

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



public class Check_DestinationTask extends Task{

 public Check_DestinationTask(String id){
  super(id,"Check_Destination");
 }



 public void execute() throws TaskException{


        AllLegsCompleted  eiAllLegsCompleted=(AllLegsCompleted)this.getFirstInputOfType("AllLegsCompleted");             










  		Vector<TaskOutput> outputs = this.getOutputs();
  		TaskOutput defaultOutput= outputs.firstElement();
  		
  		  	
  		TaskOutput	outputsdefault=findOutputAlternative("default",
  																			outputs);
  		
		
		Throw_Change outputsdefaultThrow_Change=
			(Throw_Change)
				outputsdefault.getEntityByType("Throw_Change");
		
		
		
        YellowPages yp=null; // only available for initiators of interactions


//#start_node:INGENIASCodeComponent14 <--- DO NOT REMOVE THIS	
    	outputsdefaultThrow_Change.setAltitudeChange(-1);
    	outputsdefaultThrow_Change.setHeadChange(null);	
    	//stop the plane
        outputsdefaultThrow_Change.setSpeedChange(0);
        outputsdefaultThrow_Change.setPriority(10);
//#end_node:INGENIASCodeComponent14 <--- DO NOT REMOVE THIS

 }
 
}

 