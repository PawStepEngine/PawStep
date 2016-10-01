package net.pawstep.engine.render;

import net.pawstep.engine.Component;
import net.pawstep.engine.PawStepEngine;
import net.pawstep.engine.components.ComponentType;

@ComponentType(name = "camera")
public class Camera extends Component {
	
	private RenderManager renderManager;
	
	@Override
	public void awake() {
		this.renderManager = PawStepEngine.getEngine().getRenderManager(); // FIXME Encapsulation.
	}

	@Override
	public void start() {
		this.renderManager.setMainCamera(this);
	}
	
}
