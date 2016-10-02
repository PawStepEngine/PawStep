package net.pawstep.engine.loop;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import net.pawstep.engine.hierarchy.Component;
import net.pawstep.engine.hierarchy.SceneManager;
import net.pawstep.engine.render.OglDisplay;
import net.pawstep.engine.render.RenderManager;

public class LoopManager {
	
	private SceneManager sceneManager;
	private RenderManager renderManager;
	private OglDisplay display;
	
	private List<Runnable> postFrameActions = new ArrayList<>();
	
	private volatile boolean terminateRequested;
	
	public LoopManager(SceneManager sMan, RenderManager rMan, OglDisplay disp) {
		
		this.sceneManager = sMan;
		this.renderManager = rMan;
		
		this.display = disp;
		
	}
	
	public void startLoop() {
		
		this.display.init();
		
		while (!this.terminateRequested || this.display.isCloseRequested()) {
			
			this.gameStep();
			
		}
		
		this.display.close();
		
	}
	
	private void gameStep() {
		
		// First step.
		this.forEachComponent(c -> {
			if (c.isEnabled()) c.update();
		});
		
		// Prerenders.
		this.forEachComponent(c -> c.onPreRender());
		this.renderManager.renderScene();
		this.forEachComponent(c -> c.onPostRender());
		
		// Last step.
		this.forEachComponent(c -> {
			if (c.isEnabled()) c.lateUpdate();
		});
		
		this.display.update();

	}
	
	private void forEachComponent(Consumer<Component> callback) {
		this.sceneManager.getActiveScene().forEachComponent(callback);
	}
	
	/**
	 * Terminates the game loop at the end of the next frame.
	 */
	public void terminate() {
		this.terminateRequested = true;
	}
	
	/**
	 * Queues the supplied callback to run at the end of the current tick cycle.
	 * 
	 * @param r The callback.
	 */
	public void queuePostFrameAction(Runnable r) {
		this.postFrameActions.add(r);
	}
	
}
