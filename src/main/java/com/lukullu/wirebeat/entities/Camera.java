package com.lukullu.wirebeat.entities;

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
    public float FOV = radians(90);

    public Camera( Vector2 _pos ) { super(_pos); }

    @Override
    public void update(){

        if(getKeyCode() == LEFT  && getKeyPressed()){ rotation += radians(1f);}
        if(getKeyCode() == RIGHT && getKeyPressed()){ rotation -= radians(1f);}

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

            ellipse(currentVertex.pos.x,currentVertex.pos.y,6,6);

            //which neighbor is in range of the FOV
            ArrayList<Vertex> neighborsInRange = new ArrayList<>();
            for (int i = 0; i < 2; i++)
                if (isVertexAvailable(pos,currentVertex.neighbors[i],rotation,FOV,usedVertices)) {
                    if(isVertexVisible(pos,currentVertex.neighbors[i],level)) {neighborsInRange.add(currentVertex.neighbors[i]);}
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
            line(origin.x,origin.y,origin.x + (currentVertex.pos.x - origin.x) * FOV_RADIUS,origin.y + (currentVertex.pos.y - origin.y) * FOV_RADIUS);

            lineIntersectsToCurrentVertex = Utils.eliminateDoubles(lineIntersectsToCurrentVertex);


            for(Vertex v :lineIntersectsToCurrentVertex){
                for (int i = 0; i < 2; i++) if (isVertexVisible(pos,v.neighbors[i],level) && isVertexAvailable(pos, v, rotation, FOV, usedVertices)){ currentVertex = v; break;}
                else{ if(lineIntersectionsOfFOVEnd.get(0).neighbors[0].equals(v) || lineIntersectionsOfFOVEnd.get(0).neighbors[1].equals(v)) {currentVertex = v; break;} }
            }

        }

        if( output.size() > 0) output.add(new VertexPair(currentVertex,lineIntersectionsOfFOVEnd.get(0)));

        return output;
    }

    public void paint( Level level) {

        ellipseMode(CENTER); ellipse(pos.x,pos.y,4,4);
        line(pos.x,pos.y,pos.x + (float)Math.cos(rotation + FOV/2f) * FOV_RADIUS,pos.y + (float)Math.sin(rotation + FOV/2f) * FOV_RADIUS);
        line(pos.x,pos.y,pos.x + (float)Math.cos(rotation - FOV/2f) * FOV_RADIUS,pos.y + (float)Math.sin(rotation - FOV/2f) * FOV_RADIUS);

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
            ellipse(v.pos.x, v.pos.y, 4,4);
        }

        for(Vertex v : lineIntersectionsOfFOVEnd) {
            ellipse(v.pos.x, v.pos.y, 4,4);
        }

        //rotation += radians(0.5f);
        ArrayList<VertexPair> visibleVertexPairs = createVisibleVertexPairs(pos,rotation,FOV,level);
        for(VertexPair vp : visibleVertexPairs){
            line( 200 + vp.v1.pos.x,vp.v1.pos.y,200 +vp.v2.pos.x,vp.v2.pos.y);
        }

    }

    public static boolean isVertexAvailable( Vector2 origin, Vertex v, float rotation, float FOV, ArrayList<Vertex> usedVertices) {
        boolean temp = (Utils.isVectorInRange(origin,v.pos, rotation, FOV) && !usedVertices.contains(v));
        //System.out.println(temp+"|"+usedVertices+"|"+v);
        return temp;
    }
    public static boolean isVertexVisible( Vector2 origin, Vertex v, Level level) {
        return(!(Utils.eliminateDoubles(Collision.allLineIntersects(origin, v.pos, level.vertices)).size() > 1));
    }

    public static Vertex findLastVisibleSpot( Vertex start, Vertex end, Vector2 origin, int steps, Level level) {

        float accuX = start.pos.x, accuY = start.pos.y;
        float deltaX = end.pos.x - start.pos.x, deltaY = end.pos.y - start.pos.x;
        // prepare steps for performance-micro-improvements
        float stepX = deltaX / steps, stepY = deltaY / steps;

        accuX += stepX;
        accuY += stepY;
        while(!(accuX >= end.pos.x && accuY >= end.pos.y )){

            Vertex accu = new Vertex(new Vector2(accuX, accuY));
            if(! isVertexVisible(origin, accu, level)) {
                // we hit some vertex, so return the last valid point

                if (accu.equals(end)) return end;

                return accu;
            }

            accuX += stepX;
            accuY += stepY;

        }

        return end;

    }

}
