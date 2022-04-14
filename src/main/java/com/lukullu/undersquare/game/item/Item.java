package com.lukullu.undersquare.game.item;

import com.lukullu.undersquare.game.entity.player.Player;

import java.awt.*;

public class Item{
	
	public String name;
	public Color color;
	Item(String _name, Color _color){ name = _name; color = _color; }

	public void onGet(Player target) {}
}
