
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

public class ControllerJADEAgent
		 extends JADEAgent {         
 
		 public ControllerJADEAgent(){
		 super(new ControllerProtocol(),new ControllerInteractionLocks());
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
  
		
		expectedInput=this.getMSM().getMentalEntityByType("ConflictFinished");
		if (this.getLM().canBeDeleted(expectedInput)){             
             if (expectedInput.size()==0){
				nonExistingInputs.add("ConflictFinished");
			 } else {
			    addExpectedInputs(tobject, "ConflictFinished","1",expectedInput);
             	addConsumedInput(to,"ConflictFinished",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist || expectedInput.size()!=0;
		} 
	      
		expectedInput=this.getMSM().getMentalEntityByType("Order");
		if (this.getLM().canBeDeleted(expectedInput)){             
             if (expectedInput.size()==0){
				nonExistingInputs.add("Order");
			 } else {
			    addExpectedInputs(tobject, "Order","1",expectedInput);
             	addConsumedInput(to,"Order",expectedInput);
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
         if (tobject.getType().equals("SendAvoidCollision") ){
            Vector<MentalEntity> expectedInput=null;
            RuntimeFact expectedOutput=null;
            RuntimeEvent expectedOutputEvent=null;
			RuntimeConversation expectedInt=null;
            ingenias.jade.components.Resource expectedResource=null;
			ingenias.jade.components.Application expectedApp=null;        
			boolean allEntitiesExist=true;		
			TaskOutput to=null;
			to=new TaskOutput("default");
		
	      
		
		
             expectedInput=this.getMSM().getMentalEntityByType("StartAvoidCollision");
             if (expectedInput.size()==0 && !("1".equals("0..n"))){
				nonExistingInputs.add("StartAvoidCollision");
			 } else {
			    addExpectedInputs(tobject, "StartAvoidCollision","1",expectedInput);
             	addConsumedInput(to,"1",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;

	      
	      
	     
	      
	     // Default application for all tasks executed within a conversation
	     expectedApp=(ingenias.jade.components.Application)getAM().getApplication("YellowPages");
             tobject.addApplication("YellowPages",expectedApp);
	 /*    
	     
			 expectedInt=new RuntimeConversation(MentalStateManager.generateMentalEntityID());
			 expectedInt.setInteraction(new Interaction("ControllerPilotInteraction"));
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
					m.invoke(expectedOutputRuntimeConversation, new Interaction("ControllerPilotInteraction")) ;	  
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
	     
	     
		    {Order expectedOutputOrder=		    
		     new Order(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputOrder,TaskOperations.CreateWF));
            }
	     
     
     		      
	      tobject.addOutput(to);
	      initialised= allEntitiesExist;

		if (!allEntitiesExist){
		   String[] nonexisting=new String[nonExistingInputs.size()];
		   for (int j=0;j<nonExistingInputs.size();j++){
				nonexisting[j]=nonExistingInputs.elementAt(j).toString();
			}
			EventManager.getInstance().conversationalInitializationOfTaskFailed(getLocalName(), 
												"Controller", 
												tobject, nonexisting);
		}
		return initialised;	       
	      }
                 
                  
                  
         nonExistingInputs.clear();
  		 repeatedOutputs.clear();
         if (tobject.getType().equals("PlaneNotInConflictMore") ){
            Vector<MentalEntity> expectedInput=null;
            RuntimeFact expectedOutput=null;
            RuntimeEvent expectedOutputEvent=null;
			RuntimeConversation expectedInt=null;
            ingenias.jade.components.Resource expectedResource=null;
			ingenias.jade.components.Application expectedApp=null;        
			boolean allEntitiesExist=true;		
			TaskOutput to=null;
			to=new TaskOutput("default");
		
	      
		
		
             expectedInput=this.getMSM().getMentalEntityByType("PlaneNoMoreInConflict");
             if (expectedInput.size()==0 && !("1".equals("0..n"))){
				nonExistingInputs.add("PlaneNoMoreInConflict");
			 } else {
			    addExpectedInputs(tobject, "PlaneNoMoreInConflict","1",expectedInput);
             	addConsumedInput(to,"1",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;

	      
	      
	     
	      
	     // Default application for all tasks executed within a conversation
	     expectedApp=(ingenias.jade.components.Application)getAM().getApplication("YellowPages");
             tobject.addApplication("YellowPages",expectedApp);
	 /*    
	     
			 expectedInt=new RuntimeConversation(MentalStateManager.generateMentalEntityID());
			 expectedInt.setInteraction(new Interaction("FreeConflictInteraction"));
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
					m.invoke(expectedOutputRuntimeConversation, new Interaction("FreeConflictInteraction")) ;	  
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
	     
	     
		    {ConflictFinished expectedOutputConflictFinished=		    
		     new ConflictFinished(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputConflictFinished,TaskOperations.CreateWF));
            }
	     
     
     		      
	      tobject.addOutput(to);
	      initialised= allEntitiesExist;

		if (!allEntitiesExist){
		   String[] nonexisting=new String[nonExistingInputs.size()];
		   for (int j=0;j<nonExistingInputs.size();j++){
				nonexisting[j]=nonExistingInputs.elementAt(j).toString();
			}
			EventManager.getInstance().conversationalInitializationOfTaskFailed(getLocalName(), 
												"Controller", 
												tobject, nonexisting);
		}
		return initialised;	       
	      }
                 
                  
                  
         nonExistingInputs.clear();
  		 repeatedOutputs.clear();
         if (tobject.getType().equals("IsANewConflictDetected") ){
            Vector<MentalEntity> expectedInput=null;
            RuntimeFact expectedOutput=null;
            RuntimeEvent expectedOutputEvent=null;
			RuntimeConversation expectedInt=null;
            ingenias.jade.components.Resource expectedResource=null;
			ingenias.jade.components.Application expectedApp=null;        
			boolean allEntitiesExist=true;		
			TaskOutput to=null;
			to=new TaskOutput("default");
		
             expectedInput=this.getMSM().getMentalEntityByType("ControllerMind");
             if (expectedInput.size()==0 && !("1".equals("0..n"))){
				nonExistingInputs.add("ControllerMind");
			 } else {
			  addExpectedInputs(tobject, "ControllerMind","1",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;             
             
	      
	      
		
		
             expectedInput=this.getMSM().getMentalEntityByType("PlanesInConflict");
             if (expectedInput.size()==0 && !("1".equals("0..n"))){
				nonExistingInputs.add("PlanesInConflict");
			 } else {
			    addExpectedInputs(tobject, "PlanesInConflict","1",expectedInput);
             	addConsumedInput(to,"1",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;

	      
	      
	     
	     expectedApp=(ingenias.jade.components.Application)getAM().getApplication("CheckDistanceBetweenPlanesInConflict");
             tobject.addApplication("CheckDistanceBetweenPlanesInConflict",expectedApp);
	      
	      
	     // Default application for all tasks executed within a conversation
	     expectedApp=(ingenias.jade.components.Application)getAM().getApplication("YellowPages");
             tobject.addApplication("YellowPages",expectedApp);
	 /*    
	     
	      */	  
	      
	      /**/	      
	      
 
	     
	     
		    {CanStartSendOrder expectedOutputCanStartSendOrder=		    
		     new CanStartSendOrder(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputCanStartSendOrder,TaskOperations.CreateWF));
            }
	     
     
     		      
	      tobject.addOutput(to);
	      initialised= allEntitiesExist;

		if (!allEntitiesExist){
		   String[] nonexisting=new String[nonExistingInputs.size()];
		   for (int j=0;j<nonExistingInputs.size();j++){
				nonexisting[j]=nonExistingInputs.elementAt(j).toString();
			}
			EventManager.getInstance().conversationalInitializationOfTaskFailed(getLocalName(), 
												"Controller", 
												tobject, nonexisting);
		}
		return initialised;	       
	      }
                 
                  
                  
         nonExistingInputs.clear();
  		 repeatedOutputs.clear();
         if (tobject.getType().equals("CreateNewOrders") ){
            Vector<MentalEntity> expectedInput=null;
            RuntimeFact expectedOutput=null;
            RuntimeEvent expectedOutputEvent=null;
			RuntimeConversation expectedInt=null;
            ingenias.jade.components.Resource expectedResource=null;
			ingenias.jade.components.Application expectedApp=null;        
			boolean allEntitiesExist=true;		
			TaskOutput to=null;
			to=new TaskOutput("default");
		
	      
		
		
             expectedInput=this.getMSM().getMentalEntityByType("CanStartSendOrder");
             if (expectedInput.size()==0 && !("1".equals("0..n"))){
				nonExistingInputs.add("CanStartSendOrder");
			 } else {
			    addExpectedInputs(tobject, "CanStartSendOrder","1",expectedInput);
             	addConsumedInput(to,"1",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;

	      
	      
	     
	      
	     // Default application for all tasks executed within a conversation
	     expectedApp=(ingenias.jade.components.Application)getAM().getApplication("YellowPages");
             tobject.addApplication("YellowPages",expectedApp);
	 /*    
	     
	      */	  
	      
	      /**/	      
	      
 
	     
	     
		    {StartAvoidCollision expectedOutputStartAvoidCollision=		    
		     new StartAvoidCollision(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputStartAvoidCollision,TaskOperations.CreateWF));
            }
	     
     
     		      
	      tobject.addOutput(to);
	      initialised= allEntitiesExist;

		if (!allEntitiesExist){
		   String[] nonexisting=new String[nonExistingInputs.size()];
		   for (int j=0;j<nonExistingInputs.size();j++){
				nonexisting[j]=nonExistingInputs.elementAt(j).toString();
			}
			EventManager.getInstance().conversationalInitializationOfTaskFailed(getLocalName(), 
												"Controller", 
												tobject, nonexisting);
		}
		return initialised;	       
	      }
                 
                  
                  
         nonExistingInputs.clear();
  		 repeatedOutputs.clear();
         if (tobject.getType().equals("CreateControllerMind") ){
            Vector<MentalEntity> expectedInput=null;
            RuntimeFact expectedOutput=null;
            RuntimeEvent expectedOutputEvent=null;
			RuntimeConversation expectedInt=null;
            ingenias.jade.components.Resource expectedResource=null;
			ingenias.jade.components.Application expectedApp=null;        
			boolean allEntitiesExist=true;		
			TaskOutput to=null;
			to=new TaskOutput("default");
		
             expectedInput=this.getMSM().getMentalEntityByType("ControllerMind");
             if (expectedInput.size()==0 && !("1".equals("0..n"))){
				nonExistingInputs.add("ControllerMind");
			 } else {
			  addExpectedInputs(tobject, "ControllerMind","1",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;             
             
	      
	      
		
		
             expectedInput=this.getMSM().getMentalEntityByType("StartCreateMind");
             if (expectedInput.size()==0 && !("1".equals("0..n"))){
				nonExistingInputs.add("StartCreateMind");
			 } else {
			    addExpectedInputs(tobject, "StartCreateMind","1",expectedInput);
             	addConsumedInput(to,"1",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;

	      
	      
	     
	      
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
												"Controller", 
												tobject, nonexisting);
		}
		return initialised;	       
	      }
                 
                  
                  
         nonExistingInputs.clear();
  		 repeatedOutputs.clear();
         if (tobject.getType().equals("StartMonitoringFlights") ){
            Vector<MentalEntity> expectedInput=null;
            RuntimeFact expectedOutput=null;
            RuntimeEvent expectedOutputEvent=null;
			RuntimeConversation expectedInt=null;
            ingenias.jade.components.Resource expectedResource=null;
			ingenias.jade.components.Application expectedApp=null;        
			boolean allEntitiesExist=true;		
			TaskOutput to=null;
			to=new TaskOutput("default");
		
	      
		
		
             expectedInput=this.getMSM().getMentalEntityByType("AllowStartMonitorizeFlights");
             if (expectedInput.size()==0 && !("1".equals("0..n"))){
				nonExistingInputs.add("AllowStartMonitorizeFlights");
			 } else {
			    addExpectedInputs(tobject, "AllowStartMonitorizeFlights","1",expectedInput);
             	addConsumedInput(to,"1",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;

	      
	      
	     
	     expectedApp=(ingenias.jade.components.Application)getAM().getApplication("FlightsMonitor");
             tobject.addApplication("FlightsMonitor",expectedApp);
	      
	      
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
												"Controller", 
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
  
		
		expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"ConflictFinished");
		if (this.getLM().canBeDeleted(expectedInput)){                          
             if (expectedInput.size()==0){
				nonExistingInputs.add("ConflictFinished");
			 } else {
			    addExpectedInputs(tobject, "ConflictFinished","1",expectedInput);
             	addConsumedInput(to,"ConflictFinished",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist|| expectedInput.size()!=0;
		} 
	      
		expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"Order");
		if (this.getLM().canBeDeleted(expectedInput)){                          
             if (expectedInput.size()==0){
				nonExistingInputs.add("Order");
			 } else {
			    addExpectedInputs(tobject, "Order","1",expectedInput);
             	addConsumedInput(to,"Order",expectedInput);
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
  	   	boolean correctRole=conversation.getPlayedRole().equals ("ControllerInitiator");
  	   	// Now all ascendant roles are verified, to enable tasks belonging to roles specializing a more
  	   	// generic one involved in an interaction
  	   	
  	   	correctRole=correctRole|| 
  	   	 conversation.getPlayedRole().equals ("Initiator");
  	   	
       	if (tobject.getType().equals("ObeyOrderCompleted") && (false ||
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
	     
	     	expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"ControllerMind");
			if (expectedInput.size()==0 && !("1".equals("0..n")))
				nonExistingInputs.add("ControllerMind");
			else
				addExpectedInputs(tobject, "", 
				"1", expectedInput);
		    allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;
	     
            
		
            expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"OrdenDone");
			if (expectedInput.size()==0 && !("1".equals("0..n")))
				nonExistingInputs.add("OrdenDone");
			else {
			    addExpectedInputs(tobject, "OrdenDone","1",expectedInput);
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
				 			getLocalName(), "Controller", 
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
					tobject=new ObeyOrderCompletedTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
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
         
         
         //************************************
         // Non conversational tasks evaluation
         //************************************
         
         if (goalname.equals("Collision_Avoided")){
         
         {
         boolean canbescheduled=false;
		 Task tobject=null;		 
				// If a conversational initialization fails, a conventional one is tried
				 tobject=new SendAvoidCollisionTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
				 canbescheduled=initialiseNonConversationalTask(tobject);
			 	 if (canbescheduled){
					//MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal Collision_Avoided",getLocalName()+"-"+tobject.getType());
					tasks.add(tobject);
				 } 			
	     }
         
          }
                  
         
         if (goalname.equals("FinishedPlaneConflict")){
         
         {
         boolean canbescheduled=false;
		 Task tobject=null;		 
				// If a conversational initialization fails, a conventional one is tried
				 tobject=new PlaneNotInConflictMoreTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
				 canbescheduled=initialiseNonConversationalTask(tobject);
			 	 if (canbescheduled){
					//MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal FinishedPlaneConflict",getLocalName()+"-"+tobject.getType());
					tasks.add(tobject);
				 } 			
	     }
         
          }
                  
         
         if (goalname.equals("NewConflictDetected")){
         
         {
         boolean canbescheduled=false;
		 Task tobject=null;		 
				// If a conversational initialization fails, a conventional one is tried
				 tobject=new IsANewConflictDetectedTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
				 canbescheduled=initialiseNonConversationalTask(tobject);
			 	 if (canbescheduled){
					//MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal NewConflictDetected",getLocalName()+"-"+tobject.getType());
					tasks.add(tobject);
				 } 			
	     }
         
          }
                  
         
         if (goalname.equals("SendOrderStarted")){
         
         {
         boolean canbescheduled=false;
		 Task tobject=null;		 
				// If a conversational initialization fails, a conventional one is tried
				 tobject=new CreateNewOrdersTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
				 canbescheduled=initialiseNonConversationalTask(tobject);
			 	 if (canbescheduled){
					//MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal SendOrderStarted",getLocalName()+"-"+tobject.getType());
					tasks.add(tobject);
				 } 			
	     }
         
          }
                  
         
         if (goalname.equals("ControllerMindCreated")){
         
         {
         boolean canbescheduled=false;
		 Task tobject=null;		 
				// If a conversational initialization fails, a conventional one is tried
				 tobject=new CreateControllerMindTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
				 canbescheduled=initialiseNonConversationalTask(tobject);
			 	 if (canbescheduled){
					//MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal ControllerMindCreated",getLocalName()+"-"+tobject.getType());
					tasks.add(tobject);
				 } 			
	     }
         
          }
                  
         
         if (goalname.equals("FlightsMonitorized")){
         
         {
         boolean canbescheduled=false;
		 Task tobject=null;		 
				// If a conversational initialization fails, a conventional one is tried
				 tobject=new StartMonitoringFlightsTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
				 canbescheduled=initialiseNonConversationalTask(tobject);
			 	 if (canbescheduled){
					//MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal FlightsMonitorized",getLocalName()+"-"+tobject.getType());
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
		          
         
                   
         ttypes.add("ObeyOrderCompleted");					
         
         
         
         
         
         ttypes.add("SendAvoidCollision");		         
                
         
         
         ttypes.add("PlaneNotInConflictMore");		         
                
         
         
         ttypes.add("IsANewConflictDetected");		         
                
         
         
         ttypes.add("CreateNewOrders");		         
                
         
         
         ttypes.add("CreateControllerMind");		         
                
         
         
         ttypes.add("StartMonitoringFlights");		         
                
         
         if (IAFProperties.getGraphicsOn())
          this.getGraphics().setKnownTasks(ttypes);

   // Interactions started by this agent		
   
   getCM().addKnownProtocol("ControllerPilotInteraction");
   
   getCM().addKnownProtocol("FreeConflictInteraction");
   
   boolean continueInit=false;
   // Interactions where this agent acts as collaborator
   

   // These are the initial goals of the agent. Goals determine
   // which task to execute first    
   ingenias.editor.entities.StateGoal sg=null;
   ingenias.editor.entities.RuntimeFact ff=null;
   Slot slot=null;	  
   ObjectSlot oslot=null;
   ingenias.jade.components.Application app=null;	  
   
   sg= new ingenias.editor.entities.StateGoal("Collision_Avoided");
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
   
   sg= new ingenias.editor.entities.StateGoal("NewConflictDetected");
   sg.setState("pending");
      try {
	   this.getMSM().addMentalEntity(sg);
   } catch (InvalidEntity e1) {

	   e1.printStackTrace();
   }
   
   sg= new ingenias.editor.entities.StateGoal("SendOrderStarted");
   sg.setState("pending");
      try {
	   this.getMSM().addMentalEntity(sg);
   } catch (InvalidEntity e1) {

	   e1.printStackTrace();
   }
   
   sg= new ingenias.editor.entities.StateGoal("ControllerMindCreated");
   sg.setState("pending");
      try {
	   this.getMSM().addMentalEntity(sg);
   } catch (InvalidEntity e1) {

	   e1.printStackTrace();
   }
   
   sg= new ingenias.editor.entities.StateGoal("FlightsMonitorized");
   sg.setState("pending");
      try {
	   this.getMSM().addMentalEntity(sg);
   } catch (InvalidEntity e1) {

	   e1.printStackTrace();
   }
   
   
   
   ff= new AllowStartMonitorizeFlights();      
   
   /* */
   		try {
			this.getMSM().addMentalEntity(ff);
		} catch (InvalidEntity e) {

			e.printStackTrace();
		}
   
   ff= new ControllerMind();      
   
   /* */
   		try {
			this.getMSM().addMentalEntity(ff);
		} catch (InvalidEntity e) {

			e.printStackTrace();
		}
   
   ff= new StartCreateMind();      
   
   /* */
   		try {
			this.getMSM().addMentalEntity(ff);
		} catch (InvalidEntity e) {

			e.printStackTrace();
		}
     

		//Initializing the applications panel in the manager
			
		Vector events=null;		
		RuntimeEvent event=null;
		
	//Initial applications assigned to the agent	  
	Vector actions=null;
	Vector evetns=null;

     //Initial applications assigned to the agent	  
   
     
     app=ConflictAttendedCheckerInit.createInstance(this);
	 //app.registerOwner(this);
	 	    
     this.getAM().addApplication("ConflictAttendedChecker",app);        
	 events=new Vector();
	 actions=new Vector();

	 if (getGraphics()!=null)
	  getGraphics().addApplication("ConflictAttendedChecker", events,actions);    

     //Initial applications assigned to the agent	  
   
     app=CheckDistanceBetweenPlanesInConflictInit.getInstance(this);
     //app.registerOwner(this);
	
     	    
     this.getAM().addApplication("CheckDistanceBetweenPlanesInConflict",app);        
	 events=new Vector();
	 actions=new Vector();
		
	 event= new PlaneNoMoreInConflict();
	 /*
	 slot=new Slot("441");
	 slot.setName("PlaneID");
	 slot.setType("ingenias.jade.AgentExternalDescription");
	 slot.setValue("");
	 event.addSlots(slot);
	  
	 */ 
	 events.add(event);
	 actions.add(generateActionListener(PlaneNoMoreInConflict.class));		

	 if (getGraphics()!=null)
	  getGraphics().addApplication("CheckDistanceBetweenPlanesInConflict", events,actions);    

     //Initial applications assigned to the agent	  
   
     
     app=FlightsMonitorInit.createInstance(this);
	 //app.registerOwner(this);
	 	    
     this.getAM().addApplication("FlightsMonitor",app);        
	 events=new Vector();
	 actions=new Vector();
		
	 event= new PlanesInConflict();
	 /*
	 slot=new Slot("236");
	 slot.setName("PlanesInConflict");
	 slot.setType("ArrayList<jade.core.AID>");
	 slot.setValue("");
	 event.addSlots(slot);
	 
	 slot=new Slot("382");
	 slot.setName("RiskValue");
	 slot.setType("int");
	 slot.setValue("");
	 event.addSlots(slot);
	  
	 */ 
	 events.add(event);
	 actions.add(generateActionListener(PlanesInConflict.class));		

	 if (getGraphics()!=null)
	  getGraphics().addApplication("FlightsMonitor", events,actions);    


   



   // Panel creation for interaction control
   // This panel shows a button for each interaction it starts.
   // If this agent does not start any interaction, a label showin
   // a message "DOES NOT START ANY INTERACTION" will appear
   java.awt.event.ActionListener ifPressed=null;
   
     final JADEAgent _jaControllerInitiatorControllerPilotInteraction=this;
     ifPressed=new java.awt.event.ActionListener() {
       public void actionPerformed(ActionEvent e) {
         _jaControllerInitiatorControllerPilotInteraction.addBehaviour(
             new jade.core.behaviours.OneShotBehaviour() {
           public void action() {
			 // If mental conditions are meet then the protocol is started
			 Vector<MentalEntity> expectedInput=null;
			 boolean allexist=true;
			 
			 if (allexist){
			  try {
			  getCM().launchProtocolAsInitiator("ControllerPilotInteraction",getAM().getYellowPages());
              } catch (NoAgentsFound naf){
              } catch (WrongInteraction wi){
              	wi.printStackTrace();
              }
             } else {
              if (getGraphics()!=null)
                getGraphics().runtimeWarning(" Mental conditions required for starting ControllerInitiatorControllerPilotInteraction "+
        				 " are not satisfied yet ");
             }

           }
         });
       } };
     if (getGraphics()!=null)
      getGraphics().addInteraction(this.getName(), "Role: ControllerInitiator - Int: ControllerPilotInteraction", ifPressed);
     getCM().addInitiatorRoles("ControllerPilotInteraction","ControllerInitiator");
     
     final JADEAgent _jaControllerInitiatorFreeConflictInteraction=this;
     ifPressed=new java.awt.event.ActionListener() {
       public void actionPerformed(ActionEvent e) {
         _jaControllerInitiatorFreeConflictInteraction.addBehaviour(
             new jade.core.behaviours.OneShotBehaviour() {
           public void action() {
			 // If mental conditions are meet then the protocol is started
			 Vector<MentalEntity> expectedInput=null;
			 boolean allexist=true;
			 
			 if (allexist){
			  try {
			  getCM().launchProtocolAsInitiator("FreeConflictInteraction",getAM().getYellowPages());
              } catch (NoAgentsFound naf){
              } catch (WrongInteraction wi){
              	wi.printStackTrace();
              }
             } else {
              if (getGraphics()!=null)
                getGraphics().runtimeWarning(" Mental conditions required for starting ControllerInitiatorFreeConflictInteraction "+
        				 " are not satisfied yet ");
             }

           }
         });
       } };
     if (getGraphics()!=null)
      getGraphics().addInteraction(this.getName(), "Role: ControllerInitiator - Int: FreeConflictInteraction", ifPressed);
     getCM().addInitiatorRoles("FreeConflictInteraction","ControllerInitiator");
        

    
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
		sd.setType("ControllerInitiator");
		sd.setOwnership("JADE");
		dfd.addServices(sd);
                playedRoles.add(dfd);
                
                
                result=new  DFAgentDescription[playedRoles.size()];
                playedRoles.toArray(result);
		return result;

	}


}

