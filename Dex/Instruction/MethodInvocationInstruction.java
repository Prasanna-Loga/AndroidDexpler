package com.zoho.security.sca.android.Dex.Instruction;

import java.util.List;
import org.jf.dexlib2.iface.instruction.FiveRegisterInstruction;
import org.jf.dexlib2.iface.instruction.Instruction;
import org.jf.dexlib2.iface.instruction.ReferenceInstruction;
import org.jf.dexlib2.iface.instruction.RegisterRangeInstruction;
import org.jf.dexlib2.iface.reference.MethodReference;

import com.zoho.security.sca.android.Dex.adapter.DexMethodDefinition;
import com.zoho.security.sca.android.Dex.adapter.DexUtil;

public abstract class MethodInvocationInstruction extends DexlibAbstractInstruction {

	public MethodInvocationInstruction(Instruction instruction, int codeAddress) {
		super(instruction, codeAddress);

		// TODO Auto-generated constructor stub
	}

	public void introducedTypes() {
		MethodReference method = (MethodReference) (((ReferenceInstruction) instruction).getReference());

		System.out.print("\t\t\t" + instruction.getOpcode().name + "\t");

		DexUtil qualifiedName = new DexUtil();

		System.out.print(qualifiedName.getQualifiedName(method.getReturnType()) + " ");

		
		switch (instruction.getOpcode().format) {
		case Format35c:
			System.out.print("{");
			FiveRegisterInstruction instruction = (FiveRegisterInstruction) this.instruction;
			final int regCount = instruction.getRegisterCount();

			switch (regCount) {
			case 1:
				DexMethodDefinition.registerFormatter.display(instruction.getRegisterC());
				break;
			case 2:
				DexMethodDefinition.registerFormatter.display(instruction.getRegisterC());
				System.out.print(", ");
				DexMethodDefinition.registerFormatter.display(instruction.getRegisterD());
				break;
			case 3:
				DexMethodDefinition.registerFormatter.display(instruction.getRegisterC());
				System.out.print(", ");
				DexMethodDefinition.registerFormatter.display(instruction.getRegisterD());
				System.out.print(", ");
				DexMethodDefinition.registerFormatter.display(instruction.getRegisterE());
				break;
			case 4:
				DexMethodDefinition.registerFormatter.display(instruction.getRegisterC());
				System.out.print(", ");
				DexMethodDefinition.registerFormatter.display(instruction.getRegisterD());
				System.out.print(", ");
				DexMethodDefinition.registerFormatter.display(instruction.getRegisterE());
				System.out.print(", ");
				DexMethodDefinition.registerFormatter.display(instruction.getRegisterF());
				break;
			case 5:
				DexMethodDefinition.registerFormatter.display(instruction.getRegisterC());
				System.out.print(", ");
				DexMethodDefinition.registerFormatter.display(instruction.getRegisterD());
				System.out.print(", ");
				DexMethodDefinition.registerFormatter.display(instruction.getRegisterE());
				System.out.print(", ");
				DexMethodDefinition.registerFormatter.display(instruction.getRegisterF());
				System.out.print(", ");
				DexMethodDefinition.registerFormatter.display(instruction.getRegisterG());
				break;
			}
			System.out.print('}');
			break;

		case Format3rc:

			RegisterRangeInstruction in = (RegisterRangeInstruction) this.instruction;

			int regRangeCount = in.getRegisterCount();
			if (regRangeCount == 0) {
				System.out.print("{}");
			} else {
				int startRegister = in.getStartRegister();
				DexMethodDefinition.registerFormatter.displayRegisterRange(startRegister,
						startRegister + regRangeCount - 1);
			}
			break;

		default:
			throw new RuntimeException(this.instruction.getOpcode().format.toString()+" were not handled, Map the instruction to statement");
		}


		System.out.print(qualifiedName.getQualifiedName(method.getDefiningClass()) + ".");
		System.out.print(qualifiedName.getQualifiedName(method.getName()));
		List<? extends CharSequence> paramTypes = method.getParameterTypes();
		if (paramTypes != null) {
			int size = 0;
			System.out.print("(");
			for (CharSequence type : paramTypes) {
				size++;
				System.out.print(qualifiedName.getQualifiedName(type.toString()));
				if (size != paramTypes.size())
					System.out.print(",");
			}
			System.out.println(")");
		}

	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
//		introducedTypes();
//		MethodReference method = (MethodReference) (((ReferenceInstruction) instruction).getReference());
//		MethodNode callingMethod=null;
//		Variable v=null;
//		switch (instruction.getOpcode().format) {
//		case Format35c:
//			FiveRegisterInstruction instruction = (FiveRegisterInstruction) this.instruction;
//			final int regCount = instruction.getRegisterCount();
//
//			v=(Variable) DexMethodDefinition.registerLocals[instruction.getRegisterC()];
//			callingMethod=new MethodNode(null,-1,-1,-1,-1);
//			switch (regCount) {
//			case 1:
//				break;
//			case 2:
//				callingMethod.addParameter((Variable) DexMethodDefinition.registerLocals[instruction.getRegisterD()]);
//			case 3:
//				callingMethod.addParameter((Variable) DexMethodDefinition.registerLocals[instruction.getRegisterD()]);
//				callingMethod.addParameter((Variable) DexMethodDefinition.registerLocals[instruction.getRegisterE()]);
//				break;
//			case 4:
//				callingMethod.addParameter((Variable) DexMethodDefinition.registerLocals[instruction.getRegisterD()]);
//				callingMethod.addParameter((Variable) DexMethodDefinition.registerLocals[instruction.getRegisterE()]);
//				callingMethod.addParameter((Variable) DexMethodDefinition.registerLocals[instruction.getRegisterF()]);
//				break;
//			case 5:
//				callingMethod.addParameter((Variable) DexMethodDefinition.registerLocals[instruction.getRegisterD()]);
//				callingMethod.addParameter((Variable) DexMethodDefinition.registerLocals[instruction.getRegisterE()]);
//				callingMethod.addParameter((Variable) DexMethodDefinition.registerLocals[instruction.getRegisterF()]);
//				callingMethod.addParameter((Variable) DexMethodDefinition.registerLocals[instruction.getRegisterG()]);
//				break;
//			}
//			break;
//
//		case Format3rc:
//
//			RegisterRangeInstruction in = (RegisterRangeInstruction) this.instruction;
//
//			
//			int regRangeCount = in.getRegisterCount();
//			
//			if (regRangeCount!=0&&regRangeCount == 1) {
//				v=(Variable) DexMethodDefinition.registerLocals[in.getStartRegister()];
//				callingMethod=new MethodNode(null,-1,-1,-1,-1);
//				
//				} else {
//				v=(Variable) DexMethodDefinition.registerLocals[in.getStartRegister()];
//				callingMethod=new MethodNode(null,-1,-1,-1,-1);
//				for(int i=1;i<regRangeCount;i++){
//					callingMethod.addParameter((Variable) DexMethodDefinition.registerLocals[in.getStartRegister()+i]);
//				}
//				}
//			break;
//
//		default:
//			throw new RuntimeException(this.instruction.getOpcode().format.toString()+" were not handled, Map the instruction to statement");
//		}
//
//		callingMethod.setName(method.getName());
//		callingMethod.setReturnType(method.getReturnType());

		//String invoke=v.getType()+"."+callingMethod.getName();
	}

//	public static void displayTypes(List<DexlibAbstractInstruction> instructions) {
//		// TODO Auto-generated method stub
//		for (DexlibAbstractInstruction i : instructions) {
//        	if(i!=null)
//          System.out.println(i.introducedTypes());
//        }
//	}
}
