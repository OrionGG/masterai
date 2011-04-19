

/**
 *
 *
 *  Description of the task /
 *
 * 
 *@author     Jorge J. Gomez
 *@version    1.0
 */

package ingenias.jade.components;

import java.util.*;

import gov.nasa.worldwind.geom.Position;
import ingenias.jade.exception.*;
import ingenias.jade.mental.Plane_Mind;



public  class Plane_Position_ServiceAppImp extends Plane_Position_ServiceApp{

 public Plane_Position_ServiceAppImp(){
  super();
 }


 public Position getCurrentPosition(){
//TODO: INSERT HERE YOUR CODE
	 Vector<ingenias.editor.entities.MentalEntity> oVector =  
		 this.getOwner().getMSM().getMentalEntityByType("Plane_Mind");
	 
	 Plane_Mind oPlane_Mind = (Plane_Mind) oVector.get(0);
	 
	 return new Position(oPlane_Mind.getLatitude(),oPlane_Mind.getLongitude(), oPlane_Mind.getAltitude());


} 
 
}

 