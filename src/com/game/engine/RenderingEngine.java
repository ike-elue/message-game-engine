/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.engine;

import com.game.core.Core;
import com.game.core.Message;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 17737
 */
public abstract class RenderingEngine extends Engine {

    private final List<Message> renderingMessages; 
    
    public RenderingEngine(String tag, Core core, String[] otherWantedEngines, EngineManager em) {
        super(tag, core, otherWantedEngines, em);
        renderingMessages = new ArrayList<>();
    }
    
    public void renderAll() {
        renderingMessages.forEach((m) -> {
            render(m);
        });
        renderingMessages.clear();
    }
    
    public abstract void render(Message m); 
    
    protected void addRenderMessage(Message m) {
        if(m.getMessageTag().contains("render")) {
            renderingMessages.add(m);
        }
    }
    
}
