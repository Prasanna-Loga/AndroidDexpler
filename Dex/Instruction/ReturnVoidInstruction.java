package com.zoho.security.sca.android.Dex.Instruction;

import org.jf.dexlib2.iface.instruction.Instruction;

public class ReturnVoidInstruction extends DexlibAbstractInstruction {

	  public ReturnVoidInstruction(Instruction instruction, int codeAddress) {
		super(instruction, codeAddress);
		// TODO Auto-generated constructor stub
	}
	  
	  public void display() {
		// TODO Auto-generated method stub
		 
		  System.out.print("\t\t\t"+instruction.getOpcode().name);
	  }
}
