package net.pawstep.engine.hierarchy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a collection of entities running in the same "simulation".
 * 
 * @author treyzania
 */
public class Scene implements EntityContainer, PhysicalObject {
	
	private final SceneManager manager;
	protected boolean activated = false;
	
	private List<Entity> entities = new ArrayList<>();
	
	public Scene(SceneManager man) {
		this.manager = man;
	}
	
	@Override
	public List<Entity> getChildren() {
		return new ArrayList<>(this.entities);
	}
	
	@Override
	public Entity newEntity(String name) {
		
		if (this.hasChild(name)) throw new IllegalArgumentException("A child with this name already exists!");
		
		Entity e = new Entity(name);
		e.setContainer(this);
		
		this.entities.add(e); // lol I forgot this forever and spent >30 minutes debugging it
		
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
	
	/**
	 * Gets this scene's manager.
	 * 
	 * @return The scene manager.
	 */
	public SceneManager getManager() {
		return this.manager;
	}
	
}
