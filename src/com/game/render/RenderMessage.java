/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.render;

import com.game.core.Message;

/**
 *
 * @author 17737
 */
public class RenderMessage extends Message {

    public RenderMessage(int senderID, RenderPacket rp) {
        super(senderID, "render_" + rp.name(), rp, null);
    }
}
