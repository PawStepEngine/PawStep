package net.pawstep.engine;

import net.pawstep.engine.components.ComponentManager;

public class PawStepEngine {
	
	private ComponentManager componentManager = new ComponentManager();
	
	public PawStepEngine() {
		
	}
	
	public ComponentManager getComponentManager() {
		return this.componentManager;
	}
	
}
