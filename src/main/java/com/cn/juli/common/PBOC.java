package com.cn.juli.common;

/**
 * PBOC3DES 加密算法
 * @author Administrator
 *
 */
public class PBOC {

	@SuppressWarnings("unused")
	private class Invalid{  
	}  

	private static int[][] subKey = new int[16][48];
	private static String key="BEDBC0FB201805022E76C57A36F84CE6";

	/** ***************************压缩替换S-Box************************************************* */
	private static final int[][] s1 = {
		{ 14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7 },
		{ 0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8 },
		{ 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0 },
		{ 15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13 } };
	private static final int[][] s2 = {
		{ 15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10 },
		{ 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5 },
		{ 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15 },
		{ 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9 } };
	private static final int[][] s3 = {
		{ 10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8 },
		{ 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1 },
		{ 13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7 },
		{ 1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12 } };
	private static final int[][] s4 = {
		{ 7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15 },
		{ 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9 },// erorr
		{ 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4 },
		{ 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14 } };
	private static final int[][] s5 = {
		{ 2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9 },
		{ 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6 },
		{ 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14 },
		{ 11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3 } };
	private static final int[][] s6 = {
		{ 12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11 },
		{ 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8 },
		{ 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6 },
		{ 4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13 } };
	private static final int[][] s7 = {
		{ 4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1 },
		{ 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6 },
		{ 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2 },
		{ 6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12 } };
	private static final int[][] s8 = {
		{ 13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7 },
		{ 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2 },
		{ 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8 },
		{ 2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11 } };

	private static final int[] ip = { 58, 50, 42, 34, 26, 18, 10, 2, 60, 52,
		44, 36, 28, 20, 12, 4, 62, 54, 46, 38, 30, 22, 14, 6, 64, 56, 48,
		40, 32, 24, 16, 8, 57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35,
		27, 19, 11, 3, 61, 53, 45, 37, 29, 21, 13, 5, 63, 55, 47, 39, 31,
		23, 15, 7 };
	private static final int[] _ip = { 40, 8, 48, 16, 56, 24, 64, 32, 39, 7,
		47, 15, 55, 23, 63, 31, 38, 6, 46, 14, 54, 22, 62, 30, 37, 5, 45,
		13, 53, 21, 61, 29, 36, 4, 44, 12, 52, 20, 60, 28, 35, 3, 43, 11,
		51, 19, 59, 27, 34, 2, 42, 10, 50, 18, 58, 26, 33, 1, 41, 9, 49,
		17, 57, 25 };

	// 每次密钥循环左移位数
	private static final int[] LS = { 1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2,2, 1 };

	/**
	 * 计算MAC(hex)
	 * PBOC_3DES_MAC(16的整数补8000000000000000)
	 * 前n-1组使用单长密钥DES 使用密钥是密钥的左8字节）
	 * 最后1组使用双长密钥3DES （使用全部16字节密钥）
	 * 
	 * @param data 数据
	 * @param 初始向量0000000000000000
	 * @param key 密钥(32byte)
	 * @param type  HEX:16进制的数据    ASC：字符串
	 * @return mac
	 */
	public String PBOC_3DES_MAC(String data,String vector, String key){
		if(vector.equals("")||vector==null){
			vector = "0000000000000000";
		}
		if (key.length()!=32){
			return null;
		}
		if (key.length()!=32){
			return null;
		}
		int len = data.length();
		if (len % 16 == 0) {
			data += "8000000000000000";
		} else {
			data += "8";
			for (int i = 0; i < 15 - len % 16; i++) {
				data += "0";
			}
		}
		len = data.length() / 16;
		String xor = xOr(data.substring(0,16), vector);
		String mac="";
		if(len>1){
			mac = DES_1(xor,key.substring(0, 16), 0);
			//循环进行异或,DES加密
			for(int i = 1;i < len;i++){
				xor = xOr(data.substring(i * 16, i * 16 + 16), mac);
				if(len>2&&i < len-1){
					mac = DES_1(xor,key.substring(0, 16), 0);
				}else{
					mac = xor;
				}
			}
		}else{
			mac = xor;
		}
		return DES_3( mac,key, 0);
	}

