package com.lukullu.undersquare.widgets;

import com.kilix.processing.ProcessingClass;
import com.lukullu.undersquare.common.data.Vector2;

import java.awt.*;

import static com.lukullu.undersquare.common.Constants.*;

public class Widget implements ProcessingClass {
	
	public Vector2 pos;
	public Vector2 dim;
	public int cornerBL, cornerBR, cornerTL, cornerTR;
	public Color c = Color.white;
	
	public Widget(Vector2 _pos, Vector2 _dim, int _cornerBL, int _cornerBR, int _cornerTL, int _cornerTR) { init(_pos, _dim, _cornerBL, _cornerBR, _cornerTL, _cornerTR); }
	
	public void init(Vector2 _pos, Vector2 _dim, int _cornerBL, int _cornerBR, int _cornerTL, int _cornerTR){
		pos = _pos; dim = _dim; cornerBL = _cornerBL; cornerBR = _cornerBR; cornerTL = _cornerTL; cornerTR = _cornerTR;
	}
	
	// methods that take their relative position
	// if you don't need the vec2, simply ignore it.
	public void update(Vector2 _rel) {}
	public void paint(Vector2 _rel) { noStroke(); fill(UI_CONTRAST_COLOR.getRGB()); rect(pos.x + _rel.x, pos.y + _rel.y, dim.x, dim.y, cornerTL, cornerTR, cornerBR, cornerBL);}
	// same methods, but they add the (0, 0) vector automatically
	// these should not be overridden (otherwise the ones with _rel won't work)
	public final void update() { update(ZERO_VECTOR_2); }
	public final void paint() { paint(ZERO_VECTOR_2); }
	
}
