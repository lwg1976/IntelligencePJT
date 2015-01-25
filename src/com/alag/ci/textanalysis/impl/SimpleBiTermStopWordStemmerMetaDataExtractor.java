package com.alag.ci.textanalysis.impl;

import java.util.*;

import com.alag.ci.MetaDataVector;

public class SimpleBiTermStopWordStemmerMetaDataExtractor extends SimpleStopWordStemmerMetaDataExtractor {
	protected MetaDataVector getMetaDataVector(String text) {
		Map<String, Integer> keywordMap = new HashMap<String, Integer>();
		List<String> allTokens = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(text);
		while (st.hasMoreTokens()) {
			String token = normalizeToken(st.nextToken());
			if (acceptToken(token)) {
				Integer count = keywordMap.get(token);
				if (count == null) {
					count = new Integer(0);
				}
				count ++;
				keywordMap.put(token, count);
				// 순서대로 정규화된 키워드를 저장한다.
				allTokens.add(token);
			}
		}
		
		String firstToken = allTokens.get(0);
		for (String token: allTokens.subList(1, allTokens.size())) {
			String biTerm = firstToken + " " + token;
			// 두 토큰을 입력받아 유효성을 검사한다.
			if (isValidBiTermToken(biTerm)) {
				Integer count = keywordMap.get(biTerm);
				if (count == null) {
					count = new Integer(0);
				}
				count++;
				keywordMap.put(biTerm, count);
			}
			firstToken = token;
		}
		MetaDataVector mdv = createMetaDataVector(keywordMap);
		return mdv;
	}
	
	// 구 사전을 이용해 입력된 구 후보를 테스트한다.
	private boolean isValidBiTermToken(String biTerm) {
		if ("collective intelligence".compareTo(biTerm) == 0) {
			return true;
		}
		return false;
	}
}
