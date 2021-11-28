package main.java.com.promotion.service;

import java.util.Map;

import main.java.com.promotion.dto.PromotionPriceHolder;
import main.java.com.promotion.dto.SKU;
import main.java.com.promotion.service.AbstarctPromotionalRule;
import main.java.com.promotion.dto.FixedSKU;

public class FixedNQunatityPromotionRule extends AbstarctPromotionalRule {

	FixedSKU fixedSku;

	public FixedNQunatityPromotionRule(FixedSKU fixedSku) {
		super();
		this.fixedSku = fixedSku;
	}

	@Override
	public boolean apply(PromotionPriceHolder order) {

		Map<SKU, Integer> orderToBeProcess = order.getSkuQunatityToProcess();
		boolean ispromoapplicable = false;

		SKU fixedPromotionalSKu = fixedSku.getFixedSKU();
		int minimumQunatity = fixedSku.getFixedQunatity();
		int totalPrice = order.getOrder().getTotalPrice();
		if (orderToBeProcess.containsKey(fixedPromotionalSKu) ) {
		
			int orderedQunatity = orderToBeProcess.get(fixedSku.getFixedSKU());
			if (orderedQunatity >= minimumQunatity) {
				int appliedQ = orderedQunatity / minimumQunatity;
				totalPrice = totalPrice + (appliedQ * fixedSku.getPrice());
				int remainingSKU = orderedQunatity % minimumQunatity;
				order.getOrder().setTotalPrice(totalPrice);
				orderToBeProcess.put(fixedPromotionalSKu, remainingSKU);
			}

		}
		if (this.nextRule != null) {
			return this.nextRule.apply(order);
		} else
			return ispromoapplicable;
	}

}
