

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

public  class Leg_CheckerInit {


private static java.lang.String semaphore="Leg_Checker";
 


 private static Vector<Leg_CheckerAppImp> appsinitialised=new Vector<Leg_CheckerAppImp>();
 


 public static void initialize(Leg_CheckerAppImp app){
  
 }

 public static void shutdown(Leg_CheckerAppImp app){
  
 }

public static void shutdown(){
	synchronized (semaphore) {


  for (int k=0;k<appsinitialised.size();k++){
   shutdown(appsinitialised.elementAt(k));
  }

  }
}



 public static Vector<Leg_CheckerAppImp>  getAppsInitialised(){
		return appsinitialised;
 }
  public static Leg_CheckerApp createInstance(){
  	synchronized (semaphore) {
	Leg_CheckerAppImp app=new Leg_CheckerAppImp();
    initialize(app);
	appsinitialised.add(app);
	
   return app;
   }
  }
  public static Leg_CheckerApp createInstance(JADEAgent owner){
  	synchronized (semaphore) {
	Leg_CheckerAppImp app=new Leg_CheckerAppImp();
	app.registerOwner(owner);
    initialize(app);
	appsinitialised.add(app);
	
   return app;
   }
  }


}

 