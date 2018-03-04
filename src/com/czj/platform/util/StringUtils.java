package com.czj.platform.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 操作 {@link String} 的各种实用方法集合。
 * 
 * @author xmcheng
 * @since 2013/06/25
 */
public abstract class StringUtils {
	
	private static MessageDigest digest = null;

	/**
	 * 检测给定的字符集是否为 {@code null} 或其长度是否为0.
	 * <p>
	 * <b>备注：</b>对于仅包含空格的字符集将返回 {@code true} 。
	 * </p>
	 * <p>
	 * 
	 * <pre class="code">
	 * StringUtils.hasLength(null) = false
	 * StringUtils.hasLength("") = false
	 * StringUtils.hasLength(" ") = true
	 * StringUtils.hasLength("Hello") = true
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            待检测的字符集（也可能是 {@code null}）。
	 * @return 如果待检测的字符集不为 {@code null} 且其长度不为0，则返回 {@code true}。
	 * @see #hasText(String)
	 */
	public static boolean hasLength(CharSequence str) {
		return (str != null && str.length() > 0);
	}

	/**
	 * 检测给定的字符串是否是非空字符串。
	 * 
	 * @param str
	 *            待检测的字符串（也可能是 {@code null}）。
	 * @return 如果待检测的字符串不为 {@code null} -或- 其长度大于0 -或- 其中仅包含空格，则返回 {@code true}
	 *         ，否则返回 {@code false}。
	 */
	public static boolean hasText(String str) {
		return hasText((CharSequence) str);
	}

	/**
	 * 检测给定的字符集是否是否包含非空字符。
	 * <p>
	 * 
	 * <pre class="code">
	 * StringUtils.hasText(null) = false
	 * StringUtils.hasText("") = false
	 * StringUtils.hasText(" ") = false
	 * StringUtils.hasText("12345") = true
	 * StringUtils.hasText(" 12345 ") = true
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            待检测的字符集（也可能是 {@code null}）。
	 * @return 如果待检测的字符集不为 {@code null} -或- 其长度大于0 -或- 其中仅包含空格，则返回 {@code true}
	 *         ，否则返回 {@code false}。
	 */
	public static boolean hasText(CharSequence str) {
		if (!hasLength(str)) {
			return false;
		}
		int strLen = str.length();
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	public static String convertToPinyinCode(String src)
			throws BadHanyuPinyinOutputFormatCombination {
		StringBuilder result = new StringBuilder();
		if (hasText(src)) {
			char[] srcChars = src.toCharArray();
			for (char c : srcChars) {
				result.append(convertToPinyinCode(c));
			}
		}
		return result.toString();
	}

	public static String[] tokenizeToStringArray(String str, String delimiters) {
		return tokenizeToStringArray(str, delimiters, true, true);
	}

	public static String[] tokenizeToStringArray(String str, String delimiters,
			boolean trimTokens, boolean ignoreEmptyTokens) {
		if (str == null) {
			return null;
		}
		StringTokenizer st = new StringTokenizer(str, delimiters);
		List<String> tokens = new ArrayList<String>();
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			if (trimTokens) {
				token = token.trim();
			}
			if ((!ignoreEmptyTokens) || (token.length() > 0)) {
				tokens.add(token);
			}
		}
		return tokens == null ? null : tokens
				.toArray(new String[tokens.size()]);
	}
	
