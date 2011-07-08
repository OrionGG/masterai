package global;


import enums.Waypoint;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import ingenias.jade.agents.PlaneJADEAgent;
import ingenias.jade.components.ConflictAttendedCheckerAppImp;
import ingenias.jade.components.ConflictAttendedCheckerInit;
import ingenias.jade.components.Plane_Position_ServiceAppImp;
import ingenias.jade.components.Task;
import ingenias.jade.mental.ControllerMind;
import ingenias.jade.mental.Decision;
import ingenias.jade.mental.Flight_Leg;
import ingenias.jade.mental.Flight_Plan;
import ingenias.jade.mental.Manoeuvre;
import ingenias.jade.mental.Pilot_Mind;
import ingenias.jade.mental.PlanAnswer;
import ingenias.jade.mental.Plane_Mind;
import ingenias.jade.mental.PlanesInConflict;
import ingenias.jade.mental.Throw_Instruction;

import jade.core.AID;
import jade.domain.FIPAAgentManagement.DFAgentDescription;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.locks.*;

import javax.xml.parsers.*;

import org.w3c.dom.*;

import controllers.EntitiesController;

public class GlobalVarsAndMethods {
	public static int nDegressPerSecond = 3;
	public static double dMaxAltitudeMps = 40;
	public static double dMaxAccelerationKMHps = 6.53;

	public static double dCruiseSpeedKMH = 930;
	public static double dCruiseAltitudeKM = 11;
	

	private static Lock lock = new ReentrantLock();;

	public static Hashtable<jade.core.AID, ingenias.jade.AgentExternalDescription> PlaneIdToPilotId =
		new Hashtable<jade.core.AID, ingenias.jade.AgentExternalDescription>();

	public static double dSecondsFactor = ((double)(Simulation.SimulationVars.iSleepTime * Simulation.SimulationVars.x))/1000;

	public static double dAwarenessDistance = 10.8;//6 miles
	

	//public static int iMaxNumWaypoints = 0; 
	//It is configured in the confli xml file
	public static File  fXmlPlanConfigFile = new File(".\\config\\flightplans\\flightplans.xml");
	public static File fXmlPilotConfigFile = new File(".\\config\\pilotminds\\pilotminds.xml");
	public static int iPilotMind = 0;

	public static Hashtable<jade.core.AID, Plane_Position_ServiceAppImp> PlanesPositionApps =
		new Hashtable<jade.core.AID, Plane_Position_ServiceAppImp>();

		private static Hashtable<String, Integer> PlanesInRisk =
			new Hashtable<String, Integer>();

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
			/*int iWaypointsNumber = global.GlobalVarsAndMethods.iMaxNumWaypoints;
			//int randomIndex = generator.nextInt(iWaypointsNumber + 1);
			List<gov.nasa.worldwind.geom.Position> oWayPoints = new ArrayList<gov.nasa.worldwind.geom.Position>(iWaypointsNumber);

			List<Integer> lIndexUsed = new ArrayList<Integer>();

			for (int i = 0; i < iWaypointsNumber; i++) {
				int randomWaypoint = generator.nextInt(oWaypointvalues.length);

				int j =0;
				while(((j<oWaypointvalues.length) &&
						BasicFlightDynamics.BFD.moreThanDobleDistance(oFlightPlan, i,  oWaypointvalues[randomWaypoint], oWayPoints)) ||
						lIndexUsed.contains(randomWaypoint)){
					randomWaypoint = generator.nextInt(oWaypointvalues.length);
					j++;
				}

				if(j == oWaypointvalues.length){
					break;
				}
				
				lIndexUsed.add(randomWaypoint);
				enums.Waypoint oWaypoint = oWaypointvalues[randomWaypoint];
				oWayPoints.add(oWaypoint.getoPosition());
			}
			oFlightPlan.setWaypoints(oWayPoints);*/

			oFlightPlan.setLegsNumber(oFlightPlan.getWaypoints().size() + 1);
			//Standard for a Boeing 737
			oFlightPlan.setCruisingAltitudeKM(dCruiseAltitudeKM);
			oFlightPlan.setCruisingSpeedKMH(dCruiseSpeedKMH);
			java.util.Date oDate = new java.util.Date();

