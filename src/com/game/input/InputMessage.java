/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.input;

import com.game.core.Message;

/**
 *
 * @author 17737
 */
public class InputMessage extends Message {
    
    public InputMessage(int senderID, String messageTag, Integer resendCount, InputEngine iengine) {
        super(senderID, messageTag, new InputPacket(messageTag, iengine), resendCount);
    }
    
}
