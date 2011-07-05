package controllers;

import jade.core.AID;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Hashtable;


import views.AirportView;
import views.PlaneView;
import views.WayPointView;

import enums.Airport;
import enums.Waypoint;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.examples.LayerPanel;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;

public class EntitiesController {

	private List<AirportView> lAirports;
	private List<WayPointView> lWayPoints;
	private Hashtable<AID, PlaneView> hPlaneView;
	private RenderableLayer layer;
	private WorldWindowGLCanvas wwd;
	
	private static EntitiesController instance;

	public EntitiesController() {
		super();
		
		this.lAirports = new ArrayList<AirportView>();
		for (Airport oAirport : Airport.values()) {
			AirportView oAirportsView = new AirportView(oAirport);
			this.lAirports.add(oAirportsView);
			
		}
		this.lWayPoints = new ArrayList<WayPointView>();
		//Waypoint.values();
		for (Waypoint oWaypoint : global.GlobalVarsAndMethods.oWayPointsToShow) {
			WayPointView oWayPointView = new WayPointView(oWaypoint);
			this.lWayPoints.add(oWayPointView);
		}
		
		hPlaneView = new Hashtable<AID, PlaneView>();
	}


	public void render() {
		for (Entry<AID, PlaneView> 	oEntryAidPlaneView : hPlaneView.entrySet()) {
			oEntryAidPlaneView.getValue().render(layer);
		}
		
		for (AirportView oAirportsView : lAirports) {
			oAirportsView.render(layer);
		}
		
		for (WayPointView oWayPointViewEntry : lWayPoints) {
			oWayPointViewEntry.render(layer);
		}
		
	}


	private void setNewPosition(AID aid, Position oPosition) {
		PlaneView oPlaneView = hPlaneView.get(aid);
		oPlaneView.setNewPosition(oPosition);
	}


	public void addPlaneView(PlaneView oPlaneView) {
		
		AID aid = oPlaneView.getoPlaneEntity().getAID();
		hPlaneView.put(aid, oPlaneView);
		
	}



	public static EntitiesController getInstance() {
		if(instance == null){
			instance = new EntitiesController();
		}
		return instance;
	}


	public void renderPlane(AID aid) {
		PlaneView oPlaneView = hPlaneView.get(aid);
		oPlaneView.render(layer);
		
	}


	public void setRenderableLayer(RenderableLayer layer) {
		this.layer = layer;
		
	}


	public void setWwd(WorldWindowGLCanvas wwd) {
		this.wwd = wwd;
		
	}


	private void setRangeOfRisk(AID aid, int rangeOfRisk) {
		PlaneView oPlaneView = hPlaneView.get(aid);
		oPlaneView.setRangeOfRisk(rangeOfRisk);
	}


	public void updatePlane(AID aid, Position oPosition, int rangeOfRisk) {

		this.setNewPosition(aid, oPosition);
		this.setRangeOfRisk(aid, global.GlobalVarsAndMethods.getRangeOfRisk(aid));

		this.wwd.repaint();
	}
	
}
