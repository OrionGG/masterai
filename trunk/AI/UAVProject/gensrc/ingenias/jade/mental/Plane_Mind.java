

/*
    Copyright (C) 2005 Jorge Gomez Sanz

    This file is part of INGENIAS Agent Framework, an agent infrastructure linked
    to the INGENIAS Development Kit, and availabe at http://grasia.fdi.ucm.es/ingenias or
    http://ingenias.sourceforge.net. 

    INGENIAS Agent Framework is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    INGENIAS Agent Framework is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with INGENIAS Agent Framework; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

*/


package ingenias.jade.mental;

import java.util.*;
import ingenias.jade.components.*;
import ingenias.editor.entities.*;
import ingenias.editor.entities.ViewPreferences.ViewType;

public class Plane_Mind extends ingenias.editor.entities.RuntimeFact{
   
    float FuelLiters_;   
   
    double Altitude;   
   
    gov.nasa.worldwind.geom.Angle Latitude;   
   
    gov.nasa.worldwind.geom.Angle Longitude;   
   
    double Speed;   
   
    List<Manoeuvre> AchievableManeuvers;   
   
    gov.nasa.worldwind.geom.Angle Head;   
   
    Date LastUpdate;   
    
   
  public Plane_Mind (String id){
   super(id);
   this.getPrefs().setView(ViewType.UML);
   this.type="Plane_Mind";
  }
  

  public Plane_Mind (){
   super(ingenias.jade.MentalStateManager.generateMentalEntityID());
   this.getPrefs().setView(ViewType.UML);
  }
  
  public String toString(){
   return this.getId()+":"+this.getType();
  }
  
  public String getType(){
   return "Plane_Mind";
  }
  
  public String getParentType(){
   return "RuntimeFact";
  }
  
   

   public void setFuelLiters_(float value){
     FuelLiters_=value;   
   };
   
   public float getFuelLiters_(){
     return FuelLiters_;      
   }
   

   public void setAltitude(double value){
     Altitude=value;   
   };
   
   public double getAltitude(){
     return Altitude;      
   }
   

   public void setLatitude(gov.nasa.worldwind.geom.Angle value){
     Latitude=value;   
   };
   
   public gov.nasa.worldwind.geom.Angle getLatitude(){
     return Latitude;      
   }
   

   public void setLongitude(gov.nasa.worldwind.geom.Angle value){
     Longitude=value;   
   };
   
   public gov.nasa.worldwind.geom.Angle getLongitude(){
     return Longitude;      
   }
   

   public void setSpeed(double value){
     Speed=value;   
   };
   
   public double getSpeed(){
     return Speed;      
   }
   

   public void setAchievableManeuvers(List<Manoeuvre> value){
     AchievableManeuvers=value;   
   };
   
   public List<Manoeuvre> getAchievableManeuvers(){
     return AchievableManeuvers;      
   }
   

   public void setHead(gov.nasa.worldwind.geom.Angle value){
     Head=value;   
   };
   
   public gov.nasa.worldwind.geom.Angle getHead(){
     return Head;      
   }
   

   public void setLastUpdate(Date value){
     LastUpdate=value;   
   };
   
   public Date getLastUpdate(){
     return LastUpdate;      
   }
     
    
}

 