package com.czj.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class RegexTest {

	@Test
	public void test1() {
		String input = "\\\\u1234\\\\u34fa";
		String regex = "\\\\u([0-9a-f]{4})";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(input);
		while (m.find()) {
			System.out.println(m.group());
			System.out.println(m.group(0));
			System.out.println(m.group(1));
		}
	}

}
