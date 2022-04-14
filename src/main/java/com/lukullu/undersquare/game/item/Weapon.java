package com.lukullu.undersquare.game.item;

import com.lukullu.undersquare.UnderSquare;
import com.lukullu.undersquare.common.data.Vector2;
import com.lukullu.undersquare.game.entity.player.Player;
import com.lukullu.undersquare.game.entity.projectile.ProjectileConstructor;

import java.awt.*;

import static com.lukullu.undersquare.common.Constants.*;

public class Weapon extends Item {
	
	//name ; construction ; dim ; dmg ; initForce ; bbForce ; fireRate
	public static final Weapon PISTOL          = new Weapon("Pistol",          Color.gray, bulletConstruction, new Vector2(5,10),4, new Vector2(600000,600000), new Vector2(40000,40000  ), 3  , 20, 10, 0.25f);
	public static final Weapon MACHINEGUN      = new Weapon("Machinegun",      Color.gray, bulletConstruction, new Vector2(5,10),2, new Vector2(600000,600000), new Vector2(40000,40000  ), 10 , 20, 10, 0.25f);
	public static final Weapon SHOTGUN         = new Weapon("Shotgun",         Color.gray, shellConstruction,  new Vector2(5,10),2, new Vector2(600000,600000), new Vector2(400000,400000), 1  , 20, 10, 0.25f);
	public static final Weapon FLAMETHROWER    = new Weapon("Flamethrower",    Color.gray, shellConstruction,  new Vector2(5,5 ),1, new Vector2(12000  ,12000  ), new Vector2(1000  ,1000  ), 100, 0.3f,0.2f , 0.005f);
	public static final Weapon QUADSHOT        = new Weapon("QuadShot",        Color.gray, quadConstruction,   new Vector2(5,5 ),4, new Vector2(600000,600000), ZERO_VECTOR_2, 3, 20, 10, 0.25f);

	public float fireRate;
	public ProjectileConstructor projectileConstructor;
	public Vector2 blowBackForce;
	public Vector2 initForce;
	public Vector2 dim;
	public float ttl;
	public float mass;
	public int dmg;
	public float inertiaCoefficient;
	
	public Weapon(String _name, Color _color, ProjectileConstructor _projectileConstructor, Vector2 _dim, int _dmg, Vector2 _initForce, Vector2 _blowBackForce, float _fireRate, float _ttl, float projectileMass, float _inertiaCoefficient){
		super(_name, _color);
		fireRate = _fireRate; projectileConstructor = _projectileConstructor; dmg = _dmg; blowBackForce = _blowBackForce; initForce = _initForce; dim = _dim; ttl = _ttl; mass = projectileMass; inertiaCoefficient = _inertiaCoefficient;
	}

	@Override
	public void onGet(Player target){
		if(target.weapon != null){
			//Weapon temp = target.weapon; TODO: drop weapon on picking up a new one
			//UnderSquare.getGameHandler().entities.add(new ItemBox(target.pos,target.dim,temp));
		}
		target.weapon = this;

	}
	
}
