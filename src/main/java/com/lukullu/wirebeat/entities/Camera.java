package com.lukullu.wirebeat.entities;

import com.kilix.processing.HelperMethods;
import com.kilix.processing.ProcessingClass;
import com.lukullu.wirebeat.common.collision.Collision;
import com.lukullu.wirebeat.common.data.Level;
import com.lukullu.wirebeat.common.data.Vector2;
import com.lukullu.wirebeat.common.data.Vertex;
import com.lukullu.wirebeat.common.data.VertexPair;
import com.lukullu.wirebeat.common.msc.Utils;

import java.util.ArrayList;
import java.util.Arrays;

import static com.lukullu.wirebeat.common.Constants.FOV_RADIUS;

public class Camera extends Entity implements ProcessingClass {

    public float rotation = radians(80);
    public float FOV = radians(70);

    public Camera( Vector2 _pos ) { super(_pos); }

    @Override
    public void update(){

        rotation += radians(1f);
        if(getKeyCode() == LEFT  && getKeyPressed()){ rotation -= radians(2f);}
        if(getKeyCode() == RIGHT && getKeyPressed()){ rotation += radians(2f);}

    }

    public void paint( Level level) {


        ArrayList<VertexPair> visibleVertexPairs = createVisibleVertexPairs(pos,rotation,FOV,level);

        paintMiniMap(visibleVertexPairs,level);

        paintFirstPersonView(visibleVertexPairs,pos,rotation,FOV);


    }

    public void paintMiniMap( ArrayList<VertexPair> visibleVertexPairs, Level level) {

        for(int i = 0; i < level.vertices.size(); i++){

            Vertex v1 = level.vertices.get(i);
            Vertex v2 = level.vertices.get(i).neighbors[0];

            ellipseMode(CENTER);
            ellipse(v1.pos.x*10,v1.pos.y*10,4,4);
            stroke(1);
            if(i == 0){ stroke(24,24,200);} else {stroke(0);}
            line(v1.pos.x*10,v1.pos.y*10,v2.pos.x*10,v2.pos.y*10);

        }

        ellipseMode(CENTER); ellipse(pos.x*10,pos.y*10,4,4);
        line(pos.x*10,pos.y*10,pos.x*10 + (float)Math.cos(rotation + FOV/2f) * FOV_RADIUS,pos.y*10 + (float)Math.sin(rotation + FOV/2f) * FOV_RADIUS);
        line(pos.x*10,pos.y*10,pos.x*10 + (float)Math.cos(rotation - FOV/2f) * FOV_RADIUS,pos.y*10 + (float)Math.sin(rotation - FOV/2f) * FOV_RADIUS);

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
            ellipse(v.pos.x*10, v.pos.y*10, 4,4);
        }

        for(Vertex v : lineIntersectionsOfFOVEnd) {
            ellipse(v.pos.x*10, v.pos.y*10, 4,4);
        }

