

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

public class OrderPositionAndLeg extends ingenias.editor.entities.RuntimeFact{
   
    Flight_Leg FlightLeg;   
   
    gov.nasa.worldwind.geom.Position OrderPosition;   
    
   
  public OrderPositionAndLeg (String id){
   super(id);
   this.getPrefs().setView(ViewType.UML);
   this.type="OrderPositionAndLeg";
  }
  

  public OrderPositionAndLeg (){
   super(ingenias.jade.MentalStateManager.generateMentalEntityID());
   this.getPrefs().setView(ViewType.UML);
  }
  
  public String toString(){
   return this.getId()+":"+this.getType();
  }
  
  public String getType(){
   return "OrderPositionAndLeg";
  }
  
  public String getParentType(){
   return "RuntimeFact";
  }
  
   

   public void setFlightLeg(Flight_Leg value){
     FlightLeg=value;   
   };
   
   public Flight_Leg getFlightLeg(){
     return FlightLeg;      
   }
   

   public void setOrderPosition(gov.nasa.worldwind.geom.Position value){
     OrderPosition=value;   
   };
   
   public gov.nasa.worldwind.geom.Position getOrderPosition(){
     return OrderPosition;      
   }
     
    
}

 