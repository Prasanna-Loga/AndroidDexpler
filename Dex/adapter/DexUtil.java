package com.zoho.security.sca.android.Dex.adapter;

public class DexUtil {
	
	public String getQualifiedName(String typeDescriptor) {
		
		if (isByteCodeClassName(typeDescriptor)) {
		String t = typeDescriptor;
	    int idx = 0;
	    while (idx < t.length() && t.charAt(idx) == '[') {
	      idx++;
	    }
	    
	    String className = typeDescriptor.substring(idx);

	    className = className.substring(className.indexOf('L') + 1, className.indexOf(';'));

	    className = className.replace('/', '.');
	    // for (int i = 0; i<idx; i++) {
	    // className += "[]";
	    // }
	    return className;
		}
		return typeDescriptor;

}
	 /**
	   * Check if passed class name is a byte code classname.
	   *
	   * @param className
	   *          the classname to check.
	   */
	  public static boolean isByteCodeClassName(String className) {
	    return ((className.startsWith("L") || className.startsWith("[")) && className.endsWith(";")
	        && ((className.indexOf('/') != -1 || className.indexOf('.') == -1)));
	  }
}