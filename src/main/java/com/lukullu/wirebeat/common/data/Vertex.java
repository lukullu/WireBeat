package com.lukullu.wirebeat.common.data;

import java.util.Arrays;
import java.util.Objects;

import static com.lukullu.wirebeat.common.Constants.TILE_SIZE;

public class Vertex {

    public Vector2 pos;
    public float z;
    public Vertex[] neighbors = new Vertex[2];
    public boolean isVirtual = true;

    public Vertex( Vector2 _pos, Vertex[] _neighbors, float _z) { pos = _pos; neighbors = _neighbors; z = _z;}
    public Vertex( Vector2 _pos, Vertex[] _neighbors) { pos = _pos; neighbors = _neighbors; }
    public Vertex( Vector2 _pos) { pos = _pos; }

    public String toString() { return String.format("Vec2(%.2f, %.2f)", pos.x, pos.y); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return Objects.equals(pos, vertex.pos);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(pos);
        result = 31 * result + Arrays.hashCode(neighbors);
        return result;
    }
}