        for(VertexPair vp : visibleVertexPairs){
            line( 200 + vp.v1.pos.x*10,vp.v1.pos.y*10,200 +vp.v2.pos.x*10,vp.v2.pos.y*10);
        }
    }

    public ArrayList<VertexPair> createVisibleVertexPairs(Vector2 origin, float rotation, float FOV ,Level level) {

        ArrayList<VertexPair> output = new ArrayList<>();
        ArrayList<Vertex> usedVertices = new ArrayList<>();

        ArrayList<Vertex> lineIntersectionsOfStart = Collision.allLineIntersects(
                origin,
                new Vector2(origin.x + (float) Math.cos(rotation + FOV / 2f) * FOV_RADIUS, origin.y + (float) Math.sin(rotation + FOV / 2f) * FOV_RADIUS),
                level.vertices
        );

        ArrayList<Vertex> lineIntersectionsOfFOVEnd = Collision.allLineIntersects(
                origin,
                new Vector2(origin.x + (float) Math.cos(rotation - FOV / 2f) * FOV_RADIUS, origin.y + (float) Math.sin(rotation - FOV / 2f) * FOV_RADIUS),
                level.vertices
        );

        Vertex currentVertex = lineIntersectionsOfStart.get(0);

        while(true){

            //which neighbor is in range of the FOV
            ArrayList<Vertex> neighborsInRange = new ArrayList<>();
            for (int i = 0; i < 2; i++)
                if (isVertexAvailable(origin,currentVertex.neighbors[i],rotation,FOV,usedVertices)) {
                    if(isVertexVisible(origin,currentVertex.neighbors[i],level)) {neighborsInRange.add(currentVertex.neighbors[i]);}
                    else{
                        Vertex temp = findLastVisibleSpot(currentVertex,currentVertex.neighbors[i],origin,100,level);
                        if(!usedVertices.contains(temp)) {
                            neighborsInRange.add(temp);
                            usedVertices.add(temp);
                        }
                    }
                }

            //select with which vertex to proceed
            Vertex selectedVertex;
            if (neighborsInRange.size() == 2) {
                selectedVertex = neighborsInRange.get(0); //TODO implement?
            } else if (neighborsInRange.size() == 1) {
                selectedVertex = neighborsInRange.get(0);
            } else {
                break;
            }

            output.add(new VertexPair(currentVertex, selectedVertex));
            usedVertices.add(currentVertex);
            currentVertex = selectedVertex;

            ArrayList<Vertex> lineIntersectsToCurrentVertex = Collision.allLineIntersects(
                    origin,
                    new Vector2(origin.x + (currentVertex.pos.x - origin.x) * FOV_RADIUS, origin.y + (currentVertex.pos.y - origin.y) * FOV_RADIUS),
                    level.vertices
            );

            lineIntersectsToCurrentVertex = Utils.eliminateDoubles(lineIntersectsToCurrentVertex);
            for(Vertex v :lineIntersectsToCurrentVertex){
                for (int i = 0; i < 2; i++) if (isVertexVisible(origin,v.neighbors[i],level) && isVertexAvailable(origin, v, rotation, FOV, usedVertices)){ currentVertex = v; break;}
                else{ if(lineIntersectionsOfFOVEnd.get(0).neighbors[0].equals(v) || lineIntersectionsOfFOVEnd.get(0).neighbors[1].equals(v)) {currentVertex = v; break;} }
            }
        }

        output.add(new VertexPair(currentVertex,lineIntersectionsOfFOVEnd.get(0)));
        return output;

    }

    public static boolean isVertexAvailable( Vector2 origin, Vertex v, float rotation, float FOV, ArrayList<Vertex> usedVertices) {
        return (Utils.isVectorInRange(origin,v.pos, rotation, FOV) && !usedVertices.contains(v));}
    public static boolean isVertexVisible( Vector2 origin, Vertex v, Level level) {
        return(!(Utils.eliminateDoubles(Collision.allLineIntersects(origin, v.pos, level.vertices)).size() > 1));}

    public static Vertex findLastVisibleSpot( Vertex start, Vertex end, Vector2 origin, int steps, Level level) {
        float accuX = start.pos.x, accuY = start.pos.y;
        float deltaX = end.pos.x - start.pos.x, deltaY = end.pos.y - start.pos.y;
        // prepare steps for performance-micro-improvements
        float stepX = deltaX / steps, stepY = deltaY / steps;
        accuX += stepX;
        accuY += stepY;
        int counter = 0;
        while(!(accuX >= end.pos.x && accuY >= end.pos.y )){
            counter++;
            Vertex accu = new Vertex(new Vector2(accuX, accuY));
            if(! isVertexVisible(origin, accu, level)) {
                // we hit some vertex, so return the last valid point
                if (accu.equals(end)) return end;
                return accu;
            }
            accuX += stepX;
            accuY += stepY;
            if(counter > steps){ break;}
        }
        return end;
    }

    public void paintFirstPersonView(ArrayList<VertexPair> visibleVertices, Vector2 origin, float direction, float FOV){
        for(VertexPair vv : visibleVertices){
            interpolateVertices(vv.v1,vv.v2,20,origin,direction);}
    }

    public ArrayList<VertexPair> getRidOfUnnecessaryVertecies(ArrayList<VertexPair> input){

        ArrayList<VertexPair> output = new ArrayList<>();

        for(int i = 0; i < input.size(); i++){

            if(i == 0){
                output.add(input.get(0)); continue;
            }else{
                if(!input.get(i-1).v2.equals(input.get(i).v1)) continue;
            }

            Vector2 vector1;
            Vector2 vector2;
            VertexPair vertex1;
            VertexPair vertex2;


            vertex1 = input.get(i-1);
            vertex2 = input.get(i);

            vector1 = new Vector2(vertex1.v1.pos.x - vertex2.v1.pos.x,vertex1.v1.pos.y - vertex2.v1.pos.y);
            vector2 = new Vector2(vertex2.v1.pos.x - vertex2.v2.pos.x,vertex2.v1.pos.y - vertex2.v2.pos.y);

            if(Math.round(HelperMethods.degrees(Utils.deltaAngleBetweenVectors(vector1,vector2))) == 0){
                output.add(new VertexPair(vertex1.v1,vertex2.v2));
            }else{
                output.add(input.get(i));
            }

            text(Math.round(HelperMethods.degrees(Utils.deltaAngleBetweenVectors(vector1,vector2))),300,330 + 30*i);

        }

        return output;
    }

    public void interpolateVertices( Vertex start, Vertex end, int steps, Vector2 origin, float direction) {
        float accuX = start.pos.x, accuY = start.pos.y;
        float deltaX = end.pos.x - start.pos.x, deltaY = end.pos.y - start.pos.y;
        // prepare steps for performance-micro-improvements
        float stepX = deltaX / steps, stepY = deltaY / steps;
        for(int i = 0; i < steps; i++){
            Vector2 v1 = new Vector2(accuX,accuY);
            Vector2 v2 = new Vector2(accuX+stepX,accuY+stepY);
            //float distanceFromMiddleV1 = Utils.deltaAngleBetweenVectors(Vector2.createFromAngleAndLength(direction,FOV_RADIUS),new Vector2(vv.v1.pos.x - origin.x, vv.v1.pos.y - origin.y)) / FOV * getWidth();
            float heightV1 = ((1/Utils.getDistance(origin, v1))*getHeight());// + distanceFromMiddleV1/30);
            float verticalPosV1 = (Utils.deltaAngleBetweenVectors(Vector2.createFromAngleAndLength(direction + FOV/2f,FOV_RADIUS),new Vector2(v1.x - origin.x, v1.y - origin.y)) / FOV) * getWidth();
            //float distanceFromMiddleV2 = Utils.deltaAngleBetweenVectors(Vector2.createFromAngleAndLength(direction,FOV_RADIUS),new Vector2(vv.v2.pos.x - origin.x, vv.v2.pos.y - origin.y)) / FOV * getWidth();
            float heightV2 = ((1/Utils.getDistance(origin, v2))*getHeight());// + distanceFromMiddleV2/30);
            float verticalPosV2 = (Utils.deltaAngleBetweenVectors(Vector2.createFromAngleAndLength(direction + FOV/2f,FOV_RADIUS),new Vector2(v2.x - origin.x, v2.y - origin.y)) / FOV) * getWidth();
            line(getWidth()-verticalPosV1,getHeight()/2f - heightV1/2f,getWidth()-verticalPosV1,getHeight()/2f + heightV1/2f);
            line(getWidth()-verticalPosV2,getHeight()/2f - heightV2/2f,getWidth()-verticalPosV2,getHeight()/2f + heightV2/2f);
            line(getWidth()-verticalPosV1,getHeight()/2f + heightV1/2f,getWidth()-verticalPosV2,getHeight()/2f + heightV2/2f);
            line(getWidth()-verticalPosV1,getHeight()/2f - heightV1/2f,getWidth()-verticalPosV2,getHeight()/2f - heightV2/2f);
            accuX += stepX;
            accuY += stepY;
            ellipse(200+accuX*10,accuY*10,4,4);
        }
    }

}
