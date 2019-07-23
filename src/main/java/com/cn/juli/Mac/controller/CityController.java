package com.cn.juli.Mac.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.cn.juli.Mac.service.CityService;

@RestController
public class CityController {

	@Autowired
	CityService cityService;

	/**
	 * 获取城市名称的接口
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/getCityList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public JSONObject getCityList(@RequestBody Map<String, String> map) {
		return cityService.getCityList(map);
	}
}
