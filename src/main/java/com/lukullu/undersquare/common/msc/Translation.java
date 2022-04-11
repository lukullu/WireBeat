package com.lukullu.undersquare.common.msc;

import com.lukullu.undersquare.UnderSquare;

public class Translation {
	
	public static int scaleToScreenX(int input) {
		
		return Math.round(input/1920f * UnderSquare.INSTANCE.width);
		
	}
	
	public static int scaleToScreenY(int input) {
		
		return Math.round(input/1080f * UnderSquare.INSTANCE.height);
		
	}

}
