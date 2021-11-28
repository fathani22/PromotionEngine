package main.java.com.promotion.dto;

public class ComboSKU {

	SKU sku1;

	SKU sku2;

	int comboPrice;

	public ComboSKU(SKU sku1, SKU sku2, int comboPrice) {
		super();
		this.sku1 = sku1;

		this.sku2 = sku2;

		this.comboPrice = comboPrice;
	}

	public SKU getSku1() {
		return sku1;
	}

	public void setSku1(SKU sku1) {
		this.sku1 = sku1;
	}

	public SKU getSku2() {
		return sku2;
	}

	public void setSku2(SKU sku2) {
		this.sku2 = sku2;
	}

	public int getComboPrice() {
		return comboPrice;
	}

	public void setComboPrice(int comboPrice) {
		this.comboPrice = comboPrice;
	}

}
