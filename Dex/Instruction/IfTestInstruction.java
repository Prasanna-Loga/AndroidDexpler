package com.zoho.security.sca.android.Dex.Instruction;

import org.jf.dexlib2.iface.instruction.Instruction;
import org.jf.dexlib2.iface.instruction.formats.Instruction22t;

import com.zoho.security.sca.android.Dex.adapter.DexMethodDefinition;


public class IfTestInstruction extends ConditionalJumpInstruction {

	  public IfTestInstruction(Instruction instruction, int codeAdress) {
	    super(instruction, codeAdress);
	  }
	  
	  @Override
	public void display() {
		// TODO Auto-generated method stub
		  Instruction22t i = (Instruction22t) instruction;
		    
		  System.out.print("\t\t\t"+instruction.getOpcode().name+" ");
		  
		  DexMethodDefinition.registerFormatter.display(i.getRegisterA());
		  System.out.print(",");
		  DexMethodDefinition.registerFormatter.display(i.getRegisterB());
		  
		  super.display();
		  System.out.println(targetInstruction);
	}
}