	/**
	 * 
	 * 三重DES算法(双长密32byte))
	 * 密钥K1和K2
	 * 1、先用K1加密明文
	 * 2、接K2对上的结果进行解
	 * 3、然后用K1对上的结果进行加
	 * @param source
	 * @param key
	 * @param type
	 *            0:encrypt 1:discrypt
	 * @return
	 */
	private String DES_3(String source, String key, int type) {
		if (key.length() != 32 || source.length() != 16)
			return null;
		String temp = null;
		String K1 = key.substring(0, key.length() / 2);
		String K2 = key.substring(key.length() / 2);
		if (type == 0) {
			temp = encryption(source, K1);
			temp = discryption(temp, K2);
			return encryption(temp, K1);
		}
		if (type == 1) {
			temp = discryption(source, K1);
			temp = encryption(temp, K2);
			return discryption(temp, K1);
		}
		return null;
	}

	/**
	 * DES解密--->对称密钥
	 * 解密算法与加密算法基本相同，不同之处仅在于轮子密钥的使用顺序逆序，即解密的第1
	 * 轮子密钥为加密的6 轮子密钥，解密的 轮子密钥为加密的5 轮子密钥，…，
	 * 解密的第16 轮子密钥为加密的 轮子密钥
	 * @param source密文
	 * @param key密钥
	 * @return
	 * 
	 */
	private String discryption(String source, String key) {
		String str = "";
		int[] data = string2Binary(source);// 64bit
		// 第一步初始置
		data = changeIP(data);
		int[] left = new int[32];
		int[] right = new int[32];
		int[] tmp = new int[32];
		for (int j = 0; j < 32; j++) {
			left[j] = data[j];
			right[j] = data[j + 32];
		}
		setKey(key);// sub key ok
		for (int i = 16; i > 0; i--) {
			// 获取(48bit)的轮子密
			/** *******不同之处********* */
			int[] sKey = subKey[i - 1];
			tmp = left;
			// R1 = L0
			left = right;
			// L1 = R0 ^ f(L0,K1)
			int[] fTemp = f(right, sKey);// 32bit
			right = diffOr(tmp, fTemp);
		}
		// 组合的时候，左右调换**************************************************
		for (int i = 0; i < 32; i++) {
			data[i] = right[i];
			data[32 + i] = left[i];
		}
		data = changeInverseIP(data);
		for (int i = 0; i < data.length; i++) {
			str += data[i];
		}
		str = binary2ASC(str);
		return str;
	}

	/**
	 * @param R(2bit)
	 * @param K(48bit的轮子密
	 * @return 32bit
	 */
	private int[] f(int[] R, int[] K) {
		int[] dest = new int[32];
		int[] temp = new int[48];
		// 先将输入32bit扩展8bit
		int[] expendR = expend(R);// 48bit
		// 与轮子密钥进行异或运
		temp = diffOr(expendR, K);
		// 压缩2bit
		dest = press(temp);
		return dest;
	}

