package net.pawstep.engine.render;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class OglDisplay {
	
	private final int width, height;
	
	public OglDisplay(int w, int h) {
		
		this.width = w;
		this.height = h;
		
	}
	
	public void init() {
		
		try {
			
			Display.setDisplayMode(new DisplayMode(this.width, this.height));
			Display.create();
			
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void update() {
		Display.update();
	}
	
	public void close() {
		Display.destroy();
	}
	
}
