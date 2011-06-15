package enums;

import gov.nasa.worldwind.geom.Position;

public enum Airport {
	//ALICANTE(Position.fromDegrees(38.282, -0.558, 11e3), 9842,"ALICANTE", "Alicante"),
	BARCELONA(Position.fromDegrees(41.297, 2.078, 11e3), 11654, "BARCELONA", "Barcelona"),
	//GRANCANARIA(Position.fromDegrees(27.932, -15.387, 11e3), 11654, "GRAN CANARIA", "Las Palmas"),
	BARAJAS(Position.fromDegrees(40.494, -3.567, 11e3), 11654, "BARAJAS", "Madrid")
	/*,
	MALAGA(Position.fromDegrees(36.675, -4.499, 11e3), 11654, "MALAGA", "Malaga"),
	PALMADEMALLORCA(Position.fromDegrees(39.552, 2.739, 11e3), 11654, "PALMA DE MALLORCA", "Mallorca Island"),
	SANTIAGO(Position.fromDegrees(42.896, -8.415, 11e3), 11654, "SANTIAGO", "Santiago de Compostela")*/
	//,
	//TENERIFESUR(Position.fromDegrees(28.044, -16.572, 11e3), 11654, "TENERIFE SUR", "Tenerife Island"),
	//TENERIFENORTE(Position.fromDegrees(28.483, -16.342, 11e3), 11654, "TENERIFE NORTE", "Tenerife Island")
	;
	
	
	private Position oPosition;
	private double dMaxRunWayKM;
	private String oName;
	private String sCity;
	public Position getPosition() {
		return oPosition;
	}
	public void setPosition(Position oPosition) {
		this.oPosition = oPosition;
	}
	public double getMaxRunWayKM() {
		return dMaxRunWayKM;
	}
	public void setMaxRunWayKM(double dMaxRunWayKM) {
		this.dMaxRunWayKM = dMaxRunWayKM;
	}
	public String getName() {
		return oName;
	}
	public void setName(String oName) {
		this.oName = oName;
	}
	public String getCity() {
		return sCity;
	}
	public void setCity(String sCity) {
		this.sCity = sCity;
	}
	private Airport(Position oPosition, int iMaxRunWayFT, String oName, String sCity) {
		this.oPosition = oPosition;
		this.dMaxRunWayKM = 0.0003048 * iMaxRunWayFT;
		this.oName = oName;
		this.sCity = sCity;
	}
	
	public String toString(){
		return oName;
	}
	
	

}
