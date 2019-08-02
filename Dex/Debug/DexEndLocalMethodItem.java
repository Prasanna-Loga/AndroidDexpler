package com.zoho.security.sca.android.Dex.Debug;

import javax.annotation.Nonnull;

import org.jf.dexlib2.iface.debug.EndLocal;

public class DexEndLocalMethodItem extends DexDebugMethodItem {
    @Nonnull private final EndLocal endLocal;
    

    public DexEndLocalMethodItem(int codeAddress, int sortOrder, @Nonnull EndLocal endLocal) {
        super(codeAddress, sortOrder);
        this.endLocal = endLocal;
    }

	@Override
	public void display() {
		
		System.out.print("v"+endLocal.getRegister()+" ");
    	if (endLocal.getName() != null) {
            System.out.print(endLocal.getName()+" ");
        } else {
            System.out.print("null");
        }
        System.out.print(':');
        if (endLocal.getType() != null) {
            System.out.print(endLocal.getType());
        } else {
            System.out.print("V");
        }
        if (endLocal.getSignature() != null) {
            System.out.print(", "+endLocal.getSignature());
             }
		System.out.println();
		
	}
}
