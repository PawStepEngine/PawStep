package net.pawstep.engine.render;

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
	
}
