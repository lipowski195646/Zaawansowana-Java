package entities;

import superclasses.Entity;

public class RefProduct extends Entity {
	private String name;
	private String unit;
	private int points;
    private int warranty;
	private float priceIn;
    private float priceOut;
	
	public RefProduct(int id, String name, String unit, int points, int warranty, float priceIn, float priceOut) {
		super(id);
		
		this.name = name;
		this.unit = unit;
		this.points = points;
		this.warranty = warranty;
		this.priceIn = priceIn;
		this.priceOut = priceOut;
	}

	public String getName() {
		return name;
	}

	public String getUnit() {
		return unit;
	}

	public int getPoints() {
		return points;
	}

	public int getWarranty() {
		return warranty;
	}

	public float getPriceIn() {
		return priceIn;
	}

	public float getPriceOut() {
		return priceOut;
	}

	@Override
    public String toString() {
		return name;
    }
    
}