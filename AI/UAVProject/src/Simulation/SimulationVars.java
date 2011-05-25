package Simulation;


import gov.nasa.worldwind.layers.RenderableLayer;

import java.util.ArrayList;

import views.PlaneView;

public class SimulationVars {
	public static int x = 10; //10x
	public static long iSleepTime = 5000;
	public static boolean bSimulation = false;
	public static ArrayList<PlaneView> lPlanesFlying = new ArrayList<PlaneView>();
	public static RenderableLayer layer;
}
