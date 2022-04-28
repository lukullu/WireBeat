package com.lukullu.wirebeat.common.data;

import com.kilix.processing.ProcessingClass;

import java.util.ArrayList;

public class Polygon implements ProcessingClass {

    ArrayList<Vertex> vertices = new ArrayList<>();
    ArrayList<Vertex> topVertices = new ArrayList<>();
    ArrayList<Vertex> bottomVertices = new ArrayList<>();

    public Polygon( ArrayList<Vertex> _vertices ){ vertices = _vertices; }
    public Polygon(){}

    public void addTopVertex(Vertex v){ topVertices.add(v); }
    public void addBottomVertex(Vertex v){ bottomVertices.add(v); }
    public Polygon build(){
        //debug
        fill(200);
        if(!(topVertices.size() == 0 || bottomVertices.size() == 0)){
            //add top vertices in order
            vertices.addAll(topVertices);
            //add bottom vertices in reverse order
            for(int i = bottomVertices.size()-1; i >= 0; i--)
                vertices.add(bottomVertices.get(i));
        }
        return this;
    }

    public void paint(){

        beginShape();
        for(Vertex v : vertices){
            vertex(v.pos.x, v.pos.y);
        }
        endShape();

    }



}
