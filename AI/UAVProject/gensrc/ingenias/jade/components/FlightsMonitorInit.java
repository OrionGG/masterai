

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
import ingenias.jade.JADEAgent;

public  class FlightsMonitorInit {


private static java.lang.String semaphore="FlightsMonitor";
 


 private static Vector<FlightsMonitorAppImp> appsinitialised=new Vector<FlightsMonitorAppImp>();
 


 public static void initialize(FlightsMonitorAppImp app){
  
 }

 public static void shutdown(FlightsMonitorAppImp app){
  
 }

public static void shutdown(){
	synchronized (semaphore) {


  for (int k=0;k<appsinitialised.size();k++){
   shutdown(appsinitialised.elementAt(k));
  }

  }
}



 public static Vector<FlightsMonitorAppImp>  getAppsInitialised(){
		return appsinitialised;
 }
  public static FlightsMonitorApp createInstance(){
  	synchronized (semaphore) {
	FlightsMonitorAppImp app=new FlightsMonitorAppImp();
    initialize(app);
	appsinitialised.add(app);
	
   return app;
   }
  }
  public static FlightsMonitorApp createInstance(JADEAgent owner){
  	synchronized (semaphore) {
	FlightsMonitorAppImp app=new FlightsMonitorAppImp();
	app.registerOwner(owner);
    initialize(app);
	appsinitialised.add(app);
	
   return app;
   }
  }


}

 