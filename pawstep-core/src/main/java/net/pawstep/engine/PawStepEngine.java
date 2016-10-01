package net.pawstep.engine;

import net.pawstep.engine.components.ComponentManager;
import net.pawstep.engine.render.RenderManager;

public class PawStepEngine {
	
	protected static PawStepEngine engine;
	
	private ComponentManager componentManager;
	private RenderManager renderManager;
	
	public PawStepEngine() {
		
		this.componentManager = new ComponentManager();
		this.renderManager = new RenderManager();
		
	}
	
	public ComponentManager getComponentManager() {
		return this.componentManager;
	}
	
	public RenderManager getRenderManager() {
		return this.renderManager;
	}
	
	public static PawStepEngine getEngine() {
		return engine;
	}
	
}
