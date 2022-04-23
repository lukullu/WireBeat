package com.lukullu.wirebeat.common.data;

import com.lukullu.wirebeat.entities.Camera;
import com.lukullu.wirebeat.entities.Entity;
import com.lukullu.wirebeat.entities.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Level {

    public ArrayList<Vertex> vertices;
    public ArrayList<Entity> entities;
    public Player player;

    public Level( ArrayList<Vertex> _vertices, ArrayList<Entity> _entities ) {

        vertices = linkVertices(_vertices);
        entities = _entities;
        player = new Player( new Vector2(125 ,75));

    }

    public static Level createFromFile( File file ){ return null; } //TODO: implement

    public static ArrayList<Vertex> linkVertices( ArrayList<Vertex> verticesToBeLinked){

        for(int i = 0; i < verticesToBeLinked.size(); i++){

            if(i == 0){ verticesToBeLinked.get(i).neighbors[0] = verticesToBeLinked.get(verticesToBeLinked.size()-1); }
            else{
                verticesToBeLinked.get(i).neighbors[0] = verticesToBeLinked.get(i-1);
            }
            if(i == verticesToBeLinked.size()-1){ verticesToBeLinked.get(i).neighbors[1] = verticesToBeLinked.get(0); }
            else{
                verticesToBeLinked.get(i).neighbors[1] = verticesToBeLinked.get(i+1);
            }

        }
        return verticesToBeLinked;
    }

    public static final Level HARDCODED_TEST_LEVEL = new Level(
            new ArrayList<>( List.of(
                    new Vertex( new Vector2(100 ,50) ),
                    new Vertex( new Vector2(150 ,50) ),
                    new Vertex( new Vector2(150,100) ),
                    new Vertex( new Vector2(100,100) ),
                    new Vertex( new Vector2(100,150) ),
                    new Vertex( new Vector2(50,150)  ),
                    new Vertex( new Vector2(50,100)  ),
                    new Vertex( new Vector2(50 ,50)  )
            )),
            new ArrayList<>( List.of())
    );

    public static final Level HARDCODED_TEST_LEVEL2 = new Level(
            new ArrayList<>( List.of(
                    new Vertex( new Vector2(150,150)  ),
                    new Vertex( new Vector2(200,150) ),
                    new Vertex( new Vector2(250,150) ),
                    new Vertex( new Vector2(250,200) ),
                    new Vertex( new Vector2(200,200) ),
                    new Vertex( new Vector2(200,250) ),
                    new Vertex( new Vector2(150,250) ),
                    new Vertex( new Vector2(150,200) )
            )),
            new ArrayList<>( List.of())
    );

}
