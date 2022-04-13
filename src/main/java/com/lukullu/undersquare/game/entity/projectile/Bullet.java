package com.lukullu.undersquare.game.entity.projectile;

import com.lukullu.undersquare.common.data.Vector2;
import com.lukullu.undersquare.game.entity.Entity;

public class Bullet extends Projectile{
	
	public Bullet(Vector2 _pos, Vector2 _dim, Vector2 _initForce, int _dmg, float _ttl, float _mass, float _inertiaCoefficient, Entity _origin) {
		super(_pos, _dim, _initForce, _dmg, _ttl, _mass, _inertiaCoefficient, _origin);
	}
	
}