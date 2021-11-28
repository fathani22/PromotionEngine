package main.java.com.promotion.dto;

import main.java.com.promotion.dto.SKU;

public class FixedSKU {
	
	SKU fixedSKU;
	int fixedQunatity;
	int price;
	public FixedSKU(int price, int fixedQunatity, SKU fixedSKU) {
		super();
		this.price = price;
		this.fixedQunatity = fixedQunatity;
		this.fixedSKU = fixedSKU;
	}
	public SKU getFixedSKU() {
		return fixedSKU;
	}
	public void setFixedSKU(SKU fixedSKU) {
		this.fixedSKU = fixedSKU;
	}
	public int getFixedQunatity() {
		return fixedQunatity;
	}
	public void setFixedQunatity(int fixedQunatity) {
		this.fixedQunatity = fixedQunatity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
	

}
