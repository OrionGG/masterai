package enums;

import gov.nasa.worldwind.geom.Position;


public enum Waypoint {
	AA(Position.fromDegrees(40.4482, -3.5388, 2e4), WaypointControl.LEMD, WaypointType.Terminal),
	ABRIX(Position.fromDegrees(43.6463, -1.9626, 2e4), WaypointControl.LECM, WaypointType.RNAV),
	ACD(Position.fromDegrees(40.5857, -3.6765, 2e4), WaypointControl.LEGT, WaypointType.Terminal),
	ADEDI(Position.fromDegrees(43.1761, -2.1791, 2e4), WaypointControl.LECM, WaypointType.LowLevel),
	ADRAS(Position.fromDegrees(36.8322, -2.7275, 2e4), WaypointControl.LECM, WaypointType.LowLevel),
	ADUXO(Position.fromDegrees(40.5123, -2.0643, 2e4), WaypointControl.LECM, WaypointType.HighAndLowLevel),
	ADX(Position.fromDegrees(39.5494, 2.3959, 2e4), WaypointControl.LECB, WaypointType.HighAndLowLevel),
	AGADO(Position.fromDegrees(41.8728, -8.9267, 2e4), WaypointControl.LECM, WaypointType.RNAV),
	AGENA(Position.fromDegrees(41.5448, 3.4886, 2e4), WaypointControl.LECB, WaypointType.HighAndLowLevel),
	ALAMA(Position.fromDegrees(36.8934, -4.0098, 2e4), WaypointControl.LEMG, WaypointType.Terminal);


	private Position oPosition;
	private WaypointType oType;
	private WaypointControl oControl;
	
	public Position getoPosition() {
		return oPosition;
	}

	public WaypointType getoType() {
		return oType;
	}

	public WaypointControl getoControl() {
		return oControl;
	}

	private Waypoint(Position oPosition, WaypointControl oControl, WaypointType oType){
		this.oPosition = oPosition;
		this.oControl = oControl;
		this.oType = oType;
	}
	
}
