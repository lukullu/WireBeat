package com.lukullu.undersquare.game.ai.enemy;

import com.lukullu.undersquare.common.statemashine.*;
import com.lukullu.undersquare.common.msc.*;
import com.lukullu.undersquare.game.ai.AIController;
import com.lukullu.undersquare.game.entity.Entity;
import static com.lukullu.undersquare.common.Constants.*;

public class PersuerAI extends AIController {

    PersuerAI(Entity _host){ super(_host); }

    @Override
    public State determineState() {

        State determinedState = State.IDLE;

        /**
         * if you are outside its detection radius it enters patrol state
         * if you are inside  its detection radius when it's patrolling it will start pursuing you TODO: implement line of sight via ray-cast
         * if you are inside  its attack radius when it's pursuing it will try to RAM you until the next behavior-update is called
         * if you are outside its detection radius when it's pursuing or ramming you it will start patrolling again
         */

        if(stateMashine.state == State.IDLE || stateMashine.state == State.PATROL )
            if(Geometry.getDistance(host,player) > persuerDetectionRange){ determinedState = State.PATROL; } else { determinedState = State.PURSUE; }

        if(stateMashine.state == State.PURSUE)
            if(Geometry.getDistance(host,player) < persuerAttackRange ){ determinedState = State.RAM; } else { determinedState = State.PURSUE; }

        if(stateMashine.state == State.RAM || stateMashine.state == State.PURSUE)
            if(Geometry.getDistance(host,player) < persuerDetectionRange ){ determinedState = State.PURSUE; } else { determinedState = State.PATROL; }

        return determinedState;
    }

    @Override
    public void patrolBehavior(){}
    @Override
    public void pursueBehavior(){}
    @Override
    public void ramBehavior(){}


}
