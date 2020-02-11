package edu.elsmancs.EnZinium;

import java.security.PublicKey;

public class TokenContract {
	
	private PublicKey owner;
	private String name;
	private String symbol;
	private float totalSupply;

	public TokenContract(Address owner) {
		this.owner = owner.getPK();
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public void setTotalSupply(int totalSupply) {
		this.totalSupply = totalSupply;
	}
	
	@Override
	public String toString() {
		String texto = "name = " + this.name +
				"\nsymbol = " + this.symbol +
				"\ntotal supply = " + String.valueOf(this.totalSupply) +
				"\nowner PK = " + this.owner.hashCode();
		return texto;
	}
}
