package net.pawstep.engine.hierarchy;

import java.util.List;
import java.util.function.Consumer;

/**
 * Any kind of object that can contain entites.
 * 
 * @author treyzania
 */
public interface EntityContainer {
	
	/**
	 * Gets all of the entities that this container has as its children.
	 * 
	 * @return The children.
	 */
	public List<Entity> getChildren();
	
	/**
	 * Checks to see if this container has an immediate child of the specified name.
	 * 
	 * @param name The name to check for.
	 * @return <code>true</code> if a child with the name exists, <code>false</code> otherwise.
	 */
	public default boolean hasChild(String name) {
		return this.findChild(name) != null;
	}
	
	/**
	 * Finds an entity that is an immediate descendant of this container.
	 * 
	 * @param name
	 * @return
	 */
	public default Entity findChild(String name) {
		
		for (Entity e : this.getChildren()) {
			if (e.name.equals(name)) return e;
		}
		
		return null;
		
	}
	
	/**
	 * Finds children down the specified path.  The path separator is the colon
	 * character, ':'.  This method can return null if the child is not found.
	 * 
	 * @param path The path to search down.
	 * @return The entity we found.
	 */
	public default Entity findNestedChild(String path) {
		
		String[] pathComponents = path.split(":", 2);
		
		try {
			
			Entity myChild = this.findChild(pathComponents[0]);
			
			if (myChild == null) return null;
			
			if (pathComponents.length == 2) {
				return myChild.findNestedChild(pathComponents[1]);
			} else {
				return myChild;
			}
			
		} catch (NullPointerException e) {
			return null; // This shouldn't happen.
		}
		
	}
	
	/**
	 * Removes a child with the specified name.
	 * 
	 * @param name The child to remove.
	 * @return If the child existed on there in the first place.
	 */
	public boolean removeChild(String name);
	
	/**
	 * Removes the matching entity from the container.  Also removes the
	 * entity's parent and scene references.
	 * 
	 * @param ent The entity to remove.
	 * @return If the entity was a child of this object in the first place.
	 */
	public default boolean removeChild(Entity ent) {
		return this.removeChild(ent.name);
	}
	
	public default void forEachChild(Consumer<Entity> action) {
		
		this.getChildren().forEach(e -> {
			
			try {
				action.accept(e);
			} catch (Throwable t) {
				// TODO Error handling.
			}
			
			e.forEachChild(action);
			
		});
		
	}
	
	/**
	 * Invokes the action for each component on all entity in the container.
	 * 
	 * @param action The action to conduct.
	 */
	public default void forEachComponent(Consumer<Component> action) {
		
		this.forEachChild(e -> {
			e.getComponents().forEach(c -> action.accept(c));
		});
		
	}
	
	/**
	 * Creates a new entity as a child to this container.
	 * 
	 * @param name The name of the entity to create.
	 * @return The entity.
	 */
	public Entity newEntity(String name);
	
}
