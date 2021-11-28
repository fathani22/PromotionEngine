package main.java.com.promotion.dto;

import java.util.HashMap;
import java.util.Map;

public class Order {
	
	Map<SKU, Integer> SkuQunatity= new HashMap<>();
	Integer totalPrice=0;
	

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Map<SKU, Integer> getSkuQunatity() {
		return SkuQunatity;
	}

	public void setSkuQunatity(Map<SKU, Integer> skuQunatity) {
		SkuQunatity = skuQunatity;
	}
	
		
	

}
