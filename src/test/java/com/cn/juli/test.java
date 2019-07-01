package com.cn.juli;

import org.springframework.expression.ParseException;

public class test {
	private static String hexStr = "0123456789ABCDEF";

	public static void main(String args[]) throws ParseException {

		String code1 = encode("1122334480000000");
		String code2 = encode("0000000000000000");
		System.out.println(code1);
		System.out.println(code2);

		String result = "";
		for (int i = 0; i < code1.length(); i++) {
			char c1 = code1.charAt(i);
			char c2 = code2.charAt(i);
			String a = convertHexToBinary(c1 + "");
			String b = convertHexToBinary(c2 + "");
			byte[] aa = a.getBytes();
			byte[] bb = b.getBytes();
			String cc = "";
			for (int j = 0; j < aa.length; j++) {
				cc += aa[j] ^ bb[j];
			}
			String tmp = Integer.toHexString(Integer.valueOf(cc, 2).intValue()).toString();
			if (i % 2 != 0 && i > 0) {
				result += tmp;
			}
		}
		System.err.println("result=" + result);
	}

	public static String convertHexToBinary(String hexString) {
		long l = Long.parseLong(hexString, 16);
		String binaryString = Long.toBinaryString(l);
		int shouldBinaryLen = hexString.length() * 4;
		StringBuffer addZero = new StringBuffer();
		int addZeroNum = shouldBinaryLen - binaryString.length();
		for (int i = 1; i <= addZeroNum; i++) {
			addZero.append("0");
		}
		return addZero.toString() + binaryString;
	}

	public static String encode(String str) {
		// 根据默认编码获取字节数组
		byte[] bytes = str.getBytes();
		String strs = "";
		// 将字节数组中每个字节拆解成2位16进制整数
		for (int i = 0; i < bytes.length; i++) {
			// 取得高四位
			strs += hexStr.charAt((bytes[i] & 0xf0) >> 4);
			// System.out.println(i+"--"+bytes[i]+"----"+(bytes[i] &
			// 0xf0)+"----"+hexString.charAt((bytes[i] & 0xf0) >>
			// 4)+"---"+hexString.charAt((bytes[i] & 0x0f) >> 0));
			// 取得低四位
			strs += hexStr.charAt((bytes[i] & 0x0f) >> 0);
		}
		return strs;
	}
}
