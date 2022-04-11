package com.lukullu.undersquare.widgets;

import com.kilix.processing.ProcessingClass;
import com.lukullu.undersquare.common.data.Vector2;

import static com.lukullu.undersquare.common.Constants.*;

public class TextWidget extends Widget implements ProcessingClass {
	
	public String text;
	
	public TextWidget(Vector2 _pos, Vector2 _dim, String _text) { super(_pos, _dim); init(_text); }
	
	public void init(String _text) {
		text = _text;
	}
	
	@Override
	public void paint(Vector2 _rel) {
		
		fill(c.getRGB());
		rect(pos.x + _rel.x, pos.y + _rel.y, dim.x, dim.y);
		fill(textColor.getRGB());
		textAlign(CORNER);
		textSize(30);
		text(text, pos.x+_rel.x+dim.x/10,pos.y+_rel.y+dim.y-dim.y/4);
		
	}
	
}
