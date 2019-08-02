//$Id$
package com.zoho.security.sca.android.Dex.Instruction;

import org.jf.dexlib2.iface.instruction.Instruction;
import org.jf.dexlib2.iface.instruction.formats.Instruction21c;

import com.zoho.security.sca.android.Dex.adapter.DexMethodDefinition;
import com.zoho.security.sca.android.Dex.adapter.DexUtil;

public class CheckCastInstruction extends DexlibAbstractInstruction {

	  public CheckCastInstruction(Instruction instruction, int codeAdress) {
	    super(instruction, codeAdress);
	  }

	@Override
	public void display() {
		// TODO Auto-generated method stub
		DexUtil qualifiedName=new DexUtil();
		
		if (!(instruction instanceof Instruction21c)) {
		      throw new IllegalArgumentException("Expected Instruction21c but got: " + instruction.getClass());
		    }

		    Instruction21c checkCastInstr = (Instruction21c) instruction;

		    System.out.print("\t\t\t"+instruction.getOpcode().name+" ");
		    DexMethodDefinition.registerFormatter.display(checkCastInstr.getRegisterA());
		    System.out.println(" "+qualifiedName.getQualifiedName((checkCastInstr.getReference()).toString()));
		    
//		    Variable variable=new Variable(null,-1,-1,-1,-1);
//		    variable=(Variable)(DexMethodDefinition.registerLocals[checkCastInstr.getRegisterA()]);
//		    variable.setType(qualifiedName.getQualifiedName((checkCastInstr.getReference()).toString()));
//		    DexMethodDefinition.registerLocals[checkCastInstr.getRegisterA()]=variable;
	}
}
