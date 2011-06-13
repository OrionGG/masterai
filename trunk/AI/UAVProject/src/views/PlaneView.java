package views;
import ingenias.jade.agents.PlaneJADEAgent;

import java.awt.Color;
import java.util.ArrayList;


import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Path;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.util.WWUtil;


public class PlaneView extends PointPlacemark {
	private PlaneJADEAgent oPlaneEntity;
	ArrayList<Path> lPath;
	RenderableLayer layer = null;

	public PlaneView(PlaneJADEAgent oPlaneEntityParam, String sPlaneName, Position oPosition) {
		super(oPosition);
		oPlaneEntity = oPlaneEntityParam;

		PointPlacemarkAttributes attrs2 = new PointPlacemarkAttributes();

		attrs2.setUsePointAsDefaultImage(true);
		/*int iRed = new java.util.Random().nextInt(255);
        int iGreen = new java.util.Random().nextInt(255);
        int iBlue = new java.util.Random().nextInt(255);*/

		Material m = Material.GREEN;
		attrs2.setLineMaterial(m);
		attrs2.setScale(10d);
		this.setAttributes(attrs2);

		this.setValue(AVKey.DISPLAY_NAME, sPlaneName);
		this.setAltitudeMode(WorldWind.RELATIVE_TO_GROUND);

		this.setLineEnabled(false);

		// Create and set an attribute bundle.
		Path oPath = createNewPath();

		lPath = new ArrayList<Path>();
		lPath.add(oPath);

		//renderLastPath();
	}

	public Path createNewPath() {
		ShapeAttributes attrs = new BasicShapeAttributes();
		attrs.setOutlineMaterial(Material.WHITE);
		attrs.setOutlineWidth(2d);
		ArrayList<Position> pathPositions = new ArrayList<Position>();
		Path oPath = new Path(pathPositions);
		oPath.setAttributes(attrs);
		oPath.setVisible(true);
		oPath.setAltitudeMode(WorldWind.RELATIVE_TO_GROUND);
		oPath.setPathType(AVKey.GREAT_CIRCLE);
		return oPath;
	}

	public PlaneJADEAgent getoPlaneEntity() {
		return oPlaneEntity;
	}

	public void setoPlaneEntity(PlaneJADEAgent oPlaneEntity) {
		this.oPlaneEntity = oPlaneEntity;
	}

	public void setNewPosition(Position oNewPos) {
		super.setPosition(oNewPos);
		if(lPath.size() != 0){
			Path oPath = lPath.get(lPath.size() - 1);
			ArrayList<Position> pathPositions = (ArrayList<Position>) oPath.getPositions();
			pathPositions.add(oNewPos);
			oPath.setPositions(pathPositions);
		}
	}

	public void render(RenderableLayer layer){
		if(layer != null){
			this.layer = layer;
			layer.addRenderable(this);
			if(lPath.size() != 0){
				Path oPath = lPath.get(lPath.size() - 1);
				layer.addRenderable(oPath);
			}
		}
	}

	public void setRangeOfRisk(int rangeOfRisk) {
		Material oEndPlaneMaterial = Material.YELLOW;

		Material oEndPathMaterial = Material.WHITE;

		Material oInitialPathMaterial = Material.WHITE;
		if(lPath.size() != 0){
			Path oPath = lPath.get(lPath.size() - 1);
			oInitialPathMaterial = oPath.getAttributes().getOutlineMaterial();
		}


		switch (rangeOfRisk) {
		case 0:
			oEndPlaneMaterial = Material.GREEN;

			oEndPathMaterial = Material.WHITE;
			break;
		case 9:

			oEndPlaneMaterial = Material.YELLOW;

			oEndPathMaterial = new Material(WWUtil.makeRandomColor(null));
			break;

		default:
			break;
		}

		Material oInitialPlaneMaterial = this.getAttributes().getLineMaterial();

		if(!oInitialPlaneMaterial.equals(oEndPlaneMaterial)){
			this.getAttributes().setLineMaterial(oEndPlaneMaterial);
		}


		if(oInitialPathMaterial.equals(Material.WHITE)){
			if(!oInitialPathMaterial.equals(oEndPathMaterial)){
				Path oPath2 = createNewPath();
				oPath2.getAttributes().setOutlineMaterial(oEndPathMaterial);
				lPath.add(oPath2);
				renderLastPath();
			}
		}
		else{
			if(oEndPathMaterial.equals(Material.WHITE)){
				Path oPath2 = createNewPath();
				oPath2.getAttributes().setOutlineMaterial(oEndPathMaterial);
				lPath.add(oPath2);
				renderLastPath();
			}
		}

	}

	public void renderLastPath() {
		if(lPath.size() != 0){
			Path oPath = lPath.get(lPath.size() - 1);
			layer.addRenderable(oPath);
		}
	}



}
