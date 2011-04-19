

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

import gov.nasa.worldwind.geom.Position;
import ingenias.jade.exception.*;
import ingenias.jade.mental.Flight_Plan;



public  class Plan_CreatorAppImp extends Plan_CreatorApp{

 public Plan_CreatorAppImp(){
  super();
 }


 public Flight_Plan getInitialPlan(){
	 Flight_Plan oFlight_Plan = new Flight_Plan();
	 oFlight_Plan.setFuelCalculation(10);
	 List<Position> oWayPoints = Arrays.asList(new Position[]{Position.fromDegrees(40.5857, -3.6765, 2e4),Position.fromDegrees(40.5123, -2.0643, 2e4),Position.fromDegrees(43.1761, -2.1791, 2e4)});
	 oWayPoints.add(Position.fromDegrees(40.4482, -3.5388, 2e4));
	 oWayPoints.add(Position.fromDegrees(40.5857, -3.6765, 2e4));
	 oWayPoints.add(Position.fromDegrees(40.5123, -2.0643, 2e4));
	 oWayPoints.add(Position.fromDegrees(43.1761, -2.1791, 2e4));
	 oWayPoints.add(Position.fromDegrees(41.8728, -8.9267, 2e4));
		
	 ArrayList<Position> oAlternateAirports = new ArrayList();
	 oAlternateAirports.add(Position.fromDegrees(39.5494, 2.3959, 2e4));
	 oFlight_Plan.setAlternateAirports(oAlternateAirports);
	 
return null;
} 
 
}

 