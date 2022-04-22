package com.lukullu.wirebeat.common.data;

import java.io.Serializable;
import java.util.Objects;

public class Vector2 implements Serializable {

	public float x, y;

	public Vector2(Number _x, Number _y) { x = _x.floatValue(); y = _y.floatValue(); }
	
	public String toString() { return String.format("Vec2(%.2f, %.2f)", x, y); }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Vector2 vector2 = (Vector2) o;
		return Float.compare(vector2.x, x) == 0 && Float.compare(vector2.y, y) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	public static Vector2 toVector2(float h) {
		float s = (float) Math.cos(45)/h;
		
		return new Vector2(s,s);
	}
	
	public static Vector2 calcAngleAndValueFromVector(Vector2 v) {
		return new Vector2(Math.atan2(v.y,v.x), Math.sqrt(Math.pow(v.x,2) + Math.pow(v.y,2)));}

	public static Vector2 calcVectorFromAngleAndValue(Vector2 v) {
		return new Vector2(v.y * Math.cos(v.x), v.y * Math.sin(v.x));}

	public static Vector2 unitVector2(Vector2 v){
		float len = (float) Math.sqrt(Math.pow(v.x,2) + Math.pow(v.y,2));
		return new Vector2(v.x/len, v.y/len);}
	

}