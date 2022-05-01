package com.lukullu.wirebeat.common.msc;

import com.kilix.processing.ProcessingClass;
import com.lukullu.wirebeat.common.data.Vector2;

import java.awt.*;
import java.util.ArrayList;

import static com.lukullu.wirebeat.common.msc.Translation.*;

public class Debug implements ProcessingClass {
	
	public static final Debug INSTANCE = new Debug();
	
	Color c = Color.white;
	
	public static int deltaY = 15;
	public static ArrayList<String> debugLinesTemp = new ArrayList<>();
	public static ArrayList<String> debugLinesConst = new ArrayList<>();
	
	public static void displayTemp(String input) { debugLinesTemp.add(input); }
	public static void displayConst(String input) { debugLinesConst.add(input); }
	
	public static void paint(Vector2 delta) { INSTANCE._paint(delta); }
	
	public void _paint(Vector2 delta) {
		fill(c.getRGB());
		textSize(15);

		text("Dynamic:", getWidth() - scaleToScreenX(400) + (float)delta.x, scaleToScreenY(50)+ (float)delta.y);
		for( int i = 1; i <= debugLinesTemp.size(); i++){
			text(debugLinesTemp.get(i-1),getWidth() - scaleToScreenX(400) + (float)delta.x,scaleToScreenY(50) + deltaY * i + (float)delta.y);
		}
		text("Const:",getWidth() - scaleToScreenX(400) + (float)delta.x, scaleToScreenY(50) + deltaY * (debugLinesTemp.size()+1) + (float)delta.y);
		for( int i = 1; i <= debugLinesConst.size(); i++){
			text(debugLinesConst.get(i-1),getWidth() - scaleToScreenX(400) + (float)delta.x,scaleToScreenY(50) + deltaY * i + (float)delta.y + (debugLinesTemp.size()+2) * deltaY);
		}
		
		debugLinesTemp = new ArrayList<>();
		
	}
	
}
