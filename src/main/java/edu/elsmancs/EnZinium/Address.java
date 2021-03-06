package edu.elsmancs.EnZinium;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class Address {
	
	private PublicKey PK = null;
	@SuppressWarnings("unused")
	private PrivateKey SK = null;
	private double balance = 0;
	private String symbol = "EZI";

	public void generateKeyPair() {
		
		KeyPair keys = GenSig.generateKeyPair();
		this.PK = keys.getPublic();
		this.SK = keys.getPrivate();
	}
	
	PublicKey getPK() {
		return this.PK;
	}
	
	@Override
	public String toString() {
		return Integer.toString(PK.hashCode()) + "\nBalance = " + String.valueOf(balance) + " " + symbol;
	}
	
	public void transferEZI(double ezi) {
		this.balance += ezi;
	}
	
	public void send(TokenContract contrato, double ezi) {
		contrato.payable(this, ezi);
	}

	public double getBalance() {
		return this.balance;
	}
}
