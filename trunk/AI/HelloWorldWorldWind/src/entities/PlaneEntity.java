package entities;

import gov.nasa.worldwind.geom.Position;

public class PlaneEntity {

	private float fSpeed;//knots
	private float fHead;
	private float fAltitude;
	private Position oPosition;
	private Position oDestination;

	
	public PlaneEntity(float fSpeed, float fHead, float fAltitude,
			Position oPosition, Position oDestination) {
		super();
		this.fSpeed = fSpeed;
		this.fHead = fHead;
		this.fAltitude = fAltitude;
		this.oPosition = oPosition;
		this.oDestination = oDestination;
	}
	
	public PlaneEntity(Position fromDegrees) {

		this.oPosition = fromDegrees;
	}

	public float getfSpeed() {
		return fSpeed;
	}
	public void setfSpeed(float fSpeed) {
		this.fSpeed = fSpeed;
	}
	public float getfHead() {
		return fHead;
	}
	public void setfHead(float fHead) {
		this.fHead = fHead;
	}
	public float getfAltitude() {
		return fAltitude;
	}
	public void setfAltitude(float fAltitude) {
		this.fAltitude = fAltitude;
	}
	public Position getoPosition() {
		return oPosition;
	}
	public void setoPosition(Position oPos) {
		this.oPosition = oPos;
	}

	public Position getoDestination() {
		return oDestination;
	}

	public void setoDestination(Position oDestination) {
		this.oDestination = oDestination;
	}
	
}
