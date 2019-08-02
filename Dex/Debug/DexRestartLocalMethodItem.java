package com.zoho.security.sca.android.Dex.Debug;

import javax.annotation.Nonnull;

import org.jf.dexlib2.iface.debug.RestartLocal;

public class DexRestartLocalMethodItem extends DexDebugMethodItem {
    @Nonnull private final RestartLocal restartLocal;
    
    public DexRestartLocalMethodItem(int codeAddress, int sortOrder, @Nonnull RestartLocal restartLocal) {
        super(codeAddress, sortOrder);
        this.restartLocal = restartLocal;
        
    }

	@Override
	public void display() {
		// TODO Auto-generated method stub
		
		System.out.print("v"+restartLocal.getRegister()+" ");
		if (restartLocal.getName() != null) {
            System.out.print(restartLocal.getName()+" ");
        } else {
            System.out.print("null");
        }
        System.out.print(':');
        if (restartLocal.getType() != null) {
            System.out.print(restartLocal.getType());
        } else {
            System.out.print("V");
        }
        if (restartLocal.getSignature() != null) {
            System.out.print(", "+restartLocal.getSignature());
             }
		System.out.println();
	}
}