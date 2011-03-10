package entities;

import gov.nasa.worldwind.geom.Position;

public class WayPointEntity {

	private Position oPosition;
	private String sName;
	
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public Position getoPosition() {
		return oPosition;
	}
	public void setoPosition(Position oPos) {
		this.oPosition = oPos;
	}
	public WayPointEntity(String sName, Position oPosition) {
		super();
		this.oPosition = oPosition;
		this.sName = sName;
	}
	
	

}
