package com.alag.ci.tagcloud.impl;

import java.util.*;
import com.alag.ci.tagcloud.*;

public class TagCloudImpl implements TagCloud {
	private List<TagCloudElement> elements = null;
	public TagCloudImpl() {
		super();
	}
	public TagCloudImpl(List<TagCloudElement> elements, FontSizeComputationStrategy strategy) {
		this.elements = elements;
		strategy.computeFontSize(this.elements);
		Collections.sort(this.elements);
	}

	@Override
	public List<TagCloudElement> getTagCloudElements() {
		return this.elements;
	}

}
