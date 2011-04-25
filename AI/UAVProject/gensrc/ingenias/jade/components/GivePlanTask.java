

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
		Random generator = new Random();
		
		/*//set departure point
		int iIndexDeparture = generator.nextInt(oWaypointvalues.length);
		
		lIndexUsed.add(iIndexDeparture);
		enums.Waypoint oDeparture = oWaypointvalues[iIndexDeparture];
		oFlightPlan.setDeparturePoint(oDeparture.getoPosition());*/
		Vector<Plane_Position_ServiceAppImp> oVector = Plane_Position_ServiceInit.getAppsInitialised();
        if(oVector.size()> 0){
        	Plane_Position_ServiceAppImp eaPlane_Position_ServiceAppImp = oVector.get(0);
        	//eaPlane_Position_ServiceAppImp.getOwner();
        	gov.nasa.worldwind.geom.Position oDeparture = eaPlane_Position_ServiceAppImp.getCurrentPosition();
        	oFlightPlan.setDeparturePoint(oDeparture);
        }
		
		enums.Airport[] oAirportvalues = enums.Airport.values();
		
		//set departure point
		int iIndexDestination = generator.nextInt(oAirportvalues.length);
		
		enums.Airport oDestination = oAirportvalues[iIndexDestination];
		oFlightPlan.setDestinationPoint(oDestination.getPosition());
		
        	
        //Adding Waypoints
		enums.Waypoint[] oWaypointvalues = enums.Waypoint.values();
		int iWaypointsNumber = 15;
		int randomIndex = generator.nextInt(iWaypointsNumber);
		List<gov.nasa.worldwind.geom.Position> oWayPoints = new ArrayList<gov.nasa.worldwind.geom.Position>(randomIndex);

		List<Integer> lIndexUsed = new ArrayList<Integer>();
		
		for (int i = 0; i < randomIndex; i++) {
			int randomWaypoint = generator.nextInt(oWaypointvalues.length);
			
			int j =0;
			while((j<iWaypointsNumber) &&
					BasicFlightDynamics.BFD.moreThanDobleDistance(oFlightPlan, i,  oWaypointvalues[randomWaypoint], oWayPoints)){
				randomWaypoint = generator.nextInt(oWaypointvalues.length);
				j++;
			}
			
			if(j == iWaypointsNumber){
				break;
			}
			
			while(lIndexUsed.contains(randomWaypoint)){
				randomWaypoint = generator.nextInt(oWaypointvalues.length);
			}
			lIndexUsed.add(randomWaypoint);
			enums.Waypoint oWaypoint = oWaypointvalues[randomWaypoint];
			oWayPoints.add(oWaypoint.getoPosition());
		}
		oFlightPlan.setWaypoints(oWayPoints);
		
		//Standard for a Boeing 737
		oFlightPlan.setCruisingAltitudeKM(10);
		oFlightPlan.setCruisingSpeedKMH(906);
		
		outputsdefaultPlanAnswer.setFlightPlan(oFlightPlan);
//#end_node:CodeForCreatingRandomPlan <--- DO NOT REMOVE THIS

 }
 
}

 