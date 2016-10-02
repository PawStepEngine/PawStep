package net.pawstep.engine.hierarchy;

import java.util.logging.Logger;

import net.pawstep.engine.PawStepEngine;
import net.pawstep.engine.loop.LoopManager;

public class SceneManager {
	
	private Scene activeScene;
	private LoopManager loopManager;
	
	public SceneManager() {
		this.activeScene = new Scene(this);
	}
	
	public void activateScene() {
		
		Logger log = PawStepEngine.getLogger();
		log.info("Starting components... (" + this.activeScene.getChildren().size() + ")");
		
		this.activeScene.forEachComponent(c -> {
			
			PawStepEngine.getLogger().info("Starting " + c.getName() + " on " + c.getName() + "...");
			c.start();
			
		});
		
	}
	
	public void setLoopManager(LoopManager manager) {
		this.loopManager = manager;
	}
	
	public LoopManager getLoopManager() {
		return this.loopManager;
	}
	
	/**
	 * Gets the manager's currently active scene.
	 * 
	 * @return The active scene.
	 */
	public Scene getActiveScene() {
		return this.activeScene;
	}
	
}
