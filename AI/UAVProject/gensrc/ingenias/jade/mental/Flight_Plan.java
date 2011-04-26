

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
   
    double CruisingSpeedKMH;   
   
    enums.Airport DepartureAirport;   
   
    Date DepartureTime;   
   
    double CruisingAltitudeKM;   
   
    enums.Airport DestinationAirport;   
   
    Date EstimatedTimeEnrout;   
   
    List<gov.nasa.worldwind.geom.Position> AlternateAirports;   
   
    List<gov.nasa.worldwind.geom.Position> Waypoints;   
    
   
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
   

   public void setCruisingSpeedKMH(double value){
     CruisingSpeedKMH=value;   
   };
   
   public double getCruisingSpeedKMH(){
     return CruisingSpeedKMH;      
   }
   

   public void setDepartureAirport(enums.Airport value){
     DepartureAirport=value;   
   };
   
   public enums.Airport getDepartureAirport(){
     return DepartureAirport;      
   }
   

   public void setDepartureTime(Date value){
     DepartureTime=value;   
   };
   
   public Date getDepartureTime(){
     return DepartureTime;      
   }
   

   public void setCruisingAltitudeKM(double value){
     CruisingAltitudeKM=value;   
   };
   
   public double getCruisingAltitudeKM(){
     return CruisingAltitudeKM;      
   }
   

   public void setDestinationAirport(enums.Airport value){
     DestinationAirport=value;   
   };
   
   public enums.Airport getDestinationAirport(){
     return DestinationAirport;      
   }
   

   public void setEstimatedTimeEnrout(Date value){
     EstimatedTimeEnrout=value;   
   };
   
   public Date getEstimatedTimeEnrout(){
     return EstimatedTimeEnrout;      
   }
   

   public void setAlternateAirports(List<gov.nasa.worldwind.geom.Position> value){
     AlternateAirports=value;   
   };
   
   public List<gov.nasa.worldwind.geom.Position> getAlternateAirports(){
     return AlternateAirports;      
   }
   

   public void setWaypoints(List<gov.nasa.worldwind.geom.Position> value){
     Waypoints=value;   
   };
   
   public List<gov.nasa.worldwind.geom.Position> getWaypoints(){
     return Waypoints;      
   }
     
    
}

 