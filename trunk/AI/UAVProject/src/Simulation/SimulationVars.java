package Simulation;


import gov.nasa.worldwind.layers.RenderableLayer;

import java.util.ArrayList;

import views.PlaneView;

public class SimulationVars {
	public static int x = 10; //10x
	public static double dCruiseSpeedKMH = 930;
	public static double dCruiseAltitudeKM = 11;
	public static int iSleepTime = 1000;
	public static boolean bSimulation = false;
	public static ArrayList<PlaneView> lPlanesFlying = new ArrayList<PlaneView>();
	public static RenderableLayer layer;
}
