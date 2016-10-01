package net.pawstep.engine;

import org.lwjgl.util.vector.Quaternion;
import org.lwjgl.util.vector.Vector3f;

public class Templates {
	
	public static final Vector3f v3f_one() {
		return new Vector3f(1, 1, 1);
	}
	
	public static final Vector3f v3f_zero() {
		return new Vector3f(0, 0, 0);
	}
	
	public static final Quaternion quatIdentity() {
		return new Quaternion(0, 0, 0, 1);
	}
	
}
