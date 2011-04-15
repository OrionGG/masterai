

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



public class GivePlanTask extends Task{

 public GivePlanTask(String id){
  super(id,"GivePlan");
 }



 public void execute() throws TaskException{


        PlanRequest  eiPlanRequest=(PlanRequest)this.getFirstInputOfType("PlanRequest");             










  		Vector<TaskOutput> outputs = this.getOutputs();
  		TaskOutput defaultOutput= outputs.firstElement();
  		
  		  	
  		TaskOutput	outputsdefault=findOutputAlternative("default",
  																			outputs);
  		
		
		PlanAnswer outputsdefaultPlanAnswer=
			(PlanAnswer)
				outputsdefault.getEntityByType("PlanAnswer");
		
		
		
        YellowPages yp=null; // only available for initiators of interactions


//#start_node:CodeForCreatingRandomPlan <--- DO NOT REMOVE THIS	
  		Flight_Plan oFlightPlan = new ingenias.jade.mental.Flight_Plan();
		List<Integer> lIndexUsed = new ArrayList<Integer>();
		Random generator = new Random();
		enums.Waypoint[] oWaypointvalues = enums.Waypoint.values();
		
		//set departure point
		int iIndexDeparture = generator.nextInt(oWaypointvalues.length);
		
		lIndexUsed.add(iIndexDeparture);
		enums.Waypoint oDeparture = oWaypointvalues[iIndexDeparture];
		oFlightPlan.setDeparturePoint(oDeparture.getoPosition());
		
		 
		//set DestinationPoint
		int iIndexDestination = generator.nextInt(oWaypointvalues.length);
		
		lIndexUsed.add(iIndexDestination);
		enums.Waypoint oDestination = oWaypointvalues[iIndexDestination];
		oFlightPlan.setDestinationPoint(oDestination.getoPosition());
		
        	
        //Adding Waypoints
		int randomIndex = generator.nextInt(oWaypointvalues.length-2);
		List<gov.nasa.worldwind.geom.Position> oWayPoints = new ArrayList<gov.nasa.worldwind.geom.Position>(randomIndex);
       
		for (int i = 0; i < randomIndex; i++) {
			int randomWaypoint = generator.nextInt(oWaypointvalues.length);
			while(lIndexUsed.contains(randomWaypoint)){
				randomWaypoint = generator.nextInt(oWaypointvalues.length);
			}
			lIndexUsed.add(randomWaypoint);
			enums.Waypoint oWaypoint = oWaypointvalues[randomWaypoint];
			oWayPoints.add(oWaypoint.getoPosition());
		}
		oFlightPlan.setWaypoints(oWayPoints);
		
		
		outputsdefaultPlanAnswer.setFlightPlan(oFlightPlan);
//#end_node:CodeForCreatingRandomPlan <--- DO NOT REMOVE THIS

 }
 
}

 