/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.render;

import com.game.core.DataPacket;
import com.game.utils.Loader;
import com.game.utils.Pixel;
import com.game.utils.RawModel;
import com.game.utils.ShaderProgram;
import org.joml.Vector2f;

/**
 *
 * @author 17737
 */
public class FillRectPacket extends RenderPacket {

    private final RawModel model;
    private final Vector2f coordinates;
    private final Vector2f dimensions;
    private Integer color;
    
    public FillRectPacket(ShaderProgram sp, float screenwidth, float screennheight, Vector2f coordinates, Vector2f dimensions, int color) {
        float[] positions = new float[] { 0, 0, 0, 0, 2f/screennheight, 0, 2f/screenwidth, 2f/screennheight, 0, 2f/screenwidth, 0, 0};
	float[] textureCoords = new float[] {0f, 1f, 0f, 0f, 1f, 0f, 1f, 1f};
	int[] indices = new int[] {0, 1, 2, 2, 3, 0};
	model = Loader.loadToVAO(positions, textureCoords, indices, sp);
        this.coordinates = coordinates;
        this.dimensions = dimensions;
        this.color = color;
    }
    
    @Override
    public String name() {
        return "fill_rect";
    }

    @Override
    public void updateSelf(DataPacket dp) {
        if(dp != null) {
            this.coordinates.set(((FillRectPacket)dp).coordinates);
            this.dimensions.set(((FillRectPacket)dp).dimensions);
            this.color = ((FillRectPacket)dp).color;
        }
    }

    @Override
    public void onRepeat(int totalResendCounts, int resendCountsLeft) {}

    @Override
    public Object get() {
        return new Object[]{model, coordinates, dimensions, color};
    }

    @Override
    public String toString() {
        return model + ", Coordinates are " + coordinates + ", Dimensions are " + dimensions + "Color is " + Pixel.formatColor(color);
    }
}
