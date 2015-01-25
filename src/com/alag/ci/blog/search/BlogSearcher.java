package com.alag.ci.blog.search;

public interface BlogSearcher {
	// 쿼리 파라미터를 이용해서 관련 블로그 검색 결과를 가져온다.
	public BlogQueryResult getRelevantBlogs(BlogQueryPrarmeter param) throws BlogSearcherException;
}
