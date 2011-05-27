package thread;

import java.util.Vector;

import ingenias.editor.entities.MentalEntity;
import ingenias.exception.InvalidEntity;
import ingenias.jade.agents.PlaneJADEAgent;
import ingenias.jade.components.UpdatePlaneStatusApp;
import ingenias.jade.mental.Manoeuvre;
import ingenias.jade.mental.Plane_Mind;

public class RunManoeuvre{
	Manoeuvre eiManoeuvre;
	Plane_Mind eiPlane_Mind;
	UpdatePlaneStatusApp oUpdatePlaneStatusApp;
	
	
	private RunManoeuvre(Manoeuvre eiManoeuvre,
			Plane_Mind eiPlane_Mind, UpdatePlaneStatusApp oUpdatePlaneStatusApp) {
		super();
		this.eiManoeuvre = eiManoeuvre;
		this.eiPlane_Mind = eiPlane_Mind;
		this.oUpdatePlaneStatusApp = oUpdatePlaneStatusApp;
	}
	
	public static boolean runManoeuvre(Manoeuvre eiManoeuvre,
			Plane_Mind eiPlane_Mind, PlaneJADEAgent oPlaneJADEAgent) {

		
		ingenias.jade.mental.Change_Plane_Status event=new ingenias.jade.mental.Change_Plane_Status();
		event.setNewAltitudeKM(-1);
		event.setNewHead(null);
		event.setNewLatLonPosition(null);
		
		if(eiManoeuvre.getSpeedChange()!= -1){
			double dFinalSpeedKMH = eiPlane_Mind.getSpeedKMH() + eiManoeuvre.getSpeedChange();
			if(dFinalSpeedKMH < 0){
				eiPlane_Mind.setSpeedKMH(0);
			}
			else{
				eiPlane_Mind.setSpeedKMH(eiPlane_Mind.getSpeedKMH() + eiManoeuvre.getSpeedChange());
			}
			
        }if(eiManoeuvre.getAltitudeChange()!= -1){
        	double dNewAltitudeKM = eiPlane_Mind.getAltitudeKM() + ((double) eiManoeuvre.getAltitudeChange()/1000);
        	//event.setNewAltitudeKM(dNewAltitudeKM);
        	if(dNewAltitudeKM < 0){
        		eiPlane_Mind.setAltitudeKM(0);
        	}
        	else{
        		eiPlane_Mind.setAltitudeKM(dNewAltitudeKM);
        	}
        }
        if(eiManoeuvre.getHeadChange()!= null){
        	gov.nasa.worldwind.geom.Angle oNewAngle = eiPlane_Mind.getHead().add(eiManoeuvre.getHeadChange());
        	oNewAngle = gov.nasa.worldwind.geom.Angle.fromDegrees((oNewAngle.degrees+360)%360);
        	//event.setNewHead(oNewAngle);
        	eiPlane_Mind.setHead(oNewAngle);
        	
        }
        

        /*try {
			oPlaneJADEAgent.getMSM().addMentalEntity(event);
		} catch (InvalidEntity e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
			
		return true;
		
	}
}
