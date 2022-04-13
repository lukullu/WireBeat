package com.lukullu.undersquare.game;

import com.kilix.processing.ProcessingClass;
import com.lukullu.undersquare.UnderSquare;
import com.lukullu.undersquare.common.IO;
import com.lukullu.undersquare.common.ProgramState;
import com.lukullu.undersquare.common.data.Vector2;
import com.lukullu.undersquare.game.camera.Camera;
import com.lukullu.undersquare.game.entity.Entity;
import com.lukullu.undersquare.game.geometry.LevelGeometry;

import java.util.ArrayList;

import static com.lukullu.undersquare.common.msc.Translation.*;
import static com.lukullu.undersquare.common.Constants.*;

public class GameHandler extends ProgramState implements ProcessingClass {
	
	public LevelGeometry[][] mapGeometry;
	public ArrayList<Entity> entities = new ArrayList<>();
	public LevelMap levelMap;
	public ArrayList<Entity> entitiesToDie = new ArrayList<>();

	public GameHandler(LevelMap _levelMap){levelMap = _levelMap; levelMap.collisionData = IO.convertMapDataToCollisionData(levelMap.mapData);}

	public void init() {
		
		cam = new Camera(new Vector2(scaleToScreenX((int)cameraPosition.x),scaleToScreenY((int)cameraPosition.y)),new Vector2(scaleToScreenX((int)cameraDimensions.x),scaleToScreenY((int)cameraDimensions.y)));
		mapGeometry = IO.createMapElements(levelMap.mapData,IO.convertMapDataToCollisionData(levelMap.mapData), levelMap);
		
	}
	
	
	
	public void update() {
		
		UnderSquare.INSTANCE.calcDeltaTime();
		
		for(int i = 0; i < entities.size(); i++){
			entities.get(i).update();
		}
		
		for(int i = 0; i < entities.size(); i++){
			entities.get(i).entityCollide();
		}
		
		killEntities();
		
	}
	
	public void killEntities() {
		
		for(int i = 0; i < entitiesToDie.size(); i++){
			entities.remove(entitiesToDie.get(i));
		}
		
	}
	
	public void paint() {

		background(backgroundColor.getRGB());

		for(int i = 0; i < mapGeometry.length; i++){
			for(int j = 0; j < mapGeometry[0].length; j++){
				if(mapGeometry[i][j] != null) mapGeometry[i][j].paint();
			}
		}
		
		for(int i = 0; i < entities.size(); i++){
			entities.get(i).paintAfterImages();
			entities.get(i).paint(entities.get(i).pos,255, true);
		}
		
		cam.paintHUD();
	}
	
}
