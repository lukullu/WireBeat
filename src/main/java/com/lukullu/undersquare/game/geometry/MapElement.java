package com.lukullu.undersquare.game.geometry;

import java.awt.*;

public class MapElement {
	
	public MapElement(boolean _static, boolean _drawableElement, Color _c, char _charID){ staticElement = _static; drawableElement = _drawableElement; c = _c; charID = _charID;}
	
	public boolean staticElement;
	public boolean drawableElement;
	public Color c;
	public char charID;
	
}
