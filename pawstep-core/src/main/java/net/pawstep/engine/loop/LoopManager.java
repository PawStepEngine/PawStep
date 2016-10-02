package net.pawstep.engine.loop;

import java.util.ArrayList;
import java.util.List;

import net.pawstep.engine.hierarchy.SceneManager;
import net.pawstep.engine.render.RenderManager;

public class LoopManager {
	
	private SceneManager sceneManager;
	private RenderManager renderManager;
	
	private List<Runnable> postFrameActions = new ArrayList<>();
	
	public LoopManager(SceneManager sMan, RenderManager rMan) {
		
		this.sceneManager = sMan;
		this.renderManager = rMan;
		
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
