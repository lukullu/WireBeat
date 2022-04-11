package com.lukullu.undersquare.game.item;

import com.lukullu.undersquare.UnderSquare;
import com.lukullu.undersquare.common.data.Vector2;
import com.lukullu.undersquare.game.entity.player.Player;
import com.lukullu.undersquare.game.entity.projectile.ProjectileConstructor;

import java.awt.*;

import static com.lukullu.undersquare.common.Constants.*;

public class Weapon extends Item {
	
	//name ; construction ; dim ; dmg ; initForce ; bbForce ; fireRate
	public static final Weapon PISTOL          = new Weapon("Pistol",          Color.gray, bulletConstruction, new Vector2(5,10),4, new Vector2(600000,600000), new Vector2(40000,40000), 3);
	public static final Weapon MASHINEGUN      = new Weapon("Mashinegun",      Color.gray, bulletConstruction, new Vector2(5,10),2, new Vector2(600000,600000), new Vector2(40000,40000), 10);
	public static final Weapon SHOTGUN         = new Weapon("Shotgun",         Color.gray, shellConstruction,  new Vector2(5,10),2, new Vector2(600000,600000), new Vector2(400000,400000), 1);
	public static final Weapon MASHINE_SHOTGUN = new Weapon("Mashine Shotgun", Color.gray, shellConstruction,  new Vector2(5,10),2, new Vector2(600000,600000), new Vector2(400000,400000), 100);
	
	public float fireRate;
	public ProjectileConstructor projectileConstructor;
	public Vector2 blowBackForce;
	public Vector2 initForce;
	public Vector2 dim;
	public int dmg;
	
	public Weapon(String _name, Color _color, ProjectileConstructor _projectileConstructor, Vector2 _dim, int _dmg, Vector2 _initForce, Vector2 _blowBackForce, float _fireRate){
		super(_name, _color);
		fireRate = _fireRate; projectileConstructor = _projectileConstructor; dmg = _dmg; blowBackForce = _blowBackForce; initForce = _initForce; dim = _dim;
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
