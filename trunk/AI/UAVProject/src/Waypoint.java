import gov.nasa.worldwind.geom.Position;


public enum Waypoint {
	AA(gov.nasa.worldwind.geom.Position.fromDegrees(40.4482, -3.5388, 2e4), WaypointControl.LEMD, WaypointType.Terminal),
	ABRIX(gov.nasa.worldwind.geom.Position.fromDegrees(43.6463, -1.9626, 2e4), WaypointControl.LECM, WaypointType.RNAV),
	ACD(gov.nasa.worldwind.geom.Position.fromDegrees(40.5857, -3.6765, 2e4), WaypointControl.LEGT, WaypointType.Terminal),
	ADEDI(gov.nasa.worldwind.geom.Position.fromDegrees(43.1761, -2.1791, 2e4), WaypointControl.LECM, WaypointType.LowLevel),
	ADRAS(gov.nasa.worldwind.geom.Position.fromDegrees(36.8322, -2.7275, 2e4), WaypointControl.LECM, WaypointType.LowLevel);
	
	private gov.nasa.worldwind.geom.Position oPosition;
	private WaypointType oType;
	private WaypointControl oControl;
	
	private Waypoint(gov.nasa.worldwind.geom.Position oPosition, WaypointControl oControl, WaypointType oType){
		this.oPosition = oPosition;
		this.oControl = oControl;
		this.oType = oType;
	}
	
}
