package com.zoho.security.sca.android.Dex.adapter;

import java.util.Collection;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.jf.dexlib2.AnnotationVisibility;
import org.jf.dexlib2.iface.Annotation;

import com.zoho.security.sca.android.Dex.EncodedValue.DexAnnotationEncodedValueAdapter;
import com.zoho.security.sca.java.structure.Component;

public class DexAnnotationFormatter {
	public static List<Component> display(@Nonnull Collection<? extends Annotation> annotations, @Nullable String containingClass) {
		for (Annotation annotation : annotations) {
			//annotation visibility like System,runtime
			AnnotationVisibility.getVisibility(annotation.getVisibility());
			
			Boolean throwFlag=false;
			
			if(annotation.getType().endsWith("Throws;")) {
				if(throwFlag==false) {
					System.out.print("throws ");
					throwFlag=true;
				}
			
			return DexAnnotationEncodedValueAdapter.displayElementsTo(annotation.getElements(), containingClass);

			}
		}
		return null;
	}

}
