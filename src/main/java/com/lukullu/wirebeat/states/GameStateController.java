package com.lukullu.wirebeat.states;

import com.lukullu.wirebeat.WireBeat;
import com.lukullu.wirebeat.states.gameplay.PlayState;

import static com.lukullu.wirebeat.common.Constants.*;

public class GameStateController extends GameState{

    GameState gameState;

    public GameStateController( GameState _gameState) { gameState = _gameState; }

    @Override
    public void init() { gameState.init(); }
    @Override
    public void update(){ gameState.update(); }
    @Override
    public void tick(){ gameState.tick(); }
    @Override
    public void paint(){ gameState.paint(); }
}
