package com.zoho.security.sca.android.Dex.Debug;

import org.jf.dexlib2.DebugItemType;
import org.jf.dexlib2.iface.debug.DebugItem;
import org.jf.dexlib2.iface.debug.EndLocal;
import org.jf.dexlib2.iface.debug.LineNumber;
import org.jf.dexlib2.iface.debug.RestartLocal;
import org.jf.dexlib2.iface.debug.StartLocal;
import org.jf.util.ExceptionWithContext;

import com.zoho.security.sca.android.Dex.adapter.DexMethodItem;

public abstract class DexDebugMethodItem extends DexMethodItem {
    private final int sortOrder;

    protected DexDebugMethodItem(int codeAddress, int sortOrder) {
        super(codeAddress);
        this.sortOrder = sortOrder;
    }

    @Override public double getSortOrder() { return sortOrder; }

    public static DexDebugMethodItem build(DebugItem debugItem) {
        int codeAddress = debugItem.getCodeAddress();
        switch (debugItem.getDebugItemType()) {
            case DebugItemType.START_LOCAL:
                return new DexStartLocalMethodItem(codeAddress, -1, (StartLocal)debugItem);
            case DebugItemType.END_LOCAL:
                return new DexEndLocalMethodItem(codeAddress, -1, (EndLocal)debugItem);
            case DebugItemType.RESTART_LOCAL:
                return new DexRestartLocalMethodItem(codeAddress, -1, (RestartLocal) debugItem);
            case DebugItemType.EPILOGUE_BEGIN:
                break;
            case DebugItemType.PROLOGUE_END:
                break;
            case DebugItemType.SET_SOURCE_FILE:
                break;
            case DebugItemType.LINE_NUMBER:
                return new DexLineNumberMethodItem(codeAddress, -2, (LineNumber)debugItem);
            default:
                throw new ExceptionWithContext("Invalid debug item type: %d", debugItem.getDebugItemType());
        }
		return null;
    }
}
