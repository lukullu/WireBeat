package com.lukullu.undersquare.widgets.button;


import com.lukullu.undersquare.UnderSquare;
import com.lukullu.undersquare.common.data.Vector2;
import com.lukullu.undersquare.editor.LevelEditor;
import com.lukullu.undersquare.game.GameHandler;
import com.lukullu.undersquare.menu.MainMenu;
import com.lukullu.undersquare.widgets.Grid;

import java.io.BufferedReader;
import java.util.List;

public class LoadMapButton extends ButtonWidget {
	
	public LoadMapButton(Vector2 _pos, Vector2 _dim){
		super(_pos, _dim, "Load");

	}

	@Override
	public void onClick() {

		if(UnderSquare.state instanceof LevelEditor) {
			assert UnderSquare.getLevelEditor() != null;
			if (UnderSquare.getLevelEditor().mapToBeLoaded != null)
				UnderSquare.getLevelEditor().curGrid = new Grid(new Vector2(1000, 1000), UnderSquare.getLevelEditor().mapToBeLoaded.mapData.length, UnderSquare.getLevelEditor().mapToBeLoaded, UnderSquare.getLevelEditor().fileToBeLoaded);
		} else
		if(UnderSquare.state instanceof MainMenu){
			assert UnderSquare.getMainMenu() != null;
			if (UnderSquare.getMainMenu().mapToBeLoaded != null)
				UnderSquare.changeState(new GameHandler(UnderSquare.getMainMenu().mapToBeLoaded));

		}
	}
	
}
