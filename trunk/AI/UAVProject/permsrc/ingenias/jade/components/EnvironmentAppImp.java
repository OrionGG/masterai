

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
				
				controllers.EntitiesController oEntitiesController = new controllers.EntitiesController();
				oEntitiesController.render(layer);
				
				 // Get a hold on JADE runtime
		        jade.core.Runtime rt = jade.core.Runtime.instance();

		        // Exit the JVM when there are no more containers around
		        //rt.setCloseVM(true);

		        // Create a default profile
		        Profile p = new ProfileImpl();
		        p.setParameter("preload","a*");
		        p.setParameter(Profile.MAIN_PORT, "60000");
		        p.setParameter(Profile.FILE_DIR, "jade/");

		        // Create a new non-main container, connecting to the default
		        // main container (i.e. on this host, port 1099)
		        final jade.wrapper.AgentContainer ac = rt.createAgentContainer(p);
		        @SuppressWarnings("unused")
				jade.wrapper.AgentController agcPlane;
		        /*try {
		        	agcPlane = ac.getAgent("Pilot_0DeploymentUnitByType0@OrionPC:60000/JADE");
				} catch (ControllerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/

				
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

