package com.lukullu.undersquare.common.data;

public class Vector2 {
	
	public Vector2(Number _x, Number _y) { x = _x.floatValue(); y = _y.floatValue(); }
	
	public float x, y;
	
	public String toString() { return String.format("Vec2(%.2f, %.2f)", x, y); }
	
	
	
	public static Vector2 toVector2(float h) {
		float s = (float) Math.cos(45)/h;
		
		return new Vector2(s,s);
	}
	
	public static Vector2 calcAngleAndValueFromVector(Vector2 v) {
		
		return new Vector2(Math.atan2(v.y,v.x), Math.sqrt(Math.pow(v.x,2) + Math.pow(v.y,2)));
		
	}
	
	public static Vector2 unitVector2(Vector2 v){
		
		float len = (float) Math.sqrt(Math.pow(v.x,2) + Math.pow(v.y,2));
		
		return new Vector2(v.x/len, v.y/len);
		
	}
	
	public static Vector2 calcVectorFromAngleAndValue(Vector2 v) {
		
		return new Vector2(v.y * Math.cos(v.x), v.y * Math.sin(v.x));
		
	}
	
	public static Vector2 capVector(Vector2 v, float maxValue) {
		
		Vector2 output;
		Vector2 anv = calcAngleAndValueFromVector(v);
		
		if(anv.y <= maxValue){
			
			output = v;
			
		}else{
			
			output = new Vector2(calcVectorFromAngleAndValue(v).x,maxValue);
			
		}
		
		return output;
		
	}
	
	
}