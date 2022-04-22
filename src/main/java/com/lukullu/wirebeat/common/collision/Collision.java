package com.lukullu.wirebeat.common.collision;

import com.kilix.processing.ProcessingClass;
import com.lukullu.wirebeat.common.data.Vector2;
import com.lukullu.wirebeat.common.data.Vertex;

import java.util.ArrayList;

import static com.lukullu.wirebeat.common.data.Vector2.unitVector2;


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


	public static boolean boxCollision(Vector2 pos1, Vector2 dim1, Vector2 pos2, Vector2 dim2) {

		return (pos1.x <= pos2.x + dim2.x && pos1.x + dim1.x >= pos2.x) && (pos1.y <= pos2.y + dim2.y && pos1.y + dim1.y >= pos2.y);

	}
}

