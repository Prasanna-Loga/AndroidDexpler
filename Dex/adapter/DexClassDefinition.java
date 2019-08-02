//$Id$
package com.zoho.security.sca.android.Dex.adapter;

import org.jf.dexlib2.AccessFlags;
import org.jf.dexlib2.dexbacked.DexBackedClassDef;
import org.jf.dexlib2.iface.*;
import org.jf.dexlib2.iface.instruction.Instruction;
import org.jf.dexlib2.iface.instruction.formats.Instruction21c;
import org.jf.dexlib2.iface.reference.FieldReference;
import org.jf.dexlib2.util.ReferenceUtil;

import com.zoho.security.sca.android.Dex.DexOptions;
import com.zoho.security.sca.java.structure.ClassNode;
import com.zoho.security.sca.java.structure.Constant;
import com.zoho.security.sca.java.structure.Variable;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.Map.Entry;

public class DexClassDefinition {
	@Nonnull
	public final DexOptions options;
	@Nonnull
	public final ClassDef classDef;
	@Nonnull
	private final HashSet<String> fieldsSetInStaticConstructor;

//	private String scope = null;


	DexUtil qualifiedname = new DexUtil();
	public ClassNode classNode = null;


	public DexClassDefinition(@Nonnull DexOptions options, @Nonnull ClassDef classDef) {

		this.options = options;
		this.classDef = classDef;
		fieldsSetInStaticConstructor = findFieldsSetInStaticConstructor(classDef);
		
		classNode = new ClassNode(null,-1,-1,-1,-1);
	}

	@Nonnull
	private static HashSet<String> findFieldsSetInStaticConstructor(@Nonnull ClassDef classDef) {
		HashSet<String> fieldsSetInStaticConstructor = new HashSet<String>();

		for (Method method : classDef.getDirectMethods()) {
			if (method.getName().equals("<clinit>")) {
				MethodImplementation impl = method.getImplementation();
				if (impl != null) {
					for (Instruction instruction : impl.getInstructions()) {
						switch (instruction.getOpcode()) {
						case SPUT:
						case SPUT_BOOLEAN:
						case SPUT_BYTE:
						case SPUT_CHAR:
						case SPUT_OBJECT:
						case SPUT_SHORT:
						case SPUT_WIDE: {
							Instruction21c ins = (Instruction21c) instruction;
							FieldReference fieldRef = null;
							// try {
							fieldRef = (FieldReference) ins.getReference();
							// } catch (InvalidItemIndex ex) {
							// just ignore it for now. We'll deal with it later,
							// when processing the instructions
							// themselves
							// }
							System.out.println(fieldRef.getName());
							if (fieldRef != null && fieldRef.getDefiningClass().equals((classDef.getType()))) {
								fieldsSetInStaticConstructor.add(ReferenceUtil.getShortFieldDescriptor(fieldRef));
							}
							break;
						}
						default:
							break;
						}
					}
				}
			}
		}
		return fieldsSetInStaticConstructor;
	}

	public void parseClass() {
		
		
//		 if(qualifiedname.getQualifiedName(classDef.getType()).contains("com.zoho."))
		{

			className();
			superClass();
//			scope();
			
			/**
			 *  List of interfaces
			 */
			interfaces();

			System.out.println(" {");

			/**
			 * Setting static fields of the class 
			 */
			Set<String> staticFields = displayStaticField();

			/**
			 * Setting instance field of the class
			 */
			displayInstanceField(staticFields);
			
			
			
			if(classNode.getVariableMap()!=null)
			System.out.println(classNode.getVariableMap());
			
			/**
			 * VariableMap check
			 */

			if(classNode.getVariableMap()!=null)
			for(Entry<String, Variable> entry : classNode.getVariableMap().entrySet()) {
			    Variable value = entry.getValue();

				
				if(value.getName()!=null)
				System.out.print(value.getName()+" ---> ");
				if(value.getComponentType()!=null)
					System.out.print(value.getType()+"------------>");
				if(value.getAssignments()!=null)
				System.out.print(((Constant)value.getAssignments().get(0)).getValue());
//				if(value.getAssignments()!=null)
//				System.out.print(value.getAssignments().get(0).getDescription());
				System.out.println();
			}

			/**
			 * Setting direct methods including static blocks and constructor
			 */
			Set<String> directMethods = displayDirectMethod();

			/**
			 * Setting instance methods
			 */
			displayVirtualMethod(directMethods);
			
			
			if(classNode.methodNodeMap!=null)
			System.out.println(classNode.getMethodNodeMap());
			
			System.out.println("\n}");
			
			/**
			 * VariableMap check
			 */

			if(classNode.getVariableMap()!=null)
			for(Entry<String, Variable> entry : classNode.getVariableMap().entrySet()) {
			    Variable value = entry.getValue();

				
				if(value.getName()!=null)
				System.out.print(value.getName()+" ---> ");
				if(value.getComponentType()!=null)
					System.out.print(value.getType()+"------------>");
				if(value.getAssignments()!=null){

					if(value.getAssignments().get(0) instanceof Variable)
						System.out.print(((Variable)value.getAssignments().get(0)).getName());
					else if(value.getAssignments().get(0) instanceof Constant)
					System.out.print(((Constant)value.getAssignments().get(0)).getValue());

				}
					//				if(value.getAssignments()!=null)
//				System.out.print(value.getAssignments().get(0).getDescription());
				System.out.println();
			}
		}

	}

