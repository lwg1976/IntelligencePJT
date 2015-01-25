package com.alag.ci.blog.search.impl;

import java.util.*;

public class NullBlogQueryResultImpl extends BlogQueryResultImpl {
	private List<RetrievedBlogEntry> results = null;
	private Integer queryCount = null;
	
	public NullBlogQueryResultImpl() {
		super();
		this.setResults(Collections.EMPTY_LIST);
	}
}
