//$Id$

package com.zoho.security.sca.android.Dex.adapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Nonnull;

import org.jf.dexlib2.AccessFlags;
import org.jf.dexlib2.iface.Annotation;
import org.jf.dexlib2.iface.Method;
import org.jf.dexlib2.iface.MethodImplementation;
import org.jf.dexlib2.iface.MethodParameter;
import org.jf.dexlib2.iface.debug.DebugItem;
import org.jf.dexlib2.iface.instruction.Instruction;
import org.jf.dexlib2.immutable.debug.ImmutableLineNumber;
import org.jf.dexlib2.util.MethodUtil;
import com.zoho.security.sca.android.Dex.InvalidDalvikBytecodeException;
import com.zoho.security.sca.android.Dex.Instruction.CheckCastInstruction;
import com.zoho.security.sca.android.Dex.Instruction.ConstInstruction;
import com.zoho.security.sca.android.Dex.Instruction.ConstStringInstruction;
import com.zoho.security.sca.android.Dex.Instruction.DexlibAbstractInstruction;
import com.zoho.security.sca.android.Dex.Instruction.FieldInstruction;
import com.zoho.security.sca.android.Dex.Instruction.InstructionFactory;
import com.zoho.security.sca.android.Dex.Instruction.JumpInstruction;
import com.zoho.security.sca.android.Dex.Instruction.MethodInvocationInstruction;
import com.zoho.security.sca.android.Dex.Instruction.MoveInstruction;
import com.zoho.security.sca.android.Dex.Instruction.MoveResultInstruction;
import com.zoho.security.sca.android.Dex.Instruction.NewInstanceInstruction;
import com.zoho.security.sca.android.Dex.Instruction.RegisterFormatter;
import com.zoho.security.sca.android.Dex.Instruction.ReturnInstruction;
import com.zoho.security.sca.android.Dex.Instruction.TaggedInstruction;
import com.zoho.security.sca.java.structure.Component;
import com.zoho.security.sca.java.structure.MethodNode;
import com.zoho.security.sca.java.structure.Scope;
import com.zoho.security.sca.java.structure.Variable;


public class DexMethodDefinition {
	
	@Nonnull public static DexClassDefinition classDef;
    @Nonnull public static Method method;
    @Nonnull public static MethodImplementation methodImpl;
	private static ArrayList<DexlibAbstractInstruction> instructions;
protected static HashMap<Integer, DexlibAbstractInstruction> instructionAtAddress;
	protected int numRegisters;
	public static Component[] registerLocals;
	public static Component storeResultLocal;
	public static RegisterFormatter registerFormatter;

	public MethodNode methodNode=null;
	
	
	public DexMethodDefinition(@Nonnull DexClassDefinition classDef, @Nonnull Method method,
                            @Nonnull MethodImplementation methodImpl) {

    	DexMethodDefinition.classDef = classDef;
        DexMethodDefinition.method = method;
        DexMethodDefinition.methodImpl = methodImpl;
        
        instructions = new ArrayList<DexlibAbstractInstruction>();
        instructionAtAddress = new HashMap<Integer, DexlibAbstractInstruction>();
        
        methodNode = new MethodNode(classDef.classNode,-1,-1,-1,-1);
       
    }

    

	/**
	 * Returns the register local that are stored by the execution of the instruction
	 * @return array of local components
	 */
    public static Component[] getRegisterLocals() {
        return registerLocals;
      }

      /**
       * Return the Local component that are associated with the number in the current register state.
       *
       * Handles if the register number actually points to a method parameter.
       *
       * @param num
       *          the register number
       * @throws InvalidDalvikBytecodeException
       */
      public static Component getRegisterLocal(int num) throws InvalidDalvikBytecodeException {
        int totalRegisters = registerLocals.length;
        if (num > totalRegisters) {
          throw new InvalidDalvikBytecodeException(
              "Trying to access register " + num + " but only " + totalRegisters + " is/are available.");
        }
        
        return registerLocals[num];
		
      }
      
      /**
       * Return the intermediate result that are associated with the instructions
       * 
       * @return stored result
       */
      public static Component getStoreResultLocal() {
        return storeResultLocal;
      }


      /**
       * Return the instructions that are associated at the particular address 
       * @param code address+code offset
       * @return instruction
       */
	public DexlibAbstractInstruction instructionAtAddress(int address) {
    DexlibAbstractInstruction i = null;
    while (i == null && address >= 0) {
            i = instructionAtAddress.get(address);
      address--;
    }
    return i;
  }


	/**
	 * Method definition without any method implementations
	 */
	
