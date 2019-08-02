package com.zoho.security.sca.android.Dex;


/**
 * A local variable, used within Body classes. Intermediate representations must use an implementation of Local for their
 * local variables.
 */
public class Local implements Numberable {
 private String name;
 private String type;
 
 
@Override
public void setNumber(int number) {
	// TODO Auto-generated method stub
	
}
@Override
public int getNumber() {
	// TODO Auto-generated method stub
	return 0;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
 
 
}