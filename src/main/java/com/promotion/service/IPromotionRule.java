package main.java.com.promotion.service;

import main.java.com.promotion.dto.Order;
import main.java.com.promotion.dto.PromotionPriceHolder;

public interface IPromotionRule {
	
	
	void setNextRule(IPromotionRule rule) ;
	public boolean apply(PromotionPriceHolder order);

}
