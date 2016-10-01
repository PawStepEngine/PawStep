package net.pawstep.engine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a collection of entities running in the same "simulation".
 * 
 * @author treyzania
 */
public class Scene implements EntityContainer, PhysicalObject {
	
	private List<Entity> entities;
	
	@Override
	public List<Entity> getChildren() {
		return new ArrayList<>(this.entities);
	}

	@Override
	public Entity newEntity(String name) {
		
		if (this.hasChild(name)) throw new IllegalArgumentException("A child with this name already exists!");
		
		Entity e = new Entity(name);
		e.setContainer(this);
		
		return e;
		
	}
	
	@Override
	public boolean removeChild(String name) {
		
		int count = 0;
		Iterator<Entity> ents = this.entities.iterator();
		
		while (ents.hasNext()) {
			
			Entity ent = ents.next();
			
			if (ent.name.equals(name)) {
				
				ent.setContainer(null);
				ents.remove();
				
			}
			
		}
		
		return count > 0;
		
	}

	@Override
	public Scene getScene() {
		return this; // lol
	}
	
}
