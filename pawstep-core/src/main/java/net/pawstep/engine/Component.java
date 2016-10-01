package net.pawstep.engine;

/**
 * A unit of behavior logic for controlling entities.
 * 
 * @author treyzania
 */
public class Component {
	
	protected transient Entity entity;
	private boolean enabled;
	
	/**
	 * Sets the activation state of this component.  If it is set to false it
	 * will no longer receive the majority of event calls to it.
	 * 
	 * @param state The state to set.
	 */
	public void setState(boolean state) {
		this.enabled = state;
	}
	
	/**
	 * @return The activation state of the component.
	 */
	public boolean getState() {
		return this.enabled;
	}
	
}
