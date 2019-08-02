//$Id$
package com.zoho.security.sca.android.Dex.Instruction;

import java.util.Map;

import org.jf.dexlib2.iface.instruction.Instruction;
import org.jf.dexlib2.iface.instruction.OneRegisterInstruction;
import org.jf.dexlib2.iface.instruction.ReferenceInstruction;
import org.jf.dexlib2.iface.reference.FieldReference;

import com.zoho.security.sca.android.Dex.adapter.DexMethodDefinition;
import com.zoho.security.sca.java.structure.Component;
import com.zoho.security.sca.java.structure.Constant;
import com.zoho.security.sca.java.structure.Variable;

public class SputInstruction extends FieldInstruction {

	public SputInstruction(Instruction instruction, int codeAdress) {
		super(instruction, codeAdress);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		super.display();
		
		int source = ((OneRegisterInstruction) instruction).getRegisterA();
	    FieldReference f = (FieldReference) ((ReferenceInstruction) instruction).getReference();
	    
	    Map<String, Variable> variableMap=null;
			variableMap=DexMethodDefinition.classDef.classNode.getVariableMap();
			if(variableMap!=null && variableMap.containsKey(f.getName())){
	    		Variable staticVariable=variableMap.get(f.getName());
	    		Component component=DexMethodDefinition.registerLocals[source];
	    		
	    		
	    		if(component instanceof Constant){
	    			String cst=((Constant)component).getValue();
	    			switch(instruction.getOpcode()){
	    			case SPUT:
	    				if(f.getType().equals("F"))
	    					cst = Float.toString(Float.parseFloat(cst));
	    				break;
	    			case SPUT_WIDE:
	    				if(f.getType().equals("D"))
	    					cst = Double.toString(Double.parseDouble(cst));
	    				break;
	    			case SPUT_OBJECT:
	    				if(f.getType().equals("Ljava/lang/Boolean;"))
	    					if(cst.equals("0"))
	    						cst="false";
	    					else
	    						cst="true";
	    				else
	    					if(cst==null||cst.equals("0"))
	    						cst=null;
	    				break;
					default:
						break;
	    			}
	    			((Constant)component).setValue(cst);
	    		}
	    		staticVariable.addAssignment(component);	    	
	    		}
	    
	}
}
