package com.lukullu.undersquare.game.item;

import com.lukullu.undersquare.common.data.Vector2;
import com.lukullu.undersquare.common.msc.Debug;
import com.lukullu.undersquare.game.entity.Entity;
import com.lukullu.undersquare.game.entity.player.Player;

public class ItemBox extends Entity {

    Item item;

    public ItemBox(Vector2 _pos, Vector2 _dim, Item _item){ super(_pos, _dim); item = _item;}

    @Override
    public void update(){}

    @Override
    public void collide(){

        for(int i = 0; i < entityColliders.size(); i++){
            if (entityColliders.get(i) instanceof Player) {
                takeDMG(entityColliders.get(i).dmg);
                item.onGet((Player) entityColliders.get(i));
            }
        }

    }

    @Override
    public void paint(Vector2 _pos, float opacity, boolean stroke){

        if(stroke) stroke(0); else noStroke();
        fill(item.color.getRGB());
        rect(pos.x,pos.y,dim.x,dim.y);
        stroke(1);
    }

}
