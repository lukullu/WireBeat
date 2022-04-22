package com.lukullu.wirebeat.common.collision;

import com.lukullu.wirebeat.common.data.Axis;
import com.lukullu.wirebeat.common.data.Direction;

public class DirectedAxis{
	
	public Direction dir;
	public Axis axis;
	
	public DirectedAxis( Direction _dir, Axis _axis) { dir = _dir; axis = _axis; }
	
}
