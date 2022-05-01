package com.lukullu.wirebeat.common.msc;

import com.lukullu.wirebeat.common.data.Vector2;
import com.lukullu.wirebeat.common.data.Vertex;

import java.util.ArrayList;

import static com.lukullu.wirebeat.common.Constants.FOV_RADIUS;

public class Utils {

    public static <T extends Number> T clamp(T min, T max, T value){

        return (min.doubleValue() > value.doubleValue()
                ? min
                : (max.doubleValue() < value.doubleValue()
                ? max
                : value));

    }

    public static float getDistance(Vector2 v1 , Vector2 v2) {
        return (float) Math.sqrt(Math.pow(v1.x - v2.x,2) + Math.pow(v1.y - v2.y,2));
    }
    public static float getDistance(Vertex v1 , Vertex v2) {
        return (float) Math.sqrt(Math.pow(v1.pos.x - v2.pos.x,2) + Math.pow(v1.pos.y - v2.pos.y,2));
    }
    public static float getDistanceSqr(Vector2 v1 , Vector2 v2) {
        return (float) (Math.pow(v1.x - v2.x,2) + Math.pow(v1.y - v2.y,2));
    }
    public static float getDistanceSqr(Vertex v1 , Vertex v2) {
        return (float) (Math.pow(v1.pos.x - v2.pos.x,2) + Math.pow(v1.pos.y - v2.pos.y,2));
    }

    public static float deltaAngleBetweenVectors( Vector2 v, Vector2 u) {
        if(v.equals(u)){ return 0;}
        double num = (v.x * u.x + v.y * u.y);
        float den = (float)(Math.sqrt(Math.pow(v.x, 2) + Math.pow(v.y, 2)) * (Math.sqrt(Math.pow(u.x, 2) + Math.pow(u.y, 2))) );
        float temp = (float) Math.acos(num / den);
        if(Float.isNaN(temp)){ return 0;} else return temp;
    }

    public static boolean isVectorInRange(Vector2 origin, Vector2 v, float direction, float range ){
        return (deltaAngleBetweenVectors(new Vector2(v.x - origin.x, v.y - origin.y), Vector2.createFromAngleAndLength(direction,FOV_RADIUS)) < range /2f);
    }

    public float getAngleFromVector( Vector2 origin, Vector2 vector ) {
        return (float)Math.atan2(origin.x - vector.x, origin.y - vector.y);
    }

    public static ArrayList<Vertex> eliminateDoubles(ArrayList<Vertex> input){
        ArrayList<Vertex> output = new ArrayList<>();

        for(Vertex v : input){
            if(!output.contains(v)) output.add(v);
        }

        return output;
    }

}
