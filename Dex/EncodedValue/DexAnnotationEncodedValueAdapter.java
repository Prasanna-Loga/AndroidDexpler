package com.zoho.security.sca.android.Dex.EncodedValue;

import java.util.Collection;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.jf.dexlib2.iface.AnnotationElement;
import org.jf.dexlib2.iface.value.AnnotationEncodedValue;

import com.zoho.security.sca.java.structure.*;

public class DexAnnotationEncodedValueAdapter {
	
	
	
	public static List<Component> display(@Nonnull AnnotationEncodedValue annotationEncodedValue, @Nullable String containingClass){
		

		return displayElementsTo(annotationEncodedValue.getElements(), containingClass);
        
	}

	public static List<Component> displayElementsTo(@Nonnull Collection<? extends AnnotationElement> annotationElements, @Nullable String containingClass){
		
		for (AnnotationElement annotationElement : annotationElements) {
			return DexEncodedValueAdaptor.addTag(annotationElement.getValue(), containingClass);
			}
		
		return null;
	}

}
