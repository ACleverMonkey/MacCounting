package com.cn.juli.common;

import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DES {

	public static void main(String[] args) throws Exception {
		/*
		 * String aa = encrypt("D425B11C8EFE1D6E0BDD916293CD140D",
		 * "5A4B20D12A2ED308"); aa=decode(aa, "C9F82211158E059C");
		 * System.err.println(encrypt(aa, "5A4B20D12A2ED308"));
		 */
		String key = "5A4B20D12A2ED308C9F82211158E059C";
		String k1 = key.substring(0, 16);
		String k2 = key.substring(16, key.length());
		System.err.println(k1);
		System.err.println(k2);
	}

	public String DES_DES_DES(String data, String key) {
		String k1 = key.substring(0, 16);
		String k2 = key.substring(16, key.length());
		try {
			String temp = encrypt(data, k1);
			temp = decode(temp, k2);
			return encrypt(temp, k1);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * DES加密
	 * 
	 * @param HexString
	 *            字符串（16位16进制字符串）
	 * @param keyStr
	 *            密钥16个1
	 * @throws Exception
	 */
	public static String encrypt(String HexString, String keyStr) throws Exception {
		try {
			byte[] theKey = null;
			byte[] theMsg = null;
			theMsg = hexToBytes(HexString);
			theKey = hexToBytes(keyStr);
			KeySpec ks = new DESKeySpec(theKey);
			SecretKeyFactory kf = SecretKeyFactory.getInstance("DES");
			SecretKey ky = kf.generateSecret(ks);
			Cipher cf = Cipher.getInstance("DES/ECB/NoPadding");
			cf.init(Cipher.ENCRYPT_MODE, ky);
			byte[] theCph = cf.doFinal(theMsg);
			return bytesToHex(theCph);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * DES解密
	 *
	 * @param hexStr
	 *            16位十六进制字符串
	 * @param keyStr
	 *            密钥16个1
	 * @param modeStr
	 *            解密模式:ECB
	 * @throws Exception
	 */
	public static String decode(String hexStr, String keyStr) throws Exception {
		try {
			byte[] theKey = null;
			byte[] theMsg = null;
			theMsg = hexToBytes(hexStr);
			theKey = hexToBytes(keyStr);
			KeySpec ks = new DESKeySpec(theKey);
			SecretKeyFactory kf = SecretKeyFactory.getInstance("DES");
			SecretKey ky = kf.generateSecret(ks);
			Cipher cf = Cipher.getInstance("DES/ECB/NoPadding");
			cf.init(Cipher.DECRYPT_MODE, ky);
			byte[] theCph = cf.doFinal(theMsg);
			return bytesToHex(theCph);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static byte[] hexToBytes(String str) {
		if (str == null) {
			return null;
		} else if (str.length() < 2) {
			return null;
		} else {
			int len = str.length() / 2;
			byte[] buffer = new byte[len];
			for (int i = 0; i < len; i++) {
				buffer[i] = (byte) Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16);
			}
			return buffer;
		}

	}

	public static String bytesToHex(byte[] data) {
		if (data == null) {
			return null;
		} else {
			int len = data.length;
			String str = "";
			for (int i = 0; i < len; i++) {
				if ((data[i] & 0xFF) < 16)
					str = str + "0" + java.lang.Integer.toHexString(data[i] & 0xFF);
				else
					str = str + java.lang.Integer.toHexString(data[i] & 0xFF);
			}
			return str.toUpperCase();
		}
	}
}
