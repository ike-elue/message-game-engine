/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.utils;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

/**
 *
 * @author 17737
 */
public class Maths {

    public static Matrix4f createTransformationMatrix(Vector2f positions, float width, float height) {
        Matrix4f transformmatrix = new Matrix4f();
        transformmatrix.identity();
        transformmatrix.translate(positions.x, positions.y, 1, transformmatrix);
        transformmatrix.scale(new Vector3f(width, height, 1), transformmatrix);
        return transformmatrix;
    }

}
