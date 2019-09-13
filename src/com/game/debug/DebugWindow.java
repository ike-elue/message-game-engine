/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.debug;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author 17737
 */
public class DebugWindow implements ActionListener, WindowListener {

    private final Map<String, String> dataStrings; 
    private final List<String> shouldStay;
    
    private final JFrame frame;
    private final Console c;
    private boolean disposed;
    private final boolean isDebug;
    private final JLabel fps, data;
    
    public DebugWindow(Console c, boolean isDebug) {
        this.c = c;
        this.isDebug = isDebug;    
        
        dataStrings = new HashMap<>();
        shouldStay = new ArrayList<>();
        
        frame = new JFrame("Debug Window");
        fps = new JLabel();
        data = new JLabel();

        if(isDebug) {
            disposed = false;

            
            
            frame.setSize(500, 500);
            frame.setLocation(50, 50);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setResizable(false);
            frame.addWindowListener(this);
            frame.setLayout(new BorderLayout());
            
            frame.add(fps, BorderLayout.SOUTH);
            frame.add(data, BorderLayout.NORTH);

            frame.setVisible(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    
    public boolean dispose(boolean checkingFrame) {
        if(frame != null) {
            if(checkingFrame) {
                return disposed;
            }
            disposed = true;
            return disposed;
        }
        
        return disposed;
    }

    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosing(WindowEvent e) {
        c.setRunning(false);
        dispose(false);
    }

    @Override
    public void windowClosed(WindowEvent e) {}

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}
    
    public void setFPS(String s) {
        if(isDebug)
            fps.setText(s);
    }
    
    public void setData(String[] sArray) {
        for(String s : sArray) {
            shouldStay.add(s.split("\"")[1]);
            dataStrings.put(s.split("\"")[1], s.split("\"")[3]);
        }
        
        fixMapping();
        
        String formattedString = "<html>";
        
        formattedString = dataStrings.keySet().stream().map((key) -> String.format("Message \"%s\" carries \"%s\"<br/><br/>", key, dataStrings.get(key))).reduce(formattedString, String::concat);
        
        if(isDebug)
            data.setText(formattedString);
        shouldStay.clear();
    }  
    
    private void fixMapping() {
        String[] keyset = dataStrings.keySet().toArray(new String[dataStrings.size()]).clone();
        for(String s : keyset) {
            if(!shouldStay.contains(s)) {
                dataStrings.remove(s);
            }
        }
    }
}
