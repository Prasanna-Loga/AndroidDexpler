package com.zoho.security.sca.android.Dex.Debug;

import javax.annotation.Nonnull;

import org.jf.dexlib2.iface.debug.StartLocal;


public class DexStartLocalMethodItem extends DexDebugMethodItem {
	
	
    @Nonnull private final StartLocal startLocal;
    

    public DexStartLocalMethodItem(int codeAddress, int sortOrder, @Nonnull StartLocal startLocal) {
        super(codeAddress, sortOrder);
        this.startLocal = startLocal;
        
    }

	@Override
	public void display() {
		
		System.out.print("v"+startLocal.getRegister()+" ");
		if (startLocal.getName() != null) {
            System.out.print(startLocal.getName()+" ");
        } else {
            System.out.print("null");
        }
        System.out.print(':');
        if (startLocal.getType() != null) {
            System.out.print(startLocal.getType());
        } else {
            System.out.print("V");
        }
        if (startLocal.getSignature() != null) {
            System.out.print(", "+startLocal.getSignature());
             }
		System.out.println();
	}
}

