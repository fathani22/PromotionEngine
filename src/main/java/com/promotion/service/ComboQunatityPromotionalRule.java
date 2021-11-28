package main.java.com.promotion.service;

import java.util.Map;

import main.java.com.promotion.dto.ComboSKU;
import main.java.com.promotion.dto.FixedSKU;
import main.java.com.promotion.dto.PromotionPriceHolder;
import main.java.com.promotion.dto.SKU;

public class ComboQunatityPromotionalRule extends AbstarctPromotionalRule {

	ComboSKU comboSku;

	public ComboQunatityPromotionalRule(ComboSKU comboSku) {
		super();
		this.comboSku = comboSku;
	}

	@Override
	public boolean apply(PromotionPriceHolder order) {

		Map<SKU, Integer> orderToBeProcess = order.getSkuQunatityToProcess();
		boolean ispromoapplicable = false;

		SKU sku1 = comboSku.getSku1();
		SKU sku2 = comboSku.getSku1();

		int totalPrice = order.getOrder().getTotalPrice();
		if (orderToBeProcess.containsKey(sku1) && orderToBeProcess.containsKey(sku2)) {
			int orderedQunatity1 = orderToBeProcess.get(sku1);
			int orderedQunatity2 = orderToBeProcess.get(sku2);
			int comboQunatity = orderedQunatity1 > orderedQunatity2 ? orderedQunatity2 : orderedQunatity1;

			totalPrice = totalPrice + (comboQunatity * comboSku.getComboPrice());

			order.getOrder().setTotalPrice(totalPrice);
			orderToBeProcess.put(sku1, orderedQunatity1 - comboQunatity);
			orderToBeProcess.put(sku2, orderedQunatity2 - comboQunatity);

		}
		if (this.nextRule != null) {
			return this.nextRule.apply(order);
		} else
			return ispromoapplicable;
	}

}
