//$Id$
package com.zoho.security.sca.android.Dex.Instruction;

import java.util.Map;

import org.jf.dexlib2.iface.instruction.Instruction;
import org.jf.dexlib2.iface.instruction.ReferenceInstruction;
import org.jf.dexlib2.iface.instruction.TwoRegisterInstruction;
import org.jf.dexlib2.iface.reference.FieldReference;

import com.zoho.security.sca.android.Dex.adapter.DexMethodDefinition;
import com.zoho.security.sca.java.structure.Component;
import com.zoho.security.sca.java.structure.Variable;

public class IputInstruction extends FieldInstruction{

	public IputInstruction(Instruction instruction, int codeAdress) {
		super(instruction, codeAdress);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		super.display();
		
		TwoRegisterInstruction i = (TwoRegisterInstruction) instruction;
	    int source = i.getRegisterA();
	    int object = i.getRegisterB();
	    FieldReference f = (FieldReference) ((ReferenceInstruction) instruction).getReference();
	   
	    Map<String, Variable> variableMap=null;
		variableMap=DexMethodDefinition.classDef.classNode.getVariableMap();
		
		DexMethodDefinition.registerLocals[object]=DexMethodDefinition.registerLocals[source];
		
		if(variableMap!=null && variableMap.containsKey(f.getName())){
    		Variable instanceVariable=variableMap.get(f.getName());
    		if(source<DexMethodDefinition.methodImpl.getRegisterCount()){
    		Component component=DexMethodDefinition.registerLocals[source];
    		
    		instanceVariable.addAssignment(component);	    	
    		}
    		}
	}
}
