package main.java.com.promotion;

import main.java.com.promotion.service.IPromotionRule;
import main.java.com.promotion.service.NonPromotionalRule;

import java.util.HashMap;
import java.util.Map;

import main.java.com.promotion.dto.ComboSKU;
import main.java.com.promotion.dto.FixedSKU;
import main.java.com.promotion.dto.Order;
import main.java.com.promotion.dto.PromotionPriceHolder;
import main.java.com.promotion.dto.SKU;
import main.java.com.promotion.helper.SKUPriceLoader;
import main.java.com.promotion.service.ComboQunatityPromotionalRule;
import main.java.com.promotion.service.FixedNQunatityPromotionRule;

public class PromotionEngineApplication {

	public static Map<String, SKU> initialValuesMap;

	public static void main(String[] args) {
		System.out.println("Welcome to Promotion Engine Application !");

		initialValuesMap = SKUPriceLoader.initiazedSKUPrice();

		IPromotionRule firstRule = createChainOfPromotionalRule();

		// Sceanrio 1

		/*
		 * Scenario A 1 * A 50 1 * B 30 1 * C 20 Total 100
		 */
		
		Map<SKU, Integer> SkuQunatity1= new HashMap<>();
		SkuQunatity1.put(initialValuesMap.get("A"), 1);
		SkuQunatity1.put(initialValuesMap.get("B"), 1);
		SkuQunatity1.put(initialValuesMap.get("C"), 1);
		Order order1 = new Order();
		order1.setSkuQunatity(SkuQunatity1);
		PromotionPriceHolder promotionalHolder= new PromotionPriceHolder(order1, SkuQunatity1);
		firstRule.apply(promotionalHolder);
		System.out.println("Total Bill for Sceanrio A is : " + promotionalHolder.getOrder().getTotalPrice() );

		// Sceanrio 2

				/*
				 * Scenario B
5 * A 130 + 2*50
5 * B 45 + 45 + 30
1 * C 28
Total 370
				 */
				
				Map<SKU, Integer> SkuQunatity2= new HashMap<>();
				SkuQunatity2.put(initialValuesMap.get("A"), 5);
				SkuQunatity2.put(initialValuesMap.get("B"), 5);
				SkuQunatity2.put(initialValuesMap.get("C"), 1);
				Order order2 = new Order();
				order1.setSkuQunatity(SkuQunatity2);
				PromotionPriceHolder promotionalHolder2= new PromotionPriceHolder(order2, SkuQunatity2);
				firstRule.apply(promotionalHolder2);
				System.out.println("Total Bill for Sceanrio B is : " + promotionalHolder2.getOrder().getTotalPrice() );

	
				/*
				 * Scenario C
3 * A 130
5 * B 45 + 45 + 1 * 30
1 * C -
1 * D 30
*/
				
				Map<SKU, Integer> SkuQunatity3= new HashMap<>();
				SkuQunatity3.put(initialValuesMap.get("A"), 3);
				SkuQunatity3.put(initialValuesMap.get("B"), 5);
				SkuQunatity3.put(initialValuesMap.get("C"), 1);
				SkuQunatity3.put(initialValuesMap.get("D"), 1);
				Order order3 = new Order();
				order1.setSkuQunatity(SkuQunatity3);
				PromotionPriceHolder promotionalHolder3= new PromotionPriceHolder(order3, SkuQunatity3);
				firstRule.apply(promotionalHolder3);
				System.out.println("Total Bill for Sceanrio B is : " + promotionalHolder3.getOrder().getTotalPrice() );

				 
	}

	private static IPromotionRule createChainOfPromotionalRule() {

		// Create fixedQuantity rule for 3 A's 130 rs

		FixedSKU fixedSkuA = new FixedSKU(130, 3, initialValuesMap.get("A"));
		IPromotionRule fixedARule = new FixedNQunatityPromotionRule(fixedSkuA);

		FixedSKU fixedSkuB = new FixedSKU(45, 2, initialValuesMap.get("B"));
		IPromotionRule fixedBRule = new FixedNQunatityPromotionRule(fixedSkuB);

		ComboSKU combo = new ComboSKU(initialValuesMap.get("C"), initialValuesMap.get("D"), 30);
		IPromotionRule comboCDRule = new ComboQunatityPromotionalRule(combo);

		IPromotionRule nonPromotionalRule = new NonPromotionalRule(initialValuesMap);

		fixedARule.setNextRule(fixedBRule);
		fixedBRule.setNextRule(comboCDRule);
		comboCDRule.setNextRule(nonPromotionalRule);

		return fixedARule;

	}

}
