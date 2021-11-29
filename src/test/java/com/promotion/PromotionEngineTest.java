package test.java.com.promotion;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.framework.Assert;
import main.java.com.promotion.dto.ComboSKU;
import main.java.com.promotion.dto.FixedSKU;
import main.java.com.promotion.dto.Order;
import main.java.com.promotion.dto.PromotionPriceHolder;
import main.java.com.promotion.dto.SKU;
import main.java.com.promotion.helper.SKUPriceLoader;
import main.java.com.promotion.service.ComboQunatityPromotionalRule;
import main.java.com.promotion.service.FixedNQunatityPromotionRule;
import main.java.com.promotion.service.IPromotionRule;
import main.java.com.promotion.service.NonPromotionalRule;

class PromotionEngineTest {
	
	Map<String, SKU> initialValuesMap;
	IPromotionRule firstRule;
	
	@BeforeEach
	void setup()
	{
		initialValuesMap = SKUPriceLoader.initiazedSKUPrice();

		firstRule = createChainOfPromotionalRule();
	}

	@Test
	void test1() {
		
		Map<SKU, Integer> SkuQunatity1= new HashMap<>();
		SkuQunatity1.put(initialValuesMap.get("A"), 1);
		SkuQunatity1.put(initialValuesMap.get("B"), 1);
		SkuQunatity1.put(initialValuesMap.get("C"), 1);
		Order order1 = new Order();
		order1.setSkuQunatity(SkuQunatity1);
		PromotionPriceHolder promotionalHolder= new PromotionPriceHolder(order1, SkuQunatity1);
		firstRule.apply(promotionalHolder);
		Assert.assertEquals(100, (int)order1.getTotalPrice());
		
	}
	
	@Test
	void test2() {
		
		Map<SKU, Integer> SkuQunatity2= new HashMap<>();
		SkuQunatity2.put(initialValuesMap.get("A"), 5);
		SkuQunatity2.put(initialValuesMap.get("B"), 5);
		SkuQunatity2.put(initialValuesMap.get("C"), 1);
		Order order2 = new Order();
		order2.setSkuQunatity(SkuQunatity2);
		PromotionPriceHolder promotionalHolder2= new PromotionPriceHolder(order2, SkuQunatity2);
		firstRule.apply(promotionalHolder2);
		//System.out.println("Total Bill for Sceanrio B is : " + promotionalHolder2.getOrder().getTotalPrice() );
		Assert.assertEquals(370, (int)order2.getTotalPrice());
		
	}
	
	@Test
	void test3() {
		
		Map<SKU, Integer> SkuQunatity3= new HashMap<>();
		SkuQunatity3.put(initialValuesMap.get("A"), 3);
		SkuQunatity3.put(initialValuesMap.get("B"), 5);
		SkuQunatity3.put(initialValuesMap.get("C"), 1);
		SkuQunatity3.put(initialValuesMap.get("D"), 1);
		Order order3 = new Order();
		order3.setSkuQunatity(SkuQunatity3);
		PromotionPriceHolder promotionalHolder3= new PromotionPriceHolder(order3, SkuQunatity3);
		firstRule.apply(promotionalHolder3);
		Assert.assertEquals(280, (int)order3.getTotalPrice());
		
	}
	private IPromotionRule createChainOfPromotionalRule() {

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
