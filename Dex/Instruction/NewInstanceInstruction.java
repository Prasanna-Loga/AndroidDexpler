//$Id$
package com.zoho.security.sca.android.Dex.Instruction;

import org.jf.dexlib2.iface.instruction.Instruction;
import org.jf.dexlib2.iface.instruction.formats.Instruction21c;
import org.jf.dexlib2.iface.reference.TypeReference;

import com.zoho.security.sca.android.Dex.adapter.DexMethodDefinition;
import com.zoho.security.sca.android.Dex.adapter.DexUtil;
import com.zoho.security.sca.java.structure.Variable;

public class NewInstanceInstruction extends DexlibAbstractInstruction{

	public NewInstanceInstruction(Instruction instruction, int codeAddress) {
		super(instruction, codeAddress);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		Instruction21c i = (Instruction21c) instruction;
	    int dest = i.getRegisterA();
	    
	    DexUtil qualifiedName= new DexUtil();
	    String classNameType=qualifiedName.getQualifiedName(((TypeReference)i.getReference()).toString());
	
	    Variable variable= new Variable(null, -1, -1 ,-1 ,-1);
	    variable.setType(classNameType);
	    DexMethodDefinition.registerLocals[dest]=variable;
	}

}
