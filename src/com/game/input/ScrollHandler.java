/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.input;

import org.lwjgl.glfw.GLFWScrollCallback;

/**
 *
 * @author 17737
 */
public class ScrollHandler extends GLFWScrollCallback{

    private final Float[] scrollOffsets;
    
    public ScrollHandler() {
        scrollOffsets = new Float[2];
    }
    
    @Override
    public void invoke(long window, double xOffset, double yOffset) {
        scrollOffsets[0] = (float)xOffset;
        scrollOffsets[1] = (float)yOffset;
    }
    
    public Float[] getScrollOffsets() {
        return scrollOffsets;
    }
}
