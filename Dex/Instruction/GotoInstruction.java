package com.zoho.security.sca.android.Dex.Instruction;

import org.jf.dexlib2.iface.instruction.Instruction;

public class GotoInstruction extends JumpInstruction {
	  public GotoInstruction(Instruction instruction, int codeAdress) {
		    super(instruction, codeAdress);
	  }
	  @Override
	public void display() {
		// TODO Auto-generated method stub
		super.display();
		System.out.println("\t\t\t"+instruction.getOpcode().name);
	}
}
