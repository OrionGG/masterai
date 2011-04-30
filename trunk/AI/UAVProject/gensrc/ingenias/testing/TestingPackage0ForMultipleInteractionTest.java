

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


package ingenias.testing;


import java.util.Vector;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import jade.core.*;
import jade.wrapper.StaleProxyException;
import jade.wrapper.State;
import ingenias.exception.TimeOut;
import ingenias.jade.mental.*;
import ingenias.tests.*;
import ingenias.jade.IAFProperties;
import ingenias.jade.graphics.MainInteractionManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;




public class TestingPackage0ForMultipleInteractionTest extends MultipleInteractionTest{
jade.wrapper.AgentContainer ac=null;
 
  @Before
  public void agentSetup() throws StaleProxyException, TimeOut{
  
  
        IAFProperties.setGraphicsOn(false); // disable graphics
         MainInteractionManager.goManual(); // Stop task execution
         
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
        
     
        // Exit the JVM when there are no more containers around
        rt.setCloseVM(false);
        
        // Create a new non-main container, connecting to the default
        // main container (i.e. on this host, port 1099)
        ac = rt.createAgentContainer(p);

{
        // Create a new agent
        final jade.wrapper.AgentController agcSimulation_Creator_0Deployment_Simulator = ac.createNewAgent("Simulation_Creator_0Deployment_Simulator",
            "ingenias.jade.agents.Simulation_CreatorJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
                addStartedAgent("Simulation_Creator_0Deployment_Simulator");
                
                addAgentRole("Simulation_Creator_0Deployment_Simulator","");   
    			             
    			             
               System.out.println("Starting up Simulation_Creator_0Deployment_Simulator...");
              agcSimulation_Creator_0Deployment_Simulator.start();
              
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcPlane_0DeploymentUnitByType1 = ac.createNewAgent("Plane_0DeploymentUnitByType1",
            "ingenias.jade.agents.PlaneJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
                addStartedAgent("Plane_0DeploymentUnitByType1");
                
                addAgentRole("Plane_0DeploymentUnitByType1","Colaborator");   
    			             
    			             
               System.out.println("Starting up Plane_0DeploymentUnitByType1...");
              agcPlane_0DeploymentUnitByType1.start();
              
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcPlane_1DeploymentUnitByType1 = ac.createNewAgent("Plane_1DeploymentUnitByType1",
            "ingenias.jade.agents.PlaneJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
                addStartedAgent("Plane_1DeploymentUnitByType1");
                
                addAgentRole("Plane_1DeploymentUnitByType1","Colaborator");   
    			             
    			             
               System.out.println("Starting up Plane_1DeploymentUnitByType1...");
              agcPlane_1DeploymentUnitByType1.start();
              
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcPilot_0DeploymentUnitByType0 = ac.createNewAgent("Pilot_0DeploymentUnitByType0",
            "ingenias.jade.agents.PilotJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
                addStartedAgent("Pilot_0DeploymentUnitByType0");
                
                addAgentRole("Pilot_0DeploymentUnitByType0","Initiator");   
    			             
    			             
               System.out.println("Starting up Pilot_0DeploymentUnitByType0...");
              agcPilot_0DeploymentUnitByType0.start();
              
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcPilot_1DeploymentUnitByType0 = ac.createNewAgent("Pilot_1DeploymentUnitByType0",
            "ingenias.jade.agents.PilotJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
                addStartedAgent("Pilot_1DeploymentUnitByType0");
                
                addAgentRole("Pilot_1DeploymentUnitByType0","Initiator");   
    			             
    			             
               System.out.println("Starting up Pilot_1DeploymentUnitByType0...");
              agcPilot_1DeploymentUnitByType0.start();
              
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcFlightPlanner_0Deployment_FlightPlanner = ac.createNewAgent("FlightPlanner_0Deployment_FlightPlanner",
            "ingenias.jade.agents.FlightPlannerJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
                addStartedAgent("FlightPlanner_0Deployment_FlightPlanner");
                
                addAgentRole("FlightPlanner_0Deployment_FlightPlanner","Colaborator");   
    			             
    			             
               System.out.println("Starting up FlightPlanner_0Deployment_FlightPlanner...");
              agcFlightPlanner_0Deployment_FlightPlanner.start();
              
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

}
	      MainInteractionManager.getInstance().setTitle("node Deployment");
	    
     }
     
     
     @After
	public void endTest() throws StaleProxyException{
	 ac.kill();	
	};
	

}

 