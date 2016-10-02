package net.pawstep.engine.render;

import net.pawstep.engine.hierarchy.Scene;

public class RenderManager {
	
	private Camera mainCamera;
	
	public RenderManager() {
		
	}
	
	/**
	 * Gets the main camera component.
	 * 
	 * @return The main camera.
	 */
	public Camera getMainCamera() {
		return this.mainCamera;
	}
	
	/**
	 * Sets the main camera component.
	 * 
	 * @param cam The main camera.
	 */
	protected void setMainCamera(Camera cam) {
		this.mainCamera = cam;
	}
	
	/**
	 * Renders the scene.
	 */
	public void renderScene() {
		// TODO
	}
	
	private Scene getScene() {
		return this.getMainCamera().getScene();
	}
	
}
