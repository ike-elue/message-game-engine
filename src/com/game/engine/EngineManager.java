/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.engine;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 17737
 */
public class EngineManager {

    private final List<Engine> engines;
    private final List<RenderingEngine> renderEngines;
    
    private int engineCounter;

    public EngineManager() {
        engineCounter = 0;
        engines = new ArrayList<>();
        renderEngines = new ArrayList<>();
    }

    public final void init() {
        engines.stream().forEach((e) -> {
            e.init();
        });
    }

    public final void render() {
        renderEngines.stream().forEach((e) -> {
            e.renderAll();
        });
    }
    
    public final void postUpdate() {
        engines.stream().forEach((e) -> {
            e.postUpdate();
        });
    }
    
    public final void addEngine(Engine engine) {
        engine.setId(engineCounter);
        engineCounter++;
        engines.add(engine);
        if(engine instanceof RenderingEngine) {
            renderEngines.add((RenderingEngine)engine);
        }
    }

    public final Engine getEngine(int id) {
        for (Engine e : engines) {
            if (e.getId() == id)
                return e;
        }

        return null;
    }
    
    public final Engine findEngine(String s) {
        for (Engine e : engines) {
            if (e.getTag().equalsIgnoreCase(s))
                return e;
        }

        return null;
    }
    
    public final int getEngineID(String tag) {
        for (Engine e : engines) {
            if (e.getTag().equalsIgnoreCase(tag))
                return e.getId();
        }
        return -1;
    }

    public final void removeEngine(String s) {
        Engine ee = null;
        for (Engine e : engines) {
            if (e.getTag().equalsIgnoreCase(s)) {
                ee = e;
            }
        }

        if (ee == null) {
            return;
        }

        engines.remove(ee);
    }
    
    public final void disposeEngines() {
        engines.stream().forEach((e) -> {
            e.cleanUp();
        });
    }

    public final List<Engine> getEngines() {
        return engines;
    }
}
