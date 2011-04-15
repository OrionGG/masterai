

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

public  class RadarInit {


private static java.lang.String semaphore="Radar";
 


 private static Vector<RadarAppImp> appsinitialised=new Vector<RadarAppImp>();
 


 public static void initialize(RadarAppImp app){
  		final RadarAppImp application=app;
		ingenias.jade.mental.Plane_Too_Close event=new ingenias.jade.mental.Plane_Too_Close();
		try {
			application.getOwner().getMSM().addMentalEntity(event);
		} catch (ingenias.exception.InvalidEntity e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 }

 public static void shutdown(RadarAppImp app){
  
 }

public static void shutdown(){
	synchronized (semaphore) {


  for (int k=0;k<appsinitialised.size();k++){
   shutdown(appsinitialised.elementAt(k));
  }

  }
}



 public static Vector<RadarAppImp>  getAppsInitialised(){
		return appsinitialised;
 }
  public static RadarApp createInstance(){
  	synchronized (semaphore) {
	RadarAppImp app=new RadarAppImp();
    initialize(app);
	appsinitialised.add(app);
	
   return app;
   }
  }
  public static RadarApp createInstance(JADEAgent owner){
  	synchronized (semaphore) {
	RadarAppImp app=new RadarAppImp();
	app.registerOwner(owner);
    initialize(app);
	appsinitialised.add(app);
	
   return app;
   }
  }


}

 