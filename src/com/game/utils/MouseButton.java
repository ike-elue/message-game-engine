/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.utils;

import org.lwjgl.glfw.GLFW;

/**
 *
 * @author 17737
 */
public enum MouseButton {
    GLFW_RBUTTON(GLFW.GLFW_MOUSE_BUTTON_RIGHT),
    GLFW_LBUTTON(GLFW.GLFW_MOUSE_BUTTON_LEFT),
    GLFW_MBUTTON(GLFW.GLFW_MOUSE_BUTTON_MIDDLE);
	
    private final int buttonCode;
	
    private MouseButton(int buttonCode) {
	this.buttonCode = buttonCode;
    }

    public int getButtonCode() {
        return buttonCode;
    }
}
