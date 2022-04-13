package com.lukullu.undersquare.widgets;

import com.kilix.processing.ProcessingClass;
import com.lukullu.undersquare.common.IO;
import com.lukullu.undersquare.UnderSquare;
import com.lukullu.undersquare.common.data.Vector2;
import com.lukullu.undersquare.editor.LevelEditor;
import com.lukullu.undersquare.widgets.button.ButtonWidget;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import static com.lukullu.undersquare.common.Constants.*;
import static com.lukullu.undersquare.common.msc.Translation.*;

public class ScrollWidget extends Widget implements ProcessingClass {
	
	public ArrayList<Widget> widgets = new ArrayList<>();
	public Widget title;
	String name = "";

	public int scrollPosition = 0;
	
	public ScrollWidget(Vector2 _pos, Vector2 _dim, int _cornerBL, int _cornerBR, int _cornerTL, int _cornerTR, String _name) {

		super(_pos, _dim, _cornerBL, _cornerBR, _cornerTL, _cornerTR);
		name = _name;

		title = new TextWidget(
				pos,
				new Vector2(
						dim.x,
						scaleToScreenY(80)),
				0,0, _cornerTL, cornerTR,
				name, 25);

	}
	
	// add/remove widgets to/from the list
	public void addWidget(Widget _toAdd)       { widgets.add(_toAdd); }
	public void removeWidget(Widget _toRemove) { widgets.remove(_toRemove); }
	public void removeWidget(int _index)       { widgets.remove(_index); }
	public void clearWidgets() { widgets = new ArrayList<>();}
	
	// programmatically set scroll position
	public void setScrollPosition(int _scrollPosition) { scrollPosition = _scrollPosition; }

	@Override
	public void update(Vector2 _rel) {
		int contentHeight = 0;

		for(int i = 0; i < widgets.size(); i++) {
			Widget cur = widgets.get(i);

			// top overflow
			if (scrollPosition > contentHeight) { contentHeight += cur.dim.y + scaleToScreenY(10); continue; }
			// bottom overflow
			if (contentHeight + scaleToScreenY(10) + cur.dim.y > dim.y + title.dim.y) break;
			
			cur.update(new Vector2(
							pos.x + _rel.x,
							pos.y + _rel.y + contentHeight + scaleToScreenY(10) - scrollPosition +  title.dim.y
					)
			);
			
			contentHeight += cur.dim.y + scaleToScreenY(10);
		}
		
	}
	
	@Override
	public void paint(Vector2 _rel){

		title.paint();

		noStroke();
		fill(UI_CONTRAST_COLOR.getRGB());
		rect(pos.x, pos.y + title.dim.y, dim.x, dim.y - title.dim.y); //(title.dim.y/4f)*3f

		int contentHeight = 0;
		
		for(int i = 0; i < widgets.size(); i++){
			
			Widget cur = widgets.get(i);
			
			if (scrollPosition > contentHeight) { contentHeight += cur.dim.y + scaleToScreenY(10); continue; }
			
			if (contentHeight + scaleToScreenY(10) - scrollPosition > dim.y - title.dim.y) break;
			
			cur.paint(new Vector2(
							pos.x,
							pos.y + contentHeight + scaleToScreenY(10) - scrollPosition +  title.dim.y
					)
			);
			
			contentHeight += cur.dim.y + scaleToScreenY(10);
		}
	}
	
}