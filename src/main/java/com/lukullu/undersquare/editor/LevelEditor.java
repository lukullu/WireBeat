package com.lukullu.undersquare.editor;

import com.kilix.processing.ProcessingClass;
import com.lukullu.undersquare.UnderSquare;
import com.lukullu.undersquare.common.Constants;
import com.lukullu.undersquare.common.IO;
import com.lukullu.undersquare.common.ProgramState;
import com.lukullu.undersquare.common.data.Vector2;
import com.lukullu.undersquare.game.LevelMap;
import com.lukullu.undersquare.widgets.Grid;
import com.lukullu.undersquare.widgets.ScrollWidget;
import com.lukullu.undersquare.widgets.TextWidget;
import com.lukullu.undersquare.widgets.Widget;
import com.lukullu.undersquare.widgets.button.ButtonWidget;
import com.lukullu.undersquare.widgets.button.LoadMapButton;
import com.lukullu.undersquare.widgets.button.SaveMapButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

import static com.lukullu.undersquare.common.Constants.*;
import static com.lukullu.undersquare.common.msc.Translation.*;

public class LevelEditor extends ProgramState implements ProcessingClass {
	
	public Grid curGrid;
	public Widget saveButton;
	public Widget loadButton;
	public ScrollWidget fileList = new ScrollWidget(ZERO_VECTOR_2, ZERO_VECTOR_2);
	
	public Widget[] legend;
	
	public LevelMap mapToBeLoaded;
	public File fileToBeLoaded;
	
	@Override
	public void init() {
		
		setLevel(null,null);
		curGrid = new Grid(new Vector2(1000,1000),32,mapToBeLoaded, fileToBeLoaded);
		initWidgets();
		displayFiles(IO.collectFiles());
		
	}
	
	
	public void initWidgets() {
		
		saveButton = new SaveMapButton(
				new Vector2(
						scaleToScreenX(40),
						scaleToScreenY(1000)
				),
				new Vector2(
						scaleToScreenX(200),
						scaleToScreenY(40)
				),
				this
		);
		
		loadButton = new LoadMapButton(
				new Vector2(
						scaleToScreenX(40) + scaleToScreenX(200),
						scaleToScreenY(1000)
				),
				new Vector2(
						scaleToScreenX(200),
						scaleToScreenY(40)
				)
		);
		
		
		fileList = new ScrollWidget(
				new Vector2(
						scaleToScreenX(40),
						scaleToScreenY(20)
				),
				new Vector2(
						scaleToScreenX(400),
						scaleToScreenY(900)
				)
		);
		
		
		legend = new Widget[20];
		
		for(int i = 0; i < legend.length; i++){
			
			legend[i] = new TextWidget(
					new Vector2(
							getWidth() - scaleToScreenX(440),
							(i+1) * scaleToScreenY(40) + i * scaleToScreenY(10)
					),
					new Vector2(
							scaleToScreenX(400),
							scaleToScreenY(40)
					),
					editorLegendText[i]
			);
			
		}
		
	}

	public void displayFiles(Map<String, File> files){

		files.forEach((mapName,file) -> { fileList.addWidget(
				new ButtonWidget(
						ZERO_VECTOR_2,
						new Vector2(scaleToScreenX(400),scaleToScreenY(40)),
						mapName,
						() -> {
							try {
								UnderSquare.state.setLevel( GSON.fromJson(new FileReader(file),LevelMap.class),file);
							} catch (FileNotFoundException e) {}
						}
				)
		);});

	}
	
	
	@Override
	public void update(){
		
		background(40);
		
		curGrid.update();
		saveButton.update();
		loadButton.update();
		fileList.update();
		
	}
	@Override
	public void paint() {
		
		curGrid.paint();
		saveButton.paint(ZERO_VECTOR_2);
		loadButton.paint(ZERO_VECTOR_2);
		fileList.paint(ZERO_VECTOR_2);
		
		for(int i = 0; i < legend.length; i++){
			
			legend[i].paint(ZERO_VECTOR_2);
			
		}
		
	}

	@Override
	public void setLevel(LevelMap map, File file){

		mapToBeLoaded = map;
		fileToBeLoaded = file;

	}
	
}
