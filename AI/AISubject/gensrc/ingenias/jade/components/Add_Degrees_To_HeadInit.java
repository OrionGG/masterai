

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

public  class Add_Degrees_To_HeadInit {

 private static Add_Degrees_To_HeadAppImp instance = null;


private static java.lang.String semaphore="Add_Degrees_To_Head";
 

 


 public static void initialize(Add_Degrees_To_HeadAppImp app){
  
 }

 public static void shutdown(Add_Degrees_To_HeadAppImp app){
  
 }

public static void shutdown(){
	synchronized (semaphore) {

   if (instance!=null){
	shutdown(instance);
   }


  }
}




  public static Add_Degrees_To_HeadApp getInstance(){
  	synchronized (semaphore) {
   if (instance==null){
	instance=new Add_Degrees_To_HeadAppImp();
    initialize(instance);
   }
   
   return instance;
   }
  }
    public static Add_Degrees_To_HeadApp getInstance(JADEAgent owner){
    	synchronized (semaphore) {
   if (instance==null){
	instance=new Add_Degrees_To_HeadAppImp();	
    initialize(instance);
   } 
	instance.registerMultipleOwners(owner);
	
   return instance;
   }
  }

}

 