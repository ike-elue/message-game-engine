/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.input;

import java.util.ArrayList;
import java.util.List;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import org.lwjgl.glfw.GLFWKeyCallback;

/**
 *
 * @author 17737
 */
public class KeyHandler extends GLFWKeyCallback {
    
    public float[] keys, keysLast;
    
    public KeyHandler() {
        keys = new float[15000];
        keysLast = new float[15000];
    }
    
    @Override
    public void invoke(long window, int key, int scancode, int action, int mods) {
        keysLast = keys.clone();
        keys[key] = (action != GLFW_RELEASE) ? 1f : 0f;
    }
    
    public void postUpdate() {
        keysLast = keys.clone();
    }
    
    public Float[] getKeyDowns() {
        List<Float> list = new ArrayList<>();
        for(int i = 0; i < keys.length; i++) {
            if(keys[i] == 1)
                list.add((float)i);
        }
        return (Float[]) list.toArray(new Float[list.size()]);
    }
    
    public Float[] getKeyUps() {
        List<Float> list = new ArrayList<>();
        for(int i = 0; i < keys.length; i++) {
            if(keysLast[i] == 1 && keys[i] == 0)
                list.add((float)i);
        }
        return (Float[]) list.toArray(new Float[list.size()]);
    }
    
    public Float[] getKeyPresses() {
        List<Float> list = new ArrayList<>();
        for(int i = 0; i < keys.length; i++) {
            if(keys[i] == 1 && keysLast[i] == 0)
                list.add((float)i);
        }
        return (Float[]) list.toArray(new Float[list.size()]);
    }
}
