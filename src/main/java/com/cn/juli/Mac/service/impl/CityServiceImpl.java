package com.cn.juli.Mac.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.cn.juli.Mac.mapper.cityMapper;
import com.cn.juli.Mac.model.City;
import com.cn.juli.Mac.service.CityService;
import com.cn.juli.common.DES;

@Service("CityService")
public class CityServiceImpl implements CityService {

	@Autowired
	cityMapper cityMapper;

	@Override
	public JSONObject getCityList(Map<String, String> map) {
		if (!encrypt(map.get("authenticator"))) {
			return null;
		}
		List<City> cities = cityMapper.getCityList();
		JSONObject json = new JSONObject();
		json.put("code", 10000);
		json.put("msg", "成功");
		json.put("data", cities);
		return json;
	}

	public boolean encrypt(String authenticator) {
		String Key = "5A4B20D12A2ED308C9F82211158E059C";
		String pre = authenticator.substring(0, 8);
		String back = authenticator.substring(8, authenticator.length());
		String preKey = Key.substring(0, Key.length() - 8);
		String preEncrypt = "";
		int j = 0;
		int m = 0;
		for (int i = 0; i < 8; i += 2) {
			String sub = pre.substring(i, i + 2);
			for (; j < 24; j += 8) {
				String subKey = preKey.substring(j, j + 2);
				preEncrypt += xOr(sub, subKey);
			}
			j = m + 2;
			m += 2;
		}
		String offEncrypt = pre + preEncrypt.toUpperCase();
		String result = new DES().DES_DES_DES(offEncrypt, Key).substring(0, 16);
		if (back.equals(result)) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		// System.err.println(encrypt("D425B11CDE3103380093A7DB"));
		/*
		 * tring Key = "5A4B20D12A2ED308C9F82211158E059C"; String authenticator
		 * = "D425B11CDE3103380093A7DB"; String pre = authenticator.substring(0,
		 * 8); String back = authenticator.substring(9, authenticator.length());
		 * String preKey = Key.substring(0, Key.length() - 8); String
		 * preEncrypt=""; int j = 0; int m = 0; for (int i = 0; i < 8; i+=2) {
		 * String sub = pre.substring(i, i + 2); for (; j < 24; j += 8) { String
		 * subKey = preKey.substring(j, j + 2); preEncrypt+=xOr(sub, subKey); }
		 * j = m + 2; m += 2; } String offEncrypt=pre+preEncrypt.toUpperCase();
		 * String result=new DES().DES_DES_DES(offEncrypt, Key);
		 * System.err.println(result);
		 */
	}

	/**
	 * 
	 * 将s1和s2做异或，然后返回
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 * 
	 */
	public static String xOr(String s1, String s2) {
		String str = "";
		for (int i = 0; i < s1.length(); i++) {
			int a = Integer.parseInt(s1.substring(i, i + 1), 16);
			int b = Integer.parseInt(s2.substring(i, i + 1), 16);
			str += Integer.toHexString(a ^ b);
		}
		return str;
	}
}
