package BasicFlightDynamics;

import ingenias.jade.mental.Flight_Plan;
import ingenias.jade.mental.Manoeuvre;
import ingenias.jade.mental.Plane_Mind;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


import gov.nasa.worldwind.geom.*;

public class BFD {

	private static Thread oThread;
	private static  int R = 6371; // km earth’s radius	

	public static Position getNextPos(double lat1, double lon1, double brng, double speed, long lMiliseconds){

		//long lMiliseconds = getTimeExpended(oLastUpdateDate);


		double d =  speed * ((double)lMiliseconds/(60 * 60 * 1000));
		double lat2 = Math.asin( Math.sin(lat1)*Math.cos(d/R) + 
				Math.cos(lat1)*Math.sin(d/R)*Math.cos(brng) );
		double lon2 = lon1 + Math.atan2(Math.sin(brng)*Math.sin(d/R)*Math.cos(lat1), 
				Math.cos(d/R)-Math.sin(lat1)*Math.sin(lat2));

		gov.nasa.worldwind.geom.Angle oAngleLat2 = gov.nasa.worldwind.geom.Angle.fromRadians(lat2);
		gov.nasa.worldwind.geom.Angle oAngleLon2 = gov.nasa.worldwind.geom.Angle.fromRadians(lon2);


		return new Position(oAngleLat2, oAngleLon2, 0);


	}

	public static long getTimeExpended(Date oLastUpdateDate) {
		long lMiliseconds =(new Date().getTime()) - oLastUpdateDate.getTime();
		return lMiliseconds * Simulation.SimulationVars.x;
	}

	public static Angle getHead(double lat1, double lon1,double lat2, double lon2){
		double dLat = lat2-lat1;
		double dLon = lon2-lon1; 
		double y = Math.sin(dLon) * Math.cos(lat2);
		double x = Math.cos(lat1)*Math.sin(lat2) -
		Math.sin(lat1)*Math.cos(lat2)*Math.cos(dLon);
		double brng = Math.atan2(y, x);
		gov.nasa.worldwind.geom.Angle oAngle1 = gov.nasa.worldwind.geom.Angle.fromRadians(brng);
		return oAngle1;

		/*double dLat = lat2-lat1;
    	double dLon = lon2-lon1; 
    	double y = Math.sin(dLon) * Math.cos(lat2);
    	double x = Math.cos(lat1)*Math.sin(lat2) -
    	        Math.sin(lat1)*Math.cos(lat2)*Math.cos(dLon);
    	double brng = Math.atan2(y, x);
    	gov.nasa.worldwind.geom.Angle oAngle1 = gov.nasa.worldwind.geom.Angle.fromRadians(brng);
		 */


		/*
    	Other way of calc head
    	gov.nasa.worldwind.geom.Angle oCol1 = gov.nasa.worldwind.geom.Angle.fromDegrees(90).subtract(oPosition.getLatitude());
    	gov.nasa.worldwind.geom.Angle oCol2 = gov.nasa.worldwind.geom.Angle.fromDegrees(90).subtract(oEndPoint.getLatitude());

    	gov.nasa.worldwind.geom.Angle oDieAng = oPosition.getLongitude().subtract(oEndPoint.getLongitude());

    	double oMaxCirArc = Math.cos(oCol1.radians)*Math.cos(oCol2.radians)+
    						Math.sin(oCol1.radians)*Math.sin(oCol2.radians)*Math.cos(oDieAng.radians);
    	double app = Math.acos(oMaxCirArc);

    	double brng2 = Math.asin((Math.sin(oDieAng.radians)*Math.sin(oCol2.radians))/Math.sin(app));
    	gov.nasa.worldwind.geom.Angle oAngle2 = gov.nasa.worldwind.geom.Angle.fromRadians(brng2);  	
		 */

	}

	public static double getDistance(Position oStartPosition, Position bEndPosition){
		double lat1 = oStartPosition.getLatitude().radians;
		double lon1 = oStartPosition.getLongitude().radians;
		double lat2 = bEndPosition.getLatitude().radians;
		double lon2 = bEndPosition.getLongitude().radians;

		double dLat = (lat2-lat1);
		double dLon = (lon2-lon1); 


		double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
		Math.cos(lat1) * Math.cos(lat2) * 
		Math.sin(dLon/2) * Math.sin(dLon/2); 
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
		double d = R * c;
		return d;

	}
	


	public static boolean moreThanDobleDistance(Flight_Plan oFlightPlan, int i,
			enums.Waypoint oWaypoint, List<gov.nasa.worldwind.geom.Position> oWayPoints) {
		boolean bResult = true;
		Position oStartPosition;
		
		if(i == 0){
			oStartPosition = oFlightPlan.getDeparturePoint();
		}
		else{
			oStartPosition = oWayPoints.get(i-1);
		}
		Position oEndPosition = oWaypoint.getoPosition();
		
		double dMaxDistance = getDistance(oStartPosition, oFlightPlan.getDestinationPoint());
		
		double dFirstLegDistance = getDistance(oStartPosition, oEndPosition);
		double dSecondLegDistance = getDistance(oEndPosition, oFlightPlan.getDestinationPoint());

		bResult = ((dFirstLegDistance + dSecondLegDistance) > (1.5)*dMaxDistance);
		
		return bResult;
	}
	
	
	

	public static Angle TurnHead(Plane_Mind  eiPlane_Mind,
			Manoeuvre  eiManoeuvre,
			long lMiliseconds) {
		gov.nasa.worldwind.geom.Angle oInitialAngle = eiPlane_Mind.getHead();
		gov.nasa.worldwind.geom.Angle oFinalAngle = eiManoeuvre.getHeadChange();
		gov.nasa.worldwind.geom.Angle oTotalAngleToTurn= oFinalAngle.subtract(oInitialAngle);

		if(Math.abs(oTotalAngleToTurn.degrees) > 180){
			double dTotalDegreesToTurn = (360 - Math.abs(oTotalAngleToTurn.degrees))*(oTotalAngleToTurn.degrees/Math.abs(oTotalAngleToTurn.degrees))*(-1);
			oTotalAngleToTurn = gov.nasa.worldwind.geom.Angle.fromDegrees(dTotalDegreesToTurn);
		}


		//long lMiliseconds = getTimeExpended(oLastUpdateHead);
		//degrees per milisecond (3 degrees per second)
		double dDPS = ((double)3/1000)*lMiliseconds;
		if(Math.abs(oTotalAngleToTurn.degrees)< dDPS){
			dDPS = Math.abs(oTotalAngleToTurn.degrees);
		}

		if(oTotalAngleToTurn.degrees < 0){
			//left turn
			gov.nasa.worldwind.geom.Angle oResultAngle = oInitialAngle.subtractDegrees(dDPS);
			if(oResultAngle.degrees < 0){
				oResultAngle =  gov.nasa.worldwind.geom.Angle.fromDegrees(
						oResultAngle.degrees + 360);
			}
			return oResultAngle;
		}
		else{
			//right turn
			gov.nasa.worldwind.geom.Angle oResultAngle = oInitialAngle.addDegrees(dDPS);
			if(oResultAngle.degrees > 360){
				oResultAngle =  gov.nasa.worldwind.geom.Angle.fromDegrees(
						oResultAngle.degrees - 360);
			}
			return oResultAngle;
		}

	}






}
