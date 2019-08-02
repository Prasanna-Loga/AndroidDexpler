package com.zoho.security.sca.android.Dex.Instruction;

import javax.annotation.Nonnull;

import com.zoho.security.sca.android.Dex.DexOptions;


public class RegisterFormatter {
	
	@Nonnull public final DexOptions options;
    public final int registerCount;
    public final int parameterRegisterCount;

    public RegisterFormatter(@Nonnull DexOptions options, int registerCount, int parameterRegisterCount) {
        this.options = options;
        this.registerCount = registerCount;
        this.parameterRegisterCount = parameterRegisterCount;
        
        System.out.println(registerCount+"*****************"+parameterRegisterCount);
    }
    
    public void display(int register) {
        if (options.parameterRegisters) {
            if (register >= registerCount - parameterRegisterCount) {
                System.out.print('p');
                System.out.print((register - (registerCount - parameterRegisterCount)));
                return;
            }
        }
        System.out.print('v');
        System.out.print(register);
    }
    
    public void displayRegisterRange(int startRegister, int lastRegister) {
        if (options.parameterRegisters) {
            
            if (startRegister >= registerCount - parameterRegisterCount) {
                System.out.print("{p");
                System.out.print(startRegister - (registerCount - parameterRegisterCount));
                System.out.print(" .. p");
                System.out.print(lastRegister - (registerCount - parameterRegisterCount));
                System.out.print('}');
                return;
            }
        }
        System.out.print("{v");
        System.out.print(startRegister);
        System.out.print(" .. v");
        System.out.print(lastRegister);
        System.out.print('}');
    }

}
