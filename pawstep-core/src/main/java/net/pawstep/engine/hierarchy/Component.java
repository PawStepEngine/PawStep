package net.pawstep.engine.hierarchy;

import net.pawstep.engine.components.ComponentType;
import net.pawstep.engine.loop.LoopManager;

/**
 * A unit of behavior logic for controlling entities.
 * 
 * @author treyzania
 */
public class Component {
	
	protected transient Entity entity;
	private boolean enabled = true;
	
	/**
	 * Sets the activation state of this component.  This only applied in the
	 * next frame.  If it is set to false it will no longer receive the
	 * majority of event calls to it.
	 * 
	 * @param state The state to set.
	 */
	public void setState(boolean state) {
		
		LoopManager lm = this.getScene().getManager().getLoopManager();
		lm.queuePostFrameAction(() -> {

			// Call the relevant event functions.
			if (!this.enabled && state) this.onEnable();
			if (this.enabled && !state) this.onDisable();
			
			// Then actually set it.
			this.setStateImmediate(state);
			
		});
		
	}
	
	/**
	 * Sets the activation state of this component immediately.  Does not call
	 * any event handlers and may adversely effect the logic of other
	 * components down the line.
	 * 
	 * @param state The state to set.
	 */
	public void setStateImmediate(boolean state) {
		this.enabled = state;
	}
	
	/**
	 * @return The activation state of the component.
	 */
	public boolean getState() {
		return this.enabled;
	}
	
	/**
	 * Gets the simple name for this component class, based on the component type.
	 * 
	 * @return The name.
	 */
	public final String getName() {
		
		ComponentType typeAnno = this.getClass().getDeclaredAnnotation(ComponentType.class);
		return typeAnno.name();
		
	}
	
	/**
	 * Gets the entity that this component is bound to.
	 * 
	 * @return This component's entity.
	 */
	public Entity getEntity() {
		return this.entity;
	}
	
	/**
	 * Get the scene that this component is ultimately in.
	 * 
	 * @return This component's scene.
	 */
	public Scene getScene() {
		return this.getEntity().getScene();
	}
	
	/**
	 * Gets the activation state of this component.
	 * 
	 * @return The activation state.
	 */
	public boolean isEnabled() {
		return this.enabled;
	}
	
	/**
	 * Gets the transform for the object that this component is attached onto.
	 * 
	 * @return The transform.
	 */
	public Transform getTransform() {
		return this.getEntity().getTransform();
	}
	
	/*
	 * ================================================================
	 * B'YOND HERE LIES THEE FETID CONGLOMERATE OF FALLOW EVENT METHODS
	 * ================================================================
	 */
	
	/**
	 * Called when the component is constructed.
	 */
	public void awake() {
		
	}
	
	/**
	 * Called when the component starts for the first time.
	 */
	public void start() {
		
	}
	
	/**
	 * Called once every frame at the beginning of the frame as long as the
	 * component is enabled.
	 */
	public void update() {
		
	}
	
	/**
	 * Called once every frame at the beginning of the frame as long as the
	 * component is enabled.
	 */
	public void lateUpdate() {
		
	}
	
	/**
	 * Called when the component state goes from disabled to enabled.
	 */
	public void onEnable() {
		
	}
	
	/**
	 * Called when the component state goes from enabled to disabled.
	 */
	public void onDisable() {
		
	}
	
	/**
	 * Called when the entity that this component is on is destroyed.
	 */
	public void onDestroy() {
		
	}
	
	/**
	 * Called when the component is removed from an entity.
	 */
	public void onRemove() {
		
	}
	
	/**
	 * Called before the scene is rendered.
	 */
	public void onPreRender() {
		
	}
	
	/**
	 * Called after the scene is rendered.
	 */
	public void onPostRender() {
		
	}
	
	/**
	 * Called before the entity the component is on is rendered.
	 */
	public void onPreRenderObject() {
		
	}
	
	/**
	 * Called after the entity the component is on is rendered.
	 */
	public void onPostRenderObject() {
		
	}
	
}
