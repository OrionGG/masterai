

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

public  class Plane_Position_ServiceInit {


private static java.lang.String semaphore="Plane_Position_Service";
 


 private static Vector<Plane_Position_ServiceAppImp> appsinitialised=new Vector<Plane_Position_ServiceAppImp>();
 


 public static void initialize(Plane_Position_ServiceAppImp app){
  
 }

 public static void shutdown(Plane_Position_ServiceAppImp app){
  
 }

public static void shutdown(){
	synchronized (semaphore) {


  for (int k=0;k<appsinitialised.size();k++){
   shutdown(appsinitialised.elementAt(k));
  }

  }
}



 public static Vector<Plane_Position_ServiceAppImp>  getAppsInitialised(){
		return appsinitialised;
 }
  public static Plane_Position_ServiceApp createInstance(){
  	synchronized (semaphore) {
	Plane_Position_ServiceAppImp app=new Plane_Position_ServiceAppImp();
    initialize(app);
	appsinitialised.add(app);
	
   return app;
   }
  }
  public static Plane_Position_ServiceApp createInstance(JADEAgent owner){
  	synchronized (semaphore) {
	Plane_Position_ServiceAppImp app=new Plane_Position_ServiceAppImp();
	app.registerOwner(owner);
    initialize(app);
	appsinitialised.add(app);
	
   return app;
   }
  }


}

 