package views;


import enums.Waypoint;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;

public class WayPointView extends PointPlacemark {
	private Waypoint oWayPointEntity;

	public WayPointView(Waypoint oWayPointEntityParam) {
		super(oWayPointEntityParam.getoPosition());

		oWayPointEntity = oWayPointEntityParam;
		
		this.setValue(AVKey.DISPLAY_NAME, oWayPointEntity.toString());
		
		// Create a placemark off the surface and with a line.
		this.setAltitudeMode(WorldWind.RELATIVE_TO_GROUND);
        this.setLineEnabled(true);

		PointPlacemarkAttributes attrs3 = new PointPlacemarkAttributes();
        attrs3 = new PointPlacemarkAttributes();
        attrs3.setLabelColor("ffff0000");
        attrs3.setLineMaterial(Material.MAGENTA);
        attrs3.setLineWidth(2d);
        attrs3.setUsePointAsDefaultImage(true);
        attrs3.setScale(10d);
        this.setAttributes(attrs3);
	}
	
	public void render(RenderableLayer layer){
        layer.addRenderable(this);
	}

}
