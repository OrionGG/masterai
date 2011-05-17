

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

 private static FlightsMonitorAppImp instance = null;


private static java.lang.String semaphore="FlightsMonitor";
 

 


 public static void initialize(FlightsMonitorAppImp app){
  
 }

 public static void shutdown(FlightsMonitorAppImp app){
  
 }

public static void shutdown(){
	synchronized (semaphore) {

   if (instance!=null){
	shutdown(instance);
   }


  }
}




  public static FlightsMonitorApp getInstance(){
  	synchronized (semaphore) {
   if (instance==null){
	instance=new FlightsMonitorAppImp();
    initialize(instance);
   }
   
   return instance;
   }
  }
    public static FlightsMonitorApp getInstance(JADEAgent owner){
    	synchronized (semaphore) {
   if (instance==null){
	instance=new FlightsMonitorAppImp();	
    initialize(instance);
   } 
	instance.registerMultipleOwners(owner);
	
   return instance;
   }
  }

}

 