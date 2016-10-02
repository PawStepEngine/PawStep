package net.pawstep.engine.hierarchy;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Quaternion;
import org.lwjgl.util.vector.Vector3f;

import net.pawstep.engine.Templates;

public final class Transform {
	
	public Vector3f position;
	public Quaternion rotation;
	public Vector3f scaling;
	
	public Transform() {
		this(Templates.v3f_zero());
	}
	
	public Transform(Vector3f pos) {
		this(pos, Templates.quatIdentity());
	}
	
	public Transform(Vector3f pos, Quaternion rot) {
		this(pos, rot, Templates.v3f_one());
	}
	
	public Transform(Vector3f pos, Quaternion rot, Vector3f scale) {
		
		this.position = pos;
		this.rotation = rot;
		this.scaling = scale;
		
	}
	
	/**
	 * Creates a transformation matrix that applies this transform's transformations.
	 * 
	 * @return The transformation matrix.
	 */
	public Matrix4f getTransformationMatrix() {
		
		Matrix4f matrix = new Matrix4f();
		this.applyToTransformationMatrix(matrix);
		
		return matrix;
		
	}
	
	/**
	 * Sets the matrix specified to contain the values specified by this transform.
	 * 
	 * @param dest The destination matrix.
	 */
	public void applyToTransformationMatrix(Matrix4f dest) {
		
		// ugly!
		float q00 = 2.0f * this.rotation.x * this.rotation.x;
		float q11 = 2.0f * this.rotation.y * this.rotation.y;
		float q22 = 2.0f * this.rotation.z * this.rotation.z;
		float q01 = 2.0f * this.rotation.x * this.rotation.y;
		float q02 = 2.0f * this.rotation.x * this.rotation.z;
		float q03 = 2.0f * this.rotation.x * this.rotation.w;
		float q12 = 2.0f * this.rotation.y * this.rotation.z;
		float q13 = 2.0f * this.rotation.y * this.rotation.w;
		float q23 = 2.0f * this.rotation.z * this.rotation.w;
		
		// This goes down the columns then across the rows.
		// ugly!
		dest.m00 = (1.0f - q11 - q22) * this.scaling.x;
		dest.m01 = (q01 + q23) * this.scaling.x;
		dest.m02 = (q02 - q13) * this.scaling.x;
		dest.m03 = 0.0f;
		dest.m10 = (q01 - q23) * this.scaling.y;
		dest.m11 = (1.0f - q22 - q00) * this.scaling.y;
		dest.m12 = (q12 + q03) * this.scaling.y;
		dest.m13 = 0.0f;
		dest.m20 = (q02 + q13) * this.scaling.z;
		dest.m21 = (q12 - q03) * this.scaling.z;
		dest.m22 = (1.0f - q11 - q00) * this.scaling.z;
		dest.m23 = 0.0f;
		dest.m30 = this.position.x;
		dest.m31 = this.position.y;
		dest.m32 = this.position.z;
		dest.m33 = 1.0f;
		
	}
	
	public static Matrix4f convertQuatToMatrix4f(Quaternion q) {
		
		Matrix4f matrix = new Matrix4f();
		
		// ugly!
		matrix.m00 = 1.0f - 2.0f * ( q.getY() * q.getY() + q.getZ() * q.getZ() );
		matrix.m01 = 2.0f * (q.getX() * q.getY() + q.getZ() * q.getW());
		matrix.m02 = 2.0f * (q.getX() * q.getZ() - q.getY() * q.getW());
		matrix.m03 = 0.0f;

		// ugly!
		matrix.m10 = 2.0f * ( q.getX() * q.getY() - q.getZ() * q.getW() );
		matrix.m11 = 1.0f - 2.0f * ( q.getX() * q.getX() + q.getZ() * q.getZ() );
		matrix.m12 = 2.0f * (q.getZ() * q.getY() + q.getX() * q.getW() );
		matrix.m13 = 0.0f;
		
		// ugly!
		matrix.m20 = 2.0f * ( q.getX() * q.getZ() + q.getY() * q.getW() );
		matrix.m21 = 2.0f * ( q.getY() * q.getZ() - q.getX() * q.getW() );
		matrix.m22 = 1.0f - 2.0f * ( q.getX() * q.getX() + q.getY() * q.getY() );
		matrix.m23 = 0.0f;
		
		// ugly?
		matrix.m30 = 0;
		matrix.m31 = 0;
		matrix.m32 = 0;
		matrix.m33 = 1.0f;
		
		return matrix;
		
	}
	
}
