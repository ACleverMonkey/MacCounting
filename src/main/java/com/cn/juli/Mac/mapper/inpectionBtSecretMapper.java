package com.cn.juli.Mac.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface inpectionBtSecretMapper {
	/**
	 * 通过合同号获取秘钥信息
	 * 
	 * @param obuContractNum
	 * @return
	 * @throws Exception
	 */
	String selectBtSecret(String obuContractNum) throws Exception;
}