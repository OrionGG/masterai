package global;


import gov.nasa.worldwind.geom.Angle;
import ingenias.jade.agents.PlaneJADEAgent;
import ingenias.jade.mental.Decision;
import ingenias.jade.mental.Flight_Plan;
import ingenias.jade.mental.Manoeuvre;
import ingenias.jade.mental.Plane_Mind;
import ingenias.jade.mental.Throw_Instruction;

import jade.domain.FIPAAgentManagement.DFAgentDescription;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

public class GlobalVarsAndMethods {
	public static int nDegress = 3;
	public static int iMaxNumWaypoints = 2;
	
	//public static Hashtable<PlaneJADEAgent, Thread> hUpdatePlanesThreads = new Hashtable<PlaneJADEAgent, Thread>();
	
	/*public static boolean addManoeuvre(Manoeuvre eiManoeuvre,
			Plane_Mind eiPlane_Mind) {
		boolean bResult = false;
		if(eiPlane_Mind.getRunningManoeuvres().size()!=0){
			if(!containsSimilarManoeuvres(eiPlane_Mind.getRunningManoeuvres(), eiManoeuvre)){
				Manoeuvre  oRunningManoeuvre = eiPlane_Mind.getRunningManoeuvres().get(0);
				if (oRunningManoeuvre.getPriority() == eiManoeuvre.getPriority()){
					eiPlane_Mind.getRunningManoeuvres().add(eiManoeuvre);
					bResult= true;
				}
				if (oRunningManoeuvre.getPriority() < eiManoeuvre.getPriority()){
					eiPlane_Mind.getRunningManoeuvres().clear();
					eiPlane_Mind.getRunningManoeuvres().add(eiManoeuvre);
					bResult= true;
				}
			}
		}
		else{
			eiPlane_Mind.getRunningManoeuvres().add(eiManoeuvre);
			bResult= true;
		}
		return bResult;
	}*/
	
	private static boolean containsSimilarManoeuvres(ArrayList<Manoeuvre> arrayList,
			Manoeuvre eiManoeuvre) {
		boolean bResult = false;

		for (Manoeuvre oRunningManoeuvre : arrayList) {
			double dAltitudeChangeRunning = oRunningManoeuvre.getAltitudeChange();
			double dAltitudeChange = eiManoeuvre.getAltitudeChange();
			if((dAltitudeChangeRunning!=0)&& (dAltitudeChange!=0)){
				bResult = true;
				break;
			}

			double dSpeedChangeRunning = oRunningManoeuvre.getSpeedChange();
			double dSpeedChange = eiManoeuvre.getSpeedChange();
			if((dSpeedChangeRunning!=0)&& (dSpeedChange!=0)){
				bResult = true;
				break;
			}

			Angle oHeadChangeRunning = oRunningManoeuvre.getHeadChange();
			Angle oHeadChange = eiManoeuvre.getHeadChange();
			if((oHeadChangeRunning!=null) && (oHeadChange!=null)){
				bResult = true;
				break;
			}
		}

		return bResult;
	}
	
	public static boolean containsSimilarInstrucctions(Throw_Instruction oThrow_InstructionRunning,
			Decision  eiDecision) {
		boolean bResult = false;
		
		//TODO check for instrucctions with same signum
			double dAltitudeChangeRunning = oThrow_InstructionRunning.getAltitudeChange();
			double dAltitudeChange = eiDecision.getAltitudeChange();
			if((dAltitudeChangeRunning!=0)&& (dAltitudeChange!=0)){
				bResult = true;
				return bResult;
			}

			double dSpeedChangeRunning = oThrow_InstructionRunning.getSpeedChange();
			double dSpeedChange = eiDecision.getSpeedChange();
			if((dSpeedChangeRunning!=0)&& (dSpeedChange!=0)){
				bResult = true;
				return bResult;
			}

			Angle oHeadChangeRunning = oThrow_InstructionRunning.getHeadChange();
			Angle oHeadChange = eiDecision.getHeadChange();
			if((oHeadChangeRunning!=null) && (oHeadChange!=null)){
				bResult = true;
				return bResult;
			}

		return bResult;
	}
	
	public static Flight_Plan CreateNewPlan() {
		Flight_Plan oFlightPlan = new ingenias.jade.mental.Flight_Plan();
		Random generator = new Random();
		
		enums.Airport[] oAirportvalues = enums.Airport.values();
		//set departure point
		int iIndexDeparture = generator.nextInt(oAirportvalues.length);
		
		enums.Airport oDeparture = oAirportvalues[iIndexDeparture];
		oFlightPlan.setDepartureAirport(oDeparture);
		
		
		//set departure point
		int iIndexDestination = generator.nextInt(oAirportvalues.length);
		
		while(iIndexDeparture == iIndexDestination){
			iIndexDestination = generator.nextInt(oAirportvalues.length);
		}

		enums.Airport oDestination = oAirportvalues[iIndexDestination];
		oFlightPlan.setDestinationAirport(oDestination);
		
			
		//Adding Waypoints
		enums.Waypoint[] oWaypointvalues = enums.Waypoint.values();
		int iWaypointsNumber = global.GlobalVarsAndMethods.iMaxNumWaypoints;
		int randomIndex = generator.nextInt(iWaypointsNumber + 1);
		List<gov.nasa.worldwind.geom.Position> oWayPoints = new ArrayList<gov.nasa.worldwind.geom.Position>(randomIndex);

		List<Integer> lIndexUsed = new ArrayList<Integer>();
		
		for (int i = 0; i < randomIndex; i++) {
			int randomWaypoint = generator.nextInt(oWaypointvalues.length);
			
			int j =0;
			while((j<iWaypointsNumber) &&
					BasicFlightDynamics.BFD.moreThanDobleDistance(oFlightPlan, i,  oWaypointvalues[randomWaypoint], oWayPoints)){
				randomWaypoint = generator.nextInt(oWaypointvalues.length);
				j++;
			}
			
			if(j == iWaypointsNumber){
				break;
			}
			
			while(lIndexUsed.contains(randomWaypoint)){
				randomWaypoint = generator.nextInt(oWaypointvalues.length);
			}
			lIndexUsed.add(randomWaypoint);
			enums.Waypoint oWaypoint = oWaypointvalues[randomWaypoint];
			oWayPoints.add(oWaypoint.getoPosition());
		}
		oFlightPlan.setWaypoints(oWayPoints);
		
		//Standard for a Boeing 737
		oFlightPlan.setCruisingAltitudeKM(10);
		oFlightPlan.setCruisingSpeedKMH(906);
		return oFlightPlan;
	}

	public static List<DFAgentDescription> deleteRepeated(
			List<DFAgentDescription> lDFPilotsAgentDescription) {
		List<DFAgentDescription> lResult = new ArrayList<DFAgentDescription>();
		Hashtable<String, Boolean> lResultIDs = new Hashtable<String, Boolean>();
		for (DFAgentDescription dfAgentDescription : lDFPilotsAgentDescription) {
			if(!lResultIDs.containsKey(dfAgentDescription.getName().getLocalName())){
				lResult.add(dfAgentDescription);
				lResultIDs.put(dfAgentDescription.getName().getLocalName(), true);
			}
		}

		return lResult;
	}

}
