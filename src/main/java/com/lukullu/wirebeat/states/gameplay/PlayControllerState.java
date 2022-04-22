package com.lukullu.wirebeat.states.gameplay;

import com.lukullu.wirebeat.WireBeat;
import com.lukullu.wirebeat.states.GameState;
import com.lukullu.wirebeat.states.GameStateController;

import static com.lukullu.wirebeat.common.Constants.MS_PER_TICK;
import static com.lukullu.wirebeat.common.Constants.MS_UNTIL_INITIAL_TICK;

public class PlayControllerState extends GameStateController {

    public PlayState playState;

    public PlayControllerState(PlayState _playState){ super((GameState) _playState); playState = _playState; }

    public PlayState getGameState() { return playState; }
    public void setGameState( PlayState _playState ) { playState = _playState; }

    @Override
    public void init(){
        //init ticker
        WireBeat.timer.scheduleAtFixedRate(WireBeat.tickTask,MS_UNTIL_INITIAL_TICK,MS_PER_TICK);
        //init gameState
        playState.init();
    }

}
