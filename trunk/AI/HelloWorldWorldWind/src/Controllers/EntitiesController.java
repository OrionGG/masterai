package Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

import views.WayPointView;

import entities.*;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;

public class EntitiesController {
	private PlaneController oPlaneController;
	private HashMap<String, WayPointView> lWayPoints;

	public EntitiesController() {
		super();
		//this.oPlaneController = new PlaneController(sPlaneName, oPosition);
		this.lWayPoints = new HashMap<String, WayPointView>();
	}
	
	public PlaneController getoPlaneController() {
		return oPlaneController;
	}

	public void setoPlaneController(PlaneController oPlaneController) {
		this.oPlaneController = oPlaneController;
	}

	public HashMap<String, WayPointView> getWayPoints() {
		return lWayPoints;
	}

	public void setWayPoints(HashMap<String, WayPointView> lWayPoints) {
		this.lWayPoints = lWayPoints;
	}

	public void addWayPoint(String sName, Position oPosition){
		WayPointEntity oWayPointEntity = new WayPointEntity(sName, oPosition);
		WayPointView oWayPointView = new WayPointView(oWayPointEntity);
		lWayPoints.put(sName, oWayPointView);
	}

	public void render(RenderableLayer layer) {
		oPlaneController.OPlaneView.render(layer);
		for (Entry<String, WayPointView> oWayPointViewEntry : lWayPoints.entrySet()) {
			oWayPointViewEntry.getValue().render(layer);
		}
		
	}
	
}
