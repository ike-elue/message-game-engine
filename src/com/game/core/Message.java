/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.core;

/**
 *
 * @author 17737
 */
public class Message {

    private final String messageTag;
    private final DataPacket dp;
    private final int senderID;
    private final Integer resendCount;
    private Integer countsLeft;
    
    public Message(int senderID, String messageTag, DataPacket dp, Integer resendCount) {
        this.messageTag = messageTag;
        this.dp = dp;
        this.senderID = senderID;
        this.resendCount = resendCount;
        countsLeft = resendCount;
    }

    public Message(Message m) {
        this(m.getSenderId(), m.getMessageTag(), m.getDataPacket(), m.getResendCountsLeft());
    }
    
    public boolean shouldDeactivate() {
        if(resendCount != null)
            countsLeft -= 1;
        return resendCount == null || countsLeft == -1;
    }
    
    public void onRepeat() {
        if(dp != null)
            dp.onRepeat(resendCount, countsLeft);
    }
    
    public void updateDataPacket(DataPacket dp) {
        this.dp.updateSelf(dp);
    }

    public final int getSenderId() {
        return senderID;
    }

    public final String getMessageTag() {
        return messageTag;
    }
    
    public final DataPacket getDataPacket() {
        return dp;
    }
    
    public final Integer getResendCountsLeft() {
        return countsLeft;
    }
    
    @Override
    public String toString() {
        if(dp != null && dp.toString().length() > 0) {
            return String.format("Message \"%s\" carries \"%s\"", messageTag, dp.toString());
        }
        return String.format("Message \"%s\" carries \"%s\"", messageTag, null);
    }
}
