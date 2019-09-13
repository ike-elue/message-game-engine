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
public interface DataPacket {
    
    public void updateSelf(DataPacket dp);
    
    public void onRepeat(int totalResendCounts, int resendCountsLeft);
    
    public Object get();
    
    @Override
    public String toString();
}
