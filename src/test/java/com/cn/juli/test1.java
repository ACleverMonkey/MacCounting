package com.cn.juli;

import java.math.BigInteger;


public class test1 {
	public static String hexXOR(String hex1, String hex2){
	    BigInteger i1 = new BigInteger(hex1, 16);
	    BigInteger i2 = new BigInteger(hex2, 16);
	    BigInteger res = i1.xor(i2);
	    return res.toString(16).toUpperCase();
	}
	
	public static String xorHex(String a, String b) {
	    char[] chars = new char[a.length()];
	    for (int i = 0; i < chars.length; i++) {
	        chars[i] = toHex(fromHex(a.charAt(i)) ^ fromHex(b.charAt(i)));
	    }
	    return new String(chars);
	}
	private static int fromHex(char c) {
	    if (c >= '0' && c <= '9') {
	        return c - '0';
	    }
	    if (c >= 'A' && c <= 'F') {
	        return c - 'A' + 10;
	    }
	    if (c >= 'a' && c <= 'f') {
	        return c - 'a' + 10;
	    }
	    throw new IllegalArgumentException();
	}
	
	private static char toHex(int nybble) {
	    if (nybble < 0 || nybble > 15) {
	        throw new IllegalArgumentException();
	    }
	    return "0123456789ABCDEF".charAt(nybble);
	}
	public static void main(String[] args) {
	
		String hex1="1122334480000000";
		String hex2="0000000000000000";
		System.err.println(xorHex(hex1, hex2));
	}
}
