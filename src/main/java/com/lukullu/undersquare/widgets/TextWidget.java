package com.lukullu.undersquare.widgets;

import com.kilix.processing.ProcessingClass;
import com.lukullu.undersquare.common.data.Vector2;

import static com.lukullu.undersquare.common.Constants.*;

public class TextWidget extends Widget implements ProcessingClass {
	
	public String text;
	public int textSize;
	
	public TextWidget(Vector2 _pos, Vector2 _dim, int _cornerBL, int _cornerBR, int _cornerTL, int _cornerTR, String _text, int _textSize) {
		super(_pos, _dim, _cornerBL, _cornerBR, _cornerTL, _cornerTR, UI_CONTRAST_COLOR);  textSize = _textSize; text = _text;
	}
	
	@Override
	public void paint(Vector2 _rel) {

		noStroke();
		fill(UI_CONTRAST_COLOR.getRGB());
		rect(pos.x + _rel.x, pos.y + _rel.y, dim.x, dim.y, cornerTL, cornerTR, cornerBR, cornerBL);
		fill(UI_TEXT_COLOR.getRGB());
		textAlign(CORNER);
		textSize(textSize);
		textAlign(LEFT,CENTER);
		text(text, pos.x+_rel.x+dim.x/10f,pos.y + _rel.y + dim.y/2f);
		
	}
	
}
