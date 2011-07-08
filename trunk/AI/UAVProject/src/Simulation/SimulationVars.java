package Simulation;


import gov.nasa.worldwind.layers.RenderableLayer;

import java.util.ArrayList;

import views.PlaneView;

public class SimulationVars {
	public static int x = 10; //10x
	public static long iSleepTime = 1000;
	public static boolean bSimulation = true;
	public static ArrayList<PlaneView> lPlanesFlying = new ArrayList<PlaneView>();
	public static boolean bStartButtonSimulation = false;
}
