package com.alag.ci.blog.search;

public interface BlogSearchResponseHandler {
	// 블로그 쿼리로부터 결과를 가져온다.
	public BlogQueryResult getBlogQueryResult();
}
