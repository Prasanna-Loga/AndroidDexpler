package com.zoho.security.sca.android.Dex.Instruction;

import org.jf.dexlib2.iface.instruction.Instruction;
import org.jf.dexlib2.iface.instruction.TwoRegisterInstruction;

import com.zoho.security.sca.android.Dex.adapter.DexMethodDefinition;

public class MoveInstruction extends DexlibAbstractInstruction {

	  public MoveInstruction(Instruction instruction, int codeAdress) {
	    super(instruction, codeAdress);
	  }

	  /**
	   * Move instruction updates registerLocal 
	   */
	@Override
	public void display() {
		// TODO Auto-generated method stub
		
		TwoRegisterInstruction i = (TwoRegisterInstruction) instruction;

	    int dest = i.getRegisterA();
	    int source = i.getRegisterB();
	    
	    System.out.print("\t\t\t"+instruction.getOpcode().name+"\t");
	   
	    DexMethodDefinition.registerFormatter.display(dest);System.out.print("=");
	    DexMethodDefinition.registerFormatter.display(source);
	    System.out.println(dest +"="+ source);
	    DexMethodDefinition.registerLocals[dest]=DexMethodDefinition.registerLocals[source];
	    System.out.println();
	}
}
