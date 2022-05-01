package com.lukullu.wirebeat;

import com.kilix.processing.ExtendedPApplet;
import com.lukullu.wirebeat.common.data.Level;
import com.lukullu.wirebeat.states.GameState;
import com.lukullu.wirebeat.states.gameplay.PlayControllerState;
import com.lukullu.wirebeat.states.gameplay.PlayState;

import java.util.Timer;
import java.util.TimerTask;

public class WireBeat extends ExtendedPApplet{


        public static GameState state;
        public static WireBeat INSTANCE;

        //tick interval
        public static Timer timer = new Timer();
        public static TimerTask tickTask = new TimerTask() { @Override public void run() { state.tick(); } };

        public static float deltaTime = 0;
        public static float lastFrameTime = 0;

        public WireBeat() { INSTANCE = this; }

        public void setup() {

            //universalSetup
            frameRate(60);

            //temporary state setting because there is no menu yet
            state = new PlayControllerState(new PlayState(Level.HARDCODED_TEST_LEVEL2));
            state.init();

        }

        public void draw() {

            calcDeltaTime();


            state.update();


            state.paint();
            //println(deltaTime);

        }

        public void calcDeltaTime() {
            float t = millis();
            deltaTime = (t / 1000) - lastFrameTime;
            lastFrameTime = (t / 1000);
        }

}
