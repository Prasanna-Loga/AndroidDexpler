package com.zoho.security.sca.android.Dex.Instruction;

import org.jf.dexlib2.Opcode;
import org.jf.dexlib2.iface.instruction.Instruction;
import org.jf.dexlib2.iface.instruction.formats.Instruction23x;

import com.zoho.security.sca.android.Dex.Tagkit.DexDoubleOpTag;
import com.zoho.security.sca.android.Dex.Tagkit.DexFloatOpTag;
import com.zoho.security.sca.android.Dex.Tagkit.DexIntOpTag;
import com.zoho.security.sca.android.Dex.Tagkit.DexLongOpTag;
import com.zoho.security.sca.android.Dex.adapter.DexMethodDefinition;


public class BinopInstruction extends TaggedInstruction {

	  public BinopInstruction(Instruction instruction, int codeAdress) {
	    super(instruction, codeAdress);
	  }

	@Override
	public void display() {
		// TODO Auto-generated method stub
		if (!(instruction instanceof Instruction23x)) {
		      throw new IllegalArgumentException("Expected Instruction23x but got: " + instruction.getClass());
		    }

		    Instruction23x binOpInstr = (Instruction23x) instruction;
		    int dest = binOpInstr.getRegisterA();

		    int source1 = binOpInstr.getRegisterB();
		    int source2 = binOpInstr.getRegisterC();

		    System.out.print("\t\t\t"+instruction.getOpcode().name+"  ");
		    DexMethodDefinition.registerFormatter.display(dest);
		    System.out.print(" = ");
		    DexMethodDefinition.registerFormatter.display(source1);
		    System.out.print(getExpression());
		    DexMethodDefinition.registerFormatter.display(source2);
		    System.out.println();

	}

	private String getExpression() {
		// TODO Auto-generated method stub
		Opcode opcode = instruction.getOpcode();
	    switch (opcode) {
	      case ADD_LONG:
	        setTag(new DexLongOpTag());
	        return " + ";
	      case ADD_FLOAT:
	        setTag(new DexFloatOpTag());
	        return " + ";
	      case ADD_DOUBLE:
	        setTag(new DexDoubleOpTag());
	        return " + ";
	      case ADD_INT:
	        setTag(new DexIntOpTag());
	        return " + ";

	      case SUB_LONG:
	        setTag(new DexLongOpTag());
	        return " - ";
	      case SUB_FLOAT:
	        setTag(new DexFloatOpTag());
	        return " - ";
	      case SUB_DOUBLE:
	        setTag(new DexDoubleOpTag());
	        return " - ";
	      case SUB_INT:
	        setTag(new DexIntOpTag());
	        return " - ";

	      case MUL_LONG:
	        setTag(new DexLongOpTag());
	        return " * ";
	      case MUL_FLOAT:
	        setTag(new DexFloatOpTag());
	        return " * ";
	      case MUL_DOUBLE:
	        setTag(new DexDoubleOpTag());
	        return " * ";
	      case MUL_INT:
	        setTag(new DexIntOpTag());
	        return " * ";

	      case DIV_LONG:
	        setTag(new DexLongOpTag());
	        return " / ";
	      case DIV_FLOAT:
	        setTag(new DexFloatOpTag());
	        return " / ";
	      case DIV_DOUBLE:
	        setTag(new DexDoubleOpTag());
	        return " / ";
	      case DIV_INT:
	        setTag(new DexIntOpTag());
	        return " / ";

	      case REM_LONG:
	        setTag(new DexLongOpTag());
	        return " % ";
	      case REM_FLOAT:
	        setTag(new DexFloatOpTag());
	        return " % ";
	      case REM_DOUBLE:
	        setTag(new DexDoubleOpTag());
	        return " % ";
	      case REM_INT:
	        setTag(new DexIntOpTag());
	        return " % ";

	      case AND_LONG:
	        setTag(new DexLongOpTag());
	        return " & ";
	      case AND_INT:
	        setTag(new DexIntOpTag());
	        return " & ";

	      case OR_LONG:
	        setTag(new DexLongOpTag());
	        return " | ";
	      case OR_INT:
	        setTag(new DexIntOpTag());
	        return " | ";

	      case XOR_LONG:
	        setTag(new DexLongOpTag());
	        return " ^ ";
	      case XOR_INT:
	        setTag(new DexIntOpTag());
	        return " ^ ";

	      case SHL_LONG:
	        setTag(new DexLongOpTag());
	        return " << ";
	      case SHL_INT:
	        setTag(new DexIntOpTag());
	        return " << ";

	      case SHR_LONG:
	        setTag(new DexLongOpTag());
	        return " >> ";
	      case SHR_INT:
	        setTag(new DexIntOpTag());
	        return " >> ";

	      case USHR_LONG:
	        setTag(new DexLongOpTag());
	        return " >>> ";
	      case USHR_INT:
	        setTag(new DexIntOpTag());
	        return " >>> ";

	      default:
	        throw new RuntimeException("Invalid Opcode: " + opcode);
	    }
	}

}
