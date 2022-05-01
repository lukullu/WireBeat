package com.lukullu.wirebeat.entities;

import com.lukullu.wirebeat.common.data.Direction;
import com.lukullu.wirebeat.common.data.Vector2;
import com.lukullu.wirebeat.common.msc.Utils;

import static com.lukullu.wirebeat.WireBeat.deltaTime;
import static com.lukullu.wirebeat.common.Constants.ANIMATION_TIME;
import static com.lukullu.wirebeat.common.Constants.TILE_SIZE;

public class Player extends Camera {


    public Direction facing;
    public Direction targetDirection;
    public float     targetRotation;
    public Vector2   targetPosition = pos;
    public float     deltaRotation = 0;
    public Vector2   deltaPosition = new Vector2(0,0);

    public Player(Vector2 _pos, Direction _facing) {
        super(_pos);
        facing = _facing;
        if(facing.equals(Direction.DOWN   )) rotation = HALF_PI ; else
        if(facing.equals(Direction.LEFT )) rotation = PI        ; else
        if(facing.equals(Direction.RIGHT)) rotation = 0         ; else
        if(facing.equals(Direction.UP )) rotation = PI + HALF_PI;
    }

    @Override
    public void update(){
        adjustRotation();
        adjustPosition();
        super.update();

    }

    public void adjustRotation(){
        //TODO: micro-improvements
        if(!(targetRotation >= rotation - PI/24 && targetRotation <= rotation + PI/24)) rotation += (deltaRotation / ANIMATION_TIME) * deltaTime; else{
            rotation = targetRotation; facing = targetDirection;
        }
    }

    public void adjustPosition(){
        //TODO: micro-improvements
        if(!(targetPosition.x >= pos.x - 0.3 && targetPosition.y >= pos.y - 0.3 && targetPosition.x <= pos.x + 0.3 && targetPosition.y <= pos.y + 0.3)) {
            pos.x += (deltaPosition.x / ANIMATION_TIME) * deltaTime; pos.y += (deltaPosition.y / ANIMATION_TIME) * deltaTime;
        } else {
            pos = targetPosition;
        }

    }

    @Override
    public void tick(){
        interpretInput();
        super.tick();
    }

    public void interpretInput(){

        if(getKeyPressed()){
            switch (getKey()){
                case 'e' -> rotateRight();
                case 'q' -> rotateLeft();
                case 'w' -> move(0 ,-1 );
                case 'a' -> move(-1 ,0 );
                case 's' -> move(0 ,1);
                case 'd' -> move(1,0 );
            }
        }
    }

    public void move( int x, int y ){

        //TODO: implement collision

        Vector2 deltaTargetPos = new Vector2(0,0);

        switch (facing){
            case UP -> {
                deltaTargetPos.x += x * TILE_SIZE;
                deltaTargetPos.y += y * TILE_SIZE;
            }
            case RIGHT -> {
                deltaTargetPos.x -= y * TILE_SIZE;
                deltaTargetPos.y += x * TILE_SIZE;
            }
            case LEFT -> {
                deltaTargetPos.x += y * TILE_SIZE;
                deltaTargetPos.y -= x * TILE_SIZE;
            }
            case DOWN -> {
                deltaTargetPos.x -= x * TILE_SIZE;
                deltaTargetPos.y -= y * TILE_SIZE;
            }
            default -> {}
        }

        targetPosition = new Vector2( pos.x + deltaTargetPos.x, pos.y + deltaTargetPos.y);
        deltaPosition = deltaTargetPos;
        println(x + "|" + y);
        println(deltaTargetPos);
    }

    public void rotateLeft(){

        switch (facing){
            case UP -> {
                targetDirection = Direction.LEFT;
            }
            case RIGHT -> {
                targetDirection = Direction.UP;
            }
            case LEFT -> {
                targetDirection = Direction.DOWN;
            }
            case DOWN -> {
                targetDirection = Direction.RIGHT;
            }
            default -> {}

        }

        if(targetDirection.equals(Direction.DOWN   )) targetRotation = HALF_PI     ; else
        if(targetDirection.equals(Direction.LEFT )) targetRotation = PI          ; else
        if(targetDirection.equals(Direction.RIGHT)) targetRotation = 0           ; else
        if(targetDirection.equals(Direction.UP )) targetRotation = PI + HALF_PI;

        deltaRotation = -Utils.deltaAngleBetweenVectors( Vector2.createFromAngleAndLength(rotation,1), Vector2.createFromAngleAndLength(targetRotation,1));

    }

    public void rotateRight(){

        switch (facing){
            case UP -> {
                targetDirection = Direction.RIGHT;
            }
            case RIGHT -> {
                targetDirection = Direction.DOWN;
            }
            case LEFT -> {
                targetDirection = Direction.UP;
            }
            case DOWN -> {
                targetDirection = Direction.LEFT;
            }
            default -> {}

        }

        if(targetDirection.equals(Direction.DOWN   )) targetRotation = HALF_PI     ; else
        if(targetDirection.equals(Direction.LEFT )) targetRotation = PI          ; else
        if(targetDirection.equals(Direction.RIGHT)) targetRotation = 0           ; else
        if(targetDirection.equals(Direction.UP )) targetRotation = PI + HALF_PI;

        deltaRotation = Utils.deltaAngleBetweenVectors( Vector2.createFromAngleAndLength(rotation,1), Vector2.createFromAngleAndLength(targetRotation,1));

    }


}
