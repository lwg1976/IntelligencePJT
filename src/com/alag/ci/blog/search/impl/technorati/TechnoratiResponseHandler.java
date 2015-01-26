package com.alag.ci.blog.search.impl.technorati;

import org.xml.sax.SAXException;

import com.alag.ci.blog.search.XmlToken;
import com.alag.ci.blog.search.impl.*;

public class TechnoratiResponseHandler extends BlogSearchResponseHandlerImpl {
	// 관심있는 토큰들
	public enum TechnoratiXmlToken implements XmlToken {
		COUNT("querycount"), POSTSMATCHED("postsmatched"),WEBLOG("weblog"), NAME("name"),
		LSATUPDATE("lastupdate"), URL("url"), TITLE("title"), EXCERPT("excerpt"),
		ITEM("item"), CREATED("created"), FIRSTNAME("firstname"), LASTNAME("lastname");
		
		private String tag = null;
		
		TechnoratiXmlToken(String tag) {
			this.tag = tag;
		}
		
		public String getTag() {
			return this.tag;
		}
	}
	
	private String firstName = null;
	
	@Override
	protected XmlToken[] getXMLTokens() {
		return TechnoratiXmlToken.values();
	}

	// 검색 결과 항목 아이템의 시작을 체크한다.
	@Override
	protected boolean isBlogEntryToken(XmlToken t) {
		return (TechnoratiXmlToken.ITEM.compareTo((TechnoratiXmlToken)t) == 0);
	}

	@Override
	public void characters(char[] buf, int offset, int len) throws SAXException {
		String s = this.getCharString() + new String(buf, offset, len);
		this.setCharString(s);
		System.out.println("Token=" + this.getWhichToken() + " -->" + s);
		tochnoratiXmlToken token = (TechnoratiXmlToken) this.getWhichToken();
		RetrievecBlogEntryImpl item = getItem();
		if (token != null) {
			switch (token) {
			case POSTSMATCHED: {
				this.getResult().setQueryCount(new Integers(s));
				break;
			}
			// 다른 필드들도 적절한 토큰에 대입
			}
		}
	}
	
}
