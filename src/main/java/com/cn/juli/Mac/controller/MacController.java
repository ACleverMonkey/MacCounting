package com.cn.juli.Mac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.cn.juli.Mac.service.MacService;

@RestController
public class MacController {

	@Autowired
	MacService macService;

	@RequestMapping(value = "/gerMac", method = RequestMethod.POST)
	public JSONObject getMac(String obuContractNum, String rand) {
		return macService.getMac(obuContractNum, rand);
	}
}
