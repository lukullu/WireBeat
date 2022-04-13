package com.lukullu.undersquare.common;

import com.kilix.processing.ProcessingClass;
import com.lukullu.undersquare.UnderSquare;

public class KeyHandler implements ProcessingClass {
	
	public static boolean w;
	public static boolean a;
	public static boolean s;
	public static boolean d;
	public static boolean e;
	public static boolean q;
	
	public static boolean shift = false;
	public static boolean up    = false;
	public static boolean down  = false;
	public static boolean left  = false;
	public static boolean right = false;
	
	public static char lastPressedKey;
	
	public static void updatePressed() {
		
		lastPressedKey = UnderSquare.INSTANCE.key;
		
		w = checkKeyPressed("w");
		a = checkKeyPressed("a");
		s = checkKeyPressed("s");
		d = checkKeyPressed("d");
		e = checkKeyPressed("e");
		q = checkKeyPressed("q");
		
		shift = checkKeyCodePressed(SHIFT);
		up    = checkKeyCodePressed(UP);
		down  = checkKeyCodePressed(DOWN);
		left  = checkKeyCodePressed(LEFT);
		right = checkKeyCodePressed(RIGHT);
		
	}
	
	public static void updateReleased(){
		
		w = checkKeyReleased("w");
		a = checkKeyReleased("a");
		s = checkKeyReleased("s");
		d = checkKeyReleased("d");
		e = checkKeyReleased("e");
		q = checkKeyReleased("q");
		
		shift = checkKeyCodeReleased(SHIFT);
		up    = checkKeyCodeReleased(UP);
		down  = checkKeyCodeReleased(DOWN);
		left  = checkKeyCodeReleased(LEFT);
		right = checkKeyCodeReleased(RIGHT);
		
	}
	
	public static boolean checkKeyPressed(String keyDesignator){
		char keyName = keyDesignator.charAt(0);
		boolean old = false;
		switch (keyName) {
			case 'w' -> old = w;
			case 'a' -> old = a;
			case 's' -> old = s;
			case 'd' -> old = d;
			case 'e' -> old = e;
			case 'q' -> old = q;
			default -> {
			}
		}
		return ("" + UnderSquare.INSTANCE.key).toLowerCase().charAt(0) == keyName || old;
	}
	
	public static boolean checkKeyCodePressed(int keyName){
		boolean old = false;
		switch (keyName) {
			case SHIFT -> old = shift;
			case UP -> old = up;
			case DOWN -> old = down;
			case LEFT -> old = left;
			case RIGHT -> old = right;
			default -> {
			}
		}
		return UnderSquare.INSTANCE.keyCode == keyName || old;
	}
	
	public static boolean checkKeyReleased(String keyDesignator){
		char keyName = keyDesignator.charAt(0);
		boolean old = false;
		switch (keyName) {
			case 'w' -> old = w;
			case 'a' -> old = a;
			case 's' -> old = s;
			case 'd' -> old = d;
			case 'e' -> old = e;
			case 'q' -> old = q;
			default -> {
			}
		}
		return ("" + UnderSquare.INSTANCE.key).toLowerCase().charAt(0) != keyName && old;
	}
	
	public static boolean checkKeyCodeReleased(int keyName){
		boolean old = false;
		switch (keyName) {
			case SHIFT -> old = shift;
			case UP -> old = up;
			case DOWN -> old = down;
			case LEFT -> old = left;
			case RIGHT -> old = right;
			default -> {
			}
		}
		return UnderSquare.INSTANCE.keyCode != keyName && old;
	}
	
}