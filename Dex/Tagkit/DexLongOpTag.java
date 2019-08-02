package com.zoho.security.sca.android.Dex.Tagkit;


public class DexLongOpTag implements DexTag {
	  public String getName() {
	    return "LongOpTag";
	  }

	  public byte[] getValue() {
	    byte[] b = new byte[1];
	    b[0] = 0;
	    return b;
	  }

	}