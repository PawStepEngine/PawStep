package net.pawstep.engine.components;

import java.util.HashMap;
import java.util.Map;

import net.pawstep.engine.hierarchy.Component;

public class ComponentManager {
	
	private Map<String, Class<? extends Component>> componentMappings = new HashMap<>();
	
	/**
	 * Registers the class specified as a type of component.  Must have an
	 * <code>@ComponentType</code> annotation on it and have a no-args
	 * constructor.
	 * 
	 * @param clazz The component type to use.
	 */
	public void registerComponentType(Class<? extends Component> clazz) {
		
		ComponentType typeAnno = clazz.getDeclaredAnnotation(ComponentType.class);
		
		if (typeAnno == null) {
			throw new IllegalArgumentException("Class does not have an @ComponentType annotation it!");
		}
		
		String name = typeAnno.name();
		
		// Now check to see if we can instantiate it...
		try {
			clazz.newInstance();
		} catch (Throwable t) {
			throw new IllegalArgumentException("Class does not have a no-args constructor!");
		}
		
		// At this point we're golden.
		this.componentMappings.put(name, clazz);
		
	}
	
	/**
	 * Gets the class for the component with the specified name.  Returns null
	 * if invalid.
	 * 
	 * @param name The name of the component type.
	 * @return The component type's class.
	 */
	public Class<? extends Component> getComponentClass(String name) {
		return this.componentMappings.get(name);
	}
	
}
