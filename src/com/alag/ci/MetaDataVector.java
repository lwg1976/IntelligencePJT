package com.alag.ci;

import java.util.List;

public interface MetaDataVector {
	// 텀과 가중치에 대한 정렬된 목록을 얻는다.
	public List<TagMagnitude> getTagMetaDataMagnitude();
	// 다른  MetaDataVector로부터 추가된 결과를 가져온다.
	public MetaDataVector add(MetaDataVector other);
}
