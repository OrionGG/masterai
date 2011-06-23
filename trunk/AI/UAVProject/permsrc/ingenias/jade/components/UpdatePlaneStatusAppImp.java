

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

import ingenias.jade.exception.*;
import ingenias.jade.mental.Plane_Mind;



public  class UpdatePlaneStatusAppImp extends UpdatePlaneStatusApp{

 public UpdatePlaneStatusAppImp(){
  super();
 }


 public void start(Plane_Mind  eiPlane_Mind){
	 	thread.UpdatePlaneStatusThread oUpdatePlaneStatusThread = new thread.UpdatePlaneStatusThread(
	 			eiPlane_Mind,this);
		Thread thread = new Thread(oUpdatePlaneStatusThread);
		thread.start();

} 
 
}

 