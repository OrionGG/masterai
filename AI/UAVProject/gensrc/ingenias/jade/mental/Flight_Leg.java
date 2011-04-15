

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

public class Flight_Leg extends ingenias.editor.entities.RuntimeFact{
   
    gov.nasa.worldwind.geom.Position StartPoint;   
   
    gov.nasa.worldwind.geom.Position EndPont;   
   
    float Altitude;   
   
    float Speed;   
   
    Boolean Maked;   
    
   
  public Flight_Leg (String id){
   super(id);
   this.getPrefs().setView(ViewType.UML);
   this.type="Flight_Leg";
  }
  

  public Flight_Leg (){
   super(ingenias.jade.MentalStateManager.generateMentalEntityID());
   this.getPrefs().setView(ViewType.UML);
  }
  
  public String toString(){
   return this.getId()+":"+this.getType();
  }
  
  public String getType(){
   return "Flight_Leg";
  }
  
  public String getParentType(){
   return "RuntimeFact";
  }
  
   

   public void setStartPoint(gov.nasa.worldwind.geom.Position value){
     StartPoint=value;   
   };
   
   public gov.nasa.worldwind.geom.Position getStartPoint(){
     return StartPoint;      
   }
   

   public void setEndPont(gov.nasa.worldwind.geom.Position value){
     EndPont=value;   
   };
   
   public gov.nasa.worldwind.geom.Position getEndPont(){
     return EndPont;      
   }
   

   public void setAltitude(float value){
     Altitude=value;   
   };
   
   public float getAltitude(){
     return Altitude;      
   }
   

   public void setSpeed(float value){
     Speed=value;   
   };
   
   public float getSpeed(){
     return Speed;      
   }
   

   public void setMaked(Boolean value){
     Maked=value;   
   };
   
   public Boolean getMaked(){
     return Maked;      
   }
     
    
}

 