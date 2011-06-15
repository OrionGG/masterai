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
	int iRangeOfRisk = 0;

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

			Material oEndPathMaterial = Material.WHITE;
			double dNewPosAltitudeKM = oNewPos.getAltitude()/1000;
			if(dNewPosAltitudeKM < global.GlobalVarsAndMethods.dCruiseAltitudeKM ){
				oEndPathMaterial = Material.GREEN;
			}
			else if(dNewPosAltitudeKM == global.GlobalVarsAndMethods.dCruiseAltitudeKM){
				oEndPathMaterial = Material.WHITE;				
			}
			else{
				oEndPathMaterial = Material.RED;
			}

			Material oInitialPathMaterial = Material.WHITE;
			if(lPath.size() != 0){
				Path oPath = lPath.get(lPath.size() - 1);
				oInitialPathMaterial = oPath.getAttributes().getOutlineMaterial();
			}
			
			if(!oInitialPathMaterial.equals(oEndPathMaterial)){
				Path oPath2 = createNewPath();
				oPath2.getAttributes().setOutlineMaterial(oEndPathMaterial);
				lPath.add(oPath2);
				renderLastPath();
			}
			
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
		if(iRangeOfRisk != rangeOfRisk){
			Material oEndPlaneMaterial = Material.GREEN;

			switch (rangeOfRisk) {
			case 0:
				oEndPlaneMaterial = Material.GREEN;
				break;
			case 9:

				oEndPlaneMaterial = Material.YELLOW;
				break;

			default:
				break;
			}
			this.getAttributes().setLineMaterial(oEndPlaneMaterial);
			iRangeOfRisk = rangeOfRisk;
		}


	}

	public void renderLastPath() {
		if(lPath.size() != 0){
			Path oPath = lPath.get(lPath.size() - 1);
			layer.addRenderable(oPath);
		}
	}



}
