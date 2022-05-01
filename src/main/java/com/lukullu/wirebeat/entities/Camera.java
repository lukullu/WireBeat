package com.lukullu.wirebeat.entities;

import com.kilix.processing.HelperMethods;
import com.kilix.processing.ProcessingClass;
import com.lukullu.wirebeat.common.collision.Collision;
import com.lukullu.wirebeat.common.data.*;
import com.lukullu.wirebeat.common.msc.Utils;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;

import static com.lukullu.wirebeat.common.Constants.*;

public class Camera extends Entity implements ProcessingClass {

    public float FOV = radians(45);
    public float horizonOffset = 0;
    public float verticalScale = 1;

    public Camera( Vector2 _pos) { super(_pos); }

    @Override
    public void update(){

        if(rotation > PI * 2){ rotation = rotation - 2 * PI;} else if(rotation < 0) { rotation = PI * 2 + rotation; }
        if(getKeyPressed() && getKeyCode() == RIGHT ){ rotation += radians(2f);}

    }

    @Override
    public void tick(){

    }

    public void paint( Level level) {

        background(100);
        ArrayList<Vertex> scanResult = new ArrayList<>();

        try {
            scanResult = scanVisibleArea(pos,rotation,FOV,level);
        }catch (Exception e){ println(e);}

        paintFirstPersonView(level.player.pos,scanResult,level.player.rotation,level.player.FOV);

        paintMiniMap(level);

    }

    public void paintMiniMap( Level level) {

        for(int i = 0; i < level.vertices.size(); i++){

            Vertex v1 = level.vertices.get(i);
            Vertex v2 = level.vertices.get(i).neighbors[0];

            ellipseMode(CENTER);
            ellipse((float)v1.pos.x*10,(float)v1.pos.y*10,4,4);
            stroke(1);
            if(i == 0){ stroke(24,24,200);} else {stroke(0);}
            line((float)v1.pos.x*10,(float)v1.pos.y*10,(float)v2.pos.x*10,(float)v2.pos.y*10);

        }

        ellipseMode(CENTER); ellipse((float)pos.x*10,(float)pos.y*10,4,4);
        line((float)pos.x*10,(float)pos.y*10,(float)pos.x*10 + (float)Math.cos(rotation + FOV/2f) * FOV_RADIUS,(float)pos.y*10 + (float)Math.sin(rotation + FOV/2f) * FOV_RADIUS);
        line((float)pos.x*10,(float)pos.y*10,(float)pos.x*10 + (float)Math.cos(rotation - FOV/2f) * FOV_RADIUS,(float)pos.y*10 + (float)Math.sin(rotation - FOV/2f) * FOV_RADIUS);

        ArrayList<Vertex> lineIntersections = Collision.allLineIntersects(
                pos,
                new Vector2(pos.x + (float)Math.cos(rotation + FOV/2f) * FOV_RADIUS,pos.y + (float)Math.sin(rotation + FOV/2f) * FOV_RADIUS),
                level.vertices
        );

        ArrayList<Vertex> lineIntersectionsOfFOVEnd = Collision.allLineIntersects(
                pos,
                new Vector2(pos.x + (float) Math.cos(rotation - FOV / 2f) * FOV_RADIUS, pos.y + (float) Math.sin(rotation - FOV / 2f) * FOV_RADIUS),
                level.vertices
        );

        ellipseMode(CENTER);
        for(Vertex v : lineIntersections) {
            ellipse((float)v.pos.x*10, (float)v.pos.y*10, 4,4);
        }

        for(Vertex v : lineIntersectionsOfFOVEnd) {
            ellipse((float)v.pos.x*10, (float)v.pos.y*10, 4,4);
        }

    }


    public ArrayList<Vertex> scanVisibleArea(Vector2 origin, float rotation, float FOV ,Level level){
        ArrayList<Vertex> output = new ArrayList<>();

        double deltaAngle = FOV*4d / (getWidth());

        double rightEdge = rotation - FOV*0.5;
        for(double accu = 0; accu <= FOV; accu+=deltaAngle){
            Vector2 temp = new Vector2( origin.x + Vector2.createFromAngleAndLength(rightEdge + accu, FOV_RADIUS).x,origin.y + Vector2.createFromAngleAndLength(rightEdge + accu, FOV_RADIUS).y);
            ArrayList<Vertex> intersections = Collision.allLineIntersects(origin,temp,level.vertices);
            if(intersections.size() > 0) {output.add(intersections.get(0)); }
            else output.add(new Vertex(new Vector2(FOV_RADIUS+1,FOV_RADIUS+1))); //add invalid vertex
        }

        return output;
    }


    public void paintFirstPersonView(Vector2 origin,ArrayList<Vertex> scanResult, float direction, float FOV){
        paintBackground();
        translate(0,horizonOffset);

        for(int i = 0; i < scanResult.size(); i++){
            if(!scanResult.get(i).equals(new Vertex(new Vector2(FOV_RADIUS+1,FOV_RADIUS+1))))
                paintColumn(origin,scanResult.get(i),i,direction,FOV);
        }
        translate(0,-horizonOffset);
    }

    public void paintColumn(Vector2 origin, Vertex scanResult, int index, float direction, float FOV){

        float correctionValue = (float) Math.cos(index * (FOV/getWidth()));
        float wallHeight = (1/(Utils.getDistance(origin,scanResult.pos)* correctionValue))  * 1000;

        float distanceToNearestVertex = Math.round(Vertex.getDistanceToNearestNeighbor(scanResult) * 1000 * WALL_SCALE);
        //Ambient Occlusion
        if(distanceToNearestVertex + AMBIENT_OCCLUSION_MAX_DARKNESS > 255) fill(255); else fill(distanceToNearestVertex + AMBIENT_OCCLUSION_MAX_DARKNESS);

        noStroke();
        rect(index*4,getHeight()/2f - wallHeight/2f,4,wallHeight);

    }


    public void paintBackground(){
        noStroke();
        fill(50);
        rect(0,0,getWidth(),getHeight()/2f + horizonOffset);
        fill(150);
        rect(0,getHeight()/2f + horizonOffset,getWidth(),getHeight());
        stroke(1);
    }

}
