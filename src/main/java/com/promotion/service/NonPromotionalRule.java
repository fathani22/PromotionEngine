package main.java.com.promotion.service;

import java.util.Map;
import java.util.Map.Entry;

import main.java.com.promotion.dto.PromotionPriceHolder;
import main.java.com.promotion.dto.SKU;

public class NonPromotionalRule  extends AbstarctPromotionalRule{

	Map<String, SKU> actualPrice;
	
	
	public NonPromotionalRule(Map<String, SKU> actualPrice) {
		super();
		this.actualPrice = actualPrice;
	}


	@Override
	public boolean apply(PromotionPriceHolder order) {
		
		int totalBillAmount= order.getOrder().getTotalPrice();
		for(Map.Entry<SKU, Integer> skuEntry:order.getSkuQunatityToProcess().entrySet())
		{
			if(skuEntry.getValue()>0)
			{
				totalBillAmount+=actualPrice.get(skuEntry.getKey().getSkuName()).getSkuPricePerUnit()*skuEntry.getValue();
				
			}
		}
		order.getOrder().setTotalPrice(totalBillAmount);
		if (this.nextRule != null) {
			return this.nextRule.apply(order);
		} else
			return true;
	
	}
}
