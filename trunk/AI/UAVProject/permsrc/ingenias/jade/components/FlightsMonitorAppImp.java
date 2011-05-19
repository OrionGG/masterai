

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

import thread.FlightsMonitorThread;

import ingenias.jade.exception.*;



public  class FlightsMonitorAppImp extends FlightsMonitorApp{

	public FlightsMonitorAppImp(){
		super();
	}



	public void start(){
		thread.FlightsMonitorThread oFlightsMonitorThread = 
			new thread.FlightsMonitorThread(this);
		Thread thread = new Thread(oFlightsMonitorThread);
		thread.start();
		
	} 

}

