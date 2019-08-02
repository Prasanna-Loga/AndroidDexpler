package com.zoho.security.sca.android.Dex.Instruction;

import org.jf.dexlib2.iface.instruction.Instruction;
import org.jf.dexlib2.iface.instruction.formats.Instruction21t;

import com.zoho.security.sca.android.Dex.adapter.DexMethodDefinition;

public class IfTestzInstruction extends ConditionalJumpInstruction {

	  public IfTestzInstruction(Instruction instruction, int codeAdress) {
	    super(instruction, codeAdress);
	  }

	  @Override
		public void display() {
			// TODO Auto-generated method stub
			  Instruction21t i = (Instruction21t) instruction;
			    
			  System.out.print("\t\t\t"+instruction.getOpcode().name+" ");
			  
			  DexMethodDefinition.registerFormatter.display(i.getRegisterA());
			  
			  super.display();
			  System.out.println(targetInstruction);
		}
}
