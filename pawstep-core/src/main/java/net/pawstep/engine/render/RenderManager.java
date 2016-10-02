package net.pawstep.engine.render;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Matrix4f;

import net.pawstep.engine.PawStepEngine;
import net.pawstep.engine.hierarchy.Entity;
import net.pawstep.engine.hierarchy.Scene;

public class RenderManager {
	
	private Camera mainCamera;
	
	private transient FloatBuffer liveBuffer;
	
	public RenderManager() {
		this.liveBuffer = BufferUtils.createFloatBuffer(32);
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
		
		if (this.getMainCamera() == null) {
			
			PawStepEngine.getLogger().warning("No camera is registered as main!");
			
			try {
				
				// So it's obvious something's wrong!
				Thread.sleep(250L);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			return;
			
		}
		
		// Sadly can't really find a way to make this reusable across calls.
		Matrix4f liveMatrix = this.getCameraToWorldMatrix();
		
		// Setup OpenGL.
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		
		// Set up projection stuff.
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GLU.gluPerspective(75F, (640F / 480F), 0.01F, 50F);
		
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		liveMatrix.store(this.liveBuffer);
		this.liveBuffer.rewind();
		GL11.glMultMatrix(this.liveBuffer);
		
		GL11.glColor3f(1F, 1F, 1F);
		
		// Push the matrix.
		GL11.glPushMatrix();
		
		// Now render each of the objects.
		this.getScene().getChildren().forEach(e -> renderEntity(liveMatrix, e));
		
		// Go back into camera space.
		GL11.glPopMatrix();
		
	}
	
	private void renderEntity(Matrix4f matrix, Entity ent) {
		
		GL11.glPushMatrix();
		
		// Apply transformation.
		ent.getTransform().applyToTransformationMatrix(matrix);
		matrix.store(this.liveBuffer);
		this.liveBuffer.rewind();
		GL11.glMultMatrix(this.liveBuffer);
		
		// Now do the children.
		ent.getChildren().forEach(e -> renderEntity(matrix, e));
		
		// Now actually draw it.
		ent.getComponents().forEach(c -> {
			if (c instanceof Renderer) ((Renderer) c).draw(); // Probably bad performance.
		});
		
		// Restore matrix.
		GL11.glPopMatrix();
		
	}
	
	private Matrix4f getCameraToWorldMatrix() {
		
		Matrix4f mat = new Matrix4f();
		Entity ent = this.getMainCamera().getEntity();
		
		while (ent != null) {
			
			Matrix4f.mul((Matrix4f) ent.getTransform().getTransformationMatrix(), mat, mat);
			ent = ent.getParentEntity();
			
		}
		
		return mat;
		
	}
	
	private Scene getScene() {
		return this.getMainCamera().getScene();
	}
	
}
