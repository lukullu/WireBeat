package com.lukullu.undersquare.game.camera;

import com.kilix.processing.ProcessingClass;
import com.lukullu.undersquare.common.data.Vector2;
import com.lukullu.undersquare.common.msc.Debug;
import com.lukullu.undersquare.game.entity.Entity;

public class Camera implements ProcessingClass {
	
	public Vector2 pos;
	public Vector2 dim;
	
	public Vector2 rel = new Vector2(0,0);
	
	public Camera(Vector2 _pos, Vector2 _dim){pos = _pos; dim = _dim;}
	
	public void update(Entity e,Vector2 delta){
		
		if(e.pos.x + e.dim.x > pos.x + dim.x || e.pos.x < pos.x ){ rel.x += delta.x; pos = new Vector2(pos.x + delta.x, pos.y);}
		if(e.pos.y + e.dim.y > pos.y + dim.y || e.pos.y < pos.y ){ rel.y += delta.y; pos = new Vector2(pos.x, pos.y + delta.y);}
		
		translate(Math.round(-rel.x), Math.round(-rel.y));
		
	}
	
	public void setUp(Entity e) {
		
		pos = new Vector2(pos.x + e.pos.x - getWidth()/2, pos.y + e.pos.y - getHeight()/2);
		rel = new Vector2(e.pos.x - getWidth()/2,e.pos.y - getHeight()/2);
		
	}
	
	
	public void paintHUD(){
		//debug!
		Debug.paint(rel);
	}
	
	
}

