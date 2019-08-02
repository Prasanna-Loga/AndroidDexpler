//$Id$
package com.zoho.security.sca.android.Dex.Instruction;

import org.jf.dexlib2.iface.instruction.Instruction;
import org.jf.dexlib2.iface.instruction.formats.Instruction11x;

import com.zoho.security.sca.android.Dex.adapter.DexMethodDefinition;


public class ReturnInstruction extends DexlibAbstractInstruction {

	  public ReturnInstruction(Instruction instruction, int codeAdress) {
	    super(instruction, codeAdress);
	  }
	  
	  /**
	   * Return instruction returns result which will be stored in storedResultLocal
	   */
	  @Override
	  public void display() {
		// TODO Auto-generated method stub
		  //System.out.print(((ReferenceInstruction) instruction).getReference()+"***********************************");

		  System.out.print("\t\t\t"+instruction.getOpcode().name+"\t");
		  
		  Instruction11x returnInstruction = (Instruction11x) this.instruction;
		  System.out.println("\tv"+returnInstruction.getRegisterA());
		  
		  DexMethodDefinition.storeResultLocal=DexMethodDefinition.registerLocals[returnInstruction.getRegisterA()];
		 }

}