	private void className() {

		System.out.print("class ");
		accessFlag();

		System.out.print(qualifiedname.getQualifiedName(classDef.getType()));

		String fullyQualifiedClassName = qualifiedname.getQualifiedName(classDef.getType());
		String className;
		String jarName;
		int idx = fullyQualifiedClassName.lastIndexOf('.');

		if (idx != -1) {
			className = fullyQualifiedClassName.substring(idx + 1);
			jarName = fullyQualifiedClassName.substring(0, idx);

		} else {
			className = fullyQualifiedClassName;
			jarName = "";
		}

		classNode.setClassName(className);
		classNode.setJarName(jarName);

		if (AccessFlags.INTERFACE.isSet(classDef.getAccessFlags()))
			classNode.setInterface(true);

	}

	private void superClass() {
		// TODO Auto-generated method stub

		if (classDef.getSuperclass() != null) {

			System.out.print(" extends " + qualifiedname.getQualifiedName(classDef.getSuperclass()));
			classNode.setSuperClass(qualifiedname.getQualifiedName(classDef.getSuperclass()));
		}

	}

//	private void scope() {
//		// TODO Auto-generated method stub
//
//		if (AccessFlags.PRIVATE.isSet(classDef.getAccessFlags()))
//			scope=Scope.PRIVATE.name();
//		if (AccessFlags.PUBLIC.isSet(classDef.getAccessFlags()))
//			scope=Scope.PUBLIC.name();
//		if (AccessFlags.PROTECTED.isSet(classDef.getAccessFlags()))
//			scope=Scope.PROTECTED.name();
//
//	}

	private void interfaces() {
		// TODO Auto-generated method stub

		if (classDef.getInterfaces().size() > 0) {
			List<String> interfaces = classDef.getInterfaces();
			classNode.setInterfaces(interfaces);

			System.out.print(" implements ");

			int size = 0;
			for (String interfaceName : interfaces) {
				size++;
				System.out.print(qualifiedname.getQualifiedName(interfaceName));

				if (size != interfaces.size())
					System.out.print(", ");
			}
		}

	}

	private Set<String> displayStaticField() {

		Set<String> writtenFields = new HashSet<String>();

		Iterable<? extends Field> staticFields;
		if (classDef instanceof DexBackedClassDef) {
			staticFields = ((DexBackedClassDef) classDef).getStaticFields(false);
		} else {
			staticFields = classDef.getStaticFields();
		}

		for (Field field : staticFields) {
			boolean setInStaticConstructor;

			
			String fieldString = ReferenceUtil.getShortFieldDescriptor(field);
			if (!writtenFields.add(fieldString)) {
				
				/**
				 *  duplicate field ignored
				 */

				System.err.println(String.format("Ignoring duplicate field: %s->%s", classDef.getType(), fieldString));
				setInStaticConstructor = false;
			} 
			else {
				setInStaticConstructor = fieldsSetInStaticConstructor.contains(fieldString);
			}
			DexFieldDefinition fieldvariable = new DexFieldDefinition(classNode);
			fieldvariable.display(options, field, setInStaticConstructor);
			fieldvariable.setClassVariable(true);
			
			/**
			 * Adding static Field variables to variable map of the corresponding class
			 *VariableMap contains all the the static class variables along with the assignment of final static variables only
			 */
			classNode.addVariable(fieldvariable.variable);
		}
		return writtenFields;
	}

