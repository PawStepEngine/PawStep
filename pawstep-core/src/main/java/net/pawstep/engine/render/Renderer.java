package net.pawstep.engine.render;

import net.pawstep.engine.hierarchy.Component;

public abstract class Renderer extends Component {
	
	/**
	 * Called during rendering for the Entity.
	 */
	public abstract void draw();
	
}
