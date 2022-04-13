package com.lukullu.undersquare.game.entity;

import com.kilix.processing.ProcessingClass;
import com.lukullu.undersquare.common.data.Direction;
import com.lukullu.undersquare.common.data.Vector2;
import com.lukullu.undersquare.common.msc.Debug;
import com.lukullu.undersquare.common.msc.Filter;
import com.lukullu.undersquare.game.GameHandler;
import com.lukullu.undersquare.game.entity.player.Player;
import com.lukullu.undersquare.game.entity.projectile.Projectile;
import com.lukullu.undersquare.game.item.Weapon;

import java.awt.*;
import java.util.ArrayList;

import static com.lukullu.undersquare.UnderSquare.*;
import static com.lukullu.undersquare.common.Constants.*;
import static com.lukullu.undersquare.common.collision.Collision.*;

public class Entity implements ProcessingClass {
	
	public boolean isActive = false;
	public boolean isVisible = false;
	
	public int HP = 1;
	public int dmg;
	
	public Vector2 pos;
	public Vector2 dim;
	public Vector2 deltaPos;

	public Vector2[] prevPos = new Vector2[AFTERIMAGE_LENGTH];
	
	public Vector2 force = new Vector2(0,0);
	public Direction[] collisionDirection = { Direction.NONE, Direction.NONE };
	public ArrayList<Entity> entityColliders;
	
	public float mass = 10;
	
	public Color c = Color.black;
	
	public Entity(Vector2 _pos, Vector2 _dim){
		init(_pos, _dim);
	}
	
	public void init(Vector2 _pos, Vector2 _dim){
		
		isActive  = true;
		isVisible = true;
		pos  = _pos;
		dim  = _dim;
		
	}
	
	public void update(){
		simulate();
		behavior();
		updatePreviousPositions();
	}

	public void updatePreviousPositions(){

		Vector2[] prevPrevPos = prevPos; //xD
		for(int i = prevPos.length-1; i > 0; i--){
			prevPos[i] = prevPos[i-1];
		}
		prevPos[0] = pos;
	}

	public void paintAfterImages(){

		for(int i = 0; i < prevPos.length; i++){
			if(prevPos[i] != null)
				paint(prevPos[i],125-(125f/prevPos.length)*i, false);
		}
	}
	
	public void collide(){
		
		for(int i = 0; i < entityColliders.size(); i++){
			takeDMG(entityColliders.get(i).dmg);
			if (entityColliders.get(i) instanceof Projectile) takeKnockback(entityColliders.get(i).force);
			else takeKnockback(new Vector2(  -entityColliders.get(i).force.x , -entityColliders.get(i).force.y));
		}
		
	}
	
	//default behavior
	public void behavior() {}
	public void takeDMG(int amount){ HP -= amount; if(HP <= 0){ onDeath(); } }
	public void takeKnockback(Vector2 amount){ force.x += amount.x * innertiaCoefficient;  force.y += amount.y *innertiaCoefficient;}
	public void onDeath(){ if(state instanceof GameHandler) { GameHandler game = (GameHandler) state; game.entitiesToDie.add(this);}}
	
	public void entityCollide() {
		entityColliders = entityCollision(this);
		if(entityColliders.size() > 0){ collide(); }
	}
	
	//default physics
	public void simulate(){
		deltaPos = new Vector2(0,0);
		Vector2 vel;
		Vector2 acc;
		
		if(collisionDirection[0] == Direction.RIGHT && force.x > 0) { force = new Vector2(0,force.y); }
		if(collisionDirection[0] == Direction.LEFT  && force.x < 0) { force = new Vector2(0,force.y); }
		if(collisionDirection[1] == Direction.DOWN  && force.y > 0) { force = new Vector2(force.x,0); }
		if(collisionDirection[1] == Direction.UP    && force.y < 0) { force = new Vector2(force.x,0); }
		
		// force - force * coefficientOfFriction = correctedForce
		force = new Vector2(force.x - force.x * coefficientOfFriction, force.y - force.y * coefficientOfFriction);
		// force / mass = acceleration;
		acc = new Vector2(force.x / mass, force.y / mass);
		// acceleration * deltaTime = velocity;
		vel = new Vector2(acc.x * deltaTime, acc.y * deltaTime);
		// velocity * deltaTime = deltaPosition;
		deltaPos = new Vector2(vel.x * deltaTime, vel.y * deltaTime);
		
		if(deltaPos.x < 0.001 && deltaPos.x > -0.001){ deltaPos.x = 0; }
		if(deltaPos.y < 0.001 && deltaPos.y > -0.001){ deltaPos.y = 0; }
		
		//collisionDetection
		deltaPos = rayCast(pos,dim,deltaPos);
		collisionDirection = calcCollisionAxis(new Vector2(pos.x + deltaPos.x,pos.y + deltaPos.y), dim);
		
		pos = new Vector2(pos.x + deltaPos.x, pos.y + deltaPos.y);
		
	}
	
	//default paint
	public void paint( Vector2 _pos, float opacity, boolean stroke){
		if (stroke) stroke(1); else noStroke();
		fill(c.getRGB(),opacity);
		rect(_pos.x,_pos.y,dim.x,dim.y);
		
	}
	
	public void fireWeapon(Entity origin, Weapon weapon, Direction dir) {}
	
	
	
}