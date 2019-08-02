//$Id$
package com.zoho.security.sca.android.Dex.Instruction;

import org.jf.dexlib2.Opcode;
import org.jf.dexlib2.iface.instruction.Instruction;
import org.jf.dexlib2.iface.instruction.formats.Instruction12x;
import com.zoho.security.sca.android.Dex.Tagkit.DexDoubleOpTag;
import com.zoho.security.sca.android.Dex.Tagkit.DexFloatOpTag;
import com.zoho.security.sca.android.Dex.Tagkit.DexIntOpTag;
import com.zoho.security.sca.android.Dex.Tagkit.DexLongOpTag;
import com.zoho.security.sca.android.Dex.adapter.DexMethodDefinition;

public class Binop2addrInstruction extends TaggedInstruction {

	  public Binop2addrInstruction(Instruction instruction, int codeAdress) {
	    super(instruction, codeAdress);
	  }

	  @Override
		public void display() {
			// TODO Auto-generated method stub
			if (!(instruction instanceof Instruction12x)) {
			      throw new IllegalArgumentException("Expected Instruction12x but got: " + instruction.getClass());
			    }

			Instruction12x binOp2AddrInstr = (Instruction12x) instruction;
		    int dest = binOp2AddrInstr.getRegisterA();

			    int source1 = binOp2AddrInstr.getRegisterA();
			    int source2 = binOp2AddrInstr.getRegisterB();

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
		      case ADD_LONG_2ADDR:
		        setTag(new DexLongOpTag());
		        return " + ";
		      case ADD_FLOAT_2ADDR:
		        setTag(new DexFloatOpTag());
		        return " + ";
		      case ADD_DOUBLE_2ADDR:
		        setTag(new DexDoubleOpTag());
		        return " + ";
		      case ADD_INT_2ADDR:
		        setTag(new DexIntOpTag());
		        return " + ";

		      case SUB_LONG_2ADDR:
		        setTag(new DexLongOpTag());
		        return " - ";
		      case SUB_FLOAT_2ADDR:
		        setTag(new DexFloatOpTag());
		        return " - ";
		      case SUB_DOUBLE_2ADDR:
		        setTag(new DexDoubleOpTag());
		        return " - ";
		      case SUB_INT_2ADDR:
		        setTag(new DexIntOpTag());
		        return " - ";

		      case MUL_LONG_2ADDR:
		        setTag(new DexLongOpTag());
		        return " * ";
		      case MUL_FLOAT_2ADDR:
		        setTag(new DexFloatOpTag());
		        return " * ";
		      case MUL_DOUBLE_2ADDR:
		        setTag(new DexDoubleOpTag());
		        return " * ";
		      case MUL_INT_2ADDR:
		        setTag(new DexIntOpTag());
		        return " * ";

		      case DIV_LONG_2ADDR:
		        setTag(new DexLongOpTag());
		        return " / ";
		      case DIV_FLOAT_2ADDR:
		        setTag(new DexFloatOpTag());
		        return " / ";
		      case DIV_DOUBLE_2ADDR:
		        setTag(new DexDoubleOpTag());
		        return " / ";
		      case DIV_INT_2ADDR:
		        setTag(new DexIntOpTag());
		        return " / ";

		      case REM_LONG_2ADDR:
		        setTag(new DexLongOpTag());
		        return " % ";
		      case REM_FLOAT_2ADDR:
		        setTag(new DexFloatOpTag());
		        return " % ";
		      case REM_DOUBLE_2ADDR:
		        setTag(new DexDoubleOpTag());
		        return " % ";
		      case REM_INT_2ADDR:
		        setTag(new DexIntOpTag());
		        return " % ";

		      case AND_LONG_2ADDR:
		        setTag(new DexLongOpTag());
		        return " & ";
		      case AND_INT_2ADDR:
		        setTag(new DexIntOpTag());
		        return " & ";

		      case OR_LONG_2ADDR:
		        setTag(new DexLongOpTag());
		        return " | ";
		      case OR_INT_2ADDR:
		        setTag(new DexIntOpTag());
		        return " | ";

		      case XOR_LONG_2ADDR:
		        setTag(new DexLongOpTag());
		        return " ^ ";
		      case XOR_INT_2ADDR:
		        setTag(new DexIntOpTag());
		        return " ^ ";

		      case SHL_LONG_2ADDR:
		        setTag(new DexLongOpTag());
		        return " << ";
		      case SHL_INT_2ADDR:
		        setTag(new DexIntOpTag());
		        return " << ";

		      case SHR_LONG_2ADDR:
		        setTag(new DexLongOpTag());
		        return " >> ";
		      case SHR_INT_2ADDR:
		        setTag(new DexIntOpTag());
		        return " >> ";

		      case USHR_LONG_2ADDR:
		        setTag(new DexLongOpTag());
		        return " >>> ";
		      case USHR_INT_2ADDR:
		        setTag(new DexIntOpTag());
		        return " >>> ";

		      default:
		        throw new RuntimeException("Invalid Opcode: " + opcode);
		    }
		}

	}
