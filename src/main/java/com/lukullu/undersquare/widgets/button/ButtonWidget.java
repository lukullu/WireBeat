package com.lukullu.undersquare.widgets.button;

import com.kilix.processing.ProcessingClass;
import com.lukullu.undersquare.common.data.Vector2;
import com.lukullu.undersquare.widgets.TextWidget;

import static com.lukullu.undersquare.common.Constants.textColor;
import static com.lukullu.undersquare.common.collision.Collision.pointRectangleCollision;

public class ButtonWidget extends TextWidget implements ProcessingClass {
	
	public ButtonState buttonState = ButtonState.IDLE;
	public Runnable onClick;
	
	public boolean reset = false;
	
	public ButtonWidget(Vector2 _pos, Vector2 _dim, String _text){
		super(_pos, _dim, _text);
		onClick = () -> {};
	}
	
	public ButtonWidget(Vector2 _pos, Vector2 _dim, String _text, Runnable _onClick){
		super(_pos, _dim, _text);
		onClick = _onClick;
	}
	
	@Override
	public void update(Vector2 _rel) {
		
		if(pointRectangleCollision(new Vector2(getMouseX(), getMouseY()), new Vector2(pos.x + _rel.x, pos.y + _rel.y), dim) && !reset){
			buttonState = ButtonState.HOVER;
		}else{
			buttonState = ButtonState.IDLE;
		}
		
		if(buttonState == ButtonState.HOVER && getMousePressed() && !reset){
			buttonState = ButtonState.CLICKED;
			reset = true;
			onClick();
		}
		
		if(! getMousePressed()){
			reset = false;
		}
		
	}
	
	@Override
	public void paint(Vector2 _rel) {
		switch(buttonState) {
			case CLICKED:
				fill(c.brighter().getRGB());
				break;
			case HOVER:
				fill(c.darker().getRGB());
				break;
			default:
				fill(c.getRGB());
		}
		
		rect(pos.x + _rel.x, pos.y + _rel.y, dim.x, dim.y);
		fill(textColor.getRGB());
		textAlign(CENTER);
		textSize(30);
		text(text, pos.x + _rel.x + dim.x/2, pos.y + _rel.y + dim.y-dim.y/4);
	}
	
	public void onClick() { onClick.run(); }
	
}
