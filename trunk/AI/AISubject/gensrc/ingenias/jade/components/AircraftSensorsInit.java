

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

public  class AircraftSensorsInit {

 private static AircraftSensorsAppImp instance = null;


private static java.lang.String semaphore="AircraftSensors";
 

 


 public static void initialize(AircraftSensorsAppImp app){
  
 }

 public static void shutdown(AircraftSensorsAppImp app){
  
 }

public static void shutdown(){
	synchronized (semaphore) {

   if (instance!=null){
	shutdown(instance);
   }


  }
}




  public static AircraftSensorsApp getInstance(){
  	synchronized (semaphore) {
   if (instance==null){
	instance=new AircraftSensorsAppImp();
    initialize(instance);
   }
   
   return instance;
   }
  }
    public static AircraftSensorsApp getInstance(JADEAgent owner){
    	synchronized (semaphore) {
   if (instance==null){
	instance=new AircraftSensorsAppImp();	
    initialize(instance);
   } 
	instance.registerMultipleOwners(owner);
	
   return instance;
   }
  }

}

 