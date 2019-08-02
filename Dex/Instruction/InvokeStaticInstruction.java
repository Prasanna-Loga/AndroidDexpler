//$Id$
package com.zoho.security.sca.android.Dex.Instruction;

import org.jf.dexlib2.iface.instruction.Instruction;


public class InvokeStaticInstruction extends MethodInvocationInstruction {

	  public InvokeStaticInstruction(Instruction instruction, int codeAddress) {
	    super(instruction, codeAddress);
	  }

	@Override
	public void display() {
		super.introducedTypes();
		// TODO Auto-generated method stub
//		MethodReference method = (MethodReference) (((ReferenceInstruction) instruction).getReference());
//		MethodNode callingMethod=null;
//		switch (instruction.getOpcode().format) {
//		case Format35c:
//			FiveRegisterInstruction instruction = (FiveRegisterInstruction) this.instruction;
//			final int regCount = instruction.getRegisterCount();
//
//			callingMethod=new MethodNode(null,-1,-1,-1,-1);
//			switch (regCount) {
//			case 1:
//				callingMethod.addParameter((Variable) DexMethodDefinition.registerLocals[instruction.getRegisterC()]);
//				
//			case 2:
//				callingMethod.addParameter((Variable) DexMethodDefinition.registerLocals[instruction.getRegisterC()]);
//				callingMethod.addParameter((Variable) DexMethodDefinition.registerLocals[instruction.getRegisterD()]);
//			case 3:
//				callingMethod.addParameter((Variable) DexMethodDefinition.registerLocals[instruction.getRegisterC()]);
//				callingMethod.addParameter((Variable) DexMethodDefinition.registerLocals[instruction.getRegisterD()]);
//				callingMethod.addParameter((Variable) DexMethodDefinition.registerLocals[instruction.getRegisterE()]);
//				break;
//			case 4:
//				callingMethod.addParameter((Variable) DexMethodDefinition.registerLocals[instruction.getRegisterC()]);
//				callingMethod.addParameter((Variable) DexMethodDefinition.registerLocals[instruction.getRegisterD()]);
//				callingMethod.addParameter((Variable) DexMethodDefinition.registerLocals[instruction.getRegisterE()]);
//				callingMethod.addParameter((Variable) DexMethodDefinition.registerLocals[instruction.getRegisterF()]);
//				break;
//			case 5:
//				callingMethod.addParameter((Variable) DexMethodDefinition.registerLocals[instruction.getRegisterC()]);
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
//			if (regRangeCount != 0) {
//				callingMethod=new MethodNode(null,-1,-1,-1,-1);
//				for(int i=0;i<regRangeCount;i++){
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
//
//		String invoke=callingMethod.getReturnType()+"."+callingMethod.getName();
	}

}
