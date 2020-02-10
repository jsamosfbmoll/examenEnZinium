package edu.elsmancs.EnZinium;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class Address {
	
	private PublicKey PK = null;
	private PrivateKey SK = null;
	private int balance = 0;
	private String symbol = "EZI";

	public void generateKeyPair() {
		
		KeyPair keys = GenSig.generateKeyPair();
		this.PK = keys.getPublic();
		this.SK = keys.getPrivate();
	}
	
	@Override
	public String toString() {
		return Integer.toString(PK.hashCode()) + "\nBalance = " + Integer.toString(balance) + " " + symbol;
	}
}
