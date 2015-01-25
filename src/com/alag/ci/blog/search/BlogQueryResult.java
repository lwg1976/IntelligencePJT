package com.alag.ci.blog.search;

import java.util.List;

public interface BlogQueryResult {
	// 검색 결과 개수를 반환
	public Integer getQueryCount();
	// 검색한 블로그 글 리스트를 반환
	public List<RetrievedblogEntry> getRelevantBlogs();
}
