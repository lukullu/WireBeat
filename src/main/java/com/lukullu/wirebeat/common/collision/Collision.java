package com.lukullu.wirebeat.common.collision;

import com.kilix.processing.ProcessingClass;
import com.lukullu.wirebeat.common.data.Vector2;
import com.lukullu.wirebeat.common.data.Vertex;
import com.lukullu.wirebeat.common.msc.Utils;

import java.util.ArrayList;
import java.util.Comparator;

import static com.lukullu.wirebeat.common.data.Vector2.unitVector2;
import static com.lukullu.wirebeat.common.msc.Utils.getDistance;
import static com.lukullu.wirebeat.common.msc.Utils.getDistanceSqr;


public class Collision implements ProcessingClass {


	public static boolean pointRectangleCollision(Vector2 _pointPos, Vector2 _rectanglePos, Vector2 _rectangleDim) {

		return (_pointPos.x > _rectanglePos.x && _pointPos.x < _rectanglePos.x + _rectangleDim.x)
				&& (_pointPos.y > _rectanglePos.y && _pointPos.y < _rectanglePos.y + _rectangleDim.y);

	}


	public static ArrayList<Vertex> rayCast(Vector2 pos, Vector2 dir){

		ArrayList<Vertex> result;

		float len = (float) Math.sqrt(Math.pow(dir.x,2)+Math.pow(dir.y,2));
		dir = unitVector2(dir);
		Vector2 accumulator = new Vector2(0,0);

		while(Math.sqrt(Math.pow(accumulator.x,2) + Math.pow(accumulator.y,2)) < len){
			
			float tempX = dir.x;
			float tempY = dir.y;

			//TODO: implement line Intersection detection

			accumulator.x += tempX;
			accumulator.y += tempY;
			
			if(Math.sqrt(Math.pow(accumulator.x,2)+Math.pow(accumulator.y,2)) > len){ return new ArrayList<>(); }
			
			
		}
		
		return new ArrayList<>();
		
	}

	public static Vertex lineIntersect( Vector2 p1, Vector2 p2 , Vector2 p3, Vector2 p4) {
		float denominator = (p1.x - p2.x) * (p3.y - p4.y) - (p1.y - p2.y) * (p3.x - p4.x);
		if(denominator != 0) {
			return new Vertex(new Vector2(
					((p1.x * p2.y - p1.y * p2.x) * (p3.x - p4.x) - (p1.x - p2.x) * (p3.x * p4.y - p3.y * p4.x)) / denominator,
					((p1.x * p2.y - p1.y * p2.x) * (p3.y - p4.y) - (p1.y - p2.y) * (p3.x * p4.y - p3.y * p4.x)) / denominator
			));
		}else return null;
	}


	public static Vertex lineSegmentIntersect(Vector2 p1, Vector2 q1, Vector2 p2, Vector2 q2) {
		float s1x = q1.x - p1.x,
				s2x = q2.x - p2.x,
				s1y = q1.y - p1.y,
				s2y = q2.y - p2.y;
		float v = -s2x * s1y + s1x * s2y;
		if (v == 0) return null;
		float s = (-s1y * (p1.x - p2.x) + s1x * (p1.y - p2.y)) / v;
		float t = (-s2y * (p1.x - p2.x) + s2x * (p1.y - p2.y)) / v;

		if (s >= 0 && s <= 1 && t >= 0 && t <= 1)
			return new Vertex(new Vector2(p1.x + t * s1x, p1.y + t * s1y));
		return null;
	}


	public static ArrayList<Vertex> allLineIntersects( Vector2 origin, Vector2 endPoint , ArrayList<Vertex> vertices){
		ArrayList<Vertex> output = new ArrayList<>();
		for( Vertex v : vertices){
			Vertex intersect = lineSegmentIntersect( origin, endPoint, v.pos, v.neighbors[1].pos);
			if(intersect != null) {
				intersect.neighbors[0] = v; intersect.neighbors[1] = v.neighbors[1];
				output.add(intersect);
			}
		}
		output = sortVerticesByDistance(output, origin);
		return output;
	}

	public static ArrayList<Vertex> sortVerticesByDistance( ArrayList<Vertex> input , Vector2 origin){
		ArrayList<Vertex> output = new ArrayList<>(input);
		output.sort(Comparator.comparingInt(a -> (int) getDistance(a, new Vertex(origin))));
		return output;
	}

	public static boolean boxCollision(Vector2 pos1, Vector2 dim1, Vector2 pos2, Vector2 dim2) {

		return (pos1.x <= pos2.x + dim2.x && pos1.x + dim1.x >= pos2.x) && (pos1.y <= pos2.y + dim2.y && pos1.y + dim1.y >= pos2.y);

	}
}

