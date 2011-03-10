package views;
import entities.PlaneEntity;
import gov.nasa.worldwind.Configuration;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.examples.ApplicationTemplate;
import gov.nasa.worldwind.examples.FlatWorldPanel;
import gov.nasa.worldwind.examples.Paths.AppFrame;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.globes.EarthFlat;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Offset;
import gov.nasa.worldwind.render.Path;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.util.WWUtil;
import gov.nasa.worldwind.view.orbit.FlatOrbitView;

import java.awt.BorderLayout;
import java.util.ArrayList;

import Controllers.EntitiesController;
import Controllers.PlaneController;


public class PathInFlatWorld extends ApplicationTemplate
{
	public static class AppFrame extends ApplicationTemplate.AppFrame
	{
		public AppFrame()
		{
			super(true, true, false);

			RenderableLayer layer = new RenderableLayer();

			// Create and set an attribute bundle.
			ShapeAttributes attrs = new BasicShapeAttributes();
			attrs.setOutlineMaterial(new Material(WWUtil.makeRandomColor(null)));
			attrs.setOutlineWidth(2d);

			// Create a path, set some of its properties and set its attributes.
			ArrayList<Position> pathPositions = new ArrayList<Position>();
			pathPositions.add(Position.fromDegrees( 38.345278,  -0.483056, 0));
			pathPositions.add(Position.fromDegrees(38.995556, -1.855833, 1000));
			pathPositions.add(Position.fromDegrees(40.416667, -3.7, 0));
			Path path = new Path(pathPositions);
			path.setAttributes(attrs);
			path.setVisible(true);
			path.setAltitudeMode(WorldWind.RELATIVE_TO_GROUND);
			path.setPathType(AVKey.GREAT_CIRCLE);
			layer.addRenderable(path);
			
			EntitiesController oEntitiesController = new EntitiesController();
			
			PlaneController oPlaneController = new PlaneController("Concorde", 
					Position.fromDegrees( 38.345278,  -0.483056, 1000));			
			
			oEntitiesController.setoPlaneController(oPlaneController);
			
			oEntitiesController.addWayPoint("AA", Position.fromDegrees(40.4482, -3.5388, 2e4));
			oEntitiesController.addWayPoint("ABRIX", Position.fromDegrees(43.6463, -1.9626, 2e4));
			oEntitiesController.addWayPoint("ACD", Position.fromDegrees(40.5857, -3.6765, 2e4));
			oEntitiesController.addWayPoint("ADEDI", Position.fromDegrees(43.1761, -2.1791, 2e4));
			oEntitiesController.addWayPoint("ADRAS", Position.fromDegrees(36.8322, -2.7275, 2e4));
			oEntitiesController.addWayPoint("ADUBI", Position.fromDegrees(35.8333, -6.3239, 2e4));
			oEntitiesController.addWayPoint("ADUXO", Position.fromDegrees(40.5123, -2.0643, 2e4));
			oEntitiesController.addWayPoint("ADX", Position.fromDegrees(39.5494, 2.3959, 2e4));
			oEntitiesController.addWayPoint("AGADO", Position.fromDegrees(41.8728, -8.9267, 2e4));
			oEntitiesController.render(layer);
			
			// Create a path, set some of its properties and set its attributes.
			ArrayList<Position> pathPositions2 = new ArrayList<Position>();
			Position oPosition = oEntitiesController.getWayPoints().get("ADX").getPosition();
			pathPositions2.add(oPosition);
			oPosition = oEntitiesController.getWayPoints().get("ADUXO").getPosition();
			pathPositions2.add(oPosition);
			oPosition = oEntitiesController.getWayPoints().get("ADEDI").getPosition();
			pathPositions2.add(oPosition);
			oPosition = oEntitiesController.getWayPoints().get("AGADO").getPosition();
			pathPositions2.add(oPosition);
			Path path2 = new Path(pathPositions2);
			path2.setAttributes(attrs);
			path2.setVisible(true);
			path2.setAltitudeMode(WorldWind.RELATIVE_TO_GROUND);
			path2.setPathType(AVKey.GREAT_CIRCLE);
			layer.addRenderable(path2);

			// Add the layer to the model.
			insertBeforeCompass(getWwd(), layer);

			// Update layer panel
			this.getLayerPanel().update(this.getWwd());

			// Add flat world projection control panel
			this.getLayerPanel().add(new FlatWorldPanel(this.getWwd()),  BorderLayout.SOUTH);
			
			
		}
	}

	public static void main(String[] args)
	{ 
		Configuration.setValue(AVKey.GLOBE_CLASS_NAME, EarthFlat.class.getName());
		//Configuration.setValue(AVKey.VIEW_CLASS_NAME, FlatOrbitView.class.getName());
		ApplicationTemplate.start("World Wind Paths", AppFrame.class);
		PlaneController.start();
	}
}