	public void displayEmptyMethod(){
		
		DexUtil qualifiedName=new DexUtil();
		
		System.out.print("\t");
		displayAccessFlag(method.getAccessFlags());
		String scope = null;
		if (AccessFlags.PUBLIC.isSet(method.getAccessFlags())) {
            scope=Scope.PUBLIC.name();
        } else if (AccessFlags.PRIVATE.isSet(method.getAccessFlags())) {
            scope= Scope.PRIVATE.name();
        } else if (AccessFlags.PROTECTED.isSet(method.getAccessFlags())) {
            scope = Scope.PROTECTED.name();
        } 
		methodNode.setScope(scope);
		
		methodNode.setReturnType(qualifiedName.getQualifiedName(method.getReturnType()));
		System.out.print(qualifiedName.getQualifiedName(method.getReturnType())+" ");
		
		methodNode.setName(qualifiedName.getQualifiedName(method.getName()));
		System.out.print(qualifiedName.getQualifiedName(method.getName()+"("));
		
		int size=0;
		for (MethodParameter parameter: method.getParameters()) {
			
			Variable paramVariable=new Variable(methodNode,-1,-1,-1,-1);
			if(parameter.getName()!=null){
				String name=qualifiedName.getQualifiedName(parameter.getName());
				paramVariable.setName(name);
				}
				
	            String type = qualifiedName.getQualifiedName(parameter.getType());
	            paramVariable.setType(type);
	            
            size++;
            if(type!=null)
            System.out.print(type+" ");
            if(parameter.getName()!=null)
            System.out.print(qualifiedName.getQualifiedName(parameter.getName()));
            if(size!=method.getParameters().size())
            	System.out.print(", ");
        }
		
		System.out.print(")");
		

		Collection<? extends Annotation> annotations = method.getAnnotations();
		
		String containingClass = null;
        if (classDef.options.implicitReferences) {
            containingClass = method.getDefiningClass();
        }
			
        /**
         *Annotation contains the exception throw list 
         */
		List<Component> throwList = DexAnnotationFormatter.display(annotations, containingClass);
		
		if(throwList!=null){
			System.out.print("    throws ");
		for(Component component : throwList ){
			System.out.print(component.getDescription()+" ");
			methodNode.addThrowObjects(component);
		}
		}
		System.out.println(";");
	}


	/**
	 * Method definition with the method implementations 
	 */
	public void displayMethod() {

		int parameterRegisterCount = 0;
		DexUtil qualifiedName=new DexUtil();
		
		System.out.print("\t");
		displayAccessFlag(method.getAccessFlags());
		String scope = null;
		if (AccessFlags.PUBLIC.isSet(method.getAccessFlags())) {
            scope=Scope.PUBLIC.name();
        } else if (AccessFlags.PRIVATE.isSet(method.getAccessFlags())) {
            scope= Scope.PRIVATE.name();
        } else if (AccessFlags.PROTECTED.isSet(method.getAccessFlags())) {
            scope = Scope.PROTECTED.name();
        } 
		methodNode.setScope(scope);
		
		methodNode.setReturnType(qualifiedName.getQualifiedName(method.getReturnType()));
		System.out.print(qualifiedName.getQualifiedName(method.getReturnType())+" ");
		
		methodNode.setName(qualifiedName.getQualifiedName(method.getName()));
		System.out.print(qualifiedName.getQualifiedName(method.getName()+"("));
		
		parameterRegisterCount=MethodUtil.getParameterRegisterCount(method);
		//if static
//		if(!(AccessFlags.STATIC.isSet(method.getAccessFlags())))
//		parameterRegisterCount--;
		
		
		/**
		 * 
		 * RegisterLocal contains variables including local and method parameters
		 * 
		 * register is the register count of local and parameter
		 * parameterRegisterCount is the register count for parameter 
		 * 
		 * registerLocal fills with parameter at end of array and locals at start end of array
		 * 
		 * example: method contains local variable v0,v1 parameter variable p0,p1
		 * registerLocal will be [v0,v1,p0,p1]
		 * 
		 */
		//Local
		int local=methodImpl.getRegisterCount() - parameterRegisterCount;
		//Register
		int register=methodImpl.getRegisterCount();
		
		 registerLocals = new Component[register];
		 
		 /**
		  * Setting this variable register local for static method
		  */
		 int regCount=local;
		 if(!(AccessFlags.STATIC.isSet(method.getAccessFlags()))){
		
			 Variable thisVariable=new Variable(methodNode, -1, -1, -1, -1);
			 thisVariable.setName("this");
			 thisVariable.setType(methodNode.getName());
			 registerLocals[regCount++]=thisVariable;
		 }
		 
		 
		int size=0;
		for (MethodParameter parameter: method.getParameters()) {
			
			/**
			 * parsing method parameter to variable of SCA
			 */
			Variable paramVariable = new Variable(methodNode,-1,-1,-1,-1);
			if(parameter.getName()!=null){
			String name=qualifiedName.getQualifiedName(parameter.getName());
			paramVariable.setName(name);
			}
			
            String type = qualifiedName.getQualifiedName(parameter.getType());
            paramVariable.setType(type);
            
            /**
             * Adding parameter to methodNode definition
             */
            methodNode.addParameter(paramVariable);
            
            /**
             * Adding parameter to registerLocal
             */
            registerLocals[regCount++]=paramVariable;
            
            size++;
            if(type!=null)
            System.out.print(type+" ");
            if(parameter.getName()!=null)
            System.out.print(qualifiedName.getQualifiedName(parameter.getName()));
            if(size!=method.getParameters().size())
            	System.out.print(", ");
            
        }
		
		
			System.out.print(") ");
			//System.out.println(methodImpl.getRegisterCount()+" "+parameterRegisterCount+"**************************");
			
	        registerFormatter = new RegisterFormatter(classDef.options, methodImpl.getRegisterCount(),parameterRegisterCount);
	        
			
			//System.out.print(parameterRegisterCount	);
			//System.out.print(MethodUtil.getParameterRegisterCount(method));
			
	        /**
	         *Annotation contains the exception throw list 
	         */
			Collection<? extends Annotation> annotations = method.getAnnotations();
			
			String containingClass = null;
	        if (classDef.options.implicitReferences) {
	            containingClass = method.getDefiningClass();
	        }
				
			List<Component> throwList = DexAnnotationFormatter.display(annotations, containingClass);
			
			if(throwList!=null){
				System.out.print("    throws ");
			for(Component component : throwList ){
				System.out.print(component.getDescription()+" ");
				methodNode.addThrowObjects(component);
			}
			}
			
			System.out.println("{");
			
			
			

//			 for(Component l:registerLocals)
//		        System.out.println(l+"************");
			
			 
			/**
			 * Parsing method instructions 
			 */
			getMethodItems();		
			System.out.println("\n\t}");
			
			for(Component l:registerLocals)
		        System.out.println(l+"************");
			
	}
	
	



