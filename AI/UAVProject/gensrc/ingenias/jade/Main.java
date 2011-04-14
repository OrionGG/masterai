

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


public class Main {


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
        final jade.wrapper.AgentController agcPilot = ac.createNewAgent("Pilot",
            "ingenias.jade.agents.PilotJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up Pilot...");
              agcPilot.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcPlane = ac.createNewAgent("Plane",
            "ingenias.jade.agents.PlaneJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up Plane...");
              agcPlane.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcHuman_Pilot = ac.createNewAgent("Human_Pilot",
            "ingenias.jade.agents.Human_PilotJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up Human_Pilot...");
              agcHuman_Pilot.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcFlightPlanner = ac.createNewAgent("FlightPlanner",
            "ingenias.jade.agents.FlightPlannerJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up FlightPlanner...");
              agcFlightPlanner.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcUnmanned_Autonomous_Pilot = ac.createNewAgent("Unmanned_Autonomous_Pilot",
            "ingenias.jade.agents.Unmanned_Autonomous_PilotJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up Unmanned_Autonomous_Pilot...");
              agcUnmanned_Autonomous_Pilot.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcSimulation_Creator = ac.createNewAgent("Simulation_Creator",
            "ingenias.jade.agents.Simulation_CreatorJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up Simulation_Creator...");
              agcSimulation_Creator.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBrave_Pilot = ac.createNewAgent("Brave_Pilot",
            "ingenias.jade.agents.Brave_PilotJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up Brave_Pilot...");
              agcBrave_Pilot.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcSafe_Pilot = ac.createNewAgent("Safe_Pilot",
            "ingenias.jade.agents.Safe_PilotJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up Safe_Pilot...");
              agcSafe_Pilot.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcNormal_Pilot = ac.createNewAgent("Normal_Pilot",
            "ingenias.jade.agents.Normal_PilotJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up Normal_Pilot...");
              agcNormal_Pilot.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

}
	      MainInteractionManager.getInstance().setTitle("node ");
     }
}

 