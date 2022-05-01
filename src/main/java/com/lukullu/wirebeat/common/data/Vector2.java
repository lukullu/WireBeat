package com.lukullu.wirebeat.common.data;

import java.io.Serializable;
import java.util.Objects;

public class Vector2 implements Serializable {

	public double x, y;

	public Vector2(Number _x, Number _y) { x = _x.doubleValue(); y = _y.doubleValue(); }
	
	public String toString() { return String.format("Vec2(%.2f, %.2f)", x, y); }

	public static Vector2 createFromAngleAndLength( double angle, double length) { return new Vector2(Math.cos(angle) * length,Math.sin(angle) * length);  }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Vector2 vector2 = (Vector2) o;
		return Double.compare(vector2.x, x) == 0 && Double.compare(vector2.y, y) == 0;
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

	public static Vector2 resizeVector2(Vector2 v, float length){
		float len = (float) Math.sqrt(Math.pow(v.x,2) + Math.pow(v.y,2));
		return new Vector2((v.x/len)*length, (v.y/len)*length);}

}