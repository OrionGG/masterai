

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

public  class UpdatePlaneStatusInit {


private static java.lang.String semaphore="UpdatePlaneStatus";
 


 private static Vector<UpdatePlaneStatusAppImp> appsinitialised=new Vector<UpdatePlaneStatusAppImp>();
 


 public static void initialize(UpdatePlaneStatusAppImp app){
  
 }

 public static void shutdown(UpdatePlaneStatusAppImp app){
  
 }

public static void shutdown(){
	synchronized (semaphore) {


  for (int k=0;k<appsinitialised.size();k++){
   shutdown(appsinitialised.elementAt(k));
  }

  }
}



 public static Vector<UpdatePlaneStatusAppImp>  getAppsInitialised(){
		return appsinitialised;
 }
  public static UpdatePlaneStatusApp createInstance(){
  	synchronized (semaphore) {
	UpdatePlaneStatusAppImp app=new UpdatePlaneStatusAppImp();
    initialize(app);
	appsinitialised.add(app);
	
   return app;
   }
  }
  public static UpdatePlaneStatusApp createInstance(JADEAgent owner){
  	synchronized (semaphore) {
	UpdatePlaneStatusAppImp app=new UpdatePlaneStatusAppImp();
	app.registerOwner(owner);
    initialize(app);
	appsinitialised.add(app);
	
   return app;
   }
  }


}

 