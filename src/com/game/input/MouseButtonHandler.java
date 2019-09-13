/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.input;

import java.util.ArrayList;
import java.util.List;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

/**
 *
 * @author 17737
 */
public class MouseButtonHandler extends GLFWMouseButtonCallback {

    public float[] buttons, buttonsLast;
    
    public MouseButtonHandler() {
        buttons = new float[16];
        buttonsLast = new float[16];
    }
    
    @Override
    public void invoke(long window, int button, int action, int mods) {
        buttonsLast = buttons.clone();
        buttons[button] = (action != GLFW_RELEASE) ? 1f : 0f;
    }

    public void postUpdate() {
        buttonsLast = buttons.clone();
    }

    public Float[] getMouseButtonDowns() {
        List<Float> list = new ArrayList<>();
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i] == 1) {
                list.add((float) i);
            }
        }
        return (Float[]) list.toArray(new Float[list.size()]);
    }

    public Float[] getMouseButtonPresses() {
        List<Float> list = new ArrayList<>();
        for(int i = 0; i < buttons.length; i++) {
            if(buttons[i] == 1 && buttonsLast[i] == 0)
                list.add((float)i);
        }
        return (Float[]) list.toArray(new Float[list.size()]);
    }

    public Float[] getMouseButtonUps() {
        List<Float> list = new ArrayList<>();
        for(int i = 0; i < buttons.length; i++) {
            if(buttonsLast[i] == 1 && buttons[i] == 0)
                list.add((float)i);
        }
        return (Float[]) list.toArray(new Float[list.size()]);
    }

}
