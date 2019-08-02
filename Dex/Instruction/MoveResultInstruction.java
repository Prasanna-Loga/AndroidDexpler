package com.zoho.security.sca.android.Dex.Instruction;

import org.jf.dexlib2.iface.instruction.Instruction;
import org.jf.dexlib2.iface.instruction.OneRegisterInstruction;

import com.zoho.security.sca.android.Dex.adapter.DexMethodDefinition;

public class MoveResultInstruction extends DexlibAbstractInstruction {

	  public MoveResultInstruction(Instruction instruction, int codeAdress) {
	    super(instruction, codeAdress);
	  }

	  /**
	   * Move result move storedResultLocal to registerLocal respectively
	   */
	@Override
	public void display() {
		// TODO Auto-generated method stub
		int dest = ((OneRegisterInstruction) instruction).getRegisterA();

		System.out.print("\t\t\t"+instruction.getOpcode().name+"\t");
		
		DexMethodDefinition.registerFormatter.display(dest);
		
		System.out.println();
		
		DexMethodDefinition.registerLocals[dest]=DexMethodDefinition.storeResultLocal;
			}

}
