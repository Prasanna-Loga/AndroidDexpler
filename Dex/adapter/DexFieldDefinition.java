package com.zoho.security.sca.android.Dex.adapter;

import org.jf.dexlib2.AccessFlags;
import org.jf.dexlib2.iface.Field;
import org.jf.dexlib2.iface.value.EncodedValue;
import org.jf.dexlib2.util.EncodedValueUtils;

import com.zoho.security.sca.android.Dex.DexOptions;
import com.zoho.security.sca.android.Dex.EncodedValue.DexEncodedValueAdaptor;
import com.zoho.security.sca.java.structure.*;

public class DexFieldDefinition {

	private ClassNode fieldClass;
	public Variable variable = null;
	
	public DexFieldDefinition(ClassNode classDefinition) {
		// TODO Auto-generated constructor stub
		this.fieldClass=classDefinition;
		
		variable = new Variable(fieldClass,-1,-1,-1,-1);
	}
	

	public void display(DexOptions options, Field field, boolean setInStaticConstructor) {
		
		EncodedValue initialValue = field.getInitialValue();
		int accessFlags=field.getAccessFlags();
		
		if (setInStaticConstructor &&
                AccessFlags.STATIC.isSet(accessFlags) &&
                AccessFlags.FINAL.isSet(accessFlags) &&
                initialValue != null) {
            if (!EncodedValueUtils.isDefaultValue(initialValue)) {
                // The value of this static final field might be set in the static constructor
            } else {
                // don't write out the default initial value for static final fields that get set in the static
                // constructor
                initialValue = null;
            }
        }
		
		DexUtil qualifiedName=new DexUtil();
		
		System.out.print("\t");
		displayAccessFlag(accessFlags);
		variable.setStatic(AccessFlags.STATIC.isSet(accessFlags));
		variable.setFinal(AccessFlags.FINAL.isSet(accessFlags));
		variable.setName(qualifiedName.getQualifiedName(field.getName()));
		variable.setType(qualifiedName.getQualifiedName(field.getType()));
		
		System.out.print(qualifiedName.getQualifiedName(field.getType())+" ");
		System.out.print(qualifiedName.getQualifiedName(field.getName())+" ");
		if(initialValue!=null) {
			
			// field initialisation only for the static final variables
			
			System.out.print("= ");
			String containingClass = null;
            if (options.implicitReferences) {
                containingClass = field.getDefiningClass();
            }
            
            //Assignment only for constant fields
            Component Assignment = DexEncodedValueAdaptor.addConstantTag(initialValue, containingClass);
            variable.addAssignment(Assignment);
            
		}
		System.out.println();
	}

	private static void displayAccessFlag(int accessFlags) {
		
		for (AccessFlags accessFlag: AccessFlags.getAccessFlagsForField(accessFlags)) {
			System.out.print(accessFlag+" ");
		}
	          
	}

	public void setClassVariable(boolean isClassVariable) {
		// TODO Auto-generated method stub
		variable.setClassVariable(isClassVariable);
	}

}
