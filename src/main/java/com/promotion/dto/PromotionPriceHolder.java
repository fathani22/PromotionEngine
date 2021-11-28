package main.java.com.promotion.dto;

import java.util.HashMap;
import java.util.Map;

public class PromotionPriceHolder {
	
	Order order;
	Map<SKU, Integer> SkuQunatityToProcess= new HashMap<>();
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Map<SKU, Integer> getSkuQunatityToProcess() {
		return SkuQunatityToProcess;
	}
	public void setSkuQunatityToProcess(Map<SKU, Integer> skuQunatityToProcess) {
		SkuQunatityToProcess = skuQunatityToProcess;
	}
	public PromotionPriceHolder(Order order, Map<SKU, Integer> skuQunatityToProcess) {
		super();
		this.order = order;
		SkuQunatityToProcess = skuQunatityToProcess;
	}
	
	

}
