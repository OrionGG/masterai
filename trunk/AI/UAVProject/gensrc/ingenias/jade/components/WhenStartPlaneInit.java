

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

public  class WhenStartPlaneInit {


private static java.lang.String semaphore="WhenStartPlane";
 


 private static Vector<WhenStartPlaneAppImp> appsinitialised=new Vector<WhenStartPlaneAppImp>();
 


 public static void initialize(WhenStartPlaneAppImp app){
  
 }

 public static void shutdown(WhenStartPlaneAppImp app){
  
 }

public static void shutdown(){
	synchronized (semaphore) {


  for (int k=0;k<appsinitialised.size();k++){
   shutdown(appsinitialised.elementAt(k));
  }

  }
}



 public static Vector<WhenStartPlaneAppImp>  getAppsInitialised(){
		return appsinitialised;
 }
  public static WhenStartPlaneApp createInstance(){
  	synchronized (semaphore) {
	WhenStartPlaneAppImp app=new WhenStartPlaneAppImp();
    initialize(app);
	appsinitialised.add(app);
	
   return app;
   }
  }
  public static WhenStartPlaneApp createInstance(JADEAgent owner){
  	synchronized (semaphore) {
	WhenStartPlaneAppImp app=new WhenStartPlaneAppImp();
	app.registerOwner(owner);
    initialize(app);
	appsinitialised.add(app);
	
   return app;
   }
  }


}

 