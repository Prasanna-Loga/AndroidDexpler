//$Id$
package com.zoho.security.sca.android.Dex.Instruction;

import org.jf.dexlib2.iface.instruction.Instruction;
import org.jf.dexlib2.iface.instruction.ReferenceInstruction;
import org.jf.dexlib2.iface.instruction.TwoRegisterInstruction;
import org.jf.dexlib2.iface.reference.FieldReference;

import com.zoho.security.sca.android.Dex.adapter.DexMethodDefinition;
import com.zoho.security.sca.android.Dex.adapter.DexUtil;
import com.zoho.security.sca.java.structure.Variable;

public class IgetInstruction extends FieldInstruction{

	public IgetInstruction(Instruction instruction, int codeAdress) {
		super(instruction, codeAdress);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		super.display();
		
		DexUtil qualifiedName=new DexUtil();
		TwoRegisterInstruction i = (TwoRegisterInstruction) instruction;
	    int dest = i.getRegisterA();
	    int object = i.getRegisterB();
	    FieldReference f = (FieldReference) ((ReferenceInstruction) instruction).getReference();
	    
	    String name= qualifiedName.getQualifiedName(f.getDefiningClass())+"."+qualifiedName.getQualifiedName(f.getName());
	    String type= qualifiedName.getQualifiedName(f.getType());
	    
	    Variable variable=new Variable(null, 0, 0, 0, 0);
	    
	    variable.setName(name);
	    variable.setType(type);
	    
	    System.out.println(dest+" "+object);
	   DexMethodDefinition.registerLocals[dest]=variable;
	    
	}
}
