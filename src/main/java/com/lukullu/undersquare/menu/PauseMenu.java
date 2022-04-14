package com.lukullu.undersquare.menu;

import com.kilix.processing.ProcessingClass;
import com.lukullu.undersquare.UnderSquare;
import com.lukullu.undersquare.common.ProgramState;
import com.lukullu.undersquare.common.data.Vector2;
import com.lukullu.undersquare.editor.LevelEditor;
import com.lukullu.undersquare.widgets.Widget;
import com.lukullu.undersquare.widgets.button.ButtonWidget;

import static com.lukullu.undersquare.common.Constants.DEFAULT_TEXT_SIZE;
import static com.lukullu.undersquare.common.Constants.ROUNDEDCORNERS;
import static com.lukullu.undersquare.common.msc.Translation.scaleToScreenX;
import static com.lukullu.undersquare.common.msc.Translation.scaleToScreenY;

public class PauseMenu extends ProgramState implements ProcessingClass{

    ProgramState pausedState;

    public Widget resumeButton;
    public Widget exitButton;

    public PauseMenu(ProgramState _pausedState){
        pausedState = _pausedState;
    }

    @Override
    public void init(){
        UnderSquare.INSTANCE.cursor(ARROW);
        translate(pausedState.cam.rel.x,pausedState.cam.rel.y);
        initWidgets();
    }

    public void initWidgets(){


        resumeButton = new ButtonWidget(
                new Vector2(
                        scaleToScreenX(1690),
                        scaleToScreenY(1010)
                ),
                new Vector2(
                        scaleToScreenX(200),
                        scaleToScreenY(40)
                ),
                ROUNDEDCORNERS, ROUNDEDCORNERS, ROUNDEDCORNERS, ROUNDEDCORNERS,
                "Resume Game",
                DEFAULT_TEXT_SIZE,
                () -> { UnderSquare.changeStateWithoutInit(pausedState);}
        );

        exitButton = new ButtonWidget(
                new Vector2(
                        scaleToScreenX(1480),
                        scaleToScreenY(1010)
                ),
                new Vector2(
                        scaleToScreenX(200),
                        scaleToScreenY(40)
                ),
                ROUNDEDCORNERS, ROUNDEDCORNERS, ROUNDEDCORNERS, ROUNDEDCORNERS,
                "Exit Game",
                DEFAULT_TEXT_SIZE,
                () -> { UnderSquare.changeState(new MainMenu());}
        );
    }

    @Override
    public void update(){

        resumeButton.update();
        exitButton.update();
    }

    @Override
    public void paint(){

        resumeButton.paint();
        exitButton.paint();
    }

}
