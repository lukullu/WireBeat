package com.lukullu.undersquare.common.statemashine;

public class StateMashine {

    public State state = State.IDLE;
    public State lastState = State.IDLE;

    public void update() { if(state != lastState) { lastState = state; } }

}
