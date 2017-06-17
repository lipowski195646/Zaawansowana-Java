package superclasses;

import java.util.Date;

/**
 * Abstract class - the base for the entities of the application (documents)
 * (package "entities")
 * @author vdidukh
 * @since CORE_1
 * @id
 */
public abstract class EntityDoc extends Entity {
	private Date date;
	private String number;
	private float summa;
	
	public EntityDoc(int id, Date date, String number, float summa) {
		super(id);
		
		this.date = date;
		this.number = number;
		this.summa = summa;
	}

	public Date getDate() {
		return date;
	}

	public String getNumber() {
		return number;
	}

	public float getSumma() {
		return summa;
	}

	@Override
    public String toString() {
        return " #" + number + " (" + date + ")"; 
    }
    
}