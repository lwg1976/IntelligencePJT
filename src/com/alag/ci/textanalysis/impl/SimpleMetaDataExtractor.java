package com.alag.ci.textanalysis.impl;

import java.util.*;

import com.alag.ci.*;
import com.alag.ci.textanalysis.MetaDataExtractor;

public class SimpleMetaDataExtractor implements MetaDataExtractor {
	// 발견된 모든 태그의 맵(map)을 유지한다.
	private Map<String, Long> idMap = null;
	// 고유  ID를 생성하기 위해 사용된다.
	private Long currentId = null;
	
	public SimpleMetaDataExtractor() {
		this.idMap = new HashMap<String, Long>();
		this.currentId = new Long(0);
	}
	
	@Override
	public MetaDataVector extractMetaData(String title, String body) {
		// 제목과 본문에 동일한 가중치를 준다.
		MetaDataVector titleMDV = getMetaDataVector(title);
		MetaDataVector bodyMDV = getMetaDataVector(body);
		return titleMDV.add(bodyMDV);
	}
	
	// 발견된 태그에 고유 ID를 부여한다.
	protected Long getTokenId(String token) {
		Long id = this.idMap.get(token);
		if (id == null) {
			id = this.currentId ++;
			this.idMap.put(token, id);
		}
		return id;
	}
	
	protected MetaDataVector getMetaDataVector(String text) {
		Map<String, Integer> keywordMap = new HashMap<String, Integer>();
		// 공백을 기준으로 StringTokenizer를 사용해서 자른다.
		StringTokenizer st = new StringTokenizer(text);
		while(st.hasMoreTokens()) {
			String token = normalizeToken(st.nextToken());
			// Check to see if it is not a stop word
			// 유효한 토큰인가?
			if (acceptToken(token)) {
				Integer count = keywordMap.get(token);
				if (count == null) {
					count = new Integer(0);
				}
				count ++;
				// 빈도수를 유지한다.
				keywordMap.put(token, count);
			}
		}
		// MetaDataVecor 객체를 생성한다.
		MetaDataVector mdv = createMetaDataVector(keywordMap);
		return mdv;
	}
	
	// 토큰이 유효한지 판단하는 메서드
	protected boolean acceptToken(String token) {
		return true;
	}
	
	// 문장 부호를 제거하고 소문자로 바꾼다.
	protected String normalizeToken(String token) {
		String normalizedToken = token.toLowerCase().trim();
		if ((normalizedToken.endsWith(".")) || (normalizedToken.endsWith(","))) {
			int size = normalizedToken.length();
			normalizedToken = normalizedToken.substring(0, size-1);
		}
		return normalizedToken;
	}
}
