package com.lukullu.undersquare.common;

import com.lukullu.undersquare.editor.LevelEditor;
import com.lukullu.undersquare.game.GameHandler;
import com.lukullu.undersquare.game.LevelMap;
import com.lukullu.undersquare.game.camera.Camera;
import com.lukullu.undersquare.menu.MainMenu;

import java.io.File;

public class ProgramState {
	
	public Camera cam;
	
	public void init() {}
	public void update() {}
	public void paint() {}

	public void setLevel(LevelMap map, File file){}

}