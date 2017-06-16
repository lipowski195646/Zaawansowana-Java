package superclasses;

/**
 * Abstract class - the base for the other abstract classes
 * (for references, documents and document items)
 * @author vdidukh
 * @since CORE_1
 * @id
 */
public abstract class Entity {
    private int id;
    
    public Entity() {}
 
    public Entity(int id) {
        this.id = id;
    }
 
    public int getId() {
        return id;
    }
 
}