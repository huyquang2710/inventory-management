package com.test;

import org.apache.commons.lang.StringUtils;

public class TestCode {
	public static void main(String[] args) {
		
		String s = "com.dao.BaseDAOImpl<com.model.Users>";
		
		// StringUtils.substringBetween(s, s)

		String a = StringUtils.substringBetween(s, "<", ">");

		System.out.println(a);
	}
}
