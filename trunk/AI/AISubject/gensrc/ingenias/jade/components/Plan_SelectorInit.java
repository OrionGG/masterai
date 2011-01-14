

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

public  class Plan_SelectorInit {

 private static Plan_SelectorAppImp instance = null;


private static java.lang.String semaphore="Plan_Selector";
 

 


 public static void initialize(Plan_SelectorAppImp app){
  
 }

 public static void shutdown(Plan_SelectorAppImp app){
  
 }

public static void shutdown(){
	synchronized (semaphore) {

   if (instance!=null){
	shutdown(instance);
   }


  }
}




  public static Plan_SelectorApp getInstance(){
  	synchronized (semaphore) {
   if (instance==null){
	instance=new Plan_SelectorAppImp();
    initialize(instance);
   }
   
   return instance;
   }
  }
    public static Plan_SelectorApp getInstance(JADEAgent owner){
    	synchronized (semaphore) {
   if (instance==null){
	instance=new Plan_SelectorAppImp();	
    initialize(instance);
   } 
	instance.registerMultipleOwners(owner);
	
   return instance;
   }
  }

}

 