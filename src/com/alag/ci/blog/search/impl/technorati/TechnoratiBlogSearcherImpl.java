package com.alag.ci.blog.search.impl.technorati;

import java.util.*;

import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import com.alag.ci.blog.search.BlogQueryParameter;
import com.alag.ci.blog.search.BlogSearchResponseHandler;
import com.alag.ci.blog.search.BlogSearcherException;
import com.alag.ci.blog.search.BlogQueryParameter.QueryParameter;
import com.alag.ci.blog.search.impl.BlogSearcherImpl;

public class TechnoratiBlogSearcherImpl extends BlogSearcherImpl {
	protected TechnoratiBlogSearcherImpl() throws BlogSearcherException {
		super();
	}
	
	// 파라미터를 테크노라티에 특화된 API 문자열에 매핑
	protected void initializeParamStringMap() {
		super.initializeParamStringMap();
		Map<QueryParameter, String> paramStringMap = getParamStringMap();
		paramStringMap.put(QueryParameter.KEY, "key");
		paramStringMap.put(QueryParameter.START_INDEX, "start");
		paramStringMap.put(QueryParameter.LIMIT, "limit");
		paramStringMap.put(QueryParameter.QUERY, "query");
		paramStringMap.put(QueryParameter.TAG, "tag");
		paramStringMap.put(QueryParameter.LANGUAGE, "language");
	}

	// Get나 Post 모두 가능
	@Override
	protected HttpMethod getMethod(BlogQueryParameter param) {
		// return getPostMethod(param);
		return getGetMethod(param);
	}
	
	private HttpMethod getPostMethod(BlogQueryParameter param) {
		PostMethod method = new PostMethod(param.getMethodUrl());
		Collection<QueryParameter> paramColl = param.getAllParameters();
		for (QueryParameter qp : paramColl) {
			String key = getParamStringMap().get(qp);
			if (key != null) {
				method.addParameter(key, param.getParameter(qp));
			}
		}
		return method;
	}
	
	private HttpMethod getGetMethod(BlogQueryParameter param) {
		String url = param.getMethodUrl() + "?";
		Collection<QueryParameter> paramColl = param.getAllParameters();
		for (QueryParameter qp : paramColl) {
			String key = getParamStringMap().get(qp);
			if (key != null) {
				url += "&" + key + "=" + urlEncode(param.getParameter(qp));
			}
		}
		return new GetMethod(url);
	}
	
	protected BlogSearchResponseHandler getBlogSearchResponseHandler() {
		return new TechnoratiResponseHandler();
	}
}
