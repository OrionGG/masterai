

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


public class MainOnly_One_Plane {


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
        final jade.wrapper.AgentController agcSimulation_Creator_0Deployment_Simulator = ac.createNewAgent("Simulation_Creator_0Deployment_Simulator",
            "ingenias.jade.agents.Simulation_CreatorJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up Simulation_Creator_0Deployment_Simulator...");
              agcSimulation_Creator_0Deployment_Simulator.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcPlane_0Deployment_Plane = ac.createNewAgent("Plane_0Deployment_Plane",
            "ingenias.jade.agents.PlaneJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up Plane_0Deployment_Plane...");
              agcPlane_0Deployment_Plane.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcFlightPlanner_0Deployment_FlightPlan = ac.createNewAgent("FlightPlanner_0Deployment_FlightPlan",
            "ingenias.jade.agents.FlightPlannerJADEAgent", new Object[0]);	
	
	{ Flight_Plan ment=new Flight_Plan();
	   
	     ment.setDestinationPoint(gov.nasa.worldwind.geom.Position.fromDegrees(41.8728, -8.9267, 2e4));
	   
	     ment.setDeparturePoint(gov.nasa.worldwind.geom.Position.fromDegrees(40.4482, -3.5388, 2e4));
	   
	     ment.setAlternateAirports(java.util.Arrays.asList(new gov.nasa.worldwind.geom.Position[]{gov.nasa.worldwind.geom.Position.fromDegrees(39.5494, 2.3959, 2e4)}));
	   
	     ment.setWaypoints(java.util.Arrays.asList(new gov.nasa.worldwind.geom.Position[]{gov.nasa.worldwind.geom.Position.fromDegrees(40.5857, -3.6765, 2e4),gov.nasa.worldwind.geom.Position.fromDegrees(40.5123, -2.0643, 2e4),gov.nasa.worldwind.geom.Position.fromDegrees(43.1761, -2.1791, 2e4)}));
	   	   
	   agcFlightPlanner_0Deployment_FlightPlan.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up FlightPlanner_0Deployment_FlightPlan...");
              agcFlightPlanner_0Deployment_FlightPlan.start();
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
               System.out.println("Starting up Pilot_0DeploymentUnitByType0...");
              agcPilot_0DeploymentUnitByType0.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

}
	      MainInteractionManager.getInstance().setTitle("node Only_One_Plane");
     }
}

 