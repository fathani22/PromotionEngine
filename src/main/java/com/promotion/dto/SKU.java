package main.java.com.promotion.dto;

public class SKU {

	public SKU(String skuName, int skuPricePerUnit) {
		super();
		SkuName = skuName;
		SkuPricePerUnit = skuPricePerUnit;
	}

	String SkuName;
	int SkuPricePerUnit;
	

	public String getSkuName() {
		return SkuName;
	}

	public void setSkuName(String skuName) {
		SkuName = skuName;
	}

	public int getSkuPricePerUnit() {
		return SkuPricePerUnit;
	}

	public void setSkuPricePerUnit(int skuPricePerUnit) {
		SkuPricePerUnit = skuPricePerUnit;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)

			if (obj == null || obj.getClass() != this.getClass())
				return false;

		SKU sku = (SKU) obj;

		return (sku.SkuName == this.SkuName);
	}

	@Override
	public int hashCode() {

		return super.hashCode();
	}

}
