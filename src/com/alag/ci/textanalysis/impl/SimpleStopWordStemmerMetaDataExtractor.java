package com.alag.ci.textanalysis.impl;

public class SimpleStopWordStemmerMetaDataExtractor extends SimpleStopWordMetaDataExtractor {
	protected String normalizeToken(String token) {
		// If it will be rejected, dont bother normalizing
		// 불용어로 제거된 단어는 정규화 작업을 수행하지 않는다.
		if (acceptToken(token)) {
			// First convert it to lower case
			token = super.normalizeToken(token);
			// Remove s
			if (token.endsWith("s")) {
				int index = token.lastIndexOf("s");
				if (index > 0) {
					token = token.substring(0, index);
				}
			}
		}
		return token;
	}
}