	private static List<DexMethodItem> getMethodItems() {
		
		ArrayList<DexMethodItem> methodItems = new ArrayList<DexMethodItem>();

        if ((classDef.options.registerInfo != 0) || (classDef.options.normalizeVirtualMethods) ||
                (classDef.options.deodex && needsAnalyzed())) {
            
        	// for odex file
        } else {
        	
        	//only for dex file
        	
            addInstructionMethodItems(methodItems);
        }
		return null;
	}
	

	private static void addInstructionMethodItems(ArrayList<DexMethodItem> methodItems) {
		
		int currentCodeAddress = 0;

            for (Instruction instruction : methodImpl.getInstructions()) {
                DexlibAbstractInstruction dexInstruction = InstructionFactory.fromInstruction(instruction, currentCodeAddress);
                instructions.add(dexInstruction);
                instructionAtAddress.put(currentCodeAddress, dexInstruction);
                currentCodeAddress += instruction.getCodeUnits();
              }
            
            
            
            for (DebugItem di : methodImpl.getDebugItems()) {
                if (di instanceof ImmutableLineNumber) {
                  ImmutableLineNumber ln = (ImmutableLineNumber) di;
                  DexlibAbstractInstruction ins = DexMethodDefinition.instructionAtAddress.get(ln.getCodeAddress());
                  if (ins == null) {
                    // Debug.printDbg("Line number tag pointing to invalid
                    // offset: " + ln.getCodeAddress());
                    continue;
                  }
                  
                  ins.setLineNumber(ln.getLineNumber());
                    }
              }
        usedTypes();
        
//        System.out.println(instructionAtAddress);
	}
	
	
            
            public static void usedTypes() {
            	
                for (DexlibAbstractInstruction i : instructions) {
                	
                	if(i!=null){
                		
                		if(i instanceof MethodInvocationInstruction) 
               			i.display();
                		if(i instanceof ReturnInstruction)
                			i.display();
                		if(i instanceof MoveInstruction)
                			i.display();
                		if(i instanceof MoveResultInstruction)
                			i.display();
                		if(i instanceof ConstInstruction)
                			i.display();
                		if(i instanceof ConstStringInstruction)
                			i.display();
                		if(i instanceof CheckCastInstruction)
                			i.display();
                		if(i instanceof TaggedInstruction)
                			i.display();
                		if(i instanceof JumpInstruction)
                			i.display();
                		if(i instanceof FieldInstruction)
                			i.display();
                		if(i instanceof NewInstanceInstruction)
                			i.display();
                		
                	}
                }
	}
            


	private static boolean needsAnalyzed() {
        for (Instruction instruction: methodImpl.getInstructions()) {
            if (instruction.getOpcode().odexOnly()) {
                return true;
            }
        }
        return false;
    }

	private static void displayAccessFlag(int accessFlags) {
		// TODO Auto-generated method stub
		for (AccessFlags accessFlag: AccessFlags.getAccessFlagsForMethod(accessFlags)) {
			System.out.print(accessFlag+" ");
		}
	}


}
