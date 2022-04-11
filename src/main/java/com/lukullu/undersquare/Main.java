package com.lukullu.undersquare;

import com.kilix.processing.ProcessingSketch;

public class Main {
	
	public static void main(String[] args) {
		ProcessingSketch<UnderSquare> gameSketch = ProcessingSketch
				.builder(UnderSquare.class)
				.fullScreen()
				.build();
	}
	
}
