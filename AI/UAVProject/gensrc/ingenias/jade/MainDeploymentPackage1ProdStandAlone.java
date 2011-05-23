

/*
    Copyright (C) 2005 Jorge Gomez Sanz

    This file is part of INGENIAS Agent Framework, an agent infrastructure linked
    to the INGENIAS Development Kit, and availabe at http://grasia.fdi.ucm.es/ingenias or
    http://ingenias.sourceforge.net. 

    INGENIAS Agent Framework is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    INGENIAS Agent Framework is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with INGENIAS Agent Framework; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

*/


package ingenias.jade;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import jade.core.*;
import ingenias.jade.mental.*;

import ingenias.jade.graphics.MainInteractionManager;


public class MainDeploymentPackage1ProdStandAlone {


  public static void main(String args[]) throws Exception{
		IAFProperties.setGraphicsOn(false);

		new Thread(){
			public void run(){
				String[] args1=new String[4];
				args1[0]="-port";
				args1[1]="60000";
				args1[2]="-file-dir";
				args1[3]="jade/";								 				
				jade.Boot.main(args1);		
			}
		}.start();

        // Get a hold on JADE runtime
        jade.core.Runtime rt = jade.core.Runtime.instance();

        // Exit the JVM when there are no more containers around
        rt.setCloseVM(true);

        // Create a default profile
        Profile p = new ProfileImpl();
        p.setParameter("preload","a*");
        p.setParameter(Profile.MAIN_PORT, "60000");
        p.setParameter(Profile.FILE_DIR, "jade/");
        
        
        // Waits for JADE to start
        boolean notConnected=true;
		
		while (notConnected){			
				try {
					Socket s=new Socket("localhost",Integer.parseInt("60000"));
					notConnected=false;
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					
					System.err.println("Error: "+e.getMessage());
					System.err.println("Reconnecting in one second");
					try {
						Thread.currentThread().sleep(1000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}

		}

        // Create a new non-main container, connecting to the default
        // main container (i.e. on this host, port 1099)
        final jade.wrapper.AgentContainer ac = rt.createAgentContainer(p);

{
        // Create a new agent
        final jade.wrapper.AgentController agcPilot_0DeploymentUnitByType9 = ac.createNewAgent("Pilot_0DeploymentUnitByType9",
            "ingenias.jade.agents.PilotJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up Pilot_0DeploymentUnitByType9...");
              agcPilot_0DeploymentUnitByType9.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcPilot_1DeploymentUnitByType9 = ac.createNewAgent("Pilot_1DeploymentUnitByType9",
            "ingenias.jade.agents.PilotJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up Pilot_1DeploymentUnitByType9...");
              agcPilot_1DeploymentUnitByType9.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcPilot_2DeploymentUnitByType9 = ac.createNewAgent("Pilot_2DeploymentUnitByType9",
            "ingenias.jade.agents.PilotJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up Pilot_2DeploymentUnitByType9...");
              agcPilot_2DeploymentUnitByType9.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcPilot_3DeploymentUnitByType9 = ac.createNewAgent("Pilot_3DeploymentUnitByType9",
            "ingenias.jade.agents.PilotJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up Pilot_3DeploymentUnitByType9...");
              agcPilot_3DeploymentUnitByType9.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcPilot_4DeploymentUnitByType9 = ac.createNewAgent("Pilot_4DeploymentUnitByType9",
            "ingenias.jade.agents.PilotJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up Pilot_4DeploymentUnitByType9...");
              agcPilot_4DeploymentUnitByType9.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcPilot_5DeploymentUnitByType9 = ac.createNewAgent("Pilot_5DeploymentUnitByType9",
            "ingenias.jade.agents.PilotJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up Pilot_5DeploymentUnitByType9...");
              agcPilot_5DeploymentUnitByType9.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcPilot_6DeploymentUnitByType9 = ac.createNewAgent("Pilot_6DeploymentUnitByType9",
            "ingenias.jade.agents.PilotJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up Pilot_6DeploymentUnitByType9...");
              agcPilot_6DeploymentUnitByType9.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcPilot_7DeploymentUnitByType9 = ac.createNewAgent("Pilot_7DeploymentUnitByType9",
            "ingenias.jade.agents.PilotJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up Pilot_7DeploymentUnitByType9...");
              agcPilot_7DeploymentUnitByType9.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcPilot_8DeploymentUnitByType9 = ac.createNewAgent("Pilot_8DeploymentUnitByType9",
            "ingenias.jade.agents.PilotJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up Pilot_8DeploymentUnitByType9...");
              agcPilot_8DeploymentUnitByType9.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcPilot_9DeploymentUnitByType9 = ac.createNewAgent("Pilot_9DeploymentUnitByType9",
            "ingenias.jade.agents.PilotJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up Pilot_9DeploymentUnitByType9...");
              agcPilot_9DeploymentUnitByType9.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcPlane_0DeploymentUnitByType8 = ac.createNewAgent("Plane_0DeploymentUnitByType8",
            "ingenias.jade.agents.PlaneJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up Plane_0DeploymentUnitByType8...");
              agcPlane_0DeploymentUnitByType8.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcPlane_1DeploymentUnitByType8 = ac.createNewAgent("Plane_1DeploymentUnitByType8",
            "ingenias.jade.agents.PlaneJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up Plane_1DeploymentUnitByType8...");
              agcPlane_1DeploymentUnitByType8.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcPlane_2DeploymentUnitByType8 = ac.createNewAgent("Plane_2DeploymentUnitByType8",
            "ingenias.jade.agents.PlaneJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up Plane_2DeploymentUnitByType8...");
              agcPlane_2DeploymentUnitByType8.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcPlane_3DeploymentUnitByType8 = ac.createNewAgent("Plane_3DeploymentUnitByType8",
            "ingenias.jade.agents.PlaneJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up Plane_3DeploymentUnitByType8...");
              agcPlane_3DeploymentUnitByType8.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcPlane_4DeploymentUnitByType8 = ac.createNewAgent("Plane_4DeploymentUnitByType8",
            "ingenias.jade.agents.PlaneJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up Plane_4DeploymentUnitByType8...");
              agcPlane_4DeploymentUnitByType8.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcPlane_5DeploymentUnitByType8 = ac.createNewAgent("Plane_5DeploymentUnitByType8",
            "ingenias.jade.agents.PlaneJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up Plane_5DeploymentUnitByType8...");
              agcPlane_5DeploymentUnitByType8.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcPlane_6DeploymentUnitByType8 = ac.createNewAgent("Plane_6DeploymentUnitByType8",
            "ingenias.jade.agents.PlaneJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up Plane_6DeploymentUnitByType8...");
              agcPlane_6DeploymentUnitByType8.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcPlane_7DeploymentUnitByType8 = ac.createNewAgent("Plane_7DeploymentUnitByType8",
            "ingenias.jade.agents.PlaneJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up Plane_7DeploymentUnitByType8...");
              agcPlane_7DeploymentUnitByType8.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcPlane_8DeploymentUnitByType8 = ac.createNewAgent("Plane_8DeploymentUnitByType8",
            "ingenias.jade.agents.PlaneJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up Plane_8DeploymentUnitByType8...");
              agcPlane_8DeploymentUnitByType8.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcPlane_9DeploymentUnitByType8 = ac.createNewAgent("Plane_9DeploymentUnitByType8",
            "ingenias.jade.agents.PlaneJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up Plane_9DeploymentUnitByType8...");
              agcPlane_9DeploymentUnitByType8.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcController_0DeploymentUnitByType10 = ac.createNewAgent("Controller_0DeploymentUnitByType10",
            "ingenias.jade.agents.ControllerJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up Controller_0DeploymentUnitByType10...");
              agcController_0DeploymentUnitByType10.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcController_1DeploymentUnitByType10 = ac.createNewAgent("Controller_1DeploymentUnitByType10",
            "ingenias.jade.agents.ControllerJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up Controller_1DeploymentUnitByType10...");
              agcController_1DeploymentUnitByType10.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcController_2DeploymentUnitByType10 = ac.createNewAgent("Controller_2DeploymentUnitByType10",
            "ingenias.jade.agents.ControllerJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up Controller_2DeploymentUnitByType10...");
              agcController_2DeploymentUnitByType10.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcFlightPlanner_0DeploymentUnitByType11 = ac.createNewAgent("FlightPlanner_0DeploymentUnitByType11",
            "ingenias.jade.agents.FlightPlannerJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up FlightPlanner_0DeploymentUnitByType11...");
              agcFlightPlanner_0DeploymentUnitByType11.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

}
	      MainInteractionManager.getInstance().setTitle("node DeploymentPackage1");
     }
}

 