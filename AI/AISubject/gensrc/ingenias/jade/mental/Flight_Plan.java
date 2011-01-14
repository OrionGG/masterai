

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

public class Flight_Plan extends ingenias.editor.entities.RuntimeFact{
   
    float FuelCalculation;   
   
    float CruisingSpeed;   
   
    Position DeparturePoint;   
   
    Date DepartureTime;   
   
    float CruisingAltitude;   
   
    Position DestinationPoint;   
   
    Date EstimatedTimeEnrout;   
   
    ArrayList<Position> AlternateAirports;   
   
    ArrayList<Position> Waypoints;   
    
   
  public Flight_Plan (String id){
   super(id);
   this.getPrefs().setView(ViewType.UML);
   this.type="Flight_Plan";
  }
  

  public Flight_Plan (){
   super(ingenias.jade.MentalStateManager.generateMentalEntityID());
   this.getPrefs().setView(ViewType.UML);
  }
  
  public String toString(){
   return this.getId()+":"+this.getType();
  }
  
  public String getType(){
   return "Flight_Plan";
  }
  
  public String getParentType(){
   return "RuntimeFact";
  }
  
   

   public void setFuelCalculation(float value){
     FuelCalculation=value;   
   };
   
   public float getFuelCalculation(){
     return FuelCalculation;      
   }
   

   public void setCruisingSpeed(float value){
     CruisingSpeed=value;   
   };
   
   public float getCruisingSpeed(){
     return CruisingSpeed;      
   }
   

   public void setDeparturePoint(Position value){
     DeparturePoint=value;   
   };
   
   public Position getDeparturePoint(){
     return DeparturePoint;      
   }
   

   public void setDepartureTime(Date value){
     DepartureTime=value;   
   };
   
   public Date getDepartureTime(){
     return DepartureTime;      
   }
   

   public void setCruisingAltitude(float value){
     CruisingAltitude=value;   
   };
   
   public float getCruisingAltitude(){
     return CruisingAltitude;      
   }
   

   public void setDestinationPoint(Position value){
     DestinationPoint=value;   
   };
   
   public Position getDestinationPoint(){
     return DestinationPoint;      
   }
   

   public void setEstimatedTimeEnrout(Date value){
     EstimatedTimeEnrout=value;   
   };
   
   public Date getEstimatedTimeEnrout(){
     return EstimatedTimeEnrout;      
   }
   

   public void setAlternateAirports(ArrayList<Position> value){
     AlternateAirports=value;   
   };
   
   public ArrayList<Position> getAlternateAirports(){
     return AlternateAirports;      
   }
   

   public void setWaypoints(ArrayList<Position> value){
     Waypoints=value;   
   };
   
   public ArrayList<Position> getWaypoints(){
     return Waypoints;      
   }
     
    
}

 