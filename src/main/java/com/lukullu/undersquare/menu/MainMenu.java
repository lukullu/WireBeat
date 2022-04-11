package com.lukullu.undersquare.menu;

import com.kilix.processing.ProcessingClass;
import com.lukullu.undersquare.UnderSquare;
import com.lukullu.undersquare.common.IO;
import com.lukullu.undersquare.common.ProgramState;
import com.lukullu.undersquare.common.data.Vector2;
import com.lukullu.undersquare.game.LevelMap;
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
import static com.lukullu.undersquare.common.msc.Translation.scaleToScreenX;
import static com.lukullu.undersquare.common.msc.Translation.scaleToScreenY;

public class MainMenu extends ProgramState implements ProcessingClass {

    public LevelMap mapToBeLoaded;

    public Widget loadButton;
    public ScrollWidget fileList = new ScrollWidget(ZERO_VECTOR_2, ZERO_VECTOR_2);

    @Override
    public void init(){

        initWidgets();
        displayFiles(IO.collectFiles());
    }

    public void initWidgets() {

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

        loadButton.update();
        fileList.update();

    }
    @Override
    public void paint() {

        loadButton.paint(ZERO_VECTOR_2);
        fileList.paint(ZERO_VECTOR_2);

    }

    @Override
    public void setLevel(LevelMap map, File file){

        mapToBeLoaded = map;

    }

}
