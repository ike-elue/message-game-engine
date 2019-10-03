/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.core;

import com.game.engine.EngineManager;
import org.lwjgl.opengl.GL;

/**
 *
 * @author 17737
 */
public class MessageExecutor implements Runnable {

    private volatile Message currentMessage;
    private double delta;
    private final MessageBus mb;
    private final EngineManager em;
    private final int id;
    
    public MessageExecutor(MessageBus mb, EngineManager em, int id) {
        this.mb = mb;
        delta = 0;
        this.em = em;
        this.id = id;
    }

    public void setMessage(Message message) {
        this.currentMessage = message;
    }

    public Message getMessage() {
        return currentMessage;
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }

    @Override
    public void run() {
        currentMessage = mb.receiveMessage();
        if (currentMessage != null) {
            //System.out.println(String.format("Running %s on %s", currentMessage.getMessageTag(), id));
            //System.out.println(currentMessage);
            em.getEngines().forEach((e) -> {
                e.updateWithCurrentMessage(currentMessage, delta);
            });
            if (!currentMessage.shouldDeactivate()) {
                currentMessage.onRepeat();
                mb.addMessage(currentMessage);
            }
        }
        if (!mb.isEmpty()) {
            run();
        }
        currentMessage = null;
    }

}
