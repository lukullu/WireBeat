package com.lukullu.undersquare.widgets;

import com.lukullu.undersquare.common.data.Vector2;

import java.awt.*;

import static com.lukullu.undersquare.common.Constants.*;

public class Widget {
	
	public Vector2 pos;
	public Vector2 dim;
	
	public Color c = Color.white;
	
	public Widget(Vector2 _pos, Vector2 _dim) { init(_pos, _dim); }
	
	public void init(Vector2 _pos, Vector2 _dim){
		pos = _pos; dim = _dim;
	}
	
	// methods that take their relative position
	// if you don't need the vec2, simply ignore it.
	public void update(Vector2 _rel) {}
	public void paint(Vector2 _rel) {}
	// same methods, but they add the (0, 0) vercotr automatically
	// these should not be overridden (otherwise the ones with _rel won't work)
	public final void update() { update(ZERO_VECTOR_2); }
	public final void paint() { paint(ZERO_VECTOR_2); }
	
}
