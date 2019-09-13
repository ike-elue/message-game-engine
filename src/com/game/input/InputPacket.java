/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.input;

import com.game.core.DataPacket;
import java.util.Arrays;

/**
 *
 * @author 17737
 */
public class InputPacket implements DataPacket {

    private final boolean keydown,
            keyup,
            keypressed,
            mousedown,
            mouseup,
            mousepressed,
            scroll,
            cursorpos;

    private Float[] info;
    private final InputEngine iengine;

    public InputPacket(String inputType, InputEngine iengine) {

        this.iengine = iengine;

        info = null;

        keydown = inputType.equalsIgnoreCase("keydown");
        keyup = inputType.equalsIgnoreCase("keyup");
        keypressed = inputType.equalsIgnoreCase("keypressed");

        info = keydown ? iengine.getKeyDowns() : info;
        info = keyup ? iengine.getKeyUps() : info;
        info = keypressed ? iengine.getKeyPresses() : info;

        mousedown = inputType.equalsIgnoreCase("mousedown");
        mouseup = inputType.equalsIgnoreCase("mouseup");
        mousepressed = inputType.equalsIgnoreCase("mousepressed");

        info = mousedown ? iengine.getMouseButtonDowns() : info;
        info = mouseup ? iengine.getMouseButtonUps() : info;
        info = mousepressed ? iengine.getMouseButtonPresses() : info;

        scroll = inputType.equalsIgnoreCase("scroll");
        
        info = scroll ? iengine.getScrollOffsets() : info;

        cursorpos = inputType.equalsIgnoreCase("cursorpos");
        
        info = cursorpos ? iengine.getCursorPosition() : info;
    }

    @Override
    public void updateSelf(DataPacket dp) {
        info = keydown ? iengine.getKeyDowns() : info;
        info = keyup ? iengine.getKeyUps() : info;
        info = keypressed ? iengine.getKeyPresses() : info;
        info = mousedown ? iengine.getMouseButtonDowns() : info;
        info = mouseup ? iengine.getMouseButtonUps() : info;
        info = mousepressed ? iengine.getMouseButtonPresses() : info;
        info = scroll ? iengine.getScrollOffsets() : info;
        info = cursorpos ? iengine.getCursorPosition() : info;
    }

    @Override
    public void onRepeat(int totalResendCounts, int resendCountsLeft) {
    }
    
    @Override
    public Object get() {
        return info;
    }

    @Override
    public String toString() {
        if (keydown) {
            return "Keycodes that are in state key down: " + Arrays.toString(info);
        } else if (keyup) {
            return "Keycodes that are in state key up: " + Arrays.toString(info);
        } else if (keypressed) {
            return "Keycodes that are in state key pressed: " + Arrays.toString(info);
        } else if (mousedown) {
            return "Mouse codes that are in state mouse button up: " + Arrays.toString(info);
        } else if (mouseup) {
            return "Mouse codes that are in state mouse button up: " + Arrays.toString(info);
        } else if (mousepressed) {
            return "Mouse codes that are in state mouse button pressed: " + Arrays.toString(info);
        } else if (scroll) {
            return "Scroll Offsets [x,y] respectively" + Arrays.toString(info);
        } else if (cursorpos) {
            return "Cursor Position [x,y] respectively " + Arrays.toString(info);
        }
        return null;
    }

}
