package com.lukullu.undersquare.editor;

import com.kilix.processing.ProcessingClass;
import com.lukullu.undersquare.UnderSquare;
import com.lukullu.undersquare.common.Constants;
import com.lukullu.undersquare.common.IO;
import com.lukullu.undersquare.common.ProgramState;
import com.lukullu.undersquare.common.data.Vector2;
import com.lukullu.undersquare.game.LevelMap;
import com.lukullu.undersquare.widgets.*;
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
	public Widget gridBackDrop;
	public ListWidget tileSettings;
	public ScrollWidget fileList = new ScrollWidget(ZERO_VECTOR_2, ZERO_VECTOR_2,ROUNDEDCORNERS,ROUNDEDCORNERS,ROUNDEDCORNERS,ROUNDEDCORNERS,"Maps:");
	
	public Widget[] legend;
	
	public LevelMap mapToBeLoaded;
	public File fileToBeLoaded;
	
	@Override
	public void init() {
		
		setLevel(null,null);
		curGrid = new Grid(new Vector2(scaleToScreenX(950),scaleToScreenY(950)),32,mapToBeLoaded, fileToBeLoaded);
		initWidgets();
		displayFiles(IO.collectFiles());
		
	}
	
	
	public void initWidgets() {

		gridBackDrop = new TextWidget(
						new Vector2(
								curGrid.offsetX - scaleToScreenX(25),
								curGrid.offsetY - scaleToScreenY(25)
						),
						new Vector2(
								curGrid.dim.x + scaleToScreenX(50),
								curGrid.dim.y + scaleToScreenY(50)),
					ROUNDEDCORNERS,ROUNDEDCORNERS,ROUNDEDCORNERS,ROUNDEDCORNERS,
					"", DEFAULT_TEXT_SIZE);

		saveButton = new SaveMapButton(
				new Vector2(
						scaleToScreenX(30),
						scaleToScreenY(1000)
				),
				new Vector2(
						scaleToScreenX(200),
						scaleToScreenY(40)),
				ROUNDEDCORNERS,0,0,0,
				DEFAULT_TEXT_SIZE,
				this);
		
		loadButton = new LoadMapButton(
				new Vector2(
						scaleToScreenX(30) + scaleToScreenX(200),
						scaleToScreenY(1000)
				),
				new Vector2(
						scaleToScreenX(200),
						scaleToScreenY(40)),
				0,ROUNDEDCORNERS,0,0,
				DEFAULT_TEXT_SIZE);
		
		
		fileList = new ScrollWidget(
				new Vector2(
						scaleToScreenX(30),
						scaleToScreenY(150)
				),
				new Vector2(
						scaleToScreenX(400),
						scaleToScreenY(870)),
				ROUNDEDCORNERS,ROUNDEDCORNERS,ROUNDEDCORNERS,ROUNDEDCORNERS,
				"Maps:"
		);

		tileSettings = new ListWidget(
				new Vector2(
						scaleToScreenX(1490),
						scaleToScreenY(400)
				),
				new Vector2(
						scaleToScreenX(400),
						scaleToScreenY(640)),
				ROUNDEDCORNERS,ROUNDEDCORNERS,ROUNDEDCORNERS,ROUNDEDCORNERS
		);

		
	}

	public void displayFiles(Map<String, File> files){

		files.forEach((mapName,file) -> { fileList.addWidget(
				new ButtonWidget(
						new Vector2(scaleToScreenX(10),0),
						new Vector2(scaleToScreenX(380),scaleToScreenY(40)),
						ROUNDEDCORNERS,ROUNDEDCORNERS,ROUNDEDCORNERS,ROUNDEDCORNERS,
						mapName, DEFAULT_TEXT_SIZE,
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
		
		curGrid.update();
		saveButton.update();
		loadButton.update();
		fileList.update();
		tileSettings.update();
		
	}
	@Override
	public void paint() {

		background(UI_BACKGROUND_COLOR.getRGB());

		gridBackDrop.paint();
		curGrid.paint();
		fileList.paint(ZERO_VECTOR_2);
		saveButton.paint(ZERO_VECTOR_2);
		loadButton.paint(ZERO_VECTOR_2);
		tileSettings.paint();
		
	}

	@Override
	public void setLevel(LevelMap map, File file){

		mapToBeLoaded = map;
		fileToBeLoaded = file;

	}
	
}
