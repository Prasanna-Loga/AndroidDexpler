//$Id$
package com.zoho.security.sca.android.Dex.Instruction;

import org.jf.dexlib2.iface.instruction.Instruction;

public abstract class DexlibAbstractInstruction {

	  protected int lineNumber = -1;

	  protected final Instruction instruction;
	  protected final int codeAddress;

	  public DexlibAbstractInstruction(Instruction instruction, int codeAddress) {
		    this.instruction = instruction;
		    this.codeAddress = codeAddress;
		  }
	  
	  public Instruction getInstruction() {
	    return instruction;
	  }
	  
	  public int getLineNumber() {
		    return lineNumber;
		  }

		  public void setLineNumber(int lineNumber) {
		    this.lineNumber = lineNumber;
		  }

		  /**
		   * Return the types that are be introduced by this instruction.
		   *
		   * Instructions that may introduce types should override this.
		   */
		  

		public abstract void display();

		
		
}