	/**
	 * Hashes a String using the Md5 algorithm and returns the result as a
	 * String of hexadecimal numbers. This method is synchronized to avoid
	 * excessive MessageDigest object creation. If calling this method becomes a
	 * bottleneck in your code, you may wish to maintain a pool of MessageDigest
	 * objects instead of using this method.
	 * <p>
	 * A hash is a one-way function -- that is, given an input, an output is
	 * easily computed. However, given the output, the input is almost
	 * impossible to compute. This is useful for passwords since we can store
	 * the hash and a hacker will then have a very hard time determining the
	 * original password.
	 * <p>
	 * In Jive, every time a user logs in, we simply take their plain text
	 * password, compute the hash, and compare the generated hash to the stored
	 * hash. Since it is almost impossible that two passwords will generate the
	 * same hash, we know if the user gave us the correct password or not. The
	 * only negative to this system is that password recovery is basically
	 * impossible. Therefore, a reset password method is used instead.
	 *
	 * @param data
	 *            the String to compute the hash of.
	 * @return a hashed version of the passed-in String
	 */
	public synchronized static final String hash(String data) {
		if (digest == null) {
			try {
				digest = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException nsae) {
				System.err
						.println("Failed to load the MD5 MessageDigest. " + "We will be unable to function normally.");
				nsae.printStackTrace();
			}
		}
		// Now, compute hash.
		digest.update(data.getBytes());
		return encodeHex(digest.digest());
	}
	
	/**
	 * Turns an array of bytes into a String representing each byte as an
	 * unsigned hex number.
	 * <p>
	 * Method by Santeri Paavolainen, Helsinki Finland 1996<br>
	 * (c) Santeri Paavolainen, Helsinki Finland 1996<br>
	 * Distributed under LGPL.
	 *
	 * @param bytes
	 *            an array of bytes to convert to a hex-string
	 * @return generated hex string
	 */
	public static final String encodeHex(byte[] bytes) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		int i;

		for (i = 0; i < bytes.length; i++) {
			if (((int) bytes[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString((int) bytes[i] & 0xff, 16));
		}
		return buf.toString().toUpperCase();
	}

	/**
	 * Turns a hex encoded string into a byte array. It is specifically meant to
	 * "reverse" the toHex(byte[]) method.
	 *
	 * @param hex
	 *            a hex encoded String to transform into a byte array.
	 * @return a byte array representing the hex String[
	 */
	public static final byte[] decodeHex(String hex) {
		char[] chars = hex.toCharArray();
		byte[] bytes = new byte[chars.length / 2];
		int byteCount = 0;
		for (int i = 0; i < chars.length; i += 2) {
			byte newByte = 0x00;
			newByte |= hexCharToByte(chars[i]);
			newByte <<= 4;
			newByte |= hexCharToByte(chars[i + 1]);
			bytes[byteCount] = newByte;
			byteCount++;
		}
		return bytes;
	}

	/**
	 * Returns the the byte value of a hexadecmical char (0-f). It's assumed
	 * that the hexidecimal chars are lower case as appropriate.
	 *
	 * @param ch
	 *            a hexedicmal character (0-f)
	 * @return the byte value of the character (0x00-0x0F)
	 */
	private static final byte hexCharToByte(char ch) {
		switch (ch) {
		case '0':
			return 0x00;
		case '1':
			return 0x01;
		case '2':
			return 0x02;
		case '3':
			return 0x03;
		case '4':
			return 0x04;
		case '5':
			return 0x05;
		case '6':
			return 0x06;
		case '7':
			return 0x07;
		case '8':
			return 0x08;
		case '9':
			return 0x09;
		case 'a':
			return 0x0A;
		case 'b':
			return 0x0B;
		case 'c':
			return 0x0C;
		case 'd':
			return 0x0D;
		case 'e':
			return 0x0E;
		case 'f':
			return 0x0F;
		}
		return 0x00;
	}

	private static String convertToPinyinCode(char src)
			throws BadHanyuPinyinOutputFormatCombination {
		// 创建汉语拼音处理类
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		// 输出设置，大小写，音标方式
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

		String[] results = PinyinHelper.toHanyuPinyinStringArray(src, format);
		if (results != null && results.length > 0) {
			return results[0].substring(0,1);
//			StringBuilder result = new StringBuilder(results[0]);
//			if (result.length() > 1 && 'h' == result.charAt(1)) {
//				return result.substring(0, 2);
//			} else if (result.length() > 0) {
//				return result.substring(0, 1);
//			}
		} else {
			return String.valueOf(src);
		}
	}
}
