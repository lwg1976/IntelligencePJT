package com.alag.ci.blog.search.impl.technorati;

import com.alag.ci.blog.search.impl.BlogQueryParameterImpl;

public class TechnoratiSearchBlogQueryParameterImpl extends BlogQueryParameterImpl {
	// 검색 쿼리 URL
	private static final String TECHNORATI_SEARCH_API_URL = "http://api.technorati.com/search";
	
	public TechnoratiSearchBlogQueryParameterImpl() {
		// 쿼리 타입 설정
		super(QueryType.SEARCH, TECHNORATI_SEARCH_API_URL);
	}
	
}
