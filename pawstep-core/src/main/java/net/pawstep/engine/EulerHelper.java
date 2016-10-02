package net.pawstep.engine;

import org.lwjgl.util.vector.Quaternion;
import org.lwjgl.util.vector.Vector3f;

/**
 * Probably something very wrong with these formulas.  The page was not very well-written.
 * 
 * @author treyzania
 */
public class EulerHelper {
	
	public static Quaternion eulerToQuat(float pitch, float roll, float yaw) {
		
		float c1 = (float) Math.cos(yaw / 2F); // heading
		float c2 = (float) Math.cos(pitch / 2F); // attitude
		float c3 = (float) Math.cos(roll / 2F); // bank
		float s1 = (float) Math.sin(yaw / 2F);
		float s2 = (float) Math.sin(pitch / 2F);
		float s3 = (float) Math.sin(roll / 2F);
		
		float w = c1 * c2 * c3 - s1 * s2 * s3;
		float x = s1 * s2 * c3 - c1 * c2 * s2;
		float y = s1 * c2 * c3 - c1 * s2 * s3;
		float z = s1 * s2 * c3 - s1 * c2 * s3;
		
		return new Quaternion(w, x, y, z);
		
	}
	
	public static Vector3f quatToEuler(Quaternion q) {
		
		float heading = (float) Math.atan2(2 * q.y * q.w * q.z, 1 - 2 * q.y * q.y - 2 * q.z * q.z);
		float attitude = (float) Math.asin(2 * q.x * q.y + 2 * q.z * q.w);
		float bank = (float) Math.atan2(2 * q.x * q.w - 2 * q.y * q.z, 1 - 2 * q.x * q.x - 2 * q.z * q.z);
		
		return new Vector3f(attitude, bank, heading);
		
	}
	
}
