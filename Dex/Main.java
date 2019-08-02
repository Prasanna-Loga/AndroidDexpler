package com.zoho.security.sca.android.Dex;

import java.io.File;
import java.io.IOException;

import org.jf.dexlib2.DexFileFactory;
import org.jf.dexlib2.Opcodes;

import org.jf.dexlib2.iface.ClassDef;
import org.jf.dexlib2.iface.DexFile;

import com.zoho.security.sca.android.Dex.adapter.DexClassDefinition;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		long startTime = System.currentTimeMillis();
		
//		String file="/home/local/ZOHOCORP/prasanna-8769/Downloads/app-debug/classes.dex";
		String file="/home/local/ZOHOCORP/prasanna-8769/AndroidStudioProjects/MyApplication/app/build/outputs/apk/debug/app-debug.apk";
		
		
		//int jobs = Runtime.getRuntime().availableProcessors();
		
		DexOptions option=new DexOptions();
		
		
		DexFile dexFile;	
		
		System.out.println(Opcodes.getDefault());
		
		
		dexFile = DexFileFactory.loadDexFile(new File(file), Opcodes.getDefault());
		
		System.out.println(file);
		
		for (ClassDef classDef : dexFile.getClasses()) {
			
			System.out.println();
			
			DexClassDefinition classDefinition=new DexClassDefinition(option, classDef);
			classDefinition.parseClass();
			}
		
		
		Runtime runtime = Runtime.getRuntime();
		// Run the garbage collector
		runtime.gc();
		// Calculate the used memory
		long memory = runtime.totalMemory() - runtime.freeMemory();
		System.out.println("Used memory is bytes: " + memory / (1024L * 1024L) + "mb");

		 

		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println("Time: " + elapsedTime + "ms");
		
		
	}

}
