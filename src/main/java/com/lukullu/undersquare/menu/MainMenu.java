package com.lukullu.undersquare.menu;

import com.kilix.processing.ProcessingClass;
import com.lukullu.undersquare.UnderSquare;
import com.lukullu.undersquare.common.IO;
import com.lukullu.undersquare.common.ProgramState;
import com.lukullu.undersquare.common.data.Vector2;
import com.lukullu.undersquare.editor.LevelEditor;
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

    public Widget tempTitleScreen;
    public Widget loadButton;
    public Widget editorButton;
    public ScrollWidget fileList = new ScrollWidget(ZERO_VECTOR_2, ZERO_VECTOR_2,ROUNDEDCORNERS,ROUNDEDCORNERS,ROUNDEDCORNERS,ROUNDEDCORNERS,"Maps:");

    @Override
    public void init(){

        initWidgets();
        displayFiles(IO.collectFiles());
    }

    public void initWidgets() {

        loadButton = new LoadMapButton(
                new Vector2(
                        scaleToScreenX(30),
                        scaleToScreenY(1020)
                ),
                new Vector2(
                        scaleToScreenX(400),
                        scaleToScreenY(40)),
                ROUNDEDCORNERS,ROUNDEDCORNERS,0,0,
                DEFAULT_TEXT_SIZE);

        editorButton = new ButtonWidget(
                new Vector2(
                        scaleToScreenX(1690),
                        scaleToScreenY(1010)
                ),
                new Vector2(
                        scaleToScreenX(200),
                        scaleToScreenY(40)
                ),
                ROUNDEDCORNERS, ROUNDEDCORNERS, ROUNDEDCORNERS, ROUNDEDCORNERS,
                "Level Editor",
                DEFAULT_TEXT_SIZE,
                () -> { UnderSquare.changeState(new LevelEditor());}
        );

        fileList = new ScrollWidget(
                new Vector2(
                        scaleToScreenX(30),
                        scaleToScreenY(30)
                ),
                new Vector2(
                        scaleToScreenX(400),
                        scaleToScreenY(990)),
                ROUNDEDCORNERS,ROUNDEDCORNERS,ROUNDEDCORNERS,ROUNDEDCORNERS,
                "Level Select:");

        tempTitleScreen = new TextWidget(
                new Vector2(
                        scaleToScreenX(600),
                        scaleToScreenY(100)),
                new Vector2(
                        scaleToScreenX(1000),
                        scaleToScreenY(140)),
                ROUNDEDCORNERS,ROUNDEDCORNERS,ROUNDEDCORNERS,ROUNDEDCORNERS,
                "UNDERSQUARE", 110);


    }

    public void displayFiles(Map<String, File> files){

        files.forEach((mapName,file) -> { fileList.addWidget(
                new ButtonWidget(
                        ZERO_VECTOR_2,
                        new Vector2(scaleToScreenX(400),scaleToScreenY(40)),
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

        background(UI_BACKGROUND_COLOR.getRGB());

        loadButton.update();
        editorButton.update();
        fileList.update();

    }
    @Override
    public void paint() {

        loadButton.paint();
        editorButton.paint();
        fileList.paint();
        tempTitleScreen.paint();

    }

    @Override
    public void setLevel(LevelMap map, File file){

        mapToBeLoaded = map;

    }

}
