package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

import views.AirportView;
import views.PlaneView;
import views.WayPointView;

import enums.Airport;
import enums.Waypoint;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;

public class EntitiesController {

	private List<AirportView> lAirports;
	private List<WayPointView> lWayPoints;

	public EntitiesController() {
		super();
		
		this.lAirports = new ArrayList<AirportView>();
		for (Airport oAirport : Airport.values()) {
			AirportView oAirportsView = new AirportView(oAirport);
			this.lAirports.add(oAirportsView);
		}
		this.lWayPoints = new ArrayList<WayPointView>();
		for (Waypoint oWaypoint : Waypoint.values()) {
			WayPointView oWayPointView = new WayPointView(oWaypoint);
			this.lWayPoints.add(oWayPointView);
		}
	}


	public void render(RenderableLayer layer) {
		for (PlaneView	oPlaneView : Simulation.SimulationVars.lPlanesFlying) {
			oPlaneView.render(layer);
		}

		for (AirportView oAirportsView : lAirports) {
			oAirportsView.render(layer);
		}
		
		/*for (WayPointView oWayPointViewEntry : lWayPoints) {
			oWayPointViewEntry.render(layer);
		}*/
		
	}
	
}