	private void displayInstanceField(Set<String> staticFields) {

		Set<String> writtenFields = new HashSet<String>();

		Iterable<? extends Field> instanceFields;
		if (classDef instanceof DexBackedClassDef) {
			instanceFields = ((DexBackedClassDef) classDef).getInstanceFields(false);
		} else {
			instanceFields = classDef.getInstanceFields();
		}

		for (Field field : instanceFields) {
			
			String fieldString = ReferenceUtil.getShortFieldDescriptor(field);
			if (!writtenFields.add(fieldString)) {
				
				/**
				 *  duplicate field ignored
				 */

				System.err.println(String.format("Ignoring duplicate field: %s->%s", classDef.getType(), fieldString));
				} else if (staticFields.contains(fieldString)) {
				System.err.println(String.format("Duplicate static+instance field found: %s->%s", classDef.getType(),
						fieldString));
				System.err.println("You will need to rename one of these fields, including all references.");

			}
			DexFieldDefinition fieldvariable = new DexFieldDefinition(classNode);
			
			fieldvariable.display(options, field, false);
			fieldvariable.setClassVariable(true);
			
			/**Adding instance Field variables to variable map of the corresponding class
			*VariableMap contains all the the class variables along with the assignment of final static variables only
			*/
			classNode.addVariable(fieldvariable.variable);

				}

	}

	private Set<String> displayDirectMethod() {

		Set<String> writtenMethods = new HashSet<String>();

		Iterable<? extends Method> directMethods;
		if (classDef instanceof DexBackedClassDef) {
			directMethods = ((DexBackedClassDef) classDef).getDirectMethods(false);
		} else {
			directMethods = classDef.getDirectMethods();
		}

		for (Method method : directMethods) {

			// TODO: check for method validation errors
			String methodString = ReferenceUtil.getMethodDescriptor(method, true);

			if (!writtenMethods.add(methodString)) {
				/**
				 *  duplicate method ignored
				 */
				System.err.println(String.format("Ignoring duplicate method: %s->%s", classDef.getType(), methodString));

			}

			MethodImplementation methodImpl = method.getImplementation();
			DexMethodDefinition methodDefinition = new DexMethodDefinition(this, method, methodImpl);

			if (methodImpl == null) {

				methodDefinition.displayEmptyMethod();

			} else {

//				if(method.getName().equals("<init>")||method.getName().equals("<clinit>"))
				// MethodDefinition methodDefinition=new MethodDefinition(this,
				// method, methodImpl);
				methodDefinition.displayMethod();
			}
			
			/**Adding direct methods including static blocks and constructor to the MethodNode map
			*methodNode map contains all the direct method definitions
			*methodImpl from the constructor and static blocks add assignment to field variable
			*Variable map is updated with the all class level assignments
			*/
			classNode.addMethod(methodDefinition.methodNode);

		}
		
		return writtenMethods;

	}

	private void displayVirtualMethod(Set<String> directMethods) {

		Set<String> writtenMethods = new HashSet<String>();

		Iterable<? extends Method> virtualMethods;
		if (classDef instanceof DexBackedClassDef) {
			virtualMethods = ((DexBackedClassDef) classDef).getVirtualMethods(false);
		} else {
			virtualMethods = classDef.getVirtualMethods();
		}

		for (Method method : virtualMethods) {

			// TODO: check for method validation errors
			String methodString = ReferenceUtil.getMethodDescriptor(method, true);

			if (!writtenMethods.add(methodString)) {
				// duplicate method ignored

			} else if (directMethods.contains(methodString)) {
				System.err.println(String.format("Duplicate direct+virtual method found: %s->%s", classDef.getType(),
						methodString));
				System.err.println("You will need to rename one of these methods, including all references.");
			}

			MethodImplementation methodImpl = method.getImplementation();
			DexMethodDefinition methodDefinition = new DexMethodDefinition(this, method, methodImpl);

			if (methodImpl == null) {

				methodDefinition.displayEmptyMethod();

			} else {
				// MethodDefinition methodDefinition=new MethodDefinition(this,
				// method, methodImpl);
				methodDefinition.displayMethod();

			}
			
			/**Adding instance methods to the MethodNode map
			*methodNode map contains all the method definitions of the class
			*/
			classNode.addMethod(methodDefinition.methodNode);
		}
		
		
	}

	private void accessFlag() {

		for (AccessFlags accessFlag : AccessFlags.getAccessFlagsForClass(classDef.getAccessFlags())) {
			System.out.print(accessFlag.toString() + " ");
		}
	}

}
