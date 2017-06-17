package superclasses;

/**
 * Abstract class - the base for the entities of the application (document items)
 * (package "entities")
 * @author vdidukh
 * @since CORE_1
 * @id
 */
public abstract class EntityItem extends Entity {
	private float summa;
	
	public EntityItem(int id, float summa) {
		super(id);
		
		this.summa = summa;
	}
	
	public float getSumma() {
		return summa;
	}

}