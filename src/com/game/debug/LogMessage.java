/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.debug;

import com.game.core.DataPacket;
import com.game.core.Message;

/**
 *
 * @author 17737
 */
public class LogMessage extends Message {

    private static class Log implements DataPacket {

        private final long startTime;
        private Long endTime;
        private Long lengthOfTime;

        public Log() {
            startTime = System.nanoTime() / 1000000;
            endTime = null;
            lengthOfTime = null;
        }

        @Override
        public void updateSelf(DataPacket dp) {
        }

        @Override
        public void onRepeat(int totalResendCounts, int resendCountsLeft) {
            if (totalResendCounts != resendCountsLeft && endTime == null) {
                endTime = System.nanoTime() / 1000000L;
                lengthOfTime = endTime - startTime;
            }
        }

        @Override
        public Object get() {
            return null;
        }

        @Override
        public String toString() {
            return String.format("Time Elasped: %s milliseconds, Start Time: %s milliseconds in, End Time: %s milliseconds in", lengthOfTime, startTime, endTime);
        }

    }

    public LogMessage(int senderID, String messageTag, int resendCount) {
        super(senderID, messageTag + " log_time", new Log(), resendCount);
    }
}