	/**
	 * 8bit压缩2bit
	 * @param source(48bit)
	 * @return R(32bit) B=E(R)⊕K，将48 位的B 分成8 个分组，B=B1B2B3B4B5B6B7B8
	 */
	private int[] press(int[] source) {
		int[] ret = new int[32];
		int[][] temp = new int[8][6];
		int[][][] s = { s1, s2, s3, s4, s5, s6, s7, s8 };
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 6; j++) {
				temp[i][j] = source[i * 6 + j];
			}
		}
		for (int i = 0; i < 8; i++) {
			// (16)
			int x = temp[i][0] * 2 + temp[i][5];
			// (2345)
			int y = temp[i][1] * 8 + temp[i][2] * 4 + temp[i][3] * 2
					+ temp[i][4];
			int val = s[i][x][y];
			String ch = int2Hex(val);
			str.append(ch);
		}
		ret = string2Binary(str.toString());
		// 置换P
		ret = dataP(ret);
		return ret;
	}

	/**
	 * 置换P(32bit)
	 * @param source
	 * @return
	 */
	private int[] dataP(int[] source) {
		int[] dest = new int[32];
		int[] temp = { 16, 7, 20, 21, 29, 12, 28, 17, 1, 15, 23, 26, 5, 18, 31,
				10, 2, 8, 24, 14, 32, 27, 3, 9, 19, 13, 30, 6, 22, 11, 4, 25 };
		int len = source.length;
		for (int i = 0; i < len; i++) {
			dest[i] = source[temp[i] - 1];
		}
		return dest;
	}


	/**
	 * 将int转换成Hex
	 * @param i
	 * @return
	 * @throws Exception
	 */
	private String int2Hex(int i) {
		return Integer.toHexString(i).toUpperCase();
	}

	/**
	 * 2bit扩展8bit
	 * @param source
	 * @return
	 */
	private int[] expend(int[] source) {
		int[] ret = new int[48];
		int[] temp = { 32, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 8, 9, 10, 11, 12,
				13, 12, 13, 14, 15, 16, 17, 16, 17, 18, 19, 20, 21, 20, 21, 22,
				23, 24, 25, 24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32, 1 };
		for (int i = 0; i < 48; i++) {
			ret[i] = source[temp[i] - 1];
		}
		return ret;
	}

	/**
	 * IP-1逆置
	 * @param source
	 * @return
	 */
	private int[] changeInverseIP(int[] source) {
		int[] dest = new int[64];
		for (int i = 0; i < 64; i++) {
			dest[i] = source[_ip[i] - 1];
		}
		return dest;
	}

	/**
	 * 
	 * 获取轮子密钥(48bit)
	 * 
	 * @param source
	 * 
	 * @return
	 * 
	 */
	private void setKey(String source){
		if (subKey.length > 0)
			subKey = new int[16][48];
		// 装换4bit
		int[] temp = string2Binary(source);
		// 6bit均分成两部分
		int[] left = new int[28];
		int[] right = new int[28];
		// 经过PC-14bit转换6bit
		int[] temp1 = new int[56];
		temp1 = keyPC_1(temp);
		// printArr(temp1);
		// 将经过转换的temp1均分成两部分
		for (int i = 0; i < 28; i++) {
			left[i] = temp1[i];
			right[i] = temp1[i + 28];
		}
		// 经过16次循环左移，然后PC-2置换
		for (int i = 0; i < 16; i++) {
			left = keyLeftMove(left, LS[i]);
			right = keyLeftMove(right, LS[i]);
			for (int j = 0; j < 28; j++) {
				temp1[j] = left[j];
				temp1[j + 28] = right[j];
			}
			// printArr(temp1);
			subKey[i] = keyPC_2(temp1);
		}
	}

	/**
	 * 
	 * 6bit的密钥转换成48bit
	 * @param source
	 * @return
	 * 
	 */
	private int[] keyPC_2(int[] source) {
		int[] dest = new int[48];
		int[] temp = { 14, 17, 11, 24, 1, 5,
				3, 28, 15, 6, 21, 10,
				23, 19, 12, 4, 26, 8,
				16, 7, 27, 20, 13, 2,
				41, 52, 31, 37, 47, 55,
				30, 40, 51, 45, 33, 48,
				44, 49, 39, 56, 34, 53,
				46, 42, 50, 36, 29, 32 };
		for (int i = 0; i < 48; i++) {
			dest[i] = source[temp[i] - 1];
		}
		return dest;
	}

	/**
	 * 
	 * 将密钥循环左移i
	 * @param source 二进制密钥数
	 * @param i 循环左移位数
	 * @return
	 * 
	 */
	private int[] keyLeftMove(int[] source, int i) {
		int temp = 0;
		int len = source.length;
		int ls = LS[i];
		for (int k = 0; k < ls; k++) {
			temp = source[0];
			for (int j = 0; j < len - 1; j++) {
				source[j] = source[j + 1];
			}
			source[len - 1] = temp;
		}
		return source;
	}

	/**
	 * 
	 * 4bit的密钥转换成56bit
	 * @param source
	 * @return
	 * 
	 */
	private int[] keyPC_1(int[] source) {
		int[] dest = new int[56];
		int[] temp = { 57, 49, 41, 33, 25, 17, 9,
				1, 58, 50, 42, 34, 26, 18,
				10, 2, 59, 51, 43, 35, 27,
				19, 11, 3, 60, 52, 44, 36,
				63, 55, 47, 39, 31, 23, 15,
				7, 62, 54, 46, 38, 30, 22,
				14, 6, 61, 53, 45, 37, 29,
				21, 13, 5, 28, 20, 12, 4 };
		for (int i = 0; i < 56; i++) {
			dest[i] = source[temp[i] - 1];
		}
		return dest;
	}

	/**
	 * 
	 * 单长密钥DES(16byte)
	 * @param source
	 * @param key
	 * @param type 0:encrypt 1:discrypt
	 * @return
	 * 
	 */
	private String DES_1(String source, String key, int type) {
		if (source.length() != 16 || key.length() != 16)
			return null;
		if (type == 0) {
			return encryption(source, key);
		}
		if (type == 1) {
			return discryption(source, key);
		}
		return null;
	}

	/**
	 * IP初始置换
	 * @param source
	 * @return
	 */
	private int[] changeIP(int[] source) {
		int[] dest = new int[64];
		for (int i = 0; i < 64; i++) {
			dest[i] = source[ip[i] - 1];
		}
		return dest;
	}

	/**
	 * 
	 * DES加密--->对称密钥
	 * D = Ln(32bit)+Rn(32bit)
	 * 经过16轮置
	 * @param D(16byte)明文
	 * @param K(16byte)轮子密钥
	 * @return (16byte)密文
	 */
	private String encryption(String D, String K) {
		String str = "";
		int[] temp = new int[64];
		int[] data = string2Binary(D);
		// 第一步初始置
		data = changeIP(data);
		int[][] left = new int[17][32];
		int[][] right = new int[17][32];
		for (int j = 0; j < 32; j++) {
			left[0][j] = data[j];
			right[0][j] = data[j + 32];
		}
		setKey(K);// sub key ok
		for (int i = 1; i < 17; i++) {
			// 获取(48bit)的轮子密
			int[] key = subKey[i - 1];
			// L1 = R0
			left[i] = right[i - 1];
			// R1 = L0 ^ f(R0,K1)
			int[] fTemp = f(right[i - 1], key);// 32bit
			right[i] = diffOr(left[i - 1], fTemp);
		}
		// 组合的时候，左右调换**************************************************
		for (int i = 0; i < 32; i++) {
			temp[i] = right[16][i];
			temp[32 + i] = left[16][i];
		}
		temp = changeInverseIP(temp);
		str = binary2ASC(intArr2Str(temp));
		return str;
	}


	/**
	 * 
	 * 将s1和s2做异或，然后返回
	 * @param s1
	 * @param s2
	 * @return
	 * 
	 */
	private String xOr(String s1, String s2) {
		String str = "";
		for (int i = 0; i < s1.length(); i++) {
			int a = Integer.parseInt(s1.substring(i,i+1),16);
			int b = Integer.parseInt(s2.substring(i,i+1),16);
			str += Integer.toHexString(a^b);
		}
		return str;
	}

	/**
	 * 将二进制字符串转换成十六进制字符
	 * @param s
	 * @return
	 */
	private String binary2ASC(String s) {
		String str = "";
		int ii = 0;
		int len = s.length();
		// 不够4bit左补0
		if (len % 4 != 0) {
			while (ii < 4 - len % 4) {
				s = "0" + s;
			}
		}
		for (int i = 0; i < len / 4; i++) {
			str += binary2Hex(s.substring(i * 4, i * 4 + 4));
		}
		return str;
	}
	
	/**
	 * s位长度的二进制字符串
	 * @param s
	 * @return
	 */
	private String binary2Hex(String s) {
		if (s.length() > 4)
			return null;
		int j =Integer.valueOf(s,2);//二进制转十进制  
		return Integer.toHexString(j); //十进制转成十六进制： 
	}


	/**
	 * 将字符串转换成二进制数组
	 * @param source :16字节
	 * @return
	 */
	private int[] string2Binary(String source) {
		int len = source.length();
		int[] str =new int[len * 4];
		char[] arr = source.toCharArray();
		String result="";
		for (int i = 0; i < len; i++) {
			int t = 0;
			t = getIntByChar(arr[i]);
			String strbn = Integer.toBinaryString(t);
			if(strbn.length()<4){
				for(int j=strbn.length();j<4;j++){
					strbn = "0"+strbn;
				}
			}
			result += strbn;
		}
		for (int i = 0; i < result.length(); i++) {
			str[i] = Integer.parseInt(result.substring(i,i+1));
		}
		return str;
	}


	/**
	 * 将十六进制A--F转换成对应数
	 * @param ch
	 * @return
	 * @throws Exception
	 */
	private int getIntByChar(char ch) {
		return Integer.valueOf(ch+"",16);
	}

	/**
	 * 两个等长的数组做异或
	 * @param source1
	 * @param source2
	 * @return
	 */
	private int[] diffOr(int[] source1, int[] source2) {
		int len = source1.length;
		int[] dest = new int[len];
		for (int i = 0; i < len; i++) {
			dest[i] = source1[i] ^ source2[i];
		}
		return dest;
	}

	/**
	 * 将int类型数组拼接成字符串
	 * @param arr
	 * @return
	 */
	private String intArr2Str(int[] arr) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			sb.append(arr[i]);
		}
		return sb.toString();
	}


	/**
	 * 16进制取反
	 * @param numHex
	 * @return
	 */
	private String invert(String numHex){
		String str = "";
		for (int i = 0; i < numHex.length(); i++) {
			int b = 15 - Integer.parseInt(numHex.substring(i , i+1),16);
			str += Integer.toHexString(b);
		}
		return str.toUpperCase();
	}


	/**
	 * 数据进行3des加密
	 * @param key 16字节
	 * @param data 8字节的倍数，不足后补0
	 * @param vector 8字节
	 * @returns
	 */
	private String  DES_CiRCL_EN(String key,String data) {
		if (key.length()!=32){
			return "密钥长度错误！";
		}
		int len = data.length();
		if(len%16!=0){
			return "数据格式错误";
		}
		String result="";
		for(int i=0;i<data.length() / 16;i++){
			String I = data.substring(i * 16, i * 16 + 16);
			result += DES_3(I,key,0);
		}
		return result;
	}


	/**
	 * 计算mac 所有参数为16进制字符串
	 * @param fsyz 分散因子 8字节
	 * @param data  8字节 的倍数 不足8字节的全部补0 
	 * @param random 随机数 4字节
	 */
	public String CALC_MAC(String fsyz,String data,String random){
		if(fsyz.length()!=16)
			return "分散因子长度错误";
		if(random.length()!=8)
			return "随机数长度错误";
		//对分散因子取反
		String d = invert(fsyz);
		String source1 =fsyz+d;
		//得到分散后的密钥K2 16字节
		String K2 = DES_CiRCL_EN(key,source1);
		String vector = random + invert(random);
		//计算获取8字节的mac
		String mac = PBOC_3DES_MAC(data, vector,K2);
		return mac.toUpperCase().trim();
	}

	/**
	 * 校验mac 
	 * @param fsyz 分散因子 8字节
	 * @param data 8字节 的倍数 不足8字节的全部补0
	 * @param random 随机数 4字节
	 * @param mac  8字节
	 */
	public boolean CHECH_MAC(String fsyz,String data,String random,String mac){
		if(fsyz.length()!=16)
			return false;
		if(random.length()!=8)
			return false;
		//对分散因子取反
		String d = invert(fsyz);
		String source1 =fsyz+d;
		//得到分散后的密钥K2 16字节
		String K2 = DES_CiRCL_EN(key,source1);
		String vector = random + invert(random);
		//计算获取8字节的mac
		String calc_mac = PBOC_3DES_MAC(data, vector,K2);
		if(calc_mac.equalsIgnoreCase(mac)){
			return true;
		}else{
			return false;
		}
	}
	
	public static void main(String[] args) {
		PBOC pp= new PBOC();
		String aa=pp.PBOC_3DES_MAC("11223344", "0000000000000000", "11111111111111111111111111111111");
		System.err.println(aa);
	}
}
