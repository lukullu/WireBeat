package com.lukullu.undersquare.widgets.button;

import com.kilix.processing.ProcessingClass;
import com.lukullu.undersquare.UnderSquare;
import com.lukullu.undersquare.common.IO;
import com.lukullu.undersquare.common.data.Vector2;
import com.lukullu.undersquare.editor.LevelEditor;

import java.io.PrintWriter;

public class SaveMapButton extends ButtonWidget implements ProcessingClass {
	
	public LevelEditor state;
	
	public SaveMapButton(Vector2 _pos, Vector2 _dim, LevelEditor _state){
		super(_pos, _dim, "Save");
		state = _state;
	}
	
	
	
	@Override
	public void onClick(){

		IO.saveLevelMapAsJson(UnderSquare.getLevelEditor().curGrid.map,UnderSquare.getLevelEditor().curGrid.file);
		
		if (UnderSquare.getLevelEditor() != null) {
			UnderSquare.getLevelEditor().displayFiles(IO.collectFiles());
		};
		
	}
	
}
