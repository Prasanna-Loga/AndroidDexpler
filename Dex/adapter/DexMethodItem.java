package com.zoho.security.sca.android.Dex.adapter;

public abstract class DexMethodItem implements Comparable<DexMethodItem> {
    protected final int codeAddress;

    protected DexMethodItem(int codeAddress) {
        this.codeAddress = codeAddress;
    }

    public int getCodeAddress() {
        return codeAddress;
    }

    //return an arbitrary double that determines how this item will be sorted with others at the same address
    public abstract double getSortOrder();

    public int compareTo(DexMethodItem methodItem) {
        int result = ((Integer) codeAddress).compareTo(methodItem.codeAddress);

        if (result == 0){
            return ((Double)getSortOrder()).compareTo(methodItem.getSortOrder());
        }
        return result;
    }

    public abstract void display();
}

