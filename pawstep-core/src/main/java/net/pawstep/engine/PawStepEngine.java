package net.pawstep.engine;

import net.pawstep.engine.components.ComponentManager;
import net.pawstep.engine.hierarchy.SceneManager;
import net.pawstep.engine.loop.LoopManager;
import net.pawstep.engine.render.OglDisplay;
import net.pawstep.engine.render.RenderManager;

public class PawStepEngine {
	
	protected static PawStepEngine engine;
	
	private ComponentManager componentManager;
	private OglDisplay display;
	
	private SceneManager sceneManager;
	private RenderManager renderManager;
	private LoopManager loopManager;
	
	public PawStepEngine(EngineConfig cfg) {
		
		this.componentManager = new ComponentManager();
		this.display = new OglDisplay(cfg.getWindowWidth(), cfg.getWindowHeight());
		
		this.sceneManager = new SceneManager();
		this.renderManager = new RenderManager();
		
		this.loopManager = new LoopManager(this.sceneManager, this.renderManager, this.display);
		
	}
	
	public ComponentManager getComponentManager() {
		return this.componentManager;
	}
	
	public SceneManager getSceneManager() {
		return this.sceneManager;
	}
	
	public RenderManager getRenderManager() {
		return this.renderManager;
	}
	
	public LoopManager getLoopManager() {
		return this.loopManager;
	}
	
	public static PawStepEngine getEngine() {
		return engine;
	}
	
	public static PawStepEngine init(EngineConfig cfg) {
		
		engine = new PawStepEngine(cfg);
		return getEngine();
		
	}
	
}
