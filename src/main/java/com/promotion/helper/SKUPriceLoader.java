package main.java.com.promotion.helper;

import java.util.HashMap;
import java.util.Map;

import main.java.com.promotion.dto.SKU;

public class SKUPriceLoader {
	
	public static Map initiazedSKUPrice()
	{
		Map<String, SKU> skuPriceMap= new HashMap<>();
		SKU sku1= new SKU("A",50 );
		SKU sku2= new SKU("B",30 );
		SKU sku3= new SKU("C",20 );
		SKU sku4= new SKU("D",15 );
		skuPriceMap.put("A", sku1);	
		skuPriceMap.put("B", sku2);	
		skuPriceMap.put("C", sku3);	
		skuPriceMap.put("D", sku4);	
		return skuPriceMap;		
		
	}

}
