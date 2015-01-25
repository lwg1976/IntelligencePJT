package com.alag.ci.blog.search.impl;

import java.io.*;
import java.net.URLEncoder;
import java.util.*;

import javax.xml.parsers.*;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.param.HttpMethodParams;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.alag.ci.blog.search.*;

public abstract class BlogSearcherImpl implements BlogSearcher {
	// SAX 파서 팩토리의 이름을 지정한다.
	private static final String JAXP_PROPERTY_NAME = "javax.xml.parsers.SAXParserFactory";
	private static final String APACHE_XERCES_SAX_PARSER = "org.apache.xerces.jaxp.SAXParserFactoryImpl";
	
	private SAXParser parser = null;
	private Map<QueryParameter, String> paramStringMap = null;
	
	protected BlogSearcherImpl() throws BlogSearcherException {
		// SAX 파서 인스턴스를 생성한다.
		createSAXParser();
		initializeParamStringMap();
	}
	
	private void createSAXParser() throws BlogSearcherException {
		if (System.getProperty(JAXP_PROPERTY_NAME) == null) {
			System.setProperty(JAXP_PROPERTY_NAME, APACHE_XERCES_SAX_PARSER);
		}
		// 파서 팩토리를 이용해 새 인스턴스를 생성한다.
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			// 파서 인스턴스를 생성하기 위해 팩토리를 사용한다. 
			this.parser = factory.newSAXParser();
		} catch(ParserConfigurationException e) {
			throw new BlogSearcherException("SAX parser not found", e);
		} catch(SAXException se) {
			throw new BlogSearcherException("SAX exception", se);
		}
	}
	
	protected void initializeParamStringMap() {
		paramStringMap = new HashMap<QueryParameter, String>();
	}
	
	protected Map<QueryParameter, String> getParamStringMap() {
		return paramStringMap;
	}
	
	protected SAXParser getSAXParser() {
		return this.parser;
	}
	
	@Override
	public BlogQueryResult getRelevantBlogs(BlogQueryPrarmeter param) throws BlogSearcherException {
		// 빈 응답으로 초기화
		BlogQueryResult result = new NullBlogQueryResultImpl();
		HttpClient client = new HttpClient();
		// POST나 GET 방식 모두 사용 가능
		HttpMethod method = getMethod(param);
		// 재시도 회수
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));
		
		try {
			// 실제 HTTP 요청
			int statusCode = client.executeMethod(method);
			if (statusCode == HttpStatus.SC_OK) {
				InputStream is = method.getResponseBodyAsStream();
				// 콘텐츠를 파싱한다.
				result = parseContent(is);
				is.close();
			}
		} catch (HttpException he) {
			throw new BlogSearcherException("HTTP exceotion", he);
		} catch (IOException ioe) {
			throw new BlogSearcherException("IOException while getting response body", ioe);
		} finally {
			method.releaseConnection();
		}
		return result;
	}
	
	protected BlogSearchResponseHandler getBlogSearchResponseHandler() {
		return new RSSFeedResponseHandler();
	}
	
	protected abstract HttpMethod getMethod(BlogQueryParameter param);
	
	// 핸들러를 사용해 콘텐츠를 파싱한다.
	private BlogQueryResult parsecontent(InputStream is) throws BlogSearcherException {
		try {
			BlogSearchResponseHandler h = getBlogSearchResponseHandler();
			this.getSAXParser().parse(is, (DefaultHandler) h);
			return h.getBlogQueryResult();
		} catch (SAXException se) {
			throw new BlogSearcherException("Error parsing Response XML", se);
		} catch (IOException ioe) {
			throw new BlogSearcherException("IOException while parsing XML", ioe);
		}
	}
	
	// GET 메서드일 경우 URL을 인코딩
	public static String urlEncode(String s) {
		String result = s;
		try {
			result = URLEncoder.encode(s, "UTF-8");
		} catch(UnsupportedEncodingException e) {
			// UTF-8 지원
			System.out.println("Unsupported encoding exception thrown");
		}
		return result;
	}
}
