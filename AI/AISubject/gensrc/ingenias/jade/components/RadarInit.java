

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

public  class RadarInit {

 private static RadarAppImp instance = null;


private static java.lang.String semaphore="Radar";
 

 


 public static void initialize(RadarAppImp app){
  
 }

 public static void shutdown(RadarAppImp app){
  
 }

public static void shutdown(){
	synchronized (semaphore) {

   if (instance!=null){
	shutdown(instance);
   }


  }
}




  public static RadarApp getInstance(){
  	synchronized (semaphore) {
   if (instance==null){
	instance=new RadarAppImp();
    initialize(instance);
   }
   
   return instance;
   }
  }
    public static RadarApp getInstance(JADEAgent owner){
    	synchronized (semaphore) {
   if (instance==null){
	instance=new RadarAppImp();	
    initialize(instance);
   } 
	instance.registerMultipleOwners(owner);
	
   return instance;
   }
  }

}

 