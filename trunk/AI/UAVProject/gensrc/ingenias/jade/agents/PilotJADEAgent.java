
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


package ingenias.jade.agents;


import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.core.behaviours.*;
import ingenias.jade.*;
import ingenias.jade.smachines.*;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;

import jade.core.*;
import jade.core.behaviours.*;

import jade.domain.FIPAAgentManagement.*;
import jade.domain.FIPAException;
import jade.domain.DFService;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import ingenias.jade.*;
import java.util.*;
import ingenias.jade.exception.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import ingenias.editor.entities.RuntimeFact;
import ingenias.jade.components.*;
import ingenias.jade.mental.*;
import ingenias.editor.entities.ApplicationEventSlots;
import ingenias.editor.entities.Interaction;
import ingenias.editor.entities.MentalEntity;
import ingenias.editor.entities.ObjectSlot;
import ingenias.editor.entities.RuntimeEvent;
import ingenias.editor.entities.RuntimeFact;
import ingenias.editor.entities.RuntimeConversation;
import ingenias.editor.entities.Slot;

import ingenias.jade.components.Task;
import ingenias.jade.graphics.*;
import ingenias.jade.MentalStateManager;
import ingenias.exception.InvalidEntity;

public class PilotJADEAgent
		 extends JADEAgent {         
 
		 public PilotJADEAgent(){
		 super(new PilotProtocol(),new PilotInteractionLocks());
		 }

	private boolean initialiseNonConversationalTask(Task tobject) {
	   boolean initialised=false;
	   Vector<String> repeatedOutputs=new Vector<String>();
	    Vector<String> nonExistingInputs=new Vector<String>();
	    
	    if (tobject.getType().equals("DeleteNonUsedEntitiesTask")){
            Vector<MentalEntity> expectedInput=null;
            RuntimeFact expectedOutput=null;
            RuntimeEvent expectedOutputEvent=null;
			RuntimeConversation expectedInt=null;
            ingenias.jade.components.Resource expectedResource=null;
			ingenias.jade.components.Application expectedApp=null;        
			boolean allEntitiesExist=false;		
			TaskOutput to=null;
			to=new TaskOutput("default");
  
		
		expectedInput=this.getMSM().getMentalEntityByType("PlanReceived");
		if (this.getLM().canBeDeleted(expectedInput)){             
             if (expectedInput.size()==0){
				nonExistingInputs.add("PlanReceived");
			 } else {
			    addExpectedInputs(tobject, "PlanReceived","1",expectedInput);
             	addConsumedInput(to,"PlanReceived",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist || expectedInput.size()!=0;
		} 
	      
		expectedInput=this.getMSM().getMentalEntityByType("ObjectDetected");
		if (this.getLM().canBeDeleted(expectedInput)){             
             if (expectedInput.size()==0){
				nonExistingInputs.add("ObjectDetected");
			 } else {
			    addExpectedInputs(tobject, "ObjectDetected","1",expectedInput);
             	addConsumedInput(to,"ObjectDetected",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist || expectedInput.size()!=0;
		} 
	      
		expectedInput=this.getMSM().getMentalEntityByType("Manoeuvre");
		if (this.getLM().canBeDeleted(expectedInput)){             
             if (expectedInput.size()==0){
				nonExistingInputs.add("Manoeuvre");
			 } else {
			    addExpectedInputs(tobject, "Manoeuvre","1",expectedInput);
             	addConsumedInput(to,"Manoeuvre",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist || expectedInput.size()!=0;
		} 
	      
		expectedInput=this.getMSM().getMentalEntityByType("Increase_Speed");
		if (this.getLM().canBeDeleted(expectedInput)){             
             if (expectedInput.size()==0){
				nonExistingInputs.add("Increase_Speed");
			 } else {
			    addExpectedInputs(tobject, "Increase_Speed","1",expectedInput);
             	addConsumedInput(to,"Increase_Speed",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist || expectedInput.size()!=0;
		} 
	      
		expectedInput=this.getMSM().getMentalEntityByType("Increase_Altitude");
		if (this.getLM().canBeDeleted(expectedInput)){             
             if (expectedInput.size()==0){
				nonExistingInputs.add("Increase_Altitude");
			 } else {
			    addExpectedInputs(tobject, "Increase_Altitude","1",expectedInput);
             	addConsumedInput(to,"Increase_Altitude",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist || expectedInput.size()!=0;
		} 
	      
		expectedInput=this.getMSM().getMentalEntityByType("Decrease_Speed");
		if (this.getLM().canBeDeleted(expectedInput)){             
             if (expectedInput.size()==0){
				nonExistingInputs.add("Decrease_Speed");
			 } else {
			    addExpectedInputs(tobject, "Decrease_Speed","1",expectedInput);
             	addConsumedInput(to,"Decrease_Speed",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist || expectedInput.size()!=0;
		} 
	      
		expectedInput=this.getMSM().getMentalEntityByType("Decrease_Altitude");
		if (this.getLM().canBeDeleted(expectedInput)){             
             if (expectedInput.size()==0){
				nonExistingInputs.add("Decrease_Altitude");
			 } else {
			    addExpectedInputs(tobject, "Decrease_Altitude","1",expectedInput);
             	addConsumedInput(to,"Decrease_Altitude",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist || expectedInput.size()!=0;
		} 
	      
		expectedInput=this.getMSM().getMentalEntityByType("Decrease_degrees");
		if (this.getLM().canBeDeleted(expectedInput)){             
             if (expectedInput.size()==0){
				nonExistingInputs.add("Decrease_degrees");
			 } else {
			    addExpectedInputs(tobject, "Decrease_degrees","1",expectedInput);
             	addConsumedInput(to,"Decrease_degrees",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist || expectedInput.size()!=0;
		} 
	      
		expectedInput=this.getMSM().getMentalEntityByType("TurningOnPlane");
		if (this.getLM().canBeDeleted(expectedInput)){             
             if (expectedInput.size()==0){
				nonExistingInputs.add("TurningOnPlane");
			 } else {
			    addExpectedInputs(tobject, "TurningOnPlane","1",expectedInput);
             	addConsumedInput(to,"TurningOnPlane",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist || expectedInput.size()!=0;
		} 
	      
		expectedInput=this.getMSM().getMentalEntityByType("Increase_degrees");
		if (this.getLM().canBeDeleted(expectedInput)){             
             if (expectedInput.size()==0){
				nonExistingInputs.add("Increase_degrees");
			 } else {
			    addExpectedInputs(tobject, "Increase_degrees","1",expectedInput);
             	addConsumedInput(to,"Increase_degrees",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist || expectedInput.size()!=0;
		} 
	      
		expectedInput=this.getMSM().getMentalEntityByType("OrdenDone");
		if (this.getLM().canBeDeleted(expectedInput)){             
             if (expectedInput.size()==0){
				nonExistingInputs.add("OrdenDone");
			 } else {
			    addExpectedInputs(tobject, "OrdenDone","1",expectedInput);
             	addConsumedInput(to,"OrdenDone",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist || expectedInput.size()!=0;
		} 
	      
		 tobject.addOutput(to);
     		   	
	      initialised= allEntitiesExist;

		  if (!allEntitiesExist){
			StringBuffer nonexisting=new StringBuffer();
			for (int j=0;j<nonExistingInputs.size();j++){
				nonexisting.append(nonExistingInputs.elementAt(j).toString()+",");
			}
   		  }
		  return initialised;
	    }
	
   	    
    	         
                  
         nonExistingInputs.clear();
  		 repeatedOutputs.clear();
         if (tobject.getType().equals("Stop_Plane") ){
            Vector<MentalEntity> expectedInput=null;
            RuntimeFact expectedOutput=null;
            RuntimeEvent expectedOutputEvent=null;
			RuntimeConversation expectedInt=null;
            ingenias.jade.components.Resource expectedResource=null;
			ingenias.jade.components.Application expectedApp=null;        
			boolean allEntitiesExist=true;		
			TaskOutput to=null;
			to=new TaskOutput("default");
		
             expectedInput=this.getMSM().getMentalEntityByType("Pilot_Mind");
             if (expectedInput.size()==0 && !("1".equals("0..n"))){
				nonExistingInputs.add("Pilot_Mind");
			 } else {
			  addExpectedInputs(tobject, "Pilot_Mind","1",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;             
             
	      
	      
		
		
             expectedInput=this.getMSM().getMentalEntityByType("AllLegsCompleted");
             if (expectedInput.size()==0 && !("1".equals("0..n"))){
				nonExistingInputs.add("AllLegsCompleted");
			 } else {
			    addExpectedInputs(tobject, "AllLegsCompleted","1",expectedInput);
             	addConsumedInput(to,"1",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;

	      
	      
	     
	      
	     // Default application for all tasks executed within a conversation
	     expectedApp=(ingenias.jade.components.Application)getAM().getApplication("YellowPages");
             tobject.addApplication("YellowPages",expectedApp);
	 /*    
	     
	      */	  
	      
	      /**/	      
	      
 
	     
	     
		    {Throw_Instruction expectedOutputThrow_Instruction=		    
		     new Throw_Instruction(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputThrow_Instruction,TaskOperations.CreateWF));
            }
	     
     
     		      
	      tobject.addOutput(to);
	      initialised= allEntitiesExist;

		if (!allEntitiesExist){
		   String[] nonexisting=new String[nonExistingInputs.size()];
		   for (int j=0;j<nonExistingInputs.size();j++){
				nonexisting[j]=nonExistingInputs.elementAt(j).toString();
			}
			EventManager.getInstance().conversationalInitializationOfTaskFailed(getLocalName(), 
												"Pilot", 
												tobject, nonexisting);
		}
		return initialised;	       
	      }
                 
                  
                  
         nonExistingInputs.clear();
  		 repeatedOutputs.clear();
         if (tobject.getType().equals("DescomposeDecision") ){
            Vector<MentalEntity> expectedInput=null;
            RuntimeFact expectedOutput=null;
            RuntimeEvent expectedOutputEvent=null;
			RuntimeConversation expectedInt=null;
            ingenias.jade.components.Resource expectedResource=null;
			ingenias.jade.components.Application expectedApp=null;        
			boolean allEntitiesExist=true;		
			TaskOutput to=null;
			to=new TaskOutput("default");
		
             expectedInput=this.getMSM().getMentalEntityByType("Pilot_Mind");
             if (expectedInput.size()==0 && !("1".equals("0..n"))){
				nonExistingInputs.add("Pilot_Mind");
			 } else {
			  addExpectedInputs(tobject, "Pilot_Mind","1",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;             
             
	      
	      
		
		
             expectedInput=this.getMSM().getMentalEntityByType("Decision");
             if (expectedInput.size()==0 && !("1".equals("0..n"))){
				nonExistingInputs.add("Decision");
			 } else {
			    addExpectedInputs(tobject, "Decision","1",expectedInput);
             	addConsumedInput(to,"1",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;

	      
	      
	     
	      
	     // Default application for all tasks executed within a conversation
	     expectedApp=(ingenias.jade.components.Application)getAM().getApplication("YellowPages");
             tobject.addApplication("YellowPages",expectedApp);
	 /*    
	     
	      */	  
	      
	      /**/	      
	      
 
	     
	     
		    {Throw_Instruction expectedOutputThrow_Instruction=		    
		     new Throw_Instruction(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputThrow_Instruction,TaskOperations.CreateWF));
            }
	     
		    {StartThinkNewDecision expectedOutputStartThinkNewDecision=		    
		     new StartThinkNewDecision(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputStartThinkNewDecision,TaskOperations.CreateWF));
            }
	     
     
     		      
	      tobject.addOutput(to);
	      initialised= allEntitiesExist;

		if (!allEntitiesExist){
		   String[] nonexisting=new String[nonExistingInputs.size()];
		   for (int j=0;j<nonExistingInputs.size();j++){
				nonexisting[j]=nonExistingInputs.elementAt(j).toString();
			}
			EventManager.getInstance().conversationalInitializationOfTaskFailed(getLocalName(), 
												"Pilot", 
												tobject, nonexisting);
		}
		return initialised;	       
	      }
                 
                  
                  
         nonExistingInputs.clear();
  		 repeatedOutputs.clear();
         if (tobject.getType().equals("GoingNextLegWithConflict") ){
            Vector<MentalEntity> expectedInput=null;
            RuntimeFact expectedOutput=null;
            RuntimeEvent expectedOutputEvent=null;
			RuntimeConversation expectedInt=null;
            ingenias.jade.components.Resource expectedResource=null;
			ingenias.jade.components.Application expectedApp=null;        
			boolean allEntitiesExist=true;		
			TaskOutput to=null;
			to=new TaskOutput("default");
		
             expectedInput=this.getMSM().getMentalEntityByType("OrderOldLeg");
             if (expectedInput.size()==0 && !("1".equals("0..n"))){
				nonExistingInputs.add("OrderOldLeg");
			 } else {
			  addExpectedInputs(tobject, "OrderOldLeg","1",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;             
             
	      
             expectedInput=this.getMSM().getMentalEntityByType("Pilot_Mind");
             if (expectedInput.size()==0 && !("1".equals("0..n"))){
				nonExistingInputs.add("Pilot_Mind");
			 } else {
			  addExpectedInputs(tobject, "Pilot_Mind","1",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;             
             
	      
	      
		
		
             expectedInput=this.getMSM().getMentalEntityByType("GoNextLegWithConflict");
             if (expectedInput.size()==0 && !("1".equals("0..n"))){
				nonExistingInputs.add("GoNextLegWithConflict");
			 } else {
			    addExpectedInputs(tobject, "GoNextLegWithConflict","1",expectedInput);
             	addConsumedInput(to,"1",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;

	      
	      
	     
	      
	     // Default application for all tasks executed within a conversation
	     expectedApp=(ingenias.jade.components.Application)getAM().getApplication("YellowPages");
             tobject.addApplication("YellowPages",expectedApp);
	 /*    
	     
	      */	  
	      
	      /**/	      
	      
 
	     
	     
		    {Flight_Leg expectedOutputFlight_Leg=		    
		     new Flight_Leg(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputFlight_Leg,TaskOperations.CreateWF));
            }
	     
		    {StartLegChecker expectedOutputStartLegChecker=		    
		     new StartLegChecker(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputStartLegChecker,TaskOperations.CreateWF));
            }
	     
     
     		      
	      tobject.addOutput(to);
	      initialised= allEntitiesExist;

		if (!allEntitiesExist){
		   String[] nonexisting=new String[nonExistingInputs.size()];
		   for (int j=0;j<nonExistingInputs.size();j++){
				nonexisting[j]=nonExistingInputs.elementAt(j).toString();
			}
			EventManager.getInstance().conversationalInitializationOfTaskFailed(getLocalName(), 
												"Pilot", 
												tobject, nonexisting);
		}
		return initialised;	       
	      }
                 
                  
                  
         nonExistingInputs.clear();
  		 repeatedOutputs.clear();
         if (tobject.getType().equals("Flight_Plan_Monitoring") ){
            Vector<MentalEntity> expectedInput=null;
            RuntimeFact expectedOutput=null;
            RuntimeEvent expectedOutputEvent=null;
			RuntimeConversation expectedInt=null;
            ingenias.jade.components.Resource expectedResource=null;
			ingenias.jade.components.Application expectedApp=null;        
			boolean allEntitiesExist=true;		
			TaskOutput to=null;
			to=new TaskOutput("default");
		
             expectedInput=this.getMSM().getMentalEntityByType("Pilot_Mind");
             if (expectedInput.size()==0 && !("1".equals("0..n"))){
				nonExistingInputs.add("Pilot_Mind");
			 } else {
			  addExpectedInputs(tobject, "Pilot_Mind","1",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;             
             
	      
	      
		
		
             expectedInput=this.getMSM().getMentalEntityByType("GetNextLeg");
             if (expectedInput.size()==0 && !("1".equals("0..n"))){
				nonExistingInputs.add("GetNextLeg");
			 } else {
			    addExpectedInputs(tobject, "GetNextLeg","1",expectedInput);
             	addConsumedInput(to,"1",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;

	      
	      
	     
	      
	     // Default application for all tasks executed within a conversation
	     expectedApp=(ingenias.jade.components.Application)getAM().getApplication("YellowPages");
             tobject.addApplication("YellowPages",expectedApp);
	 /*    
	     
	      */	  
	      
	      /**/	      
	      
 
	     
	     
		    {Flight_Leg expectedOutputFlight_Leg=		    
		     new Flight_Leg(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputFlight_Leg,TaskOperations.CreateWF));
            }
	     
		    {StartLegChecker expectedOutputStartLegChecker=		    
		     new StartLegChecker(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputStartLegChecker,TaskOperations.CreateWF));
            }
	     
     
     		      
	      tobject.addOutput(to);
	      initialised= allEntitiesExist;

		if (!allEntitiesExist){
		   String[] nonexisting=new String[nonExistingInputs.size()];
		   for (int j=0;j<nonExistingInputs.size();j++){
				nonexisting[j]=nonExistingInputs.elementAt(j).toString();
			}
			EventManager.getInstance().conversationalInitializationOfTaskFailed(getLocalName(), 
												"Pilot", 
												tobject, nonexisting);
		}
		return initialised;	       
	      }
                 
                  
                  
         nonExistingInputs.clear();
  		 repeatedOutputs.clear();
         if (tobject.getType().equals("IsTimeToStartPlane") ){
            Vector<MentalEntity> expectedInput=null;
            RuntimeFact expectedOutput=null;
            RuntimeEvent expectedOutputEvent=null;
			RuntimeConversation expectedInt=null;
            ingenias.jade.components.Resource expectedResource=null;
			ingenias.jade.components.Application expectedApp=null;        
			boolean allEntitiesExist=true;		
			TaskOutput to=null;
			to=new TaskOutput("default");
		
             expectedInput=this.getMSM().getMentalEntityByType("Pilot_Mind");
             if (expectedInput.size()==0 && !("1".equals("0..n"))){
				nonExistingInputs.add("Pilot_Mind");
			 } else {
			  addExpectedInputs(tobject, "Pilot_Mind","1",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;             
             
	      
	      
		
		
             expectedInput=this.getMSM().getMentalEntityByType("CanInitiateStartPlane");
             if (expectedInput.size()==0 && !("1".equals("0..n"))){
				nonExistingInputs.add("CanInitiateStartPlane");
			 } else {
			    addExpectedInputs(tobject, "CanInitiateStartPlane","1",expectedInput);
             	addConsumedInput(to,"1",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;

	      
	      
	     
	     expectedApp=(ingenias.jade.components.Application)getAM().getApplication("WhenStartPlane");
             tobject.addApplication("WhenStartPlane",expectedApp);
	      
	      
	     // Default application for all tasks executed within a conversation
	     expectedApp=(ingenias.jade.components.Application)getAM().getApplication("YellowPages");
             tobject.addApplication("YellowPages",expectedApp);
	 /*    
	     
	      */	  
	      
	      /**/	      
	      
 
	     
	     
     
     		      
	      tobject.addOutput(to);
	      initialised= allEntitiesExist;

		if (!allEntitiesExist){
		   String[] nonexisting=new String[nonExistingInputs.size()];
		   for (int j=0;j<nonExistingInputs.size();j++){
				nonexisting[j]=nonExistingInputs.elementAt(j).toString();
			}
			EventManager.getInstance().conversationalInitializationOfTaskFailed(getLocalName(), 
												"Pilot", 
												tobject, nonexisting);
		}
		return initialised;	       
	      }
                 
                  
                  
         nonExistingInputs.clear();
  		 repeatedOutputs.clear();
         if (tobject.getType().equals("CreatePilotMind") ){
            Vector<MentalEntity> expectedInput=null;
            RuntimeFact expectedOutput=null;
            RuntimeEvent expectedOutputEvent=null;
			RuntimeConversation expectedInt=null;
            ingenias.jade.components.Resource expectedResource=null;
			ingenias.jade.components.Application expectedApp=null;        
			boolean allEntitiesExist=true;		
			TaskOutput to=null;
			to=new TaskOutput("default");
		
	      
		
		
             expectedInput=this.getMSM().getMentalEntityByType("Flight_Plan");
             if (expectedInput.size()==0 && !("1".equals("0..n"))){
				nonExistingInputs.add("Flight_Plan");
			 } else {
			    addExpectedInputs(tobject, "Flight_Plan","1",expectedInput);
             	addConsumedInput(to,"1",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;

	      
	      
	     
	      
	     // Default application for all tasks executed within a conversation
	     expectedApp=(ingenias.jade.components.Application)getAM().getApplication("YellowPages");
             tobject.addApplication("YellowPages",expectedApp);
	 /*    
	     
	      */	  
	      
	      /**/	      
	      
 
	     
	     
		    {Pilot_Mind expectedOutputPilot_Mind=		    
		     new Pilot_Mind(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputPilot_Mind,TaskOperations.CreateWF));
            }
	     
     
     		      
	      tobject.addOutput(to);
	      initialised= allEntitiesExist;

		if (!allEntitiesExist){
		   String[] nonexisting=new String[nonExistingInputs.size()];
		   for (int j=0;j<nonExistingInputs.size();j++){
				nonexisting[j]=nonExistingInputs.elementAt(j).toString();
			}
			EventManager.getInstance().conversationalInitializationOfTaskFailed(getLocalName(), 
												"Pilot", 
												tobject, nonexisting);
		}
		return initialised;	       
	      }
                 
                  
                  
         nonExistingInputs.clear();
  		 repeatedOutputs.clear();
         if (tobject.getType().equals("CreateNewLegForOrder") ){
            Vector<MentalEntity> expectedInput=null;
            RuntimeFact expectedOutput=null;
            RuntimeEvent expectedOutputEvent=null;
			RuntimeConversation expectedInt=null;
            ingenias.jade.components.Resource expectedResource=null;
			ingenias.jade.components.Application expectedApp=null;        
			boolean allEntitiesExist=true;		
			TaskOutput to=null;
			to=new TaskOutput("default");
		
	      
		
		
             expectedInput=this.getMSM().getMentalEntityByType("OrderNewLeg");
             if (expectedInput.size()==0 && !("1".equals("0..n"))){
				nonExistingInputs.add("OrderNewLeg");
			 } else {
			    addExpectedInputs(tobject, "OrderNewLeg","1",expectedInput);
             	addConsumedInput(to,"1",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;

	      
	      
	     
	      
	     // Default application for all tasks executed within a conversation
	     expectedApp=(ingenias.jade.components.Application)getAM().getApplication("YellowPages");
             tobject.addApplication("YellowPages",expectedApp);
	 /*    
	     
	      */	  
	      
	      /**/	      
	      
 
	     
	     
		    {Flight_Leg expectedOutputFlight_Leg=		    
		     new Flight_Leg(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputFlight_Leg,TaskOperations.CreateWF));
            }
	     
		    {StartLegChecker expectedOutputStartLegChecker=		    
		     new StartLegChecker(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputStartLegChecker,TaskOperations.CreateWF));
            }
	     
     
     		      
	      tobject.addOutput(to);
	      initialised= allEntitiesExist;

		if (!allEntitiesExist){
		   String[] nonexisting=new String[nonExistingInputs.size()];
		   for (int j=0;j<nonExistingInputs.size();j++){
				nonexisting[j]=nonExistingInputs.elementAt(j).toString();
			}
			EventManager.getInstance().conversationalInitializationOfTaskFailed(getLocalName(), 
												"Pilot", 
												tobject, nonexisting);
		}
		return initialised;	       
	      }
                 
                  
                  
         nonExistingInputs.clear();
  		 repeatedOutputs.clear();
         if (tobject.getType().equals("ThinkNewDecision") ){
            Vector<MentalEntity> expectedInput=null;
            RuntimeFact expectedOutput=null;
            RuntimeEvent expectedOutputEvent=null;
			RuntimeConversation expectedInt=null;
            ingenias.jade.components.Resource expectedResource=null;
			ingenias.jade.components.Application expectedApp=null;        
			boolean allEntitiesExist=true;		
			TaskOutput to=null;
			to=new TaskOutput("default");
		
             expectedInput=this.getMSM().getMentalEntityByType("Pilot_Mind");
             if (expectedInput.size()==0 && !("1".equals("0..n"))){
				nonExistingInputs.add("Pilot_Mind");
			 } else {
			  addExpectedInputs(tobject, "Pilot_Mind","1",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;             
             
	      
             expectedInput=this.getMSM().getMentalEntityByType("Flight_Leg");
             if (expectedInput.size()==0 && !("1".equals("0..n"))){
				nonExistingInputs.add("Flight_Leg");
			 } else {
			  addExpectedInputs(tobject, "Flight_Leg","1",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;             
             
	      
	      
		
		
             expectedInput=this.getMSM().getMentalEntityByType("StartThinkNewDecision");
             if (expectedInput.size()==0 && !("1".equals("0..n"))){
				nonExistingInputs.add("StartThinkNewDecision");
			 } else {
			    addExpectedInputs(tobject, "StartThinkNewDecision","1",expectedInput);
             	addConsumedInput(to,"1",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;

	      
	      
	     
	      
	     // Default application for all tasks executed within a conversation
	     expectedApp=(ingenias.jade.components.Application)getAM().getApplication("YellowPages");
             tobject.addApplication("YellowPages",expectedApp);
	 /*    
	     
	      */	  
	      
	      /**/	      
	      
 
	     
	     
		    {CanCreateNewDecision expectedOutputCanCreateNewDecision=		    
		     new CanCreateNewDecision(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputCanCreateNewDecision,TaskOperations.CreateWF));
            }
	     
     
     		      
	      tobject.addOutput(to);
	      initialised= allEntitiesExist;

		if (!allEntitiesExist){
		   String[] nonexisting=new String[nonExistingInputs.size()];
		   for (int j=0;j<nonExistingInputs.size();j++){
				nonexisting[j]=nonExistingInputs.elementAt(j).toString();
			}
			EventManager.getInstance().conversationalInitializationOfTaskFailed(getLocalName(), 
												"Pilot", 
												tobject, nonexisting);
		}
		return initialised;	       
	      }
                 
                  
                  
         nonExistingInputs.clear();
  		 repeatedOutputs.clear();
         if (tobject.getType().equals("GoNextLeg") ){
            Vector<MentalEntity> expectedInput=null;
            RuntimeFact expectedOutput=null;
            RuntimeEvent expectedOutputEvent=null;
			RuntimeConversation expectedInt=null;
            ingenias.jade.components.Resource expectedResource=null;
			ingenias.jade.components.Application expectedApp=null;        
			boolean allEntitiesExist=true;		
			TaskOutput to=null;
			to=new TaskOutput("default");
		
             expectedInput=this.getMSM().getMentalEntityByType("Pilot_Mind");
             if (expectedInput.size()==0 && !("1".equals("0..n"))){
				nonExistingInputs.add("Pilot_Mind");
			 } else {
			  addExpectedInputs(tobject, "Pilot_Mind","1",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;             
             
	      
	      
		
		
             expectedInput=this.getMSM().getMentalEntityByType("LegCompleted");
             if (expectedInput.size()==0 && !("1".equals("0..n"))){
				nonExistingInputs.add("LegCompleted");
			 } else {
			    addExpectedInputs(tobject, "LegCompleted","1",expectedInput);
             	addConsumedInput(to,"1",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;

	      
		
             expectedInput=this.getMSM().getMentalEntityByType("Flight_Leg");
             if (expectedInput.size()==0 && !("1".equals("0..n"))){
				nonExistingInputs.add("Flight_Leg");
			 } else {
			    addExpectedInputs(tobject, "Flight_Leg","1",expectedInput);
             	addConsumedInput(to,"1",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;

	      
	      
	     
	      
	     // Default application for all tasks executed within a conversation
	     expectedApp=(ingenias.jade.components.Application)getAM().getApplication("YellowPages");
             tobject.addApplication("YellowPages",expectedApp);
	 /*    
	     
	      */	  
	      
	      /**/	      
	      
 
	     
	     
		    {AllLegsCompleted expectedOutputAllLegsCompleted=		    
		     new AllLegsCompleted(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputAllLegsCompleted,TaskOperations.CreateWF));
            }
	     
		    {GoNextLegWithConflict expectedOutputGoNextLegWithConflict=		    
		     new GoNextLegWithConflict(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputGoNextLegWithConflict,TaskOperations.CreateWF));
            }
	     
		    {GetNextLeg expectedOutputGetNextLeg=		    
		     new GetNextLeg(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputGetNextLeg,TaskOperations.CreateWF));
            }
	     
     
     		      
	      tobject.addOutput(to);
	      initialised= allEntitiesExist;

		if (!allEntitiesExist){
		   String[] nonexisting=new String[nonExistingInputs.size()];
		   for (int j=0;j<nonExistingInputs.size();j++){
				nonexisting[j]=nonExistingInputs.elementAt(j).toString();
			}
			EventManager.getInstance().conversationalInitializationOfTaskFailed(getLocalName(), 
												"Pilot", 
												tobject, nonexisting);
		}
		return initialised;	       
	      }
                 
                  
                  
         nonExistingInputs.clear();
  		 repeatedOutputs.clear();
         if (tobject.getType().equals("Execute_Instruction") ){
            Vector<MentalEntity> expectedInput=null;
            RuntimeFact expectedOutput=null;
            RuntimeEvent expectedOutputEvent=null;
			RuntimeConversation expectedInt=null;
            ingenias.jade.components.Resource expectedResource=null;
			ingenias.jade.components.Application expectedApp=null;        
			boolean allEntitiesExist=true;		
			TaskOutput to=null;
			to=new TaskOutput("default");
		
             expectedInput=this.getMSM().getMentalEntityByType("Pilot_Mind");
             if (expectedInput.size()==0 && !("1".equals("0..n"))){
				nonExistingInputs.add("Pilot_Mind");
			 } else {
			  addExpectedInputs(tobject, "Pilot_Mind","1",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;             
             
	      
	      
		
		
             expectedInput=this.getMSM().getMentalEntityByType("Throw_Instruction");
             if (expectedInput.size()==0 && !("1".equals("0..n"))){
				nonExistingInputs.add("Throw_Instruction");
			 } else {
			    addExpectedInputs(tobject, "Throw_Instruction","1",expectedInput);
             	addConsumedInput(to,"1",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;

	      
	      
	     
	      
	     // Default application for all tasks executed within a conversation
	     expectedApp=(ingenias.jade.components.Application)getAM().getApplication("YellowPages");
             tobject.addApplication("YellowPages",expectedApp);
	 /*    
	     
			 expectedInt=new RuntimeConversation(MentalStateManager.generateMentalEntityID());
			 expectedInt.setInteraction(new Interaction("PilotPlaneInteraction"));
             to.add(new OutputEntity(expectedInt,TaskOperations.CreateMS));
	      
	      */	  
	      
	      /**/	      
	      
 
	     
 			{
 			RuntimeConversation expectedOutputRuntimeConversation=
 				new RuntimeConversation(MentalStateManager.generateMentalEntityID());
 			if (RuntimeConversation.class.isAssignableFrom(expectedOutputRuntimeConversation.getClass())){
 			    java.lang.reflect.Method m;
				try {
					m = expectedOutputRuntimeConversation.getClass().getMethod("setInteraction", new Class[]{Interaction.class});
					m.invoke(expectedOutputRuntimeConversation, new Interaction("PilotPlaneInteraction")) ;	  
				} catch (SecurityException e) {
					
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					
					e.printStackTrace();
				}
 			}	 			
            to.add(new OutputEntity(expectedOutputRuntimeConversation,TaskOperations.CreateMS));
            }
	     
	     
		    {Manoeuvre expectedOutputManoeuvre=		    
		     new Manoeuvre(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputManoeuvre,TaskOperations.CreateWF));
            }
	     
     
     		      
	      tobject.addOutput(to);
	      initialised= allEntitiesExist;

		if (!allEntitiesExist){
		   String[] nonexisting=new String[nonExistingInputs.size()];
		   for (int j=0;j<nonExistingInputs.size();j++){
				nonexisting[j]=nonExistingInputs.elementAt(j).toString();
			}
			EventManager.getInstance().conversationalInitializationOfTaskFailed(getLocalName(), 
												"Pilot", 
												tobject, nonexisting);
		}
		return initialised;	       
	      }
                 
                  
                  
         nonExistingInputs.clear();
  		 repeatedOutputs.clear();
         if (tobject.getType().equals("Check_Leg_Completed") ){
            Vector<MentalEntity> expectedInput=null;
            RuntimeFact expectedOutput=null;
            RuntimeEvent expectedOutputEvent=null;
			RuntimeConversation expectedInt=null;
            ingenias.jade.components.Resource expectedResource=null;
			ingenias.jade.components.Application expectedApp=null;        
			boolean allEntitiesExist=true;		
			TaskOutput to=null;
			to=new TaskOutput("default");
		
             expectedInput=this.getMSM().getMentalEntityByType("Flight_Leg");
             if (expectedInput.size()==0 && !("1".equals("0..n"))){
				nonExistingInputs.add("Flight_Leg");
			 } else {
			  addExpectedInputs(tobject, "Flight_Leg","1",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;             
             
	      
	      
		
		
             expectedInput=this.getMSM().getMentalEntityByType("StartLegChecker");
             if (expectedInput.size()==0 && !("1".equals("0..n"))){
				nonExistingInputs.add("StartLegChecker");
			 } else {
			    addExpectedInputs(tobject, "StartLegChecker","1",expectedInput);
             	addConsumedInput(to,"1",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;

	      
	      
	     
	     expectedApp=(ingenias.jade.components.Application)getAM().getApplication("Leg_Checker");
             tobject.addApplication("Leg_Checker",expectedApp);
	      
	      
	     // Default application for all tasks executed within a conversation
	     expectedApp=(ingenias.jade.components.Application)getAM().getApplication("YellowPages");
             tobject.addApplication("YellowPages",expectedApp);
	 /*    
	     
	      */	  
	      
	      /**/	      
	      
 
	     
	     
     
     		      
	      tobject.addOutput(to);
	      initialised= allEntitiesExist;

		if (!allEntitiesExist){
		   String[] nonexisting=new String[nonExistingInputs.size()];
		   for (int j=0;j<nonExistingInputs.size();j++){
				nonexisting[j]=nonExistingInputs.elementAt(j).toString();
			}
			EventManager.getInstance().conversationalInitializationOfTaskFailed(getLocalName(), 
												"Pilot", 
												tobject, nonexisting);
		}
		return initialised;	       
	      }
                 
                  
                  
         nonExistingInputs.clear();
  		 repeatedOutputs.clear();
         if (tobject.getType().equals("Make_Decisions") ){
            Vector<MentalEntity> expectedInput=null;
            RuntimeFact expectedOutput=null;
            RuntimeEvent expectedOutputEvent=null;
			RuntimeConversation expectedInt=null;
            ingenias.jade.components.Resource expectedResource=null;
			ingenias.jade.components.Application expectedApp=null;        
			boolean allEntitiesExist=true;		
			TaskOutput to=null;
			to=new TaskOutput("default");
		
             expectedInput=this.getMSM().getMentalEntityByType("Flight_Leg");
             if (expectedInput.size()==0 && !("1".equals("0..n"))){
				nonExistingInputs.add("Flight_Leg");
			 } else {
			  addExpectedInputs(tobject, "Flight_Leg","1",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;             
             
	      
	      
		
		
             expectedInput=this.getMSM().getMentalEntityByType("CanCreateNewDecision");
             if (expectedInput.size()==0 && !("1".equals("0..n"))){
				nonExistingInputs.add("CanCreateNewDecision");
			 } else {
			    addExpectedInputs(tobject, "CanCreateNewDecision","1",expectedInput);
             	addConsumedInput(to,"1",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;

	      
	      
	     
	      
	     // Default application for all tasks executed within a conversation
	     expectedApp=(ingenias.jade.components.Application)getAM().getApplication("YellowPages");
             tobject.addApplication("YellowPages",expectedApp);
	 /*    
	     
	      */	  
	      
	      /**/	      
	      
 
	     
	     
		    {Decision expectedOutputDecision=		    
		     new Decision(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputDecision,TaskOperations.CreateWF));
            }
	     
     
     		      
	      tobject.addOutput(to);
	      initialised= allEntitiesExist;

		if (!allEntitiesExist){
		   String[] nonexisting=new String[nonExistingInputs.size()];
		   for (int j=0;j<nonExistingInputs.size();j++){
				nonexisting[j]=nonExistingInputs.elementAt(j).toString();
			}
			EventManager.getInstance().conversationalInitializationOfTaskFailed(getLocalName(), 
												"Pilot", 
												tobject, nonexisting);
		}
		return initialised;	       
	      }
                 
                  
                  
         nonExistingInputs.clear();
  		 repeatedOutputs.clear();
         if (tobject.getType().equals("GoingToStartPlane") ){
            Vector<MentalEntity> expectedInput=null;
            RuntimeFact expectedOutput=null;
            RuntimeEvent expectedOutputEvent=null;
			RuntimeConversation expectedInt=null;
            ingenias.jade.components.Resource expectedResource=null;
			ingenias.jade.components.Application expectedApp=null;        
			boolean allEntitiesExist=true;		
			TaskOutput to=null;
			to=new TaskOutput("default");
		
             expectedInput=this.getMSM().getMentalEntityByType("Pilot_Mind");
             if (expectedInput.size()==0 && !("1".equals("0..n"))){
				nonExistingInputs.add("Pilot_Mind");
			 } else {
			  addExpectedInputs(tobject, "Pilot_Mind","1",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;             
             
	      
	      
		
		
             expectedInput=this.getMSM().getMentalEntityByType("InitiateStartPlane");
             if (expectedInput.size()==0 && !("1".equals("0..n"))){
				nonExistingInputs.add("InitiateStartPlane");
			 } else {
			    addExpectedInputs(tobject, "InitiateStartPlane","1",expectedInput);
             	addConsumedInput(to,"1",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;

	      
	      
	     
	      
	     // Default application for all tasks executed within a conversation
	     expectedApp=(ingenias.jade.components.Application)getAM().getApplication("YellowPages");
             tobject.addApplication("YellowPages",expectedApp);
	 /*    
	     
			 expectedInt=new RuntimeConversation(MentalStateManager.generateMentalEntityID());
			 expectedInt.setInteraction(new Interaction("StartPlaneInteraction"));
             to.add(new OutputEntity(expectedInt,TaskOperations.CreateMS));
	      
	      */	  
	      
	      /**/	      
	      
 
	     
 			{
 			RuntimeConversation expectedOutputRuntimeConversation=
 				new RuntimeConversation(MentalStateManager.generateMentalEntityID());
 			if (RuntimeConversation.class.isAssignableFrom(expectedOutputRuntimeConversation.getClass())){
 			    java.lang.reflect.Method m;
				try {
					m = expectedOutputRuntimeConversation.getClass().getMethod("setInteraction", new Class[]{Interaction.class});
					m.invoke(expectedOutputRuntimeConversation, new Interaction("StartPlaneInteraction")) ;	  
				} catch (SecurityException e) {
					
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					
					e.printStackTrace();
				}
 			}	 			
            to.add(new OutputEntity(expectedOutputRuntimeConversation,TaskOperations.CreateMS));
            }
	     
	     
		    {TurningOnPlane expectedOutputTurningOnPlane=		    
		     new TurningOnPlane(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputTurningOnPlane,TaskOperations.CreateWF));
            }
	     
     
     		      
	      tobject.addOutput(to);
	      initialised= allEntitiesExist;

		if (!allEntitiesExist){
		   String[] nonexisting=new String[nonExistingInputs.size()];
		   for (int j=0;j<nonExistingInputs.size();j++){
				nonexisting[j]=nonExistingInputs.elementAt(j).toString();
			}
			EventManager.getInstance().conversationalInitializationOfTaskFailed(getLocalName(), 
												"Pilot", 
												tobject, nonexisting);
		}
		return initialised;	       
	      }
                 
         
	      return false;
	}

	private boolean initialiseConversationalTask(RuntimeConversation conversation, Task tobject) {
	   boolean initialised=false;
	   Vector<String> nonExistingInputs=new Vector<String>();
	   Vector<String> repeatedOutputs=new Vector<String>();
	   boolean validConversationType=false;
	   
	   if (tobject.getType().equals("DeleteNonUsedEntitiesTask")){
            Vector<MentalEntity> expectedInput=null;
            RuntimeFact expectedOutput=null;
            RuntimeEvent expectedOutputEvent=null;
			RuntimeConversation expectedInt=null;
            ingenias.jade.components.Resource expectedResource=null;
			ingenias.jade.components.Application expectedApp=null;        
			boolean allEntitiesExist=false;		
			TaskOutput to=null;
			to=new TaskOutput("default");
			tobject.setConversationContext(conversation);
  
		
		expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"PlanReceived");
		if (this.getLM().canBeDeleted(expectedInput)){                          
             if (expectedInput.size()==0){
				nonExistingInputs.add("PlanReceived");
			 } else {
			    addExpectedInputs(tobject, "PlanReceived","1",expectedInput);
             	addConsumedInput(to,"PlanReceived",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist|| expectedInput.size()!=0;
		} 
	      
		expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"ObjectDetected");
		if (this.getLM().canBeDeleted(expectedInput)){                          
             if (expectedInput.size()==0){
				nonExistingInputs.add("ObjectDetected");
			 } else {
			    addExpectedInputs(tobject, "ObjectDetected","1",expectedInput);
             	addConsumedInput(to,"ObjectDetected",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist|| expectedInput.size()!=0;
		} 
	      
		expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"Manoeuvre");
		if (this.getLM().canBeDeleted(expectedInput)){                          
             if (expectedInput.size()==0){
				nonExistingInputs.add("Manoeuvre");
			 } else {
			    addExpectedInputs(tobject, "Manoeuvre","1",expectedInput);
             	addConsumedInput(to,"Manoeuvre",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist|| expectedInput.size()!=0;
		} 
	      
		expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"Increase_Speed");
		if (this.getLM().canBeDeleted(expectedInput)){                          
             if (expectedInput.size()==0){
				nonExistingInputs.add("Increase_Speed");
			 } else {
			    addExpectedInputs(tobject, "Increase_Speed","1",expectedInput);
             	addConsumedInput(to,"Increase_Speed",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist|| expectedInput.size()!=0;
		} 
	      
		expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"Increase_Altitude");
		if (this.getLM().canBeDeleted(expectedInput)){                          
             if (expectedInput.size()==0){
				nonExistingInputs.add("Increase_Altitude");
			 } else {
			    addExpectedInputs(tobject, "Increase_Altitude","1",expectedInput);
             	addConsumedInput(to,"Increase_Altitude",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist|| expectedInput.size()!=0;
		} 
	      
		expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"Decrease_Speed");
		if (this.getLM().canBeDeleted(expectedInput)){                          
             if (expectedInput.size()==0){
				nonExistingInputs.add("Decrease_Speed");
			 } else {
			    addExpectedInputs(tobject, "Decrease_Speed","1",expectedInput);
             	addConsumedInput(to,"Decrease_Speed",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist|| expectedInput.size()!=0;
		} 
	      
		expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"Decrease_Altitude");
		if (this.getLM().canBeDeleted(expectedInput)){                          
             if (expectedInput.size()==0){
				nonExistingInputs.add("Decrease_Altitude");
			 } else {
			    addExpectedInputs(tobject, "Decrease_Altitude","1",expectedInput);
             	addConsumedInput(to,"Decrease_Altitude",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist|| expectedInput.size()!=0;
		} 
	      
		expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"Decrease_degrees");
		if (this.getLM().canBeDeleted(expectedInput)){                          
             if (expectedInput.size()==0){
				nonExistingInputs.add("Decrease_degrees");
			 } else {
			    addExpectedInputs(tobject, "Decrease_degrees","1",expectedInput);
             	addConsumedInput(to,"Decrease_degrees",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist|| expectedInput.size()!=0;
		} 
	      
		expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"TurningOnPlane");
		if (this.getLM().canBeDeleted(expectedInput)){                          
             if (expectedInput.size()==0){
				nonExistingInputs.add("TurningOnPlane");
			 } else {
			    addExpectedInputs(tobject, "TurningOnPlane","1",expectedInput);
             	addConsumedInput(to,"TurningOnPlane",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist|| expectedInput.size()!=0;
		} 
	      
		expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"Increase_degrees");
		if (this.getLM().canBeDeleted(expectedInput)){                          
             if (expectedInput.size()==0){
				nonExistingInputs.add("Increase_degrees");
			 } else {
			    addExpectedInputs(tobject, "Increase_degrees","1",expectedInput);
             	addConsumedInput(to,"Increase_degrees",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist|| expectedInput.size()!=0;
		} 
	      
		expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"OrdenDone");
		if (this.getLM().canBeDeleted(expectedInput)){                          
             if (expectedInput.size()==0){
				nonExistingInputs.add("OrdenDone");
			 } else {
			    addExpectedInputs(tobject, "OrdenDone","1",expectedInput);
             	addConsumedInput(to,"OrdenDone",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist|| expectedInput.size()!=0;
		} 
	      
		 tobject.addOutput(to);
     		   	
	      initialised= allEntitiesExist;

		  if (!allEntitiesExist){
			StringBuffer nonexisting=new StringBuffer();
			for (int j=0;j<nonExistingInputs.size();j++){
				nonexisting.append(nonExistingInputs.elementAt(j).toString()+",");
			}
   		  }
		  return initialised;
	    }
	    
		    

		    
		validConversationType=validConversationType||
				conversation.getInteraction().getId().equalsIgnoreCase("ControllerPilotInteraction");
	 	
				
		if (validConversationType){
    	         
         
	   	nonExistingInputs.clear();
  	   	repeatedOutputs.clear();
  	   	boolean correctRole=conversation.getPlayedRole().equals ("PilotColaborator");
  	   	// Now all ascendant roles are verified, to enable tasks belonging to roles specializing a more
  	   	// generic one involved in an interaction
  	   	
  	   	correctRole=correctRole|| 
  	   	 conversation.getPlayedRole().equals ("Colaborator");
  	   	
       	if (tobject.getType().equals("ObeyOrderCheck") && (false ||
       		correctRole)){
	        Vector<MentalEntity> expectedInput=null;
            
       	RuntimeFact expectedOutput=null;
	   	RuntimeConversation expectedInt=null;
       	ingenias.jade.components.Resource expectedResource=null;
	   	ingenias.jade.components.Application expectedApp=null;        	
	   	TaskOutput to=null;
	   	to=new TaskOutput("default");

		tobject.setConversationContext(conversation);
		boolean allEntitiesExist=true;
	     
            
		
            expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"OrderFinished");
			if (expectedInput.size()==0 && !("1".equals("0..n")))
				nonExistingInputs.add("OrderFinished");
			else {
			    addExpectedInputs(tobject, "OrderFinished","1",expectedInput);
			    addConsumedInput(to, "1", expectedInput);
			}
	      allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;
	      
            expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"OrderOldLeg");
			if (expectedInput.size()==0 && !("1".equals("0..n")))
				nonExistingInputs.add("OrderOldLeg");
			else {
			    addExpectedInputs(tobject, "OrderOldLeg","1",expectedInput);
			    addConsumedInput(to, "1", expectedInput);
			}
	      allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;
	      
		
	      expectedApp=(ingenias.jade.components.Application)getAM().getApplication("YellowPages");
             tobject.addApplication("YellowPages",expectedApp);
        /*     
		
	      */	      
	     boolean alreadyExists=true;
	 
	     
 			{
 			Flight_Leg expectedOutputFlight_Leg=
 				new Flight_Leg(MentalStateManager.generateMentalEntityID());
 			if (RuntimeConversation.class.isAssignableFrom(expectedOutputFlight_Leg.getClass())){
 			    java.lang.reflect.Method m;
				try {
					m = expectedOutputFlight_Leg.getClass().getMethod("setInteraction", new Class[]{Interaction.class});
					m.invoke(expectedOutputFlight_Leg, new Interaction("")) ;	  
				} catch (SecurityException e) {
					
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					
					e.printStackTrace();
				}
 			}	 			
            to.add(new OutputEntity(expectedOutputFlight_Leg,TaskOperations.CreateMS));
            }
	     
 			{
 			StartLegChecker expectedOutputStartLegChecker=
 				new StartLegChecker(MentalStateManager.generateMentalEntityID());
 			if (RuntimeConversation.class.isAssignableFrom(expectedOutputStartLegChecker.getClass())){
 			    java.lang.reflect.Method m;
				try {
					m = expectedOutputStartLegChecker.getClass().getMethod("setInteraction", new Class[]{Interaction.class});
					m.invoke(expectedOutputStartLegChecker, new Interaction("")) ;	  
				} catch (SecurityException e) {
					
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					
					e.printStackTrace();
				}
 			}	 			
            to.add(new OutputEntity(expectedOutputStartLegChecker,TaskOperations.CreateMS));
            }
	     
	     
		    {OrdenDone expectedOutputOrdenDone=		    
		     new OrdenDone(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputOrdenDone,TaskOperations.CreateWF));
            }
	     
     
	     tobject.addOutput(to);
	     
	     
     	      if (!allEntitiesExist){
     	         String[] nonexisting=new String[nonExistingInputs.size()];
		   		 for (int j=0;j<nonExistingInputs.size();j++){
					nonexisting[j]=nonExistingInputs.elementAt(j).toString();
				 }
				 EventManager.getInstance().conversationalInitializationOfTaskFailed(
				 			getLocalName(), "Pilot", 
												tobject, nonexisting);
     	     			
			   }
	        	       
 	      initialised= allEntitiesExist;
 	       return initialised;
	      }
         
         
         }
         validConversationType=false;
             

		    
		validConversationType=validConversationType||
				conversation.getInteraction().getId().equalsIgnoreCase("ControllerPilotInteraction");
	 	
				
		if (validConversationType){
    	         
         
	   	nonExistingInputs.clear();
  	   	repeatedOutputs.clear();
  	   	boolean correctRole=conversation.getPlayedRole().equals ("PilotColaborator");
  	   	// Now all ascendant roles are verified, to enable tasks belonging to roles specializing a more
  	   	// generic one involved in an interaction
  	   	
  	   	correctRole=correctRole|| 
  	   	 conversation.getPlayedRole().equals ("Colaborator");
  	   	
       	if (tobject.getType().equals("TakeOrder") && (false ||
       		correctRole)){
	        Vector<MentalEntity> expectedInput=null;
            
       	RuntimeFact expectedOutput=null;
	   	RuntimeConversation expectedInt=null;
       	ingenias.jade.components.Resource expectedResource=null;
	   	ingenias.jade.components.Application expectedApp=null;        	
	   	TaskOutput to=null;
	   	to=new TaskOutput("default");

		tobject.setConversationContext(conversation);
		boolean allEntitiesExist=true;
	     
            
		
            expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"Order");
			if (expectedInput.size()==0 && !("1".equals("0..n")))
				nonExistingInputs.add("Order");
			else {
			    addExpectedInputs(tobject, "Order","1",expectedInput);
			    addConsumedInput(to, "1", expectedInput);
			}
	      allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;
	      
            expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"Flight_Leg");
			if (expectedInput.size()==0 && !("1".equals("0..n")))
				nonExistingInputs.add("Flight_Leg");
			else {
			    addExpectedInputs(tobject, "Flight_Leg","1",expectedInput);
			    addConsumedInput(to, "1", expectedInput);
			}
	      allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;
	      
		
	      expectedApp=(ingenias.jade.components.Application)getAM().getApplication("YellowPages");
             tobject.addApplication("YellowPages",expectedApp);
        /*     
		
	      */	      
	     boolean alreadyExists=true;
	 
	     
 			{
 			OrderOldLeg expectedOutputOrderOldLeg=
 				new OrderOldLeg(MentalStateManager.generateMentalEntityID());
 			if (RuntimeConversation.class.isAssignableFrom(expectedOutputOrderOldLeg.getClass())){
 			    java.lang.reflect.Method m;
				try {
					m = expectedOutputOrderOldLeg.getClass().getMethod("setInteraction", new Class[]{Interaction.class});
					m.invoke(expectedOutputOrderOldLeg, new Interaction("")) ;	  
				} catch (SecurityException e) {
					
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					
					e.printStackTrace();
				}
 			}	 			
            to.add(new OutputEntity(expectedOutputOrderOldLeg,TaskOperations.CreateMS));
            }
	     
 			{
 			OrderNewLeg expectedOutputOrderNewLeg=
 				new OrderNewLeg(MentalStateManager.generateMentalEntityID());
 			if (RuntimeConversation.class.isAssignableFrom(expectedOutputOrderNewLeg.getClass())){
 			    java.lang.reflect.Method m;
				try {
					m = expectedOutputOrderNewLeg.getClass().getMethod("setInteraction", new Class[]{Interaction.class});
					m.invoke(expectedOutputOrderNewLeg, new Interaction("")) ;	  
				} catch (SecurityException e) {
					
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					
					e.printStackTrace();
				}
 			}	 			
            to.add(new OutputEntity(expectedOutputOrderNewLeg,TaskOperations.CreateMS));
            }
	     
	     
     
	     tobject.addOutput(to);
	     
	     
     	      if (!allEntitiesExist){
     	         String[] nonexisting=new String[nonExistingInputs.size()];
		   		 for (int j=0;j<nonExistingInputs.size();j++){
					nonexisting[j]=nonExistingInputs.elementAt(j).toString();
				 }
				 EventManager.getInstance().conversationalInitializationOfTaskFailed(
				 			getLocalName(), "Pilot", 
												tobject, nonexisting);
     	     			
			   }
	        	       
 	      initialised= allEntitiesExist;
 	       return initialised;
	      }
         
         
         }
         validConversationType=false;
             

		    
		validConversationType=validConversationType||
				conversation.getInteraction().getId().equalsIgnoreCase("FlightPlannerPilotInteraction");
	 	
				
		if (validConversationType){
    	         
         
	   	nonExistingInputs.clear();
  	   	repeatedOutputs.clear();
  	   	boolean correctRole=conversation.getPlayedRole().equals ("PilotColaborator");
  	   	// Now all ascendant roles are verified, to enable tasks belonging to roles specializing a more
  	   	// generic one involved in an interaction
  	   	
  	   	correctRole=correctRole|| 
  	   	 conversation.getPlayedRole().equals ("Colaborator");
  	   	
       	if (tobject.getType().equals("Take_Initial_Plan") && (false ||
       		correctRole)){
	        Vector<MentalEntity> expectedInput=null;
            
       	RuntimeFact expectedOutput=null;
	   	RuntimeConversation expectedInt=null;
       	ingenias.jade.components.Resource expectedResource=null;
	   	ingenias.jade.components.Application expectedApp=null;        	
	   	TaskOutput to=null;
	   	to=new TaskOutput("default");

		tobject.setConversationContext(conversation);
		boolean allEntitiesExist=true;
	     
            
		
            expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"PlanAnswer");
			if (expectedInput.size()==0 && !("1".equals("0..n")))
				nonExistingInputs.add("PlanAnswer");
			else {
			    addExpectedInputs(tobject, "PlanAnswer","1",expectedInput);
			    addConsumedInput(to, "1", expectedInput);
			}
	      allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;
	      
		
	      expectedApp=(ingenias.jade.components.Application)getAM().getApplication("YellowPages");
             tobject.addApplication("YellowPages",expectedApp);
        /*     
		
	      */	      
	     boolean alreadyExists=true;
	 
	     
 			{
 			Flight_Plan expectedOutputFlight_Plan=
 				new Flight_Plan(MentalStateManager.generateMentalEntityID());
 			if (RuntimeConversation.class.isAssignableFrom(expectedOutputFlight_Plan.getClass())){
 			    java.lang.reflect.Method m;
				try {
					m = expectedOutputFlight_Plan.getClass().getMethod("setInteraction", new Class[]{Interaction.class});
					m.invoke(expectedOutputFlight_Plan, new Interaction("")) ;	  
				} catch (SecurityException e) {
					
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					
					e.printStackTrace();
				}
 			}	 			
            to.add(new OutputEntity(expectedOutputFlight_Plan,TaskOperations.CreateMS));
            }
	     
 			{
 			CanInitiateStartPlane expectedOutputCanInitiateStartPlane=
 				new CanInitiateStartPlane(MentalStateManager.generateMentalEntityID());
 			if (RuntimeConversation.class.isAssignableFrom(expectedOutputCanInitiateStartPlane.getClass())){
 			    java.lang.reflect.Method m;
				try {
					m = expectedOutputCanInitiateStartPlane.getClass().getMethod("setInteraction", new Class[]{Interaction.class});
					m.invoke(expectedOutputCanInitiateStartPlane, new Interaction("")) ;	  
				} catch (SecurityException e) {
					
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					
					e.printStackTrace();
				}
 			}	 			
            to.add(new OutputEntity(expectedOutputCanInitiateStartPlane,TaskOperations.CreateMS));
            }
	     
	     
		    {PlanReceived expectedOutputPlanReceived=		    
		     new PlanReceived(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputPlanReceived,TaskOperations.CreateWF));
            }
	     
     
	     tobject.addOutput(to);
	     
	     
     	      if (!allEntitiesExist){
     	         String[] nonexisting=new String[nonExistingInputs.size()];
		   		 for (int j=0;j<nonExistingInputs.size();j++){
					nonexisting[j]=nonExistingInputs.elementAt(j).toString();
				 }
				 EventManager.getInstance().conversationalInitializationOfTaskFailed(
				 			getLocalName(), "Pilot", 
												tobject, nonexisting);
     	     			
			   }
	        	       
 	      initialised= allEntitiesExist;
 	       return initialised;
	      }
         
         
         }
         validConversationType=false;
             

		    
		validConversationType=validConversationType||
				conversation.getInteraction().getId().equalsIgnoreCase("FreeConflictInteraction");
	 	
				
		if (validConversationType){
    	         
         
	   	nonExistingInputs.clear();
  	   	repeatedOutputs.clear();
  	   	boolean correctRole=conversation.getPlayedRole().equals ("PilotColaborator");
  	   	// Now all ascendant roles are verified, to enable tasks belonging to roles specializing a more
  	   	// generic one involved in an interaction
  	   	
  	   	correctRole=correctRole|| 
  	   	 conversation.getPlayedRole().equals ("Colaborator");
  	   	
       	if (tobject.getType().equals("IsNotInConflictNow") && (false ||
       		correctRole)){
	        Vector<MentalEntity> expectedInput=null;
            
       	RuntimeFact expectedOutput=null;
	   	RuntimeConversation expectedInt=null;
       	ingenias.jade.components.Resource expectedResource=null;
	   	ingenias.jade.components.Application expectedApp=null;        	
	   	TaskOutput to=null;
	   	to=new TaskOutput("default");

		tobject.setConversationContext(conversation);
		boolean allEntitiesExist=true;
	     
            
		
            expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"ConflictFinished");
			if (expectedInput.size()==0 && !("1".equals("0..n")))
				nonExistingInputs.add("ConflictFinished");
			else {
			    addExpectedInputs(tobject, "ConflictFinished","1",expectedInput);
			    addConsumedInput(to, "1", expectedInput);
			}
	      allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;
	      
            expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"Flight_Leg");
			if (expectedInput.size()==0 && !("1".equals("0..n")))
				nonExistingInputs.add("Flight_Leg");
			else {
			    addExpectedInputs(tobject, "Flight_Leg","1",expectedInput);
			    addConsumedInput(to, "1", expectedInput);
			}
	      allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;
	      
		
	      expectedApp=(ingenias.jade.components.Application)getAM().getApplication("YellowPages");
             tobject.addApplication("YellowPages",expectedApp);
        /*     
		
	      */	      
	     boolean alreadyExists=true;
	 
	     
 			{
 			OrderFinished expectedOutputOrderFinished=
 				new OrderFinished(MentalStateManager.generateMentalEntityID());
 			if (RuntimeConversation.class.isAssignableFrom(expectedOutputOrderFinished.getClass())){
 			    java.lang.reflect.Method m;
				try {
					m = expectedOutputOrderFinished.getClass().getMethod("setInteraction", new Class[]{Interaction.class});
					m.invoke(expectedOutputOrderFinished, new Interaction("")) ;	  
				} catch (SecurityException e) {
					
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					
					e.printStackTrace();
				}
 			}	 			
            to.add(new OutputEntity(expectedOutputOrderFinished,TaskOperations.CreateMS));
            }
	     
	     
     
	     tobject.addOutput(to);
	     
	     
     	      if (!allEntitiesExist){
     	         String[] nonexisting=new String[nonExistingInputs.size()];
		   		 for (int j=0;j<nonExistingInputs.size();j++){
					nonexisting[j]=nonExistingInputs.elementAt(j).toString();
				 }
				 EventManager.getInstance().conversationalInitializationOfTaskFailed(
				 			getLocalName(), "Pilot", 
												tobject, nonexisting);
     	     			
			   }
	        	       
 	      initialised= allEntitiesExist;
 	       return initialised;
	      }
         
         
         }
         validConversationType=false;
             

		    
		validConversationType=validConversationType||
				conversation.getInteraction().getId().equalsIgnoreCase("PilotPlaneInteraction");
	 	
				
		if (validConversationType){
    	         
         
	   	nonExistingInputs.clear();
  	   	repeatedOutputs.clear();
  	   	boolean correctRole=conversation.getPlayedRole().equals ("PilotInitiator");
  	   	// Now all ascendant roles are verified, to enable tasks belonging to roles specializing a more
  	   	// generic one involved in an interaction
  	   	
  	   	correctRole=correctRole|| 
  	   	 conversation.getPlayedRole().equals ("Initiator");
  	   	
       	if (tobject.getType().equals("Check_Change") && (false ||
       		correctRole)){
	        Vector<MentalEntity> expectedInput=null;
            
       	RuntimeFact expectedOutput=null;
	   	RuntimeConversation expectedInt=null;
       	ingenias.jade.components.Resource expectedResource=null;
	   	ingenias.jade.components.Application expectedApp=null;        	
	   	TaskOutput to=null;
	   	to=new TaskOutput("default");

		tobject.setConversationContext(conversation);
		boolean allEntitiesExist=true;
	     
	     	expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"Pilot_Mind");
			if (expectedInput.size()==0 && !("1".equals("0..n")))
				nonExistingInputs.add("Pilot_Mind");
			else
				addExpectedInputs(tobject, "", 
				"1", expectedInput);
		    allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;
	     
            
		
            expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"Plane_Change");
			if (expectedInput.size()==0 && !("1".equals("0..n")))
				nonExistingInputs.add("Plane_Change");
			else {
			    addExpectedInputs(tobject, "Plane_Change","1",expectedInput);
			    addConsumedInput(to, "1", expectedInput);
			}
	      allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;
	      
		
	      expectedApp=(ingenias.jade.components.Application)getAM().getApplication("YellowPages");
             tobject.addApplication("YellowPages",expectedApp);
        /*     
		
	      */	      
	     boolean alreadyExists=true;
	 
	     
	     
     
	     tobject.addOutput(to);
	     
	     
     	      if (!allEntitiesExist){
     	         String[] nonexisting=new String[nonExistingInputs.size()];
		   		 for (int j=0;j<nonExistingInputs.size();j++){
					nonexisting[j]=nonExistingInputs.elementAt(j).toString();
				 }
				 EventManager.getInstance().conversationalInitializationOfTaskFailed(
				 			getLocalName(), "Pilot", 
												tobject, nonexisting);
     	     			
			   }
	        	       
 	      initialised= allEntitiesExist;
 	       return initialised;
	      }
         
         
         }
         validConversationType=false;
             

		    
		validConversationType=validConversationType||
				conversation.getInteraction().getId().equalsIgnoreCase("StartPlaneInteraction");
	 	
				
		if (validConversationType){
    	         
         
	   	nonExistingInputs.clear();
  	   	repeatedOutputs.clear();
  	   	boolean correctRole=conversation.getPlayedRole().equals ("PilotInitiator");
  	   	// Now all ascendant roles are verified, to enable tasks belonging to roles specializing a more
  	   	// generic one involved in an interaction
  	   	
  	   	correctRole=correctRole|| 
  	   	 conversation.getPlayedRole().equals ("Initiator");
  	   	
       	if (tobject.getType().equals("StartDescomposingPlan") && (false ||
       		correctRole)){
	        Vector<MentalEntity> expectedInput=null;
            
       	RuntimeFact expectedOutput=null;
	   	RuntimeConversation expectedInt=null;
       	ingenias.jade.components.Resource expectedResource=null;
	   	ingenias.jade.components.Application expectedApp=null;        	
	   	TaskOutput to=null;
	   	to=new TaskOutput("default");

		tobject.setConversationContext(conversation);
		boolean allEntitiesExist=true;
	     
            
		
            expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"PlaneOn");
			if (expectedInput.size()==0 && !("1".equals("0..n")))
				nonExistingInputs.add("PlaneOn");
			else {
			    addExpectedInputs(tobject, "PlaneOn","1",expectedInput);
			    addConsumedInput(to, "1", expectedInput);
			}
	      allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;
	      
		
	      expectedApp=(ingenias.jade.components.Application)getAM().getApplication("YellowPages");
             tobject.addApplication("YellowPages",expectedApp);
        /*     
		
	      */	      
	     boolean alreadyExists=true;
	 
	     
 			{
 			GetNextLeg expectedOutputGetNextLeg=
 				new GetNextLeg(MentalStateManager.generateMentalEntityID());
 			if (RuntimeConversation.class.isAssignableFrom(expectedOutputGetNextLeg.getClass())){
 			    java.lang.reflect.Method m;
				try {
					m = expectedOutputGetNextLeg.getClass().getMethod("setInteraction", new Class[]{Interaction.class});
					m.invoke(expectedOutputGetNextLeg, new Interaction("")) ;	  
				} catch (SecurityException e) {
					
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					
					e.printStackTrace();
				}
 			}	 			
            to.add(new OutputEntity(expectedOutputGetNextLeg,TaskOperations.CreateMS));
            }
	     
 			{
 			StartThinkNewDecision expectedOutputStartThinkNewDecision=
 				new StartThinkNewDecision(MentalStateManager.generateMentalEntityID());
 			if (RuntimeConversation.class.isAssignableFrom(expectedOutputStartThinkNewDecision.getClass())){
 			    java.lang.reflect.Method m;
				try {
					m = expectedOutputStartThinkNewDecision.getClass().getMethod("setInteraction", new Class[]{Interaction.class});
					m.invoke(expectedOutputStartThinkNewDecision, new Interaction("")) ;	  
				} catch (SecurityException e) {
					
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					
					e.printStackTrace();
				}
 			}	 			
            to.add(new OutputEntity(expectedOutputStartThinkNewDecision,TaskOperations.CreateMS));
            }
	     
	     
     
	     tobject.addOutput(to);
	     
	     
     	      if (!allEntitiesExist){
     	         String[] nonexisting=new String[nonExistingInputs.size()];
		   		 for (int j=0;j<nonExistingInputs.size();j++){
					nonexisting[j]=nonExistingInputs.elementAt(j).toString();
				 }
				 EventManager.getInstance().conversationalInitializationOfTaskFailed(
				 			getLocalName(), "Pilot", 
												tobject, nonexisting);
     	     			
			   }
	        	       
 	      initialised= allEntitiesExist;
 	       return initialised;
	      }
         
         
         }
         validConversationType=false;
         
		return false;
	}       

		
 	// This method returns the tasks this agent can perform in
	// order to satisfy the goal
	public Vector tasksThatSatisfyGoal(String goalname){
         Vector tasks=new Vector();
         Vector<String> typesOfConversation=null;
         //************************************
         // Conversational tasks evaluation
         //************************************
         
         typesOfConversation=new Vector<String>();
	     
	     typesOfConversation.add("ControllerPilotInteraction");
		 
         
         if (goalname.equals("Collision_Avoided")){
         
          {
		    Task tobject=null;
			Vector<RuntimeConversation>  conversations=getCM().getCurrentActiveConversations(typesOfConversation);
				boolean canbescheduled=false;
				for (int k=0;k<conversations.size();k++){
					tobject=new ObeyOrderCheckTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
					canbescheduled=initialiseConversationalTask(conversations.elementAt(k),tobject);
					if (canbescheduled){
					//	MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal Collision_Avoided",getLocalName()+"-"+tobject.getType());
						tasks.add(tobject);
					}
					tobject=new DeleteNonUsedEntitiesTask("DeleteNonUsedEntitiesTask","DeleteNonUsedEntitiesTask");
					canbescheduled=initialiseConversationalTask(conversations.elementAt(k),tobject);
					 if (canbescheduled && IAFProperties.getGarbageCollectionEnabled()){			
							tasks.add(tobject);
					 }
				}
				// If a conversational initialization fails, a conventional one is tried
	      }
         
          }        
         
         
         typesOfConversation=new Vector<String>();
	     
	     typesOfConversation.add("ControllerPilotInteraction");
		 
         
         if (goalname.equals("Collision_Avoided")){
         
          {
		    Task tobject=null;
			Vector<RuntimeConversation>  conversations=getCM().getCurrentActiveConversations(typesOfConversation);
				boolean canbescheduled=false;
				for (int k=0;k<conversations.size();k++){
					tobject=new TakeOrderTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
					canbescheduled=initialiseConversationalTask(conversations.elementAt(k),tobject);
					if (canbescheduled){
					//	MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal Collision_Avoided",getLocalName()+"-"+tobject.getType());
						tasks.add(tobject);
					}
					tobject=new DeleteNonUsedEntitiesTask("DeleteNonUsedEntitiesTask","DeleteNonUsedEntitiesTask");
					canbescheduled=initialiseConversationalTask(conversations.elementAt(k),tobject);
					 if (canbescheduled && IAFProperties.getGarbageCollectionEnabled()){			
							tasks.add(tobject);
					 }
				}
				// If a conversational initialization fails, a conventional one is tried
	      }
         
          }        
         
         
         typesOfConversation=new Vector<String>();
	     
	     typesOfConversation.add("FlightPlannerPilotInteraction");
		 
         
         if (goalname.equals("Flight_Plan_Taken")){
         
          {
		    Task tobject=null;
			Vector<RuntimeConversation>  conversations=getCM().getCurrentActiveConversations(typesOfConversation);
				boolean canbescheduled=false;
				for (int k=0;k<conversations.size();k++){
					tobject=new Take_Initial_PlanTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
					canbescheduled=initialiseConversationalTask(conversations.elementAt(k),tobject);
					if (canbescheduled){
					//	MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal Flight_Plan_Taken",getLocalName()+"-"+tobject.getType());
						tasks.add(tobject);
					}
					tobject=new DeleteNonUsedEntitiesTask("DeleteNonUsedEntitiesTask","DeleteNonUsedEntitiesTask");
					canbescheduled=initialiseConversationalTask(conversations.elementAt(k),tobject);
					 if (canbescheduled && IAFProperties.getGarbageCollectionEnabled()){			
							tasks.add(tobject);
					 }
				}
				// If a conversational initialization fails, a conventional one is tried
	      }
         
          }        
         
         
         typesOfConversation=new Vector<String>();
	     
	     typesOfConversation.add("FreeConflictInteraction");
		 
         
         if (goalname.equals("FinishedPlaneConflict")){
         
          {
		    Task tobject=null;
			Vector<RuntimeConversation>  conversations=getCM().getCurrentActiveConversations(typesOfConversation);
				boolean canbescheduled=false;
				for (int k=0;k<conversations.size();k++){
					tobject=new IsNotInConflictNowTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
					canbescheduled=initialiseConversationalTask(conversations.elementAt(k),tobject);
					if (canbescheduled){
					//	MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal FinishedPlaneConflict",getLocalName()+"-"+tobject.getType());
						tasks.add(tobject);
					}
					tobject=new DeleteNonUsedEntitiesTask("DeleteNonUsedEntitiesTask","DeleteNonUsedEntitiesTask");
					canbescheduled=initialiseConversationalTask(conversations.elementAt(k),tobject);
					 if (canbescheduled && IAFProperties.getGarbageCollectionEnabled()){			
							tasks.add(tobject);
					 }
				}
				// If a conversational initialization fails, a conventional one is tried
	      }
         
          }        
         
         
         typesOfConversation=new Vector<String>();
	     
	     typesOfConversation.add("PilotPlaneInteraction");
		 
         
         if (goalname.equals("Executed_Instruction")){
         
          {
		    Task tobject=null;
			Vector<RuntimeConversation>  conversations=getCM().getCurrentActiveConversations(typesOfConversation);
				boolean canbescheduled=false;
				for (int k=0;k<conversations.size();k++){
					tobject=new Check_ChangeTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
					canbescheduled=initialiseConversationalTask(conversations.elementAt(k),tobject);
					if (canbescheduled){
					//	MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal Executed_Instruction",getLocalName()+"-"+tobject.getType());
						tasks.add(tobject);
					}
					tobject=new DeleteNonUsedEntitiesTask("DeleteNonUsedEntitiesTask","DeleteNonUsedEntitiesTask");
					canbescheduled=initialiseConversationalTask(conversations.elementAt(k),tobject);
					 if (canbescheduled && IAFProperties.getGarbageCollectionEnabled()){			
							tasks.add(tobject);
					 }
				}
				// If a conversational initialization fails, a conventional one is tried
	      }
         
          }        
         
         
         typesOfConversation=new Vector<String>();
	     
	     typesOfConversation.add("StartPlaneInteraction");
		 
         
         if (goalname.equals("PlaneStarted")){
         
          {
		    Task tobject=null;
			Vector<RuntimeConversation>  conversations=getCM().getCurrentActiveConversations(typesOfConversation);
				boolean canbescheduled=false;
				for (int k=0;k<conversations.size();k++){
					tobject=new StartDescomposingPlanTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
					canbescheduled=initialiseConversationalTask(conversations.elementAt(k),tobject);
					if (canbescheduled){
					//	MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal PlaneStarted",getLocalName()+"-"+tobject.getType());
						tasks.add(tobject);
					}
					tobject=new DeleteNonUsedEntitiesTask("DeleteNonUsedEntitiesTask","DeleteNonUsedEntitiesTask");
					canbescheduled=initialiseConversationalTask(conversations.elementAt(k),tobject);
					 if (canbescheduled && IAFProperties.getGarbageCollectionEnabled()){			
							tasks.add(tobject);
					 }
				}
				// If a conversational initialization fails, a conventional one is tried
	      }
         
          }        
         
         
         //************************************
         // Non conversational tasks evaluation
         //************************************
         
         if (goalname.equals("Flight_Plan_Completed")){
         
         {
         boolean canbescheduled=false;
		 Task tobject=null;		 
				// If a conversational initialization fails, a conventional one is tried
				 tobject=new Stop_PlaneTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
				 canbescheduled=initialiseNonConversationalTask(tobject);
			 	 if (canbescheduled){
					//MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal Flight_Plan_Completed",getLocalName()+"-"+tobject.getType());
					tasks.add(tobject);
				 } 			
	     }
         
          }
                  
         
         if (goalname.equals("DecisionDescomposed")){
         
         {
         boolean canbescheduled=false;
		 Task tobject=null;		 
				// If a conversational initialization fails, a conventional one is tried
				 tobject=new DescomposeDecisionTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
				 canbescheduled=initialiseNonConversationalTask(tobject);
			 	 if (canbescheduled){
					//MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal DecisionDescomposed",getLocalName()+"-"+tobject.getType());
					tasks.add(tobject);
				 } 			
	     }
         
          }
                  
         
         if (goalname.equals("FlightPlanDescomposedWithConflict")){
         
         {
         boolean canbescheduled=false;
		 Task tobject=null;		 
				// If a conversational initialization fails, a conventional one is tried
				 tobject=new GoingNextLegWithConflictTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
				 canbescheduled=initialiseNonConversationalTask(tobject);
			 	 if (canbescheduled){
					//MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal FlightPlanDescomposedWithConflict",getLocalName()+"-"+tobject.getType());
					tasks.add(tobject);
				 } 			
	     }
         
          }
                  
         
         if (goalname.equals("Flight_Plan_Descomposed")){
         
         {
         boolean canbescheduled=false;
		 Task tobject=null;		 
				// If a conversational initialization fails, a conventional one is tried
				 tobject=new Flight_Plan_MonitoringTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
				 canbescheduled=initialiseNonConversationalTask(tobject);
			 	 if (canbescheduled){
					//MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal Flight_Plan_Descomposed",getLocalName()+"-"+tobject.getType());
					tasks.add(tobject);
				 } 			
	     }
         
          }
                  
         
         if (goalname.equals("TimeToStartPlane")){
         
         {
         boolean canbescheduled=false;
		 Task tobject=null;		 
				// If a conversational initialization fails, a conventional one is tried
				 tobject=new IsTimeToStartPlaneTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
				 canbescheduled=initialiseNonConversationalTask(tobject);
			 	 if (canbescheduled){
					//MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal TimeToStartPlane",getLocalName()+"-"+tobject.getType());
					tasks.add(tobject);
				 } 			
	     }
         
          }
                  
         
         if (goalname.equals("Created_Pilot_Mind")){
         
         {
         boolean canbescheduled=false;
		 Task tobject=null;		 
				// If a conversational initialization fails, a conventional one is tried
				 tobject=new CreatePilotMindTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
				 canbescheduled=initialiseNonConversationalTask(tobject);
			 	 if (canbescheduled){
					//MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal Created_Pilot_Mind",getLocalName()+"-"+tobject.getType());
					tasks.add(tobject);
				 } 			
	     }
         
          }
                  
         
         if (goalname.equals("NewLegForOrderCreated")){
         
         {
         boolean canbescheduled=false;
		 Task tobject=null;		 
				// If a conversational initialization fails, a conventional one is tried
				 tobject=new CreateNewLegForOrderTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
				 canbescheduled=initialiseNonConversationalTask(tobject);
			 	 if (canbescheduled){
					//MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal NewLegForOrderCreated",getLocalName()+"-"+tobject.getType());
					tasks.add(tobject);
				 } 			
	     }
         
          }
                  
         
         if (goalname.equals("NewDecisionThought")){
         
         {
         boolean canbescheduled=false;
		 Task tobject=null;		 
				// If a conversational initialization fails, a conventional one is tried
				 tobject=new ThinkNewDecisionTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
				 canbescheduled=initialiseNonConversationalTask(tobject);
			 	 if (canbescheduled){
					//MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal NewDecisionThought",getLocalName()+"-"+tobject.getType());
					tasks.add(tobject);
				 } 			
	     }
         
          }
                  
         
         if (goalname.equals("PreparedForNextLeg")){
         
         {
         boolean canbescheduled=false;
		 Task tobject=null;		 
				// If a conversational initialization fails, a conventional one is tried
				 tobject=new GoNextLegTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
				 canbescheduled=initialiseNonConversationalTask(tobject);
			 	 if (canbescheduled){
					//MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal PreparedForNextLeg",getLocalName()+"-"+tobject.getType());
					tasks.add(tobject);
				 } 			
	     }
         
          }
                  
         
         if (goalname.equals("Executed_Instruction")){
         
         {
         boolean canbescheduled=false;
		 Task tobject=null;		 
				// If a conversational initialization fails, a conventional one is tried
				 tobject=new Execute_InstructionTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
				 canbescheduled=initialiseNonConversationalTask(tobject);
			 	 if (canbescheduled){
					//MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal Executed_Instruction",getLocalName()+"-"+tobject.getType());
					tasks.add(tobject);
				 } 			
	     }
         
          }
                  
         
         if (goalname.equals("Flight_Leg_Completed")){
         
         {
         boolean canbescheduled=false;
		 Task tobject=null;		 
				// If a conversational initialization fails, a conventional one is tried
				 tobject=new Check_Leg_CompletedTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
				 canbescheduled=initialiseNonConversationalTask(tobject);
			 	 if (canbescheduled){
					//MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal Flight_Leg_Completed",getLocalName()+"-"+tobject.getType());
					tasks.add(tobject);
				 } 			
	     }
         
          }
                  
         
         if (goalname.equals("DecisionsForLegMade")){
         
         {
         boolean canbescheduled=false;
		 Task tobject=null;		 
				// If a conversational initialization fails, a conventional one is tried
				 tobject=new Make_DecisionsTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
				 canbescheduled=initialiseNonConversationalTask(tobject);
			 	 if (canbescheduled){
					//MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal DecisionsForLegMade",getLocalName()+"-"+tobject.getType());
					tasks.add(tobject);
				 } 			
	     }
         
          }
                  
         
         if (goalname.equals("PlaneStarted")){
         
         {
         boolean canbescheduled=false;
		 Task tobject=null;		 
				// If a conversational initialization fails, a conventional one is tried
				 tobject=new GoingToStartPlaneTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
				 canbescheduled=initialiseNonConversationalTask(tobject);
			 	 if (canbescheduled){
					//MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal PlaneStarted",getLocalName()+"-"+tobject.getType());
					tasks.add(tobject);
				 } 			
	     }
         
          }
                  
         
         Task tobject=new DeleteNonUsedEntitiesTask("DeleteNonUsedEntitiesTask","DeleteNonUsedEntitiesTask");
         boolean canbescheduled=initialiseNonConversationalTask(tobject);
		 if (canbescheduled && IAFProperties.getGarbageCollectionEnabled()){			
				tasks.add(tobject);
		 }
        return tasks;
	}


	/**
	 *  Initializes the agent
	 */
	public void setup() {
		super.setup();
		Vector<String> ttypes=new Vector<String>(); 
		          
         
                   
         ttypes.add("ObeyOrderCheck");					
         
         
                  
         
                   
         ttypes.add("TakeOrder");					
         
         
                  
         
                   
         ttypes.add("Take_Initial_Plan");					
         
         
                  
         
                   
         ttypes.add("IsNotInConflictNow");					
         
         
                  
         
                   
         ttypes.add("Check_Change");					
         
         
                  
         
                   
         ttypes.add("StartDescomposingPlan");					
         
         
         
         
         
         ttypes.add("Stop_Plane");		         
                
         
         
         ttypes.add("DescomposeDecision");		         
                
         
         
         ttypes.add("GoingNextLegWithConflict");		         
                
         
         
         ttypes.add("Flight_Plan_Monitoring");		         
                
         
         
         ttypes.add("IsTimeToStartPlane");		         
                
         
         
         ttypes.add("CreatePilotMind");		         
                
         
         
         ttypes.add("CreateNewLegForOrder");		         
                
         
         
         ttypes.add("ThinkNewDecision");		         
                
         
         
         ttypes.add("GoNextLeg");		         
                
         
         
         ttypes.add("Execute_Instruction");		         
                
         
         
         ttypes.add("Check_Leg_Completed");		         
                
         
         
         ttypes.add("Make_Decisions");		         
                
         
         
         ttypes.add("GoingToStartPlane");		         
                
         
         if (IAFProperties.getGraphicsOn())
          this.getGraphics().setKnownTasks(ttypes);

   // Interactions started by this agent		
   
   getCM().addKnownProtocol("StartPlaneInteraction");
   
   getCM().addKnownProtocol("PilotPlaneInteraction");
   
   boolean continueInit=false;
   // Interactions where this agent acts as collaborator
   
   getCM().addKnownProtocol("ControllerPilotInteraction");
   
   getCM().addKnownProtocol("FreeConflictInteraction");
   
   getCM().addKnownProtocol("FlightPlannerPilotInteraction");
   

   // These are the initial goals of the agent. Goals determine
   // which task to execute first    
   ingenias.editor.entities.StateGoal sg=null;
   ingenias.editor.entities.RuntimeFact ff=null;
   Slot slot=null;	  
   ObjectSlot oslot=null;
   ingenias.jade.components.Application app=null;	  
   
   sg= new ingenias.editor.entities.StateGoal("Flight_Plan_Completed");
   sg.setState("pending");
      try {
	   this.getMSM().addMentalEntity(sg);
   } catch (InvalidEntity e1) {

	   e1.printStackTrace();
   }
   
   sg= new ingenias.editor.entities.StateGoal("Arrived_To_Destination");
   sg.setState("pending");
      try {
	   this.getMSM().addMentalEntity(sg);
   } catch (InvalidEntity e1) {

	   e1.printStackTrace();
   }
   
   sg= new ingenias.editor.entities.StateGoal("Collision_Avoided");
   sg.setState("pending");
      try {
	   this.getMSM().addMentalEntity(sg);
   } catch (InvalidEntity e1) {

	   e1.printStackTrace();
   }
   
   sg= new ingenias.editor.entities.StateGoal("DecisionDescomposed");
   sg.setState("pending");
      try {
	   this.getMSM().addMentalEntity(sg);
   } catch (InvalidEntity e1) {

	   e1.printStackTrace();
   }
   
   sg= new ingenias.editor.entities.StateGoal("FlightPlanDescomposedWithConflict");
   sg.setState("pending");
      try {
	   this.getMSM().addMentalEntity(sg);
   } catch (InvalidEntity e1) {

	   e1.printStackTrace();
   }
   
   sg= new ingenias.editor.entities.StateGoal("Flight_Plan_Descomposed");
   sg.setState("pending");
      try {
	   this.getMSM().addMentalEntity(sg);
   } catch (InvalidEntity e1) {

	   e1.printStackTrace();
   }
   
   sg= new ingenias.editor.entities.StateGoal("TimeToStartPlane");
   sg.setState("pending");
      try {
	   this.getMSM().addMentalEntity(sg);
   } catch (InvalidEntity e1) {

	   e1.printStackTrace();
   }
   
   sg= new ingenias.editor.entities.StateGoal("Created_Pilot_Mind");
   sg.setState("pending");
      try {
	   this.getMSM().addMentalEntity(sg);
   } catch (InvalidEntity e1) {

	   e1.printStackTrace();
   }
   
   sg= new ingenias.editor.entities.StateGoal("NewLegForOrderCreated");
   sg.setState("pending");
      try {
	   this.getMSM().addMentalEntity(sg);
   } catch (InvalidEntity e1) {

	   e1.printStackTrace();
   }
   
   sg= new ingenias.editor.entities.StateGoal("Flight_Plan_Taken");
   sg.setState("pending");
      try {
	   this.getMSM().addMentalEntity(sg);
   } catch (InvalidEntity e1) {

	   e1.printStackTrace();
   }
   
   sg= new ingenias.editor.entities.StateGoal("NewDecisionThought");
   sg.setState("pending");
      try {
	   this.getMSM().addMentalEntity(sg);
   } catch (InvalidEntity e1) {

	   e1.printStackTrace();
   }
   
   sg= new ingenias.editor.entities.StateGoal("FinishedPlaneConflict");
   sg.setState("pending");
      try {
	   this.getMSM().addMentalEntity(sg);
   } catch (InvalidEntity e1) {

	   e1.printStackTrace();
   }
   
   sg= new ingenias.editor.entities.StateGoal("PreparedForNextLeg");
   sg.setState("pending");
      try {
	   this.getMSM().addMentalEntity(sg);
   } catch (InvalidEntity e1) {

	   e1.printStackTrace();
   }
   
   sg= new ingenias.editor.entities.StateGoal("Executed_Instruction");
   sg.setState("pending");
      try {
	   this.getMSM().addMentalEntity(sg);
   } catch (InvalidEntity e1) {

	   e1.printStackTrace();
   }
   
   sg= new ingenias.editor.entities.StateGoal("Flight_Leg_Completed");
   sg.setState("pending");
      try {
	   this.getMSM().addMentalEntity(sg);
   } catch (InvalidEntity e1) {

	   e1.printStackTrace();
   }
   
   sg= new ingenias.editor.entities.StateGoal("DecisionsForLegMade");
   sg.setState("pending");
      try {
	   this.getMSM().addMentalEntity(sg);
   } catch (InvalidEntity e1) {

	   e1.printStackTrace();
   }
   
   sg= new ingenias.editor.entities.StateGoal("Arrive_Safe_And_Sound");
   sg.setState("pending");
      try {
	   this.getMSM().addMentalEntity(sg);
   } catch (InvalidEntity e1) {

	   e1.printStackTrace();
   }
   
   sg= new ingenias.editor.entities.StateGoal("PlaneStarted");
   sg.setState("pending");
      try {
	   this.getMSM().addMentalEntity(sg);
   } catch (InvalidEntity e1) {

	   e1.printStackTrace();
   }
   
   
     

		//Initializing the applications panel in the manager
			
		Vector events=null;		
		RuntimeEvent event=null;
		
	//Initial applications assigned to the agent	  
	Vector actions=null;
	Vector evetns=null;

     //Initial applications assigned to the agent	  
   
     
     app=WhenStartPlaneInit.createInstance(this);
	 //app.registerOwner(this);
	 	    
     this.getAM().addApplication("WhenStartPlane",app);        
	 events=new Vector();
	 actions=new Vector();
		
	 event= new InitiateStartPlane();
	 /* 
	 */ 
	 events.add(event);
	 actions.add(generateActionListener(InitiateStartPlane.class));		

	 if (getGraphics()!=null)
	  getGraphics().addApplication("WhenStartPlane", events,actions);    

     //Initial applications assigned to the agent	  
   
     
     app=RadarInit.createInstance(this);
	 //app.registerOwner(this);
	 	    
     this.getAM().addApplication("Radar",app);        
	 events=new Vector();
	 actions=new Vector();
		
	 event= new ObstacleDetected();
	 /* 
	 */ 
	 events.add(event);
	 actions.add(generateActionListener(ObstacleDetected.class));		

	 if (getGraphics()!=null)
	  getGraphics().addApplication("Radar", events,actions);    

     //Initial applications assigned to the agent	  
   
     
     app=Leg_CheckerInit.createInstance(this);
	 //app.registerOwner(this);
	 	    
     this.getAM().addApplication("Leg_Checker",app);        
	 events=new Vector();
	 actions=new Vector();
		
	 event= new LegCompleted();
	 /* 
	 */ 
	 events.add(event);
	 actions.add(generateActionListener(LegCompleted.class));		

	 if (getGraphics()!=null)
	  getGraphics().addApplication("Leg_Checker", events,actions);    


   



   // Panel creation for interaction control
   // This panel shows a button for each interaction it starts.
   // If this agent does not start any interaction, a label showin
   // a message "DOES NOT START ANY INTERACTION" will appear
   java.awt.event.ActionListener ifPressed=null;
   
     final JADEAgent _jaPilotInitiatorStartPlaneInteraction=this;
     ifPressed=new java.awt.event.ActionListener() {
       public void actionPerformed(ActionEvent e) {
         _jaPilotInitiatorStartPlaneInteraction.addBehaviour(
             new jade.core.behaviours.OneShotBehaviour() {
           public void action() {
			 // If mental conditions are meet then the protocol is started
			 Vector<MentalEntity> expectedInput=null;
			 boolean allexist=true;
			 
			 if (allexist){
			  try {
			  getCM().launchProtocolAsInitiator("StartPlaneInteraction",getAM().getYellowPages());
              } catch (NoAgentsFound naf){
              } catch (WrongInteraction wi){
              	wi.printStackTrace();
              }
             } else {
              if (getGraphics()!=null)
                getGraphics().runtimeWarning(" Mental conditions required for starting PilotInitiatorStartPlaneInteraction "+
        				 " are not satisfied yet ");
             }

           }
         });
       } };
     if (getGraphics()!=null)
      getGraphics().addInteraction(this.getName(), "Role: PilotInitiator - Int: StartPlaneInteraction", ifPressed);
     getCM().addInitiatorRoles("StartPlaneInteraction","PilotInitiator");
     
     final JADEAgent _jaPilotInitiatorPilotPlaneInteraction=this;
     ifPressed=new java.awt.event.ActionListener() {
       public void actionPerformed(ActionEvent e) {
         _jaPilotInitiatorPilotPlaneInteraction.addBehaviour(
             new jade.core.behaviours.OneShotBehaviour() {
           public void action() {
			 // If mental conditions are meet then the protocol is started
			 Vector<MentalEntity> expectedInput=null;
			 boolean allexist=true;
			 
			 if (allexist){
			  try {
			  getCM().launchProtocolAsInitiator("PilotPlaneInteraction",getAM().getYellowPages());
              } catch (NoAgentsFound naf){
              } catch (WrongInteraction wi){
              	wi.printStackTrace();
              }
             } else {
              if (getGraphics()!=null)
                getGraphics().runtimeWarning(" Mental conditions required for starting PilotInitiatorPilotPlaneInteraction "+
        				 " are not satisfied yet ");
             }

           }
         });
       } };
     if (getGraphics()!=null)
      getGraphics().addInteraction(this.getName(), "Role: PilotInitiator - Int: PilotPlaneInteraction", ifPressed);
     getCM().addInitiatorRoles("PilotPlaneInteraction","PilotInitiator");
        

    
    // Final Graphics initialization
    if (getGraphics()!=null)
     getGraphics().startAgentDebug();
    
    getMSM().setModified(); // to trigger a first planning round
    // To indicate that the MSP can start
    this.agentInitialised();

	}




	/**
	 *  Obtains a DFAgentDescription array that describes the different roles an
       *  agent can play
	 *
	 *@return    Roles played
	 */
	public DFAgentDescription[ ] getDescription() {
               DFAgentDescription[] result=null;
	       Vector playedRoles=new Vector();
	        DFAgentDescription dfd=null;
                dfd = new DFAgentDescription();
                ServiceDescription sd=null;
                
		dfd.setName(getAID());
		sd = new ServiceDescription();
		sd.setName(getLocalName() + "-sub-df");
		sd.setType("Initiator");
		sd.setOwnership("JADE");
		dfd.addServices(sd);
                playedRoles.add(dfd);
                
		dfd.setName(getAID());
		sd = new ServiceDescription();
		sd.setName(getLocalName() + "-sub-df");
		sd.setType("Colaborator");
		sd.setOwnership("JADE");
		dfd.addServices(sd);
                playedRoles.add(dfd);
                
		dfd.setName(getAID());
		sd = new ServiceDescription();
		sd.setName(getLocalName() + "-sub-df");
		sd.setType("PilotInitiator");
		sd.setOwnership("JADE");
		dfd.addServices(sd);
                playedRoles.add(dfd);
                
		dfd.setName(getAID());
		sd = new ServiceDescription();
		sd.setName(getLocalName() + "-sub-df");
		sd.setType("PilotColaborator");
		sd.setOwnership("JADE");
		dfd.addServices(sd);
                playedRoles.add(dfd);
                
                
                result=new  DFAgentDescription[playedRoles.size()];
                playedRoles.toArray(result);
		return result;

	}


}

