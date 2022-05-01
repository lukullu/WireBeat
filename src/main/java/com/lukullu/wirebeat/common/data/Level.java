package com.lukullu.wirebeat.common.data;

import com.lukullu.wirebeat.entities.Camera;
import com.lukullu.wirebeat.entities.Entity;
import com.lukullu.wirebeat.entities.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.lukullu.wirebeat.common.Constants.TILE_SIZE;

public class Level {
    //TODO: 1. make new test level
    //TODO: 2. split screen in half or find out a way to

    public ArrayList<Vertex> vertices;
    public ArrayList<Entity> entities;
    public Player player;
    public final Vector2 spawnPosition;

    public Level( ArrayList<Vertex> _vertices, ArrayList<Entity> _entities , Vector2 _spawnPosition) {

        vertices = linkVertices(_vertices);
        entities = _entities;
        spawnPosition = _spawnPosition;
        player = new Player( spawnPosition, Direction.LEFT);

    }

    public static Level createFromFile( File file ){ return null; } //TODO: implement

    public static ArrayList<Vertex> linkVertices( ArrayList<Vertex> verticesToBeLinked){

        for(int i = 0; i < verticesToBeLinked.size(); i++){
            verticesToBeLinked.get(i).isVirtual = false;
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
                    new Vertex( new Vector2(3 * TILE_SIZE,1 * TILE_SIZE) ),
                    new Vertex( new Vector2(3 * TILE_SIZE,2 * TILE_SIZE) ),
                    new Vertex( new Vector2(2 * TILE_SIZE,2 * TILE_SIZE) ),
                    new Vertex( new Vector2(2 * TILE_SIZE,3 * TILE_SIZE) ),
                    new Vertex( new Vector2(1 * TILE_SIZE,3 * TILE_SIZE) ),
                    new Vertex( new Vector2(1 * TILE_SIZE,1 * TILE_SIZE) )
            )),
            new ArrayList<>( List.of()),
            new Vector2(2.5 * TILE_SIZE, 1.5 * TILE_SIZE)
    );

    public static final Level HARDCODED_TEST_LEVEL2 = new Level(
            new ArrayList<>( List.of(
                    new Vertex( new Vector2(1 * TILE_SIZE,1 * TILE_SIZE) ),
                    new Vertex( new Vector2(4 * TILE_SIZE,1 * TILE_SIZE) ),
                    new Vertex( new Vector2(4 * TILE_SIZE,2 * TILE_SIZE) ),
                    new Vertex( new Vector2(3 * TILE_SIZE,2 * TILE_SIZE) ),
                    new Vertex( new Vector2(3 * TILE_SIZE,3 * TILE_SIZE) ),
                    new Vertex( new Vector2(4 * TILE_SIZE,3 * TILE_SIZE) ),
                    new Vertex( new Vector2(4 * TILE_SIZE,4 * TILE_SIZE) ),
                    new Vertex( new Vector2(1 * TILE_SIZE,4 * TILE_SIZE) )
            )),
            new ArrayList<>( List.of()),
            new Vector2(1.5 * TILE_SIZE, 2.5 * TILE_SIZE)
    );


}
