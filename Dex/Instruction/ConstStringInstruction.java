package com.zoho.security.sca.android.Dex.Instruction;

import org.jf.dexlib2.iface.instruction.Instruction;
import org.jf.dexlib2.iface.instruction.OneRegisterInstruction;
import org.jf.dexlib2.iface.instruction.formats.Instruction21c;
import org.jf.dexlib2.iface.instruction.formats.Instruction31c;
import org.jf.dexlib2.iface.reference.StringReference;

import com.zoho.security.sca.android.Dex.adapter.DexMethodDefinition;
import com.zoho.security.sca.java.structure.Constant;

public class ConstStringInstruction extends DexlibAbstractInstruction {

	  public ConstStringInstruction(Instruction instruction, int codeAdress) {
	    super(instruction, codeAdress);
	  }

	@Override
	public void display() {
		// TODO Auto-generated method stub
		
		int dest = ((OneRegisterInstruction) instruction).getRegisterA();
	    String s;
	    if (instruction instanceof Instruction21c) {
	      Instruction21c i = (Instruction21c) instruction;
	      s = ((StringReference) (i.getReference())).getString();
	    } else if (instruction instanceof Instruction31c) {
	      Instruction31c i = (Instruction31c) instruction;
	      s = ((StringReference) (i.getReference())).getString();
	    } else {
	      throw new IllegalArgumentException("Expected Instruction21c or Instruction31c but got neither.");
	    }
	    
	    System.out.print("\t\t\t"+instruction.getOpcode().name+"\t");
	    DexMethodDefinition.registerFormatter.display(dest);
	    System.out.println(" = \""+s+"\"");
	    
	    Constant constant=new Constant(null, 0, 0, 0, 0);
	    constant.setValue(s);
	    DexMethodDefinition.registerLocals[dest]=constant;
	
	}
}
