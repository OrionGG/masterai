package thread;



import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.xml.parsers.DocumentBuilderFactory;

import gov.nasa.worldwind.Configuration;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.examples.ApplicationTemplate;
import gov.nasa.worldwind.examples.FlatWorldPanel;
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
			JButton plansConfigJButton = new JButton("Select Plan Config File",null);
			JButton pilotConfigJButton = new JButton("Select Pilot Config File",null);
			
			JButton startJButton = new JButton("Start",null);
			JButton stopJButton = new JButton("Stop",null);
			
			JTextArea plansLabel= new JTextArea();
			JTextArea pilotLabel = new JTextArea();
			
			public AppFrame()
			{
				super(false, true, false);
				//removeAllLayer();
				RenderableLayer layer = new RenderableLayer();
				layer.setName("Show Planes");
				
				controllers.EntitiesController.getInstance().setRenderableLayer(layer);
				controllers.EntitiesController.getInstance().setWwd(this.getWwd());
				controllers.EntitiesController.getInstance().render();
				
				// Add the layer to the model.
				getWwd().getModel().getLayers().add(layer);
				
				// Add control panels
	            JPanel controls = new JPanel();
	            controls.setLayout(new BoxLayout(controls, BoxLayout.Y_AXIS));

        		plansLabel.setText(global.GlobalVarsAndMethods.fXmlPlanConfigFile.getAbsolutePath());
        		pilotLabel.setText(global.GlobalVarsAndMethods.fXmlPilotConfigFile.getAbsolutePath());
	            
	            // Add view control panel
	            controls.add(configPanel());
	            
	            stopJButton.setEnabled(false);
	            // Add view control panel
	            controls.add(simulationPanel());
	            
	            this.getLayerPanel().add(controls,  BorderLayout.SOUTH);

				//removeLayer("ViewControlsLayer");

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
			
			private JPanel configPanel()
	        {
				JPanel configPanel = new JPanel();
				configPanel.setLayout(new BoxLayout(configPanel, BoxLayout.Y_AXIS));

	            JPanel plansConfigPanel = new JPanel(new GridLayout(0, 1, 0, 0));
	            plansConfigPanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

 
				
				plansConfigJButton.addActionListener(new ActionListener()
	            {
	                public void actionPerformed(ActionEvent e)
	                {
	                	//Create a file chooser
	                	JFileChooser fc = new JFileChooser(global.GlobalVarsAndMethods.fXmlPlanConfigFile);
	                	JFrame frame = new JFrame();
	                	int returnVal = fc.showOpenDialog(frame);

	                	if (returnVal == JFileChooser.APPROVE_OPTION) {
	                		global.GlobalVarsAndMethods.fXmlPlanConfigFile = fc.getSelectedFile();
	                		plansLabel.setText(global.GlobalVarsAndMethods.fXmlPlanConfigFile.getAbsolutePath());
	                	}
	                }
	            });
				

				plansConfigPanel.add(plansConfigJButton);
		        configPanel.add(plansConfigPanel);
				
				JPanel plansLabelPanel = new JPanel(new GridLayout(0, 1, 0, 0));
				plansLabelPanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
				this.plansLabel.setEditable(false);
	            this.plansLabel.setLineWrap(true);
				
	            plansLabelPanel.add(plansLabel);
		        configPanel.add(plansLabelPanel);
		        
				  JPanel pilotConfigPanel = new JPanel(new GridLayout(0, 1, 0, 0));
				  pilotConfigPanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
				pilotConfigJButton.addActionListener(new ActionListener()
	            {
	                public void actionPerformed(ActionEvent e)
	                {
	                	//Create a file chooser
	                	JFileChooser fc = new JFileChooser(global.GlobalVarsAndMethods.fXmlPilotConfigFile);
	                	JFrame frame = new JFrame();
	                	int returnVal = fc.showOpenDialog(frame);

	                	if (returnVal == JFileChooser.APPROVE_OPTION) {
	                		global.GlobalVarsAndMethods.fXmlPilotConfigFile = fc.getSelectedFile();
	                		pilotLabel.setText(global.GlobalVarsAndMethods.fXmlPilotConfigFile.getAbsolutePath());
	                	}
	                }
	            });
				

				pilotConfigPanel.add(pilotConfigJButton);
		        configPanel.add(pilotConfigPanel);
				
	            JPanel pilotLabelPanel = new JPanel(new GridLayout(0, 1, 0, 0));
	            pilotLabelPanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
				//this.pilotLabel.setPreferredSize(new Dimension(200, 140));

				
				this.pilotLabel.setEditable(false);
	            this.pilotLabel.setLineWrap(true);
	            pilotLabelPanel.add(pilotLabel);
		        configPanel.add(pilotLabelPanel);
		        
		        configPanel.setBorder(
		                new CompoundBorder(BorderFactory.createEmptyBorder(9, 9, 9, 9), new TitledBorder("Configuration")));
				
				return configPanel;
				
	        }
			
			private JPanel simulationPanel() {
				JPanel simulationPanel = new JPanel();
				simulationPanel.setLayout(new BoxLayout(simulationPanel, BoxLayout.Y_AXIS));

	            JPanel startJButtonPanel = new JPanel(new GridLayout(0, 1, 0, 0));
	            startJButtonPanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
 
				startJButton.addActionListener(new ActionListener()
	            {
	                public void actionPerformed(ActionEvent e)
	                {
	                	plansConfigJButton.setEnabled(false);
	                	pilotConfigJButton.setEnabled(false);
	                	startJButton.setEnabled(false);
	                	stopJButton.setEnabled(true);;
	                	Simulation.SimulationVars.bStartButtonSimulation = true;
	                	Simulation.SimulationVars.x = 10;
	                }
	            });

				 JPanel stopJButtonPanel = new JPanel(new GridLayout(0, 1, 0, 0));
				 stopJButtonPanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
				stopJButton.addActionListener(new ActionListener()
	            {
	                public void actionPerformed(ActionEvent e)
	                {
	                	startJButton.setEnabled(true);
	                	stopJButton.setEnabled(false);;
	                	Simulation.SimulationVars.x = 0;
	                }
	            });

				startJButtonPanel.add(startJButton);
				stopJButtonPanel.add(stopJButton);
		        simulationPanel.add(startJButtonPanel);
		        simulationPanel.add(stopJButtonPanel);
		        
		        simulationPanel.setBorder(
		                new CompoundBorder(BorderFactory.createEmptyBorder(9, 9, 9, 9), new TitledBorder("Simulation")));
				
				return simulationPanel;
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
