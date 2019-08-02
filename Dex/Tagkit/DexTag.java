package com.zoho.security.sca.android.Dex.Tagkit;

public interface DexTag {
	  /** Returns the tag name. */
	  public String getName();

	  /** Returns the tag raw data. */
	  public byte[] getValue() throws DexAttributeValueException;
	}