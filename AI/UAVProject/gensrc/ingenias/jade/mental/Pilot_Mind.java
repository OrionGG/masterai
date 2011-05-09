

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

public class Pilot_Mind extends ingenias.editor.entities.RuntimeFact{
   
    Flight_Plan PilotFlightPlan;   
   
    ArrayList<Manoeuvre> AvailableManeuvers;   
   
    float Experience;   
   
    String TypeOfPilot;   
   
    float Fatigue;   
   
    float Stress;   
   
    int LegsCompleted;   
   
    Throw_Instruction InstructionRunning;   
    
   
  public Pilot_Mind (String id){
   super(id);
   this.getPrefs().setView(ViewType.UML);
   this.type="Pilot_Mind";
  }
  

  public Pilot_Mind (){
   super(ingenias.jade.MentalStateManager.generateMentalEntityID());
   this.getPrefs().setView(ViewType.UML);
  }
  
  public String toString(){
   return this.getId()+":"+this.getType();
  }
  
  public String getType(){
   return "Pilot_Mind";
  }
  
  public String getParentType(){
   return "RuntimeFact";
  }
  
   

   public void setPilotFlightPlan(Flight_Plan value){
     PilotFlightPlan=value;   
   };
   
   public Flight_Plan getPilotFlightPlan(){
     return PilotFlightPlan;      
   }
   

   public void setAvailableManeuvers(ArrayList<Manoeuvre> value){
     AvailableManeuvers=value;   
   };
   
   public ArrayList<Manoeuvre> getAvailableManeuvers(){
     return AvailableManeuvers;      
   }
   

   public void setExperience(float value){
     Experience=value;   
   };
   
   public float getExperience(){
     return Experience;      
   }
   

   public void setTypeOfPilot(String value){
     TypeOfPilot=value;   
   };
   
   public String getTypeOfPilot(){
     return TypeOfPilot;      
   }
   

   public void setFatigue(float value){
     Fatigue=value;   
   };
   
   public float getFatigue(){
     return Fatigue;      
   }
   

   public void setStress(float value){
     Stress=value;   
   };
   
   public float getStress(){
     return Stress;      
   }
   

   public void setLegsCompleted(int value){
     LegsCompleted=value;   
   };
   
   public int getLegsCompleted(){
     return LegsCompleted;      
   }
   

   public void setInstructionRunning(Throw_Instruction value){
     InstructionRunning=value;   
   };
   
   public Throw_Instruction getInstructionRunning(){
     return InstructionRunning;      
   }
     
    
}

 