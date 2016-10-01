package net.pawstep.engine.render;

public class RenderManager {
	
	private Camera mainCamera;
	
	public RenderManager() {
		
	}
	
	public Camera getMainCamera() {
		return this.mainCamera;
	}
	
	protected void setMainCamera(Camera cam) {
		this.mainCamera = cam;
	}
	
}
