package com.lukullu.wirebeat.entities;

import com.kilix.processing.ProcessingClass;
import com.lukullu.wirebeat.common.data.Direction;
import com.lukullu.wirebeat.common.data.Vector2;

public class Entity implements ProcessingClass {

    Vector2 pos;
    public float rotation = radians(80);

    public Entity( Vector2 _pos) { pos = _pos;}

    public void init() {}
    public void tick() {}
    public void update() {}
    public void paint() {}


}
