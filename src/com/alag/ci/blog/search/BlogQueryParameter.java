package com.alag.ci.blog.search;

import java.util.Collection;

public interface BlogQueryParameter {
	// 검색 타입
	public enum QueryType {SEARCH, TAG};
	// 쿼리 파라미터 종류
	public enum queryParameter {KEY, APIUSER, START_INDEX, LIMIT, QUERY, TAG, SORTBY, LANGUAGE};
	
	public String getParameter(QueryParameter param);
	public void setParameter(QueryParameter param,String vlaue);
	public Collection<QueryParameter> getAllParameters();
	public QueryType getQueryType();
	public String getMethodUrl();
}
