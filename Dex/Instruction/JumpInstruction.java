package com.zoho.security.sca.android.Dex.Instruction;

import org.jf.dexlib2.iface.instruction.Instruction;
import org.jf.dexlib2.iface.instruction.OffsetInstruction;

import com.zoho.security.sca.android.Dex.adapter.DexMethodDefinition;

public class JumpInstruction extends DexlibAbstractInstruction {
	  protected DexlibAbstractInstruction targetInstruction;
	  
	  public JumpInstruction(Instruction instruction, int codeAdress) {
	    super(instruction, codeAdress);
	  }

	  protected DexlibAbstractInstruction getTargetInstruction(DexMethodDefinition method) {
	    int offset = ((OffsetInstruction) instruction).getCodeOffset();
	    int targetAddress = codeAddress + offset;
	    targetInstruction = method.instructionAtAddress(targetAddress);
	    return targetInstruction;
	  }

	@Override
	public void display() {
		// TODO Auto-generated method stub

	}
	}