package main.java.com.promotion.service;

import main.java.com.promotion.dto.PromotionPriceHolder;

public abstract class AbstarctPromotionalRule  implements  IPromotionRule {
	
	protected IPromotionRule nextRule;
	public void setNextRule(IPromotionRule rule) {
		this.nextRule=rule;
	}
	
	public boolean applyNextRuleIfExits(PromotionPriceHolder promoPrice)
	{
		
		if(this.nextRule!=null)
		{
			return this.nextRule.apply(promoPrice);
		}
		
		return false;
	}
	

}
