/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.input;

import org.lwjgl.glfw.GLFWCursorPosCallback;

/**
 *
 * @author 17737
 */
public class CursorPosHandler extends GLFWCursorPosCallback {

	private final Float[] mousepos;

        public CursorPosHandler() {
            mousepos = new Float[2];
        }
        
	@Override
	public void invoke(long window, double xpos, double ypos) {
		mousepos[0] = (float)xpos;
		mousepos[1] = (float)ypos;
	}

	public Float[] getCursorPos() {
		return mousepos;
        }
    
}
