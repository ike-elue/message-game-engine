/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game;

import com.game.core.AbstractGame;

/**
 *
 * @author 17737
 */
public class Test extends AbstractGame{
    public static void main(String[] args){
        Test t = new Test("Test",1280, 960, 60, true);
        t.start();
    }

    public Test(String title, int width, int height, double frameRate, boolean isDebug) {
		super(title, width, height, frameRate, isDebug);
    }

    
    @Override
    public void init() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initEngines() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
