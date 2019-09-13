/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.engine;

import com.game.core.Core;
import com.game.core.DataPacket;
import com.game.core.Message;

/**
 *
 * @author 17737
 */
public abstract class Engine {
    private final String tag;
    private int id;
    private final Core core;
    private final String[] otherWantedEngines;
    private final EngineManager em;
    
    public Engine(String tag, Core core, String[] otherWantedEngines, EngineManager em) {
        this.tag = tag;
        this.core = core;
        this.otherWantedEngines = otherWantedEngines;
        this.em = em;
    }
     
    /**
     * Used to tell engine what info to get overall, variable initialization, classifications it has for data, or resource gathering
     */
    public abstract void init();
    
    public void sendMessage(Message newMessage) {
        core.getMessageBus().addMessage(newMessage);
    }
    
    public void sendMessage(Message existingMessage, DataPacket newDP) {
        existingMessage.updateDataPacket(newDP);
        core.getMessageBus().addMessage(existingMessage);
    }
    
    public void updateWithCurrentMessage(Message m, double delta) {
        if(m.getSenderId() == id || onList(m.getSenderId()))
            update(m, delta);
    }
    
    private boolean onList(int id) {
        if(otherWantedEngines == null)
            return false;
        if(otherWantedEngines[0].equalsIgnoreCase("all"))
            return true;
        for(String s : otherWantedEngines) {
            if(em.getEngineID(s) == id)
                return true;
        }
        return false;
    }
    
    public abstract void update(Message m, double delta);

    public abstract void postUpdate();
    
    public abstract void dispose();

    public final void setRunning(boolean running) {
        core.setRunning(running);
    }
    
    private void disposalLine() {
        System.out.println("Cleaned up the " + getTag() + " system!");
    }
    
    public final void cleanUp() {
        dispose();
        disposalLine();
    }

    public final String getTag() {
        return tag;
    }

    public final int getId() {
        return id;
    }

    public final void setId(int id) {
        this.id = id;
    }
    
    public final Core getCore() {
        return core;
    }
}
