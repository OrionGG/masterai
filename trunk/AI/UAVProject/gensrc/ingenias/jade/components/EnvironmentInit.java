

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

public  class EnvironmentInit {


private static java.lang.String semaphore="Environment";
 


 private static Vector<EnvironmentAppImp> appsinitialised=new Vector<EnvironmentAppImp>();
 


 public static void initialize(EnvironmentAppImp app){
  		/*Configuration.setValue(AVKey.GLOBE_CLASS_NAME, EarthFlat.class.getName());
		//Configuration.setValue(AVKey.VIEW_CLASS_NAME, FlatOrbitView.class.getName());
		EnvironmentAppImp.start("World Wind Paths", EnvironmentAppImp.AppFrame.class);*/
 }

 public static void shutdown(EnvironmentAppImp app){
  
 }

public static void shutdown(){
	synchronized (semaphore) {


  for (int k=0;k<appsinitialised.size();k++){
   shutdown(appsinitialised.elementAt(k));
  }

  }
}



 public static Vector<EnvironmentAppImp>  getAppsInitialised(){
		return appsinitialised;
 }
  public static EnvironmentApp createInstance(){
  	synchronized (semaphore) {
	EnvironmentAppImp app=new EnvironmentAppImp();
    initialize(app);
	appsinitialised.add(app);
	
   return app;
   }
  }
  public static EnvironmentApp createInstance(JADEAgent owner){
  	synchronized (semaphore) {
	EnvironmentAppImp app=new EnvironmentAppImp();
	app.registerOwner(owner);
    initialize(app);
	appsinitialised.add(app);
	
   return app;
   }
  }


}

 