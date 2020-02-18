package edu.elsmancs.EnZinium;

import java.security.PublicKey;
import java.util.HashMap;

public class TokenContract {
	
	private PublicKey owner;
	private final HashMap<PublicKey, Double> balances = new HashMap<PublicKey, Double>();
	private String name;
	private String symbol;
	private double totalSupply;

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

	public double totalSupply() {
		return this.totalSupply;
	}

	public void addOwner(PublicKey publicKey, double supply) {
		if (!this.balances.containsKey(publicKey)) {
			this.balances.put(publicKey, supply);
		}
	}

	public int numOwners() {
		return this.balances.size();
	}

	public double balanceOf(PublicKey publicKey) {
		return this.balances.containsKey(publicKey) ? this.balances.get(publicKey) : 0;
	}

	public String symbol() {
		return this.symbol;
	}
	
	private boolean require(PublicKey publicKey, double tokens) throws Exception {
		if (this.balanceOf(publicKey) < tokens) {
			throw new Exception();
		} else {
			return true;
		}
	}
	
	public void transfer(PublicKey publicKey, double tokens) {
		try {
			if (require(this.owner, tokens)) {
				this.balances.put(this.owner, this.balanceOf(this.owner) - tokens);
				if (this.balances.containsKey(publicKey)) {
					this.balances.put(publicKey, this.balanceOf(publicKey) + tokens);
				} else {
					this.balances.put(publicKey, tokens);
				}
			}
		} catch (Exception e) {
			;; //No hace nada en caso de lanzar el error
		}
	}
	
	public void transfer(PublicKey publicKeyRemitente, PublicKey publicKeyDestinatario, double tokens) {
		try {
			if (require(publicKeyRemitente, tokens) && balanceOf(publicKeyRemitente) >= 0) {
				balances.put(publicKeyRemitente, balanceOf(publicKeyRemitente) - tokens);
				if (balances.containsKey(publicKeyDestinatario)) {
					balances.put(publicKeyDestinatario, balanceOf(publicKeyDestinatario) + tokens);
				} else {
					balances.put(publicKeyDestinatario, tokens);
				}
			}
		} catch (Exception e) {
			;; //En caso de error no hace nada
		}
	}
	
	public void owners() {
		for (PublicKey publicKey : balances.keySet()) {
			if (publicKey != this.owner) {
				System.out.println("Owner: " + publicKey.hashCode()
						+ " " + balanceOf(publicKey) + " "
						+ this.symbol);
			}
		}
	}
	
	public int totalTokensSold() {
		int tokensVendidos = 0;
		for (PublicKey publicKey : balances.keySet()) {
			if (publicKey != this.owner) {
				tokensVendidos += balanceOf(publicKey);
			}
		}
		return tokensVendidos;
	}
}
