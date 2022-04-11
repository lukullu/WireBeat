package com.lukullu.undersquare.game;

import com.kilix.processing.ProcessingClass;

public class LevelMap implements ProcessingClass {

	//women and children
	public String name;
	public String author;
	public int gridSize;
	public float packHierarchy;
	public char[][] mapData;
	public int[] itemBoxFillData;
	public int[] enemyFillData;
	public int[] senderReceiverPairingIDs;

	//not being saved from certain death
	public transient boolean[][] collisionData;

}
