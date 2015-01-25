package com.alag.ci.blog.search.impl;

import java.util.*;

import com.alag.ci.blog.search.BlogQueryParameter;

public abstract class BlogQueryParameterImpl implements BlogQueryParameter {
	// QueryParameter를 저장하기 위해 맵(Map) 구조를 사용한다.
	private Map<QueryParameter, String> params = null;
	private QueryType queryType = null;
	private String methodUrl = null;

	// queryType과 methodUrl을 인자로 받는 생성자
	public BlogQueryParameterImpl(QueryType queryType, String methodUrl) {
		this.queryType = queryType;
		this.methodUrl = methodUrl;
		this.params = new HashMap<QueryParameter, String>();
	}
	
	@Override
	public String getParameter(QueryParameter param) {
		return null;
	}
	@Override
	public void setParameter(QueryParameter param, String vlaue) {
		this.params.put(param, value);
	}
	@Override
	public Collection<QueryParameter> getAllParameters() {
		return null;
	}
	@Override
	public QueryType getQueryType() {
		return null;
	}
	@Override
	public String getMethodUrl() {
		return null;
	}
}
