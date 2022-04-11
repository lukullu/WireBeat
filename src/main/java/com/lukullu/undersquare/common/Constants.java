package com.lukullu.undersquare.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lukullu.undersquare.common.data.Vector2;
import com.lukullu.undersquare.game.entity.projectile.Bullet;
import com.lukullu.undersquare.game.entity.projectile.ProjectileConstructor;
import com.lukullu.undersquare.game.entity.projectile.Shell;

import java.awt.*;
import java.io.File;

public class Constants {

	// TODO before handover: WTH is this:
	public static final Vector2 ZERO_VECTOR_2 = new Vector2(0, 0);
	
	//entities
	public static final float appliedForce = 45000f;
	public static final float appliedShiftForce = 15000f;
	public static final float coefficientOfFriction = 0.08f;
	
	//player
	public static final int playerDimensions = 40;
	public static final int playerHP = 100;
	public static final int playerContactDMG = 1;

	//itemBox
	public static final int itemBoxDimensions = 30;

	//AI
	public static final float timeUntilNextBehaviorUpdate = 2;

	//enemy
	public final static int enemyDimensions = 40;
	
	public static final Vector2 bouncerStartingForce = new Vector2(300000, 300000);
	public static final int bouncerHP = 10;
	public static final int bouncerContactDMG = 5;

	//Persuer
	public static final int persuerDetectionRange = 1000;
	public static final int persuerAttackRange = 200;

	//projectiles
	
	public static int prjTTL = 20;

	//colors
	public static final Color wallGridColor = Color.black;
	public static final Color emptyGridColor = Color.white;
	public static final Color errorGridColor = Color.orange;
	public static final Color textColor = Color.black;
	public static final Color playerGridColor = Color.red;
	public static final Color enemyGridColor = Color.green.darker();
	public static final Color itemBoxGridColor = Color.blue.darker();
	public static final Color backgroundColor = Color.gray.darker().darker();
	public static final Color prjColor = Color.black;
	public static final Color enemyColor = Color.black;
	
	//mapGrid
	public static int mapGridSize = 80; // TODO: create way for this to be loaded with a map

	//LevelEdior
	public static final int defaultGridSize = 32;

	//rayCast
	public static final float rayCastAccuracy = 0.2f;
	
	//collision
	public static final int checkRange = 1;
	public static final float scrollScale = 10;
	
	//camera
	public static Vector2 cameraPosition = new Vector2(600,300);
	public static Vector2 cameraDimensions = new Vector2(720,480);
	
	public static final String[] editorLegendText =
			{
					"'#' ... Wall     ",
					"'   ' ... Empty   ",
					"'P' ... Player   ",
					"'B' ... Bouncer  ",
					"'I' ... ItemBox  ",
					"'X' ... tbc  ",
					"'X' ... tbc  ",
					"'X' ... tbc  ",
					"'X' ... tbc  ",
					"'X' ... tbc  ",
					"'X' ... tbc  ",
					"'X' ... tbc  ",
					"'X' ... tbc  ",
					"'X' ... tbc  ",
					"'X' ... tbc  ",
					"'X' ... tbc  ",
					"'X' ... tbc  ",
					"'X' ... tbc  ",
					"'X' ... tbc  ",
					"'X' ... tbc  "
			};

	//prj
	
	//Pellets
	public static final int pelletSpreadForce = 100000;
	
	//projectile constructions
	public static final ProjectileConstructor bulletConstruction = Bullet::new;
	public static final ProjectileConstructor shellConstruction = Shell::new;
	
	//weapons
	public static final float innertiaCoefficient = 0.25f;
	
	// I/O
	public static final Gson GSON = new GsonBuilder().setLenient().setPrettyPrinting().create();
	public static final File MAPS_BASE_DIR = new File("./data/maps/");
	
}
