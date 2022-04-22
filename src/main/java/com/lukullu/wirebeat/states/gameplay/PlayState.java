package com.lukullu.wirebeat.states.gameplay;

import com.kilix.processing.ProcessingClass;
import com.lukullu.wirebeat.common.data.Level;
import com.lukullu.wirebeat.common.data.Vertex;
import com.lukullu.wirebeat.entities.Player;
import com.lukullu.wirebeat.states.GameState;

public class PlayState extends GameState implements ProcessingClass {

    Level level;

    public PlayState( Level _level ) { level = _level; }

    @Override
    public void paint() { paintWireFrame(); }

    public void paintWireFrame() {

        for(int i = 0; i < level.vertices.size(); i++){

            Vertex v1 = level.vertices.get(i);
            Vertex v2 = level.vertices.get(i).neighbors[1];

            ellipseMode(CENTER);
            ellipse(v1.pos.x,v1.pos.y,4,4);
            stroke(1);
            line(v1.pos.x,v1.pos.y,v2.pos.x,v2.pos.y);

        }

        level.player.paint();

    }

    public void paintVisibleOnly() {}

}
