package net.pawstep.engine.hierarchy;

import net.pawstep.engine.loop.LoopManager;

public class SceneManager {
	
	private Scene activeScene;
	private LoopManager loopManager;
	
	public SceneManager() {
		
	}
	
	public void activateScene() {
		
		this.activeScene.forEachComponent(c -> {
			
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
