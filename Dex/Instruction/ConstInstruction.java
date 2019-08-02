//$Id$
package com.zoho.security.sca.android.Dex.Instruction;

import org.jf.dexlib2.iface.instruction.Instruction;
import org.jf.dexlib2.iface.instruction.NarrowLiteralInstruction;
import org.jf.dexlib2.iface.instruction.OneRegisterInstruction;
import org.jf.dexlib2.iface.instruction.WideLiteralInstruction;

import com.zoho.security.sca.android.Dex.adapter.DexMethodDefinition;
import com.zoho.security.sca.java.structure.Constant;

public class ConstInstruction extends DexlibAbstractInstruction {

	  public ConstInstruction(Instruction instruction, int codeAdress) {
	    super(instruction, codeAdress);
	  }

	@Override
	public void display() {
		// TODO Auto-generated method stub
		
		int dest = ((OneRegisterInstruction) instruction).getRegisterA();

		long literal = 0;

	    if (instruction instanceof WideLiteralInstruction) {
	      literal = ((WideLiteralInstruction) instruction).getWideLiteral();
	    } else if (instruction instanceof NarrowLiteralInstruction) {
	      literal = ((NarrowLiteralInstruction) instruction).getNarrowLiteral();
	    } else {
	      throw new RuntimeException("literal error: expected narrow or wide literal.");
	    }
	    
	    System.out.print("line"+getLineNumber());
	    System.out.print("\t\t\t"+instruction.getOpcode().name+"\t");
	    DexMethodDefinition.registerFormatter.display(dest);
	    System.out.println(" = "+literal);
	    
	    Constant constant=new Constant(null,0, 0, 0, 0);
	    constant.setValue(Long.toString(literal));
	    DexMethodDefinition.registerLocals[dest]=constant;
	}

}
