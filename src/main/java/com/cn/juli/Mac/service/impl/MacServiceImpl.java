package com.cn.juli.Mac.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.cn.juli.Mac.mapper.inpectionBtSecretMapper;
import com.cn.juli.Mac.service.MacService;
import com.cn.juli.common.PBOC;

@Service("MacService")
public class MacServiceImpl implements MacService {

	private Logger Logger = LoggerFactory.getLogger(MacServiceImpl.class);

	@Autowired
	inpectionBtSecretMapper inpectionBtSecretMapper;

	@Override
	public JSONObject getMac(String obuContractNum, String rand) {
		JSONObject json = new JSONObject();
		PBOC pp = new PBOC();
		String vector = "0000000000000000";
		try {
			String btSecret = inpectionBtSecretMapper.selectBtSecret(obuContractNum);
			if (btSecret != null) {
				String mac = pp.PBOC_3DES_MAC(rand, vector, btSecret);
				if (mac != null) {
					json.put("code", 10000);
					json.put("date", mac.toUpperCase());
					json.put("msg", null);
					return json;
				} else {
					json.put("code", 10001);
					json.put("date", null);
					json.put("msg", "mac计算错误");
					return json;
				}
			} else {
				json.put("code", 10002);
				json.put("date", btSecret);
				json.put("msg", "该合同号对应的秘钥不存在");
				return json;
			}
		} catch (Exception e) {
			Logger.error("异常信息:" + e + e.getMessage());
			json.put("code", 500);
			json.put("date", null);
			json.put("msg", "系统异常");
			return json;
		}
	}
}
