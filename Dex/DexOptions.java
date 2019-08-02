package com.zoho.security.sca.android.Dex;

import org.jf.dexlib2.analysis.ClassPath;
import org.jf.dexlib2.analysis.InlineMethodResolver;
import org.jf.dexlib2.util.SyntheticAccessorResolver;

public class DexOptions {

	 public int apiLevel = 15;

	    public boolean parameterRegisters = true;
	    public boolean localsDirective = false;
	    public boolean sequentialLabels = false;
	    public boolean debugInfo = true;
	    public boolean codeOffsets = false;
	    public boolean accessorComments = true;
	    public boolean allowOdex = false;
	    public boolean deodex = false;
	    public boolean implicitReferences = false;
	    public boolean normalizeVirtualMethods = false;

	    // register info values
	    public static final int ALL = 1;
	    public static final int ALLPRE = 2;
	    public static final int ALLPOST = 4;
	    public static final int ARGS = 8;
	    public static final int DEST = 16;
	    public static final int MERGE = 32;
	    public static final int FULLMERGE = 64;

	    public int registerInfo = 0;
	    
	    public InlineMethodResolver inlineResolver = null;
	    public ClassPath classPath = null;
	    public SyntheticAccessorResolver syntheticAccessorResolver = null;

}
