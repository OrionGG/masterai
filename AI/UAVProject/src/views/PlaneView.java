package views;
import ingenias.jade.agents.PlaneJADEAgent;

import java.awt.Color;


import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;


public class PlaneView extends PointPlacemark {
	private PlaneJADEAgent oPlaneEntity;
	
	public PlaneView(PlaneJADEAgent oPlaneEntityParam, String sPlaneName, Position oPosition) {
		super(oPosition);
		oPlaneEntity = oPlaneEntityParam;
		
		PointPlacemarkAttributes attrs2 = new PointPlacemarkAttributes();
        
        attrs2.setUsePointAsDefaultImage(true);
        /*int iRed = new java.util.Random().nextInt(255);
        int iGreen = new java.util.Random().nextInt(255);
        int iBlue = new java.util.Random().nextInt(255);*/
        
        Material m = Material.BLACK;
        attrs2.setLineMaterial(m);
        attrs2.setScale(5d);
        this.setAttributes(attrs2);

        this.setValue(AVKey.DISPLAY_NAME, sPlaneName);

        this.setLineEnabled(false);
	}

	public PlaneJADEAgent getoPlaneEntity() {
		return oPlaneEntity;
	}

	public void setoPlaneEntity(PlaneJADEAgent oPlaneEntity) {
		this.oPlaneEntity = oPlaneEntity;
	}

	public void setNewPosition(Position oNewPos) {
		super.setPosition(oNewPos);
	}
	
	public void render(RenderableLayer layer){
        layer.addRenderable(this);
	}
	
	

}
