package global;


import gov.nasa.worldwind.geom.Angle;
import ingenias.jade.agents.PlaneJADEAgent;
import ingenias.jade.components.ConflictAttendedCheckerAppImp;
import ingenias.jade.components.ConflictAttendedCheckerInit;
import ingenias.jade.components.Task;
import ingenias.jade.mental.Decision;
import ingenias.jade.mental.Flight_Leg;
import ingenias.jade.mental.Flight_Plan;
import ingenias.jade.mental.Manoeuvre;
import ingenias.jade.mental.PlanAnswer;
import ingenias.jade.mental.Plane_Mind;
import ingenias.jade.mental.PlanesInConflict;
import ingenias.jade.mental.Throw_Instruction;

import jade.domain.FIPAAgentManagement.DFAgentDescription;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class GlobalVarsAndMethods {
	public static int nDegressPerSecond = 3;
	public static double dMaxAltitudeMps = 40;
	public static double dMaxAccelerationKMHps = 6.53;

	public static double dCruiseSpeedKMH = 930;
	public static double dCruiseAltitudeKM = 11;
	
	public static Hashtable<jade.core.AID, ingenias.jade.AgentExternalDescription> PlaneIdToPilotId =
		new Hashtable<jade.core.AID, ingenias.jade.AgentExternalDescription>();
	
	public static double dSecondsFactor = ((double)(Simulation.SimulationVars.iSleepTime * Simulation.SimulationVars.x))/1000;
	
	public static double dAwarenessDistance = 64.37376;//40 miles
	
	public static int iMaxNumWaypoints = 0;
	
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
	
	public static boolean containsSimilarInstrucctions(List<Throw_Instruction> oInstructionsRunning,
			Decision  eiDecision) {
		boolean bResult = false;
		
		for (Throw_Instruction throw_Instruction : oInstructionsRunning) {
			
		//TODO check for instrucctions with same signum
			double dAltitudeChangeRunning = throw_Instruction.getAltitudeChange();
			double dAltitudeChange = eiDecision.getAltitudeChange();
			if((dAltitudeChangeRunning!=-1)&& (dAltitudeChange!=-1)){
				bResult = true;
				return bResult;
			}

			double dSpeedChangeRunning = throw_Instruction.getSpeedChange();
			double dSpeedChange = eiDecision.getSpeedChange();
			if((dSpeedChangeRunning!=-1)&& (dSpeedChange!=-1)){
				bResult = true;
				return bResult;
			}

			Angle oHeadChangeRunning = throw_Instruction.getHeadChange();
			Angle oHeadChange = eiDecision.getHeadChange();
			if((oHeadChangeRunning!=null) && (oHeadChange!=null)){
				bResult = true;
				return bResult;
			}
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
		oFlightPlan.setCruisingAltitudeKM(dCruiseAltitudeKM);
		oFlightPlan.setCruisingSpeedKMH(dCruiseSpeedKMH);
		java.util.Date oDate = new java.util.Date();
		
		oDate.setMinutes(oDate.getMinutes()+3);
		oFlightPlan.setDepartureTime(oDate);
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

	public static int getMaxPriority(
			List<Throw_Instruction> oInstructionsRunning) {
		int iMaxPriority = 0;

		for (Throw_Instruction throw_Instruction : oInstructionsRunning) {
			if(iMaxPriority < throw_Instruction.getPriority()){
				iMaxPriority = throw_Instruction.getPriority();
			}
		}
		
		return iMaxPriority;
	}
	
	public static void CreateSpeedInstruction(
			Throw_Instruction outputsdefaultThrow_Instruction, double dSpeedChange) {
		double dKMPS = ((double)global.GlobalVarsAndMethods.dMaxAccelerationKMHps)
		* global.GlobalVarsAndMethods.dSecondsFactor;
		
		int dNSpeed = (int) (dSpeedChange/dKMPS);
		if(Math.abs(dNSpeed)> 0){
			double dSpeedToChange = dKMPS * (dNSpeed/ Math.abs(dNSpeed));
			outputsdefaultThrow_Instruction.setSpeedChange(dSpeedToChange);
			
		}
		else{
			double dRestSpeedToChange = (dSpeedChange%dKMPS);
			outputsdefaultThrow_Instruction.setSpeedChange(dRestSpeedToChange);
		
		}
	}
	
	public static void CreateAltitudeInstruction(
			Throw_Instruction outputsdefaultThrow_Instruction,
			double dAltitudeChange) {
		double dMPS = ((double)global.GlobalVarsAndMethods.dMaxAltitudeMps)
		* global.GlobalVarsAndMethods.dSecondsFactor;
		
		int dNAltitude = (int) (dAltitudeChange/dMPS);
		if(Math.abs(dNAltitude)> 0){
			double dAltitudeToChange = dMPS * (dNAltitude/ Math.abs(dNAltitude));
			outputsdefaultThrow_Instruction.setAltitudeChange(dAltitudeToChange);
			
		}
		else{
			double dRestAltitudeToChange = (dAltitudeChange%dMPS);
			outputsdefaultThrow_Instruction.setAltitudeChange(dRestAltitudeToChange);
		
		}
	}
	
	public static void CreateHeadInstruction(
			Throw_Instruction outputsdefaultThrow_Instruction,
			gov.nasa.worldwind.geom.Angle oHeadChange) {
		//degrees per milisecond (3 degrees per second)
		double dDPS = ((double)global.GlobalVarsAndMethods.nDegressPerSecond)
		* global.GlobalVarsAndMethods.dSecondsFactor;
		
		int dNDegreeAngle = (int) (oHeadChange.degrees/dDPS);
		if(Math.abs(dNDegreeAngle)> 0){
			double dAngleTurn = dDPS * (dNDegreeAngle/ Math.abs(dNDegreeAngle));
			outputsdefaultThrow_Instruction.setHeadChange(gov.nasa.worldwind.geom.Angle.fromDegrees(dAngleTurn));
			
		}
		else{
			double dRestDegreeAngle = (oHeadChange.degrees%dDPS);
			outputsdefaultThrow_Instruction.setHeadChange(gov.nasa.worldwind.geom.Angle.fromDegrees(dRestDegreeAngle));
		}
	}

	public static void sleep(int iSleepTime) {
		try {
			Thread.sleep(iSleepTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void copyDecisions(Decision oDecision,
			Decision outputsdefaultDecision) {
		outputsdefaultDecision.setAltitudeChange(oDecision.getAltitudeChange());
		outputsdefaultDecision.setHeadChange(oDecision.getHeadChange());
		outputsdefaultDecision.setSpeedChange(oDecision.getSpeedChange());
		outputsdefaultDecision.setPriority(oDecision.getPriority());
		
	}

	public static void copyFlightLeg(Flight_Leg oFlightLeg,
			Flight_Leg outputsdefaultFlight_Leg) {
		outputsdefaultFlight_Leg.setAltitudeKM(oFlightLeg.getAltitudeKM());
		outputsdefaultFlight_Leg.setStartPoint(oFlightLeg.getStartPoint());
		outputsdefaultFlight_Leg.setEndPoint(oFlightLeg.getEndPoint());
		outputsdefaultFlight_Leg.setSpeedKMH(oFlightLeg.getSpeedKMH());
		outputsdefaultFlight_Leg.setPlaneID(oFlightLeg.getPlaneID());

		 
		
	}
	
	public static void copyFlightPlan(PlanAnswer eiPlanAnswer,
			Flight_Plan outputsdefaultFlight_Plan) {
		outputsdefaultFlight_Plan.setCruisingAltitudeKM(eiPlanAnswer.getFlightPlan().getCruisingAltitudeKM());
		outputsdefaultFlight_Plan.setCruisingSpeedKMH(eiPlanAnswer.getFlightPlan().getCruisingSpeedKMH());
		
		outputsdefaultFlight_Plan.setDepartureAirport(eiPlanAnswer.getFlightPlan().getDepartureAirport());
		outputsdefaultFlight_Plan.setDestinationAirport(eiPlanAnswer.getFlightPlan().getDestinationAirport());
		outputsdefaultFlight_Plan.setWaypoints(eiPlanAnswer.getFlightPlan().getWaypoints());
		outputsdefaultFlight_Plan.setPilotID(eiPlanAnswer.getFlightPlan().getPilotID());
		outputsdefaultFlight_Plan.setPlaneID(eiPlanAnswer.getFlightPlan().getPlaneID());
		outputsdefaultFlight_Plan.setDepartureTime(eiPlanAnswer.getFlightPlan().getDepartureTime());
	}
	 

	public static boolean isAlreadyConflictProcessed(
			PlanesInConflict eiPlanesInConflict) {
		boolean bAlreadyProcessed = false;
		ArrayList<jade.core.AID> aPlanesInConflict = eiPlanesInConflict.getPlanesInConflict();
		
		Vector<ConflictAttendedCheckerAppImp> oVector = ConflictAttendedCheckerInit.getAppsInitialised();
		for (ConflictAttendedCheckerAppImp conflictAttendedCheckerAppImp : oVector) {
			if(conflictAttendedCheckerAppImp.isConflictAttended(aPlanesInConflict)){
				bAlreadyProcessed = true;
				break;
			}
			
		}
		return bAlreadyProcessed;
	}
	public static int whereIsConflictAttended(

			ArrayList<jade.core.AID> aPlanesInConflict,
			ArrayList<ArrayList<jade.core.AID>> aConflictsAttended) {
		
		int iWhereIsConflictAttended = -1;
		 
		for (int i = 0; i < aConflictsAttended.size(); i++) {
	    	ArrayList<jade.core.AID> arrayList = aConflictsAttended.get(i);
	    	
			 boolean bAreAllPlanesAttended = true;
			 
			 for (jade.core.AID newPlaneInConflict : aPlanesInConflict) {
				boolean bIsPlaneInConflict = false;	
				
				for (jade.core.AID mindPlanesInConflict : arrayList) {
					if(newPlaneInConflict.equals(mindPlanesInConflict))
					{
						bIsPlaneInConflict = true;
						break;
					}
				}
				
				if(!bIsPlaneInConflict){
					bAreAllPlanesAttended = false;
					break;
				}
				
			 }
			 
			 if(bAreAllPlanesAttended){
				 iWhereIsConflictAttended = i;
				 break;
			 }
		}
		return iWhereIsConflictAttended;
	} 
}
