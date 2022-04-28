package com.lukullu.wirebeat.states.gameplay;

import com.kilix.processing.ProcessingClass;
import com.lukullu.wirebeat.common.data.Level;
import com.lukullu.wirebeat.common.data.Vertex;
import com.lukullu.wirebeat.entities.Player;
import com.lukullu.wirebeat.states.GameState;

import java.awt.*;
import java.util.Arrays;

public class PlayState extends GameState implements ProcessingClass {

    Level level;

    public PlayState( Level _level ) { level = _level; }

    @Override
    public void tick(){
        //Toolkit.getDefaultToolkit().beep();
        level.player.tick();
    }

    @Override
    public void update(){
        level.player.update();
    }

    @Override
    public void paint() {

        level.player.paint(level);

    }



}
