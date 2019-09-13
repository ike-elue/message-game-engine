/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.debug;

import com.game.core.Core;
import com.game.core.DataPacket;
import com.game.core.Message;
import com.game.engine.Engine;
import com.game.engine.EngineManager;

/**
 *
 * @author 17737
 */
public class Console extends Engine{
    private DebugWindow dwindow;
    
    private final boolean isDebug;
    
    public Console(Core core, boolean isDebug, EngineManager em) {
        super("console", core, null, em);
        this.isDebug = isDebug;
    }

    @Override
    public void init() {
        if(isDebug) {
            dwindow = new DebugWindow(this, isDebug);
            this.sendMessage(new Message(getId(), "updater", new DataPacket() {
                public void updateSelf(DataPacket dp){}
    
                public void onRepeat(int i1, int i2) {}
                
                @Override
                public Object get() {
                    return null;
                }
                
                @Override
                public String toString(){
                    return "Nothing to see here";
                }
            }, null));
            this.sendMessage(new LogMessage(getId(), "console", 1));
        }
    }

    @Override
    public void update(Message m, double delta) {
        if(isDebug && m.getMessageTag().equalsIgnoreCase("updater")) {
            if(dwindow.dispose(true))
                setRunning(false);
            dwindow.setFPS(getCore().getFPSString());
            sendMessage(m);
        }
    }

    private void iterateConsole(String messages) {
        dwindow.setData(messages.split(";"));
    }
    
    @Override
    public void dispose() {
        if(isDebug)
            dwindow.dispose(false);
    }

    public DebugWindow getDwindow() {
        return dwindow;
    }

    @Override
    public void postUpdate() {
        iterateConsole(getCore().getMessageBus().toString());
    }
}
