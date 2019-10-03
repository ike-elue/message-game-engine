/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.utils;

/**
 *
 * @author 17737
 */
public class RawModel {
    private final int vaoID;
    private final int vertexCount;
    private final ShaderProgram sp;
	
    public RawModel(int vaoID, int vertexCount, ShaderProgram sp) {
            this.vaoID = vaoID;
            this.vertexCount = vertexCount;
            this.sp = sp;
    }

    public int getVaoID() {
            return vaoID;
    }

    public int getVertexCount() {
            return vertexCount;
    }
    
    public ShaderProgram getShaderProgram() {
        return sp;
    }

    @Override
    public String toString() {
        return "RawModel's ShaderProgram = " + sp.getTag();
    }
}
