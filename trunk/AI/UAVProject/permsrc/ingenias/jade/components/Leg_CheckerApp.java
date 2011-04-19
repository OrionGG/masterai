

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
import ingenias.jade.mental.Flight_Leg;






public abstract class Leg_CheckerApp extends Application{


 public Leg_CheckerApp(){
  super();
 }
 
 public boolean isLegCompleted(Flight_Leg eiFlight_Leg){
	 boolean bResutl = false;
	 Vector<Plane_Position_ServiceAppImp> oVector = Plane_Position_ServiceInit.getAppsInitialised();
     if(oVector.size()> 0){
     	Plane_Position_ServiceAppImp eaPlane_Position_ServiceAppImp = oVector.get(0);
     	//eaPlane_Position_ServiceAppImp.getOwner();
     	gov.nasa.worldwind.geom.Position oPosition = eaPlane_Position_ServiceAppImp.getCurrentPosition();
     	double dDistance = BasicFlightDynamics.BFD.getDistance(eiFlight_Leg.getEndPoint(), oPosition);
     	if(dDistance < 10){
     		bResutl =  true;
     	}
     	
     }
     return bResutl;
 }


 
}

 