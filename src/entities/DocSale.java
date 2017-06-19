package entities;

import java.util.Date;

import superclasses.EntityDoc;

public class DocSale extends EntityDoc {
	private int sellerId;
	private String sellerName;
	private String sellerSurname;
	
	public DocSale(int id, Date date, String number, int sellerId, String sellerName, String sellerSurname, float summa) {
		super(id, date, number, summa);
		
		this.sellerId = sellerId;
		this.sellerName = sellerName;
		this.sellerSurname = sellerSurname;
	}
	
	public int getSellerId() {
		return sellerId;
	}

	public String getSellerName() {
		return sellerName + " " + sellerSurname;
	}

	@Override
    public String toString() {
        return "<Sale" + super.toString() + ">";
    }
	
}