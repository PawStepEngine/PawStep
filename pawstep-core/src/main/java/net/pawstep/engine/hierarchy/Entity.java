package net.pawstep.engine.hierarchy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.pawstep.engine.PawStepEngine;

/**
 * The generic container class for behavior of these entities.
 * 
 * @author treyzania
 */
public class Entity implements EntityContainer, PhysicalObject {
	
	protected transient EntityContainer parent;
	protected String name;
	
	protected Transform transform = new Transform();
	
	private List<Entity> children = new ArrayList<>();
	private List<Component> components = new ArrayList<>();
	
	protected Entity(String name) {
		this.name = name;
	}
	
	/**
	 * Gets this object's transform.
	 * 
	 * @return The transform.
	 */
	public Transform getTransform() {
		return this.transform;
	}
	
	/**
	 * Adds a new component of the specified type, assuming that one doesn't already exist.
	 * 
	 * @param component The component class to create.
	 * @return The instantiated component.
	 */
	public <T extends Component> T addComponent(Class<T> component) {
		
		if (this.hasComponent(component)) throw new IllegalArgumentException("Entity already has component of that type!");
		
		PawStepEngine.getLogger().info("Attaching " + component.getName() + " onto " + this.name + "...");
		
		try {
			
			T comp = component.newInstance();
			
			// Set the owner to us.
			comp.entity = this;
			
			// Now awaken it!
			comp.awake();
			
			this.components.add(comp);
			return comp;
			
		} catch (InstantiationException e) {
			throw new RuntimeException("Problem creating component!", e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Problem creating component!", e);
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	/**
	 * Gets the component of the object or creates a new one if necessary.
	 * 
	 * @param component The component class to get an instance of.
	 * @return The component we've created or found.
	 */
	public <T extends Component> T getOrCreateComponent(Class<T> component) {
		
		try {
			return this.addComponent(component);
		} catch (IllegalArgumentException e) {
			return this.getComponent(component);
		}
		
	}
	
	/**
	 * Checks to see if there exists an instance of the specified component
	 * type on this entity.
	 * 
	 * @param component The class to check for.
	 * @return <code>true</code> if there is one, <code>false</code> if there isn't.
	 */
	public boolean hasComponent(Class<? extends Component> component) {
		return this.getComponent(component) != null;
	}
	
	/**
	 * Gets the the instance of the specified component type on this entity,
	 * can return null.
	 * 
	 * @param clazz The type to check for.
	 * @return The entity's component.
	 */
	@SuppressWarnings("unchecked")
	public <T extends Component> T getComponent(Class<T> clazz) {
		
		if (clazz == null) return null;
		
		for (Component c : this.components) {
			if (c.getClass().isAssignableFrom(clazz)) return (T) c;
		}
		
		return null;
		
	}
	
	/**
	 * Removes the instance of the specified component from this entity.
	 * 
	 * @param clazz The type of component to remove.
	 * @return If there was a component to remove at all.
	 */
	public <T extends Component> boolean removeComponent(Class<T> clazz) {
		return this.components.removeIf(c -> c.getClass().equals(clazz));
	} 
	
	/**
	 * Gets all instances of the specified component type in all of this
	 * entity's children.
	 * 
	 * @param clazz The component type.
	 * @param recursive If we should check all of the decendents or just the first generation.
	 * @return The list of relevant component.
	 */
	public <T extends Component> List<T> getComponentsInChildren(Class<T> clazz, boolean recursive) {
		
		List<T> list = new ArrayList<>();
		
		for (Entity e : this.getChildren()) {
			
			if (e.hasComponent(clazz)) list.add(e.getComponent(clazz));
			if (recursive) list.addAll(e.getComponentsInChildren(clazz, true));
			
		}
		
		return list;
		
	}
	
	@Override
	public List<Entity> getChildren() {
		return new ArrayList<>(this.children);
	}
	
	/**
	 * Returns a list of all of the components on this entity.
	 * 
	 * @return The components.
	 */
	public List<Component> getComponents() {
		return new ArrayList<>(this.components);
	}
	
	@Override
	public boolean removeChild(String name) {
		
		int count = 0;
		Iterator<Entity> ents = this.children.iterator();
		
		while (ents.hasNext()) {
			
			Entity ent = ents.next();
			
			if (ent.name.equals(name)) {
				
				ent.setContainer(null);
				ents.remove();
				
			}
			
		}
		
		return count > 0;
		
	}
	
	protected void setContainer(EntityContainer container) {
		this.parent = container;		
	}
	
	public EntityContainer getContainer() {
		return this.parent;
	}
	
	/**
	 * Gets this entity's parent if the parent is an entity.
	 * 
	 * @return The parent.
	 */
	public Entity getParentEntity() {
		return this.parent instanceof Entity ? (Entity) this.parent : null;
	}
	
	@Override
	public Entity newEntity(String name) {
		
		if (this.hasChild(name)) throw new IllegalArgumentException("A child with this name already exists!");
		
		Entity e = new Entity(name);
		e.setContainer(this);
		this.children.add(e); // lol I fixed this faster this time, but I still forgot it for a while
		
		return e;
		
	}
	
	@Override
	public Scene getScene() {
		return this.parent instanceof Scene ? (Scene) this.parent : ((Entity) this.parent).getScene();
	}
	
}
