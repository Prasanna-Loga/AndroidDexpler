package com.zoho.security.sca.android.Dex.Instruction;

import org.jf.dexlib2.Opcode;
import org.jf.dexlib2.iface.instruction.Instruction;
import org.jf.dexlib2.iface.instruction.NarrowLiteralInstruction;
import org.jf.dexlib2.iface.instruction.TwoRegisterInstruction;
import org.jf.dexlib2.iface.instruction.formats.Instruction22b;
import org.jf.dexlib2.iface.instruction.formats.Instruction22s;

import com.zoho.security.sca.android.Dex.Tagkit.DexIntOpTag;
import com.zoho.security.sca.android.Dex.adapter.DexMethodDefinition;

public class BinopLitInstruction extends TaggedInstruction {

	  public BinopLitInstruction(Instruction instruction, int codeAdress) {
	    super(instruction, codeAdress);
	  }
	  

		@Override
		public void display() {
			// TODO Auto-generated method stub
			if (!(instruction instanceof Instruction22s) && !(instruction instanceof Instruction22b)) {
			      throw new IllegalArgumentException("Expected Instruction22s or Instruction22b but got: " + instruction.getClass());
			    }

			    NarrowLiteralInstruction binOpLitInstr = (NarrowLiteralInstruction) this.instruction;
			    int dest = ((TwoRegisterInstruction) instruction).getRegisterA();
			    int source1 = ((TwoRegisterInstruction) instruction).getRegisterB();

			    int source2 = binOpLitInstr.getNarrowLiteral();
			    System.out.print("\t\t\t"+instruction.getOpcode().name+"  ");
			    DexMethodDefinition.registerFormatter.display(dest);
			    System.out.print(" = ");
			    DexMethodDefinition.registerFormatter.display(source1);
			    System.out.print(getExpression()+source2);
			    
			    System.out.println();

		}

		private String getExpression() {
			// TODO Auto-generated method stub
			Opcode opcode = instruction.getOpcode();
		    switch (opcode) {
		    case ADD_INT_LIT16:
		        setTag(new DexIntOpTag());
		      case ADD_INT_LIT8:
		        setTag(new DexIntOpTag());
		        return " + ";

		      case RSUB_INT:
		        setTag(new DexIntOpTag());
		      case RSUB_INT_LIT8:
		        setTag(new DexIntOpTag());
		        return " - ";

		      case MUL_INT_LIT16:
		        setTag(new DexIntOpTag());
		      case MUL_INT_LIT8:
		        setTag(new DexIntOpTag());
		        return " * ";

		      case DIV_INT_LIT16:
		        setTag(new DexIntOpTag());
		      case DIV_INT_LIT8:
		        setTag(new DexIntOpTag());
		        return " / ";

		      case REM_INT_LIT16:
		        setTag(new DexIntOpTag());
		      case REM_INT_LIT8:
		        setTag(new DexIntOpTag());
		        return " % ";

		      case AND_INT_LIT8:
		        setTag(new DexIntOpTag());
		      case AND_INT_LIT16:
		        setTag(new DexIntOpTag());
		        return " & ";

		      case OR_INT_LIT16:
		        setTag(new DexIntOpTag());
		      case OR_INT_LIT8:
		        setTag(new DexIntOpTag());
		        return " | ";

		      case XOR_INT_LIT16:
		        setTag(new DexIntOpTag());
		      case XOR_INT_LIT8:
		        setTag(new DexIntOpTag());
		        return " ^ ";

		      case SHL_INT_LIT8:
		        setTag(new DexIntOpTag());
		        return " << ";

		      case SHR_INT_LIT8:
		        setTag(new DexIntOpTag());
		        return " >> ";

		      case USHR_INT_LIT8:
		        setTag(new DexIntOpTag());
		        return " >>> ";

		      default:
		        throw new RuntimeException("Invalid Opcode: " + opcode);
		    }
		}

	}