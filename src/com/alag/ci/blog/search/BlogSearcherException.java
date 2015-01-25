package com.alag.ci.blog.search;

public class BlogSearcherException extends Exception {
	// 연쇄 예외 생성자
	public BlogSearcherException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public BlogSearcherException(String message) {
		super(message);
	}
}
