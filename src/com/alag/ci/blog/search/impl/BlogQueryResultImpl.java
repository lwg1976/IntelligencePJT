package com.alag.ci.blog.search.impl;

import java.util.*;

public class BlogQueryResultImpl {
	List<RetrievedBlogEntry> results = null;
	Integer queryCount = null;
	
	public BlogQueryResultImpl() {
		super();
	}

	public List<RetrievedBlogEntry> getRelevantBlogs() {
		return results;
	}
	public void setResults(List<RetrievedBlogEntry> results) {
		this.results = results;
	}
	public Integer getQueryCount() {
		return queryCount;
	}
	public void setQueryCount(Integer queryCount) {
		this.queryCount = queryCount;
	}
}
