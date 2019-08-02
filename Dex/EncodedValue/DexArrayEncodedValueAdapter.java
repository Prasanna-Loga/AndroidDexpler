//$Id$

package com.zoho.security.sca.android.Dex.EncodedValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.jf.dexlib2.iface.value.ArrayEncodedValue;
import org.jf.dexlib2.iface.value.EncodedValue;

import com.zoho.security.sca.java.structure.Component;

public class DexArrayEncodedValueAdapter {

	public static List<Component> display(@Nonnull ArrayEncodedValue arrayEncodedValue, @Nullable String containingClass) {
		
		Collection<? extends EncodedValue> values = arrayEncodedValue.getValue();
		if (values.size() == 0) {
			
			return null;
		}
		int size=0;

		List<Component> ArrayValueList = new ArrayList<Component>();
		for (EncodedValue encodedValue : values) {

			ArrayValueList.add(DexEncodedValueAdaptor.addConstantTag(encodedValue, containingClass));
			
//			if(EncodedValueAdaptor.addConstantTag(encodedValue, containingClass)!=null)
//			System.out.println(((Constant)EncodedValueAdaptor.addConstantTag(encodedValue, containingClass)).getValue()+"$$$$$$$$$$");
//			
			size++;
			if(size!=values.size())
			System.out.print(", ");
		}
		return ArrayValueList;
	}
}
