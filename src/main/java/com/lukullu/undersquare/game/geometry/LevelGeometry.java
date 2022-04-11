package com.lukullu.undersquare.game.geometry;

import com.kilix.processing.ProcessingClass;
import com.lukullu.undersquare.common.data.Vector2;

import java.awt.*;

public class LevelGeometry implements ProcessingClass {
	
	public Vector2 pos;
	public Vector2 dim;
	
	public Color c = Color.black;
	public boolean visible;
	
	public LevelGeometry(Vector2 _pos, Vector2 _dim, Color _c, boolean _visible){
		init(_pos, _dim, _c, _visible);
	}
	
	public void init(Vector2 _pos, Vector2 _dim, Color _c, boolean _visible){ pos = _pos; dim = _dim; c = _c; visible = _visible;}
	
	public void paint() {
		if(visible){
			fill(c.getRGB());
			rect(pos.x, pos.y, dim.x, dim.y);
		}
	}
	
}
