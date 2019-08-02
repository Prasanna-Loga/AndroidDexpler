package com.zoho.security.sca.android.Dex.Debug;

import javax.annotation.Nonnull;

import org.jf.dexlib2.iface.debug.LineNumber;

public class DexLineNumberMethodItem extends DexDebugMethodItem {
    private final int lineNumber;

    public DexLineNumberMethodItem(int codeAddress, int sortOrder, @Nonnull LineNumber lineNumber) {
        super(codeAddress, sortOrder);
        this.lineNumber = lineNumber.getLineNumber();
    }


	@Override
	public void display() {
		// TODO Auto-generated method stub
		System.out.println(lineNumber);
	}
}
