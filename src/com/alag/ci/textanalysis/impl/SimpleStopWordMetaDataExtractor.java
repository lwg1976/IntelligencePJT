package com.alag.ci.textanalysis.impl;

import java.util.*;

public class SimpleStopWordMetaDataExtractor extends SimpleMetaDataExtractor {
	// 불용어 사전
	private static final String[] stopWords = {"and", "of", "the", "to", "is", "their", "can", "all", ""};
	private Map<String, String> stopWordsMap = null;
	
	public SimpleStopWordMetaDataExtractor() {
		this.stopWordsMap = new HashMap<String, String>();
		for (String s: stopWords) {
			this.stopWordsMap.put(s, s);
		}
	}
	
	// 불용어일 경우 토큰으로 받아들이지 않는다.
	protected boolean acceptToken(String token) {
		return !this.stopWordsMap.containsKey(token);
	}
}
