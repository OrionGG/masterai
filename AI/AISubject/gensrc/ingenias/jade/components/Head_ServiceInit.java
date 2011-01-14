

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

public  class Head_ServiceInit {

 private static Head_ServiceAppImp instance = null;


private static java.lang.String semaphore="Head_Service";
 

 


 public static void initialize(Head_ServiceAppImp app){
  
 }

 public static void shutdown(Head_ServiceAppImp app){
  
 }

public static void shutdown(){
	synchronized (semaphore) {

   if (instance!=null){
	shutdown(instance);
   }


  }
}




  public static Head_ServiceApp getInstance(){
  	synchronized (semaphore) {
   if (instance==null){
	instance=new Head_ServiceAppImp();
    initialize(instance);
   }
   
   return instance;
   }
  }
    public static Head_ServiceApp getInstance(JADEAgent owner){
    	synchronized (semaphore) {
   if (instance==null){
	instance=new Head_ServiceAppImp();	
    initialize(instance);
   } 
	instance.registerMultipleOwners(owner);
	
   return instance;
   }
  }

}

 