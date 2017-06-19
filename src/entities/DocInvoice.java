package entities;

import java.util.Date;

import superclasses.EntityDoc;

public class DocInvoice extends EntityDoc {
	private int clientId;
	private String clientName;
	private String clientSurname;
	private int storeId;
	private String storeName;
	private String storeSurname;
	
	public DocInvoice(int id, Date date, String number, int clientId, String clientName, String clientSurname, int storeId, String storeName, String storeSurname, float summa) {
		super(id, date, number, summa);
		
		this.clientId = clientId;
		this.clientName = clientName;
		this.clientSurname = clientSurname;
		this.storeId = storeId;
		this.storeName = storeName;
		this.storeSurname = storeSurname;
	}

	public int getClientId() {
		return clientId;
	}

	public String getClientName() {
		return clientName + " " + clientSurname;
	}

	public int getStoreId() {
		return storeId;
	}

	public String getStoreName() {
		return storeName + " " + storeSurname;
	}
	
	@Override
    public String toString() {
        return "<Invoice " + super.toString() + ">";
    }
	
}