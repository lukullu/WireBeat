package com.lukullu.wirebeat.common.collision;

import com.kilix.processing.ProcessingClass;
import com.lukullu.wirebeat.common.data.Vector2;


public class Collision implements ProcessingClass {


	public static boolean pointRectangleCollision(Vector2 _pointPos, Vector2 _rectanglePos, Vector2 _rectangleDim) {

		return (_pointPos.x > _rectanglePos.x && _pointPos.x < _rectanglePos.x + _rectangleDim.x)
				&& (_pointPos.y > _rectanglePos.y && _pointPos.y < _rectanglePos.y + _rectangleDim.y);

	}

	/*
	public static Vector2 rayCast(Vector2 pos, Vector2 dim, Vector2 dir){
		float len = (float) sqrt(pow(dir.x,2)+pow(dir.y,2));
		
		dir = unitVector2(dir);
		Vector2 accumulator = new Vector2(0,0);
		
		boolean[][] collisionData; if (UnderSquare.getGameHandler() != null) { collisionData = UnderSquare.getGameHandler().levelMap.collisionData; } else return ZERO_VECTOR_2;
		
		while(sqrt(pow(accumulator.x,2) + pow(accumulator.y,2)) < len){
			
			float tempX = dir.x;
			float tempY = dir.y;
			
			Vector2[] coords = getGridPositions(new Vector2(pos.x + accumulator.x + tempX, pos.y + accumulator.y + tempY),dim);
			
			if(collisionData[(int)coords[0].y][(int)coords[0].x])break;
			if(collisionData[(int)coords[1].y][(int)coords[1].x])break;
			if(collisionData[(int)coords[2].y][(int)coords[2].x])break;
			if(collisionData[(int)coords[3].y][(int)coords[3].x])break;
			
			
			accumulator.x += tempX;
			accumulator.y += tempY;
			
			if(sqrt(pow(accumulator.x,2)+pow(accumulator.y,2)) > len){ return new Vector2(dir.x * len, dir.y * len); }
			
			
		}
		
		return accumulator;
		
	}*/


	public static boolean boxCollision(Vector2 pos1, Vector2 dim1, Vector2 pos2, Vector2 dim2) {

		return (pos1.x <= pos2.x + dim2.x && pos1.x + dim1.x >= pos2.x) && (pos1.y <= pos2.y + dim2.y && pos1.y + dim1.y >= pos2.y);

	}
}

