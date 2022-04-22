package com.lukullu.wirebeat.entities;

import com.kilix.processing.ProcessingClass;
import com.lukullu.wirebeat.common.data.Vector2;
import com.lukullu.wirebeat.common.data.Vertex;

public class Camera extends Entity implements ProcessingClass {

    public float rotation = radians(-90);
    public float FOV = radians(90);

    public Camera( Vector2 _pos ) { super(_pos); }

    @Override
    public void paint() {
        ellipseMode(CENTER); ellipse(pos.x,pos.y,4,4);
        line(pos.x,pos.y,pos.x + (float)Math.cos(rotation + FOV/2f) * 100,pos.y + (float)Math.sin(rotation + FOV/2f) * 100);
        line(pos.x,pos.y,pos.x + (float)Math.cos(rotation - FOV/2f) * 100,pos.y + (float)Math.sin(rotation - FOV/2f) * 100);
    }

}
