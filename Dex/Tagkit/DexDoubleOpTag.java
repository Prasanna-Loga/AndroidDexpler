package com.zoho.security.sca.android.Dex.Tagkit;

public class DexDoubleOpTag implements DexTag {
	  public String getName() {
	    return "DoubleOpTag";
	  }

	  public byte[] getValue() {
	    byte[] b = new byte[1];
	    b[0] = 0;
	    return b;
	  }

	}
