package com.lukullu.wirebeat;

import com.kilix.processing.ProcessingSketch;

public class Main {
	
	public static void main(String[] args) {

		ProcessingSketch<WireBeat> gameSketch = ProcessingSketch
				.builder(WireBeat.class)
				.fullScreen()
				.build();

	}

}
