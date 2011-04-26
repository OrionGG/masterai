

/**
 *
 *
 *  Description of the task /
 *
 * 
 *@author     Jorge J. Gomez
 *@version    1.0
 */

package ingenias.jade.components;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.ControllerException;

import java.awt.BorderLayout;
import java.util.*;

import javax.xml.parsers.DocumentBuilderFactory;



import gov.nasa.worldwind.Configuration;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.examples.ApplicationTemplate;
import gov.nasa.worldwind.examples.FlatWorldPanel;
import gov.nasa.worldwind.globes.EarthFlat;
import gov.nasa.worldwind.layers.RenderableLayer;
import ingenias.jade.exception.*;



public  class EnvironmentAppImp extends EnvironmentApp{

	public EnvironmentAppImp(){
		super();
	}


	public ArrayList<ingenias.jade.mental.ObjectDetected> getCloseObjects(){
		//TODO: INSERT HERE YOUR CODE
		return null;
	} 
	
	public static class ATemplate extends ApplicationTemplate
	{
		public static class AppFrame extends ApplicationTemplate.AppFrame
		{
			public AppFrame()
			{
				super(true, true, false);

				RenderableLayer layer = new RenderableLayer();
				
				Simulation.SimulationVars.layer = layer;
				
				controllers.EntitiesController oEntitiesController = new controllers.EntitiesController();
				oEntitiesController.render(layer);
				
				

				
				// Add the layer to the model.
				
				insertBeforeCompass(getWwd(), layer);

				// Update layer panel
				this.getLayerPanel().update(this.getWwd());

					
			}
		}
	}
	
	public void start(){
		System.setProperty("javax.xml.parsers.DocumentBuilderFactory", "com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl");
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		//gov.nasa.worldwind.examples.ApplicationTemplate.main(new String[]{});
		Configuration.setValue(AVKey.GLOBE_CLASS_NAME, EarthFlat.class.getName());
		//Configuration.setValue(AVKey.VIEW_CLASS_NAME, FlatOrbitView.class.getName());

		ApplicationTemplate.start("World Wind Paths", ATemplate.AppFrame.class);
		
	} 

}

