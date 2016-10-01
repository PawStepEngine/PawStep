package net.pawstep.engine.hierarchy;

import org.lwjgl.util.vector.Quaternion;
import org.lwjgl.util.vector.Vector3f;

import net.pawstep.engine.Templates;

public final class Transform {
	
	public Vector3f position;
	public Quaternion rotation;
	public Vector3f scaling;
	
	public Transform() {
		this(Templates.v3f_zero);
	}
	
	public Transform(Vector3f pos) {
		this(pos, Templates.quatIdentity);
	}
	
	public Transform(Vector3f pos, Quaternion rot) {
		this(pos, rot, Templates.v3f_one);
	}
	
	public Transform(Vector3f pos, Quaternion rot, Vector3f scale) {
		
		this.position = pos;
		this.rotation = rot;
		this.scaling = scale;
		
	}
	
}
