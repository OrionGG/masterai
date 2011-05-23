

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

import jade.core.*;
import ingenias.jade.mental.*;

import ingenias.jade.graphics.MainInteractionManager;


public class MainDeploymentPackage0 {


  public static void main(String args[]) throws Exception{


        // Get a hold on JADE runtime
        jade.core.Runtime rt = jade.core.Runtime.instance();

        // Exit the JVM when there are no more containers around
        rt.setCloseVM(true);

        // Create a default profile
        Profile p = new ProfileImpl();
        p.setParameter("preload","a*");
        p.setParameter(Profile.MAIN_PORT, "60000");
        p.setParameter(Profile.FILE_DIR, "jade/");

        // Create a new non-main container, connecting to the default
        // main container (i.e. on this host, port 1099)
        final jade.wrapper.AgentContainer ac = rt.createAgentContainer(p);

{
        // Create a new agent
        final jade.wrapper.AgentController agcController_0DeploymentUnitByType5 = ac.createNewAgent("Controller_0DeploymentUnitByType5",
            "ingenias.jade.agents.ControllerJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up Controller_0DeploymentUnitByType5...");
              agcController_0DeploymentUnitByType5.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcController_1DeploymentUnitByType5 = ac.createNewAgent("Controller_1DeploymentUnitByType5",
            "ingenias.jade.agents.ControllerJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up Controller_1DeploymentUnitByType5...");
              agcController_1DeploymentUnitByType5.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcPilot_0DeploymentUnitByType4 = ac.createNewAgent("Pilot_0DeploymentUnitByType4",
            "ingenias.jade.agents.PilotJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up Pilot_0DeploymentUnitByType4...");
              agcPilot_0DeploymentUnitByType4.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcPilot_1DeploymentUnitByType4 = ac.createNewAgent("Pilot_1DeploymentUnitByType4",
            "ingenias.jade.agents.PilotJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up Pilot_1DeploymentUnitByType4...");
              agcPilot_1DeploymentUnitByType4.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcPilot_2DeploymentUnitByType4 = ac.createNewAgent("Pilot_2DeploymentUnitByType4",
            "ingenias.jade.agents.PilotJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up Pilot_2DeploymentUnitByType4...");
              agcPilot_2DeploymentUnitByType4.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcPlane_0DeploymentUnitByType3 = ac.createNewAgent("Plane_0DeploymentUnitByType3",
            "ingenias.jade.agents.PlaneJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up Plane_0DeploymentUnitByType3...");
              agcPlane_0DeploymentUnitByType3.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcPlane_1DeploymentUnitByType3 = ac.createNewAgent("Plane_1DeploymentUnitByType3",
            "ingenias.jade.agents.PlaneJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up Plane_1DeploymentUnitByType3...");
              agcPlane_1DeploymentUnitByType3.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcPlane_2DeploymentUnitByType3 = ac.createNewAgent("Plane_2DeploymentUnitByType3",
            "ingenias.jade.agents.PlaneJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up Plane_2DeploymentUnitByType3...");
              agcPlane_2DeploymentUnitByType3.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcSimulation_Creator_0DeploymentUnitByType7 = ac.createNewAgent("Simulation_Creator_0DeploymentUnitByType7",
            "ingenias.jade.agents.Simulation_CreatorJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up Simulation_Creator_0DeploymentUnitByType7...");
              agcSimulation_Creator_0DeploymentUnitByType7.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcFlightPlanner_0DeploymentUnitByType6 = ac.createNewAgent("FlightPlanner_0DeploymentUnitByType6",
            "ingenias.jade.agents.FlightPlannerJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up FlightPlanner_0DeploymentUnitByType6...");
              agcFlightPlanner_0DeploymentUnitByType6.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

}
	      MainInteractionManager.getInstance().setTitle("node DeploymentPackage0");
     }
}

 