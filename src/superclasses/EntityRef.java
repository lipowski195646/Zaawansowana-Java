package superclasses;

/**
 * Abstract class - the base for the entities of the application (references)
 * (package "entities")
 * @author vdidukh
 * @since CORE_1
 * @id
 */
public abstract class EntityRef extends Entity {
	protected String name;
    protected String surname;
	
	public EntityRef(int id, String name, String surname) {
		super(id);
		
		this.name = name;
        this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}
	
	@Override
    public String toString() {
		return name + " " + surname;
    }

}