package com.alag.ci.tagcloud.impl;

public class LogFontSizeComputationStrategy extends FontSizeComputationStrategyImpl {

	public LogFontSizeComputationStrategy(int numSizes, String prefix) {
		super(numSizes, prefix);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected double scaleCount(double count) {
		return Math.log10(count);
	}
}
