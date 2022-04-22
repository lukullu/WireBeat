package com.lukullu.wirebeat.common.data;

public class Vertex {

    public Vector2 pos;
    public Vertex[] neighbors = new Vertex[2];

    public Vertex( Vector2 _pos, Vertex[] _neighbors) { pos = _pos; neighbors = _neighbors; }
    public Vertex( Vector2 _pos) { pos = _pos;}


}
