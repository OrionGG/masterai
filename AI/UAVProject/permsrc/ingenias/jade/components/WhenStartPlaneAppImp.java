

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

import thread.WhenStartPlaneThread;

import ingenias.jade.exception.*;



public  class WhenStartPlaneAppImp extends WhenStartPlaneApp{

 public WhenStartPlaneAppImp(){
  super();
 }


 public void startApp(Date oDepartureTime){
		thread.WhenStartPlaneThread oWhenStartPlaneThread = 
			new thread.WhenStartPlaneThread(
					(ingenias.jade.agents.PilotJADEAgent)this.getOwner(), oDepartureTime);
		Thread thread = new Thread(oWhenStartPlaneThread);
		thread.start();

} 
 
}

 