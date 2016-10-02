package net.pawstep.engine.render;

import java.nio.FloatBuffer;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

import net.pawstep.engine.hierarchy.Component;
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
		
		Matrix4f liveMatrix = this.getCameraToWorldMatrix();
		FloatBuffer fb = FloatBuffer.allocate(16);
		
		// Push the matrix.
		GL11.glPushMatrix();
		
		// Cam -> World, convert our coordinates into world space.
		liveMatrix.store(fb);
		GL11.glMultMatrix(fb);
		
		this.getScene().forEachChild(e -> {
			
			/*
			 * FIXME Change the depth-first thing to actually recursively push the matrix stack.
			 * FIXME Change the depth-first thing to actually recursively push the matrix stack.
			 * FIXME Change the depth-first thing to actually recursively push the matrix stack.
			 * FIXME You're an idiot, Trey.
			 */
			
			// Transform the the coordinates of the model.
			e.getTransform().applyToTransformationMatrix(liveMatrix);
			liveMatrix.store(fb);
			GL11.glPushMatrix();
			GL11.glMultMatrix(fb);
			
			// Render the thing.
			List<Component> comps = e.getComponents();
			comps.forEach(c -> c.onPreRenderObject());
			// TODO Actually render it, somehow.
			comps.forEach(c -> c.onPostRenderObject());
			
			// Go back to the child things.
			GL11.glPopMatrix();
			
		});
		
		// Go back into camera space.
		GL11.glPopMatrix();
		
	}
	
	private Matrix4f getCameraToWorldMatrix() {
		
		Matrix4f mat = new Matrix4f();
		
		this.getScene().forEachChild(e -> {
			Matrix4f.mul(mat, e.getTransform().getTransformationMatrix(), mat);
		});
		
		return (Matrix4f) mat.invert(); // Why casting?
		
	}
	
	private Scene getScene() {
		return this.getMainCamera().getScene();
	}
	
}
