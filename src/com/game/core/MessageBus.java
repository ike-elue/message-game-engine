/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.core;

import com.game.engine.EngineManager;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author 17737
 */
public class MessageBus {
    private final MessageExecutor[] executors;
    
    private final BlockingQueue<Message> messages1, messages2;
    private volatile boolean using1;
    
    public MessageBus(int threadCount, EngineManager em) {
        messages1 = new LinkedBlockingQueue<>();
        messages2 = new LinkedBlockingQueue<>();
        executors = new MessageExecutor[threadCount];
        for(int i = 0; i < threadCount; i++)
            executors[i] = new MessageExecutor(this, em, i);
        using1 = true;
    }
    
    public synchronized boolean isEmpty() {
        return getCurrentMessageList().isEmpty();
    }
    
    public void flipMessages() {
        using1 = !using1;
    }
    
    public synchronized Message receiveMessage() {
        return getCurrentMessageList().poll();
    }
    
    public synchronized void addMessage(Message message) {
       getFutureMessageList().add(message);
    }
    
    public BlockingQueue<Message> getFutureMessageList() {
        if(!using1)
            return messages1;
        return messages2;
    }
    
    public BlockingQueue<Message> getCurrentMessageList() {
        if(using1)
            return messages1;
        return messages2;
    }
    
    public MessageExecutor[] getExecutors() {
        return executors;
    }
    
    @Override
    public String toString() {
        String list = "";
        list = getCurrentMessageList().stream().map((m) -> m.toString() + ";").reduce(list, String::concat);
        return list;
    } 
}
