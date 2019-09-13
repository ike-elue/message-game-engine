/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.scene;

import com.game.core.Core;
import com.game.core.DataPacket;
import com.game.core.Message;
import com.game.engine.Engine;
import com.game.engine.EngineManager;
import com.game.utils.Key;

/**
 *
 * @author 17737
 */
public class SceneEngine extends Engine {

    private String currentScene;
    
    public SceneEngine(Core core, EngineManager em) {
        super("scene_engine", core, new String[]{"all"}, em);
        currentScene = "";
    }
    
    @Override
    public void init() {
        this.sendMessage(new Message(getId(), "current_scene", new DataPacket(){
                @Override
                public void updateSelf(DataPacket dp) {}
    
                @Override
                public void onRepeat(int totalResendCounts, int resendCountsLeft){}
                
                @Override
                public Object get() {
                    return null;
                }
                
                @Override
                public String toString(){
                    return currentScene;
                }
        }, null));
    }

    @Override
    public void update(Message m, double delta) {
        if(m.getMessageTag().equalsIgnoreCase("current_scene")) {
            this.sendMessage(m, new DataPacket() {
                @Override
                public void updateSelf(DataPacket dp) {}
    
                @Override
                public void onRepeat(int totalResendCounts, int resendCountsLeft){}
                
                @Override
                public Object get() {
                    return null;
                }
                
                @Override
                public String toString(){
                    return currentScene;
                }
            });
        } else if(m.getMessageTag().equalsIgnoreCase("change_scene")) {
            currentScene = m.getDataPacket().toString();
        } else if(m.getMessageTag().equalsIgnoreCase("keydown") && contains((Float[])m.getDataPacket().get(), Key.GLFW_Q.getKeycode())) {
            this.sendMessage(new Message(getId(), "change_scene", new DataPacket(){
                @Override
                public void updateSelf(DataPacket dp) {}
    
                @Override
                public void onRepeat(int totalResendCounts, int resendCountsLeft){}
                
                @Override
                public Object get() {
                    return null;
                }
                
                @Override
                public String toString(){
                    return "pause";
                }
        }, null));
        }
        
    }

    private boolean contains(Float[] farray, float value) {
        for(Float f : farray) {
            if(f == value)
                return true;
        }
        return false;
    }
    
    @Override
    public void postUpdate() {}

    @Override
    public void dispose() {}
    
}
