

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

public class ObjectDetected extends ingenias.editor.entities.RuntimeFact{
   
    float height;   
   
    float lenght;   
   
    float width;   
   
    float direction;   
   
    float velocity;   
    
   
  public ObjectDetected (String id){
   super(id);
   this.getPrefs().setView(ViewType.UML);
   this.type="ObjectDetected";
  }
  

  public ObjectDetected (){
   super(ingenias.jade.MentalStateManager.generateMentalEntityID());
   this.getPrefs().setView(ViewType.UML);
  }
  
  public String toString(){
   return this.getId()+":"+this.getType();
  }
  
  public String getType(){
   return "ObjectDetected";
  }
  
  public String getParentType(){
   return "RuntimeFact";
  }
  
   

   public void setheight(float value){
     height=value;   
   };
   
   public float getheight(){
     return height;      
   }
   

   public void setlenght(float value){
     lenght=value;   
   };
   
   public float getlenght(){
     return lenght;      
   }
   

   public void setwidth(float value){
     width=value;   
   };
   
   public float getwidth(){
     return width;      
   }
   

   public void setdirection(float value){
     direction=value;   
   };
   
   public float getdirection(){
     return direction;      
   }
   

   public void setvelocity(float value){
     velocity=value;   
   };
   
   public float getvelocity(){
     return velocity;      
   }
     
    
}

 