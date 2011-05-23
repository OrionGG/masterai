package thread;



import javax.xml.parsers.DocumentBuilderFactory;

import gov.nasa.worldwind.Configuration;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.examples.ApplicationTemplate;
import gov.nasa.worldwind.globes.EarthFlat;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.LayerList;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.layers.ViewControlsLayer;

public class EnvironmentThread implements Runnable{
	public static class ATemplate extends ApplicationTemplate
	{
		public static class AppFrame extends ApplicationTemplate.AppFrame
		{
			public AppFrame()
			{
				super(false, true, false);
				//removeAllLayer();
				RenderableLayer layer = new RenderableLayer();
				layer.setName("Show Planes");
				
				Simulation.SimulationVars.layer = layer;
				
				controllers.EntitiesController oEntitiesController = new controllers.EntitiesController();
				oEntitiesController.render(layer);
				
				// Add the layer to the model.
				getWwd().getModel().getLayers().add(layer);

				removeLayer("ViewControlsLayer");

				// Update layer panel
				this.getLayerPanel().update(this.getWwd());

					
			}
			
			public void removeAllLayer() {
				getWwd().getModel().getLayers().clear();
			}

			public void removeLayer(String targetName) {
				int targetPosition = 0;
				LayerList layers = getWwd().getModel().getLayers();
		        for (Layer l : layers)
		        {
		        	if (l.getName().indexOf(targetName) != -1)
		            {
		                targetPosition = layers.indexOf(l);
		                break;
		            }
		        }
		        layers.remove(targetPosition);
			}
		}
	}
	@Override
	public void run() {
		System.setProperty("javax.xml.parsers.DocumentBuilderFactory", "com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl");
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		//gov.nasa.worldwind.examples.ApplicationTemplate.main(new String[]{});
		Configuration.setValue(AVKey.GLOBE_CLASS_NAME, EarthFlat.class.getName());
		//Configuration.setValue(AVKey.VIEW_CLASS_NAME, FlatOrbitView.class.getName());

		ApplicationTemplate.start("World Wind Paths", ATemplate.AppFrame.class);
		
	}

}
