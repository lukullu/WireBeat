package com.lukullu.undersquare.game.entity.player;

import com.lukullu.undersquare.UnderSquare;
import com.lukullu.undersquare.common.KeyHandler;
import com.lukullu.undersquare.common.data.Direction;
import com.lukullu.undersquare.common.data.Vector2;
import com.lukullu.undersquare.common.msc.Debug;
import com.lukullu.undersquare.common.msc.Filter;
import com.lukullu.undersquare.game.entity.Entity;
import com.lukullu.undersquare.game.item.Weapon;

import static com.lukullu.undersquare.common.Constants.*;
import static com.lukullu.undersquare.common.collision.Collision.entityCollision;

public class Player extends Entity {
	
	public Weapon weapon = Weapon.MASHINEGUN;
	float timeSinceLastShot = 0;
	
	public Player(Vector2 _pos, Vector2 _dim) {
		super(_pos, _dim);
		UnderSquare.state.cam.setUp(this);
		HP = playerHP;
		dmg = playerContactDMG;
	}

	@Override
	public void onDeath() {}

	@Override
	public void entityCollide() {

		entityColliders = entityCollision(this);
		entityColliders = Filter.filterOwnPrj(entityColliders,this);
		if(entityColliders.size() > 0){ collide(); }

	}

	public void behavior() {

		Debug.displayTemp(""+HP);

		if (UnderSquare.state.cam != null) UnderSquare.state.cam.update(this,deltaPos);
		
		float fx = 0;
		float fy = 0;
		
		if(KeyHandler.a && KeyHandler.shift){ fx -= appliedShiftForce;} else
		if(KeyHandler.a){ fx -= appliedForce;}
		
		if(KeyHandler.d && KeyHandler.shift){ fx += appliedShiftForce;} else
		if(KeyHandler.d){ fx += appliedForce;}
		
		if(KeyHandler.w && KeyHandler.shift){ fy -= appliedShiftForce;} else
		if(KeyHandler.w){ fy -= appliedForce;}
		
		if(KeyHandler.s && KeyHandler.shift){ fy += appliedShiftForce;} else
		if(KeyHandler.s){ fy += appliedForce;}
		
		force = new Vector2(fx == 0 ? force.x : force.x + fx, fy == 0 ? force.y : force.y + fy);
		
		if(weapon != null){
			if(timeSinceLastShot > 1/weapon.fireRate){
				if(KeyHandler.up || KeyHandler.down || KeyHandler.left || KeyHandler.right) {
					
					Direction dir = Direction.NONE;
					
					if(KeyHandler.up){ dir = Direction.UP;}
					if(KeyHandler.down){ dir = Direction.DOWN;}
					
					if(KeyHandler.right){ dir = Direction.LEFT;}
					if(KeyHandler.left){ dir = Direction.RIGHT;}
					
					fireWeapon(this,weapon,dir);
					timeSinceLastShot = 0;
				}
			}
		}
		timeSinceLastShot += UnderSquare.deltaTime;
	}
	
	public void fireWeapon(Entity origin, Weapon weapon, Direction dir) {
		Vector2 startingForce = new Vector2(0,0);
		Vector2 prjDim = weapon.dim;
		
		Vector2 directedBlowBackForce = new Vector2(0,0);
		
		if(dir == Direction.LEFT) {startingForce.x += weapon.initForce.x; directedBlowBackForce.x += weapon.blowBackForce.x; prjDim = new Vector2(weapon.dim.y,weapon.dim.x);}
		if(dir == Direction.RIGHT){startingForce.x -= weapon.initForce.x; directedBlowBackForce.x -= weapon.blowBackForce.x; prjDim = new Vector2(weapon.dim.y,weapon.dim.x);}
		if(dir == Direction.UP)   {startingForce.y -= weapon.initForce.y; directedBlowBackForce.y -= weapon.blowBackForce.x; }
		if(dir == Direction.DOWN) {startingForce.y += weapon.initForce.y; directedBlowBackForce.y += weapon.blowBackForce.x; }
		
		startingForce.x += origin.force.x * 0.25;
		startingForce.y += origin.force.y * 0.25;
		
		UnderSquare.getGameHandler().entities.add( weapon.projectileConstructor.construct(
						new Vector2(
								origin.pos.x + origin.dim.x/2 - prjDim.x/2,
								origin.pos.y + origin.dim.y/2 - prjDim.y/2
						),
						prjDim,
						startingForce,
						weapon.dmg,
						origin
				)
		);
		
		origin.force = new Vector2(origin.force.x - directedBlowBackForce.x, origin.force.y - directedBlowBackForce.y);
		
	}
}
