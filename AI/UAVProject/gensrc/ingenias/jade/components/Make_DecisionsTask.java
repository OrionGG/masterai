

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


        Flight_Leg  eiFlight_Leg=(Flight_Leg)this.getFirstInputOfType("Flight_Leg");             










  		Vector<TaskOutput> outputs = this.getOutputs();
  		TaskOutput defaultOutput= outputs.firstElement();
  		
  		  	
  		TaskOutput	outputsdefault=findOutputAlternative("default",
  																			outputs);
  		
		
		Throw_Change outputsdefaultThrow_Change=
			(Throw_Change)
				outputsdefault.getEntityByType("Throw_Change");
		
		
		
        YellowPages yp=null; // only available for initiators of interactions


//#start_node:INGENIASCodeComponent7 <--- DO NOT REMOVE THIS	
        Vector<Plane_Position_ServiceAppImp> oVector = Plane_Position_ServiceInit.getAppsInitialised();
        if(oVector.size()> 0){
        	Plane_Position_ServiceAppImp eaPlane_Position_ServiceAppImp = oVector.get(0);
        	gov.nasa.worldwind.geom.Position oPosition = eaPlane_Position_ServiceAppImp.getCurrentPosition();
        	
        	gov.nasa.worldwind.geom.Position oEndPoint = eiFlight_Leg.getEndPont();
        	
        	double lat1 = oPosition.getLatitude().radians;
        	double lat2 = oEndPoint.getLatitude().radians;
        	double lon1 = oPosition.getLongitude().radians;
            double lon2 = oEndPoint.getLongitude().radians;
                		
        	double dLat = lat2-lat1;
        	double dLon = lon2-lon1; 
        	double y = Math.sin(dLon) * Math.cos(lat2);
        	double x = Math.cos(lat1)*Math.sin(lat2) -
        	        Math.sin(lat1)*Math.cos(lat2)*Math.cos(dLon);
        	double brng = Math.atan2(y, x);
        	gov.nasa.worldwind.geom.Angle oAngle1 = gov.nasa.worldwind.geom.Angle.fromRadians(brng);
        	
        	/*
        	Other way of calc head
        	gov.nasa.worldwind.geom.Angle oCol1 = gov.nasa.worldwind.geom.Angle.fromDegrees(90).subtract(oPosition.getLatitude());
        	gov.nasa.worldwind.geom.Angle oCol2 = gov.nasa.worldwind.geom.Angle.fromDegrees(90).subtract(oEndPoint.getLatitude());
        	
        	gov.nasa.worldwind.geom.Angle oDieAng = oPosition.getLongitude().subtract(oEndPoint.getLongitude());
        	
        	double oMaxCirArc = Math.cos(oCol1.radians)*Math.cos(oCol2.radians)+
        						Math.sin(oCol1.radians)*Math.sin(oCol2.radians)*Math.cos(oDieAng.radians);
        	double app = Math.acos(oMaxCirArc);
        	
        	double brng2 = Math.asin((Math.sin(oDieAng.radians)*Math.sin(oCol2.radians))/Math.sin(app));
        	gov.nasa.worldwind.geom.Angle oAngle2 = gov.nasa.worldwind.geom.Angle.fromRadians(brng2);  	
*/
        	outputsdefaultThrow_Change.setHeadChange(oAngle1);

        }
//#end_node:INGENIASCodeComponent7 <--- DO NOT REMOVE THIS

 }
 
}

 