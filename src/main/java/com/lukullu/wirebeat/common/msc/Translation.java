package com.lukullu.wirebeat.common.msc;

import com.lukullu.wirebeat.WireBeat;

public class Translation {
	
	public static int scaleToScreenX(int input) {
		
		return Math.round(input/1920f * WireBeat.INSTANCE.width);
		
	}
	
	public static int scaleToScreenY(int input) {
		
		return Math.round(input/1080f * WireBeat.INSTANCE.height);
		
	}

}