			double num = Math.random()*2;
			long iPart;
			double fPart;

			iPart = (long) num;
			fPart = num - iPart;

			oDate.setMinutes((int) (oDate.getMinutes()+ iPart));
			oDate.setSeconds((int) (fPart * 60));
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

		public static void sleepRandom(long iSleepTime) {
			try {
				Thread.sleep((long)(iSleepTime * Math.random()));
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
			outputsdefaultFlight_Leg.setIsFromControllerOrder(oFlightLeg.getIsFromControllerOrder());


		}

		public static void copyFlightPlan(Flight_Plan eiFlight_Plan,
				Flight_Plan outputsdefaultFlight_Plan) {
			outputsdefaultFlight_Plan.setCruisingAltitudeKM(eiFlight_Plan.getCruisingAltitudeKM());
			outputsdefaultFlight_Plan.setCruisingSpeedKMH(eiFlight_Plan.getCruisingSpeedKMH());

			outputsdefaultFlight_Plan.setDepartureAirport(eiFlight_Plan.getDepartureAirport());
			outputsdefaultFlight_Plan.setDestinationAirport(eiFlight_Plan.getDestinationAirport());
			outputsdefaultFlight_Plan.setWaypoints(eiFlight_Plan.getWaypoints());
			outputsdefaultFlight_Plan.setPilotID(eiFlight_Plan.getPilotID());
			outputsdefaultFlight_Plan.setPlaneID(eiFlight_Plan.getPlaneID());
			outputsdefaultFlight_Plan.setDepartureTime(eiFlight_Plan.getDepartureTime());
			outputsdefaultFlight_Plan.setLegsNumber(eiFlight_Plan.getLegsNumber());
		}


		public static boolean isAlreadyConflictProcessed(
				PlanesInConflict eiPlanesInConflict, ControllerMind oControllerMind ) {
			boolean bAlreadyProcessed = false;
			ArrayList<jade.core.AID> aPlanesInConflict = eiPlanesInConflict.getPlanesInConflict();

			Vector<ConflictAttendedCheckerAppImp> oVector = ConflictAttendedCheckerInit.getAppsInitialised();
			for (ConflictAttendedCheckerAppImp conflictAttendedCheckerAppImp : oVector) {
				if(conflictAttendedCheckerAppImp.isConflictAttended(aPlanesInConflict, oControllerMind )){
					bAlreadyProcessed = true;
					break;
				}

			}
			return bAlreadyProcessed;
		}
		public static int whereIsConflictAttended(

				ArrayList<jade.core.AID>  aPlanesInConflict,
				Hashtable<Integer, ArrayList<jade.core.AID>>  aConflictsAttended) {

			int iWhereIsConflictAttended = -1;

			for (Entry<Integer, ArrayList<jade.core.AID>> oConflict : aConflictsAttended.entrySet()) {
				ArrayList<jade.core.AID> hashConflictsList = oConflict.getValue();

				boolean bAreAllPlanesAttended = true;
				
				for (jade.core.AID newPlaneInConflict : aPlanesInConflict) {
					boolean bIsPlaneInConflict = false;
					for (jade.core.AID mindPlanesInConflict : hashConflictsList) {

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
					iWhereIsConflictAttended = oConflict.getKey();
					break;
				}
			}
			return iWhereIsConflictAttended;
		} 

		public static void putPlanesInRisk(ArrayList<jade.core.AID> value, int iRangeOfRisk){
			for (jade.core.AID aid : value) {
				PlanesInRisk.put(aid.getLocalName(), iRangeOfRisk);
			}

		}

		public static int getRangeOfRisk(jade.core.AID oAid){
			int iRangeOfRisk = 0;
			try{
				iRangeOfRisk = PlanesInRisk.get(oAid.getLocalName());
			}
			catch(Exception ex){
				iRangeOfRisk = 0;
			}
			return iRangeOfRisk;

		}

		public static ArrayList<jade.core.AID> getPlanesFreeOfConflict(ArrayList<jade.core.AID> aPlanesAIDs){
			ArrayList<jade.core.AID> aPlanesFreeOfConflict = new ArrayList<AID>(); 
			ArrayList<jade.core.AID> aPlanesStillInConflict = new ArrayList<AID>();


			for (int i = 0; i < aPlanesAIDs.size(); i++) {
				boolean bFinish = true;
				jade.core.AID oPlaneAIDi = aPlanesAIDs.get(i);
				if(aPlanesStillInConflict.contains(oPlaneAIDi)){
					break;
				}

				Position oPositioni = null;
				double dSpeedi = 0;

				try{
					Plane_Position_ServiceAppImp plane_Position_ServiceAppImpi = 
						global.GlobalVarsAndMethods.PlanesPositionApps.get(oPlaneAIDi);
					oPositioni = plane_Position_ServiceAppImpi.getCurrentPosition();
					dSpeedi = plane_Position_ServiceAppImpi.getCurrentSpeed();
				}
				catch(Exception ex){

				}

				if(dSpeedi > 0){

					for (int j = i+1; j < aPlanesAIDs.size(); j++) {
						jade.core.AID oPlaneAIDj =aPlanesAIDs.get(j);
						Plane_Position_ServiceAppImp plane_Position_ServiceAppImpj = 
							global.GlobalVarsAndMethods.PlanesPositionApps.get(oPlaneAIDj);
						Position oPositionj = plane_Position_ServiceAppImpj.getCurrentPosition();

						double dDistance = Double.MAX_VALUE;
						//double dDistanceWithHigh = Double.MAX_VALUE;
						double dDistanceHigh = Double.MAX_VALUE;
						gov.nasa.worldwind.geom.Position oPositionSecondService = null;
						gov.nasa.worldwind.geom.Angle oHeadSecondj = null;
						double dSpeedj = 0;
						try{
							oPositionj = plane_Position_ServiceAppImpj.getCurrentPosition();
							dSpeedj = plane_Position_ServiceAppImpj.getCurrentSpeed();

							dDistance = BasicFlightDynamics.BFD.getDistanceWithHigh(
									oPositioni , oPositionj);

						}
						catch(Exception ex){

						}

						if(dSpeedj <= 0){
							continue;
						}

						if(dDistance < global.GlobalVarsAndMethods.dAwarenessDistance * Simulation.SimulationVars.x){//6 miles
							aPlanesStillInConflict.add(oPlaneAIDi);
							aPlanesStillInConflict.add(oPlaneAIDj);
							bFinish = false;
							break;
						}
					}
				}

				if(bFinish){
					aPlanesFreeOfConflict.add(oPlaneAIDi);
				}
			}
			return aPlanesFreeOfConflict;
		}

		public static int isAnyPlaneInConflict(
				ArrayList<AID> lPlanesInConflict, Hashtable<Integer, ArrayList<AID>> aConflictsAttended ) {
			int iResult = -1;
			
			for (Entry<Integer, ArrayList<jade.core.AID>> oConflict : aConflictsAttended.entrySet()) {
				ArrayList<jade.core.AID> hashConflictsList = oConflict.getValue();

				boolean bSomePlaneAttended = false;

				for (jade.core.AID mindPlanesInConflict : hashConflictsList) {
					boolean bIsPlaneInConflict = false;
					for (jade.core.AID newPlaneInConflict : lPlanesInConflict) {

						if(newPlaneInConflict.equals(mindPlanesInConflict))
						{
							bIsPlaneInConflict = true;
							break;
						}
					}

					if(bIsPlaneInConflict){
						bSomePlaneAttended = true;
						break;
					}
				}

				if(bSomePlaneAttended){
					iResult = oConflict.getKey();
					break;
				}
			}
			
			
			return iResult;
		}

		public static void AddPlanesToConflictsAttended(int iConflictNumber,
				ArrayList<AID> lPlanesInConflict,
				Hashtable<Integer, ArrayList<AID>> aConflictsAttended) {
			ArrayList<AID> lFirstPlanesInConflict = aConflictsAttended.get(iConflictNumber);
			ArrayList<AID> lLastPlanesInConflict = addNoDuplicates(lFirstPlanesInConflict, lPlanesInConflict);

	 	    aConflictsAttended.put(iConflictNumber, lLastPlanesInConflict);
			
			
		}

		private static ArrayList<AID> addNoDuplicates(
				ArrayList<AID> lFirstPlanesInConflict,
				ArrayList<AID> lPlanesInConflict) {
			
			ArrayList<AID> lLastPlanesInConflict = new ArrayList<AID>();
			lLastPlanesInConflict.addAll(lFirstPlanesInConflict);
			
			for (AID aid : lPlanesInConflict) {
				if(!lLastPlanesInConflict.contains(aid)){
					lLastPlanesInConflict.add(aid);
				}
				
			}
			
			return lLastPlanesInConflict;
		}

		public static Flight_Plan getNewPlan(int i) {

			Flight_Plan oFlightPlan = new ingenias.jade.mental.Flight_Plan();
			
			try {

				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(fXmlPlanConfigFile);
				doc.getDocumentElement().normalize();

				NodeList nList = doc.getElementsByTagName("Flight_Plan");

				Node nNode = nList.item(i);	    
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					setDepartureAirport(oFlightPlan, nNode);
					setDestinationAirport(oFlightPlan, nNode);

					setWaypoints(doc, oFlightPlan, nNode);
					
					setDepartureTime(oFlightPlan, nNode);
					
					//Standard for a Boeing 737
					oFlightPlan.setCruisingAltitudeKM(dCruiseAltitudeKM);
					oFlightPlan.setCruisingSpeedKMH(dCruiseSpeedKMH);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return oFlightPlan;
		}

		private static void setDepartureTime(Flight_Plan oFlightPlan, Node nNode) {
			String sElementName = "DepartureTime";
			String sDepartureTime =  getElementValue(nNode, sElementName);

			double num = Double.parseDouble(sDepartureTime);

			long iPart;
			double fPart;

			iPart = (long) num;
			fPart = num - iPart;

			java.util.Date oDate = new java.util.Date();
			oDate.setMinutes((int) (oDate.getMinutes()+ iPart));
			oDate.setSeconds((int) (fPart * 60));
			oFlightPlan.setDepartureTime(oDate);
			
		}

		public static void setWaypoints(Document doc, Flight_Plan oFlightPlan,
				Node nNode ) {
			//Adding Waypoints
			Element eElement = (Element) nNode;

			String sElementName = "Waypoint";
			NodeList nList = eElement.getElementsByTagName(sElementName);
			
			enums.Waypoint[] oWaypointvalues = enums.Waypoint.values();
			List<gov.nasa.worldwind.geom.Position> oWayPoints = new ArrayList<gov.nasa.worldwind.geom.Position>();

			for (int i = 0; i < nList.getLength(); i++) {
				Node nSubNode = nList.item(i);	    
				if (nSubNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eSubElement = (Element) nSubNode;
					NodeList textList = eSubElement.getChildNodes();
					String sWaypoint = ((Node)textList.item(0)).getNodeValue().trim();
					
					//set waypoint point
					int indexWaypoint =Integer.parseInt(sWaypoint);

					enums.Waypoint oWaypoint = oWaypointvalues[indexWaypoint];
					oWayPoints.add(oWaypoint.getoPosition());
					EntitiesController.getInstance().addWaypointsView(oWaypoint);
				}
			}
			
			oFlightPlan.setWaypoints(oWayPoints);
			oFlightPlan.setLegsNumber(oFlightPlan.getWaypoints().size() + 1);
		}

		public static void setDepartureAirport(Flight_Plan oFlightPlan,
				Node nNode) {
			String sElementName = "DepartureAirport";
			String sDepartureAirport =  getElementValue(nNode, sElementName);
			//set departure point
			int iIndexDeparture = Integer.parseInt(sDepartureAirport);

			enums.Airport[] oAirportvalues = enums.Airport.values();
			enums.Airport oDeparture = oAirportvalues[iIndexDeparture];
			oFlightPlan.setDepartureAirport(oDeparture);
		}
		
		public static void setDestinationAirport(Flight_Plan oFlightPlan,
				Node nNode) {
			String sElementName = "DestinationAirport";
			String sDestinationAirport =  getElementValue(nNode, sElementName);
			//set departure point
			int iIndexDestination =Integer.parseInt(sDestinationAirport);

			enums.Airport[] oAirportvalues = enums.Airport.values();
			enums.Airport oDestination = oAirportvalues[iIndexDestination];
			oFlightPlan.setDestinationAirport(oDestination);
		}

		public static String getElementValue(Node nNode, String sElementName) {
			Element eElement = (Element) nNode;
			
			NodeList oList = eElement.getElementsByTagName(sElementName);
			Element oElement = (Element)oList.item(0);

			NodeList textList = oElement.getChildNodes();
			String text = ((Node)textList.item(0)).getNodeValue().trim();
			return text;
		}

		public static List<DFAgentDescription> clearDuplicated(
				List<DFAgentDescription> lDFPilotsAgentDescription) {
			List<DFAgentDescription> lResult = new ArrayList<DFAgentDescription>();
			for (DFAgentDescription dfAgentDescription : lDFPilotsAgentDescription) {
				if(!containsAID(lResult, dfAgentDescription.getName())){
					lResult.add(dfAgentDescription);
				}
			}
			return lResult;
			
		}

		private static boolean containsAID(List<DFAgentDescription> lResult,
				AID name) {
			boolean bResult = false;
			for (DFAgentDescription dfAgentDescription : lResult) {
				if(dfAgentDescription.getName().getLocalName().equals(name.getLocalName())){
					bResult = true;
					break;
				}
			}
			return bResult;
		}
		
		public static void setStatusValues(Pilot_Mind outputsdefaultPilot_Mind) {			
			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(fXmlPilotConfigFile);
				doc.getDocumentElement().normalize();


				int i = 0;
				NodeList nList = doc.getElementsByTagName("PilotMind");
				
				lock.lock();
				try {
					i = iPilotMind;
					iPilotMind ++;
				} finally {
					lock.unlock();
				}
				
				Node nNode = nList.item(i);	    
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					setStress(outputsdefaultPilot_Mind, nNode);
					setFatigue(outputsdefaultPilot_Mind, nNode);
					setExperience(outputsdefaultPilot_Mind, nNode);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}

		private static void setExperience(Pilot_Mind outputsdefaultPilot_Mind,
				Node nNode) {
			String sElementName = "Experience";
			String sExperience =  getElementValue(nNode, sElementName);
			//set departure point
			float fExperience =Float.parseFloat(sExperience);
			outputsdefaultPilot_Mind.setExperience(fExperience);
		}

		private static void setFatigue(Pilot_Mind outputsdefaultPilot_Mind,
				Node nNode) {
			String sElementName = "Fatigue";
			String sFatigue =  getElementValue(nNode, sElementName);
			//set departure point
			float fFatigue =Float.parseFloat(sFatigue);
			outputsdefaultPilot_Mind.setFatigue(fFatigue);
			
		}

		private static void setStress(Pilot_Mind outputsdefaultPilot_Mind,
				Node nNode) {
			String sElementName = "Stress";
			String sStress =  getElementValue(nNode, sElementName);
			//set departure point
			float fStress =Float.parseFloat(sStress);
			outputsdefaultPilot_Mind.setStress(fStress);
			
		}
		


		public static gov.nasa.worldwind.geom.Position getLegEndPoint(int iLegsCompleted,
				Flight_Plan oFlightPlan) {
			gov.nasa.worldwind.geom.Position oEndPoint;
			if(oFlightPlan.getWaypoints().size() <= iLegsCompleted){
				oEndPoint = oFlightPlan.getDestinationAirport().getPosition();
			}
			else{
				oEndPoint = oFlightPlan.getWaypoints().get(iLegsCompleted);
			}
			return oEndPoint;
		}



		public static gov.nasa.worldwind.geom.Position getLegStartPoint(int iLegsCompleted,
				Flight_Plan oFlightPlan) {
			gov.nasa.worldwind.geom.Position oStartPoint;
			if(iLegsCompleted == 0 ){
				oStartPoint = oFlightPlan.getDepartureAirport().getPosition();
			}
			else{
				oStartPoint = oFlightPlan.getWaypoints().get(iLegsCompleted-1);
			}
			return oStartPoint;
		}
}
