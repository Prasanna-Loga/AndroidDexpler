//$Id$
package com.zoho.security.sca.android.Dex.Tagkit;

import com.zoho.security.sca.java.structure.Constant;

public class DexBooleanConstantValueTag extends Constant{

	public DexBooleanConstantValueTag(boolean value) {
		// TODO Auto-generated constructor stub
		super(null, 0, 0, 0, 0);
		if(value)
		setValue("true");
		else
			setValue("false");
	}

	
}
