package entities;

import superclasses.EntityItem;

public class DocSaleItem extends EntityItem {
	private int storeId;
	private String storeName;
	private String storeSurname;
	private int productId;
	private String productName;
	private String productUnit;
	private float quantity;
	private float price;
	
	public DocSaleItem(int id, int storeId, String storeName, String storeSurname, int productId, String productName, String productUnit, float quantity, float price, float summa) {
		super(id, summa);
		
		this.storeId = storeId;
		this.storeName = storeName;
		this.storeSurname = storeSurname;
		this.productId = productId;
		this.productName = productName;
		this.productUnit = productUnit;
		this.quantity = quantity;
		this.price = price;
	}
	
	public int getStoreId() {
		return storeId;
	}

	public String getStoreName() {
		return storeName + " " + storeSurname;
	}

	public int getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public String getProductUnit() {
		return productUnit;
	}

	public float getQuantity() {
		return quantity;
	}

	public float getPrice() {
		return price;
	}

	@Override
    public String toString() {
        return productName + ": " + quantity + " " + productUnit + " * " + price + " = " + super.getSumma();
    }

}