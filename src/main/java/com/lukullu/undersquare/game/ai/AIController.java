package com.lukullu.undersquare.game.ai;

import com.lukullu.undersquare.UnderSquare;
import com.lukullu.undersquare.common.ProgramState;
import com.lukullu.undersquare.common.statemashine.State;
import com.lukullu.undersquare.common.statemashine.StateMashine;
import com.lukullu.undersquare.game.GameHandler;
import com.lukullu.undersquare.game.entity.Entity;
import com.lukullu.undersquare.game.entity.player.Player;

import static com.lukullu.undersquare.UnderSquare.*;
import static com.lukullu.undersquare.common.Constants.*;


public abstract class AIController {

    public Player player;
    public Entity host;
    public StateMashine stateMashine = new StateMashine();

    float timer = 0;

    public AIController(Entity _host) { host = _host; player = getPlayerEntity(); }

    public void update() {

        if(timer > timeUntilNextBehaviorUpdate) {
            stateMashine.state = determineState();
            timer = 0;
        }timer += deltaTime;

        performStateBehavior();
    }

    public abstract State determineState();

    public void performStateBehavior() {
        switch (stateMashine.state){
            case IDLE   -> idleBehavior();
            case PATROL -> patrolBehavior();
            case PURSUE -> pursueBehavior();
            case RAM    -> ramBehavior();
            case HOLD   -> holdBehavior();
            case AIM    -> aimBehavior();
        }
    }

    public void idleBehavior  () {  }
    public void patrolBehavior() { stateMashine.state = State.IDLE; performStateBehavior(); }
    public void pursueBehavior() { stateMashine.state = State.IDLE; performStateBehavior(); }
    public void ramBehavior   () { stateMashine.state = State.IDLE; performStateBehavior(); }
    public void holdBehavior  () { stateMashine.state = State.IDLE; performStateBehavior(); }
    public void aimBehavior   () { stateMashine.state = State.IDLE; performStateBehavior(); }

    public static Player getPlayerEntity(){
        GameHandler game = UnderSquare.getGameHandler();
        for(int i = 0; i < game.entities.size(); i++){
            if(game.entities.get(i) instanceof Player) return (Player) game.entities.get(i);
        }
        return null;
    }

}
