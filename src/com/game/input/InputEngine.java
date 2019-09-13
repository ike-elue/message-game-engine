/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.input;

import com.game.core.Core;
import com.game.core.Message;
import com.game.engine.Engine;
import com.game.engine.EngineManager;
import static org.lwjgl.glfw.GLFW.glfwSetCursorPosCallback;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetMouseButtonCallback;
import static org.lwjgl.glfw.GLFW.glfwSetScrollCallback;

/**
 *
 * @author 17737
 */
public class InputEngine extends Engine {

    private final KeyHandler keyHandler;
    private final MouseButtonHandler mouseButtonHandler;
    private final ScrollHandler scrollHandler;
    private final CursorPosHandler cursorPosHandler;
    
    public InputEngine(Core core, EngineManager em) {
        super("input_engine", core, null, em);
        keyHandler = new KeyHandler();
        mouseButtonHandler = new MouseButtonHandler();
        scrollHandler = new ScrollHandler();
        cursorPosHandler = new CursorPosHandler();
    }
    
    @Override
    public void init() {
        glfwSetKeyCallback(getCore().getWindow(), keyHandler);
        glfwSetMouseButtonCallback(getCore().getWindow(), mouseButtonHandler);
        glfwSetScrollCallback(getCore().getWindow(), scrollHandler);
        glfwSetCursorPosCallback(getCore().getWindow(),cursorPosHandler);
        
        this.sendMessage(new InputMessage(getId(), "keydown", null, this));
        this.sendMessage(new InputMessage(getId(), "keyup", null, this));
        this.sendMessage(new InputMessage(getId(), "keypressed", null, this));
        this.sendMessage(new InputMessage(getId(), "mousedown", null, this));
        this.sendMessage(new InputMessage(getId(), "mouseup", null, this));
        this.sendMessage(new InputMessage(getId(), "mousepressed", null, this));
        this.sendMessage(new InputMessage(getId(), "scroll", null, this));
        this.sendMessage(new InputMessage(getId(), "cursorpos", null, this));
    }

    @Override
    public void update(Message m, double delta) {
        this.sendMessage(m, null);
    }

    @Override
    public void dispose() {
    
    }
    
    public Float[] getKeyDowns() {
        return keyHandler.getKeyDowns();
    }
    
    public Float[] getKeyUps() {
        return keyHandler.getKeyUps();
    }
    
    public Float[] getKeyPresses() {
        return keyHandler.getKeyPresses();
    }
    
    public Float[] getMouseButtonDowns() {
        return mouseButtonHandler.getMouseButtonDowns();
    }
    
    public Float[] getMouseButtonUps() {
        return mouseButtonHandler.getMouseButtonUps();
    }
    
    public Float[] getMouseButtonPresses() {
        return mouseButtonHandler.getMouseButtonPresses();
    }
    
    public Float[] getScrollOffsets() {
        return scrollHandler.getScrollOffsets();
    }
    
    public Float[] getCursorPosition() {
        return cursorPosHandler.getCursorPos();
    }
    
    
    @Override
    public void postUpdate() {
        keyHandler.postUpdate();
        mouseButtonHandler.postUpdate();
    }
}
