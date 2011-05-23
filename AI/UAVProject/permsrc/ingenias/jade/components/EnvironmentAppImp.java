

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
	
	
	
	public void start(){
		thread.EnvironmentThread oEnvironmentThread = 
			new thread.EnvironmentThread();
		Thread thread = new Thread(oEnvironmentThread);
		thread.start();
		
	} 

}

