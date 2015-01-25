package com.alag.ci.blog.search.impl;

import java.text.*;
import java.util.*;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import com.alag.ci.blog.search.*;

public abstract class BlogSearchResponseHandlerImpl  extends DefaultHandler implements BlogSearchResponseHandler {
	// 결과를 저장
	private BlogQueryResultImpl result = null;
	private List<RetrievedBlogEntry> entries = null;
	private RetrievedBlogEntryImpl item = null;
	// 파싱할 토큰을 추적한다.
	private XmlToken whichToken = null;
	private Map<String, XmlToken> tagMap = null;
	private String charString = null;
	private DateFormat dateFormat = null;
	// 날짜 포맷을 파싱하기 위한 변수
	private DateFormat timeZoneDateFormat = null;
	private DateFormat timeZoneDayDateFormat = null;
	private DateFormat timeZoneYearDateFormat = null;
	
	public BlogSearchResponseHandlerImpl() {
		this.result = new BlogQueryResultImpl();
		this.entries = new ArrayList<RetrievedBlogEntry>();
		this.result.setResults(this.entries);
		this.tagMap = new HashMap<String, XmlToken>();
		// 파싱할 XML 토큰 정보를 맵(Map)에 저장
		for (XmlToken t : getXMLTokens()) {
			this.tagMap.put(t.getTag(), t);
		}
		this.dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.timeZoneDayDateFormat = new SimpleDateFormat("EEE, dd MMM yy HH:mm:ss zzz");
		this.timeZoneYearDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss zzz");
		this.timeZoneDateFormat = new SimpleDateFormat("dd MMM yy HH:mm:ss zzz");
	}
	
	protected abstract XmlToken[] getXMLTokens();
	
	// 새로운 검색 결과 아이템의 시작인지 판단
	protected abstract boolean isBlogEntryToken(XmlToken t);
	
	public BlogQueryResultImpl getResult() {
		return result;
	}
	public List<RetrievedBlogEntry> getEntries() {
		return entries;
	}
	public RetrievedBlogEntryImpl getItem() {
		return item;
	}
	public XmlToken getWhichToken() {
		return whichToken;
	}
	public Map<String, XmlToken> getTagMap() {
		return tagMap;
	}
	public String getCharString() {
		return charString;
	}
	public DateFormat getDateFormat() {
		return dateFormat;
	}
	public DateFormat getTimeZoneDateFormat() {
		return timeZoneDateFormat;
	}
	public DateFormat getTimeZoneDayDateFormat() {
		return timeZoneDayDateFormat;
	}
	public DateFormat getTimeZoneYearDateFormat() {
		return timeZoneYearDateFormat;
	}
	
	@Override
	public BlogQueryResult getBlogQueryResult() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void startElement(String namespaceURI, String localName, String qNmae, Attributes atts) throws SAXException {
		XmlToken t = this.tagMap.get(qName);
		if (t != null) {
			this.whichToken = t;
			// 새로운 아이템이 시작되면 새 인스턴스를 생성
			if (isBlogEntryToken(t)) {
				this.item = new RetrievedBlogEntryImpl();
				this.entries.add(this.item);
			}
		}
		charString = "";
	}
	
	public void endElement(String namespaceUrI, String sName, String qName) throws SAXException {
		this.whichToken = null;
	}
	
	public abstract void characters(char buf[], int offset, int len) throws SAXException;
	
	// get/get charString
	// 날짜 문자열을 파싱
	// proteccted Date getParsedDate(String s) {}
}
