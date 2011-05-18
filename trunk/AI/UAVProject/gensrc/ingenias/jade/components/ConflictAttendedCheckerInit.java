

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

public  class ConflictAttendedCheckerInit {


private static java.lang.String semaphore="ConflictAttendedChecker";
 


 private static Vector<ConflictAttendedCheckerAppImp> appsinitialised=new Vector<ConflictAttendedCheckerAppImp>();
 


 public static void initialize(ConflictAttendedCheckerAppImp app){
  
 }

 public static void shutdown(ConflictAttendedCheckerAppImp app){
  
 }

public static void shutdown(){
	synchronized (semaphore) {


  for (int k=0;k<appsinitialised.size();k++){
   shutdown(appsinitialised.elementAt(k));
  }

  }
}



 public static Vector<ConflictAttendedCheckerAppImp>  getAppsInitialised(){
		return appsinitialised;
 }
  public static ConflictAttendedCheckerApp createInstance(){
  	synchronized (semaphore) {
	ConflictAttendedCheckerAppImp app=new ConflictAttendedCheckerAppImp();
    initialize(app);
	appsinitialised.add(app);
	
   return app;
   }
  }
  public static ConflictAttendedCheckerApp createInstance(JADEAgent owner){
  	synchronized (semaphore) {
	ConflictAttendedCheckerAppImp app=new ConflictAttendedCheckerAppImp();
	app.registerOwner(owner);
    initialize(app);
	appsinitialised.add(app);
	
   return app;
   }
  }


}

 