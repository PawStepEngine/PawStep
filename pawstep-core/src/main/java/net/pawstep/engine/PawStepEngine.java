package net.pawstep.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import net.pawstep.engine.components.ComponentManager;
import net.pawstep.engine.hierarchy.Component;
import net.pawstep.engine.hierarchy.SceneManager;
import net.pawstep.engine.loop.LoopManager;
import net.pawstep.engine.render.OglDisplay;
import net.pawstep.engine.render.RenderManager;

public class PawStepEngine {
	
	protected static PawStepEngine engine;
	protected static Logger logger = Logger.getLogger("PawStep");
	private static List<Class<? extends Component>> queuedComponentRegistrations = new ArrayList<>();
	
	private ComponentManager componentManager;
	private OglDisplay display;
	
	private SceneManager sceneManager;
	private RenderManager renderManager;
	private LoopManager loopManager;
	
	public PawStepEngine(EngineConfig cfg) {
		
		this.componentManager = new ComponentManager();
		this.display = new OglDisplay(cfg.getWindowWidth(), cfg.getWindowHeight());
		
		// Set up managers.
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
		queuedComponentRegistrations.forEach(c -> engine.getComponentManager().registerComponentType(c));
		
		// Set up the initial scene.
		cfg.getSceneProvider().populateInitialScene(engine.getSceneManager().getActiveScene());
		
		return getEngine();
		
	}
	
	public static void registerComponentType(Class<? extends Component> clazz) {
		queuedComponentRegistrations.add(clazz);
	}
	
	/**
	 * Gets the global engine logger.
	 * 
	 * @return The logger.
	 */
	public static Logger getLogger() {
		return logger;
	}
	
}
