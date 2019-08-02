package com.zoho.security.sca.android.Dex.Instruction;

import org.jf.dexlib2.iface.instruction.Instruction;

import com.zoho.security.sca.android.Dex.Tagkit.DexTag;

public abstract class TaggedInstruction extends DexlibAbstractInstruction {

	  private DexTag instructionTag = null;

	  public TaggedInstruction(Instruction instruction, int codeAddress) {
	    super(instruction, codeAddress);
	  }

	  public void setTag(DexTag t) {
	    instructionTag = t;
	  }

	  public DexTag getTag() {
	    if (instructionTag == null) {
	      throw new RuntimeException(
	          "Must tag instruction first! (0x" + Integer.toHexString(codeAddress) + ": " + instruction + ")");
	    }
	    return instructionTag;
	  }
}