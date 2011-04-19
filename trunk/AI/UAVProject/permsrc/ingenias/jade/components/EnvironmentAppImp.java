

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

import java.awt.*;
import java.util.*;

import javax.swing.*;

import gov.nasa.worldwind.*;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.awt.*;
import gov.nasa.worldwind.event.RenderingExceptionListener;
import gov.nasa.worldwind.globes.EarthFlat;
import gov.nasa.worldwind.layers.ViewControlsLayer;
import gov.nasa.worldwind.util.WWUtil;
import ingenias.jade.exception.*;
import ingenias.jade.mental.ObjectDetected;
import gov.nasa.worldwind.examples.LayerPanel;
import gov.nasa.worldwind.exception.*;



public  class EnvironmentAppImp extends EnvironmentApp{

 public EnvironmentAppImp(){
  super();
 }


	public static class AppPanel extends JPanel{
		protected WorldWindowGLCanvas wwd;

		public AppPanel(Dimension canvasSize, boolean includeStatusBar)
		{
			super(new BorderLayout());

			this.wwd = this.createWorldWindow();
			this.wwd.setPreferredSize(canvasSize);

			// Create the default model as described in the current worldwind properties.
			Model m = (Model) WorldWind.createConfigurationComponent(AVKey.MODEL_CLASS_NAME);
			this.wwd.setModel(m);

			// Setup a select listener for the worldmap click-and-go feature
			//this.wwd.addSelectListener(new ClickAndGoSelectListener(this.getWwd(), WorldMapLayer.class));

			this.add(this.wwd, BorderLayout.CENTER);

		}

		protected WorldWindowGLCanvas createWorldWindow()
		{
			return new WorldWindowGLCanvas();
		}

		public WorldWindowGLCanvas getWwd()
		{
			return wwd;
		}
	}

	protected static class AppFrame extends JFrame
	{
		private Dimension canvasSize = new Dimension(800, 600);

		protected AppPanel wwjPanel;
     protected LayerPanel layerPanel;

		public AppFrame()
		{
			this.initialize(true, true, false);
		}

		public AppFrame(boolean includeStatusBar, boolean includeLayerPanel, boolean includeStatsPanel)
		{
			this.initialize(includeStatusBar, includeLayerPanel, includeStatsPanel);
		}

		protected void initialize(boolean includeStatusBar, boolean includeLayerPanel, boolean includeStatsPanel)
		{
			// Create the WorldWindow.
			this.wwjPanel = this.createAppPanel(this.canvasSize, includeStatusBar);
			this.wwjPanel.setPreferredSize(canvasSize);


			  // Put the pieces together.
         this.getContentPane().add(wwjPanel, BorderLayout.CENTER);
         if (includeLayerPanel)
         {
             this.layerPanel = new LayerPanel(this.wwjPanel.getWwd(), null);
             this.getContentPane().add(this.layerPanel, BorderLayout.WEST);
         }

			
			this.wwjPanel.getWwd().addRenderingExceptionListener(new RenderingExceptionListener()
			{
				public void exceptionThrown(Throwable t)
				{
					if (t instanceof WWAbsentRequirementException)
					{
						String message = "Computer does not meet minimum graphics requirements.\n";
						message += "Please install up-to-date graphics driver and try again.\n";
						message += "Reason: " + t.getMessage() + "\n";
						message += "This program will end when you press OK.";

						JOptionPane.showMessageDialog(AppFrame.this, message, "Unable to Start Program",
								JOptionPane.ERROR_MESSAGE);
						System.exit(-1);
					}
				}
			});

			this.pack();

			// Center the application on the screen.
			WWUtil.alignComponent(null, this, AVKey.CENTER);
			this.setResizable(true);
		}

     public LayerPanel getLayerPanel()
     {
         return layerPanel;
     }

		protected AppPanel createAppPanel(Dimension canvasSize, boolean includeStatusBar)
		{
			return new AppPanel(canvasSize, includeStatusBar);
		}

		public Dimension getCanvasSize()
		{
			return canvasSize;
		}

		public AppPanel getWwjPanel()
		{
			return wwjPanel;
		}

		public WorldWindowGLCanvas getWwd()
		{
			return this.wwjPanel.getWwd();
		}
	}
	
	 static
	    {
	        System.setProperty("java.net.useSystemProxies", "true");
	        if (Configuration.isMacOS())
	        {
	            System.setProperty("apple.laf.useScreenMenuBar", "true");
	            System.setProperty("com.apple.mrj.application.apple.menu.about.name", "World Wind Application");
	            System.setProperty("com.apple.mrj.application.growbox.intrudes", "false");
	            System.setProperty("apple.awt.brushMetalLook", "true");
	        }
	        else if (Configuration.isWindowsOS())
	        {
	            System.setProperty("sun.awt.noerasebackground", "true"); // prevents flashing during window resizing
	        }
	    }

		public static AppFrame start(String appName, Class appFrameClass)
		{
			if (Configuration.isMacOS() && appName != null)
			{
	            System.setProperty("apple.laf.useScreenMenuBar", "true");
	            System.setProperty("com.apple.mrj.application.apple.menu.about.name", "World Wind Application");
	            System.setProperty("com.apple.mrj.application.growbox.intrudes", "false");
	            System.setProperty("apple.awt.brushMetalLook", "true");
	   
				System.setProperty("com.apple.mrj.application.apple.menu.about.name", appName);
			}

			try
			{
				final AppFrame frame = (AppFrame) appFrameClass.newInstance();
				frame.setTitle(appName);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				java.awt.EventQueue.invokeLater(new Runnable()
				{
					public void run()
					{
						frame.setVisible(true);
					}
				});

				return frame;
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return null;
			}
		}

 public ArrayList<ObjectDetected> getCloseObjects(){
//TODO: INSERT HERE YOUR CODE
return null;
} 
 
}

 