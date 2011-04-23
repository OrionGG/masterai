package enums;

import gov.nasa.worldwind.geom.Position;


public enum Waypoint {
	//AA(Position.fromDegrees(40.4482, -3.5388, 1e4, 1e4), WaypointControl.LEMD, WaypointType.Terminal),
	//ABRIX(Position.fromDegrees(43.6463, -1.9626, 1e4, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	//ACD(Position.fromDegrees(40.5857, -3.6765, 1e4, 1e4), WaypointControl.LEGT, WaypointType.Terminal),
	//ADEDI(Position.fromDegrees(43.1761, -2.1791, 1e4, 1e4), WaypointControl.LECM, WaypointType.LowLevel),
	//ADRAS(Position.fromDegrees(36.8322, -2.7275, 1e4, 1e4), WaypointControl.LECM, WaypointType.LowLevel),
	//ADUBI(Position.fromDegrees(35.8333, -6.3239, 1e4, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	//ADUXO(Position.fromDegrees(40.5123, -2.0643, 1e4, 1e4), WaypointControl.LECM, WaypointType.HighAndLowLevel),
	//ADX(Position.fromDegrees(39.5494, 2.3959, 1e4, 1e4), WaypointControl.LECB, WaypointType.HighAndLowLevel),
	//AGADO(Position.fromDegrees(41.8728, -8.9267, 1e4, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	//AGENA(Position.fromDegrees(41.5448, 3.4886, 1e4, 1e4), WaypointControl.LECB, WaypointType.HighAndLowLevel),
	//ALAMA(Position.fromDegrees(36.8934, -4.0098, 1e4, 1e4), WaypointControl.LEMG, WaypointType.Terminal),
	//ALT(Position.fromDegrees(38.2683, -0.5701, 1e4, 1e4), WaypointControl.LECB, WaypointType.HighAndLowLevel),
	//R2305(Position.fromDegrees(38.2845, -0.5750, 1e4, 1e4), WaypointControl.LEAL, WaypointType.Terminal),
	ABRIX(Position.fromDegrees(43.6463,-1.9626, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	ADUBI(Position.fromDegrees(35.8333,-6.3239, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	AGADO(Position.fromDegrees(41.8728,-8.9267, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	ANTON(Position.fromDegrees(41.2599,1.6983, 1e4), WaypointControl.LECB, WaypointType.RNAV),
	BABOV(Position.fromDegrees(39.8764,-6.8736, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	BADRU(Position.fromDegrees(43.7365,-2.7965, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	BAKUP(Position.fromDegrees(44.4125,-4.4361, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	BANIL(Position.fromDegrees(43.4487,-4.525, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	BARBO(Position.fromDegrees(42.4488,0.9027, 1e4), WaypointControl.LECB, WaypointType.RNAV),
	BASSO(Position.fromDegrees(39.5443,3.1225, 1e4), WaypointControl.LECB, WaypointType.RNAV),
	BERUX(Position.fromDegrees(45,-11, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	BIPET(Position.fromDegrees(25,-16.3588, 1e4), WaypointControl.GCCC, WaypointType.RNAV),
	BISBA(Position.fromDegrees(42.0864,3.6258, 1e4), WaypointControl.LECB, WaypointType.RNAV),
	BLASO(Position.fromDegrees(40.6528,3.6445, 1e4), WaypointControl.LECB, WaypointType.RNAV),
	BOLKA(Position.fromDegrees(36.4792,-2.6088, 1e4), WaypointControl.LECS, WaypointType.RNAV),
	BRA(Position.fromDegrees(40.4691,-3.5575, 1e4), WaypointControl.LELE, WaypointType.RNAV),
	BRIKE(Position.fromDegrees(36.5048,-5.4164, 1e4), WaypointControl.LEAM, WaypointType.RNAV),
	BROTO(Position.fromDegrees(42.4244,0.154, 1e4), WaypointControl.LECB, WaypointType.RNAV),
	CASIM(Position.fromDegrees(40.2308,0.143, 1e4), WaypointControl.LECB, WaypointType.RNAV),
	CEGAM(Position.fromDegrees(42.9837,-2.2364, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	DADIV(Position.fromDegrees(40.3931,-3.3407, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	DIPOL(Position.fromDegrees(40.4165,-4.6763, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	DIRMU(Position.fromDegrees(41.7855,0.1596, 1e4), WaypointControl.LECB, WaypointType.RNAV),
	DIXIS(Position.fromDegrees(45,-10, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	DOLES(Position.fromDegrees(40.4078,-4.0691, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	EDIGO(Position.fromDegrees(41.5044,-3.4117, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	EDUMO(Position.fromDegrees(22.9167,-23.6, 1e4), WaypointControl.GCCC, WaypointType.RNAV),
	ETIBA(Position.fromDegrees(21.3383,-18.6788, 1e4), WaypointControl.GCCC, WaypointType.RNAV),
	GALAT(Position.fromDegrees(40.1544,1.9409, 1e4), WaypointControl.LECB, WaypointType.RNAV),
	GASBA(Position.fromDegrees(41.9478,-6.446, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	GOMER(Position.fromDegrees(28,-17.3333, 1e4), WaypointControl.GCCC, WaypointType.RNAV),
	GORDO(Position.fromDegrees(44.0139,-6.9277, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	GUNET(Position.fromDegrees(19.595,-19.735, 1e4), WaypointControl.GCCC, WaypointType.RNAV),
	HIDRA(Position.fromDegrees(44.5,-13, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	KALMA(Position.fromDegrees(40.4114,-4.2994, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	KAROL(Position.fromDegrees(42.8254,-6.9172, 1e4), WaypointControl.LEBC, WaypointType.RNAV),
	KOPAS(Position.fromDegrees(44,-13, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	KUMAN(Position.fromDegrees(41.9048,-0.3533, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	KUREG(Position.fromDegrees(40.8993,2.7188, 1e4), WaypointControl.LECB, WaypointType.RNAV),
	LARPA(Position.fromDegrees(40.6266,2.3486, 1e4), WaypointControl.LECB, WaypointType.RNAV),
	LARYS(Position.fromDegrees(28.8718,-14.8343, 1e4), WaypointControl.GCCC, WaypointType.RNAV),
	LATRO(Position.fromDegrees(41.7716,1.6183, 1e4), WaypointControl.LECB, WaypointType.RNAV),
	LAVAL(Position.fromDegrees(40.3172,3.57, 1e4), WaypointControl.LECS, WaypointType.RNAV),
	LEPES(Position.fromDegrees(37.1654,-7.1918, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	LIPOR(Position.fromDegrees(40.4911,-2.8481, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	LOBAR(Position.fromDegrees(41.748,0.3184, 1e4), WaypointControl.LECB, WaypointType.RNAV),
	LOTOS(Position.fromDegrees(40.5497,1.003, 1e4), WaypointControl.LECB, WaypointType.RNAV),
	LURAN(Position.fromDegrees(42.9728,-0.9792, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	MECKI(Position.fromDegrees(41.662,0.6786, 1e4), WaypointControl.LECB, WaypointType.RNAV),
	MELON(Position.fromDegrees(39.7667,-5.3187, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	MINVI(Position.fromDegrees(41.1261,-5.1482, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	MITUM(Position.fromDegrees(40.6456,-3.2478, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	MUDOS(Position.fromDegrees(43.5,-13, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	NELAS(Position.fromDegrees(40.0941,3.5208, 1e4), WaypointControl.LECB, WaypointType.RNAV),
	NETOS(Position.fromDegrees(41.3075,-6.2777, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	NINOS(Position.fromDegrees(41.1296,-6.7771, 1e4), WaypointControl.LEMD, WaypointType.RNAV),
	NORED(Position.fromDegrees(24.6384,-22.48, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	NUBLO(Position.fromDegrees(42.666,-4.9889, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	OSTUR(Position.fromDegrees(40.7809,2.894, 1e4), WaypointControl.LECB, WaypointType.RNAV),
	OXACA(Position.fromDegrees(37.95,-6, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	PARKA(Position.fromDegrees(39,-5.1499, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	PASAS(Position.fromDegrees(45,-13, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	PERDU(Position.fromDegrees(42.7321,0.1512, 1e4), WaypointControl.LECB, WaypointType.RNAV),
	PESAS(Position.fromDegrees(37.0367,-7.3833, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	PETEK(Position.fromDegrees(42.6789,-12, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	PIPOR(Position.fromDegrees(43.009,-1.1081, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	PITAX(Position.fromDegrees(45,-12, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	PODUX(Position.fromDegrees(42.901,-1.8815, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	POPUL(Position.fromDegrees(43.9486,-2.8401, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	PORLI(Position.fromDegrees(39.5288,-7.3664, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	RAKOD(Position.fromDegrees(39.7808,-6.6285, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	RAMON(Position.fromDegrees(41.0093,-0.2852, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	REMGI(Position.fromDegrees(27.0902,-15.2696, 1e4), WaypointControl.GCCC, WaypointType.RNAV),
	RETEN(Position.fromDegrees(43,-13, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	REVAT(Position.fromDegrees(40.3831,-2.9427, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	RIDAV(Position.fromDegrees(40.5352,-5.8083, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	RIPEL(Position.fromDegrees(42.2831,-10.8162, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	RODAP(Position.fromDegrees(39.6324,-7.0653, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	ROSTA(Position.fromDegrees(28.2561,-20, 1e4), WaypointControl.GCCC, WaypointType.RNAV),
	RUBEO(Position.fromDegrees(40.9541,-0.6865, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	SADUR(Position.fromDegrees(41.4171,1.6739, 1e4), WaypointControl.LECB, WaypointType.RNAV),
	SALAS(Position.fromDegrees(41.1097,0.4764, 1e4), WaypointControl.LECB, WaypointType.RNAV),
	SARAY(Position.fromDegrees(29.7586,-14.1575, 1e4), WaypointControl.GCCC, WaypointType.RNAV),
	SEGRE(Position.fromDegrees(41.0228,-2.3765, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	SELVA(Position.fromDegrees(41.1962,1.1663, 1e4), WaypointControl.LECB, WaypointType.RNAV),
	SEROX(Position.fromDegrees(41.3467,0.2187, 1e4), WaypointControl.LECB, WaypointType.RNAV),
	SOLAX(Position.fromDegrees(39.2426,-4.9492, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	SORAS(Position.fromDegrees(40.5522,4.6667, 1e4), WaypointControl.LECB, WaypointType.RNAV),
	SOVAR(Position.fromDegrees(42.7881,-0.2445, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	SULID(Position.fromDegrees(41.1844,3.4414, 1e4), WaypointControl.LECB, WaypointType.RNAV),
	SUMMO(Position.fromDegrees(38.3603,-0.2818, 1e4), WaypointControl.LECB, WaypointType.RNAV),
	SURCO(Position.fromDegrees(42.3288,-0.5679, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	TEDRI(Position.fromDegrees(43.5053,-5.2269, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	TENPA(Position.fromDegrees(21.3617,-21.9733, 1e4), WaypointControl.GCCC, WaypointType.RNAV),
	USOTI(Position.fromDegrees(23.0668,-20.8364, 1e4), WaypointControl.GCCC, WaypointType.RNAV),
	VASTO(Position.fromDegrees(30.5094,-13.5728, 1e4), WaypointControl.GCCC, WaypointType.RNAV),
	VASUM(Position.fromDegrees(42.2719,-2.011, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	VOLTO(Position.fromDegrees(39.4491,2.2476, 1e4), WaypointControl.LECV, WaypointType.RNAV),
	WALLY(Position.fromDegrees(39.7543,-1.0943, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	XERES(Position.fromDegrees(42.0239,-10.068, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	ZANKO(Position.fromDegrees(41.2881,-4.9646, 1e4), WaypointControl.LECM, WaypointType.RNAV),
	ZARKO(Position.fromDegrees(42.0049,-0.7928, 1e4), WaypointControl.LECB, WaypointType.RNAV),
	ZORBA(Position.fromDegrees(40.1881,-5.3939, 1e4), WaypointControl.LECM, WaypointType.RNAV);


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
