//$Id$
package com.zoho.security.sca.android.Dex.EncodedValue;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.jf.dexlib2.ValueType;
import org.jf.dexlib2.iface.value.*;
import org.jf.dexlib2.util.ReferenceUtil;

import com.zoho.security.sca.android.Dex.Tagkit.DexBooleanConstantValueTag;
import com.zoho.security.sca.android.Dex.Tagkit.DexByteConstantValueTag;
import com.zoho.security.sca.android.Dex.Tagkit.DexCharacterConstantValueTag;
import com.zoho.security.sca.android.Dex.Tagkit.DexDoubleConstantValueTag;
import com.zoho.security.sca.android.Dex.Tagkit.DexFloatConstantValueTag;
import com.zoho.security.sca.android.Dex.Tagkit.DexIntegerConstantValueTag;
import com.zoho.security.sca.android.Dex.Tagkit.DexLongConstantValueTag;
import com.zoho.security.sca.android.Dex.Tagkit.DexShortConstantValueTag;
import com.zoho.security.sca.android.Dex.Tagkit.DexStringConstantValueTag;
import com.zoho.security.sca.android.Dex.adapter.DexUtil;
import com.zoho.security.sca.java.structure.Component;

public class DexEncodedValueAdaptor {
    private static boolean useImplicitReference;
    
    public static List<Component> addTag(@Nonnull EncodedValue encodedValue, @Nullable String containingClass){
		switch (encodedValue.getValueType()) {
        
            case ValueType.ANNOTATION:
                return DexAnnotationEncodedValueAdapter.display((AnnotationEncodedValue)encodedValue, containingClass);
                
            case ValueType.ARRAY:
                return DexArrayEncodedValueAdapter.display((ArrayEncodedValue)encodedValue, containingClass);
                
                
        }
		return null;
    }

	public static Component addConstantTag(@Nonnull EncodedValue encodedValue, @Nullable String containingClass){
		Component constantTag = null;
        switch (encodedValue.getValueType()) {
        
                
            case ValueType.BOOLEAN:
            	constantTag = new DexBooleanConstantValueTag(((BooleanEncodedValue) encodedValue).getValue());
                System.out.print(((BooleanEncodedValue)encodedValue).getValue());
                break;
            case ValueType.BYTE:
            	constantTag = new DexByteConstantValueTag(((ByteEncodedValue)encodedValue).getValue());
                System.out.print(((ByteEncodedValue)encodedValue).getValue());
                break;
            case ValueType.SHORT:
            	constantTag = new DexShortConstantValueTag(((ShortEncodedValue)encodedValue).getValue());
                System.out.print(((ShortEncodedValue)encodedValue).getValue());
                break;
            case ValueType.FLOAT:
            	constantTag = new DexFloatConstantValueTag(((FloatEncodedValue)encodedValue).getValue());
                System.out.print(((FloatEncodedValue)encodedValue).getValue());
                break;
            case ValueType.INT:
            	constantTag = new DexIntegerConstantValueTag(((IntEncodedValue)encodedValue).getValue());
                System.out.print(((IntEncodedValue)encodedValue).getValue());
                break;
            case ValueType.LONG:
            	constantTag = new DexLongConstantValueTag(((LongEncodedValue)encodedValue).getValue());
                System.out.print(((LongEncodedValue)encodedValue).getValue());
                break;
            case ValueType.CHAR:
            	constantTag = new DexCharacterConstantValueTag(((CharEncodedValue)encodedValue).getValue());
                System.out.print(((CharEncodedValue)encodedValue).getValue());
                break;
            case ValueType.DOUBLE:
            	constantTag = new DexDoubleConstantValueTag(((DoubleEncodedValue)encodedValue).getValue());
                System.out.print(((DoubleEncodedValue)encodedValue).getValue());
                break;
                
                
            case ValueType.STRING:
            	constantTag = new DexStringConstantValueTag(((StringEncodedValue)encodedValue).getValue());
                System.out.print(((StringEncodedValue)encodedValue).getValue());
                break;
                
            case ValueType.TYPE:
            	DexUtil qualifiedName=new DexUtil();
            	
            	constantTag = new DexStringConstantValueTag(qualifiedName.getQualifiedName(((TypeEncodedValue)encodedValue).getValue()));
            	System.out.print(qualifiedName.getQualifiedName(((TypeEncodedValue)encodedValue).getValue()));
                break;
                
                
                
                
                
                
                
            case ValueType.FIELD:
                FieldEncodedValue fieldEncodedValue = (FieldEncodedValue)encodedValue;
                useImplicitReference = false;
                if (fieldEncodedValue.getValue().getDefiningClass().equals(containingClass)) {
                    useImplicitReference = true;
                }
                System.out.println(ReferenceUtil.getFieldDescriptor(fieldEncodedValue.getValue(), useImplicitReference));
                break;
            
            case ValueType.METHOD:
                MethodEncodedValue methodEncodedValue = (MethodEncodedValue)encodedValue;
                useImplicitReference = false;
                if (methodEncodedValue.getValue().getDefiningClass().equals(containingClass)) {
                    useImplicitReference = true;
                }
                
//                if(!useImplicitReference)
//                	System.out.print(methodEncodedValue.getValue().getDefiningClass()+" ");
//                System.out.print(methodEncodedValue.getValue().getName()+" ");
//                for (CharSequence paramType: methodEncodedValue.getValue().getParameterTypes()) {
//                    System.out.print(paramType.toString());
//                }
//                System.out.println(" "+methodEncodedValue.getValue().getReturnType());
                
                System.out.println(ReferenceUtil.getMethodDescriptor(methodEncodedValue.getValue(),useImplicitReference));
                break;
            case ValueType.NULL:
                System.out.print("null");
                break;
            
            
            
                
                
                
//            case ValueType.ENUM:
//                EnumEncodedValue enumEncodedValue = (EnumEncodedValue)encodedValue;
//                boolean useImplicitReference = false;
//                if (enumEncodedValue.getValue().getDefiningClass().equals(containingClass)) {
//                    useImplicitReference = true;
//                }
//                writer.write(".enum ");
//                ReferenceUtil.writeFieldDescriptor(writer, enumEncodedValue.getValue(), useImplicitReference);
//                return;
           
                
            case ValueType.ENUM:
            	throw new IllegalArgumentException("not handled "+encodedValue.getValueType());
            default :
            	throw new IllegalArgumentException("Unknown encoded value type: " + encodedValue.getValueType());
                
        }
        return constantTag;
        
        
    }
}