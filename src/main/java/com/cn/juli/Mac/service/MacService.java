package com.cn.juli.Mac.service;

import com.alibaba.fastjson.JSONObject;

public interface MacService {

	JSONObject   getMac(String obuContractNum,String rand);
}
