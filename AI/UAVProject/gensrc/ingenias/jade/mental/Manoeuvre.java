

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

public class Manoeuvre extends ingenias.editor.entities.RuntimeFact{
   
    gov.nasa.worldwind.geom.Angle HeadChange;   
   
    double SpeedChange;   
   
    double AltitudeChange;   
   
    int Priority;   
   
    Throw_Instruction ThrowInstruction;   
    
   
  public Manoeuvre (String id){
   super(id);
   this.getPrefs().setView(ViewType.UML);
   this.type="Manoeuvre";
  }
  

  public Manoeuvre (){
   super(ingenias.jade.MentalStateManager.generateMentalEntityID());
   this.getPrefs().setView(ViewType.UML);
  }
  
  public String toString(){
   return this.getId()+":"+this.getType();
  }
  
  public String getType(){
   return "Manoeuvre";
  }
  
  public String getParentType(){
   return "RuntimeFact";
  }
  
   

   public void setHeadChange(gov.nasa.worldwind.geom.Angle value){
     HeadChange=value;   
   };
   
   public gov.nasa.worldwind.geom.Angle getHeadChange(){
     return HeadChange;      
   }
   

   public void setSpeedChange(double value){
     SpeedChange=value;   
   };
   
   public double getSpeedChange(){
     return SpeedChange;      
   }
   

   public void setAltitudeChange(double value){
     AltitudeChange=value;   
   };
   
   public double getAltitudeChange(){
     return AltitudeChange;      
   }
   

   public void setPriority(int value){
     Priority=value;   
   };
   
   public int getPriority(){
     return Priority;      
   }
   

   public void setThrowInstruction(Throw_Instruction value){
     ThrowInstruction=value;   
   };
   
   public Throw_Instruction getThrowInstruction(){
     return ThrowInstruction;      
   }
     
    
}

 