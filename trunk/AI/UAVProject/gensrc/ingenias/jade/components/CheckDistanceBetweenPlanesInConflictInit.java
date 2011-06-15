

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

public  class CheckDistanceBetweenPlanesInConflictInit {

 private static CheckDistanceBetweenPlanesInConflictAppImp instance = null;


private static java.lang.String semaphore="CheckDistanceBetweenPlanesInConflict";
 

 


 public static void initialize(CheckDistanceBetweenPlanesInConflictAppImp app){
  
 }

 public static void shutdown(CheckDistanceBetweenPlanesInConflictAppImp app){
  
 }

public static void shutdown(){
	synchronized (semaphore) {

   if (instance!=null){
	shutdown(instance);
   }


  }
}




  public static CheckDistanceBetweenPlanesInConflictApp getInstance(){
  	synchronized (semaphore) {
   if (instance==null){
	instance=new CheckDistanceBetweenPlanesInConflictAppImp();
    initialize(instance);
   }
   
   return instance;
   }
  }
    public static CheckDistanceBetweenPlanesInConflictApp getInstance(JADEAgent owner){
    	synchronized (semaphore) {
   if (instance==null){
	instance=new CheckDistanceBetweenPlanesInConflictAppImp();	
    initialize(instance);
   } 
	instance.registerMultipleOwners(owner);
	
   return instance;
   }
  }

}

 