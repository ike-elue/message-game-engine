/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.render;

import com.game.core.Core;
import com.game.core.Message;
import com.game.engine.EngineManager;
import com.game.engine.RenderingEngine;
import com.game.utils.DefaultShader;
import com.game.utils.Maths;
import com.game.utils.Pixel;
import com.game.utils.RawModel;
import com.game.utils.ShaderProgram;
import java.awt.Color;
import org.joml.Vector2f;
import org.joml.Vector3f;
import static org.lwjgl.opengl.ARBVertexArrayObject.glBindVertexArray;
import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;

/**
 *
 * @author 17737
 */
public class RenderEngine extends RenderingEngine {

    private final int hWidth;
    private final int hHeight;

    public RenderEngine(Core core, EngineManager em) {
        super("render_engine", core, new String[]{"all"}, em);
        hWidth = core.width / 2;
        hHeight = core.height / 2;
    }

    public void start(ShaderProgram sp) {
        sp.bind();
    }

    public void stop(ShaderProgram sp) {
        sp.unbind();
    }

    private Vector2f getRealCoords(Vector2f pos) {
        Vector2f position = new Vector2f(pos.x - hWidth*2, pos.y - hHeight*2);
        return position.mul(1 / hWidth/2, 1 / hHeight/2); 
    }

    private void setUniforms(ShaderProgram sp, Vector2f position, float width, float height, boolean isTexture, int color) {
        sp.setUniform("transformationMatrix", Maths.createTransformationMatrix(getRealCoords(position), width, height));
        sp.setUniform("isTexture", isTexture);
        sp.setUniform("color", new Vector3f(Pixel.getRed(color), Pixel.getGreen(color), Pixel.getBlue(color)));
    }

    private void enableVAO(RawModel model) {
        // Bind the vertex array and enable locations
        glBindVertexArray(model.getVaoID());
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
    }

    private void disableVAO() {
        // Disable locations
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glBindVertexArray(0);
    }

    public void drawRect(RawModel model, Vector2f pos, float width, float height, int color) {
        start(model.getShaderProgram());
        setUniforms(model.getShaderProgram(), pos, width, height, false, color);
        enableVAO(model);
        glDrawElements(GL_LINES, model.getVertexCount(), GL_UNSIGNED_INT, 0);
        disableVAO();
        stop(model.getShaderProgram());
    }

    public String fillRect(RawModel model, Vector2f pos, Vector2f dimensions, int color) {
        start(model.getShaderProgram());
        setUniforms(model.getShaderProgram(), pos, dimensions.x, dimensions.y, false, color);
        enableVAO(model);
        glDrawElements(GL_TRIANGLES, model.getVertexCount(), GL_UNSIGNED_INT, 0);
        disableVAO();
        stop(model.getShaderProgram());
        return pos + " " + dimensions + " " + Pixel.formatColor(color);
    }

    public String fillRect(Message m) {
        return fillRect((RawModel) ((Object[]) (((FillRectPacket) m.getDataPacket()).get()))[0],
                (Vector2f) ((Object[]) (((FillRectPacket) m.getDataPacket()).get()))[1],
                (Vector2f) ((Object[]) (((FillRectPacket) m.getDataPacket()).get()))[2],
                (Integer) ((Object[]) (((FillRectPacket) m.getDataPacket()).get()))[3]);
    }

    @Override
    public void init() {
        ShaderProgram shader = null;
        try {
            shader = new DefaultShader("res/standard_vertex_shader.vert", "res/standard_fragment_shader.frag");
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.sendMessage(new RenderMessage(getId(), new FillRectPacket(shader, getCore().width, getCore().height, new Vector2f(200, 200), new Vector2f(100, 100), Pixel.getColor(Color.PINK))));
    }

    @Override
    public void update(Message m, double delta) {
        if (m.getMessageTag().contains("render")) {
            addRenderMessage(m);
            sendMessage(m);
        }
    }
    
    @Override
    public void postUpdate() {
    }

    @Override
    public void dispose() {}
    
    @Override
    public void render(Message m) {
       if (m.getMessageTag().equalsIgnoreCase("render_fill_rect")) {
            //System.out.println(fillRect(m));
            fillRect(m);
       } 
    }
}
