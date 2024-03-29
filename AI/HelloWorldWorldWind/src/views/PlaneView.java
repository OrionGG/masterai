package views;
import java.awt.Color;

import entities.PlaneEntity;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;


public class PlaneView extends PointPlacemark {
	private PlaneEntity oPlaneEntity;
	
	public PlaneView(PlaneEntity oPlaneEntityParam, String sPlaneName) {
		super(oPlaneEntityParam.getoPosition());
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

	public PlaneEntity getoPlaneEntity() {
		return oPlaneEntity;
	}

	public void setoPlaneEntity(PlaneEntity oPlaneEntity) {
		this.oPlaneEntity = oPlaneEntity;
	}

	public void setNewPosition(Position oNewPos) {
		super.setPosition(oNewPos);
		this.oPlaneEntity.setoPosition(oNewPos);
	}
	
	public void render(RenderableLayer layer){
        layer.addRenderable(this);
	}
	
	

}
